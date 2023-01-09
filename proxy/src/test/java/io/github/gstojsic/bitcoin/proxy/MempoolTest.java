package io.github.gstojsic.bitcoin.proxy;

import io.github.gstojsic.bitcoin.proxy.json.model.MempoolData;
import io.github.gstojsic.bitcoin.proxy.json.model.MempoolInfo;
import io.github.gstojsic.bitcoin.proxy.json.model.MempoolWithSeq;
import io.github.gstojsic.bitcoin.proxy.model.PsbtDescriptor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import static io.github.gstojsic.bitcoin.proxy.util.Util.getTestName;
import static org.junit.jupiter.api.Assertions.*;

public class MempoolTest extends BitcoinDockerBase {

    @Test
    void getMempoolInfo() {
        MempoolInfo r = proxy.getMempoolInfo();
        assertNotNull(r);
        assertTrue(r.isLoaded());
    }

    @Test
    void getRawMempoolVerbose() {
        Map<String, MempoolData> r = proxy.getRawMempoolVerbose();
        assertNotNull(r);
        assertTrue(r.isEmpty());
    }

    @Test
    void getRawMempoolWithSequence() {
        MempoolWithSeq r = proxy.getRawMempoolWithSequence();
        assertNotNull(r);
        assertEquals(1, r.getMempoolSequence());
        assertEquals(0, r.getTxIds().size());
    }

    @Test
    void mempool(TestInfo info) {
        var alice = createWalletProxy("alice" + getTestName(info));
        var bob = createWalletProxy("bob" + getTestName(info));
        fundWallet(alice, 101);
        //fill the mempool
        for (int i = 1; i <= 20; i++) {
            var recAddr = bob.getNewAddress(null, null);
            var send = 0.01;

            var output = Map.of(
                    recAddr, Double.toString(send)
            );
            var rawTransaction = alice.createRawTransaction(null, output, null, null, null);
            var fundedTx = alice.fundRawTransaction(rawTransaction, null, null);
            var signed = alice.signRawTransactionWithWallet(fundedTx.getHex(), null, null);
            alice.sendRawTransaction(signed.getHex(), null);
        }

        List<String> mempool = alice.getRawMempool();
        assertNotNull(mempool);
        assertEquals(20, mempool.size());
        var setMempool = new HashSet<>(mempool);

        List<String> ancestors = alice.getMempoolAncestors(mempool.get(19));
        ancestors.forEach(mp -> assertTrue(setMempool.contains(mp)));

        var ancestorsVerbose = alice.getMempoolAncestorsVerbose(mempool.get(19));
        ancestorsVerbose.keySet().forEach(mp -> assertTrue(setMempool.contains(mp)));

        List<String> descendants = alice.getMempoolDescendants(mempool.get(0));
        descendants.forEach(mp -> assertTrue(setMempool.contains(mp)));

        var descendantsVerbose = alice.getMempoolDescendantsVerbose(mempool.get(0));
        descendantsVerbose.keySet().forEach(mp -> assertTrue(setMempool.contains(mp)));

        var entry = alice.getMempoolEntry(mempool.get(19));
        assertNotNull(entry);
    }

    @Test
    void estimateFee(TestInfo info) {
        var alice = createWalletProxy("alice" + getTestName(info));
        var bob = createWalletProxy("bob" + getTestName(info));
        fundWallet(alice, 101);
        //generate some transactions
        for (int j = 1; j <= 5; j++) {
            for (int i = 1; i <= 10; i++) {
                var recAddr = bob.getNewAddress(null, null);
                var send = 0.001;

                var output = Map.of(
                        recAddr, Double.toString(send)
                );
                var rawTransaction = alice.createRawTransaction(null, output, null, null, null);
                var fundedTx = alice.fundRawTransaction(rawTransaction, null, null);
                var signed = alice.signRawTransactionWithWallet(fundedTx.getHex(), null, null);
                alice.sendRawTransaction(signed.getHex(), null);
            }
            bob.generateToAddress(1, bob.getNewAddress(null, null), null);
        }
        var feeEstimate = alice.estimateSmartFee(4, null);
        assertNotNull(feeEstimate);
        assertNull(feeEstimate.getErrors());
        assertTrue(feeEstimate.getFeeRate() > 0);
    }

    /**
     *  check out tests in .../bitcoin/test/functional/rpc_scantxoutset.py
     */
    @Test
    void scanTxOutset(TestInfo info) {
        var alice = createWalletProxy("alice" + getTestName(info));
        var bob = createWalletProxy("bob" + getTestName(info));
        fundWallet(alice, 101);
        //generate some transactions
        var addresses = new ArrayList<String>(10);
        for (int i = 1; i <= 10; i++) {
            var recAddr = bob.getNewAddress(null, null);
            addresses.add(recAddr);
            var send = 0.001;

            var output = Map.of(
                    recAddr, Double.toString(send)
            );
            var rawTransaction = alice.createRawTransaction(null, output, null, null, null);
            var fundedTx = alice.fundRawTransaction(rawTransaction, null, null);
            var signed = alice.signRawTransactionWithWallet(fundedTx.getHex(), null, null);
            alice.sendRawTransaction(signed.getHex(), null);
        }
        bob.generateToAddress(1, bob.getNewAddress(null, null), null);

        var seven = bob.getAddressInfo(addresses.get(6));
        var one = bob.getAddressInfo(addresses.get(0));


        var scanObjects = List.of(
                new PsbtDescriptor(one.getDesc(), null),
                new PsbtDescriptor(seven.getDesc(), null)
        );

        var res = bob.scanTxOutset(scanObjects);
        assertNotNull(res);
        assertTrue(res.isSuccess());
        assertEquals(2, res.getUnspents().size());
    }
}
