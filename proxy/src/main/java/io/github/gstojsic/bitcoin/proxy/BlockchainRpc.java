package io.github.gstojsic.bitcoin.proxy;

import io.github.gstojsic.bitcoin.proxy.json.model.Block1;
import io.github.gstojsic.bitcoin.proxy.json.model.Block2;
import io.github.gstojsic.bitcoin.proxy.json.model.BlockFilter;
import io.github.gstojsic.bitcoin.proxy.json.model.BlockHeader;
import io.github.gstojsic.bitcoin.proxy.json.model.BlockStats;
import io.github.gstojsic.bitcoin.proxy.json.model.BlockchainInfo;
import io.github.gstojsic.bitcoin.proxy.json.model.ChainTipData;
import io.github.gstojsic.bitcoin.proxy.json.model.ChainTxStats;
import io.github.gstojsic.bitcoin.proxy.json.model.DumpFile;
import io.github.gstojsic.bitcoin.proxy.json.model.MempoolData;
import io.github.gstojsic.bitcoin.proxy.json.model.MempoolInfo;
import io.github.gstojsic.bitcoin.proxy.json.model.MempoolWithSeq;
import io.github.gstojsic.bitcoin.proxy.json.model.ScanTxOutResult;
import io.github.gstojsic.bitcoin.proxy.json.model.TransactionOutput;
import io.github.gstojsic.bitcoin.proxy.json.model.TransactionOutputSetInfo;
import io.github.gstojsic.bitcoin.proxy.model.BlockStatOptions;
import io.github.gstojsic.bitcoin.proxy.model.HashType;
import io.github.gstojsic.bitcoin.proxy.model.PsbtDescriptor;
import io.github.gstojsic.bitcoin.proxy.model.ScanTxAction;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface BlockchainRpc {

    /**
     * @see BlockchainRpcAsync#getBestBlockhash()
     */
    String getBestBlockhash();

    /**
     * @see BlockchainRpcAsync#getBlock(String)
     */
    String getBlock(String blockHash);

    /**
     * @see BlockchainRpcAsync#getBlockVerbose1(String)
     */
    Block1 getBlockVerbose1(String blockHash);

    /**
     * @see BlockchainRpcAsync#getBlockVerbose2(String)
     */
    Block2 getBlockVerbose2(String blockHash);

    /**
     * @see BlockchainRpcAsync#getBlockVerbose3(String)
     */
    Block2 getBlockVerbose3(String blockHash);

    /**
     * @see BlockchainRpcAsync#getBlockchainInfo()
     */
    BlockchainInfo getBlockchainInfo();

    /**
     * @see BlockchainRpcAsync#getBlockCount()
     */
    int getBlockCount();

    /**
     * @see BlockchainRpcAsync#getBlockFilter(String, String)
     */
    BlockFilter getBlockFilter(String blockhash, String filterType);

    /**
     * @see BlockchainRpcAsync#getBlockhash(int)
     */
    String getBlockhash(int height);

    /**
     * @see BlockchainRpcAsync#getBlockHeader(String)
     */
    String getBlockHeader(String blockhash);

    /**
     * @see BlockchainRpcAsync#getBlockHeaderVerbose(String)
     */
    BlockHeader getBlockHeaderVerbose(String blockhash);

    /**
     * @see BlockchainRpcAsync#getBlockStats(String, Set)
     */
    BlockStats getBlockStats(String hash, Set<BlockStatOptions> blockStats);

    /**
     * @see BlockchainRpcAsync#getBlockStats(int, Set)
     */
    BlockStats getBlockStats(int height, Set<BlockStatOptions> blockStats);

    /**
     * @see BlockchainRpcAsync#getChainTips()
     */
    List<ChainTipData> getChainTips();

    /**
     * @see BlockchainRpcAsync#getChainTxStats(Integer, String)
     */
    ChainTxStats getChainTxStats(Integer nBlocks, String blockhash);

    /**
     * @see BlockchainRpcAsync#getDifficulty()
     */
    double getDifficulty();

    /**
     * @see BlockchainRpcAsync#getMempoolAncestors(String)
     */
    List<String> getMempoolAncestors(String txId);

    /**
     * @see BlockchainRpcAsync#getMempoolAncestorsVerbose(String)
     */
    Map<String, MempoolData> getMempoolAncestorsVerbose(String txId);

    /**
     * @see BlockchainRpcAsync#getMempoolDescendants(String)
     */
    List<String> getMempoolDescendants(String txId);

    /**
     * @see BlockchainRpcAsync#getMempoolDescendantsVerbose(String)
     */
    Map<String, MempoolData> getMempoolDescendantsVerbose(String txId);

    /**
     * @see BlockchainRpcAsync#getMempoolEntry(String)
     */
    MempoolData getMempoolEntry(String txId);

    /**
     * @see BlockchainRpcAsync#getMempoolInfo()
     */
    MempoolInfo getMempoolInfo();

    /**
     * @see BlockchainRpcAsync#getRawMempool()
     */
    List<String> getRawMempool();

    /**
     * @see BlockchainRpcAsync#getRawMempoolVerbose()
     */
    Map<String, MempoolData> getRawMempoolVerbose();

    /**
     * @see BlockchainRpcAsync#getRawMempoolWithSequence()
     */
    MempoolWithSeq getRawMempoolWithSequence();

    /**
     * @see BlockchainRpcAsync#getTxOut(String, int, Boolean)
     */
    TransactionOutput getTxOut(String txId, int n, Boolean includeMempool);

    /**
     * @see BlockchainRpcAsync#getTxOutProof(List, String)
     */
    String getTxOutProof(List<String> txIds, String blockhash);

    /**
     * @see BlockchainRpcAsync#getTxOutsetInfo(HashType)
     */
    TransactionOutputSetInfo getTxOutsetInfo(HashType hashType);

    /**
     * @see BlockchainRpcAsync#preciousBlock(String)
     */
    void preciousBlock(String blockhash);

    /**
     * @see BlockchainRpcAsync#pruneBlockchain(int)
     */
    int pruneBlockchain(int height);

    /**
     * @see BlockchainRpcAsync#saveMempool()
     */
    DumpFile saveMempool();

    /**
     * @see BlockchainRpcAsync#scanTxOutset(ScanTxAction, List)
     */
    ScanTxOutResult scanTxOutset(ScanTxAction action, List<PsbtDescriptor> scanObjects);

    /**
     * @see BlockchainRpcAsync#verifyChain(int, int)
     */
    boolean verifyChain(int checkLevel, int nBlocks);

    /**
     * @see BlockchainRpcAsync#verifyTxOutProof(String)
     */
    List<String> verifyTxOutProof(String proof);
}