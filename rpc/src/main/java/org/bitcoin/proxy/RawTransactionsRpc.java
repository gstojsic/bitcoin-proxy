package org.bitcoin.proxy;

public interface RawTransactionsRpc {
    //analyzepsbt
    void analyzePsbt();

    //combinepsbt
    void combinePsbt();

    //combinerawtransaction
    void combineRawTransaction();

    //converttopsbt
    void convertToPsbt();

    //createpsbt
    void createPsbt();

    //createrawtransaction
    void createRawTransaction();

    //decodepsbt
    void decodePsbt();

    //decoderawtransaction
    void decodeRawTransaction();

    //decodescript
    void decodeScript();

    //finalizepsbt
    void finalizePsbt();

    //fundrawtransaction
    void fundRawTransaction();

    //getrawtransaction
    void getRawTransaction();

    //joinpsbts
    void joinPsbts();

    //sendrawtransaction
    void sendRawTransaction();

    //signrawtransactionwithkey
    void signRawTransactionWithKey();

    //testmempoolaccept
    void testMempoolAccept();

    //utxoupdatepsbt
    void utxoUpdatePsbt();
}
