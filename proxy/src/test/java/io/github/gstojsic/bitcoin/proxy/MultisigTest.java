package io.github.gstojsic.bitcoin.proxy;

import io.github.gstojsic.bitcoin.proxy.model.AddressType;
import io.github.gstojsic.bitcoin.proxy.model.ImportData;
import io.github.gstojsic.bitcoin.proxy.model.ImportType;
import io.github.gstojsic.bitcoin.proxy.model.PrevTx;
import io.github.gstojsic.bitcoin.proxy.model.PsbtInput;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class MultisigTest extends BitcoinDockerBase {
    @Test
    void multisig() {
        proxy.createWallet(
                "aliceMultisig",
                null,
                null,
                null,
                true,
                null,
                null,
                null);

        var aliceProxy = createProxy("aliceMultisig");

        proxy.createWallet(
                "bobMultisig",
                null,
                null,
                null,
                true,
                null,
                null,
                null);

        var bobProxy = createProxy("bobMultisig");

        var miningAddress = aliceProxy.getNewAddress(null, null);
        aliceProxy.generateToAddress(101, miningAddress, null);

        var aliceMultisigAddress = aliceProxy.getNewAddress(null, null);
        var aliceMsAddrInfo = aliceProxy.getAddressInfo(aliceMultisigAddress);
        var bobMultisigAddress = bobProxy.getNewAddress(null, null);
        var bobMsAddrInfo = bobProxy.getAddressInfo(bobMultisigAddress);

        var keys = List.of(
                aliceMsAddrInfo.getPubKey(),
                bobMsAddrInfo.getPubKey()
        );
        var multisig = aliceProxy.createMultisig(2, keys, null);

        //generate
        var unspent = aliceProxy.listUnspent(null, null, null, null, null).get(0);
        var changeAddress = aliceProxy.getRawChangeAddress(AddressType.LEGACY);

        var input = List.of(
                new PsbtInput(unspent.getTxId(), unspent.getVout(), null, null)
        );
        var fee = aliceProxy.getNetworkInfo().getRelayFee();
        var total = unspent.getAmount();
        var send = 1.0;
        var change = total - send - fee;

        var output = Map.of(
                multisig.getAddress(), Double.toString(send),
                changeAddress, Double.toString(change)
        );
        var rawTransaction = aliceProxy.createRawTransaction(input, output, null, null, null);

        var signed = aliceProxy.signRawTransactionWithWallet(rawTransaction, null, null);

        var sentTx = aliceProxy.sendRawTransaction(signed.getHex(), null);

        var aliceBalance = aliceProxy.getBalance(null, null, null);
        assertEquals(change, aliceBalance);

        aliceProxy.importAddress(multisig.getAddress(), null, null, null);
        var multisigTransaction = aliceProxy.getTransaction(sentTx, null, true);
        var multisigTxOut = multisigTransaction.getDecoded().getOutputs().stream()
                .filter(o -> "scripthash".equals(o.getScriptPubKey().getType()))
                .findFirst().orElseThrow();

        var multisigChangeAddr = aliceProxy.getRawChangeAddress(null);

        // alice creates trans and signs with her pk
        var multisigInput = List.of(
                new PsbtInput(sentTx, multisigTxOut.getN(), null, null)
        );

        var multisigOutput = Map.of(
                multisigChangeAddr, Double.toString(send - 0.0001)
        );
        var multisigRawTransaction = aliceProxy.createRawTransaction(multisigInput, multisigOutput, null, null, null);

        var aliceMultisigPrivKey = aliceProxy.dumpPrivKey(aliceMultisigAddress);

        List<PrevTx> previousTxs = List.of(new PrevTx(sentTx, multisigTxOut.getN(), multisigTxOut.getScriptPubKey().getHex(), multisig.getRedeemScript(), null, null));
        var aliceSignPkRes = aliceProxy.signRawTransactionWithKey(
                multisigRawTransaction,
                List.of(aliceMultisigPrivKey),
                previousTxs,
                null);
        assertFalse(aliceSignPkRes.isComplete());
        assertFalse(aliceSignPkRes.getErrors().isEmpty());
        var aliceSignPkError = aliceSignPkRes.getErrors().get(0);
        assertTrue(aliceSignPkError.getError().contains("failing with non-zero signature"));

        // bob signs
        var bobMultisigPrivKey = bobProxy.dumpPrivKey(bobMultisigAddress);
        var bobSignPkRes = bobProxy.signRawTransactionWithKey(
                aliceSignPkRes.getHex(),
                List.of(bobMultisigPrivKey),
                previousTxs,
                null);
        assertTrue(bobSignPkRes.isComplete());
        assertNull(bobSignPkRes.getErrors());

        var newAliceBalance = aliceProxy.getBalance(null, null, null);
        assertEquals(aliceBalance + send, newAliceBalance);
    }

    @Test
    void multisigAutomated() {
        proxy.createWallet(
                "aliceMultisigAutomated",
                null,
                null,
                null,
                true,
                null,
                null,
                null);

        var aliceProxy = createProxy("aliceMultisigAutomated");

        proxy.createWallet(
                "bobMultisigAutomated",
                null,
                null,
                null,
                true,
                null,
                null,
                null);

        var bobProxy = createProxy("bobMultisigAutomated");

        var miningAddress = aliceProxy.getNewAddress(null, null);
        aliceProxy.generateToAddress(101, miningAddress, null);

        var aliceMultisigAddress = aliceProxy.getNewAddress(null, null);
        var aliceMsAddrInfo = aliceProxy.getAddressInfo(aliceMultisigAddress);
        var bobMultisigAddress = bobProxy.getNewAddress(null, null);
        var bobMsAddrInfo = bobProxy.getAddressInfo(bobMultisigAddress);

        var aliceMultisig = aliceProxy.addMultisigAddress(2,
                List.of(aliceMultisigAddress, bobMsAddrInfo.getPubKey()),
                null,
                null);

        var bobMultisig = bobProxy.addMultisigAddress(2,
                List.of(aliceMsAddrInfo.getPubKey(), bobMultisigAddress),
                null,
                null);
        assertEquals(aliceMultisig.getAddress(), bobMultisig.getAddress());
        assertEquals(aliceMultisig.getRedeemScript(), bobMultisig.getRedeemScript());

        var decoded = aliceProxy.decodeScript(aliceMultisig.getRedeemScript());
        assertEquals("multisig", decoded.getType());

        var aliceImport = aliceProxy.importMulti(
                List.of(new ImportData(
                        ImportType.descriptor,
                        aliceMultisig.getDescriptor(),
                        null,
                        "now",
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null)),
                null
        );
        assertEquals(1, aliceImport.size());
        assertTrue(aliceImport.get(0).isSuccess());

        var bobImport = bobProxy.importMulti(
                List.of(new ImportData(
                        ImportType.descriptor,
                        bobMultisig.getDescriptor(),
                        null,
                        "now",
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null)),
                null
        );
        assertEquals(1, bobImport.size());
        assertTrue(bobImport.get(0).isSuccess());

        var changeAddress = aliceProxy.getRawChangeAddress(null);
        var unspent = aliceProxy.listUnspent(null, null, null, null, null).get(0);

        var input = List.of(
                new PsbtInput(unspent.getTxId(), unspent.getVout(), null, null)
        );
        var fee = aliceProxy.getNetworkInfo().getRelayFee();
        var total = unspent.getAmount();
        var send = 1.0;
        var change = total - send - fee;

        var output = Map.of(
                aliceMultisig.getAddress(), Double.toString(send),
                changeAddress, Double.toString(change)
        );
        var rawTransaction = aliceProxy.createRawTransaction(input, output, null, null, null);
        var signed = aliceProxy.signRawTransactionWithWallet(rawTransaction, null, null);
        var sentTx = aliceProxy.sendRawTransaction(signed.getHex(), null);
        var aliceBalance = aliceProxy.getBalance(null, null, null);

        var multisigTransaction = aliceProxy.getTransaction(sentTx, null, true);
        var multisigTxOut = multisigTransaction.getDecoded().getOutputs().stream()
                .filter(o -> o.getScriptPubKey().getType().contains("scripthash"))
                .findFirst().orElseThrow();

        var bobRcvAddr = bobProxy.getNewAddress(null, null);
        //alice sends from multisig to bob
        var multisigInput = List.of(
                new PsbtInput(multisigTransaction.getTxId(), multisigTxOut.getN(), null, null)
        );

        var multisigOutput = Map.of(
                bobRcvAddr, Double.toString(send - 0.0001)
        );
        var aliceMsRawTx = aliceProxy.createRawTransaction(multisigInput, multisigOutput, null, null, null);
        var aliceMsSigned = aliceProxy.signRawTransactionWithWallet(aliceMsRawTx, null, null);
        assertFalse(aliceMsSigned.isComplete());
        assertFalse(aliceMsSigned.getErrors().isEmpty());
        var aliceSignPkError = aliceMsSigned.getErrors().get(0);
        assertTrue(aliceSignPkError.getError().contains("failing with non-zero signature"));

        //bob signs
        var bobMsSigned = bobProxy.signRawTransactionWithWallet(aliceMsSigned.getHex(), null, null);
        assertTrue(bobMsSigned.isComplete());
        assertNull(bobMsSigned.getErrors());
        //bob sends tx
        bobProxy.sendRawTransaction(bobMsSigned.getHex(), null);

        var bobMiningAddress = bobProxy.getNewAddress(null, null);
        bobProxy.generateToAddress(1, bobMiningAddress, null);

        var newAliceBalance = aliceProxy.getBalance(null, null, null);
        assertEquals(aliceBalance - send, newAliceBalance);

        var newBobBalance = bobProxy.getBalance(null, null, null);
        assertEquals(send - 0.0001, newBobBalance);
    }
}
