package org.bitcoin.proxy;

public interface MiningRpc {
    //getblocktemplate
    void getBlockTemplate();

    //getmininginfo
    void getMiningInfo();

    //getnetworkhashps
    void getNetworkHashPs();

    //prioritisetransaction
    void prioritiseTransaction();

    //submitblock
    void submitBlock();

    //submitheader
    void submitHeader();
}
