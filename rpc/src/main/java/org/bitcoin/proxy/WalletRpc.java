package org.bitcoin.proxy;

/**
 * Note: the wallet RPCs are only available if Bitcoin Core was built with wallet support, which is the default.
 */
public interface WalletRpc {
    //abandontransaction
    void abandonTransaction();

    //abortrescan
    void abortRescan();

    //addmultisigaddress
    void addMultisigAddress();

    //backupwallet
    void backupWallet();

    //bumpfee
    void bumpFee();

    //createwallet
    void createWallet();

    //dumpprivkey
    void dumpPrivKey();

    //dumpwallet
    void dumpWallet();

    //encryptwallet
    void encryptWallet();

    //getaddressesbylabel
    void getAddressesByLabel();

    //getaddressinfo
    void getAddressInfo();

    //getbalance
    void getBalance();

    //getbalances
    void getBalances();

    //getnewaddress
    void getNewAddress();

    //getrawchangeaddress
    void getRawChangeAddress();

    //getreceivedbyaddress
    void getReceivedByAddress();

    //getreceivedbylabel
    void getReceivedByLabel();

    //gettransaction
    void getTransaction();

    //getunconfirmedbalance
    void getUnconfirmedBalance();

    //getwalletinfo
    void getWalletInfo();

    //importaddress
    void importAddress();

    //importdescriptors
    void importDescriptors();

    //importmulti
    void importMulti();

    //importprivkey
    void importPrivKey();

    //importprunedfunds
    void importPrunedFunds();

    //importpubkey
    void importPubKey();

    //importwallet
    void importWallet();

    //keypoolrefill
    void keypoolRefill();

    //listaddressgroupings
    void listAddressGroupings();

    //listlabels
    void listLabels();

    //listlockunspent
    void listLockUnspent();
    
    //listreceivedbyaddress
    void listReceivedByAddress();

    //listreceivedbylabel
    void listReceivedByLabel();

    //listsinceblock
    void listSinceBlock();

    //listtransactions
    void listTransactions();

    //listunspent
    void listUnspent();

    //listwalletdir
    void listWalletDir();

    //listwallets
    void listWallets();

    //loadwallet
    void loadWallet();

    //lockunspent
    void lockUnspent();

    //psbtbumpfee
    void psbtBumpFee();

    //removeprunedfunds
    void removePrunedFunds();

    //rescanblockchain
    void rescanBlockchain();

    //send
    void send();

    //sendmany
    void sendMany();

    //sendtoaddress
    void sendToAddress();

    //sethdseed
    void setHdSeed();

    //setlabel
    void setLabel();

    //settxfee
    void setTxFee();

    //setwalletflag
    void setWalletFlag();

    //signmessage
    void signMessage();

    //signrawtransactionwithwallet
    void signRawTransactionWithWallet();

    //unloadwallet
    void unloadWallet();

    //upgradewallet
    void upgradeWallet();

    //walletcreatefundedpsbt
    void walletCreateFundedPsbt();

    //walletlock
    void walletLock();

    //walletpassphrase
    void walletPassphrase();

    //walletpassphrasechange
    void walletPassphraseChange();

    //walletprocesspsbt
    void walletProcessPsbt();
}
