package io.github.gstojsic.bitcoin.proxy;

import io.github.gstojsic.bitcoin.proxy.json.model.BlockStats;
import io.github.gstojsic.bitcoin.proxy.model.AddressType;
import io.github.gstojsic.bitcoin.proxy.model.PsbtInput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static io.github.gstojsic.bitcoin.proxy.util.Util.getTestName;
import static org.junit.jupiter.api.Assertions.*;

public class RawTransactionTest extends BitcoinDockerBase {
    @Test
    void rawTransaction(TestInfo info) {
        var aliceProxy = createWalletProxy("alice" + getTestName(info));
        var bobProxy = createWalletProxy("bob" + getTestName(info));

        var address = aliceProxy.getNewAddress(null, null);
        var hashes = aliceProxy.generateToAddress(101, address, null);

        //getBlockStats interlude
        BlockStats blockStats = aliceProxy.getBlockStats(hashes.get(100), null);
        assertNotNull(blockStats);
        assertEquals(hashes.get(100), blockStats.getBlockhash());
        assertEquals(101, blockStats.getHeight());

        BlockStats blockStatsHeight = aliceProxy.getBlockStats(101, null);
        assertEquals(hashes.get(100), blockStatsHeight.getBlockhash());
        assertEquals(101, blockStatsHeight.getHeight());
        //end getBlockStats interlude

        var unspent = aliceProxy.listUnspent(null, null, null, null, null).get(0);

        var recAddr = bobProxy.getNewAddress("payout", null);
        var input = List.of(
                new PsbtInput(unspent.getTxId(), unspent.getVout(), null, null)
        );
        var networkInfo = aliceProxy.getNetworkInfo();
        var recAmount = unspent.getAmount() - networkInfo.getRelayFee();

        var output = Map.of(
                recAddr, Double.toString(recAmount)
        );
        var rawTransaction = aliceProxy.createRawTransaction(input, output, null, null, null);

        var decoded = aliceProxy.decodeRawTransaction(rawTransaction, null);
        var decodedInputs = decoded.getInputs();
        assertEquals(1, decodedInputs.size());
        var decodedInput = decodedInputs.get(0);
        assertEquals(unspent.getTxId(), decodedInput.getTxId());
        assertEquals(unspent.getVout(), decodedInput.getVout());

        var decodedOutputs = decoded.getOutputs();
        assertEquals(1, decodedOutputs.size());
        var decodedOutput = decodedOutputs.get(0);
        assertEquals(recAmount, decodedOutput.getValue());
        assertEquals(recAddr, decodedOutput.getScriptPubKey().getAddress());

        var signed = aliceProxy.signRawTransactionWithWallet(rawTransaction, null, null);
        assertTrue(signed.isComplete());
        assertFalse(signed.getHex().isEmpty());
        assertNull(signed.getErrors());

        var mempoolAccept = aliceProxy.testMempoolAccept(List.of(signed.getHex()), null).get(0);
        assertEquals(decoded.getTxId(), mempoolAccept.getTxId());
        assertTrue(mempoolAccept.getAllowed());

        var txHash = aliceProxy.sendRawTransaction(signed.getHex(), null);
        assertFalse(txHash.isEmpty());

        var mempool = aliceProxy.getMempoolEntry(decoded.getTxId());
        assertNotNull(mempool);

        var raw = aliceProxy.getRawTransaction(decoded.getTxId(), null);
        assertEquals(signed.getHex(), raw);
        var rawVerbose = aliceProxy.getRawTransactionVerbose(decoded.getTxId(), null);
        assertEquals(signed.getHex(), rawVerbose.getHex());

        var utxo = rawVerbose.getOutputs().get(0);
        var txOut = aliceProxy.getTxOut(rawVerbose.getTxId(), utxo.getN(), true);
        assertEquals(utxo.getValue(), txOut.getValue());
        assertEquals(utxo.getScriptPubKey().getAddress(), txOut.getScriptPubKey().getAddress());
        assertEquals("witness_v0_keyhash", txOut.getScriptPubKey().getType());

        var listUnspent = aliceProxy.listUnspent(null, null, null, null, null);
        assertTrue(listUnspent.isEmpty());
        var aliceBalance = aliceProxy.getBalance(null, null, null);
        assertEquals(0.0, aliceBalance);

        var bobTransactions = bobProxy.listTransactions("payout", null, null, null);
        assertEquals(1, bobTransactions.size());
        var bobTransaction = bobTransactions.get(0);
        assertEquals(recAmount, bobTransaction.getAmount());
        assertEquals(recAddr, bobTransaction.getAddress());
        assertEquals("receive", bobTransaction.getCategory());
        assertEquals(txHash, bobTransaction.getTxId());

        bobProxy.generateToAddress(1, address, null);
        var bobBalance = bobProxy.getBalance(null, null, null);
        assertEquals(recAmount, bobBalance);
    }

    @Test
    public void rawTransactionWithChange(TestInfo info) {
        var aliceProxy = createWalletProxy("alice" + getTestName(info));
        var bobProxy = createWalletProxy("bob" + getTestName(info));

        var address = aliceProxy.getNewAddress(null, null);
        aliceProxy.generateToAddress(101, address, null);

        var unspent = aliceProxy.listUnspent(null, null, null, null, null).get(0);
        var changeAddress = aliceProxy.getRawChangeAddress(AddressType.LEGACY);

        var recAddr = bobProxy.getNewAddress("payout", null);
        var input = List.of(
                new PsbtInput(unspent.getTxId(), unspent.getVout(), null, null)
        );
        var fee = aliceProxy.getNetworkInfo().getRelayFee();
        var total = unspent.getAmount();
        var send = 1.0;
        var change = total - send - fee;

        var output = Map.of(
                recAddr, Double.toString(send),
                changeAddress, Double.toString(change)
        );
        var rawTransaction = aliceProxy.createRawTransaction(input, output, null, null, null);

        var signed = aliceProxy.signRawTransactionWithWallet(rawTransaction, null, null);

        aliceProxy.sendRawTransaction(signed.getHex(), null);

        var aliceBalance = aliceProxy.getBalance(null, null, null);
        assertEquals(change, aliceBalance);

        bobProxy.generateToAddress(1, address, null);
        var bobBalance = bobProxy.getBalance(null, null, null);
        assertEquals(send, bobBalance);
    }

    @Test
    public void rawTransactionAutomated(TestInfo info) {
        var aliceProxy = createWalletProxy("alice" + getTestName(info));
        var bobProxy = createWalletProxy("bob" + getTestName(info));

        var address = aliceProxy.getNewAddress("aliceMining", null);
        aliceProxy.generateToAddress(101, address, null);
        var aliceBalance = aliceProxy.getBalance(null, null, null);

        var recAddr = bobProxy.getNewAddress("payout", null);
        var send = 1.0;

        var output = Map.of(
                recAddr, Double.toString(send)
        );
        var rawTransaction = aliceProxy.createRawTransaction(null, output, null, null, null);

        var transactionFunding = aliceProxy.fundRawTransaction(rawTransaction, null, null);
        var decoded = aliceProxy.decodeRawTransaction(transactionFunding.getHex(), null);

        var txOutput1 = decoded.getOutputs().get(0);
        var txOutput2 = decoded.getOutputs().get(1);
        assertEquals(aliceBalance, txOutput1.getValue() + txOutput2.getValue() + transactionFunding.getFee());

        var signed = aliceProxy.signRawTransactionWithWallet(transactionFunding.getHex(), null, null);

        aliceProxy.sendRawTransaction(signed.getHex(), null);

        var change = txOutput1.getValue() == send ? txOutput2.getValue() : txOutput1.getValue();
        aliceBalance = aliceProxy.getBalance(null, null, null);
        assertEquals(change, aliceBalance);

        bobProxy.generateToAddress(1, address, null);
        var bobBalance = bobProxy.getBalance(null, null, null);
        assertEquals(send, bobBalance);
        var bobUnspent = bobProxy.listUnspent(null, null, null, null, null).get(0);
        assertTrue(bobUnspent.getDesc().startsWith("wpkh")); //segwit
        var groupings = aliceProxy.listAddressGroupings();
        assertEquals(1, groupings.size());
        var grouping = groupings.get(0);
        assertEquals(2, grouping.size());
        var part = grouping.stream().collect(Collectors.partitioningBy(c -> "aliceMining".equals(c.getLabel())));
        var aliceMiningGroup = part.get(true);
        assertEquals(1, aliceMiningGroup.size());
        var aliceMining = aliceMiningGroup.get(0);
        assertEquals("aliceMining", aliceMining.getLabel());
        var otherGroup = part.get(false);
        assertEquals(1, otherGroup.size());
        var other = otherGroup.get(0);
        assertNull(other.getLabel());
    }

    @Test
    public void combineRawTransaction(TestInfo info) {
        var alice = createWalletProxy("alice" + getTestName(info));
        var bob = createWalletProxy("bob" + getTestName(info));
        fundWallet(alice, 101);
        var aliceBalance = alice.getBalance(null, null, null);

        var recAddr1 = bob.getNewAddress("payout1", null);
        var send1 = 1.0;
        var output1 = Map.of(
                recAddr1, Double.toString(send1)
        );
        var rawTransaction1 = alice.createRawTransaction(null, output1, null, null, null);

        var transactionFunding1 = alice.fundRawTransaction(rawTransaction1, null, null);
        var signed1 = alice.signRawTransactionWithWallet(transactionFunding1.getHex(), null, null);

        var recAddr2 = alice.getNewAddress("another address", null);
        var send2 = 0.5;
        var output2 = Map.of(
                recAddr2, Double.toString(send2)
        );
        var rawTransaction2 = alice.createRawTransaction(null, output2, null, null, null);

        var transactionFunding2 = alice.fundRawTransaction(rawTransaction2, null, null);
        var signed2 = alice.signRawTransactionWithWallet(transactionFunding2.getHex(), null, null);

        //this is probably not how combineRawTransaction should be used. the rpc goes through
        var combined = alice.combineRawTransaction(List.of(signed2.getHex(), signed1.getHex()));
        assertNotNull(combined);
    }

    @Test
    public void rawTransactionRBF(TestInfo info) {
        var aliceProxy = createWalletProxy("alice" + getTestName(info));
        var bobProxy = createWalletProxy("bob" + getTestName(info));

        var address = aliceProxy.getNewAddress(null, null);
        aliceProxy.generateToAddress(101, address, null);

        var unspent = aliceProxy.listUnspent(null, null, null, null, null).get(0);
        var changeAddress = aliceProxy.getRawChangeAddress(AddressType.LEGACY);

        var recAddr = bobProxy.getNewAddress("payout", null);
        var input = List.of(
                new PsbtInput(unspent.getTxId(), unspent.getVout(), 1, null) //RBF
        );
        var fee = aliceProxy.getNetworkInfo().getRelayFee();
        var total = unspent.getAmount();
        var send = 1.0;
        var change = total - send - fee;

        var output = Map.of(
                recAddr, Double.toString(send),
                changeAddress, Double.toString(change)
        );
        var rawTransaction = aliceProxy.createRawTransaction(input, output, null, null, null);

        var signed = aliceProxy.signRawTransactionWithWallet(rawTransaction, null, null);

        var txOriginal = aliceProxy.sendRawTransaction(signed.getHex(), null);

        var txInfo = aliceProxy.getTransaction(txOriginal, null, true);
        assertEquals(txOriginal, txInfo.getTxId());
        assertEquals(0, txInfo.getConfirmations()); //RBF
        assertEquals("yes", txInfo.getBip125Replaceable()); //RBF

        output = Map.of(
                recAddr, Double.toString(send),
                changeAddress, Double.toString(change - (2 * fee)) // triple the fee
        );
        rawTransaction = aliceProxy.createRawTransaction(input, output, null, null, null);

        signed = aliceProxy.signRawTransactionWithWallet(rawTransaction, null, null);

        var tx = aliceProxy.sendRawTransaction(signed.getHex(), null);

        txInfo = aliceProxy.getTransaction(txOriginal, null, true);
        assertEquals(tx, txInfo.getWalletConflicts().get(0));

        var aliceBalance = aliceProxy.getBalance(null, null, null);
        assertEquals(change - (2 * fee), aliceBalance);

        bobProxy.generateToAddress(1, address, null);
        var bobBalance = bobProxy.getBalance(null, null, null);
        assertEquals(send, bobBalance);

        txInfo = aliceProxy.getTransaction(txOriginal, null, true);
        assertEquals(-1, txInfo.getConfirmations());

        txInfo = aliceProxy.getTransaction(tx, null, true);
        assertEquals(1, txInfo.getConfirmations());
    }

    @Test
    public void rawTransactionBumpFee(TestInfo info) {
        var aliceProxy = createWalletProxy("alice" + getTestName(info));
        var bobProxy = createWalletProxy("bob" + getTestName(info));

        var address = aliceProxy.getNewAddress(null, null);
        aliceProxy.generateToAddress(101, address, null);

        var unspent = aliceProxy.listUnspent(null, null, null, null, null).get(0);
        var changeAddress = aliceProxy.getRawChangeAddress(null);

        var recAddr = bobProxy.getNewAddress("payout", null);
        var input = List.of(
                new PsbtInput(unspent.getTxId(), unspent.getVout(), 1, null) //RBF
        );
        var fee = aliceProxy.getNetworkInfo().getRelayFee();
        var total = unspent.getAmount();
        var send = 1.0;
        var change = total - send - fee;

        var output = Map.of(
                recAddr, Double.toString(send),
                changeAddress, Double.toString(change)
        );
        var rawTransaction = aliceProxy.createRawTransaction(input, output, null, null, null);

        var signed = aliceProxy.signRawTransactionWithWallet(rawTransaction, null, null);

        var txOriginal = aliceProxy.sendRawTransaction(signed.getHex(), null);

        var txInfo = aliceProxy.getTransaction(txOriginal, null, true);
        assertEquals(txOriginal, txInfo.getTxId());
        assertEquals(0, txInfo.getConfirmations()); //RBF
        assertEquals("yes", txInfo.getBip125Replaceable()); //RBF

        var bumpFee = aliceProxy.bumpFee(txOriginal, null);
        assertTrue(bumpFee.getErrors().isEmpty());
        assertTrue(bumpFee.getOrigFee() <= bumpFee.getFee());

        var bumpedTx = aliceProxy.getTransaction(bumpFee.getTxId(), null, null);
        assertEquals(-1, bumpedTx.getAmount());
        assertEquals(txOriginal, bumpedTx.getReplacesTxId());

        var aliceBalance = aliceProxy.getBalance(null, null, null);
        assertEquals(total - send - bumpFee.getFee(), aliceBalance);

        bobProxy.generateToAddress(1, address, null);
        var bobBalance = bobProxy.getBalance(null, null, null);
        assertEquals(send, bobBalance);
    }

    @Test
    public void rawTransactionCPFP(TestInfo info) {
        var aliceProxy = createWalletProxy("alice" + getTestName(info));
        var bobProxy = createWalletProxy("bob" + getTestName(info));

        var address = aliceProxy.getNewAddress(null, null);
        aliceProxy.generateToAddress(101, address, null);

        var unspent = aliceProxy.listUnspent(null, null, null, null, null).get(0);
        var changeAddress = aliceProxy.getRawChangeAddress(null);

        var recAddr = bobProxy.getNewAddress("payout", null);
        var input = List.of(
                new PsbtInput(unspent.getTxId(), unspent.getVout(), null, null)
        );
        var fee = aliceProxy.getNetworkInfo().getRelayFee();
        var total = unspent.getAmount();
        var send = 1.0;
        var change = total - send - fee;

        var output = Map.of(
                recAddr, Double.toString(send),
                changeAddress, Double.toString(change)
        );
        var rawTransaction = aliceProxy.createRawTransaction(input, output, null, null, null);
        var signed = aliceProxy.signRawTransactionWithWallet(rawTransaction, null, null);
        aliceProxy.sendRawTransaction(signed.getHex(), null);

        //bob takes over
        var mempool = bobProxy.getRawMempool();

        var txInfo = bobProxy.getRawTransactionVerbose(mempool.get(0), null);
        assertEquals(mempool.get(0), txInfo.getTxId());
        var bobsOutput = txInfo.getOutputs().stream().filter(o -> recAddr.equals(o.getScriptPubKey().getAddress())).findFirst().orElse(null);
        assertNotNull(bobsOutput);

        var newBobAddress = bobProxy.getRawChangeAddress(null);

        var bobInput = List.of(
                new PsbtInput(txInfo.getTxId(), bobsOutput.getN(), null, null)
        );
        var additionalFee = 0.0001;
        var bobOutput = Map.of(
                newBobAddress, Double.toString(send - additionalFee)
        );
        var bobRawTransaction = bobProxy.createRawTransaction(bobInput, bobOutput, null, null, null);
        var bobSigned = bobProxy.signRawTransactionWithWallet(bobRawTransaction, null, null);
        var bobTxOriginal = bobProxy.sendRawTransaction(bobSigned.getHex(), null);

        var aliceBalance = aliceProxy.getBalance(null, null, null);
        assertEquals(total - send - fee, aliceBalance);

        bobProxy.generateToAddress(1, address, null);
        var bobBalance = bobProxy.getBalance(null, null, null);
        assertEquals(send - additionalFee, bobBalance);
    }
}
