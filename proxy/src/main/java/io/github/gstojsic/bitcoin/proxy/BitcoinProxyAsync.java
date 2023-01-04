package io.github.gstojsic.bitcoin.proxy;

import io.github.gstojsic.bitcoin.proxy.exception.RpcException;
import io.github.gstojsic.bitcoin.proxy.json.Parsers;
import io.github.gstojsic.bitcoin.proxy.json.ParsersBuilder;
import io.github.gstojsic.bitcoin.proxy.json.RpcResponse;
import io.github.gstojsic.bitcoin.proxy.json.model.AddPeerAddress;
import io.github.gstojsic.bitcoin.proxy.json.model.AddedNodeInfo;
import io.github.gstojsic.bitcoin.proxy.json.model.AddressByLabel;
import io.github.gstojsic.bitcoin.proxy.json.model.AddressInfo;
import io.github.gstojsic.bitcoin.proxy.json.model.AddressValidation;
import io.github.gstojsic.bitcoin.proxy.json.model.BalanceByAddress;
import io.github.gstojsic.bitcoin.proxy.json.model.BalancesData;
import io.github.gstojsic.bitcoin.proxy.json.model.BannedInfo;
import io.github.gstojsic.bitcoin.proxy.json.model.Block1;
import io.github.gstojsic.bitcoin.proxy.json.model.Block2;
import io.github.gstojsic.bitcoin.proxy.json.model.BlockFilter;
import io.github.gstojsic.bitcoin.proxy.json.model.BlockHeader;
import io.github.gstojsic.bitcoin.proxy.json.model.BlockStats;
import io.github.gstojsic.bitcoin.proxy.json.model.BlockTemplate;
import io.github.gstojsic.bitcoin.proxy.json.model.BlockchainInfo;
import io.github.gstojsic.bitcoin.proxy.json.model.BumpFee;
import io.github.gstojsic.bitcoin.proxy.json.model.ChainTipData;
import io.github.gstojsic.bitcoin.proxy.json.model.ChainTxStats;
import io.github.gstojsic.bitcoin.proxy.json.model.DecodedPsbt;
import io.github.gstojsic.bitcoin.proxy.json.model.DecodedScript;
import io.github.gstojsic.bitcoin.proxy.json.model.DeploymentInfo;
import io.github.gstojsic.bitcoin.proxy.json.model.DescriptorInfo;
import io.github.gstojsic.bitcoin.proxy.json.model.Descriptors;
import io.github.gstojsic.bitcoin.proxy.json.model.DumpFile;
import io.github.gstojsic.bitcoin.proxy.json.model.Empty;
import io.github.gstojsic.bitcoin.proxy.json.model.FeeEstimate;
import io.github.gstojsic.bitcoin.proxy.json.model.FinalizedPsbt;
import io.github.gstojsic.bitcoin.proxy.json.model.GeneratedBlock;
import io.github.gstojsic.bitcoin.proxy.json.model.ImportMultiResult;
import io.github.gstojsic.bitcoin.proxy.json.model.IndexInfo;
import io.github.gstojsic.bitcoin.proxy.json.model.ListAddressGrouping;
import io.github.gstojsic.bitcoin.proxy.json.model.MemoryInfo;
import io.github.gstojsic.bitcoin.proxy.json.model.MempoolAccept;
import io.github.gstojsic.bitcoin.proxy.json.model.MempoolData;
import io.github.gstojsic.bitcoin.proxy.json.model.MempoolInfo;
import io.github.gstojsic.bitcoin.proxy.json.model.MempoolWithSeq;
import io.github.gstojsic.bitcoin.proxy.json.model.MiningInfo;
import io.github.gstojsic.bitcoin.proxy.json.model.MultisigAddress;
import io.github.gstojsic.bitcoin.proxy.json.model.NetTotals;
import io.github.gstojsic.bitcoin.proxy.json.model.NetworkInfo;
import io.github.gstojsic.bitcoin.proxy.json.model.NodeAddress;
import io.github.gstojsic.bitcoin.proxy.json.model.PeerInfo;
import io.github.gstojsic.bitcoin.proxy.json.model.PsbtAnalysis;
import io.github.gstojsic.bitcoin.proxy.json.model.PsbtBumpFee;
import io.github.gstojsic.bitcoin.proxy.json.model.RawTransaction;
import io.github.gstojsic.bitcoin.proxy.json.model.RpcInfo;
import io.github.gstojsic.bitcoin.proxy.json.model.ScanInfo;
import io.github.gstojsic.bitcoin.proxy.json.model.ScanTxOutResult;
import io.github.gstojsic.bitcoin.proxy.json.model.SendInfo;
import io.github.gstojsic.bitcoin.proxy.json.model.SendToAddressInfo;
import io.github.gstojsic.bitcoin.proxy.json.model.SignTransactionResult;
import io.github.gstojsic.bitcoin.proxy.json.model.Signers;
import io.github.gstojsic.bitcoin.proxy.json.model.TransactionByLabel;
import io.github.gstojsic.bitcoin.proxy.json.model.TransactionFunding;
import io.github.gstojsic.bitcoin.proxy.json.model.TransactionOutput;
import io.github.gstojsic.bitcoin.proxy.json.model.TransactionOutputSetInfo;
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
import io.github.gstojsic.bitcoin.proxy.json.model.ZmqNotificationInfo;
import io.github.gstojsic.bitcoin.proxy.model.AddNetworkCommand;
import io.github.gstojsic.bitcoin.proxy.model.AddressType;
import io.github.gstojsic.bitcoin.proxy.model.BlockStatOptions;
import io.github.gstojsic.bitcoin.proxy.model.BumpFeeOptions;
import io.github.gstojsic.bitcoin.proxy.model.Command;
import io.github.gstojsic.bitcoin.proxy.model.CreateFundedPsbtOptions;
import io.github.gstojsic.bitcoin.proxy.model.Descriptor;
import io.github.gstojsic.bitcoin.proxy.model.EstimateMode;
import io.github.gstojsic.bitcoin.proxy.model.FundTransactionOptions;
import io.github.gstojsic.bitcoin.proxy.model.HashType;
import io.github.gstojsic.bitcoin.proxy.model.ImportData;
import io.github.gstojsic.bitcoin.proxy.model.ImportType;
import io.github.gstojsic.bitcoin.proxy.model.InputWeight;
import io.github.gstojsic.bitcoin.proxy.model.ListUnspentOptions;
import io.github.gstojsic.bitcoin.proxy.model.Logging;
import io.github.gstojsic.bitcoin.proxy.model.MiningTemplate;
import io.github.gstojsic.bitcoin.proxy.model.NetworkType;
import io.github.gstojsic.bitcoin.proxy.model.PrevTx;
import io.github.gstojsic.bitcoin.proxy.model.PsbtDescriptor;
import io.github.gstojsic.bitcoin.proxy.model.PsbtInput;
import io.github.gstojsic.bitcoin.proxy.model.ScanTxAction;
import io.github.gstojsic.bitcoin.proxy.model.SendInput;
import io.github.gstojsic.bitcoin.proxy.model.SendOptions;
import io.github.gstojsic.bitcoin.proxy.model.SetBanCommand;
import io.github.gstojsic.bitcoin.proxy.model.SigHashType;
import io.github.gstojsic.bitcoin.proxy.model.SolveData;
import io.github.gstojsic.bitcoin.proxy.model.Transaction;
import io.github.gstojsic.bitcoin.proxy.model.WalletFlag;
import io.github.gstojsic.bitcoin.proxy.util.Pair;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

import static java.lang.String.join;
import static java.util.Objects.requireNonNull;
import static java.util.Objects.requireNonNullElse;
import static java.util.stream.Collectors.toList;

/**
 * <p>Async implementation of the bitcoin proxy (RPC client). Results are returned as CompletableFutures. Synchronous
 * version of the proxy is implemented as {@link BitcoinProxy}</p>
 * <br/>
 * Sample usage:
 * <br/>
 * <pre>
 *  var proxy = new BitcoinProxyAsync(
 *      "localhost",
 *      8443,
 *      "user",
 *      "secret",
 *      null);
 *  var uptime = proxy.uptime().get();
 * </pre>
 */
public class BitcoinProxyAsync implements BtcRpcAsync {

    private final HttpClient client;

    private final HttpRequest.Builder builder;

    private final Parsers parsers = ParsersBuilder.get();

    private final HttpRequest getBestBlockHashRequest;
    private final HttpRequest getBlockchainInfoRequest;
    private final HttpRequest getBlockCountRequest;
    private final HttpRequest getChainTipsRequest;
    private final HttpRequest getDifficultyRequest;
    private final HttpRequest getMempoolInfoRequest;
    private final HttpRequest saveMempoolRequest;
    private final HttpRequest getRpcInfoRequest;
    private final HttpRequest stopRequest;
    private final HttpRequest uptimeRequest;
    private final HttpRequest getMiningInfoRequest;
    private final HttpRequest clearBannedRequest;
    private final HttpRequest getConnectionCountRequest;
    private final HttpRequest getNetTotalsRequest;
    private final HttpRequest getNetworkInfoRequest;
    private final HttpRequest getPeerInfoRequest;
    private final HttpRequest listBannedRequest;
    private final HttpRequest pingRequest;
    private final HttpRequest abortRescanRequest;
    private final HttpRequest getBalancesRequest;
    private final HttpRequest getWalletInfoRequest;
    private final HttpRequest listAddressGroupingsRequest;
    private final HttpRequest listDescriptorsRequest;
    private final HttpRequest listLockUnspentRequest;
    private final HttpRequest listWalletDirRequest;
    private final HttpRequest listWalletsRequest;
    private final HttpRequest walletLockRequest;
    private final HttpRequest getZmqNotificationsRequest;
    private final HttpRequest newKeypoolRequest;
    private final HttpRequest enumerateSignersRequest;
    private final HttpRequest getMemoryInfoRequest;
    private final HttpRequest getMemoryInfoMallocRequest;

    public BitcoinProxyAsync(String host, int port, String user, String password) {
        this(host, port, user, password, null);
    }

    public BitcoinProxyAsync(String host, int port, String user, String password, String wallet) {
        URI uri;
        try {
            uri = wallet == null
                    ? new URI("http://%s:%d".formatted(host, port))
                    : new URI("http://%s:%d/wallet/%s".formatted(host, port, wallet));
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("invalid uri %s, %d".formatted(host, port), e);
        }

        client = HttpClient.newBuilder()
                .authenticator(new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(
                                user, password.toCharArray()
                        );
                    }
                })
                .build();

        builder = HttpRequest.newBuilder(uri)
                .setHeader("content-type", "text/plain");

        getBestBlockHashRequest = getConstRequest(Command.getbestblockhash);
        getBlockchainInfoRequest = getConstRequest(Command.getblockchaininfo);
        getBlockCountRequest = getConstRequest(Command.getblockcount);
        getChainTipsRequest = getConstRequest(Command.getchaintips);
        getDifficultyRequest = getConstRequest(Command.getdifficulty);
        getMempoolInfoRequest = getConstRequest(Command.getmempoolinfo);
        saveMempoolRequest = getConstRequest(Command.savemempool);
        getRpcInfoRequest = getConstRequest(Command.getrpcinfo);
        stopRequest = getConstRequest(Command.stop);
        uptimeRequest = getConstRequest(Command.uptime);
        getMiningInfoRequest = getConstRequest(Command.getmininginfo);
        clearBannedRequest = getConstRequest(Command.clearbanned);
        getConnectionCountRequest = getConstRequest(Command.getconnectioncount);
        getNetTotalsRequest = getConstRequest(Command.getnettotals);
        getNetworkInfoRequest = getConstRequest(Command.getnetworkinfo);
        getPeerInfoRequest = getConstRequest(Command.getpeerinfo);
        listBannedRequest = getConstRequest(Command.listbanned);
        pingRequest = getConstRequest(Command.ping);
        abortRescanRequest = getConstRequest(Command.abortrescan);
        getBalancesRequest = getConstRequest(Command.getbalances);
        getWalletInfoRequest = getConstRequest(Command.getwalletinfo);
        listAddressGroupingsRequest = getConstRequest(Command.listaddressgroupings);
        listDescriptorsRequest = getConstRequest(Command.listdescriptors);
        listLockUnspentRequest = getConstRequest(Command.listlockunspent);
        listWalletDirRequest = getConstRequest(Command.listwalletdir);
        listWalletsRequest = getConstRequest(Command.listwallets);
        walletLockRequest = getConstRequest(Command.walletlock);
        getZmqNotificationsRequest = getConstRequest(Command.getzmqnotifications);
        newKeypoolRequest = getConstRequest(Command.newkeypool);
        enumerateSignersRequest = getConstRequest(Command.enumeratesigners);
        getMemoryInfoRequest = getConstRequest(Command.getmemoryinfo);
        getMemoryInfoMallocRequest = getRequest(Command.getmemoryinfo, "\"mallocinfo\"");
    }

    //blockchain rpc
    @Override
    public CompletableFuture<String> getBestBlockhash() {
        return send(getBestBlockHashRequest, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrString)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<String> getBlock(String blockhash) {
        final String params = """
                "%s",0\
                """.formatted(blockhash);
        final HttpRequest request = getRequest(Command.getblock, params);
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrString)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<Block1> getBlockVerbose1(String blockhash) {
        final HttpRequest request = getRequest(Command.getblock, jsonStr(blockhash));
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrBlock1)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<Block2> getBlockVerbose2(String blockhash) {
        final String params = """
                "%s",2\
                """.formatted(blockhash);
        final HttpRequest request = getRequest(Command.getblock, params);
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrBlock2)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<Block2> getBlockVerbose3(String blockhash) {
        final String params = """
                "%s",3\
                """.formatted(blockhash);
        final HttpRequest request = getRequest(Command.getblock, params);
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrBlock2)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<BlockchainInfo> getBlockchainInfo() {
        return send(getBlockchainInfoRequest, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrBlockchainInfo)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<Integer> getBlockCount() {
        return send(getBlockCountRequest, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrInteger)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<BlockFilter> getBlockFilter(String blockhash, String filterType) {
        filterType = requireNonNullElse(filterType, "basic");
        final String params = """
                "%s","%s"\
                """.formatted(blockhash, filterType);
        final HttpRequest request = getRequest(Command.getblockfilter, params);
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrBlockFilter)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<String> getBlockhash(int height) {
        final String params = "%d".formatted(height);
        final HttpRequest request = getRequest(Command.getblockhash, params);
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrString)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<String> getBlockHeader(String blockhash) {
        final String params = """
                "%s",false\
                """.formatted(blockhash);
        final HttpRequest request = getRequest(Command.getblockheader, params);
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrString)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<BlockHeader> getBlockHeaderVerbose(String blockhash) {
        final HttpRequest request = getRequest(Command.getblockheader, jsonStr(blockhash));
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrBlockHeader)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<BlockStats> getBlockStats(String hash, Set<BlockStatOptions> options) {
        final String stats = getStats(options);
        final String params = """
                "%s"%s\
                """.formatted(hash, stats);
        final HttpRequest request = getRequest(Command.getblockstats, params);
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrBlockStats)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<BlockStats> getBlockStats(int height, Set<BlockStatOptions> options) {
        final String stats = getStats(options);
        final String params = """
                %d%s\
                """.formatted(height, stats);
        final HttpRequest request = getRequest(Command.getblockstats, params);
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrBlockStats)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<List<ChainTipData>> getChainTips() {
        return send(getChainTipsRequest, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrChainTipList)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<ChainTxStats> getChainTxStats(Integer nBlocks, String blockhash) {
        final String params = nBlocks != null
                ? blockhash != null ? nBlocks + ",\"" + blockhash + "\"" : Integer.toString(nBlocks)
                : blockhash != null ? blockhash : "";

        final HttpRequest request = getRequest(Command.getchaintxstats, params);
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrChainTxStats)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<Double> getDifficulty() {
        return send(getDifficultyRequest, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrDouble)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<List<String>> getMempoolAncestors(String txId) {
        final HttpRequest request = getRequest(Command.getmempoolancestors, jsonStr(txId));
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrStringList)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<Map<String, MempoolData>> getMempoolAncestorsVerbose(String txId) {
        final HttpRequest request = getRequest(Command.getmempoolancestors, """
                "%s",true\
                """.formatted(txId));
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrMapMempoolData)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<List<String>> getMempoolDescendants(String txId) {
        final HttpRequest request = getRequest(Command.getmempooldescendants, jsonStr(txId));
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrStringList)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<Map<String, MempoolData>> getMempoolDescendantsVerbose(String txId) {
        final HttpRequest request = getRequest(Command.getmempooldescendants, """
                "%s",true\
                """.formatted(txId));

        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrMapMempoolData)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<MempoolData> getMempoolEntry(String txId) {
        final HttpRequest request = getRequest(Command.getmempoolentry, jsonStr(txId));

        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrMempoolData)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<MempoolInfo> getMempoolInfo() {
        return send(getMempoolInfoRequest, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrMempoolInfo)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<List<String>> getRawMempool() {
        final HttpRequest request = getRequest(Command.getrawmempool, "");
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrStringList)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<Map<String, MempoolData>> getRawMempoolVerbose() {
        final HttpRequest request = getRequest(Command.getrawmempool, "true");
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrMapMempoolData)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<MempoolWithSeq> getRawMempoolWithSequence() {
        final HttpRequest request = getRequest(Command.getrawmempool, "false,true");
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrMempoolWithSeq)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<TransactionOutput> getTxOut(String txId, int n, Boolean includeMempool) {
        includeMempool = includeMempool == null || includeMempool; // set default

        final String params = """
                "%s",%d,%b\
                """.formatted(txId, n, includeMempool);

        final HttpRequest request = getRequest(Command.gettxout, params);
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrTransactionOutput)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<String> getTxOutProof(List<String> txIds, String blockhash) {
        if (txIds == null || txIds.isEmpty()) throw new IllegalArgumentException("txIds is null or empty");

        String params = "[" + join(",", txIds.stream().map(BitcoinProxyAsync::jsonStr).toList()) + "]";

        if (blockhash != null)
            params += """
                    ,"%s"\
                    """.formatted(blockhash);
        final HttpRequest request = getRequest(Command.gettxoutproof, params);
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrString)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<TransactionOutputSetInfo> getTxOutsetInfo(HashType hashType) {
        hashType = hashType == null ? HashType.hash_serialized_2 : hashType; // set default
        final HttpRequest request = getRequest(Command.gettxoutsetinfo, jsonStr(hashType.toString()));
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrTransactionOutputSetInfo)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<Void> preciousBlock(String blockhash) {
        final HttpRequest request = getRequest(Command.preciousblock, jsonStr(blockhash));
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrString)
                .thenAccept(this::checkRpcVoidResponse);
    }

    @Override
    public CompletableFuture<Integer> pruneBlockchain(int height) {
        final HttpRequest request = getRequest(Command.pruneblockchain, Integer.toString(height));
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrInteger)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<DumpFile> saveMempool() {
        return send(saveMempoolRequest, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrDumpFile)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<ScanTxOutResult> scanTxOutset(ScanTxAction action, List<PsbtDescriptor> scanObjects) {
        requireNonNull(action);

        final String params = action == ScanTxAction.start
                ? """
                "%s",%s\
                """.formatted(action, arrayToStr(scanObjects, this::psbtDescriptorToStr))
                : jsonStr(action.toString());

        final HttpRequest request = getRequest(Command.scantxoutset, params);
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrScanTxOutResult)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<Boolean> verifyChain(int checkLevel, int nBlocks) {
        if (0 < checkLevel && checkLevel < 4) throw new IllegalArgumentException("checkLevel:" + checkLevel);

        final String params = """
                %d,%d\
                """.formatted(checkLevel, nBlocks);

        final HttpRequest request = getRequest(Command.verifychain, params);
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrBoolean)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<List<String>> verifyTxOutProof(String proof) {
        final HttpRequest request = getRequest(Command.verifytxoutproof, jsonStr(proof));
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrStringList)
                .thenApply(this::checkRpcResponse);
    }
    //end blockchain rpc

    //control rpc
    @Override
    public CompletableFuture<MemoryInfo> getMemoryInfo() {
        return send(getMemoryInfoRequest, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrMemoryInfo)
                .thenApply(this::checkRpcResponse);
    }

    public CompletableFuture<String> getMemoryInfoMalloc() {
        return send(getMemoryInfoMallocRequest, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrString)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<RpcInfo> getRpcInfo() {
        return send(getRpcInfoRequest, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrRpcInfo)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<String> help(Command command) {
        final String params = command == null ? "" : jsonStr(command.toString());
        final HttpRequest request = getRequest(Command.help, params);
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrString)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<Map<String, Boolean>> logging(Set<Logging> include, Set<Logging> exclude) {
        final String includeParams = setParams(include);
        final String excludeParams = setParams(exclude);
        final HttpRequest request = getRequest(Command.logging, "%s,%s".formatted(includeParams, excludeParams));
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrMapBoolean)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<String> stop() {
        return send(stopRequest, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrString)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<Integer> uptime() {
        return send(uptimeRequest, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrInteger)
                .thenApply(this::checkRpcResponse);
    }
    //end control rpc

    //generating rpc
    @Override
    public CompletableFuture<GeneratedBlock> generateBlock(String output, List<String> transactionIds) {
        final String transactions = setParams(transactionIds);
        final HttpRequest request = getRequest(Command.generateblock, """
                "%s",%s\
                """.formatted(output, transactions));
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrGeneratedBlock)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<List<String>> generateToAddress(int nBlocks, String address, Integer maxTries) {
        maxTries = maxTries != null ? maxTries : 1000000;
        final HttpRequest request = getRequest(Command.generatetoaddress, """
                %d,"%s",%d\
                """.formatted(nBlocks, address, maxTries));
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrStringList)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<List<String>> generateToDescriptor(int nBlocks, String descriptor, Integer maxTries) {
        maxTries = maxTries != null ? maxTries : 1000000;
        final HttpRequest request = getRequest(Command.generatetodescriptor, """
                %d,"%s",%d\
                """.formatted(nBlocks, descriptor, maxTries));
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrStringList)
                .thenApply(this::checkRpcResponse);
    }
    //end generating rpc

    //mining rpc
    @Override
    public CompletableFuture<BlockTemplate> getBlockTemplate(MiningTemplate templateRequest) {
        String params = templateRequest != null ? miningTemplateToStr(templateRequest, false) : """
                {"rules": ["segwit"]}\
                """;
        final HttpRequest request = getRequest(Command.getblocktemplate, params);
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrBlockTemplate)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<String> getBlockTemplateProposal(MiningTemplate templateRequest) {
        requireNonNull(templateRequest);
        String params = miningTemplateToStr(templateRequest, true);
        final HttpRequest request = getRequest(Command.getblocktemplate, params);
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrString)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<MiningInfo> getMiningInfo() {
        return send(getMiningInfoRequest, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrMiningInfo)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<Double> getNetworkHashPs(Integer nBlocks, Integer height) {
        nBlocks = nBlocks != null ? nBlocks : 120;
        height = height != null ? height : -1;
        final HttpRequest request = getRequest(Command.getnetworkhashps, "%d,%d".formatted(nBlocks, height));
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrDouble)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<Boolean> prioritiseTransaction(String txId, int feeDelta) {
        final HttpRequest request = getRequest(Command.prioritisetransaction, "%s,0.0,%d".formatted(jsonStr(txId), feeDelta));
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrBoolean)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<String> submitBlock(String hexData) {
        final HttpRequest request = getRequest(Command.submitblock, "%s".formatted(jsonStr(hexData)));
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrString)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<Void> submitHeader(String hexData) {
        final HttpRequest request = getRequest(Command.submitheader, "%s".formatted(jsonStr(hexData)));
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrString)
                .thenAccept(this::checkRpcVoidResponse);
    }
    //end mining rpc

    //network rpc
    @Override
    public CompletableFuture<Void> addNode(String node, AddNetworkCommand command) {
        requireNonNull(node);
        requireNonNull(command);
        final HttpRequest request = getRequest(Command.addnode, "%s,%s".formatted(jsonStr(node), jsonStr(command.toString())));
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrString)
                .thenAccept(this::checkRpcVoidResponse);
    }

    @Override
    public CompletableFuture<Void> clearBanned() {
        return send(clearBannedRequest, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrString)
                .thenAccept(this::checkRpcVoidResponse);
    }

    @Override
    public CompletableFuture<Void> disconnectNode(String address, Integer nodeId) {
        String params;
        if (address != null)
            params = jsonStr(address);
        else if (nodeId != null)
            params = """
                    "",%d\
                    """.formatted(nodeId);
        else
            throw new IllegalArgumentException("Both address and nodeId are null");

        final HttpRequest request = getRequest(Command.disconnectnode, params);
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrString)
                .thenAccept(this::checkRpcVoidResponse);
    }

    @Override
    public CompletableFuture<List<AddedNodeInfo>> getAddedNodeInfo(String node) {
        final String params = node != null ? jsonStr(node) : "";
        final HttpRequest request = getRequest(Command.getaddednodeinfo, params);
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrAddedNodeInfoList)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<Empty> getBlockFromPeer(String blockhash, int peerId) {
        requireNonNull(blockhash);
        final HttpRequest request = getRequest(Command.getblockfrompeer, """
                "%s",%d\
                """.formatted(blockhash, peerId));
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrEmpty)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<Integer> getConnectionCount() {
        return send(getConnectionCountRequest, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrInteger)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<NetTotals> getNetTotals() {
        return send(getNetTotalsRequest, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrNetTotals)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<NetworkInfo> getNetworkInfo() {
        return send(getNetworkInfoRequest, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrNetworkInfo)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<List<NodeAddress>> getNodeAddresses(Integer count, NetworkType networkType) {
        count = requireNonNullElse(count, 1);
        final String params;
        if (networkType != null) {
            params = """
                    %d,"%s"\
                    """.formatted(count, networkType);
        } else {
            params = Integer.toString(count);
        }
        final HttpRequest request = getRequest(Command.getnodeaddresses, params);
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrNodeAddressList)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<List<PeerInfo>> getPeerInfo() {
        return send(getPeerInfoRequest, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrPeerInfoList)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<List<BannedInfo>> listBanned() {
        return send(listBannedRequest, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrBannedInfoList)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<Void> ping() {
        return send(pingRequest, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrString)
                .thenAccept(this::checkRpcVoidResponse);
    }

    @Override
    public CompletableFuture<Void> setBan(String subnet, SetBanCommand command, Integer banTime, Boolean absolute) {
        requireNonNull(subnet);
        requireNonNull(command);

        banTime = requireNonNullElse(banTime, 0);
        absolute = requireNonNullElse(absolute, false);

        final HttpRequest request = getRequest(Command.setban,
                "%s,%s,%d,%b".formatted(jsonStr(subnet), jsonStr(command.toString()), banTime, absolute));
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrString)
                .thenAccept(this::checkRpcVoidResponse);
    }

    @Override
    public CompletableFuture<Boolean> setNetworkActive(boolean state) {
        final HttpRequest request = getRequest(Command.setnetworkactive, "%b".formatted(state));
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrBoolean)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<AddPeerAddress> addPeerAddress(String host, int port) {
        requireNonNull(host);
        final HttpRequest request = getRequest(Command.addpeeraddress, """
                "%s",%d
                """.formatted(host, port));
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrAddPeerAddress)
                .thenApply(this::checkRpcResponse);
    }
    //end network rpc

    //raw transactions rpc
    @Override
    public CompletableFuture<PsbtAnalysis> analyzePsbt(String psbt) {
        final HttpRequest request = getRequest(Command.analyzepsbt, jsonStr(psbt));
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrPsbtAnalysis)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<String> combinePsbt(List<String> txs) {
        final String transactions = setParams(txs);
        final HttpRequest request = getRequest(Command.combinepsbt, transactions);
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrString)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<String> combineRawTransaction(List<String> txs) {
        final String transactions = setParams(txs);
        final HttpRequest request = getRequest(Command.combinerawtransaction, transactions);
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrString)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<String> convertToPsbt(String hexRawTrans, Boolean permitSigData, Boolean isWitness) {
        requireNonNull(hexRawTrans);
        permitSigData = requireNonNullElse(permitSigData, false);
        final String params;
        if (isWitness == null)
            params = "%s,%b".formatted(jsonStr(hexRawTrans), permitSigData);
        else
            params = "%s,%b,%b".formatted(jsonStr(hexRawTrans), permitSigData, isWitness);

        final HttpRequest request = getRequest(Command.converttopsbt, params);
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrString)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<String> createPsbt(
            List<PsbtInput> inputs,
            Map<String, String> outputAddresses,
            String outputData,
            Integer lockTime,
            Boolean replaceable
    ) {
        if (inputs == null || inputs.isEmpty())
            throw new IllegalArgumentException("inputs is null or empty");
        lockTime = requireNonNullElse(lockTime, 0);
        replaceable = requireNonNullElse(replaceable, false);

        final String sInput = arrayToStr(inputs, this::psbtInputToStr);
        final String sOutput = getPsbtOutputParam(outputAddresses, outputData);
        final HttpRequest request = getRequest(Command.createpsbt, "%s,%s,%d,%b".formatted(sInput, sOutput, lockTime, replaceable));
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrString)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<String> createRawTransaction(
            List<PsbtInput> inputs,
            Map<String, String> outputAddresses,
            String outputData,
            Integer lockTime,
            Boolean replaceable
    ) {
        lockTime = requireNonNullElse(lockTime, 0);
        replaceable = requireNonNullElse(replaceable, false);

        final String sInput = arrayToStr(inputs, this::psbtInputToStr);
        final String sOutput = getPsbtOutputParam(outputAddresses, outputData);
        final HttpRequest request = getRequest(Command.createrawtransaction, "%s,%s,%d,%b".formatted(sInput, sOutput, lockTime, replaceable));
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrString)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<DecodedPsbt> decodePsbt(String psbt) {
        requireNonNull(psbt);
        final HttpRequest request = getRequest(Command.decodepsbt, jsonStr(psbt));
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrDecodedPsbt)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<RawTransaction> decodeRawTransaction(String hexRawTrans, Boolean isWitness) {
        requireNonNull(hexRawTrans);
        final String params;
        if (isWitness == null)
            params = jsonStr(hexRawTrans);
        else
            params = "%s,%b".formatted(jsonStr(hexRawTrans), isWitness);

        final HttpRequest request = getRequest(Command.decoderawtransaction, params);
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrRawTransaction)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<DecodedScript> decodeScript(String hexScript) {
        requireNonNull(hexScript);
        final HttpRequest request = getRequest(Command.decodescript, jsonStr(hexScript));
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrDecodedScript)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<FinalizedPsbt> finalizePsbt(String psbt, Boolean extract) {
        requireNonNull(psbt);
        extract = requireNonNullElse(extract, true);
        final HttpRequest request = getRequest(Command.finalizepsbt, "%s,%b".formatted(jsonStr(psbt), extract));
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrFinalizedPsbt)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<TransactionFunding> fundRawTransaction(String hexRawTrans, FundTransactionOptions options, Boolean isWitness) {
        requireNonNull(hexRawTrans);
        String params = jsonStr(hexRawTrans);

        if (options != null) {
            String o = fundTransactionOptionsToStr(options);
            if (!o.isBlank())
                params += "," + o;
        } else {
            params += ",{}";
        }

        if (isWitness != null)
            params += ",%b".formatted(isWitness);

        final HttpRequest request = getRequest(Command.fundrawtransaction, params);
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrTransactionFunding)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<String> getRawTransaction(String txId, String blockHash) {
        requireNonNull(txId);
        String params = blockHash != null
                ? """
                "%s",false,"%s"\
                """.formatted(txId, blockHash)
                : jsonStr(txId);
        final HttpRequest request = getRequest(Command.getrawtransaction, params);
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrString)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<RawTransaction> getRawTransactionVerbose(String txId, String blockHash) {
        requireNonNull(txId);
        String params = blockHash != null
                ? """
                "%s",true,"%s"\
                """.formatted(txId, blockHash)
                : """
                "%s",true\
                """.formatted(txId);
        final HttpRequest request = getRequest(Command.getrawtransaction, params);
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrRawTransaction)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<String> joinPsbts(List<String> transactions) {
        requireNonNull(transactions);
        final HttpRequest request = getRequest(Command.joinpsbts, setParams(transactions));
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrString)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<String> sendRawTransaction(String hexTrans, String maxFeeRate) {
        requireNonNull(hexTrans);
        String params = jsonStr(hexTrans) + (maxFeeRate != null ? "," + maxFeeRate : "");
        final HttpRequest request = getRequest(Command.sendrawtransaction, params);
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrString)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<SignTransactionResult> signRawTransactionWithKey(
            String hexTrans,
            List<String> privateKeys,
            List<PrevTx> previousTxs,
            SigHashType sigHashType
    ) {
        requireNonNull(hexTrans);
        requireNonNull(privateKeys);
        String params = "%s,%s,%s".formatted(jsonStr(hexTrans), setParams(privateKeys), arrayToStr(previousTxs, this::prevTxsToStr));

        if (sigHashType != null)
            params += ("," + jsonStr(sigHashType.getVal()));
        final HttpRequest request = getRequest(Command.signrawtransactionwithkey, params);
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrSignTransactionResult)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<List<MempoolAccept>> testMempoolAccept(List<String> rawTxs, String maxFeeRate) {
        requireNonNull(rawTxs);
        final String params;
        if (maxFeeRate != null)
            params = """
                    %s,"%s"\
                    """.formatted(setParams(rawTxs), maxFeeRate);
        else
            params = setParams(rawTxs);

        final HttpRequest request = getRequest(Command.testmempoolaccept, params);
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrMempoolAcceptList)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<String> utxoUpdatePsbt(String psbt, List<String> descriptors) {
        requireNonNull(psbt);
        final String params;
        if (descriptors != null && !descriptors.isEmpty())
            params = """
                    "%s",%s\
                    """.formatted(psbt, setParams(descriptors));
        else
            params = jsonStr(psbt);

        final HttpRequest request = getRequest(Command.utxoupdatepsbt, params);
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrString)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<String> utxoUpdatePsbtDesc(String psbt, List<PsbtDescriptor> descriptors) {
        requireNonNull(psbt);

        final String params;
        if (descriptors != null && !descriptors.isEmpty())
            params = """
                    "%s",%s\
                    """.formatted(psbt, arrayToStr(descriptors, this::psbtDescriptorToStr));
        else
            params = jsonStr(psbt);

        final HttpRequest request = getRequest(Command.utxoupdatepsbt, params);
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrString)
                .thenApply(this::checkRpcResponse);
    }
    //end raw transactions rpc

    //util rpc
    @Override
    public CompletableFuture<MultisigAddress> createMultisig(int nRequired, List<String> keys, AddressType addressType) {
        addressType = requireNonNullElse(addressType, AddressType.LEGACY);
        final HttpRequest request = getRequest(Command.createmultisig, """
                %d,%s,"%s"\
                """.formatted(nRequired, setParams(keys), addressType.getVal()));
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrMultisigAddress)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<List<String>> deriveAddresses(String descriptor, Pair<Integer, Integer> range) {
        String params = jsonStr(descriptor);
        if (range != null)
            params += ",[%d,%d]".formatted(range.a(), range.b());
        final HttpRequest request = getRequest(Command.deriveaddresses, params);
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrStringList)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<FeeEstimate> estimateSmartFee(int confirmationTarget, EstimateMode estimateMode) {
        String params = Integer.toString(confirmationTarget);
        if (estimateMode != null)
            params += ",%s".formatted(jsonStr(estimateMode.toString()));
        final HttpRequest request = getRequest(Command.estimatesmartfee, params);
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrFeeEstimate)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<DeploymentInfo> getDeploymentInfo(String blockhash) {
        String params = blockhash != null ? jsonStr(blockhash) : "";
        final HttpRequest request = getRequest(Command.getdeploymentinfo, params);
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrDeploymentInfo)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<DescriptorInfo> getDescriptorInfo(String descriptor) {
        requireNonNull(descriptor);
        final HttpRequest request = getRequest(Command.getdescriptorinfo, jsonStr(descriptor));
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrDescriptorInfo)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<Map<String, IndexInfo>> getIndexInfo(String indexName) {
        String params = indexName != null ? jsonStr(indexName) : "";
        final HttpRequest request = getRequest(Command.getindexinfo, params);
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrIndexInfoMap)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<List<ZmqNotificationInfo>> getZmqNotifications() {
        return send(getZmqNotificationsRequest, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrZmqNotificationInfoList)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<String> signMessageWithPrivKey(String privateKey, String message) {
        requireNonNull(privateKey);
        requireNonNull(message);
        final HttpRequest request = getRequest(Command.signmessagewithprivkey, """
                "%s","%s"\
                """.formatted(privateKey, message));
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrString)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<AddressValidation> validateAddress(String address) {
        requireNonNull(address);
        final HttpRequest request = getRequest(Command.validateaddress, jsonStr(address));
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrAddressValidation)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<Boolean> verifyMessage(String address, String signature, String message) {
        requireNonNull(address);
        requireNonNull(signature);
        requireNonNull(message);
        final HttpRequest request = getRequest(Command.verifymessage, """
                "%s","%s","%s"\
                """.formatted(address, signature, message));
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrBoolean)
                .thenApply(this::checkRpcResponse);
    }
    //end util rpc

    //wallet rpc
    @Override
    public CompletableFuture<Void> abandonTransaction(String txId) {
        requireNonNull(txId);
        final HttpRequest request = getRequest(Command.abandontransaction, jsonStr(txId));
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrString)
                .thenAccept(this::checkRpcVoidResponse);
    }

    @Override
    public CompletableFuture<Boolean> abortRescan() {
        return send(abortRescanRequest, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrBoolean)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<MultisigAddress> addMultisigAddress(int nRequired, List<String> keys, String label, AddressType addressType) {
        requireNonNull(keys);
        label = stringOrNull(label);
        String strAddressType = addressType != null ? jsonStr(addressType.getVal()) : NULL;

        final HttpRequest request = getRequest(Command.addmultisigaddress,
                "%d,%s,%s,%s".formatted(nRequired, setParams(keys), label, strAddressType));
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrMultisigAddress)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<Void> backupWallet(String destination) {
        requireNonNull(destination);
        final HttpRequest request = getRequest(Command.backupwallet, jsonStr(destination));
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrString)
                .thenAccept(this::checkRpcVoidResponse);
    }

    @Override
    public CompletableFuture<BumpFee> bumpFee(String txId, BumpFeeOptions options) {
        requireNonNull(txId);
        String params;
        if (options != null)
            params = "%s,%s".formatted(jsonStr(txId), bumpFeeOptionsToStr(options));
        else
            params = jsonStr(txId);
        final HttpRequest request = getRequest(Command.bumpfee, params);
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrBumpFee)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<Wallet> createWallet(
            String walletName,
            Boolean disablePrivateKeys,
            Boolean blank,
            String passphrase,
            Boolean avoidReuse,
            Boolean descriptors,
            Boolean loadOnStartup,
            Boolean externalSigner
    ) {
        requireNonNull(walletName);
        disablePrivateKeys = requireNonNullElse(disablePrivateKeys, false);
        blank = requireNonNullElse(blank, false);
        passphrase = stringOrNull(passphrase);
        avoidReuse = requireNonNullElse(avoidReuse, false);
        descriptors = requireNonNullElse(descriptors, false);
        String strLoadOnStartup = booleanOrNull(loadOnStartup);
        externalSigner = requireNonNullElse(externalSigner, false);

        final HttpRequest request = getRequest(Command.createwallet, """
                "%s",%b,%b,%s,%b,%b,%s,%b\
                """.formatted(
                walletName,
                disablePrivateKeys,
                blank,
                passphrase,
                avoidReuse,
                descriptors,
                strLoadOnStartup,
                externalSigner
        ));
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrWallet)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<String> dumpPrivKey(String address) {
        requireNonNull(address);
        final HttpRequest request = getRequest(Command.dumpprivkey, jsonStr(address));
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrString)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<DumpFile> dumpWallet(String filename) {
        requireNonNull(filename);
        final HttpRequest request = getRequest(Command.dumpwallet, jsonStr(filename));
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrDumpFile)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<String> encryptWallet(String passphrase) {
        requireNonNull(passphrase);
        final HttpRequest request = getRequest(Command.encryptwallet, jsonStr(passphrase));
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrString)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<Signers> enumerateSigners() {
        return send(enumerateSignersRequest, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrSigners)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<Map<String, AddressByLabel>> getAddressesByLabel(String label) {
        requireNonNull(label);
        final HttpRequest request = getRequest(Command.getaddressesbylabel, jsonStr(label));
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrMapAddressByLabel)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<AddressInfo> getAddressInfo(String address) {
        requireNonNull(address);
        final HttpRequest request = getRequest(Command.getaddressinfo, jsonStr(address));
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrAddressInfo)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<Double> getBalance(Integer minConf, Boolean includeWatchOnly, Boolean avoidReuse) {
        minConf = requireNonNullElse(minConf, 0);
        includeWatchOnly = requireNonNullElse(includeWatchOnly, true);
        avoidReuse = requireNonNullElse(avoidReuse, true);
        final HttpRequest request = getRequest(Command.getbalance, """
                "*",%d,%b,%b\
                """.formatted(minConf, includeWatchOnly, avoidReuse));
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrDouble)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<BalancesData> getBalances() {
        return send(getBalancesRequest, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrBalancesData)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<String> getNewAddress(String label, AddressType addressType) {
        label = requireNonNullElse(label, "");
        String params;
        if (addressType != null)
            params = """
                    "%s","%s"\
                    """.formatted(label, addressType.getVal());
        else
            params = jsonStr(label);

        final HttpRequest request = getRequest(Command.getnewaddress, params);
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrString)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<String> getRawChangeAddress(AddressType addressType) {
        String params = addressType != null ? jsonStr(addressType.getVal()) : "";
        final HttpRequest request = getRequest(Command.getrawchangeaddress, params);
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrString)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<Double> getReceivedByAddress(String address, Integer minConf, Boolean includeImmatureCoinbase) {
        requireNonNull(address);
        minConf = requireNonNullElse(minConf, 1);
        includeImmatureCoinbase = requireNonNullElse(includeImmatureCoinbase, false);

        final HttpRequest request = getRequest(Command.getreceivedbyaddress, """
                "%s",%d,%b\
                """.formatted(address, minConf, includeImmatureCoinbase));
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrDouble)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<Double> getReceivedByLabel(String label, Integer minConf, Boolean includeImmatureCoinbase) {
        requireNonNull(label);
        minConf = requireNonNullElse(minConf, 1);
        includeImmatureCoinbase = requireNonNullElse(includeImmatureCoinbase, false);

        final HttpRequest request = getRequest(Command.getreceivedbylabel, """
                "%s",%d,%b\
                """.formatted(label, minConf, includeImmatureCoinbase));
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrDouble)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<WalletTransaction> getTransaction(String txId, Boolean includeWatchOnly, Boolean verbose) {
        requireNonNull(txId);
        includeWatchOnly = requireNonNullElse(includeWatchOnly, true);
        verbose = requireNonNullElse(verbose, false);
        final HttpRequest request = getRequest(Command.gettransaction, """
                "%s",%b,%b\
                """.formatted(txId, includeWatchOnly, verbose));
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrWalletTransaction)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<String> getUnconfirmedBalance() {
        throw new UnsupportedOperationException();
    }

    @Override
    public CompletableFuture<WalletInfo> getWalletInfo() {
        return send(getWalletInfoRequest, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrWalletInfo)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<Void> importAddress(String address, String label, Boolean rescan, Boolean p2sh) {
        requireNonNull(address);
        label = requireNonNullElse(label, "");
        rescan = requireNonNullElse(rescan, true);
        p2sh = requireNonNullElse(p2sh, false);

        final HttpRequest request = getRequest(Command.importaddress, """
                "%s","%s",%b,%b\
                """.formatted(address, label, rescan, p2sh));
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrString)
                .thenAccept(this::checkRpcVoidResponse);
    }

    @Override
    public CompletableFuture<List<ImportMultiResult>> importDescriptors(List<Descriptor> descriptors) {
        requireNonNull(descriptors);
        final HttpRequest request = getRequest(Command.importdescriptors, arrayToStr(descriptors, this::walletDescriptorToStr));
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrImportMultiResultList)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<List<ImportMultiResult>> importMulti(List<ImportData> requests, Boolean rescan) {
        requireNonNull(requests);
        rescan = requireNonNullElse(rescan, true);
        final HttpRequest request = getRequest(Command.importmulti, """
                %s,{"rescan":%b}\
                """.formatted(arrayToStr(requests, this::multiDescriptorToStr), rescan));
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrImportMultiResultList)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<Void> importPrivKey(String privateKey, String label, Boolean rescan) {
        requireNonNull(privateKey);
        label = requireNonNullElse(label, "");
        rescan = requireNonNullElse(rescan, true);
        final HttpRequest request = getRequest(Command.importprivkey, """
                "%s","%s",%b\
                """.formatted(privateKey, label, rescan));
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrString)
                .thenAccept(this::checkRpcVoidResponse);
    }

    @Override
    public CompletableFuture<Void> importPrunedFunds(String rawTransaction, String txOutProof) {
        requireNonNull(rawTransaction);
        requireNonNull(txOutProof);
        final HttpRequest request = getRequest(Command.importprunedfunds, """
                "%s","%s"\
                """.formatted(rawTransaction, txOutProof));
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrString)
                .thenAccept(this::checkRpcVoidResponse);
    }

    @Override
    public CompletableFuture<Void> importPubKey(String pubKey, String label, Boolean rescan) {
        requireNonNull(pubKey);
        label = requireNonNullElse(label, "");
        rescan = requireNonNullElse(rescan, true);
        final HttpRequest request = getRequest(Command.importpubkey, """
                "%s","%s",%b\
                """.formatted(pubKey, label, rescan));
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrString)
                .thenAccept(this::checkRpcVoidResponse);
    }

    @Override
    public CompletableFuture<Void> importWallet(String filename) {
        requireNonNull(filename);
        final HttpRequest request = getRequest(Command.importwallet, jsonStr(filename));
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrString)
                .thenAccept(this::checkRpcVoidResponse);
    }

    @Override
    public CompletableFuture<Void> keypoolRefill(Integer newSize) {
        newSize = requireNonNullElse(newSize, 100);
        final HttpRequest request = getRequest(Command.keypoolrefill, Integer.toString(newSize));
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrString)
                .thenAccept(this::checkRpcVoidResponse);
    }

    @Override
    public CompletableFuture<List<List<ListAddressGrouping>>> listAddressGroupings() {
        return send(listAddressGroupingsRequest, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrListAddressGrouping)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<Descriptors> listDescriptors(Boolean showPrivate) {
        return send(listDescriptorsRequest, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrDescriptors)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<List<String>> listLabels(String purpose) {
        purpose = requireNonNullElse(purpose, "");
        final HttpRequest request = getRequest(Command.listlabels, jsonStr(purpose));
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrStringList)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<List<UtxoKey>> listLockUnspent() {
        return send(listLockUnspentRequest, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrUtxoKeyList)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<List<BalanceByAddress>> listReceivedByAddress(
            Integer minConf,
            Boolean includeEmpty,
            Boolean includeWatchOnly,
            String addressFilter,
            Boolean includeImmatureCoinbase
    ) {
        minConf = requireNonNullElse(minConf, 1);
        includeEmpty = requireNonNullElse(includeEmpty, false);
        includeWatchOnly = requireNonNullElse(includeWatchOnly, false);
        addressFilter = stringOrNull(addressFilter);
        includeImmatureCoinbase = requireNonNullElse(includeImmatureCoinbase, false);

        final HttpRequest request = getRequest(Command.listreceivedbyaddress, """
                %d,%b,%b,%s,%b\
                """.formatted(minConf, includeEmpty, includeWatchOnly, addressFilter, includeImmatureCoinbase));
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrBalanceByAddressList)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<List<TransactionByLabel>> listReceivedByLabel(
            Integer minConf,
            Boolean includeEmpty,
            Boolean includeWatchOnly,
            Boolean includeImmatureCoinbase) {
        minConf = requireNonNullElse(minConf, 1);
        includeEmpty = requireNonNullElse(includeEmpty, false);
        includeWatchOnly = requireNonNullElse(includeWatchOnly, false);
        includeImmatureCoinbase = requireNonNullElse(includeImmatureCoinbase, false);
        final HttpRequest request = getRequest(Command.listreceivedbylabel,
                "%d,%b,%b,%b".formatted(minConf, includeEmpty, includeWatchOnly, includeImmatureCoinbase));
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrBalanceByLabelList)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<TransactionSinceBlock> listSinceBlock(
            String blockhash,
            Integer targetConfirmations,
            Boolean includeWatchOnly,
            Boolean includeRemoved
    ) {
        blockhash = stringOrNull(blockhash);
        targetConfirmations = requireNonNullElse(targetConfirmations, 1);
        includeWatchOnly = requireNonNullElse(includeWatchOnly, false);
        includeRemoved = requireNonNullElse(includeRemoved, true);
        final HttpRequest request = getRequest(Command.listsinceblock,
                "%s,%d,%b,%b".formatted(blockhash, targetConfirmations, includeWatchOnly, includeRemoved));
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrTransactionSinceBlock)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<List<WalletTransactionInfo>> listTransactions(String label, Integer count, Integer skip, Boolean includeWatchOnly) {
        label = requireNonNullElse(label, "*");
        count = requireNonNullElse(count, 10);
        skip = requireNonNullElse(skip, 0);
        includeWatchOnly = requireNonNullElse(includeWatchOnly, false);
        final HttpRequest request = getRequest(Command.listtransactions, """
                "%s",%d,%d,%b\
                """.formatted(label, count, skip, includeWatchOnly));
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrWalletTransactionInfoList)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<List<UnspentInfo>> listUnspent(
            Integer minConf,
            Integer maxConf,
            List<String> addresses,
            Boolean includeUnsafe,
            ListUnspentOptions queryOptions
    ) {
        minConf = requireNonNullElse(minConf, 1);
        maxConf = requireNonNullElse(maxConf, 9999999);
        includeUnsafe = requireNonNullElse(includeUnsafe, true);
        final HttpRequest request = getRequest(Command.listunspent, "%d,%d,%s,%b,%s"
                .formatted(minConf, maxConf, setParams(addresses), includeUnsafe, listUnspentOptionsToStr(queryOptions)));
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrUnspentInfoList)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<Wallets> listWalletDir() {
        return send(listWalletDirRequest, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrWallets)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<List<String>> listWallets() {
        return send(listWalletsRequest, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrStringList)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<Wallet> loadWallet(String filename, Boolean loadOnStartup) {
        requireNonNull(filename);
        String los = booleanOrNull(loadOnStartup);
        final HttpRequest request = getRequest(Command.loadwallet, """
                "%s",%s\
                """.formatted(filename, los));
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrWallet)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<Boolean> lockUnspent(boolean unlock, List<Transaction> transactions) {
        final HttpRequest request = getRequest(Command.lockunspent,
                "%b,%s".formatted(unlock, arrayToStr(transactions, this::lockUnspentTxToStr)));
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrBoolean)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<Void> newKeypool() {
        return send(newKeypoolRequest, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrString)
                .thenAccept(this::checkRpcVoidResponse);
    }

    @Override
    public CompletableFuture<Void> removePrunedFunds(String txId) {
        requireNonNull(txId);
        final HttpRequest request = getRequest(Command.removeprunedfunds, jsonStr(txId));
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrString)
                .thenAccept(this::checkRpcVoidResponse);
    }

    @Override
    public CompletableFuture<PsbtBumpFee> psbtBumpFee(String txId, BumpFeeOptions options) {
        requireNonNull(txId);
        String params;
        if (options != null)
            params = "%s,%s".formatted(jsonStr(txId), bumpFeeOptionsToStr(options));
        else
            params = jsonStr(txId);
        final HttpRequest request = getRequest(Command.psbtbumpfee, params);
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrPsbtBumpFee)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<ScanInfo> rescanBlockchain(Integer startHeight, Integer stopHeight) {
        startHeight = requireNonNullElse(startHeight, 0);
        String params = stopHeight != null
                ? "%d,%d".formatted(startHeight, stopHeight)
                : Integer.toString(startHeight);
        final HttpRequest request = getRequest(Command.rescanblockchain, params);
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrScanInfo)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<Wallet> restoreWallet(String walletName, String backupFile, Boolean loadOnStartup) {
        requireNonNull(walletName);
        requireNonNull(backupFile);
        String los = booleanOrNull(loadOnStartup);
        final HttpRequest request = getRequest(Command.restorewallet, """
                "%s","%s",%s\
                """.formatted(walletName, backupFile, los));
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrWallet)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<SendInfo> send(
            Map<String, String> outputs,
            Integer confTarget,
            EstimateMode estimateMode,
            String feeRate,
            SendOptions sendOptions
    ) {
        requireNonNull(outputs);
        String confirmationTarget = integerOrNull(confTarget);
        estimateMode = requireNonNullElse(estimateMode, EstimateMode.UNSET);
        feeRate = stringOrNull(feeRate);
        final HttpRequest request = getRequest(Command.send, """
                %s,%s,"%s",%s,%s\
                """.formatted(getSendOutputParam(outputs),
                confirmationTarget,
                estimateMode.toString(),
                feeRate,
                sendOptionsToStr(sendOptions)));
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrSendInfo)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<String> sendMany(
            Map<String, String> amounts,
            String comment,
            List<String> subtractFeeFrom,
            Boolean replaceable,
            Integer confTarget,
            EstimateMode estimateMode,
            String feeRate
    ) {
        requireNonNull(amounts);
        comment = requireNonNullElse(comment, "");
        final String strReplaceable = booleanOrNull(replaceable);
        final String strConfTarget = integerOrNull(confTarget);
        estimateMode = requireNonNullElse(estimateMode, EstimateMode.UNSET);
        feeRate = stringOrNull(feeRate);
        final HttpRequest request = getRequest(Command.sendmany, """
                "",%s,1,"%s",%s,%s,%s,"%s",%s\
                """.formatted(
                mapToObj(amounts),
                comment,
                setParams(subtractFeeFrom),
                strReplaceable,
                strConfTarget,
                estimateMode.toString(),
                feeRate));
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrString)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<SendToAddressInfo> sendManyVerbose(
            Map<String, String> amounts,
            String comment,
            List<String> subtractFeeFrom,
            Boolean replaceable,
            Integer confTarget,
            EstimateMode estimateMode,
            String feeRate
    ) {
        requireNonNull(amounts);
        comment = requireNonNullElse(comment, "");
        final String strReplaceable = booleanOrNull(replaceable);
        final String strConfTarget = integerOrNull(confTarget);
        estimateMode = requireNonNullElse(estimateMode, EstimateMode.UNSET);
        feeRate = stringOrNull(feeRate);
        final HttpRequest request = getRequest(Command.sendmany, """
                "",%s,1,"%s",%s,%s,%s,"%s",%s,true\
                """.formatted(
                mapToObj(amounts),
                comment,
                setParams(subtractFeeFrom),
                strReplaceable,
                strConfTarget,
                estimateMode.toString(),
                feeRate));
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrSendToAddressInfo)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<String> sendToAddress(
            String address,
            String amount,
            String comment,
            String commentTo,
            Boolean subtractFeeFromAmount,
            Boolean replaceable,
            Integer confTarget,
            EstimateMode estimateMode,
            Boolean avoidReuse,
            String feeRate
    ) {
        requireNonNull(address);
        requireNonNull(amount);
        comment = requireNonNullElse(comment, "");
        commentTo = requireNonNullElse(commentTo, "");
        subtractFeeFromAmount = requireNonNullElse(subtractFeeFromAmount, false);
        final String strReplaceable = booleanOrNull(replaceable);
        final String strConfTarget = integerOrNull(confTarget);
        estimateMode = requireNonNullElse(estimateMode, EstimateMode.UNSET);
        avoidReuse = requireNonNullElse(avoidReuse, true);
        feeRate = stringOrNull(feeRate);

        final HttpRequest request = getRequest(Command.sendtoaddress, """
                "%s","%s","%s","%s",%b,%b,%s,"%s",%b,%s\
                """.formatted(
                address,
                amount,
                comment,
                commentTo,
                subtractFeeFromAmount,
                strReplaceable,
                strConfTarget,
                estimateMode.toString(),
                avoidReuse,
                feeRate));
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrString)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<SendToAddressInfo> sendToAddressVerbose(
            String address,
            String amount,
            String comment,
            String commentTo,
            Boolean subtractFeeFromAmount,
            Boolean replaceable,
            Integer confTarget,
            EstimateMode estimateMode,
            Boolean avoidReuse,
            String feeRate
    ) {
        requireNonNull(address);
        requireNonNull(amount);
        comment = requireNonNullElse(comment, "");
        commentTo = requireNonNullElse(commentTo, "");
        subtractFeeFromAmount = requireNonNullElse(subtractFeeFromAmount, false);
        final String strReplaceable = booleanOrNull(replaceable);
        final String strConfTarget = integerOrNull(confTarget);
        estimateMode = requireNonNullElse(estimateMode, EstimateMode.UNSET);
        avoidReuse = requireNonNullElse(avoidReuse, true);
        feeRate = stringOrNull(feeRate);

        final HttpRequest request = getRequest(Command.sendtoaddress, """
                "%s","%s","%s","%s",%b,%b,%s,"%s",%b,%s,true\
                """.formatted(
                address,
                amount,
                comment,
                commentTo,
                subtractFeeFromAmount,
                strReplaceable,
                strConfTarget,
                estimateMode.toString(),
                avoidReuse,
                feeRate));
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrSendToAddressInfo)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<Void> setHdSeed(Boolean newKeyPool, String seed) {
        newKeyPool = requireNonNullElse(newKeyPool, true);
        String params = seed != null
                ? """
                %b,"%s"\
                """.formatted(newKeyPool, seed)
                : Boolean.toString(newKeyPool);
        final HttpRequest request = getRequest(Command.sethdseed, params);
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrString)
                .thenAccept(this::checkRpcVoidResponse);
    }

    @Override
    public CompletableFuture<Void> setLabel(String address, String label) {
        requireNonNull(address);
        requireNonNull(label);
        final HttpRequest request = getRequest(Command.setlabel, """
                "%s","%s"\
                """.formatted(address, label));
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrString)
                .thenAccept(this::checkRpcVoidResponse);
    }

    @Override
    public CompletableFuture<Boolean> setTxFee(String amount) {
        requireNonNull(amount);
        final HttpRequest request = getRequest(Command.settxfee, jsonStr(amount));
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrBoolean)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<WalletFlagInfo> setWalletFlag(WalletFlag flag, Boolean value) {
        requireNonNull(flag);
        value = requireNonNullElse(value, true);
        final HttpRequest request = getRequest(Command.setwalletflag, """
                "%s",%b\
                """.formatted(flag.toString(), value));
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrWalletFlagInfo)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<String> signMessage(String address, String message) {
        requireNonNull(address);
        requireNonNull(message);
        final HttpRequest request = getRequest(Command.signmessage, """
                "%s","%s"\
                """.formatted(address, message));
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrString)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<SignTransactionResult> signRawTransactionWithWallet(String hexTran, List<PrevTx> prevTxs, SigHashType sigHashType) {
        requireNonNull(hexTran);
        sigHashType = requireNonNullElse(sigHashType, SigHashType.ALL);
        final HttpRequest request = getRequest(Command.signrawtransactionwithwallet, """
                "%s",%s,"%s"\
                """.formatted(hexTran, arrayToStr(prevTxs, this::prevTxsToStr), sigHashType.getVal()));
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrSignTransactionResult)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<UnloadWallet> unloadWallet(String walletName, Boolean loadOnStartup) {
        walletName = stringOrNull(walletName);
        final String strLoadOnStartup = booleanOrNull(loadOnStartup);
        final HttpRequest request = getRequest(Command.unloadwallet, "%s,%s".formatted(walletName, strLoadOnStartup));
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrUnloadWallet)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<UpgradeWallet> upgradeWallet(Integer version) {
        String params = version != null ? Integer.toString(version) : "";
        final HttpRequest request = getRequest(Command.upgradewallet, params);
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrUpgradeWallet)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<WalletFundedPsbt> walletCreateFundedPsbt(
            List<PsbtInput> inputs,
            Map<String, String> outputAddresses,
            String outputData,
            Integer lockTime,
            CreateFundedPsbtOptions options,
            Boolean bip32Derivs
    ) {
        final String sInput = arrayToStr(inputs, this::psbtInputToStr);
        final String sOutput = getPsbtOutputParam(outputAddresses, outputData);
        lockTime = requireNonNullElse(lockTime, 0);
        final String sOptions = createFundedPsbtOptionsToStr(options);
        bip32Derivs = requireNonNullElse(bip32Derivs, true);

        final HttpRequest request = getRequest(Command.walletcreatefundedpsbt,
                "%s,%s,%d,%s,%b".formatted(sInput, sOutput, lockTime, sOptions, bip32Derivs));
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrWalletFundedPsbt)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<WalletDisplayAddress> walletDisplayAddress(String address) {
        requireNonNull(address);
        final HttpRequest request = getRequest(Command.walletdisplayaddress, jsonStr(address));
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrWalletDisplayAddress)
                .thenApply(this::checkRpcResponse);
    }

    @Override
    public CompletableFuture<Void> walletLock() {
        return send(walletLockRequest, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrString)
                .thenAccept(this::checkRpcVoidResponse);
    }

    @Override
    public CompletableFuture<Void> walletPassphrase(String passphrase, int timeout) {
        requireNonNull(passphrase);
        final HttpRequest request = getRequest(Command.walletpassphrase, """
                "%s",%d\
                """.formatted(passphrase, timeout));
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrString)
                .thenAccept(this::checkRpcVoidResponse);
    }

    @Override
    public CompletableFuture<Void> walletPassphraseChange(String oldPassphrase, String newPassphrase) {
        requireNonNull(oldPassphrase);
        requireNonNull(newPassphrase);
        final HttpRequest request = getRequest(Command.walletpassphrasechange, """
                "%s","%s"\
                """.formatted(oldPassphrase, newPassphrase));
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrString)
                .thenAccept(this::checkRpcVoidResponse);
    }

    @Override
    public CompletableFuture<WalletPsbt> walletProcessPsbt(
            String psbt,
            Boolean sign,
            SigHashType sigHashType,
            Boolean bip32Derivs,
            Boolean finalize) {
        requireNonNull(psbt);
        sign = requireNonNullElse(sign, true);
        sigHashType = requireNonNullElse(sigHashType, SigHashType.ALL);
        bip32Derivs = requireNonNullElse(bip32Derivs, true);
        finalize = requireNonNullElse(finalize, true);

        final HttpRequest request = getRequest(Command.walletprocesspsbt, """
                "%s",%b,"%s",%b,%b\
                """.formatted(psbt, sign, sigHashType.getVal(), bip32Derivs, finalize));
        return send(request, HttpResponse.BodyHandlers.ofInputStream())
                .thenApply(parsers::parseJrWalletPsbt)
                .thenApply(this::checkRpcResponse);
    }
    //end wallet rpc

    private HttpRequest getConstRequest(Command command) {
        return builder.copy()
                .POST(BodyPublishers.ofString(JSON_RPC_REQUEST.formatted(command, "")))
                .build();
    }

    private HttpRequest getRequest(Command command, String params) {
        return builder.copy()
                .POST(BodyPublishers.ofString(JSON_RPC_REQUEST.formatted(command, params)))
                .build();
    }

    private String getStats(Set<BlockStatOptions> blockStats) {
        return blockStats != null && !blockStats.isEmpty()
                ? ",[" + join(",", blockStats.stream().map(s -> "\"" + s + "\"").toList()) + "]" : "";
    }

    private String miningTemplateToStr(MiningTemplate template, boolean proposal) {
        List<String> items = new ArrayList<>();
        if (proposal)
            items.add("""
                    "mode":"proposal"\
                    """);
        if (template.capabilities() != null && !template.capabilities().isEmpty())
            items.add("""
                    "capabilities":%s\
                    """.formatted(setParams(template.capabilities())));
        Set<String> rules = template.rules() != null ? new HashSet<>(template.rules()) : new HashSet<>();
        rules.add("segwit"); //segwit must be present
        items.add("""
                "rules":%s\
                """.formatted(setParams(rules)));

        return "{" + join(",", items) + "}";
    }

    private String psbtInputToStr(PsbtInput input) {
        var sequence = integerOrNull(input.sequence());
        var weight = integerOrNull(input.weight());
        return """
                {"txid":"%s","vout":%d,"sequence":%s,"weight":%s}\
                """.formatted(input.txId(), input.vOut(), sequence, weight);
    }

    private String getPsbtOutputParam(Map<String, String> psbtOutputs, String outputData) {
        List<String> outputs = outputsList(psbtOutputs);
        if (outputData != null)
            outputs.add(psbtOutputDataToStr(outputData));
        return "[" + join(",", outputs) + "]";
    }

    private List<String> outputsList(Map<String, String> outputs) {
        return outputs.entrySet().stream().map(this::outputToStr).collect(toList());
    }

    private String outputToStr(Map.Entry<String, String> output) {
        return """
                {"%s":"%s"}\
                """.formatted(output.getKey(), output.getValue());
    }

    private String psbtOutputDataToStr(String outputData) {
        return """
                {"data":"%s"}\
                """.formatted(outputData);
    }

    private String fundTransactionOptionsToStr(FundTransactionOptions options) {
        List<String> items = new ArrayList<>();
        addBoolParam("add_inputs", options.addInputs(), items);
        addBoolParam("include_unsafe", options.includeUnsafe(), items);
        addStrParam("changeAddress", options.changeAddress(), items);
        addIntParam("changePosition", options.changePosition(), items);
        if (options.changeType() != null)
            items.add("""
                    "change_type":"%s"\
                    """.formatted(options.changeType().getVal()));
        addBoolParam("includeWatching", options.includeWatching(), items);
        addBoolParam("lockUnspents", options.lockUnspent(), items);
        addStrParam("fee_rate", options.feeRateSat(), items);
        addStrParam("feeRate", options.feeRateBtc(), items);
        if (options.subtractFeeFromOptions() != null && !options.subtractFeeFromOptions().isEmpty())
            items.add("""
                    "subtractFeeFromOutputs":%s\
                    """.formatted(setIntParams(options.subtractFeeFromOptions())));
        if (options.inputWeights() != null && !options.inputWeights().isEmpty())
            items.add("""
                    "input_weights":%s\
                    """.formatted(arrayToStr(options.inputWeights(), this::inputWeightToStr)));
        addIntParam("conf_target", options.confTarget(), items);
        if (options.estimateMode() != null)
            items.add("""
                    "estimate_mode":"%s"\
                    """.formatted(options.estimateMode().toString()));
        addBoolParam("replaceable", options.replaceable(), items);
        if (options.solvingData() != null)
            items.add("""
                    "solving_data":%s\
                    """.formatted(solveDataToStr(options.solvingData())));
        if (items.isEmpty())
            return "";

        return "{" + join(",", items) + "}";
    }

    private String prevTxsToStr(PrevTx tx) {
        requireNonNull(tx.txId());
        requireNonNull(tx.scriptPubKey());
        List<String> items = new ArrayList<>();
        items.add("""
                "txid":"%s","vout":%d,"scriptPubKey":"%s"\
                """.formatted(tx.txId(), tx.vOut(), tx.scriptPubKey()));

        addStrParam("redeemScript", tx.redeemScript(), items);
        addStrParam("witnessScript", tx.witnessScript(), items);
        addStrParam("amount", tx.amount(), items);

        return "{" + join(",", items) + "}";
    }

    private String psbtDescriptorToStr(PsbtDescriptor descriptor) {
        requireNonNull(descriptor.desc());
        List<String> items = new ArrayList<>();
        items.add("""
                "desc":"%s"\
                """.formatted(descriptor.desc()));

        if (descriptor.range() != null)
            items.add("""
                    "range":[%d,%d]\
                    """.formatted(descriptor.range().a(), descriptor.range().b()));

        return "{" + join(",", items) + "}";
    }

    private String bumpFeeOptionsToStr(BumpFeeOptions options) {
        List<String> items = new ArrayList<>();
        addIntParam("conf_target", options.confTarget(), items);
        addStrParam("fee_rate", options.feeRate(), items);
        addBoolParam("replaceable", options.replaceable(), items);

        if (options.estimateMode() != null)
            items.add("""
                    "estimate_mode":"%s"\
                    """.formatted(options.estimateMode().toString()));

        return "{" + join(",", items) + "}";
    }

    private String walletDescriptorToStr(Descriptor descriptor) {
        requireNonNull(descriptor.desc());
        requireNonNull(descriptor.timestamp());
        String timestamp = "now".equals(descriptor.timestamp()) ? jsonStr("now") : descriptor.timestamp();
        List<String> items = new ArrayList<>();
        items.add("""
                "desc":"%s"\
                """.formatted(descriptor.desc()));

        if (descriptor.range() != null)
            items.add("""
                    "range":[%d,%d]\
                    """.formatted(descriptor.range().a(), descriptor.range().b()));

        items.add("""
                "timestamp":%s\
                """.formatted(timestamp));

        if (descriptor.active() != null)
            items.add("""
                    "active":%b\
                    """.formatted(descriptor.active()));

        if (descriptor.nextIndex() != null)
            items.add("""
                    ""next_index":%d\
                    """.formatted(descriptor.nextIndex()));

        if (descriptor.internal() != null)
            items.add("""
                    "internal":%b\
                    """.formatted(descriptor.internal()));

        if (descriptor.label() != null)
            items.add("""
                    "label":"%s"\
                    """.formatted(descriptor.label()));

        return "{" + join(",", items) + "}";
    }

    private String multiDescriptorToStr(ImportData descriptor) {
        ImportType type = descriptor.type();
        requireNonNull(type);
        requireNonNull(descriptor.timestamp());
        String timestamp = "now".equals(descriptor.timestamp()) ? jsonStr("now") : descriptor.timestamp();
        List<String> items = new ArrayList<>();

        if (type == ImportType.descriptor)
            items.add("""
                    "desc":"%s","timestamp":%s\
                    """.formatted(descriptor.desc(), timestamp));
        else {
            if (type == ImportType.script)
                items.add("""
                        "scriptPubKey":"%s"\
                        """.formatted(descriptor.scriptPubKey()));
            else
                items.add("""
                        "scriptPubKey":{"address":"%s"}\
                        """.formatted(descriptor.scriptPubKey()));

            items.add("""
                    "timestamp":%s\
                    """.formatted(timestamp));

            addStrParam("redeemscript", descriptor.redeemScript(), items);
            addStrParam("witnessscript", descriptor.witnessScript(), items);

            if (descriptor.pubKeys() != null)
                items.add("""
                        "pubkeys":%s\
                        """.formatted(setParams(descriptor.pubKeys())));
        }

        if (descriptor.keys() != null)
            items.add("""
                    "keys":%s\
                    """.formatted(setParams(descriptor.keys())));

        if (descriptor.range() != null)
            items.add("""
                    "range":[%d,%d]\
                    """.formatted(descriptor.range().a(), descriptor.range().b()));

        addBoolParam("internal", descriptor.internal(), items);
        addBoolParam("watchonly", descriptor.watchOnly(), items);
        addStrParam("label", descriptor.label(), items);
        addBoolParam("keypool", descriptor.keypool(), items);

        return "{" + join(",", items) + "}";
    }

    private String listUnspentOptionsToStr(ListUnspentOptions options) {
        if (options == null) return "{}";
        List<String> items = new ArrayList<>();
        addStrParam("minimumAmount", options.minAmount(), items);
        addStrParam("maximumAmount", options.maxAmount(), items);
        addIntParam("maximumCount", options.maxCount(), items);
        addStrParam("minimumSumAmount", options.minSumAmount(), items);
        return "{" + join(",", items) + "}";
    }

    private String lockUnspentTxToStr(Transaction transaction) {
        requireNonNull(transaction.txId());
        return """
                {"txid":"%s","vout":%d}\
                """.formatted(transaction.txId(), transaction.vOut());
    }

    private String sendOptionsToStr(SendOptions options) {
        if (options == null) return "{}";
        List<String> items = new ArrayList<>();
        addBoolParam("add_inputs", options.addInputs(), items);
        addBoolParam("include_unsafe", options.includeUnsafe(), items);
        addBoolParam("add_to_wallet", options.addToWallet(), items);
        addStrParam("change_address", options.changeAddress(), items);
        addIntParam("change_position", options.changePosition(), items);

        if (options.changeType() != null)
            items.add("""
                    "change_type":"%s"\
                    """.formatted(options.changeType().getVal()));

        addStrParam("fee_rate", options.feeRate(), items);
        addBoolParam("include_watching", options.includeWatching(), items);

        if (options.inputs() != null && !options.inputs().isEmpty())
            items.add("""
                    "inputs":%s\
                    """.formatted(arrayToStr(options.inputs(), this::sendInputToStr)));

        addIntParam("locktime", options.lockTime(), items);
        addBoolParam("lock_unspents", options.lockUnspents(), items);
        addBoolParam("psbt", options.psbt(), items);

        if (options.subtractFeeFromOutputs() != null && !options.subtractFeeFromOutputs().isEmpty())
            items.add("""
                    "psbt":%s\
                    """.formatted(setIntParams(options.subtractFeeFromOutputs())));

        addIntParam("conf_target", options.confTarget(), items);
        if (options.estimateMode() != null)
            items.add("""
                    "estimate_mode":"%s"\
                    """.formatted(options.estimateMode().toString()));

        addBoolParam("replaceable", options.replaceable(), items);
        if (options.solvingData() != null)
            items.add("""
                    "solving_data":%s\
                    """.formatted(solveDataToStr(options.solvingData())));

        return "{" + join(",", items) + "}";
    }

    private String sendInputToStr(SendInput input) {
        if (input.weight() != null)
            return """
                    {"txid":"%s","vout":%d,"sequence":%d,"weight":%d}\
                    """.formatted(input.txId(), input.vOut(), input.sequence(), input.weight());
        else
            return """
                    {"txid":"%s","vout":%d,"sequence":%d}\
                    """.formatted(input.txId(), input.vOut(), input.sequence());
    }

    private String solveDataToStr(SolveData data) {
        List<String> items = new ArrayList<>();

        if (data.pubKeys() != null && !data.pubKeys().isEmpty())
            items.add("""
                    "pubkeys":%s\
                    """.formatted(setParams(data.pubKeys())));

        if (data.scripts() != null && !data.scripts().isEmpty())
            items.add("""
                    "scripts":%s\
                    """.formatted(setParams(data.scripts())));

        if (data.descriptors() != null && !data.descriptors().isEmpty())
            items.add("""
                    "descriptors":%sq\
                    """.formatted(setParams(data.descriptors())));

        return "{" + join(",", items) + "}";
    }

    private String inputWeightToStr(InputWeight input) {
        return """
                {"txid":"%s","vout":%d,"weight":%d}\
                """.formatted(input.txId(), input.vOut(), input.weight());
    }

    private String createFundedPsbtOptionsToStr(CreateFundedPsbtOptions options) {
        if (options == null) return NULL;
        List<String> items = new ArrayList<>();
        addBoolParam("add_inputs", options.addInputs(), items);
        addBoolParam("include_unsafe", options.includeUnsafe(), items);
        addStrParam("changeAddress", options.changeAddress(), items);
        addIntParam("changePosition", options.changePosition(), items);
        if (options.changeType() != null)
            items.add("""
                    "change_type":"%s"\
                    """.formatted(options.changeType().getVal()));
        addBoolParam("includeWatching", options.includeWatching(), items);
        addBoolParam("lockUnspents", options.lockUnspent(), items);
        addStrParam("fee_rate", options.feeRateSat(), items);
        addStrParam("feeRate", options.feeRateBtc(), items);
        if (options.subtractFeeFromOptions() != null && !options.subtractFeeFromOptions().isEmpty())
            items.add("""
                    "subtractFeeFromOutputs":%s\
                    """.formatted(setIntParams(options.subtractFeeFromOptions())));
        addIntParam("conf_target", options.confTarget(), items);
        if (options.estimateMode() != null)
            items.add("""
                    "estimate_mode":"%s"\
                    """.formatted(options.estimateMode().toString()));
        addBoolParam("replaceable", options.replaceable(), items);
        if (options.solvingData() != null)
            items.add("""
                    "solving_data":%s\
                    """.formatted(solveDataToStr(options.solvingData())));
        if (items.isEmpty())
            return "";

        return "{" + join(",", items) + "}";
    }

    private String mapToObj(Map<String, String> outputs) {
        List<String> sendOutputs = itemList(outputs);
        return "{" + join(",", sendOutputs) + "}";
    }

    private List<String> itemList(Map<String, String> outputs) {
        return outputs.entrySet().stream().map(this::itemToStr).collect(toList());
    }

    private String itemToStr(Map.Entry<String, String> output) {
        return """
                "%s":"%s"\
                """.formatted(output.getKey(), output.getValue());
    }

    private String getSendOutputParam(Map<String, String> outputs) {
        List<String> sendOutputs = outputsList(outputs);
        return "[" + join(",", sendOutputs) + "]";
    }

    private <T> String arrayToStr(List<T> items, Function<T, String> mapper) {
        return items != null && !items.isEmpty() ?
                "[" + join(",", items.stream().map(mapper).toList()) + "]" : "[]";
    }

    private String setParams(Set<? extends Enum<?>> enums) {
        return enums != null && !enums.isEmpty()
                ? "[" + join(",", enums.stream().map(s -> "\"" + s + "\"").toList()) + "]" : "[]";
    }

    private String setParams(Collection<String> strings) {
        return strings != null && !strings.isEmpty()
                ? "[" + join(",", strings.stream().map(BitcoinProxyAsync::jsonStr).toList()) + "]" : "[]";
    }

    private String setIntParams(List<Integer> integers) {
        return integers != null && !integers.isEmpty()
                ? "[" + join(",", integers.stream().map(i -> Integer.toString(i)).toList()) + "]" : "[]";
    }

    private <T> T checkRpcResponse(RpcResponse<T> response) {
        if (!"1".equals(response.getId()))
            throw new IllegalArgumentException("rpc id is invalid. expected '1' got %s".formatted(response.getId()));
        if (response.getError() != null)
            throw new RpcException("rpc invocation failed with error", response.getError());
        return response.getResult();
    }

    private void checkRpcVoidResponse(RpcResponse<String> response) {
        if (checkRpcResponse(response) != null)
            throw new IllegalArgumentException("rpc response is not null");
    }

    private static void addStrParam(String name, String s, List<String> items) {
        if (s != null)
            items.add("""
                    "%s":"%s"\
                    """.formatted(name, s));
    }

    private static void addBoolParam(String name, Boolean b, List<String> items) {
        if (b != null)
            items.add("""
                    "%s":%b\
                    """.formatted(name, b));
    }

    private static void addIntParam(String name, Integer i, List<String> items) {
        if (i != null)
            items.add("""
                    "%s":%d\
                    """.formatted(name, i));
    }

    private static String jsonStr(String s) {
        return "\"" + s + "\"";
    }

    private static String stringOrNull(String s) {
        return s != null ? jsonStr(s) : NULL;
    }

    private static String booleanOrNull(Boolean b) {
        return b != null ? b.toString() : NULL;
    }

    private static String integerOrNull(Integer i) {
        return i != null ? i.toString() : NULL;
    }

    private <T> CompletableFuture<T> send(HttpRequest request, HttpResponse.BodyHandler<T> responseBodyHandler) {
        return client.sendAsync(request, responseBodyHandler).thenApply(HttpResponse::body);
    }

    private static final String JSON_RPC_REQUEST = """
            {"jsonrpc":"1.0","id":"1","method":"%s","params":[%s]}""";

    private static final String NULL = "null";
}
