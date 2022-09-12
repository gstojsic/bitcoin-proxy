package org.bitcoin.proxy;

public interface UtilRpc {
    //createmultisig
    void createMultisig();

    //deriveaddresses
    void deriveAddresses();

    //estimatesmartfee
    void estimateSmartFee();

    //getdescriptorinfo
    void getDescriptorInfo();

    //getindexinfo
    void getIndexInfo();

    //signmessagewithprivkey
    void signMessageWithPrivKey();

    //validateaddress
    void validateAddress();

    //verifymessage
    void verifyMessage();
}
