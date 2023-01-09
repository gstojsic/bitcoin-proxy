package io.github.gstojsic.bitcoin.proxy;

import io.github.gstojsic.bitcoin.proxy.json.model.AddressByLabel;
import io.github.gstojsic.bitcoin.proxy.json.model.AddressInfo;
import io.github.gstojsic.bitcoin.proxy.json.model.BalanceByAddress;
import io.github.gstojsic.bitcoin.proxy.json.model.BalancesData;
import io.github.gstojsic.bitcoin.proxy.json.model.BumpFee;
import io.github.gstojsic.bitcoin.proxy.json.model.Descriptors;
import io.github.gstojsic.bitcoin.proxy.json.model.DumpFile;
import io.github.gstojsic.bitcoin.proxy.json.model.ImportMultiResult;
import io.github.gstojsic.bitcoin.proxy.json.model.ListAddressGrouping;
import io.github.gstojsic.bitcoin.proxy.json.model.MigrateWalletInfo;
import io.github.gstojsic.bitcoin.proxy.json.model.MultisigAddress;
import io.github.gstojsic.bitcoin.proxy.json.model.PsbtBumpFee;
import io.github.gstojsic.bitcoin.proxy.json.model.ScanInfo;
import io.github.gstojsic.bitcoin.proxy.json.model.SendInfo;
import io.github.gstojsic.bitcoin.proxy.json.model.SendToAddressInfo;
import io.github.gstojsic.bitcoin.proxy.json.model.SignTransactionResult;
import io.github.gstojsic.bitcoin.proxy.json.model.Signers;
import io.github.gstojsic.bitcoin.proxy.json.model.SimulateRawTransactionInfo;
import io.github.gstojsic.bitcoin.proxy.json.model.TransactionByLabel;
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
import io.github.gstojsic.bitcoin.proxy.model.AddressType;
import io.github.gstojsic.bitcoin.proxy.model.BumpFeeOptions;
import io.github.gstojsic.bitcoin.proxy.model.CreateFundedPsbtOptions;
import io.github.gstojsic.bitcoin.proxy.model.Descriptor;
import io.github.gstojsic.bitcoin.proxy.model.EstimateMode;
import io.github.gstojsic.bitcoin.proxy.model.ImportData;
import io.github.gstojsic.bitcoin.proxy.model.ListUnspentOptions;
import io.github.gstojsic.bitcoin.proxy.model.PrevTx;
import io.github.gstojsic.bitcoin.proxy.model.PsbtInput;
import io.github.gstojsic.bitcoin.proxy.model.SendOptions;
import io.github.gstojsic.bitcoin.proxy.model.SigHashType;
import io.github.gstojsic.bitcoin.proxy.model.Transaction;
import io.github.gstojsic.bitcoin.proxy.model.WalletFlag;

import java.util.List;
import java.util.Map;

/**
 * Note: the wallet RPCs are only available if Bitcoin Core was built with wallet support, which is the default.
 */
public interface WalletRpc {

    /**
     * @see WalletRpcAsync#abandonTransaction(String)
     */
    void abandonTransaction(String txId);

    /**
     * @see WalletRpcAsync#abortRescan()
     */
    boolean abortRescan();

    /**
     * @see WalletRpcAsync#addMultisigAddress(int, List, String, AddressType)
     */
    MultisigAddress addMultisigAddress(int nRequired, List<String> keys, String label, AddressType addressType);

    /**
     * @see WalletRpcAsync#backupWallet(String)
     */
    void backupWallet(String destination);

    /**
     * @see WalletRpcAsync#bumpFee(String, BumpFeeOptions)
     */
    BumpFee bumpFee(String txId, BumpFeeOptions options);

    /**
     * @see WalletRpcAsync#createWallet(String, Boolean, Boolean, String, Boolean, Boolean, Boolean, Boolean)
     */
    Wallet createWallet(String walletName,
                        Boolean disablePrivateKeys,
                        Boolean blank,
                        String passphrase,
                        Boolean avoidReuse,
                        Boolean descriptors,
                        Boolean loadOnStartup,
                        Boolean externalSigner);

    /**
     * @see WalletRpcAsync#dumpPrivKey(String)
     */
    String dumpPrivKey(String address);

    /**
     * @see WalletRpcAsync#dumpWallet(String)
     */
    DumpFile dumpWallet(String filename);

    /**
     * @see WalletRpcAsync#encryptWallet(String)
     */
    String encryptWallet(String passphrase);

    /**
     * @see WalletRpcAsync#enumerateSigners()
     */
    Signers enumerateSigners();

    /**
     * @see WalletRpcAsync#getAddressesByLabel(String)
     */
    Map<String, AddressByLabel> getAddressesByLabel(String label);

    /**
     * @see WalletRpcAsync#getAddressInfo(String)
     */
    AddressInfo getAddressInfo(String address);

    /**
     * @see WalletRpcAsync#getBalance(Integer, Boolean, Boolean)
     */
    double getBalance(Integer minConf, Boolean includeWatchOnly, Boolean avoidReuse);

    /**
     * @see WalletRpcAsync#getBalances()
     */
    BalancesData getBalances();

    /**
     * @see WalletRpcAsync#getNewAddress(String, AddressType)
     */
    String getNewAddress(String label, AddressType addressType);

    /**
     * @see WalletRpcAsync#getRawChangeAddress(AddressType)
     */
    String getRawChangeAddress(AddressType addressType);

    /**
     * @see WalletRpcAsync#getReceivedByAddress(String, Integer, Boolean)
     */
    double getReceivedByAddress(String address, Integer minConf, Boolean includeImmatureCoinbase);

    /**
     * @see WalletRpcAsync#getReceivedByLabel(String, Integer, Boolean)
     */
    double getReceivedByLabel(String label, Integer minConf, Boolean includeImmatureCoinbase);

    /**
     * @see WalletRpcAsync#getTransaction(String, Boolean, Boolean)
     */
    WalletTransaction getTransaction(String txId, Boolean includeWatchOnly, Boolean verbose);

    /**
     * @see WalletRpcAsync#getUnconfirmedBalance()
     */
    @Deprecated
    String getUnconfirmedBalance();

    /**
     * @see WalletRpcAsync#getWalletInfo()
     */
    WalletInfo getWalletInfo();

    /**
     * @see WalletRpcAsync#importAddress(String, String, Boolean, Boolean)
     */
    void importAddress(String address, String label, Boolean rescan, Boolean p2sh);

    /**
     * @see WalletRpcAsync#importDescriptors(List)
     */
    List<ImportMultiResult> importDescriptors(List<Descriptor> descriptors);

    /**
     * @see WalletRpcAsync#importMulti(List, Boolean)
     */
    List<ImportMultiResult> importMulti(List<ImportData> requests, Boolean rescan);

    /**
     * @see WalletRpcAsync#importPrivKey(String, String, Boolean)
     */
    void importPrivKey(String privateKey, String label, Boolean rescan);

    /**
     * @see WalletRpcAsync#importPrunedFunds(String, String)
     */
    void importPrunedFunds(String rawTransaction, String txOutProof);

    /**
     * @see WalletRpcAsync#importPubKey(String, String, Boolean)
     */
    void importPubKey(String pubKey, String label, Boolean rescan);

    /**
     * @see WalletRpcAsync#importWallet(String)
     */
    void importWallet(String filename);

    /**
     * @see WalletRpcAsync#keypoolRefill(Integer)
     */
    void keypoolRefill(Integer newSize);

    /**
     * @see WalletRpcAsync#listAddressGroupings()
     */
    List<List<ListAddressGrouping>> listAddressGroupings();

    /**
     * @see WalletRpcAsync#listDescriptors(Boolean)
     */
    Descriptors listDescriptors(Boolean showPrivate);

    /**
     * @see WalletRpcAsync#listLabels(String)
     */
    List<String> listLabels(String purpose);

    /**
     * @see WalletRpcAsync#listLockUnspent()
     */
    List<UtxoKey> listLockUnspent();

    /**
     * @see WalletRpcAsync#listReceivedByAddress(Integer, Boolean, Boolean, String, Boolean)
     */
    List<BalanceByAddress> listReceivedByAddress(
            Integer minConf,
            Boolean includeEmpty,
            Boolean includeWatchOnly,
            String addressFilter,
            Boolean includeImmatureCoinbase);

    /**
     * @see WalletRpcAsync#listReceivedByLabel(Integer, Boolean, Boolean, Boolean)
     */
    List<TransactionByLabel> listReceivedByLabel(
            Integer minConf,
            Boolean includeEmpty,
            Boolean includeWatchOnly,
            Boolean includeImmatureCoinbase);

    /**
     * @see WalletRpcAsync#listSinceBlock(String, Integer, Boolean, Boolean, Boolean)
     */
    TransactionSinceBlock listSinceBlock(String blockhash,
                                         Integer targetConfirmations,
                                         Boolean includeWatchOnly,
                                         Boolean includeRemoved,
                                         Boolean includeChange);

    /**
     * @see WalletRpcAsync#listTransactions(String, Integer, Integer, Boolean)
     */
    List<WalletTransactionInfo> listTransactions(String label, Integer count, Integer skip, Boolean includeWatchOnly);

    /**
     * @see WalletRpcAsync#listUnspent(Integer, Integer, List, Boolean, ListUnspentOptions)
     */
    List<UnspentInfo> listUnspent(Integer minConf,
                                  Integer maxConf,
                                  List<String> addresses,
                                  Boolean includeUnsafe,
                                  ListUnspentOptions queryOptions);

    /**
     * @see WalletRpcAsync#listWalletDir()
     */
    Wallets listWalletDir();

    /**
     * @see WalletRpcAsync#listWallets()
     */
    List<String> listWallets();

    /**
     * @see WalletRpcAsync#loadWallet(String, Boolean)
     */
    Wallet loadWallet(String filename, Boolean loadOnStartup);

    /**
     * @see WalletRpcAsync#lockUnspent(boolean, List)
     */
    boolean lockUnspent(boolean unlock, List<Transaction> transactions);

    /**
     * @see WalletRpcAsync#migrateWallet()
     */
    MigrateWalletInfo migrateWallet();

    /**
     * @see WalletRpcAsync#newKeypool()
     */
    void newKeypool();

    /**
     * @see WalletRpcAsync#psbtBumpFee(String, BumpFeeOptions)
     */
    PsbtBumpFee psbtBumpFee(String txId, BumpFeeOptions options);

    /**
     * @see WalletRpcAsync#removePrunedFunds(String)
     */
    void removePrunedFunds(String txId);

    /**
     * @see WalletRpcAsync#rescanBlockchain(Integer, Integer)
     */
    ScanInfo rescanBlockchain(Integer startHeight, Integer stopHeight);

    /**
     * @see WalletRpcAsync#restoreWallet(String, String, Boolean)
     */
    Wallet restoreWallet(String walletName, String backupFile, Boolean loadOnStartup);

    /**
     * @see WalletRpcAsync#send(Map, Integer, EstimateMode, String, SendOptions)
     */
    SendInfo send(
            Map<String, String> outputs,
            Integer confTarget,
            EstimateMode estimateMode,
            String feeRate,
            SendOptions sendOptions);

    /**
     * @see WalletRpcAsync#sendAll(List, Map, Integer, EstimateMode, String, SendOptions)
     */
    SendInfo sendAll(
            List<String> recipientsUnspecified,
            Map<String, String> recipientsSpecified,
            Integer confTarget,
            EstimateMode estimateMode,
            String feeRate,
            SendOptions sendOptions);

    /**
     * @see WalletRpcAsync#sendMany(Map, String, List, Boolean, Integer, EstimateMode, String)
     */
    String sendMany(
            Map<String, String> amounts,
            String comment,
            List<String> subtractFeeFrom,
            Boolean replaceable,
            Integer confTarget,
            EstimateMode estimateMode,
            String feeRate);

    /**
     * @see WalletRpcAsync#sendManyVerbose(Map, String, List, Boolean, Integer, EstimateMode, String)
     */
    SendToAddressInfo sendManyVerbose(
            Map<String, String> amounts,
            String comment,
            List<String> subtractFeeFrom,
            Boolean replaceable,
            Integer confTarget,
            EstimateMode estimateMode,
            String feeRate);

    /**
     * @see WalletRpcAsync#sendToAddress(String, String, String, String, Boolean, Boolean, Integer, EstimateMode, Boolean, String)
     */
    String sendToAddress(
            String address,
            String amount,
            String comment,
            String commentTo,
            Boolean subtractFeeFromAmount,
            Boolean replaceable,
            Integer confTarget,
            EstimateMode estimateMode,
            Boolean avoidReuse,
            String feeRate);

    /**
     * @see WalletRpcAsync#sendToAddressVerbose(String, String, String, String, Boolean, Boolean, Integer, EstimateMode, Boolean, String)
     */
    SendToAddressInfo sendToAddressVerbose(
            String address,
            String amount,
            String comment,
            String commentTo,
            Boolean subtractFeeFromAmount,
            Boolean replaceable,
            Integer confTarget,
            EstimateMode estimateMode,
            Boolean avoidReuse,
            String feeRate);

    /**
     * @see WalletRpcAsync#setHdSeed(Boolean, String)
     */
    void setHdSeed(Boolean newKeyPool, String seed);

    /**
     * @see WalletRpcAsync#setLabel(String, String)
     */
    void setLabel(String address, String label);

    /**
     * @see WalletRpcAsync#setTxFee(String)
     */
    boolean setTxFee(String amount);

    /**
     * @see WalletRpcAsync#setWalletFlag(WalletFlag, Boolean)
     */
    WalletFlagInfo setWalletFlag(WalletFlag flag, Boolean value);

    /**
     * @see WalletRpcAsync#signMessage(String, String)
     */
    String signMessage(String address, String message);

    /**
     * @see WalletRpcAsync#signRawTransactionWithWallet(String, List, SigHashType)
     */
    SignTransactionResult signRawTransactionWithWallet(String hexTran, List<PrevTx> prevTxs, SigHashType sigHashType);


    /**
     * @see WalletRpcAsync#simulateRawTransaction(List, Boolean) 
     */
    SimulateRawTransactionInfo simulateRawTransaction(List<String> rawTxs, Boolean includeWatchOnly);

    /**
     * @see WalletRpcAsync#unloadWallet(String, Boolean)
     */
    UnloadWallet unloadWallet(String walletName, Boolean loadOnStartup);

    /**
     * @see WalletRpcAsync#upgradeWallet(Integer)
     */
    UpgradeWallet upgradeWallet(Integer version);

    /**
     * @see WalletRpcAsync#walletCreateFundedPsbt(List, Map, String, Integer, CreateFundedPsbtOptions, Boolean)
     */
    WalletFundedPsbt walletCreateFundedPsbt(
            List<PsbtInput> inputs,
            Map<String, String> outputAddresses,
            String outputData,
            Integer lockTime,
            CreateFundedPsbtOptions options,
            Boolean bip32Derivs);

    /**
     * @see WalletRpcAsync#walletDisplayAddress(String)
     */
    WalletDisplayAddress walletDisplayAddress(String address);

    /**
     * @see WalletRpcAsync#walletLock()
     */
    void walletLock();

    /**
     * @see WalletRpcAsync#walletPassphrase(String, int)
     */
    void walletPassphrase(String passphrase, int timeout);

    /**
     * @see WalletRpcAsync#walletPassphraseChange(String, String)
     */
    void walletPassphraseChange(String oldPassphrase, String newPassphrase);

    /**
     * @see WalletRpcAsync#walletProcessPsbt(String, Boolean, SigHashType, Boolean, Boolean)
     */
    WalletPsbt walletProcessPsbt(String psbt, Boolean sign, SigHashType sigHashType, Boolean bip32Derivs, Boolean finalize);
}