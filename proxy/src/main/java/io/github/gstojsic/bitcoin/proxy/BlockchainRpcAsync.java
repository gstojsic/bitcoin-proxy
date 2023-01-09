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
import io.github.gstojsic.bitcoin.proxy.json.model.ScanTxOutsetStatus;
import io.github.gstojsic.bitcoin.proxy.json.model.TransactionOutput;
import io.github.gstojsic.bitcoin.proxy.json.model.TransactionOutputSetInfo;
import io.github.gstojsic.bitcoin.proxy.json.model.TxSpendingPrevOutInfo;
import io.github.gstojsic.bitcoin.proxy.model.BlockStatOptions;
import io.github.gstojsic.bitcoin.proxy.model.HashType;
import io.github.gstojsic.bitcoin.proxy.model.PsbtDescriptor;
import io.github.gstojsic.bitcoin.proxy.model.Transaction;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public interface BlockchainRpcAsync {

    /**
     * <p>Calls getbestblockhash method on the bitcoin node which returns the hash of the best (tip) block in the
     * most-work fully-validated chain.</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.getbestblockhash);</pre>
     *
     * @return the block hash, hex-encoded
     */
    CompletableFuture<String> getBestBlockhash();

    /**
     * <p>Calls getblock method on the bitcoin node which returns a string that is serialized, hex-encoded data for
     * block ‘hash’.</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.getblock);</pre>
     *
     * @param blockhash The block hash
     * @return A string that is serialized, hex-encoded data for block ‘hash’
     */
    CompletableFuture<String> getBlock(String blockhash);

    /**
     * <p>Calls getblock method on the bitcoin node which returns an Object with information about block ‘hash’</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.getblock);</pre>
     *
     * @param blockhash The block hash
     * @return an Object with information about block ‘hash’. See {@link Block1} docs
     */
    CompletableFuture<Block1> getBlockVerbose1(String blockhash);

    /**
     * <p>Calls getblock method on the bitcoin node which returns an Object with information about block ‘hash’
     * and information about each transaction.</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.getblock); </pre>
     *
     * @param blockhash The block hash
     * @return an Object with information about block ‘hash’ and information about each transaction.
     * For more info check {@link Block2} docs
     */
    CompletableFuture<Block2> getBlockVerbose2(String blockhash);

    /**
     * <p>Calls getblock method on the bitcoin node which returns an Object with information about blockhash and
     * information about each transaction, including prevout information for inputs (only for unpruned blocks in the
     * current best chain).</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.getblock); </pre>
     *
     * @param blockhash The block hash
     * @return an Object with information about blockhash and information about each transaction, including prevout
     * information for inputs (only for unpruned blocks in the current best chain). See {@link Block2} docs
     */
    CompletableFuture<Block2> getBlockVerbose3(String blockhash);

    /**
     * <p>Calls getblockchaininfo method on the bitcoin node which returns an object containing various state info
     * regarding blockchain processing.</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.getblockchaininfo);</pre>
     *
     * @return an object containing various state info regarding blockchain processing. See {@link BlockchainInfo} docs
     */
    CompletableFuture<BlockchainInfo> getBlockchainInfo();

    /**
     * <p>Calls getblockcount method on the bitcoin node which returns the height of the most-work fully-validated chain.</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.getblockcount);</pre>
     *
     * @return the height of the most-work fully-validated chain
     */
    CompletableFuture<Integer> getBlockCount();

    /**
     * <p>Calls getblockfilter method on the bitcoin node which returns a BIP 157 content filter for a particular block.</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.getblockfilter);</pre>
     *
     * @param blockhash  The hash of the block
     * @param filterType The type name of the filter
     * @return a BIP 157 content filter for a particular block. See {@link BlockFilter} docs
     */
    CompletableFuture<BlockFilter> getBlockFilter(String blockhash, String filterType);

    /**
     * <p>Calls getblockhash method on the bitcoin node which returns the hash of block in best-block-chain at height provided.</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.getblockhash);</pre>
     *
     * @param height The height index
     * @return the hash of block in best-block-chain at height provided.
     */
    CompletableFuture<String> getBlockhash(int height);

    /**
     * <p>Calls getblockheader method on the bitcoin node which returns a string that is serialized, hex-encoded data for blockheader ‘hash’.</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.getblockheader);</pre>
     *
     * @param blockhash The block hash
     * @return a string that is serialized, hex-encoded data for blockheader ‘hash’.
     */
    CompletableFuture<String> getBlockHeader(String blockhash);

    /**
     * <p>Calls getblockheader method on the bitcoin node which returns an Object with information about blockheader ‘hash’.</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.getblockheader);</pre>
     *
     * @param blockhash The block hash
     * @return an Object with information about blockheader ‘hash’. See {@link BlockFilter} docs
     */
    CompletableFuture<BlockHeader> getBlockHeaderVerbose(String blockhash);

    /**
     * <p>Calls getblockstats method on the bitcoin node which returns per block statistics for a given window. All amounts are in satoshis.</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.getblockstats);</pre>
     *
     * @param hash    The block hash of the target block
     * @param options stats options. default is all values
     * @return per block statistics for a given window. See {@link BlockStats} docs
     */
    CompletableFuture<BlockStats> getBlockStats(String hash, Set<BlockStatOptions> options);

    /**
     * <p>Calls getblockstats method on the bitcoin node which returns per block statistics for a given window. All amounts are in satoshis.</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.getblockstats);</pre>
     *
     * @param height  The height of the target block
     * @param options stats options. default is all values
     * @return per block statistics for a given window. See {@link BlockStats} docs
     */
    CompletableFuture<BlockStats> getBlockStats(int height, Set<BlockStatOptions> options);

    /**
     * <p>Calls getchaintips method on the bitcoin node which returns information about all known tips in the block tree,
     * including the main chain as well as orphaned branches.</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.getchaintips);</pre>
     *
     * @return information about all known tips in the block tree including the main chain as well as orphaned branches.
     * See {@link ChainTipData} docs
     */
    CompletableFuture<List<ChainTipData>> getChainTips();

    /**
     * <p>Calls getchaintxstats method on the bitcoin node which returns statistics about the total number and rate of
     * transactions in the chain.</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.getchaintxstats);</pre>
     *
     * @param nBlocks   Size of the window in number of blocks
     * @param blockhash The hash of the block that ends the window.
     * @return statistics about the total number and rate of transactions in the chain. See {@link ChainTxStats} docs
     */
    CompletableFuture<ChainTxStats> getChainTxStats(Integer nBlocks, String blockhash);

    /**
     * <p>Calls getdifficulty method on the bitcoin node which returns the proof-of-work difficulty as a multiple of
     * the minimum difficulty.</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.getdifficulty);</pre>
     *
     * @return the proof-of-work difficulty as a multiple of the minimum difficulty.
     */
    CompletableFuture<Double> getDifficulty();

    /**
     * <p>Calls getmempoolancestors method on the bitcoin node which returns all in-mempool ancestors.</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.getmempoolancestors);</pre>
     *
     * @param txId The transaction id (must be in mempool)
     * @return list of in mempool ancestor transactions
     */
    CompletableFuture<List<String>> getMempoolAncestors(String txId);

    /**
     * <p>Calls getmempoolancestors method on the bitcoin node which returns all in-mempool ancestors.</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.getmempoolancestors);</pre>
     *
     * @param txId The transaction id (must be in mempool)
     * @return map of in mempool ancestor transactions. Key is transactionId, for value see {@link MempoolData} docs
     */
    CompletableFuture<Map<String, MempoolData>> getMempoolAncestorsVerbose(String txId);

    /**
     * <p>Calls getmempooldescendants method on the bitcoin node which returns all in-mempool descendants.</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.getmempooldescendants);</pre>
     *
     * @param txId The transaction id (must be in mempool)
     * @return list of all in-mempool descendants.
     */
    CompletableFuture<List<String>> getMempoolDescendants(String txId);

    /**
     * <p>Calls getmempooldescendants method on the bitcoin node which returns all in-mempool descendants.</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.getmempooldescendants);</pre>
     *
     * @param txId The transaction id (must be in mempool)
     * @return map of in mempool descendant transactions. Key is transactionId, for value see {@link MempoolData} docs
     */
    CompletableFuture<Map<String, MempoolData>> getMempoolDescendantsVerbose(String txId);

    /**
     * <p>Calls getmempoolentry method on the bitcoin node which returns mempool data for a given transaction</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.getmempoolentry);</pre>
     *
     * @param txId The transaction id (must be in mempool)
     * @return data about the mempool transaction. See {@link MempoolData} docs
     */
    CompletableFuture<MempoolData> getMempoolEntry(String txId);

    /**
     * <p>Calls getmempoolinfo method on the bitcoin node which returns details on the active state of the TX memory pool</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.getmempoolinfo);</pre>
     *
     * @return details on the active state of the TX memory pool. See {@link MempoolInfo} docs
     */
    CompletableFuture<MempoolInfo> getMempoolInfo();

    /**
     * <p>Calls getrawmempool method on the bitcoin node which returns all transaction ids in memory pool as a list
     * of transaction ids</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.getrawmempool);</pre>
     *
     * @return list of transaction ids
     */
    CompletableFuture<List<String>> getRawMempool();

    /**
     * <p>Calls getrawmempool method on the bitcoin node which returns all transaction ids in memory pool as a map
     * of transaction ids to mempool data</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.getrawmempool);</pre>
     *
     * @return map of txIds to {@link MempoolData}
     */
    CompletableFuture<Map<String, MempoolData>> getRawMempoolVerbose();

    /**
     * <p>Calls getrawmempool method on the bitcoin node which returns all transaction ids in memory pool</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.getrawmempool);</pre>
     *
     * @return an {@link MempoolWithSeq} object
     */
    CompletableFuture<MempoolWithSeq> getRawMempoolWithSequence();

    /**
     * <p>Calls gettxout method on the bitcoin node which returns details about an unspent transaction output</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.gettxout);</pre>
     *
     * @param txId           the transaction id
     * @param n              vout number
     * @param includeMempool Whether to include the mempool.
     * @return details about an unspent transaction output. See {@link TransactionOutput}
     */
    CompletableFuture<TransactionOutput> getTxOut(String txId, int n, Boolean includeMempool);

    /**
     * <p>Calls gettxoutproof method on the bitcoin node which returns a hex-encoded proof that “txid” was included in
     * a block.</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.gettxoutproof);</pre>
     *
     * @param txIds     the txids to filter
     * @param blockhash if specified, looks for txid in the block with this hash
     * @return A string that is a serialized, hex-encoded data for the proof.
     */
    CompletableFuture<String> getTxOutProof(List<String> txIds, String blockhash);

    /**
     * <p>Calls gettxoutsetinfo method on the bitcoin node which returns statistics about the unspent transaction output set.</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.gettxoutsetinfo);</pre>
     *
     * @param hashType which UTXO set hash should be calculated
     * @param hash     hash of target block, or
     * @param height   height of target block
     * @param useIndex use coinstatsindex, if available.
     * @return statistics about the unspent transaction output set. See {@link TransactionOutputSetInfo}
     */
    CompletableFuture<TransactionOutputSetInfo> getTxOutsetInfo(
            HashType hashType,
            String hash,
            Integer height,
            Boolean useIndex);

    /**
     * <p>Calls gettxspendingprevout method on the bitcoin node which scans the mempool to find transactions spending
     * any of the given outputs.</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.gettxspendingprevout);</pre>
     *
     * @return list of found transactions. See {@link TxSpendingPrevOutInfo}
     * @since bitcoin core v.24
     */
    CompletableFuture<List<TxSpendingPrevOutInfo>> getTxSpendingPrevOut(List<Transaction> utxoList);

    /**
     * <p>Calls preciousblock method on the bitcoin node which causes the node to treat a block as if it were received
     * before others with the same work.</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.preciousblock);</pre>
     *
     * @param blockhash the hash of the block to mark as precious
     * @return void
     */
    CompletableFuture<Void> preciousBlock(String blockhash);

    /**
     * <p>Calls pruneblockchain method on the bitcoin node which prunes the stored blockchain</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.pruneblockchain);</pre>
     *
     * @param height The block height to prune up to
     * @return Height of the last block pruned
     */
    CompletableFuture<Integer> pruneBlockchain(int height);

    /**
     * <p>Calls savemempool method on the bitcoin node which dumps the mempool to disk</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.savemempool);</pre>
     *
     * @return the directory and file where the mempool was saved. See {@link DumpFile}
     */
    CompletableFuture<DumpFile> saveMempool();

    /**
     * <p>Calls scantxoutset method on the bitcoin node which starts the scan of the unspent transaction output set for
     * entries that match certain output descriptors. Action start is implied.</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.scantxoutset);</pre>
     *
     * @param scanObjects list of scan objects
     * @return an object containing the results of the scan. See {@link ScanTxOutResult}
     */
    CompletableFuture<ScanTxOutResult> scanTxOutset(List<PsbtDescriptor> scanObjects);

    /**
     * <p>Calls scantxoutset method on the bitcoin node which aborts the scan of the unspent transaction output set for
     * entries that match certain output descriptors. Action abort is implied.</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.scantxoutset);</pre>
     *
     * @return true if scan will be aborted (not necessarily before this RPC returns), or false if there is no scan to abort
     */
    CompletableFuture<Boolean> scanTxOutsetAbort();

    /**
     * <p>Calls scantxoutset method on the bitcoin node which gets the status of the scan of the unspent transaction
     * output set for entries that match certain output descriptors. Action status is implied.</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.scantxoutset);</pre>
     *
     * @return Approximate percent complete. See {@link ScanTxOutsetStatus}
     */
    CompletableFuture<ScanTxOutsetStatus> scanTxOutsetStatus();

    /**
     * <p>Calls verifychain method on the bitcoin node which verifies the blockchain database.</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.verifychain);</pre>
     *
     * @param checkLevel the level of how thorough the block verification is
     * @param nBlocks    the number of blocks to check
     * @return true if the chain is verified
     */
    CompletableFuture<Boolean> verifyChain(int checkLevel, int nBlocks);

    /**
     * <p>Calls verifytxoutproof method on the bitcoin node which verifies that a proof points to a transaction in
     * a block, returning the transaction it commits to and throwing an RPC error if the block is not in our best chain</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.verifytxoutproof);</pre>
     *
     * @param proof The hex-encoded proof generated by gettxoutproof
     * @return The txid(s) which the proof commits to, or empty list if the proof can not be validated.
     */
    CompletableFuture<List<String>> verifyTxOutProof(String proof);
}
