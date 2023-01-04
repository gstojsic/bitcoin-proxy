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
import io.github.gstojsic.bitcoin.proxy.json.model.MultisigAddress;
import io.github.gstojsic.bitcoin.proxy.json.model.PsbtBumpFee;
import io.github.gstojsic.bitcoin.proxy.json.model.ScanInfo;
import io.github.gstojsic.bitcoin.proxy.json.model.SendInfo;
import io.github.gstojsic.bitcoin.proxy.json.model.SendToAddressInfo;
import io.github.gstojsic.bitcoin.proxy.json.model.SignTransactionResult;
import io.github.gstojsic.bitcoin.proxy.json.model.Signers;
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
import java.util.concurrent.CompletableFuture;

public interface WalletRpcAsync {

    /**
     * <p>Calls abandontransaction method on the bitcoin node which marks an in-wallet transaction as abandoned</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.abandontransaction);</pre>
     *
     * @param txId the transaction id
     * @return void
     */
    CompletableFuture<Void> abandonTransaction(String txId);

    /**
     * <p>Calls abortrescan method on the bitcoin node which stops current wallet rescan triggered by an RPC call</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.abortrescan);</pre>
     *
     * @return whether the abort was successful
     */
    CompletableFuture<Boolean> abortRescan();

    /**
     * <p>Calls addmultisigaddress method on the bitcoin node which adds an nrequired-to-sign multisignature address
     * to the wallet</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.addmultisigaddress);</pre>
     *
     * @param nRequired   the number of required signatures out of the n keys or addresses
     * @param keys        a list of bitcoin addresses or hex-encoded public keys
     * @param label       a label to assign the addresses to
     * @param addressType the address type to use
     * @return a multisig address. See {@link MultisigAddress}
     */
    CompletableFuture<MultisigAddress> addMultisigAddress(
            int nRequired,
            List<String> keys,
            String label,
            AddressType addressType);

    /**
     * <p>Calls backupwallet method on the bitcoin node which safely copies current wallet file to destination, which
     * can be a directory or a path with filename</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.backupwallet);</pre>
     *
     * @param destination the destination directory or file
     * @return void
     */
    CompletableFuture<Void> backupWallet(String destination);

    /**
     * <p>Calls bumpfee method on the bitcoin node which bumps the fee of an opt-in-RBF transaction, replacing it with
     * a new transaction</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.bumpfee);</pre>
     *
     * @param txId    the txid to be bumped
     * @param options fee bump options, see {@link BumpFeeOptions}
     * @return an object with bump fee information. See {@link BumpFee}
     */
    CompletableFuture<BumpFee> bumpFee(String txId, BumpFeeOptions options);

    /**
     * <p>Calls createwallet method on the bitcoin node which creates and loads a new wallet</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.createwallet);</pre>
     *
     * @param walletName         the name for the new wallet. If this is a path, the wallet will be created at the path location
     * @param disablePrivateKeys disable the possibility of private keys
     * @param blank              create a blank wallet. A blank wallet has no keys or HD seed
     * @param passphrase         encrypt the wallet with this passphrase
     * @param avoidReuse         keep track of coin reuse, and treat dirty and clean coins differently with privacy
     *                           considerations in mind
     * @param descriptors        create a native descriptor wallet
     * @param loadOnStartup      save wallet name to persistent settings and load on startup
     * @param externalSigner     use an external signer such as a hardware wallet
     * @return an object containing the created wallet info. See {@link Wallet}
     */
    CompletableFuture<Wallet> createWallet(
            String walletName,
            Boolean disablePrivateKeys,
            Boolean blank,
            String passphrase,
            Boolean avoidReuse,
            Boolean descriptors,
            Boolean loadOnStartup,
            Boolean externalSigner);

    /**
     * <p>Calls dumpprivkey method on the bitcoin node which reveals the private key corresponding to the address</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.dumpprivkey);</pre>
     *
     * @param address the bitcoin address for the private key
     * @return The private key
     */
    CompletableFuture<String> dumpPrivKey(String address);

    /**
     * <p>Calls dumpwallet method on the bitcoin node which dumps all wallet keys in a human-readable format to
     * a server-side file</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.dumpwallet);</pre>
     *
     * @param filename the filename with path (absolute path recommended)
     * @return the filename. See {@link DumpFile}
     */
    CompletableFuture<DumpFile> dumpWallet(String filename);

    /**
     * <p>Calls encryptwallet method on the bitcoin node which encrypts the wallet with passphrase</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.encryptwallet);</pre>
     *
     * @param passphrase the pass phrase to encrypt the wallet with
     * @return a string with further instructions
     */
    CompletableFuture<String> encryptWallet(String passphrase);

    /**
     * <p>Calls enumeratesigners method on the bitcoin node which returns a list of external signers</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.enumeratesigners);</pre>
     *
     * @return a list of external signers. See {@link Signers}
     */
    CompletableFuture<Signers> enumerateSigners();

    /**
     * <p>Calls getaddressesbylabel method on the bitcoin node which returns the list of addresses assigned to
     * the specified label</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.getaddressesbylabel);</pre>
     *
     * @param label the label for which to retrieve the addresses
     * @return map of address to {@link AddressByLabel}
     */
    CompletableFuture<Map<String, AddressByLabel>> getAddressesByLabel(String label);

    /**
     * <p>Calls getaddressinfo method on the bitcoin node which returns information about the given bitcoin address</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.getaddressinfo);</pre>
     *
     * @param address the bitcoin address for which to get information
     * @return information about the given bitcoin address. See {@link AddressInfo}
     */
    CompletableFuture<AddressInfo> getAddressInfo(String address);

    /**
     * <p>Calls getbalance method on the bitcoin node which returns the total available balance</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.getbalance);</pre>
     *
     * @param minConf          only include transactions confirmed at least this many times
     * @param includeWatchOnly also include balance in watch-only addresses
     * @param avoidReuse       do not include balance in dirty outputs
     * @return the total available balance
     */
    CompletableFuture<Double> getBalance(Integer minConf, Boolean includeWatchOnly, Boolean avoidReuse);

    /**
     * <p>Calls getbalances method on the bitcoin node which returns an object with all balances in BTC</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.getbalances);</pre>
     *
     * @return an object with all balances in BTC. See {@link BalancesData}
     */
    CompletableFuture<BalancesData> getBalances();

    /**
     * <p>Calls getnewaddress method on the bitcoin node which returns a new Bitcoin address for receiving payments</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.getnewaddress);</pre>
     *
     * @param label       the label name for the address to be linked to
     * @param addressType the address type to use
     * @return a new Bitcoin address for receiving payments
     */
    CompletableFuture<String> getNewAddress(String label, AddressType addressType);

    /**
     * <p>Calls getrawchangeaddress method on the bitcoin node which returns a new Bitcoin address, for receiving change</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.getrawchangeaddress);</pre>
     *
     * @param addressType the address type to use
     * @return a new Bitcoin address, for receiving change
     */
    CompletableFuture<String> getRawChangeAddress(AddressType addressType);

    /**
     * <p>Calls getreceivedbyaddress method on the bitcoin node which returns the total amount received by the given address</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.getreceivedbyaddress);</pre>
     *
     * @param address                 the bitcoin address for transactions
     * @param minConf                 only include transactions confirmed at least this many times
     * @param includeImmatureCoinbase include immature coinbase transactions
     * @return the total amount received by the given address
     */
    CompletableFuture<Double> getReceivedByAddress(String address, Integer minConf, Boolean includeImmatureCoinbase);

    /**
     * <p>Calls getreceivedbylabel method on the bitcoin node which returns the total amount received by addresses with label</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.getreceivedbylabel);</pre>
     *
     * @param label                   the selected label
     * @param minConf                 only include transactions confirmed at least this many times
     * @param includeImmatureCoinbase include immature coinbase transactions
     * @return the total amount received by addresses with label
     */
    CompletableFuture<Double> getReceivedByLabel(String label, Integer minConf, Boolean includeImmatureCoinbase);

    /**
     * <p>Calls gettransaction method on the bitcoin node which returns detailed information about in-wallet transaction
     * specified by txId</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.gettransaction);</pre>
     *
     * @param txId             the transaction id for which info is requested
     * @param includeWatchOnly whether to include watch-only addresses in balance calculation and details
     * @param verbose          whether to include a `decoded` field containing the decoded transaction
     * @return detailed information the transaction. See {@link WalletTransaction}
     */
    CompletableFuture<WalletTransaction> getTransaction(String txId, Boolean includeWatchOnly, Boolean verbose);

    /**
     * @throws UnsupportedOperationException not supported
     * @deprecated it is the same as getbalances().mine.untrusted_pending, use that instead.
     */
    @Deprecated
    CompletableFuture<String> getUnconfirmedBalance();

    /**
     * <p>Calls getwalletinfo method on the bitcoin node which returns an object containing various wallet state info</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.getwalletinfo);</pre>
     *
     * @return wallet state info. See {@link WalletInfo}
     */
    CompletableFuture<WalletInfo> getWalletInfo();

    /**
     * <p>Calls importaddress method on the bitcoin node which adds an address or script (in hex) that can be watched
     * as if it were in your wallet but cannot be used to spend</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.importaddress);</pre>
     *
     * @param address the Bitcoin address (or hex-encoded script)
     * @param label   an optional label
     * @param rescan  rescan the wallet for transactions
     * @param p2sh    add the P2SH version of the script as well
     * @return void
     */
    CompletableFuture<Void> importAddress(String address, String label, Boolean rescan, Boolean p2sh);

    /**
     * <p>Calls importdescriptors method on the bitcoin node which imports descriptors</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.importdescriptors);</pre>
     *
     * @param descriptors list of {@link Descriptor}s to be imported
     * @return list of import results. See {@link ImportMultiResult}
     */
    CompletableFuture<List<ImportMultiResult>> importDescriptors(List<Descriptor> descriptors);

    /**
     * <p>Calls importmulti method on the bitcoin node which imports addresses/scripts, optionally rescanning
     * the blockchain from the earliest creation time of the imported scripts</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.importmulti);</pre>
     *
     * @param requests import requests. see {@link ImportData}
     * @param rescan   Stating if should rescan the blockchain after all imports
     * @return list of import results. See {@link ImportMultiResult}
     */
    CompletableFuture<List<ImportMultiResult>> importMulti(List<ImportData> requests, Boolean rescan);

    /**
     * <p>Calls importprivkey method on the bitcoin node which adds a private key to the wallet</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.importprivkey);</pre>
     *
     * @param privateKey the private key
     * @param label      an optional label
     * @param rescan     rescan the wallet for transactions
     * @return void
     */
    CompletableFuture<Void> importPrivKey(String privateKey, String label, Boolean rescan);

    /**
     * <p>Calls importprunedfunds method on the bitcoin node which imports funds without rescan</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.importprunedfunds);</pre>
     *
     * @param rawTransaction a raw transaction in hex funding an already-existing address in wallet
     * @param txOutProof     the hex output from gettxoutproof that contains the transaction
     * @return void
     */
    CompletableFuture<Void> importPrunedFunds(String rawTransaction, String txOutProof);

    /**
     * <p>Calls importpubkey method on the bitcoin node which adds a public key (in hex) that can be watched as if
     * it were in the wallet but cannot be used to spend</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.importpubkey);</pre>
     *
     * @param pubKey the hex-encoded public key
     * @param label  an optional label
     * @param rescan rescan the wallet for transactions
     * @return void
     */
    CompletableFuture<Void> importPubKey(String pubKey, String label, Boolean rescan);

    /**
     * <p>Calls importwallet method on the bitcoin node which imports keys from a wallet dump file</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.importwallet);</pre>
     *
     * @param filename the wallet dump file
     * @return void
     */
    CompletableFuture<Void> importWallet(String filename);

    /**
     * <p>Calls keypoolrefill method on the bitcoin node which fills the keypool</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.keypoolrefill);</pre>
     *
     * @param newSize the new keypool size
     * @return void
     */
    CompletableFuture<Void> keypoolRefill(Integer newSize);

    /**
     * <p>Calls listaddressgroupings method on the bitcoin node which lists groups of addresses which have had their
     * common ownership made public by common use as inputs or as the resulting change in past transactions</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.listaddressgroupings);</pre>
     *
     * @return groups of addresses. See {@link ListAddressGrouping}
     */
    CompletableFuture<List<List<ListAddressGrouping>>> listAddressGroupings();

    /**
     * <p>Calls listdescriptors method on the bitcoin node which returns a list of descriptors imported into
     * a descriptor-enabled wallet</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.listdescriptors);</pre>
     *
     * @param showPrivate show private descriptors.
     * @return a list of descriptors. See {@link Descriptors}
     */
    CompletableFuture<Descriptors> listDescriptors(Boolean showPrivate);

    /**
     * <p>Calls listlabels method on the bitcoin node which returns the list of all labels, or labels that are assigned
     * to addresses with a specific purpose</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.listlabels);</pre>
     *
     * @param purpose address purpose to list labels for
     * @return a list of labels.
     */
    CompletableFuture<List<String>> listLabels(String purpose);

    /**
     * <p>Calls listlockunspent method on the bitcoin node which returns a list of temporarily unspendable outputs</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.listlockunspent);</pre>
     *
     * @return a list of temporarily unspendable outputs. See {@link UtxoKey}
     */
    CompletableFuture<List<UtxoKey>> listLockUnspent();

    /**
     * <p>Calls listreceivedbyaddress method on the bitcoin node which returns a list of balances by receiving address</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.listreceivedbyaddress);</pre>
     *
     * @param minConf                 the minimum number of confirmations before payments are included
     * @param includeEmpty            whether to include addresses that haven't received any payments
     * @param includeWatchOnly        whether to include watch-only addresses
     * @param addressFilter           if present and non-empty, only return information on this address
     * @param includeImmatureCoinbase include immature coinbase transactions
     * @return a list of balances by receiving address. See {@link BalanceByAddress}
     */
    CompletableFuture<List<BalanceByAddress>> listReceivedByAddress(
            Integer minConf,
            Boolean includeEmpty,
            Boolean includeWatchOnly,
            String addressFilter,
            Boolean includeImmatureCoinbase);

    /**
     * <p>Calls listreceivedbylabel method on the bitcoin node which returns a list of received transactions by label</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.listreceivedbylabel);</pre>
     *
     * @param minConf                 the minimum number of confirmations before payments are included
     * @param includeEmpty            whether to include labels that haven't received any payments
     * @param includeWatchOnly        whether to include watch-only addresses
     * @param includeImmatureCoinbase include immature coinbase transactions
     * @return a list of received transactions by label. See {@link TransactionByLabel}
     */
    CompletableFuture<List<TransactionByLabel>> listReceivedByLabel(
            Integer minConf,
            Boolean includeEmpty,
            Boolean includeWatchOnly,
            Boolean includeImmatureCoinbase);

    /**
     * <p>Calls listsinceblock method on the bitcoin node which returns all transactions in blocks since block specified
     * by blockhash, or all transactions if omitted</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.listsinceblock);</pre>
     *
     * @param blockhash           if set, the block hash to list transactions since, otherwise list all transactions
     * @param targetConfirmations return the nth block hash from the main chain
     * @param includeWatchOnly    include transactions to watch-only addresses
     * @param includeRemoved      show transactions that were removed due to a reorg in the "removed" array
     * @return info on transactions since block. See {@link TransactionSinceBlock}
     */
    CompletableFuture<TransactionSinceBlock> listSinceBlock(
            String blockhash,
            Integer targetConfirmations,
            Boolean includeWatchOnly,
            Boolean includeRemoved);

    /**
     * <p>Calls listtransactions method on the bitcoin node which returns a list of transactions filtered by invocation
     * parameters</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.listtransactions);</pre>
     *
     * @param label            if set, should be a valid label name to return only incoming transactions, "*" to disable
     *                         filtering and return all transactions
     * @param count            the max number of transactions to return
     * @param skip             the number of transactions to skip
     * @param includeWatchOnly include transactions to watch-only addresses
     * @return a list of wallet transactions. See {@link WalletTransactionInfo}
     */
    CompletableFuture<List<WalletTransactionInfo>> listTransactions(String label, Integer count, Integer skip, Boolean includeWatchOnly);

    /**
     * <p>Calls listunspent method on the bitcoin node which returns a list of unspent transaction outputs</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.listunspent);</pre>
     *
     * @param minConf       the minimum confirmations to filter
     * @param maxConf       the maximum confirmations to filter
     * @param addresses     the bitcoin addresses to filter
     * @param includeUnsafe include outputs that are not safe to spend
     * @param queryOptions  query options. see {@link ListUnspentOptions}
     * @return a list of unspent transaction outputs. See {@link UnspentInfo}
     */
    CompletableFuture<List<UnspentInfo>> listUnspent(
            Integer minConf,
            Integer maxConf,
            List<String> addresses,
            Boolean includeUnsafe,
            ListUnspentOptions queryOptions);

    /**
     * <p>Calls listwalletdir method on the bitcoin node which returns a list of wallets in the wallet directory</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.listwalletdir);</pre>
     *
     * @return a list of wallets. See {@link Wallets}
     */
    CompletableFuture<Wallets> listWalletDir();

    /**
     * <p>Calls listwallets method on the bitcoin node which returns a list of currently loaded wallets</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.listwallets);</pre>
     *
     * @return a list of currently loaded wallets
     */
    CompletableFuture<List<String>> listWallets();

    /**
     * <p>Calls loadwallet method on the bitcoin node which loads a wallet from a wallet file or directory</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.loadwallet);</pre>
     *
     * @param filename      the wallet directory or .dat file
     * @param loadOnStartup save wallet name to persistent settings and load on startup. True to add wallet to startup
     *                      list, false to remove, null to leave unchanged.
     * @return info on loaded wallet. See {@link Wallet}
     */
    CompletableFuture<Wallet> loadWallet(String filename, Boolean loadOnStartup);

    /**
     * <p>Calls lockunspent method on the bitcoin node which updates the list of temporarily unspendable outputs</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.lockunspent);</pre>
     *
     * @param unlock       whether to unlock (true) or lock (false) the specified transactions
     * @param transactions the transaction outputs. see {@link Transaction}
     * @return Whether the command was successful or not
     */
    CompletableFuture<Boolean> lockUnspent(boolean unlock, List<Transaction> transactions);

    /**
     * <p>Calls newkeypool method on the bitcoin node which entirely clears and refills the keypool</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.newkeypool);</pre>
     *
     * @return void
     */
    CompletableFuture<Void> newKeypool();

    /**
     * <p>Calls psbtbumpfee method on the bitcoin node which bumps the fee of an opt-in-RBF transaction, replacing it
     * with a new transaction.</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.psbtbumpfee);</pre>
     *
     * @param txId    the txid to be bumped
     * @param options bump fee options. see {@link BumpFeeOptions}
     * @return info on the bumped fee. See {@link PsbtBumpFee}
     */
    CompletableFuture<PsbtBumpFee> psbtBumpFee(String txId, BumpFeeOptions options);

    /**
     * <p>Calls removeprunedfunds method on the bitcoin node which deletes the specified transaction from the wallet</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.removeprunedfunds);</pre>
     *
     * @param txId The hex-encoded id of the transaction being deleted
     * @return void
     */
    CompletableFuture<Void> removePrunedFunds(String txId);

    /**
     * <p>Calls rescanblockchain method on the bitcoin node which rescans the local blockchain for wallet related transactions</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.rescanblockchain);</pre>
     *
     * @param startHeight block height where the rescan should start
     * @param stopHeight  the last block height that should be scanned
     * @return scan results. See {@link ScanInfo}
     */
    CompletableFuture<ScanInfo> rescanBlockchain(Integer startHeight, Integer stopHeight);

    /**
     * <p>Calls restorewallet method on the bitcoin node which restores and loads a wallet from backup</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.restorewallet);</pre>
     *
     * @param walletName    the name that will be applied to the restored wallet
     * @param backupFile    the backup file that will be used to restore the wallet
     * @param loadOnStartup save wallet name to persistent settings and load on startup
     * @return info on restored wallet. See {@link Wallet}
     */
    CompletableFuture<Wallet> restoreWallet(String walletName, String backupFile, Boolean loadOnStartup);

    /**
     * <p>Calls send method on the bitcoin node which sends a transaction</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.send);</pre>
     *
     * @param outputs      address -> amount pairs
     * @param confTarget   confirmation target in blocks
     * @param estimateMode the fee estimate mode
     * @param feeRate      a fee rate in sat/vB
     * @param sendOptions  options. see {@link SendOptions}
     * @return status of send request. See {@link SendInfo}
     */
    CompletableFuture<SendInfo> send(
            Map<String, String> outputs,
            Integer confTarget,
            EstimateMode estimateMode,
            String feeRate,
            SendOptions sendOptions);

    /**
     * <p>Calls sendmany method on the bitcoin node which sends multiple times</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.sendmany);</pre>
     *
     * @param amounts         address -> amount pairs
     * @param comment         a comment, if any
     * @param subtractFeeFrom optional list of addresses to subtract the fees from.
     * @param replaceable     allow this transaction to be replaced by a transaction with higher fees via BIP 125
     * @param confTarget      confirmation target in blocks
     * @param estimateMode    the fee estimate mode
     * @param feeRate         specify a fee rate in sat/vB
     * @return The transaction id for the send
     */
    CompletableFuture<String> sendMany(
            Map<String, String> amounts,
            String comment,
            List<String> subtractFeeFrom,
            Boolean replaceable,
            Integer confTarget,
            EstimateMode estimateMode,
            String feeRate);

    /**
     * <p>Calls sendmany method on the bitcoin node which sends multiple times</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.sendmany);</pre>
     *
     * @param amounts         address -> amount pairs
     * @param comment         a comment, if any
     * @param subtractFeeFrom optional list of addresses to subtract the fees from.
     * @param replaceable     allow this transaction to be replaced by a transaction with higher fees via BIP 125
     * @param confTarget      confirmation target in blocks
     * @param estimateMode    the fee estimate mode
     * @param feeRate         specify a fee rate in sat/vB
     * @return send result. See {@link SendToAddressInfo}
     */
    CompletableFuture<SendToAddressInfo> sendManyVerbose(
            Map<String, String> amounts,
            String comment,
            List<String> subtractFeeFrom,
            Boolean replaceable,
            Integer confTarget,
            EstimateMode estimateMode,
            String feeRate);

    /**
     * <p>Calls sendtoaddress method on the bitcoin node which sends an amount to a given address</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.sendtoaddress);</pre>
     *
     * @param address               the bitcoin address to send to
     * @param amount                the amount in BTC to send
     * @param comment               a comment used to store what the transaction is for
     * @param commentTo             a comment to store the name of the person or organization to which the coin is
     *                              being sent
     * @param subtractFeeFromAmount the fee will be deducted from the amount being sent
     * @param replaceable           allow this transaction to be replaced by a transaction with higher fees via BIP 125
     * @param confTarget            confirmation target in blocks
     * @param estimateMode          the fee estimate mode
     * @param avoidReuse            avoid spending from dirty addresses
     * @param feeRate               specify a fee rate in sat/vB
     * @return The transaction id
     */
    CompletableFuture<String> sendToAddress(
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
     * <p>Calls sendtoaddress method on the bitcoin node which sends an amount to a given address</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.sendtoaddress);</pre>
     *
     * @param address               the bitcoin address to send to
     * @param amount                the amount in BTC to send
     * @param comment               a comment used to store what the transaction is for
     * @param commentTo             a comment to store the name of the person or organization to which the coin is
     *                              being sent
     * @param subtractFeeFromAmount the fee will be deducted from the amount being sent
     * @param replaceable           allow this transaction to be replaced by a transaction with higher fees via BIP 125
     * @param confTarget            confirmation target in blocks
     * @param estimateMode          the fee estimate mode
     * @param avoidReuse            avoid spending from dirty addresses
     * @param feeRate               specify a fee rate in sat/vB
     * @return send result. See {@link SendToAddressInfo}
     */
    CompletableFuture<SendToAddressInfo> sendToAddressVerbose(
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
     * <p>Calls sethdseed method on the bitcoin node which sets or generates a new HD wallet seed.</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.sethdseed);</pre>
     *
     * @param newKeyPool whether to flush old unused addresses, including change addresses, from the keypool and regenerate it
     * @param seed       the WIF private key to use as the new HD seed
     * @return void
     */
    CompletableFuture<Void> setHdSeed(Boolean newKeyPool, String seed);

    /**
     * <p>Calls setlabel method on the bitcoin node which sets the label associated with the given address.</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.setlabel);</pre>
     *
     * @param address the bitcoin address to be associated with a label
     * @param label   the label to assign to the address
     * @return void
     */
    CompletableFuture<Void> setLabel(String address, String label);

    /**
     * <p>Calls settxfee method on the bitcoin node which sets the transaction fee rate in BTC/kvB for this wallet</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.settxfee);</pre>
     *
     * @param amount the transaction fee rate in BTC/kvB
     * @return true if successful
     */
    CompletableFuture<Boolean> setTxFee(String amount);

    /**
     * <p>Calls setwalletflag method on the bitcoin node which changes the state of the given wallet flag for a wallet</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.setwalletflag);</pre>
     *
     * @param flag  the name of the flag to change
     * @param value the new state
     * @return info for the given flag. See {@link WalletFlagInfo}
     */
    CompletableFuture<WalletFlagInfo> setWalletFlag(WalletFlag flag, Boolean value);

    /**
     * <p>Calls signmessage method on the bitcoin node which signs a message with the private key of an address</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.signmessage);</pre>
     *
     * @param address the bitcoin address to use for the private key
     * @param message the message to create a signature of
     * @return The signature of the message encoded in base 64
     */
    CompletableFuture<String> signMessage(String address, String message);

    /**
     * <p>Calls signrawtransactionwithwallet method on the bitcoin node which signs the inputs for raw transaction
     * (serialized, hex-encoded)</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.signrawtransactionwithwallet);</pre>
     *
     * @param hexTran     the transaction hex string
     * @param prevTxs     the previous dependent transaction outputs. see {@link PrevTx}
     * @param sigHashType the signature hash type
     * @return signing result. See {@link SignTransactionResult}
     */
    CompletableFuture<SignTransactionResult> signRawTransactionWithWallet(
            String hexTran,
            List<PrevTx> prevTxs,
            SigHashType sigHashType);

    /**
     * <p>Calls unloadwallet method on the bitcoin node which unloads the wallet referenced by the request endpoint
     * otherwise unloads the wallet specified in the argument</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.unloadwallet);</pre>
     *
     * @param walletName    the name of the wallet to unload
     * @param loadOnStartup save wallet name to persistent settings and load on startup
     * @return unload result. See {@link UnloadWallet}
     */
    CompletableFuture<UnloadWallet> unloadWallet(String walletName, Boolean loadOnStartup);

    /**
     * <p>Calls upgradewallet method on the bitcoin node which upgrade the wallet</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.upgradewallet);</pre>
     *
     * @param version The version number to upgrade to. Default is the latest wallet version
     * @return upgrade results. See {@link UpgradeWallet}
     */
    CompletableFuture<UpgradeWallet> upgradeWallet(Integer version);

    /**
     * <p>Calls walletcreatefundedpsbt method on the bitcoin node which creates and funds a transaction in
     * the Partially Signed Transaction format</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.walletcreatefundedpsbt);</pre>
     *
     * @param inputs          Leave empty to add inputs automatically. See {@link PsbtInput}
     * @param outputAddresses The outputs (address -> amount pairs), where none of the addresses are duplicated.
     * @param outputData      hex-encoded data, check node docs
     * @param lockTime        Raw locktime.
     * @param options         options. see {@link CreateFundedPsbtOptions}
     * @param bip32Derivs     include BIP 32 derivation paths for public keys if we know them
     * @return psbt creation result. See {@link WalletFundedPsbt}
     */
    CompletableFuture<WalletFundedPsbt> walletCreateFundedPsbt(
            List<PsbtInput> inputs,
            Map<String, String> outputAddresses,
            String outputData,
            Integer lockTime,
            CreateFundedPsbtOptions options,
            Boolean bip32Derivs);

    /**
     * <p>Calls walletdisplayaddress method on the bitcoin node which displays an address on an external signer
     * for verification</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.walletdisplayaddress);</pre>
     *
     * @param address bitcoin address to display
     * @return display result. See {@link WalletDisplayAddress}
     */
    CompletableFuture<WalletDisplayAddress> walletDisplayAddress(String address);

    /**
     * <p>Calls walletlock method on the bitcoin node which removes the wallet encryption key from memory,
     * locking the wallet</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.walletlock);</pre>
     *
     * @return void
     */
    CompletableFuture<Void> walletLock();

    /**
     * <p>Calls walletpassphrase method on the bitcoin node which stores the wallet decryption key in memory
     * for timeout seconds</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.walletpassphrase);</pre>
     *
     * @param passphrase the wallet passphrase
     * @param timeout    the time to keep the decryption key in seconds; capped at 100000000 (~3 years)
     * @return void
     */
    CompletableFuture<Void> walletPassphrase(String passphrase, int timeout);

    /**
     * <p>Calls walletpassphrasechange method on the bitcoin node which changes the wallet passphrase</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.walletpassphrasechange);</pre>
     *
     * @param oldPassphrase the current passphrase
     * @param newPassphrase the new passphrase
     * @return void
     */
    CompletableFuture<Void> walletPassphraseChange(String oldPassphrase, String newPassphrase);

    /**
     * <p>Calls walletprocesspsbt method on the bitcoin node which updates a PSBT with input information from the wallet
     * and then sign inputs that can be signed</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.walletprocesspsbt);</pre>
     *
     * @param psbt        the transaction base64 string
     * @param sign        also sign the transaction when updating
     * @param sigHashType the signature hash type to sign with if not specified by the PSBT
     * @param bip32Derivs include BIP 32 derivation paths for public keys if they are known
     * @param finalize    finalize inputs if possible
     * @return psbt processing result. See {@link WalletPsbt}
     */
    CompletableFuture<WalletPsbt> walletProcessPsbt(
            String psbt,
            Boolean sign,
            SigHashType sigHashType,
            Boolean bip32Derivs,
            Boolean finalize);
}
