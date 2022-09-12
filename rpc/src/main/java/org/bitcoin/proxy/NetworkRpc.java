package org.bitcoin.proxy;

public interface NetworkRpc {
    //addnode
    void addNode();

    //clearbanned
    void clearBanned();

    //disconnectnode
    void disconnectNode();

    //getaddednodeinfo
    void getAddedNodeInfo();

    //getconnectioncount
    void getConnectionCount();

    //getnettotals
    void getNetTotals();

    //getnetworkinfo
    void getNetworkInfo();

    //getnodeaddresses
    void getNodeAddresses();

    //getpeerinfo
    void getPeerInfo();

    //listbanned
    void listBanned();

    //ping
    void ping();

    //setban
    void setBan();

    //setnetworkactive
    void setNetworkActive();
}
