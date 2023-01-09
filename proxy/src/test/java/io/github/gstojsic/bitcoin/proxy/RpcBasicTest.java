package io.github.gstojsic.bitcoin.proxy;

import io.github.gstojsic.bitcoin.proxy.exception.RpcException;
import io.github.gstojsic.bitcoin.proxy.json.model.ChainTxStats;
import io.github.gstojsic.bitcoin.proxy.model.AddressType;
import io.github.gstojsic.bitcoin.proxy.model.Descriptor;
import io.github.gstojsic.bitcoin.proxy.model.MiningTemplate;
import io.github.gstojsic.bitcoin.proxy.model.PsbtInput;
import io.github.gstojsic.bitcoin.proxy.model.Transaction;
import io.github.gstojsic.bitcoin.proxy.model.WalletFlag;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import static io.github.gstojsic.bitcoin.proxy.util.Const.*;
import static io.github.gstojsic.bitcoin.proxy.util.Util.getTestName;
import static org.junit.jupiter.api.Assertions.*;

public class RpcBasicTest extends BitcoinDockerBase {

    @Test
    void uptime() {
        var uptime = proxy.uptime();
        assertTrue(uptime >= 0);
    }

    @Test
    void uptimeAsync() throws ExecutionException, InterruptedException {
        var proxy = new BitcoinProxyAsync(
                bitcoinDaemon.getHost(),
                bitcoinDaemon.getMappedPort(RPC_PORT),
                RPC_USER,
                RPC_PWD,
                null);
        var uptimeF = proxy.uptime();
        var uptime = uptimeF.get();
        assertTrue(uptime >= 0);
    }

    @Test
    void generateToAddress() {
        var wallet = proxy.createWallet(
                "generateToAddress",
                null,
                null,
                null,
                true,
                null,
                null,
                null);
        assertEquals("generateToAddress", wallet.getName());
        assertTrue(wallet.getWarning().isEmpty());

        var walletProxy = createProxy("generateToAddress");
        var address = walletProxy.getNewAddress(null, null);

        var blockHashes = walletProxy.generateToAddress(1, address, null);
        assertEquals(1, blockHashes.size());

        var txs = walletProxy.listTransactions(null, null, null, null);
        assertEquals(1, txs.size());
        var tx = txs.get(0);
        assertEquals("immature", tx.getCategory());
    }

    @Test
    void generateToDescriptor() {
        var wallet = proxy.createWallet(
                "generateToDescriptor",
                null,
                null,
                null,
                true,
                null,
                null,
                null);
        assertEquals("generateToDescriptor", wallet.getName());

        var walletProxy = createProxy("generateToDescriptor");
        var address = walletProxy.getNewAddress(null, null);
        var addressInfo = walletProxy.getAddressInfo(address);

        var blockHashes = walletProxy.generateToDescriptor(1, addressInfo.getDesc(), null);
        assertEquals(1, blockHashes.size());

        var txs = walletProxy.listTransactions(null, null, null, null);
        assertEquals(1, txs.size());
        var tx = txs.get(0);
        assertEquals("immature", tx.getCategory());
    }

    @Test
    void createWallet() {
        var aliceWallet = proxy.createWallet(
                "alice",
                null,
                null,
                null,
                null,
                null,
                null,
                null);
        assertEquals("alice", aliceWallet.getName());
        assertTrue(aliceWallet.getWarning().isEmpty());

        var bobWallet = proxy.createWallet(
                "bob",
                null,
                null,
                null,
                null,
                null,
                null,
                null);
        assertEquals("bob", bobWallet.getName());
        assertTrue(bobWallet.getWarning().isEmpty());

        var aliceProxy = createProxy("alice");
        var ar = aliceProxy.listReceivedByAddress(1, true, null, null, null);
        assertTrue(ar.isEmpty());

        var bobProxy = createProxy("bob");
        var br = bobProxy.listReceivedByAddress(1, true, null, null, null);
        assertTrue(br.isEmpty());
    }

    @Test
    void getBalance() {
        var wallet = proxy.createWallet(
                "getBalance",
                null,
                null,
                null,
                true,
                null,
                null,
                null);
        assertEquals("getBalance", wallet.getName());
        assertTrue(wallet.getWarning().isEmpty());

        var walletProxy = createProxy("getBalance");
        var r = walletProxy.getBalance(null, null, null);
        assertEquals(0.0, r);
    }

    @Test
    void getBalances() {
        proxy.createWallet(
                "getBalances",
                null,
                null,
                null,
                true,
                null,
                null,
                null);

        var walletProxy = createProxy("getBalances");
        var r = walletProxy.getBalances();
        assertEquals(0.0, r.getMine().getTrusted());
        assertEquals(0.0, r.getMine().getUntrustedPending());
        assertEquals(0.0, r.getMine().getImmature());
        assertEquals(0.0, r.getMine().getUsed());
        assertNull(r.getWatchOnly());
    }

    @Test
    void getNewAddress() {
        var aliceWallet = proxy.createWallet(
                "getNewAddress",
                null,
                null,
                null,
                null,
                null,
                null,
                null);
        assertEquals("getNewAddress", aliceWallet.getName());
        assertTrue(aliceWallet.getWarning().isEmpty());

        var walletProxy = createProxy("getNewAddress");
        var address = walletProxy.getNewAddress(null, null);
        assertFalse(address.isEmpty());

        var list = walletProxy.listReceivedByAddress(1, true, null, null, null);
        assertEquals(1, list.size());
        var balance = list.get(0);
        assertEquals(address, balance.getAddress());
        assertEquals(0.0, balance.getAmount());
        assertEquals(0, balance.getConfirmations());
    }

    @Test
    void getTransaction() {
        proxy.createWallet(
                "getTransaction",
                null,
                null,
                null,
                null,
                null,
                null,
                null);

        var walletProxy = createProxy("getTransaction");
        var address = walletProxy.getNewAddress(null, null);
        var blockHash = walletProxy.generateToAddress(1, address, null).get(0);

        var txs = walletProxy.listTransactions(null, null, null, null);
        var tx = txs.get(0);

        var wtx = walletProxy.getTransaction(tx.getTxId(), null, true);
        assertEquals(tx.getTxId(), wtx.getTxId());
        assertEquals(blockHash, wtx.getBlockhash());
        var decoded = wtx.getDecoded();
        assertEquals(tx.getTxId(), decoded.getTxId());
        assertEquals(1, decoded.getInputs().size());
        assertEquals(2, decoded.getOutputs().size());
    }

    @Test
    void listTransactions(TestInfo info) {
        var c = createWalletProxy(getTestName(info));
        var txs = c.listTransactions(null, null, null, null);
        assertEquals(0, txs.size());

        fundWallet(c, 1);
        txs = c.listTransactions(null, null, null, null);
        assertEquals(1, txs.size());
        var tx = txs.get(0);
        assertEquals("immature", tx.getCategory());
        assertEquals(1, tx.getConfirmations());
    }

    @Test
    void listUnspent() {
        proxy.createWallet(
                "listUnspent",
                null,
                null,
                null,
                null,
                false,
                null,
                null);

        var walletProxy = createProxy("listUnspent");
        var unspent = walletProxy.listUnspent(null, null, null, null, null);
        assertEquals(0, unspent.size());

        var address = walletProxy.getNewAddress(null, null);
        var hashes = walletProxy.generateToAddress(101, address, null);

        unspent = walletProxy.listUnspent(null, null, null, null, null);
        assertEquals(1, unspent.size());
        var u = unspent.get(0);
        assertEquals(address, u.getAddress());
        assertEquals(0, u.getVout());
        assertEquals(101, u.getConfirmations());

        var best = walletProxy.getBestBlockhash();
        assertEquals(hashes.get(100), best);

        ChainTxStats txStats = walletProxy.getChainTxStats(10, null);
        assertEquals(102, txStats.getTxCount());
        assertEquals(101, txStats.getWindowFinalBlockHeight());
        assertEquals(best, txStats.getWindowFinalBlockHash());

        var utxo = new Transaction(u.getTxId(), u.getVout());
        var success = walletProxy.lockUnspent(false, List.of(utxo));
        assertTrue(success);

        var lockUnspent = walletProxy.listLockUnspent();
        assertEquals(1, lockUnspent.size());
        var locked = lockUnspent.get(0);
        assertEquals(utxo.txId(), locked.getTxId());
        assertEquals(utxo.vOut(), locked.getVout());

        assertDoesNotThrow(() -> walletProxy.preciousBlock(hashes.get(50)));

        var txOutsetInfo = proxy.getTxOutsetInfo(null, null, null, null);
        assertEquals(hashes.get(100), txOutsetInfo.getBestBlock());
    }

    @Test
    void listReceivedByAddress() {
        var aliceWallet = proxy.createWallet(
                "listReceivedByAddress",
                null,
                null,
                null,
                null,
                null,
                null,
                null);
        assertEquals("listReceivedByAddress", aliceWallet.getName());
        assertTrue(aliceWallet.getWarning().isEmpty());

        var walletProxy = createProxy("listReceivedByAddress");
        var list = walletProxy.listReceivedByAddress(1, true, null, null, null);
        assertTrue(list.isEmpty());
    }

    @Test
    void sendToAddress(TestInfo info) {
        var aliceProxy = createWalletProxy("alice" + getTestName(info));
        var unspent = initialFundWallet(aliceProxy);
        assertEquals(1, unspent.size());
        var utx = unspent.get(0);
        var bobProxy = createWalletProxy("bob" + getTestName(info));

        var balance = aliceProxy.getBalance(null, null, null);
        assertEquals(unspent.get(0).getAmount(), balance);

        var recAddress = bobProxy.getNewAddress("salary", null);

        var tx = aliceProxy.sendToAddress(recAddress,
                "1.0",
                null,
                null,
                null,
                null,
                null,
                null,
                true,
                null);

        var transaction = aliceProxy.getTransaction(tx, null, null);
        assertEquals(tx, transaction.getTxId());
        assertEquals(-1.0, transaction.getAmount());
        assertEquals(-0.0000141, transaction.getFee());
        assertEquals(0, transaction.getConfirmations());

        var aliceBalance = aliceProxy.getBalance(null, null, null);
        assertEquals(utx.getAmount() - 1 - 0.0000141, aliceBalance);
        var bobBalances = bobProxy.getBalances();
        assertEquals(1.0, bobBalances.getMine().getUntrustedPending());

        fundWallet(aliceProxy, 1);

        transaction = aliceProxy.getTransaction(tx, null, null);
        assertEquals(1, transaction.getConfirmations());
        var bobBalance = bobProxy.getBalance(null, null, null);
        assertEquals(1.0, bobBalance);

        var amount = bobProxy.getReceivedByAddress(recAddress, null, null);
        assertEquals(1.0, amount);

        var amountLabel = bobProxy.getReceivedByLabel("salary", null, null);
        assertEquals(1.0, amountLabel);

        var byLabel = bobProxy.listReceivedByLabel(null, null, null, null);
        assertEquals(1, byLabel.size());
        var firstByLabel = byLabel.get(0);
        assertEquals("salary", firstByLabel.getLabel());
        assertEquals(1.0, firstByLabel.getAmount());

        //var s = bobProxy.listAddressGroupings();
        //System.out.println(s);
        //{"result":[[["bcrt1q8t7cferqsq5nqcnh8xq96nucurdxy9n8vxwc9z",1.00000000,"salary"]]],"error":null,"id":"1"}
    }

    @Test
    void sendToAddressVerbose(TestInfo info) {
        var alice = createWalletProxy("alice" + getTestName(info));
        var unspent = initialFundWallet(alice);
        assertEquals(1, unspent.size());

        var bob = createWalletProxy("bob" + getTestName(info));

        var recAddress = bob.getNewAddress("salary", null);
        var sendToAddressInfo = alice.sendToAddressVerbose(
                recAddress,
                "1.0",
                null,
                null,
                null,
                null,
                null,
                null,
                true,
                null);

        var transaction = alice.getTransaction(sendToAddressInfo.getTxId(), null, null);
        assertEquals(sendToAddressInfo.getTxId(), transaction.getTxId());
        assertEquals(-1.0, transaction.getAmount());
        assertEquals(-0.0000141, transaction.getFee());
        assertEquals(0, transaction.getConfirmations());
    }

    @Test
    void send(TestInfo info) {
        var alice = createWalletProxy("alice" + getTestName(info));
        var unspent = initialFundWallet(alice);
        assertEquals(1, unspent.size());

        var bob = createWalletProxy("bob" + getTestName(info));

        var recAddress = bob.getNewAddress("salary", null);

        var sendInfo = alice.send(
                Map.of(recAddress, "1.0"),
                null,
                null,
                null,
                null);

        var transaction = alice.getTransaction(sendInfo.getTxId(), null, null);
        assertEquals(sendInfo.getTxId(), transaction.getTxId());
        assertEquals(-1.0, transaction.getAmount());
        assertEquals(-0.0000141, transaction.getFee());
        assertEquals(0, transaction.getConfirmations());
        bob.generateToAddress(1, bob.getNewAddress(null, null), null);
        var balance = bob.getBalance(null, null, null);
        assertEquals(1, balance);
    }

    @Test
    void sendAll(TestInfo info) {
        var alice = createWalletProxy("alice" + getTestName(info));
        initialFundWallet(alice);

        var aliceChange = alice.getRawChangeAddress(null);

        var bob = createWalletProxy("bob" + getTestName(info));

        var bobContract1Address = bob.getNewAddress("contract1", null);
        var bobContract2Address = bob.getNewAddress("contract2", null);

        var sendInfo = alice.sendAll(
                List.of(aliceChange),
                Map.of(
                        bobContract1Address, "1.0",
                        bobContract2Address, "0.5"
                ),
                null,
                null,
                null,
                null);
        assertTrue(sendInfo.isComplete());

        var transaction = alice.getTransaction(sendInfo.getTxId(), null, null);
        assertEquals(sendInfo.getTxId(), transaction.getTxId());
        assertEquals(-1.5, transaction.getAmount());
        assertEquals(-0.0000172, transaction.getFee());
        assertEquals(0, transaction.getConfirmations());
        bob.generateToAddress(1, bob.getNewAddress(null, null), null);
        var balance = bob.getBalance(null, null, null);
        assertEquals(1.5, balance);
    }

    @Test
    void sendMany(TestInfo info) {
        var alice = createWalletProxy("alice" + getTestName(info));
        var unspent = initialFundWallet(alice);
        assertEquals(1, unspent.size());

        var bob = createWalletProxy("bob" + getTestName(info));

        var recAddress = bob.getNewAddress("salary", null);

        var tx = alice.sendMany(
                Map.of(recAddress, "1.0"),
                null,
                null,
                null,
                null,
                null,
                null);

        var transaction = alice.getTransaction(tx, null, null);
        assertEquals(tx, transaction.getTxId());
        assertEquals(-1.0, transaction.getAmount());
        assertEquals(-0.0000141, transaction.getFee());
        assertEquals(0, transaction.getConfirmations());
        bob.generateToAddress(1, bob.getNewAddress(null, null), null);
        var balance = bob.getBalance(null, null, null);
        assertEquals(1, balance);
    }

    @Test
    void sendManyVerbose(TestInfo info) {
        var alice = createWalletProxy("alice" + getTestName(info));
        var unspent = initialFundWallet(alice);
        assertEquals(1, unspent.size());

        var bob = createWalletProxy("bob" + getTestName(info));

        var recAddress = bob.getNewAddress("salary", null);

        var tx = alice.sendManyVerbose(
                Map.of(recAddress, "1.0"),
                null,
                null,
                null,
                null,
                null,
                null);

        var transaction = alice.getTransaction(tx.getTxId(), null, null);
        assertEquals(tx.getTxId(), transaction.getTxId());
        assertEquals(-1.0, transaction.getAmount());
        assertEquals(-0.0000141, transaction.getFee());
        assertEquals(0, transaction.getConfirmations());
        bob.generateToAddress(1, bob.getNewAddress(null, null), null);
        var balance = bob.getBalance(null, null, null);
        assertEquals(1, balance);
    }

    @Test
    void listSinceBlock(TestInfo info) {
        var c = createWalletProxy(getTestName(info));
        var unspent = initialFundWallet(c).get(0).getTxId();
        var blockhash = c.getBlockhash(0);
        var transactions = c.listSinceBlock(blockhash, null, null, null, null);
        assertTrue(transactions.getTransactions().stream().anyMatch(t -> unspent.equals(t.getTxId())));
    }

    @Test
    void rescanBlockchain(TestInfo info) {
        var c = createWalletProxy(getTestName(info));
        initialFundWallet(c);
        var scanInfo = c.rescanBlockchain(0, 101);
        assertEquals(0, scanInfo.getStartHeight());
        assertEquals(101, scanInfo.getStopHeight());
    }

    @Test
    void signMessage() {
        proxy.createWallet("signMessage",
                null,
                null,
                null,
                true,
                null,
                null,
                null);
        var c = createProxy("signMessage");
        var address = c.getNewAddress(null, AddressType.LEGACY);
        var signed = c.signMessage(address, "γνῶθι σεαυτόν");
        assertFalse(signed.isEmpty());
    }

    @Test
    void verifyMessage() {
        proxy.createWallet("verifyMessage",
                null,
                null,
                null,
                true,
                null,
                null,
                null);
        var c = createProxy("verifyMessage");
        var address = c.getNewAddress(null, AddressType.LEGACY);
        var message = "γνῶθι σεαυτόν";
        var signature = c.signMessage(address, message);

        assertTrue(c.verifyMessage(address, signature, message));
        //assertFalse(c.verifyMessage(address + "d", signature, message));
        assertFalse(c.verifyMessage(address, "FAKE" + signature, message));
    }

    @Test
    void signMessageWithPrivKey(TestInfo info) {
        var c = createWalletProxy(getTestName(info));

        var address = c.getNewAddress(null, AddressType.LEGACY);
        var message = "γνῶθι σεαυτόν";
        var privKey = c.dumpPrivKey(address);
        var signature = c.signMessageWithPrivKey(privKey, message);

        assertTrue(c.verifyMessage(address, signature, message));
        assertFalse(c.verifyMessage(address, "FAKE" + signature, message));
    }

    @Test
    void dumpPrivKey() {
        proxy.createWallet(
                "dumpPrivKey",
                null,
                null,
                null,
                true,
                false,
                null,
                null);
        var c = createProxy("dumpPrivKey");
        var address = c.getNewAddress(null, AddressType.LEGACY);
        var dump = c.dumpPrivKey(address);
        assertFalse(dump.isEmpty());
    }

    @Test
    void importPrivKey(TestInfo info) {
        var c = createWalletProxy(getTestName(info));
        var address = c.getNewAddress(null, AddressType.LEGACY);
        var dump = c.dumpPrivKey(address);
        assertDoesNotThrow(() -> c.importPrivKey(dump, null, null));
    }

    @Test
    void abortRescan(TestInfo info) {
        var c = createWalletProxy(getTestName(info));
        var address = c.getNewAddress(null, AddressType.LEGACY);
        var dump = c.dumpPrivKey(address);
        c.importPrivKey(dump, null, null);
        var aborted = c.abortRescan();
        assertFalse(aborted);
    }

    @Test
    void keypoolRefill(TestInfo info) {
        var c = createWalletProxy(getTestName(info));
        assertDoesNotThrow(() -> c.keypoolRefill(1000));
    }

    @Test
    void newKeypool(TestInfo info) {
        var c = createWalletProxy(getTestName(info));
        assertDoesNotThrow(c::newKeypool);
    }

    @Test
    void getWalletInfo() {
        proxy.createWallet(
                "getWalletInfo",
                null,
                null,
                null,
                true,
                false,
                null,
                null);
        var c = createProxy("getWalletInfo");
        var info = c.getWalletInfo();
        assertEquals("getWalletInfo", info.getWalletName());
        assertEquals("bdb", info.getFormat());
        assertTrue(info.isAvoidReuse());
    }

    @Test
    void getBlockchainInfo() {
        var info = proxy.getBlockchainInfo();
        assertEquals("regtest", info.getChain());
    }

    @Test
    void getMiningInfo() {
        var info = proxy.getMiningInfo();
        assertEquals("regtest", info.getChain());
    }

    @Test
    void getNetworkInfo() {
        var info = proxy.getNetworkInfo();

        assertEquals(240001, info.getVersion());
        assertEquals("/Satoshi:24.0.1/", info.getSubversion());
    }

    @Test
    void getNetTotals() {
        var netTotals = proxy.getNetTotals();

        assertTrue(netTotals.getTimeMillis() <= System.currentTimeMillis());
        assertTrue(netTotals.getTotalBytesRecv() >= 0);
        assertTrue(netTotals.getTotalBytesSent() >= 0);
    }

    @Test
    void getConnectionCount() {
        var count = proxy.getConnectionCount();
        assertTrue(count >= 0);
    }

    @Test
    void saveMempool() {
        var file = proxy.saveMempool();
        assertTrue(file.getFilename().contains("/home/bitcoin/.bitcoin"));
    }

    @Test
    void verifyChain() {
        assertTrue(proxy.verifyChain(4, 0));
    }

    @Test
    @Disabled
        // maybe a test on its own.
    void stop() {
        var msg = proxy.stop();
        assertEquals("Bitcoin Core stopping", msg);
    }

    @Test
    void getDeploymentInfo() {
        var deploymentInfo = proxy.getDeploymentInfo(null);
        assertFalse(deploymentInfo.getHash().isEmpty());
        assertTrue(deploymentInfo.getDeployments().containsKey("segwit"));
        assertTrue(deploymentInfo.getDeployments().containsKey("csv"));
        assertTrue(deploymentInfo.getDeployments().containsKey("bip34"));
        assertTrue(deploymentInfo.getDeployments().containsKey("taproot"));
    }

    @Test
    void getDescriptorInfo() {
        var descriptorInfo = proxy.getDescriptorInfo("wpkh([d34db33f/84h/0h/0h]0279be667ef9dcbbac55a06295Ce870b07029Bfcdb2dce28d959f2815b16f81798)");
        assertEquals("qwlqgth7", descriptorInfo.getChecksum());
        assertFalse(descriptorInfo.isRange());
        assertTrue(descriptorInfo.isSolvable());
        assertFalse(descriptorInfo.isPrivateKeys());
    }

    @Test
    void importDescriptors(TestInfo info) {
        var c = createWalletProxy(getTestName(info));
        fundWallet(c, 101);
        var unspent = c.listUnspent(null, null, null, null, null).get(0);
        var anotherWallet = "another" + getTestName(info);
        c.createWallet(
                anotherWallet,
                true,
                null,
                null,
                true,
                true,
                null,
                null);
        var another = createProxy(anotherWallet);
        var importDesc = another.importDescriptors(
                List.of(
                        new Descriptor(
                                unspent.getDesc(),
                                null,
                                null,
                                null,
                                "now",
                                null,
                                null
                        )
                )
        );
        assertEquals(1, importDesc.size());
        assertTrue(importDesc.get(0).isSuccess());
    }

    @Test
    void importPubKey(TestInfo info) {
        var c = createWalletProxy(getTestName(info));
        var add = c.getNewAddress(null, null);
        var addinfo = c.getAddressInfo(add);

        var another = createWalletProxy("another" + getTestName(info));
        assertDoesNotThrow(() -> another.importPubKey(addinfo.getPubKey(), null, false));
    }

    @Test
    void getIndexInfo() {
        var indexInfo = proxy.getIndexInfo(null);
        assertNotNull(indexInfo);
        var txIndex = indexInfo.get("txindex");
        assertNotNull(txIndex);
        assertTrue(txIndex.isSynced());
    }

    @Test
    void listLabels() {
        proxy.createWallet(
                "listLabels",
                null,
                null,
                null,
                true,
                null,
                null,
                null);
        var c = createProxy("listLabels");
        c.getNewAddress("banana", AddressType.LEGACY);
        var labels = c.listLabels(null);
        assertEquals(1, labels.size());
        assertEquals("banana", labels.get(0));
    }

    @Test
    void listWalletDir() {
        proxy.createWallet(
                "listWalletDir1",
                null,
                null,
                null,
                true,
                null,
                null,
                null);
        proxy.createWallet(
                "listWalletDir2",
                null,
                null,
                null,
                true,
                null,
                null,
                null);
        var wallets = proxy.listWalletDir();
        assertTrue(wallets.getWallets().size() >= 2);
        assertTrue(wallets.getWallets().stream().anyMatch(w -> "listWalletDir1".equals(w.getName())));
        assertTrue(wallets.getWallets().stream().anyMatch(w -> "listWalletDir2".equals(w.getName())));
    }

    @Test
    void listWallets() {
        proxy.createWallet(
                "listWallets1",
                null,
                null,
                null,
                true,
                null,
                null,
                null);
        proxy.createWallet(
                "listWallets2",
                null,
                null,
                null,
                true,
                null,
                null,
                null);
        var wallets = proxy.listWallets();
        assertTrue(wallets.size() >= 2);
        assertTrue(wallets.stream().anyMatch("listWallets1"::equals));
        assertTrue(wallets.stream().anyMatch("listWallets2"::equals));
    }

    @Test
    void upgradeWallet() {
        proxy.createWallet(
                "upgradeWallet",
                null,
                null,
                null,
                true,
                null,
                null,
                null);
        var c = createProxy("upgradeWallet");
        var w = c.upgradeWallet(null);
        assertEquals("upgradeWallet", w.getWalletName());
    }

    @Test
    void unloadWallet() {
        proxy.createWallet(
                "unloadWallet",
                null,
                null,
                null,
                true,
                null,
                null,
                null);
        var w = proxy.unloadWallet("unloadWallet", null);
        assertTrue(w.getWarning().isEmpty());
    }

    @Test
    void getAddressInfo() {
        proxy.createWallet(
                "getAddressInfo",
                null,
                null,
                null,
                true,
                null,
                null,
                null);
        var c = createProxy("getAddressInfo");
        var a = c.getNewAddress(null, AddressType.LEGACY);
        var addressInfo = c.getAddressInfo(a);
        assertEquals(a, addressInfo.getAddress());
        assertTrue(addressInfo.isSolvable());
        assertFalse(addressInfo.getLabels().isEmpty());
    }

    @Test
    void setLabel() {
        proxy.createWallet(
                "setLabel",
                null,
                null,
                null,
                true,
                null,
                null,
                null);
        var c = createProxy("setLabel");
        var a = c.getNewAddress(null, AddressType.LEGACY);
        c.setLabel(a, "memento mori");
        var addressInfo = c.getAddressInfo(a);
        assertEquals(a, addressInfo.getAddress());
        assertTrue(addressInfo.isSolvable());
        assertFalse(addressInfo.getLabels().isEmpty());
        assertEquals("memento mori", addressInfo.getLabels().get(0));
    }

    @Test
    void getAddressesByLabel() {
        proxy.createWallet(
                "getAddressesByLabel",
                null,
                null,
                null,
                true,
                null,
                null,
                null);
        var c = createProxy("getAddressesByLabel");
        var a = c.getNewAddress(null, AddressType.LEGACY);
        c.setLabel(a, "memento mori");
        var addressesByLabel = c.getAddressesByLabel("memento mori");
        assertFalse(addressesByLabel.isEmpty());
        assertEquals("receive", addressesByLabel.get(a).getPurpose());
    }

    @Test
    void encryptWallet() {
        proxy.createWallet(
                "encryptWallet",
                null,
                null,
                null,
                true,
                null,
                null,
                null);
        var c = createProxy("encryptWallet");
        var a = c.encryptWallet("fiatSucks");
        assertEquals("wallet encrypted; The keypool has been flushed and a new HD seed was generated (if you are using HD). You need to make a new backup.", a);
    }

    @Test
    void getRawChangeAddress() {
        proxy.createWallet(
                "getRawChangeAddress",
                null,
                null,
                null,
                true,
                null,
                null,
                null);
        var c = createProxy("getRawChangeAddress");
        var a = c.getRawChangeAddress(AddressType.LEGACY);
        assertFalse(a.isEmpty());
    }

    @Test
    void getReceivedByAddress() {
        proxy.createWallet(
                "getReceivedByAddress",
                null,
                null,
                null,
                true,
                null,
                null,
                null);
        var c = createProxy("getReceivedByAddress");
        var a = c.getNewAddress(null, AddressType.LEGACY);
        var amount = c.getReceivedByAddress(a, null, null);
        assertEquals(0.0, amount);
    }

    @Test
    void getReceivedByLabel() {
        proxy.createWallet(
                "getReceivedByLabel",
                null,
                null,
                null,
                true,
                null,
                null,
                null);
        var c = createProxy("getReceivedByLabel");
        c.getNewAddress("salary", AddressType.LEGACY);
        var amount = c.getReceivedByLabel("salary", null, null);
        assertEquals(0.0, amount);
    }

    @Test
    void getBlockhash() {
        proxy.createWallet(
                "getBlockhash",
                null,
                null,
                null,
                null,
                null,
                null,
                null);

        var walletProxy = createProxy("getBlockhash");
        var address = walletProxy.getNewAddress(null, null);
        walletProxy.generateToAddress(1, address, null);
        var hash = walletProxy.getBlockhash(1);
        assertFalse(hash.isEmpty());
    }

    @Test
    void getBlockHeader() {
        proxy.createWallet(
                "getBlockHeader",
                null,
                null,
                null,
                null,
                null,
                null,
                null);

        var walletProxy = createProxy("getBlockHeader");
        var address = walletProxy.getNewAddress(null, null);
        walletProxy.generateToAddress(1, address, null);
        var hash = walletProxy.getBlockhash(1);
        var header = walletProxy.getBlockHeader(hash);
        assertFalse(header.isEmpty());
    }

    @Test
    void getBlockHeaderVerbose() {
        proxy.createWallet(
                "getBlockHeaderVerbose",
                null,
                null,
                null,
                null,
                null,
                null,
                null);

        var walletProxy = createProxy("getBlockHeaderVerbose");
        var address = walletProxy.getNewAddress(null, null);
        walletProxy.generateToAddress(1, address, null);
        var hash = walletProxy.getBlockhash(1);
        var header = walletProxy.getBlockHeaderVerbose(hash);
        assertEquals(hash, header.getHash());
    }

    @Test
    void getBlock() {
        proxy.createWallet(
                "getBlock",
                null,
                null,
                null,
                null,
                null,
                null,
                null);

        var walletProxy = createProxy("getBlock");
        var address = walletProxy.getNewAddress(null, null);
        walletProxy.generateToAddress(1, address, null);
        var hash = walletProxy.getBlockhash(1);
        var block = walletProxy.getBlock(hash);
        assertFalse(block.isEmpty());
    }

    @Test
    void getBlockVerbose1() {
        proxy.createWallet(
                "getBlockVerbose1",
                null,
                null,
                null,
                null,
                null,
                null,
                null);

        var walletProxy = createProxy("getBlockVerbose1");
        var address = walletProxy.getNewAddress(null, null);
        walletProxy.generateToAddress(1, address, null);
        var hash = walletProxy.getBlockhash(1);
        var block = walletProxy.getBlockVerbose1(hash);
        assertEquals(hash, block.getHash());
    }

    @Test
    void getBlockVerbose2() {
        proxy.createWallet(
                "getBlockVerbose2",
                null,
                null,
                null,
                null,
                null,
                null,
                null);

        var walletProxy = createProxy("getBlockVerbose2");
        var address = walletProxy.getNewAddress(null, null);
        walletProxy.generateToAddress(1, address, null);
        var hash = walletProxy.getBlockhash(1);
        var block = walletProxy.getBlockVerbose2(hash);
        assertEquals(hash, block.getHash());
    }

    @Test
    void getBlockVerbose3() {
        proxy.createWallet(
                "getBlockVerbose3",
                null,
                null,
                null,
                null,
                null,
                null,
                null);

        var walletProxy = createProxy("getBlockVerbose3");
        var address = walletProxy.getNewAddress(null, null);
        walletProxy.generateToAddress(1, address, null);
        var hash = walletProxy.getBlockhash(1);
        var block = walletProxy.getBlockVerbose3(hash);
        assertEquals(hash, block.getHash());
    }

    @Test
    void deriveAddresses() {
        proxy.createWallet(
                "deriveAddresses",
                null,
                null,
                null,
                true,
                null,
                null,
                null);
        var c = createProxy("deriveAddresses");
        var a = c.getNewAddress(null, AddressType.LEGACY);
        var addressInfo = c.getAddressInfo(a);
        var addresses = c.deriveAddresses(addressInfo.getDesc(), null);
        assertFalse(addresses.isEmpty());
    }

    @Test
    void logging() {
        var logging = proxy.logging(null, null);
        assertFalse(logging.isEmpty());
        assertFalse(logging.get("net"));
    }

    @Test
    void getMemoryInfoMalloc() {
        var memoryInfoMalloc = proxy.getMemoryInfoMalloc();
        assertFalse(memoryInfoMalloc.isEmpty());
    }

    @Test
    void getMemoryInfo() {
        var memoryInfo = proxy.getMemoryInfo();
        assertNotNull(memoryInfo);
        assertNotNull(memoryInfo.getLocked());
        assertTrue(memoryInfo.getLocked().getTotal() > 0);
        assertTrue(memoryInfo.getLocked().getFree() > 0);
        assertTrue(memoryInfo.getLocked().getUsed() > 0);
    }

    @Test
    void getRpcInfo() {
        var rpcInfo = proxy.getRpcInfo();
        assertNotNull(rpcInfo);
        assertFalse(rpcInfo.getActiveCommands().isEmpty());
        assertTrue(rpcInfo.getLogPath().contains("debug.log"));
    }

    @Test
    void walletPassphrase() {
        var pass = "fiatSucks";
        proxy.createWallet(
                "walletPassphrase",
                null,
                null,
                null,
                null,
                null,
                null,
                null);

        var c = createProxy("walletPassphrase");
        c.encryptWallet(pass);
        assertDoesNotThrow(() -> c.walletPassphrase(pass, 100));
    }

    @Test
    void walletPassphraseChange() {
        var pass = "fiatSucks";
        proxy.createWallet(
                "walletPassphraseChange",
                null,
                null,
                pass,
                null,
                null,
                null,
                null);

        var c = createProxy("walletPassphraseChange");
        assertDoesNotThrow(() -> c.walletPassphrase(pass, 10));
        var newPass = "inflationSucks";
        c.walletPassphraseChange(pass, newPass);
        assertDoesNotThrow(() -> c.walletPassphrase(newPass, 10));
    }

    @Test
    void setTxFee() {
        proxy.createWallet(
                "setTxFee",
                null,
                null,
                null,
                null,
                null,
                null,
                null);

        var c = createProxy("setTxFee");
        assertTrue(c.setTxFee("0.010"));
    }

    @Test
    void listDescriptors() {
        proxy.createWallet(
                "listDescriptors",
                null,
                null,
                null,
                null,
                true,
                null,
                null);

        var c = createProxy("listDescriptors");
        var descriptors = c.listDescriptors(null);
        assertEquals("listDescriptors", descriptors.getWalletName());
        assertFalse(descriptors.getDescriptors().isEmpty());
    }

    @Test
    void lockUnspent() {
        proxy.createWallet(
                "lockUnspent",
                null,
                null,
                null,
                null,
                true,
                null,
                null);

        var c = createProxy("lockUnspent");
        assertTrue(c.lockUnspent(false, null));
    }

    @Test
    void listLockUnspent() {
        proxy.createWallet(
                "listLockUnspent",
                null,
                null,
                null,
                null,
                true,
                null,
                null);

        var c = createProxy("listLockUnspent");
        var lockUnspent = c.listLockUnspent();
        assertTrue(lockUnspent.isEmpty());
    }

    @Test
    void setWalletFlag() {
        proxy.createWallet(
                "setWalletFlag",
                null,
                null,
                null,
                null,
                true,
                null,
                null);

        var c = createProxy("setWalletFlag");
        var walletInfo = c.getWalletInfo();
        assertFalse(walletInfo.isAvoidReuse());
        var walletFlag = c.setWalletFlag(WalletFlag.avoid_reuse, true);
        assertEquals("avoid_reuse", walletFlag.getFlagName());
        assertTrue(walletFlag.isFlagState());
        walletInfo = c.getWalletInfo();
        assertTrue(walletInfo.isAvoidReuse());
    }

    @Test
    void walletLock() {
        proxy.createWallet(
                "walletLock",
                null,
                null,
                "passphrase",
                null,
                true,
                null,
                null);

        var c = createProxy("walletLock");
        assertDoesNotThrow(c::walletLock);
    }

    @Test
    void setNetworkActive() {
        assertFalse(proxy.setNetworkActive(false));
        assertTrue(proxy.setNetworkActive(true));
    }

    @Test
    void validateAddress() {
        proxy.createWallet(
                "validateAddress",
                null,
                null,
                null,
                true,
                null,
                null,
                null);
        var c = createProxy("validateAddress");
        var a = c.getNewAddress(null, AddressType.LEGACY);
        var validation = c.validateAddress(a);
        assertTrue(validation.isValid());
        assertEquals(a, validation.getAddress());
    }

    @Test
    void listReceivedByLabel() {
        proxy.createWallet(
                "listReceivedByLabel",
                null,
                null,
                null,
                true,
                null,
                null,
                null);
        var c = createProxy("listReceivedByLabel");
        var validation = c.listReceivedByLabel(null, null, null, null);
        assertTrue(validation.isEmpty());
    }

    @Test
    void getNetworkHashPs() {
        var hps = proxy.getNetworkHashPs(null, null);
        assertTrue(hps >= 0.0);
    }

    @Test
    void getBlockTemplate() {
        var blockTemplate = proxy.getBlockTemplate(null);
        assertNotNull(blockTemplate);
        assertEquals(536870912, blockTemplate.getVersion());
    }

    @Test
    @Disabled
    void getBlockTemplateProposal() {
        var temReq = new MiningTemplate(null, Set.of("test"));
        var blockTemplate = proxy.getBlockTemplateProposal(temReq);
        assertNotNull(blockTemplate);
    }

    @Test
    void getZmqNotifications() {
        var zmq = proxy.getZmqNotifications();
        assertTrue(zmq.isEmpty());
    }

    /**
     * need an external device for this?
     */
    @Test
    @Disabled
    void enumerateSigners() {
        var zmq = proxy.enumerateSigners();
        assertTrue(zmq.getSigners().isEmpty());
    }

    /**
     * need an external device for this?
     */
    @Test
    @Disabled
    void walletDisplayAddress() {
        proxy.createWallet(
                "walletDisplayAddress",
                null,
                null,
                null,
                null,
                null,
                null,
                null);

        var c = createProxy("walletDisplayAddress");
        var a = c.getNewAddress("satoshi stash", AddressType.LEGACY);
        var walletInfo = c.walletDisplayAddress(a);
        /*
        walletdisplayaddress "address"

        Display address on an external signer for verification.

        Arguments:
        1. address    (string, required) bitcoin address to display

        Result:
        {                       (json object)
          "address" : "str"     (string) The address as confirmed by the signer
        }
         */
    }

    @Test
    void setHdSeed(TestInfo info) {
        var c = createWalletProxy(getTestName(info));
        assertDoesNotThrow(() -> c.setHdSeed(null, null));
    }

    @Test
    void importPrunedFundsAndRemovePrunedFunds(TestInfo info) {
        var alice = createWalletProxy("alice" + getTestName(info));
        var unspent = initialFundWallet(alice).get(0);

        var changeAddress = alice.getRawChangeAddress(AddressType.LEGACY);

        var recAddr = alice.getNewAddress("moveAround", null);
        var input = List.of(
                new PsbtInput(unspent.getTxId(), unspent.getVout(), null, null)
        );
        var fee = alice.getNetworkInfo().getRelayFee();
        var total = unspent.getAmount();
        var send = 1.0;
        var change = total - send - fee;

        var output = Map.of(
                recAddr, Double.toString(send),
                changeAddress, Double.toString(change)
        );
        var rawTransaction = alice.createRawTransaction(input, output, null, null, null);

        var decoded = alice.decodeRawTransaction(rawTransaction, null);
        var signed = alice.signRawTransactionWithWallet(rawTransaction, null, null);

        alice.sendRawTransaction(signed.getHex(), null);

        fundWallet(alice, 1);
        var txOutProof = alice.getTxOutProof(List.of(decoded.getTxId()), null);

        var bob = createWalletProxy("bob" + getTestName(info));
        bob.importAddress(recAddr, "pruned", null, null);
        //import pruned
        assertDoesNotThrow(() -> bob.importPrunedFunds(rawTransaction, txOutProof));

        //and remove them
        assertDoesNotThrow(() -> bob.removePrunedFunds(decoded.getTxId()));
    }

    @Test
    void prioritiseTransaction(TestInfo info) {
        var c = createWalletProxy(getTestName(info));
        var unspent = initialFundWallet(c).get(0);

        var changeAddress = c.getRawChangeAddress(null);

        var recAddr = c.getNewAddress("moveAround", null);
        var input = List.of(
                new PsbtInput(unspent.getTxId(), unspent.getVout(), null, null)
        );
        var fee = c.getNetworkInfo().getRelayFee();
        var total = unspent.getAmount();
        var send = 1.0;
        var change = total - send - fee;

        var output = Map.of(
                recAddr, Double.toString(send),
                changeAddress, Double.toString(change)
        );
        var rawTransaction = c.createRawTransaction(input, output, null, null, null);

        var decoded = c.decodeRawTransaction(rawTransaction, null);
        var signed = c.signRawTransactionWithWallet(rawTransaction, null, null);

        c.sendRawTransaction(signed.getHex(), null);

        assertTrue(c.prioritiseTransaction(decoded.getTxId(), 100));
    }

    @Test
    void abandonTransaction(TestInfo info) {
        var c = createWalletProxy(getTestName(info));
        var unspent = initialFundWallet(c).get(0);

        var changeAddress = c.getRawChangeAddress(null);

        var recAddr = c.getNewAddress("moveAround", null);
        var input = List.of(
                new PsbtInput(unspent.getTxId(), unspent.getVout(), null, null)
        );
        var fee = c.getNetworkInfo().getRelayFee();
        var total = unspent.getAmount();
        var send = 1.0;
        var change = total - send - fee;

        var output = Map.of(
                recAddr, Double.toString(send),
                changeAddress, Double.toString(change)
        );
        var rawTransaction = c.createRawTransaction(input, output, null, null, null);

        var signed = c.signRawTransactionWithWallet(rawTransaction, null, null);
        var sentTx = c.sendRawTransaction(signed.getHex(), null);

        // don't know how this is supposed to work, but it seems like a deprecated feature
        // https://github.com/bitcoin/bitcoin/issues/11060
        assertThrows(RpcException.class, () -> c.abandonTransaction(sentTx));
    }

    @Test
    void help() {
        var h = proxy.help(null);
        assertTrue(h.contains("== Blockchain =="));
        assertTrue(h.contains("== Control =="));
        assertTrue(h.contains("== Mining =="));
        assertTrue(h.contains("== Network =="));
        assertTrue(h.contains("== Rawtransactions =="));
        assertTrue(h.contains("== Signer =="));
        assertTrue(h.contains("== Util =="));
        assertTrue(h.contains("== Wallet =="));
    }

    @Test
    void getDifficulty() {
        double r = proxy.getDifficulty();
        assertTrue(r > 0);
    }
}
