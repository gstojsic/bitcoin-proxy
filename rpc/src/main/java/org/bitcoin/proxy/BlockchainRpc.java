package org.bitcoin.proxy;

public interface BlockchainRpc {
    //getbestblockhash
    void getBestBlockhash();

    //getblock
    void getBlock();

    //getblockchaininfo
    void getBlockchainInfo();

    //getblockcount
    void getBlockCount();

    //getblockfilter
    void getBlockFilter();

    //getblockhash
    void getBlockhash();

    //getblockheader
    void getBlockHeader();

    //getblockstats
    void getBlockStats();

    //getchaintips
    void getChainTips();

    //getchaintxstats
    void getChainTxStats();

    //getdifficulty
    int getDifficulty();

    //getmempoolancestors
    void getMempoolAncestors();

    //getmempooldescendants
    void getMempoolDescendants();

    //getmempoolentry
    void getMempoolEntry();

    //getmempoolinfo
    void getMempoolInfo();

    //getrawmempool
    void getRawMempool();

    //gettxout
    void getTxOut();

    //gettxoutproof
    void getTxOutProof();

    //gettxoutsetinfo
    void getTxOutsetInfo();

    //preciousblock
    void preciousBlock();

    //pruneblockchain
    void pruneBlockchain();

    //savemempool
    void saveMempool();

    //scantxoutset
    void scanTxOutset();

    //verifychain
    void verifyChain();

    //verifytxoutproof
    void verifyTxOutProof();
}
