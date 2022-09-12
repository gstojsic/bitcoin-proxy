package org.bitcoin.proxy;

public interface GeneratingRpc {
    //generateblock
    void generateBlock();

    //generatetoaddress
    void generateToAddress();

    //generatetodescriptor
    void generateToDescriptor();
}
