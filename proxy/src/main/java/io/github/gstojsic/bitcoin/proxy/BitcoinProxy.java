package io.github.gstojsic.bitcoin.proxy;

import io.github.gstojsic.bitcoin.proxy.exception.RpcException;
import io.github.gstojsic.bitcoin.proxy.json.model.AddPeerAddress;
import io.github.gstojsic.bitcoin.proxy.json.model.AddedNodeInfo;
import io.github.gstojsic.bitcoin.proxy.json.model.AddressByLabel;
import io.github.gstojsic.bitcoin.proxy.json.model.AddressInfo;
import io.github.gstojsic.bitcoin.proxy.json.model.AddressValidation;
import io.github.gstojsic.bitcoin.proxy.json.model.BalanceByAddress;
import io.github.gstojsic.bitcoin.proxy.json.model.BalancesData;
import io.github.gstojsic.bitcoin.proxy.json.model.BannedInfo;
import io.github.gstojsic.bitcoin.proxy.json.model.Block1;
import io.github.gstojsic.bitcoin.proxy.json.model.Block2;
import io.github.gstojsic.bitcoin.proxy.json.model.BlockFilter;
import io.github.gstojsic.bitcoin.proxy.json.model.BlockHeader;
import io.github.gstojsic.bitcoin.proxy.json.model.BlockStats;
import io.github.gstojsic.bitcoin.proxy.json.model.BlockTemplate;
import io.github.gstojsic.bitcoin.proxy.json.model.BlockchainInfo;
import io.github.gstojsic.bitcoin.proxy.json.model.BumpFee;
import io.github.gstojsic.bitcoin.proxy.json.model.ChainTipData;
import io.github.gstojsic.bitcoin.proxy.json.model.ChainTxStats;
import io.github.gstojsic.bitcoin.proxy.json.model.DecodedPsbt;
import io.github.gstojsic.bitcoin.proxy.json.model.DecodedScript;
import io.github.gstojsic.bitcoin.proxy.json.model.DeploymentInfo;
import io.github.gstojsic.bitcoin.proxy.json.model.DescriptorInfo;
import io.github.gstojsic.bitcoin.proxy.json.model.Descriptors;
import io.github.gstojsic.bitcoin.proxy.json.model.DumpFile;
import io.github.gstojsic.bitcoin.proxy.json.model.Empty;
import io.github.gstojsic.bitcoin.proxy.json.model.FeeEstimate;
import io.github.gstojsic.bitcoin.proxy.json.model.FinalizedPsbt;
import io.github.gstojsic.bitcoin.proxy.json.model.GeneratedBlock;
import io.github.gstojsic.bitcoin.proxy.json.model.ImportMultiResult;
import io.github.gstojsic.bitcoin.proxy.json.model.IndexInfo;
import io.github.gstojsic.bitcoin.proxy.json.model.ListAddressGrouping;
import io.github.gstojsic.bitcoin.proxy.json.model.MemoryInfo;
import io.github.gstojsic.bitcoin.proxy.json.model.MempoolAccept;
import io.github.gstojsic.bitcoin.proxy.json.model.MempoolData;
import io.github.gstojsic.bitcoin.proxy.json.model.MempoolInfo;
import io.github.gstojsic.bitcoin.proxy.json.model.MempoolWithSeq;
import io.github.gstojsic.bitcoin.proxy.json.model.MiningInfo;
import io.github.gstojsic.bitcoin.proxy.json.model.MultisigAddress;
import io.github.gstojsic.bitcoin.proxy.json.model.NetTotals;
import io.github.gstojsic.bitcoin.proxy.json.model.NetworkInfo;
import io.github.gstojsic.bitcoin.proxy.json.model.NodeAddress;
import io.github.gstojsic.bitcoin.proxy.json.model.PeerInfo;
import io.github.gstojsic.bitcoin.proxy.json.model.PsbtAnalysis;
import io.github.gstojsic.bitcoin.proxy.json.model.PsbtBumpFee;
import io.github.gstojsic.bitcoin.proxy.json.model.RawTransaction;
import io.github.gstojsic.bitcoin.proxy.json.model.RpcInfo;
import io.github.gstojsic.bitcoin.proxy.json.model.ScanInfo;
import io.github.gstojsic.bitcoin.proxy.json.model.ScanTxOutResult;
import io.github.gstojsic.bitcoin.proxy.json.model.SendInfo;
import io.github.gstojsic.bitcoin.proxy.json.model.SendToAddressInfo;
import io.github.gstojsic.bitcoin.proxy.json.model.SignTransactionResult;
import io.github.gstojsic.bitcoin.proxy.json.model.Signers;
import io.github.gstojsic.bitcoin.proxy.json.model.TransactionByLabel;
import io.github.gstojsic.bitcoin.proxy.json.model.TransactionFunding;
import io.github.gstojsic.bitcoin.proxy.json.model.TransactionOutput;
import io.github.gstojsic.bitcoin.proxy.json.model.TransactionOutputSetInfo;
import io.github.gstojsic.bitcoin.proxy.json.model.TransactionSinceBlock;
import io.github.gstojsic.bitcoin.proxy.json.model.UnloadWallet;
import io.github.gstojsic.bitcoin.proxy.json.model.UnspentInfo;
import io.github.gstojsic.bitcoin.proxy.json.model.UpgradeWallet;
import io.github.gstojsic.bitcoin.proxy.json.model.UtxoKey;
import io.github.gstojsic.bitcoin.proxy.json.model.Wallet;
import io.github.gstojsic.bitcoin.proxy.json.model.WalletDisplayAddress;
import io.github.gstojsic.bitcoin.proxy.json.model.WalletFlagInfo;
import io.github.gstojsic.bitcoin.proxy.json.model.WalletFundedPsbt;
import io.github.gstojsic.bitcoin.proxy.json.model.WalletInfo;
import io.github.gstojsic.bitcoin.proxy.json.model.WalletPsbt;
import io.github.gstojsic.bitcoin.proxy.json.model.WalletTransaction;
import io.github.gstojsic.bitcoin.proxy.json.model.WalletTransactionInfo;
import io.github.gstojsic.bitcoin.proxy.json.model.Wallets;
import io.github.gstojsic.bitcoin.proxy.json.model.ZmqNotificationInfo;
import io.github.gstojsic.bitcoin.proxy.model.AddNetworkCommand;
import io.github.gstojsic.bitcoin.proxy.model.AddressType;
import io.github.gstojsic.bitcoin.proxy.model.BlockStatOptions;
import io.github.gstojsic.bitcoin.proxy.model.BumpFeeOptions;
import io.github.gstojsic.bitcoin.proxy.model.Command;
import io.github.gstojsic.bitcoin.proxy.model.CreateFundedPsbtOptions;
import io.github.gstojsic.bitcoin.proxy.model.Descriptor;
import io.github.gstojsic.bitcoin.proxy.model.EstimateMode;
import io.github.gstojsic.bitcoin.proxy.model.FundTransactionOptions;
import io.github.gstojsic.bitcoin.proxy.model.HashType;
import io.github.gstojsic.bitcoin.proxy.model.ImportData;
import io.github.gstojsic.bitcoin.proxy.model.ListUnspentOptions;
import io.github.gstojsic.bitcoin.proxy.model.Logging;
import io.github.gstojsic.bitcoin.proxy.model.MiningTemplate;
import io.github.gstojsic.bitcoin.proxy.model.NetworkType;
import io.github.gstojsic.bitcoin.proxy.model.PrevTx;
import io.github.gstojsic.bitcoin.proxy.model.PsbtDescriptor;
import io.github.gstojsic.bitcoin.proxy.model.PsbtInput;
import io.github.gstojsic.bitcoin.proxy.model.ScanTxAction;
import io.github.gstojsic.bitcoin.proxy.model.SendOptions;
import io.github.gstojsic.bitcoin.proxy.model.SetBanCommand;
import io.github.gstojsic.bitcoin.proxy.model.SigHashType;
import io.github.gstojsic.bitcoin.proxy.model.Transaction;
import io.github.gstojsic.bitcoin.proxy.model.WalletFlag;
import io.github.gstojsic.bitcoin.proxy.util.Pair;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class BitcoinProxy implements BtcRpc {
    private final BitcoinProxyAsync client;

    public BitcoinProxy(String host, int port, String user, String password) {
        this(host, port, user, password, null);
    }

    public BitcoinProxy(String host, int port, String user, String password, String wallet) {
        this.client = new BitcoinProxyAsync(host, port, user, password, wallet);
    }

    @Override
    public String getBestBlockhash() {
        return doGet(client.getBestBlockhash());
    }

    @Override
    public String getBlock(String blockHash) {
        return doGet(client.getBlock(blockHash));
    }

    @Override
    public Block1 getBlockVerbose1(String blockHash) {
        return doGet(client.getBlockVerbose1(blockHash));
    }

    @Override
    public Block2 getBlockVerbose2(String blockHash) {
        return doGet(client.getBlockVerbose2(blockHash));
    }

    @Override
    public Block2 getBlockVerbose3(String blockHash) {
        return doGet(client.getBlockVerbose3(blockHash));
    }

    @Override
    public BlockchainInfo getBlockchainInfo() {
        return doGet(client.getBlockchainInfo());
    }

    @Override
    public int getBlockCount() {
        return doGet(client.getBlockCount());
    }

    @Override
    public BlockFilter getBlockFilter(String blockhash, String filterType) {
        return doGet(client.getBlockFilter(blockhash, filterType));
    }

    @Override
    public String getBlockhash(int height) {
        return doGet(client.getBlockhash(height));
    }

    @Override
    public String getBlockHeader(String blockhash) {
        return doGet(client.getBlockHeader(blockhash));
    }

    @Override
    public BlockHeader getBlockHeaderVerbose(String blockhash) {
        return doGet(client.getBlockHeaderVerbose(blockhash));
    }

    @Override
    public BlockStats getBlockStats(String hash, Set<BlockStatOptions> blockStats) {
        return doGet(client.getBlockStats(hash, blockStats));
    }

    @Override
    public BlockStats getBlockStats(int height, Set<BlockStatOptions> blockStats) {
        return doGet(client.getBlockStats(height, blockStats));
    }

    @Override
    public List<ChainTipData> getChainTips() {
        return doGet(client.getChainTips());
    }

    @Override
    public ChainTxStats getChainTxStats(Integer nBlocks, String blockhash) {
        return doGet(client.getChainTxStats(nBlocks, blockhash));
    }

    @Override
    public double getDifficulty() {
        return doGet(client.getDifficulty());
    }

    @Override
    public List<String> getMempoolAncestors(String txId) {
        return doGet(client.getMempoolAncestors(txId));
    }

    @Override
    public Map<String, MempoolData> getMempoolAncestorsVerbose(String txId) {
        return doGet(client.getMempoolAncestorsVerbose(txId));
    }

    @Override
    public List<String> getMempoolDescendants(String txId) {
        return doGet(client.getMempoolDescendants(txId));
    }

    @Override
    public Map<String, MempoolData> getMempoolDescendantsVerbose(String txId) {
        return doGet(client.getMempoolDescendantsVerbose(txId));
    }

    @Override
    public MempoolData getMempoolEntry(String txId) {
        return doGet(client.getMempoolEntry(txId));
    }

    @Override
    public MempoolInfo getMempoolInfo() {
        return doGet(client.getMempoolInfo());
    }

    @Override
    public List<String> getRawMempool() {
        return doGet(client.getRawMempool());
    }

    @Override
    public Map<String, MempoolData> getRawMempoolVerbose() {
        return doGet(client.getRawMempoolVerbose());
    }

    @Override
    public MempoolWithSeq getRawMempoolWithSequence() {
        return doGet(client.getRawMempoolWithSequence());
    }

    @Override
    public TransactionOutput getTxOut(String txId, int n, Boolean includeMempool) {
        return doGet(client.getTxOut(txId, n, includeMempool));
    }

    @Override
    public String getTxOutProof(List<String> txIds, String blockhash) {
        return doGet(client.getTxOutProof(txIds, blockhash));
    }

    @Override
    public TransactionOutputSetInfo getTxOutsetInfo(HashType hashType) {
        return doGet(client.getTxOutsetInfo(hashType));
    }

    @Override
    public void preciousBlock(String blockhash) {
        doGet(client.preciousBlock(blockhash));
    }

    @Override
    public int pruneBlockchain(int height) {
        return doGet(client.pruneBlockchain(height));
    }

    @Override
    public DumpFile saveMempool() {
        return doGet(client.saveMempool());
    }

    @Override
    public ScanTxOutResult scanTxOutset(ScanTxAction action, List<PsbtDescriptor> scanObjects) {
        return doGet(client.scanTxOutset(action, scanObjects));
    }

    @Override
    public boolean verifyChain(int checkLevel, int nBlocks) {
        return doGet(client.verifyChain(checkLevel, nBlocks));
    }

    @Override
    public List<String> verifyTxOutProof(String proof) {
        return doGet(client.verifyTxOutProof(proof));
    }

    @Override
    public MemoryInfo getMemoryInfo() {
        return doGet(client.getMemoryInfo());
    }

    @Override
    public String getMemoryInfoMalloc() {
        return doGet(client.getMemoryInfoMalloc());
    }

    @Override
    public RpcInfo getRpcInfo() {
        return doGet(client.getRpcInfo());
    }

    @Override
    public String help(Command command) {
        return doGet(client.help(command));
    }

    @Override
    public Map<String, Boolean> logging(Set<Logging> include, Set<Logging> exclude) {
        return doGet(client.logging(include, exclude));
    }

    @Override
    public String stop() {
        return doGet(client.stop());
    }

    @Override
    public int uptime() {
        return doGet(client.uptime());
    }

    @Override
    public GeneratedBlock generateBlock(String output, List<String> transactionIds) {
        return doGet(client.generateBlock(output, transactionIds));
    }

    @Override
    public List<String> generateToAddress(int nBlocks, String address, Integer maxTries) {
        return doGet(client.generateToAddress(nBlocks, address, maxTries));
    }

    @Override
    public List<String> generateToDescriptor(int nBlocks, String descriptor, Integer maxTries) {
        return doGet(client.generateToDescriptor(nBlocks, descriptor, maxTries));
    }

    @Override
    public BlockTemplate getBlockTemplate(MiningTemplate templateRequest) {
        return doGet(client.getBlockTemplate(templateRequest));
    }

    @Override
    public String getBlockTemplateProposal(MiningTemplate templateRequest) {
        return doGet(client.getBlockTemplateProposal(templateRequest));
    }

    @Override
    public MiningInfo getMiningInfo() {
        return doGet(client.getMiningInfo());
    }

    @Override
    public double getNetworkHashPs(Integer nBlocks, Integer height) {
        return doGet(client.getNetworkHashPs(nBlocks, height));
    }

    @Override
    public boolean prioritiseTransaction(String txId, int feeDelta) {
        return doGet(client.prioritiseTransaction(txId, feeDelta));
    }

    @Override
    public String submitBlock(String hexData) {
        return doGet(client.submitBlock(hexData));
    }

    @Override
    public void submitHeader(String hexData) {
        doGet(client.submitHeader(hexData));
    }

    @Override
    public void addNode(String node, AddNetworkCommand command) {
        doGet(client.addNode(node, command));
    }

    @Override
    public void clearBanned() {
        doGet(client.clearBanned());
    }

    @Override
    public void disconnectNode(String address, Integer nodeId) {
        doGet(client.disconnectNode(address, nodeId));
    }

    @Override
    public List<AddedNodeInfo> getAddedNodeInfo(String node) {
        return doGet(client.getAddedNodeInfo(node));
    }

    @Override
    public Empty getBlockFromPeer(String blockhash, int peerId) {
        return doGet(client.getBlockFromPeer(blockhash, peerId));
    }

    @Override
    public int getConnectionCount() {
        return doGet(client.getConnectionCount());
    }

    @Override
    public NetTotals getNetTotals() {
        return doGet(client.getNetTotals());
    }

    @Override
    public NetworkInfo getNetworkInfo() {
        return doGet(client.getNetworkInfo());
    }

    @Override
    public List<NodeAddress> getNodeAddresses(Integer count, NetworkType networkType) {
        return doGet(client.getNodeAddresses(count, networkType));
    }

    @Override
    public List<PeerInfo> getPeerInfo() {
        return doGet(client.getPeerInfo());
    }

    @Override
    public List<BannedInfo> listBanned() {
        return doGet(client.listBanned());
    }

    @Override
    public void ping() {
        doGet(client.ping());
    }

    @Override
    public void setBan(String subnet, SetBanCommand command, Integer banTime, Boolean absolute) {
        doGet(client.setBan(subnet, command, banTime, absolute));
    }

    @Override
    public boolean setNetworkActive(boolean state) {
        return doGet(client.setNetworkActive(state));
    }

    @Override
    public AddPeerAddress addPeerAddress(String host, int port) {
        return doGet(client.addPeerAddress(host, port));
    }

    @Override
    public PsbtAnalysis analyzePsbt(String psbt) {
        return doGet(client.analyzePsbt(psbt));
    }

    @Override
    public String combinePsbt(List<String> txs) {
        return doGet(client.combinePsbt(txs));
    }

    @Override
    public String combineRawTransaction(List<String> txs) {
        return doGet(client.combineRawTransaction(txs));
    }

    @Override
    public String convertToPsbt(String hexRawTrans, Boolean permitSigData, Boolean isWitness) {
        return doGet(client.convertToPsbt(hexRawTrans, permitSigData, isWitness));
    }

    @Override
    public String createPsbt(List<PsbtInput> inputs, Map<String, String> outputAddresses, String outputData, Integer lockTime, Boolean replaceable) {
        return doGet(client.createPsbt(inputs, outputAddresses, outputData, lockTime, replaceable));
    }

    @Override
    public String createRawTransaction(List<PsbtInput> inputs, Map<String, String> outputAddresses, String outputData, Integer lockTime, Boolean replaceable) {
        return doGet(client.createRawTransaction(inputs, outputAddresses, outputData, lockTime, replaceable));
    }

    @Override
    public DecodedPsbt decodePsbt(String psbt) {
        return doGet(client.decodePsbt(psbt));
    }

    @Override
    public RawTransaction decodeRawTransaction(String hexRawTrans, Boolean isWitness) {
        return doGet(client.decodeRawTransaction(hexRawTrans, isWitness));
    }

    @Override
    public DecodedScript decodeScript(String hexScript) {
        return doGet(client.decodeScript(hexScript));
    }

    @Override
    public FinalizedPsbt finalizePsbt(String psbt, Boolean extract) {
        return doGet(client.finalizePsbt(psbt, extract));
    }

    @Override
    public TransactionFunding fundRawTransaction(String hexRawTrans, FundTransactionOptions options, Boolean isWitness) {
        return doGet(client.fundRawTransaction(hexRawTrans, options, isWitness));
    }

    @Override
    public String getRawTransaction(String txId, String blockHash) {
        return doGet(client.getRawTransaction(txId, blockHash));
    }

    @Override
    public RawTransaction getRawTransactionVerbose(String txId, String blockHash) {
        return doGet(client.getRawTransactionVerbose(txId, blockHash));
    }

    @Override
    public String joinPsbts(List<String> transactions) {
        return doGet(client.joinPsbts(transactions));
    }

    @Override
    public String sendRawTransaction(String hexTrans, String maxFeeRate) {
        return doGet(client.sendRawTransaction(hexTrans, maxFeeRate));
    }

    @Override
    public SignTransactionResult signRawTransactionWithKey(String hexTrans, List<String> privateKeys, List<PrevTx> previousTxs, SigHashType sigHashType) {
        return doGet(client.signRawTransactionWithKey(hexTrans, privateKeys, previousTxs, sigHashType));
    }

    @Override
    public List<MempoolAccept> testMempoolAccept(List<String> rawTxs, String maxFeeRate) {
        return doGet(client.testMempoolAccept(rawTxs, maxFeeRate));
    }

    @Override
    public String utxoUpdatePsbt(String psbt, List<String> descriptors) {
        return doGet(client.utxoUpdatePsbt(psbt, descriptors));
    }

    @Override
    public String utxoUpdatePsbtDesc(String psbt, List<PsbtDescriptor> descriptors) {
        return doGet(client.utxoUpdatePsbtDesc(psbt, descriptors));
    }

    @Override
    public MultisigAddress createMultisig(int nRequired, List<String> keys, AddressType addressType) {
        return doGet(client.createMultisig(nRequired, keys, addressType));
    }

    @Override
    public List<String> deriveAddresses(String descriptor, Pair<Integer, Integer> range) {
        return doGet(client.deriveAddresses(descriptor, range));
    }

    @Override
    public FeeEstimate estimateSmartFee(int confirmationTarget, EstimateMode estimateMode) {
        return doGet(client.estimateSmartFee(confirmationTarget, estimateMode));
    }

    @Override
    public DeploymentInfo getDeploymentInfo(String blockhash) {
        return doGet(client.getDeploymentInfo(blockhash));
    }

    @Override
    public DescriptorInfo getDescriptorInfo(String descriptor) {
        return doGet(client.getDescriptorInfo(descriptor));
    }

    @Override
    public Map<String, IndexInfo> getIndexInfo(String indexName) {
        return doGet(client.getIndexInfo(indexName));
    }

    @Override
    public List<ZmqNotificationInfo> getZmqNotifications() {
        return doGet(client.getZmqNotifications());
    }

    @Override
    public String signMessageWithPrivKey(String privateKey, String message) {
        return doGet(client.signMessageWithPrivKey(privateKey, message));
    }

    @Override
    public AddressValidation validateAddress(String address) {
        return doGet(client.validateAddress(address));
    }

    @Override
    public boolean verifyMessage(String address, String signature, String message) {
        return doGet(client.verifyMessage(address, signature, message));
    }

    @Override
    public void abandonTransaction(String txId) {
        doGet(client.abandonTransaction(txId));
    }

    @Override
    public boolean abortRescan() {
        return doGet(client.abortRescan());
    }

    @Override
    public MultisigAddress addMultisigAddress(int nRequired, List<String> keys, String label, AddressType addressType) {
        return doGet(client.addMultisigAddress(nRequired, keys, label, addressType));
    }

    @Override
    public void backupWallet(String destination) {
        doGet(client.backupWallet(destination));
    }

    @Override
    public BumpFee bumpFee(String txId, BumpFeeOptions options) {
        return doGet(client.bumpFee(txId, options));
    }

    @Override
    public Wallet createWallet(String walletName,
                               Boolean disablePrivateKeys,
                               Boolean blank,
                               String passphrase,
                               Boolean avoidReuse,
                               Boolean descriptors,
                               Boolean loadOnStartup,
                               Boolean externalSigner) {
        return doGet(client.createWallet(
                walletName,
                disablePrivateKeys,
                blank,
                passphrase,
                avoidReuse,
                descriptors,
                loadOnStartup,
                externalSigner));
    }

    @Override
    public String dumpPrivKey(String address) {
        return doGet(client.dumpPrivKey(address));
    }

    @Override
    public DumpFile dumpWallet(String filename) {
        return doGet(client.dumpWallet(filename));
    }

    @Override
    public String encryptWallet(String passphrase) {
        return doGet(client.encryptWallet(passphrase));
    }

    @Override
    public Signers enumerateSigners() {
        return doGet(client.enumerateSigners());
    }

    @Override
    public Map<String, AddressByLabel> getAddressesByLabel(String label) {
        return doGet(client.getAddressesByLabel(label));
    }

    @Override
    public AddressInfo getAddressInfo(String address) {
        return doGet(client.getAddressInfo(address));
    }

    @Override
    public double getBalance(Integer minConf, Boolean includeWatchOnly, Boolean avoidReuse) {
        return doGet(client.getBalance(minConf, includeWatchOnly, avoidReuse));
    }

    @Override
    public BalancesData getBalances() {
        return doGet(client.getBalances());
    }

    @Override
    public String getNewAddress(String label, AddressType addressType) {
        return doGet(client.getNewAddress(label, addressType));
    }

    @Override
    public String getRawChangeAddress(AddressType addressType) {
        return doGet(client.getRawChangeAddress(addressType));
    }

    @Override
    public double getReceivedByAddress(String address, Integer minConf, Boolean includeImmatureCoinbase) {
        return doGet(client.getReceivedByAddress(address, minConf, includeImmatureCoinbase));
    }

    @Override
    public double getReceivedByLabel(String label, Integer minConf, Boolean includeImmatureCoinbase) {
        return doGet(client.getReceivedByLabel(label, minConf, includeImmatureCoinbase));
    }

    @Override
    public WalletTransaction getTransaction(String txId, Boolean includeWatchOnly, Boolean verbose) {
        return doGet(client.getTransaction(txId, includeWatchOnly, verbose));
    }

    @Override
    public String getUnconfirmedBalance() {
        return doGet(client.getUnconfirmedBalance());
    }

    @Override
    public WalletInfo getWalletInfo() {
        return doGet(client.getWalletInfo());
    }

    @Override
    public void importAddress(String address, String label, Boolean rescan, Boolean p2sh) {
        doGet(client.importAddress(address, label, rescan, p2sh));
    }

    @Override
    public List<ImportMultiResult> importDescriptors(List<Descriptor> descriptors) {
        return doGet(client.importDescriptors(descriptors));
    }

    @Override
    public List<ImportMultiResult> importMulti(List<ImportData> requests, Boolean rescan) {
        return doGet(client.importMulti(requests, rescan));
    }

    @Override
    public void importPrivKey(String privateKey, String label, Boolean rescan) {
        doGet(client.importPrivKey(privateKey, label, rescan));
    }

    @Override
    public void importPrunedFunds(String rawTransaction, String txOutProof) {
        doGet(client.importPrunedFunds(rawTransaction, txOutProof));
    }

    @Override
    public void importPubKey(String pubKey, String label, Boolean rescan) {
        doGet(client.importPubKey(pubKey, label, rescan));
    }

    @Override
    public void importWallet(String filename) {
        doGet(client.importWallet(filename));
    }

    @Override
    public void keypoolRefill(Integer newSize) {
        doGet(client.keypoolRefill(newSize));
    }

    @Override
    public List<List<ListAddressGrouping>> listAddressGroupings() {
        return doGet(client.listAddressGroupings());
    }

    @Override
    public Descriptors listDescriptors(Boolean showPrivate) {
        return doGet(client.listDescriptors(showPrivate));
    }

    @Override
    public List<String> listLabels(String purpose) {
        return doGet(client.listLabels(purpose));
    }

    @Override
    public List<UtxoKey> listLockUnspent() {
        return doGet(client.listLockUnspent());
    }

    @Override
    public List<BalanceByAddress> listReceivedByAddress(
            Integer minConf,
            Boolean includeEmpty,
            Boolean includeWatchOnly,
            String addressFilter,
            Boolean includeImmatureCoinbase) {
        return doGet(client.listReceivedByAddress(minConf, includeEmpty, includeWatchOnly, addressFilter, includeImmatureCoinbase));
    }

    @Override
    public List<TransactionByLabel> listReceivedByLabel(
            Integer minConf,
            Boolean includeEmpty,
            Boolean includeWatchOnly,
            Boolean includeImmatureCoinbase) {
        return doGet(client.listReceivedByLabel(minConf, includeEmpty, includeWatchOnly, includeImmatureCoinbase));
    }

    @Override
    public TransactionSinceBlock listSinceBlock(String blockhash, Integer targetConfirmations, Boolean includeWatchOnly, Boolean includeRemoved) {
        return doGet(client.listSinceBlock(blockhash, targetConfirmations, includeWatchOnly, includeRemoved));
    }

    @Override
    public List<WalletTransactionInfo> listTransactions(String label, Integer count, Integer skip, Boolean includeWatchOnly) {
        return doGet(client.listTransactions(label, count, skip, includeWatchOnly));
    }

    @Override
    public List<UnspentInfo> listUnspent(Integer minConf, Integer maxConf, List<String> addresses, Boolean includeUnsafe, ListUnspentOptions queryOptions) {
        return doGet(client.listUnspent(minConf, maxConf, addresses, includeUnsafe, queryOptions));
    }

    @Override
    public Wallets listWalletDir() {
        return doGet(client.listWalletDir());
    }

    @Override
    public List<String> listWallets() {
        return doGet(client.listWallets());
    }

    @Override
    public Wallet loadWallet(String filename, Boolean loadOnStartup) {
        return doGet(client.loadWallet(filename, loadOnStartup));
    }

    @Override
    public boolean lockUnspent(boolean unlock, List<Transaction> transactions) {
        return doGet(client.lockUnspent(unlock, transactions));
    }

    @Override
    public void newKeypool() {
        doGet(client.newKeypool());
    }

    @Override
    public PsbtBumpFee psbtBumpFee(String txId, BumpFeeOptions options) {
        return doGet(client.psbtBumpFee(txId, options));
    }

    @Override
    public void removePrunedFunds(String txId) {
        doGet(client.removePrunedFunds(txId));
    }

    @Override
    public ScanInfo rescanBlockchain(Integer startHeight, Integer stopHeight) {
        return doGet(client.rescanBlockchain(startHeight, stopHeight));
    }

    @Override
    public Wallet restoreWallet(String walletName, String backupFile, Boolean loadOnStartup) {
        return doGet(client.restoreWallet(walletName, backupFile, loadOnStartup));
    }

    @Override
    public SendInfo send(
            Map<String, String> outputs,
            Integer confTarget,
            EstimateMode estimateMode,
            String feeRate,
            SendOptions sendOptions) {
        return doGet(client.send(outputs, confTarget, estimateMode, feeRate, sendOptions));
    }

    @Override
    public String sendMany(
            Map<String, String> amounts,
            String comment,
            List<String> subtractFeeFrom,
            Boolean replaceable,
            Integer confTarget,
            EstimateMode estimateMode,
            String feeRate) {
        return doGet(client.sendMany(amounts, comment, subtractFeeFrom, replaceable, confTarget, estimateMode, feeRate));
    }

    @Override
    public SendToAddressInfo sendManyVerbose(
            Map<String, String> amounts,
            String comment,
            List<String> subtractFeeFrom,
            Boolean replaceable,
            Integer confTarget,
            EstimateMode estimateMode,
            String feeRate) {
        return doGet(client.sendManyVerbose(amounts, comment, subtractFeeFrom, replaceable, confTarget, estimateMode, feeRate));
    }

    @Override
    public String sendToAddress(String address,
                                String amount,
                                String comment,
                                String commentTo,
                                Boolean subtractFeeFromAmount,
                                Boolean replaceable,
                                Integer confTarget,
                                EstimateMode estimateMode,
                                Boolean avoidReuse,
                                String feeRate) {
        return doGet(client.sendToAddress(
                address,
                amount,
                comment,
                commentTo,
                subtractFeeFromAmount,
                replaceable,
                confTarget,
                estimateMode,
                avoidReuse,
                feeRate));
    }

    @Override
    public SendToAddressInfo sendToAddressVerbose(String address,
                                                  String amount,
                                                  String comment,
                                                  String commentTo,
                                                  Boolean subtractFeeFromAmount,
                                                  Boolean replaceable,
                                                  Integer confTarget,
                                                  EstimateMode estimateMode,
                                                  Boolean avoidReuse,
                                                  String feeRate) {
        return doGet(client.sendToAddressVerbose(
                address,
                amount,
                comment,
                commentTo,
                subtractFeeFromAmount,
                replaceable,
                confTarget,
                estimateMode,
                avoidReuse,
                feeRate));
    }

    @Override
    public void setHdSeed(Boolean newKeyPool, String seed) {
        doGet(client.setHdSeed(newKeyPool, seed));
    }

    @Override
    public void setLabel(String address, String label) {
        doGet(client.setLabel(address, label));
    }

    @Override
    public boolean setTxFee(String amount) {
        return doGet(client.setTxFee(amount));
    }

    @Override
    public WalletFlagInfo setWalletFlag(WalletFlag flag, Boolean value) {
        return doGet(client.setWalletFlag(flag, value));
    }

    @Override
    public String signMessage(String address, String message) {
        return doGet(client.signMessage(address, message));
    }

    @Override
    public SignTransactionResult signRawTransactionWithWallet(String hexTran, List<PrevTx> prevTxs, SigHashType sigHashType) {
        return doGet(client.signRawTransactionWithWallet(hexTran, prevTxs, sigHashType));
    }

    @Override
    public UnloadWallet unloadWallet(String walletName, Boolean loadOnStartup) {
        return doGet(client.unloadWallet(walletName, loadOnStartup));
    }

    @Override
    public UpgradeWallet upgradeWallet(Integer version) {
        return doGet(client.upgradeWallet(version));
    }

    @Override
    public WalletFundedPsbt walletCreateFundedPsbt(
            List<PsbtInput> inputs,
            Map<String, String> outputAddresses,
            String outputData,
            Integer lockTime,
            CreateFundedPsbtOptions options,
            Boolean bip32Derivs) {
        return doGet(client.walletCreateFundedPsbt(inputs, outputAddresses, outputData, lockTime, options, bip32Derivs));
    }

    @Override
    public WalletDisplayAddress walletDisplayAddress(String address) {
        return doGet(client.walletDisplayAddress(address));
    }

    @Override
    public void walletLock() {
        doGet(client.walletLock());
    }

    @Override
    public void walletPassphrase(String passphrase, int timeout) {
        doGet(client.walletPassphrase(passphrase, timeout));
    }

    @Override
    public void walletPassphraseChange(String oldPassphrase, String newPassphrase) {
        doGet(client.walletPassphraseChange(oldPassphrase, newPassphrase));
    }

    @Override
    public WalletPsbt walletProcessPsbt(String psbt, Boolean sign, SigHashType sigHashType, Boolean bip32Derivs, Boolean finalize) {
        return doGet(client.walletProcessPsbt(psbt, sign, sigHashType, bip32Derivs, finalize));
    }

    private <T> T doGet(CompletableFuture<T> future) {
        try {
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            var cause = e.getCause();
            if (cause instanceof RpcException)
                throw (RpcException) cause;
            else
                throw new RuntimeException(e);
        }
    }
}
