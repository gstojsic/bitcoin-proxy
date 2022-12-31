package io.github.gstojsic.bitcoin.proxy;

import io.github.gstojsic.bitcoin.proxy.model.Command;
import io.github.gstojsic.bitcoin.proxy.model.PsbtInput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import java.util.List;
import java.util.Map;

import static io.github.gstojsic.bitcoin.proxy.util.Util.getTestName;
import static org.junit.jupiter.api.Assertions.*;

public class PartiallySignedTransactionTest extends BitcoinDockerBase {

    @Test
    void convertToPsbt(TestInfo info) {
        var c = createWalletProxy(getTestName(info));
        var unspent = fundWallet(c, 102);

        var utxo1 = unspent.get(0);
        var utxo2 = unspent.get(1);

        var input = List.of(
                new PsbtInput(utxo1.getTxId(), utxo1.getVout(), null, null),
                new PsbtInput(utxo2.getTxId(), utxo2.getVout(), null, null)
        );
        var networkInfo = c.getNetworkInfo();
        var recAmount = utxo1.getAmount() + utxo2.getAmount() - networkInfo.getRelayFee();

        var recAddr = c.getNewAddress(null, null);
        var output = Map.of(
                recAddr, Double.toString(recAmount)
        );

        var rawTransaction = c.createRawTransaction(input, output, null, null, null);
        var decodedTx = c.decodeRawTransaction(rawTransaction, null);
        var psbt = c.convertToPsbt(rawTransaction, null, null);
        assertNotNull(psbt);

        var decoded = c.decodePsbt(psbt);
        assertEquals(decodedTx.getTxId(), decoded.getTx().getTxId());
    }

    @Test
    void createPsbtHard(TestInfo info) {
        var c = createWalletProxy(getTestName(info));
        var unspent = fundWallet(c, 102);

        var utxo1 = unspent.get(0);
        var utxo2 = unspent.get(1);

        var input = List.of(
                new PsbtInput(utxo1.getTxId(), utxo1.getVout(), null, null),
                new PsbtInput(utxo2.getTxId(), utxo2.getVout(), null, null)
        );
        var networkInfo = c.getNetworkInfo();
        var recAmount = utxo1.getAmount() + utxo2.getAmount() - networkInfo.getRelayFee();

        var recAddr = c.getNewAddress(null, null);
        var output = Map.of(
                recAddr, Double.toString(recAmount)
        );
        var psbt = c.createPsbt(input, output, null, null, null);
        assertNotNull(psbt);

        var decoded = c.decodePsbt(psbt);
        assertEquals(recAddr, decoded.getTx().getOutputs().get(0).getScriptPubKey().getAddress());
        assertEquals(recAmount, decoded.getTx().getOutputs().get(0).getValue());

        var analysis = c.analyzePsbt(psbt);
        assertEquals("updater", analysis.getNext());

        var walletPsbt = c.walletProcessPsbt(psbt, null, null, null, null);
        assertTrue(walletPsbt.isComplete());

        decoded = c.decodePsbt(walletPsbt.getPsbt());
        assertEquals(2, decoded.getInputs().size());
        assertNotNull(decoded.getInputs().get(0).getWitnessUtxo());
        assertNotNull(decoded.getInputs().get(1).getWitnessUtxo());

        analysis = c.analyzePsbt(walletPsbt.getPsbt());
        assertEquals(2, analysis.getInputs().size());
        var firstA = analysis.getInputs().get(0);
        assertTrue(firstA.isHasUtxo());
        assertTrue(firstA.isFinalInput());
        var secondA = analysis.getInputs().get(1);
        assertTrue(secondA.isHasUtxo());
        assertTrue(secondA.isFinalInput());
    }

    @Test
    void createPsbtEasy(TestInfo info) {
        var c = createWalletProxy(getTestName(info));
        fundWallet(c, 102);
        var recAmount = 0.5;

        var recAddr = c.getNewAddress(null, null);
        var output = Map.of(
                recAddr, Double.toString(recAmount)
        );
        var psbt = c.walletCreateFundedPsbt(List.of(), output, null, null, null, null);
        assertTrue(psbt.getFee() > 0);

        var decoded = c.decodePsbt(psbt.getPsbt());
        assertEquals(1, decoded.getInputs().size());
        assertNotNull(decoded.getInputs().get(0).getWitnessUtxo());

        var walletPsbt = c.walletProcessPsbt(psbt.getPsbt(), null, null, null, null);
        assertTrue(walletPsbt.isComplete());

        var finalizedPsbt = c.finalizePsbt(walletPsbt.getPsbt(), null);
        assertTrue(finalizedPsbt.isComplete());
    }

    @Test
    void joinPsbt(TestInfo info) {
        var c = createWalletProxy(getTestName(info));
        fundWallet(c, 102);
        var recAmount = 0.5;

        var unspent = c.listUnspent(null, null, null, null, null);
        var utxo1 = unspent.get(0);
        var utxo2 = unspent.get(1);

        var input1 = List.of(
                new PsbtInput(utxo1.getTxId(), utxo1.getVout(), null, null)
        );
        var recAddr1 = c.getNewAddress(null, null);
        var output1 = Map.of(
                recAddr1, Double.toString(recAmount)
        );
        var psbt1 = c.createPsbt(input1, output1, null, null, null);

        var input2 = List.of(
                new PsbtInput(utxo2.getTxId(), utxo2.getVout(), null, null)
        );
        var recAddr2 = c.getNewAddress(null, null);
        var output2 = Map.of(
                recAddr2, Double.toString(recAmount)
        );
        var psbt2 = c.createPsbt(input2, output2, null, null, null);

        var joined = c.joinPsbts(List.of(psbt1, psbt2));

        var walletPsbt = c.walletProcessPsbt(joined, null, null, null, null);
        assertTrue(walletPsbt.isComplete());

        var finalizedPsbt = c.finalizePsbt(walletPsbt.getPsbt(), null);
        assertTrue(finalizedPsbt.isComplete());
    }

    @Test
    void usePsbtToSpendMultisig(TestInfo info) {
        var testName = getTestName(info);
        var alice = createWalletProxy("alice" + testName);
        initialFundWallet(alice);

        var bob = createWalletProxy("bob" + testName);

        //generate multisig
        var aliceMultisigAddress = alice.getNewAddress(null, null);
        var aliceMsAddrInfo = alice.getAddressInfo(aliceMultisigAddress);
        var bobMultisigAddress = bob.getNewAddress(null, null);
        var bobMsAddrInfo = bob.getAddressInfo(bobMultisigAddress);

        var aliceMultisig = alice.addMultisigAddress(
                2,
                List.of(aliceMsAddrInfo.getPubKey(), bobMsAddrInfo.getPubKey()),
                null,
                null);
        alice.importAddress(aliceMultisig.getAddress(), null, null, null);

        var bobMultisig = bob.addMultisigAddress(
                2,
                List.of(aliceMsAddrInfo.getPubKey(), bobMsAddrInfo.getPubKey()),
                null,
                null);
        bob.importAddress(bobMultisig.getAddress(), null, null, null);

        //fund multisig
        var send = 1.0;
        var output = Map.of(
                aliceMultisig.getAddress(), Double.toString(send)
        );
        var rawTransaction = alice.createRawTransaction(null, output, null, null, null);
        var transactionFunding = alice.fundRawTransaction(rawTransaction, null, null);
        var signed = alice.signRawTransactionWithWallet(transactionFunding.getHex(), null, null);
        var sentTx = alice.sendRawTransaction(signed.getHex(), null);
        alice.generateToAddress(1, alice.getNewAddress(null, null), null);

        var aUnspent = alice.listUnspent(null, null, null, null, null)
                .stream().filter(t -> t.getTxId().equals(sentTx) && t.getAmount() == send).findFirst().orElseThrow();
        var bUnspent = bob.listUnspent(null, null, null, null, null)
                .stream().filter(t -> t.getTxId().equals(sentTx) && t.getAmount() == send).findFirst().orElseThrow();
        assertEquals(aUnspent.getTxId(), bUnspent.getTxId());
        assertEquals(aUnspent.getVout(), bUnspent.getVout());
        assertEquals(aUnspent.getAddress(), bUnspent.getAddress());

        //split coins from multisig
        var aliceAddr = alice.getNewAddress("spentMultisig", null);
        var bobAddr = bob.getNewAddress("spentMultisig", null);

        //create psbt on bob side
        var psbtInput = List.of(
                new PsbtInput(aUnspent.getTxId(), aUnspent.getVout(), null, null)
        );
        var psbtFee = 0.001;
        var psbtAmount = (aUnspent.getAmount() - psbtFee) / 2;
        var psbtOutput = Map.of(
                aliceAddr, Double.toString(psbtAmount),
                bobAddr, Double.toString(psbtAmount)
        );

        var psbt = bob.createPsbt(psbtInput, psbtOutput, null, null, null);

        var walletPsbt = bob.walletProcessPsbt(psbt, null, null, null, null);

        var alicePsbt = alice.createPsbt(psbtInput, psbtOutput, null, null, null);
        assertEquals(alicePsbt, psbt);
        var aliceWalletPsbt = alice.walletProcessPsbt(alicePsbt, null, null, null, null);

        var combined = alice.combinePsbt(List.of(aliceWalletPsbt.getPsbt(), walletPsbt.getPsbt()));

        var analyzed = alice.analyzePsbt(combined);
        assertTrue("finalizer".equals(analyzed.getNext()) || "extractor".equals(analyzed.getNext()));

        var finalizedPsbt = alice.finalizePsbt(combined, null);

        var raw = alice.sendRawTransaction(finalizedPsbt.getHex(), null);
        //generate block
        alice.generateToAddress(1, alice.getNewAddress(null, null), null);
        var aliceTx = alice.getTransaction(raw, null, null);
        assertEquals(raw, aliceTx.getTxId());
        var bobTx = bob.getTransaction(raw, null, null);
        assertEquals(raw, bobTx.getTxId());
        //var bobUnspent = bob.listUnspent(null, null, null, null, null);
        var bobBalance = bob.getBalance(null, null, null);
        assertEquals(psbtAmount, bobBalance);
        var aliceReceived = alice.getReceivedByLabel("spentMultisig", null, null);
        assertEquals(psbtAmount, aliceReceived);
    }

    @Test
    void poolMoney(TestInfo info) {
        var testName = getTestName(info);
        var alice = createWalletProxy("alice" + testName);
        initialFundWallet(alice);
        var bob = createWalletProxy("bob" + testName);
        var bobReceive = bob.getNewAddress("fromAlice", null);

        //give some money to bob
        var send = 1.0;
        var output = Map.of(
                bobReceive, Double.toString(send)
        );
        var rawTransaction = alice.createRawTransaction(null, output, null, null, null);
        var transactionFunding = alice.fundRawTransaction(rawTransaction, null, null);
        var signed = alice.signRawTransactionWithWallet(transactionFunding.getHex(), null, null);
        alice.sendRawTransaction(signed.getHex(), null);
        alice.generateToAddress(1, alice.getNewAddress(null, null), null);

        var bobReceivedAmt = bob.getReceivedByLabel("fromAlice", null, null);
        assertEquals(send, bobReceivedAmt);

        //generate multisig
        var aliceMultisigAddress = alice.getNewAddress(null, null);
        var aliceMsAddrInfo = alice.getAddressInfo(aliceMultisigAddress);
        var bobMultisigAddress = bob.getNewAddress(null, null);
        var bobMsAddrInfo = bob.getAddressInfo(bobMultisigAddress);

        var aliceMultisig = alice.addMultisigAddress(
                2,
                List.of(aliceMsAddrInfo.getPubKey(), bobMsAddrInfo.getPubKey()),
                null,
                null);
        alice.importAddress(aliceMultisig.getAddress(), null, null, null);

        var bobMultisig = bob.addMultisigAddress(
                2,
                List.of(aliceMsAddrInfo.getPubKey(), bobMsAddrInfo.getPubKey()),
                null,
                null);
        bob.importAddress(bobMultisig.getAddress(), null, null, null);
        assertEquals(bobMultisig.getAddress(), aliceMultisig.getAddress());
        assertEquals(bobMultisig.getRedeemScript(), aliceMultisig.getRedeemScript());

        //find funds
        var aliceFunds = alice.listUnspent(null, null, null, null, null).stream().filter(t -> t.getAmount() < 50).findFirst().orElseThrow();
        var bobFunds = bob.listUnspent(null, null, null, null, null).get(0);
        var aliceChange = alice.getRawChangeAddress(null);
        var bobChange = bob.getRawChangeAddress(null);

        //create psbt
        var psbtInput = List.of(
                new PsbtInput(aliceFunds.getTxId(), aliceFunds.getVout(), null, null),
                new PsbtInput(bobFunds.getTxId(), bobFunds.getVout(), null, null)
        );
        var psbtSend = 1.0;
        var psbtOutput = Map.of(
                bobMultisig.getAddress(), Double.toString(psbtSend),
                aliceChange, Double.toString(aliceFunds.getAmount() - 0.5 - 0.001),
                bobChange, Double.toString(bobFunds.getAmount() - 0.5 - 0.00001)
        );

        var psbt = bob.createPsbt(psbtInput, psbtOutput, null, null, null);
        var bobPsbt = bob.walletProcessPsbt(psbt, null, null, null, null);
        var alicePsbt = alice.walletProcessPsbt(bobPsbt.getPsbt(), null, null, null, null);
        var analyzed = alice.analyzePsbt(alicePsbt.getPsbt());
        assertEquals(2, analyzed.getInputs().size());
        assertTrue(analyzed.getInputs().get(0).isHasUtxo());
        assertTrue(analyzed.getInputs().get(0).isFinalInput());
        assertTrue(analyzed.getInputs().get(1).isHasUtxo());
        assertTrue(analyzed.getInputs().get(1).isFinalInput());
        assertEquals(0.00101, analyzed.getFee());
        assertEquals("extractor", analyzed.getNext());

        var finalized = alice.finalizePsbt(alicePsbt.getPsbt(), null);
        assertTrue(finalized.isComplete());
        assertNotNull(finalized.getHex());

        alice.sendRawTransaction(finalized.getHex(), null);

        bob.generateToAddress(1, bob.getNewAddress(null, null), null);
        var aliceBalance = alice.getBalance(null, null, null);
        var bobBalance = bob.getBalance(null, null, null);
        //both see the multisig coin on their balance, 0.00101 is psbt fee and 0.0000141 is fee alice paid to send first coin to bob
        assertEquals(50 + 1, aliceBalance + bobBalance + 0.00101 + 0.0000141);
    }

    @Test
    void coinJoin(TestInfo info) {
        var testName = getTestName(info);
        var alice = createWalletProxy("alice" + testName);
        initialFundWallet(alice);
        var bob = createWalletProxy("bob" + testName);
        var bobReceive = bob.getNewAddress("fromAlice", null);

        //give some money to bob
        var send = 1.0;
        var output = Map.of(
                bobReceive, Double.toString(send)
        );
        var rawTransaction = alice.createRawTransaction(null, output, null, null, null);
        var transactionFunding = alice.fundRawTransaction(rawTransaction, null, null);
        var signed = alice.signRawTransactionWithWallet(transactionFunding.getHex(), null, null);
        var sentTx = alice.sendRawTransaction(signed.getHex(), null);
        bob.generateToAddress(1, bob.getNewAddress(null, null), null);

        var bobReceivedAmt = bob.getReceivedByLabel("fromAlice", null, null);
        assertEquals(send, bobReceivedAmt);

        //find funds
        var aliceFunds = alice.listUnspent(null, null, null, null, null).stream().filter(t -> t.getAmount() < 50).findFirst().orElseThrow();
        var bobFunds = bob.listUnspent(null, null, null, null, null).get(0);
        var aliceChange = alice.getRawChangeAddress(null);
        var bobChange = bob.getRawChangeAddress(null);

        //create psbt
        var psbtInput = List.of(
                new PsbtInput(aliceFunds.getTxId(), aliceFunds.getVout(), null, null),
                new PsbtInput(bobFunds.getTxId(), bobFunds.getVout(), null, null)
        );
        var fee = 0.001;
        var toSend = 0.5;
        //alice sends another half coin to bob
        var psbtOutput = Map.of(
                aliceChange, Double.toString(aliceFunds.getAmount() - toSend - fee),
                bobChange, Double.toString(bobFunds.getAmount() + toSend)
        );

        // bob creates psbt and signs
        var psbt = bob.createPsbt(psbtInput, psbtOutput, null, null, null);
        var bobWalletPsbt = bob.walletProcessPsbt(psbt, null, null, null, null);

        // alice creates psbt and signs
        var alicePsbt = alice.createPsbt(psbtInput, psbtOutput, null, null, null);
        assertEquals(alicePsbt, psbt);
        var aliceWalletPsbt = alice.walletProcessPsbt(alicePsbt, null, null, null, null);

        // alice combines and finalizes
        var combined = alice.combinePsbt(List.of(aliceWalletPsbt.getPsbt(), bobWalletPsbt.getPsbt()));
        var finalizedPsbt = alice.finalizePsbt(combined, null);
        var raw = alice.sendRawTransaction(finalizedPsbt.getHex(), null);

        //generate block
        var generated = bob.generateBlock(bob.getNewAddress(null, null), List.of(raw));
        assertNotNull(generated);

        var aliceTx = alice.getTransaction(raw, null, null);
        assertEquals(raw, aliceTx.getTxId());
        var bobTx = bob.getTransaction(raw, null, null);
        assertEquals(raw, bobTx.getTxId());
        var bobBalance = bob.getBalance(null, null, null);
        var aliceBalance = alice.getBalance(null, null, null);
        //inputs must match outputs + fee
        assertEquals(aliceFunds.getAmount() + bobFunds.getAmount(), aliceBalance + bobBalance + fee);
    }

    @Test
    void psbtBumpFee(TestInfo info) {
        var c = createWalletProxy(getTestName(info));
        var unspent = fundWallet(c, 101);

        var utxo1 = unspent.get(0);

        var input = List.of(
                new PsbtInput(utxo1.getTxId(), utxo1.getVout(), 1, null)
        );
        var fee = c.getNetworkInfo().getRelayFee();

        var total = utxo1.getAmount();
        var recAmount = 1.0;
        var change = total - recAmount - fee;

        var recAddr = c.getNewAddress(null, null);
        var changeAddr = c.getRawChangeAddress(null);
        var output = Map.of(
                recAddr, Double.toString(recAmount),
                changeAddr, Double.toString(change)
        );
        var psbt = c.createPsbt(input, output, null, null, null);
        assertNotNull(psbt);

        var walletPsbt = c.walletProcessPsbt(psbt, null, null, null, null);
        assertTrue(walletPsbt.isComplete());

        var finalizedPsbt = c.finalizePsbt(walletPsbt.getPsbt(), null);

        var raw = c.sendRawTransaction(finalizedPsbt.getHex(), null);
        var bumpFee = c.psbtBumpFee(raw, null);
        assertTrue(bumpFee.getErrors().isEmpty());
        assertNotNull(bumpFee.getPsbt());
        assertTrue(bumpFee.getOrigFee() < bumpFee.getFee()); //fee increased
    }

    @Test
    void utxoUpdatePsbt(TestInfo info) {
        var c = createWalletProxy(getTestName(info));
        var unspent = fundWallet(c, 101);

        var utxo1 = unspent.get(0);

        var input = List.of(
                new PsbtInput(utxo1.getTxId(), utxo1.getVout(), null, null)
        );
        var fee = c.getNetworkInfo().getRelayFee();

        var total = utxo1.getAmount();
        var recAmount = 1.0;
        var change = total - recAmount - fee;

        var recAddr = c.getNewAddress(null, null);
        var changeAddr = c.getRawChangeAddress(null);
        var output = Map.of(
                recAddr, Double.toString(recAmount),
                changeAddr, Double.toString(change)
        );
        var psbt = c.createPsbt(input, output, null, null, null);
        assertNotNull(psbt);

        var walletPsbt = c.walletProcessPsbt(psbt, null, null, null, null);
        assertTrue(walletPsbt.isComplete());

        var updated = c.utxoUpdatePsbt(walletPsbt.getPsbt(), null);
        assertFalse(updated.isEmpty());
    }

    @Test
    void utxoUpdatePsbtDesc(TestInfo info) {
        var c = createWalletProxy(getTestName(info));
        var unspent = fundWallet(c, 101);

        var utxo1 = unspent.get(0);

        var input = List.of(
                new PsbtInput(utxo1.getTxId(), utxo1.getVout(), null, null)
        );
        var fee = c.getNetworkInfo().getRelayFee();

        var total = utxo1.getAmount();
        var recAmount = 1.0;
        var change = total - recAmount - fee;

        var recAddr = c.getNewAddress(null, null);
        var changeAddr = c.getRawChangeAddress(null);
        var output = Map.of(
                recAddr, Double.toString(recAmount),
                changeAddr, Double.toString(change)
        );
        var psbt = c.createPsbt(input, output, null, null, null);
        assertNotNull(psbt);

        var walletPsbt = c.walletProcessPsbt(psbt, null, null, null, null);
        assertTrue(walletPsbt.isComplete());

        var updated = c.utxoUpdatePsbtDesc(walletPsbt.getPsbt(), null);
        assertFalse(updated.isEmpty());
    }

    @Test
    void help() {
        var help = proxy.help(Command.getreceivedbylabel);
        System.out.println(help);
    }
}
