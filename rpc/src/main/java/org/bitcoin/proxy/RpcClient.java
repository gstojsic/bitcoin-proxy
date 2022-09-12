package org.bitcoin.proxy;

import java.io.IOException;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class RpcClient implements BtcRpc {
    public RpcClient(String user, String password) {
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
    }

    private final HttpClient client;

    //blockchain rpc
    @Override
    public void getBestBlockhash() {

    }

    @Override
    public void getBlock() {

    }

    @Override
    public void getBlockchainInfo() {

    }

    @Override
    public void getBlockCount() {

    }

    @Override
    public void getBlockFilter() {

    }

    @Override
    public void getBlockhash() {

    }

    @Override
    public void getBlockHeader() {

    }

    @Override
    public void getBlockStats() {

    }

    @Override
    public void getChainTips() {

    }

    @Override
    public void getChainTxStats() {

    }

    @Override
    public int getDifficulty() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .POST(HttpRequest.BodyPublishers.ofString(GET_DIFFICULTY))
                    .uri(new URI("http://127.0.0.1:8332"))
                    .setHeader("content-type", "text/plain")
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println(response.body());
        } catch (IOException | InterruptedException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    @Override
    public void getMempoolAncestors() {

    }

    @Override
    public void getMempoolDescendants() {

    }

    @Override
    public void getMempoolEntry() {

    }

    @Override
    public void getMempoolInfo() {

    }

    @Override
    public void getRawMempool() {

    }

    @Override
    public void getTxOut() {

    }

    @Override
    public void getTxOutProof() {

    }

    @Override
    public void getTxOutsetInfo() {

    }

    @Override
    public void preciousBlock() {

    }

    @Override
    public void pruneBlockchain() {

    }

    @Override
    public void saveMempool() {

    }

    @Override
    public void scanTxOutset() {

    }

    @Override
    public void verifyChain() {

    }

    @Override
    public void verifyTxOutProof() {

    }
    //end blockchain rpc

    //control rpc
    @Override
    public void getMemoryInfo() {

    }

    @Override
    public void getRpcInfo() {

    }

    @Override
    public void help() {

    }

    @Override
    public void logging() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void uptime() {

    }
    //end control rpc

    //generating rpc
    @Override
    public void generateBlock() {

    }

    @Override
    public void generateToAddress() {

    }

    @Override
    public void generateToDescriptor() {

    }
    //end generating rpc

    //mining rpc
    @Override
    public void getBlockTemplate() {

    }

    @Override
    public void getMiningInfo() {

    }

    @Override
    public void getNetworkHashPs() {

    }

    @Override
    public void prioritiseTransaction() {

    }

    @Override
    public void submitBlock() {

    }

    @Override
    public void submitHeader() {

    }
    //end mining rpc

    //network rpc
    @Override
    public void addNode() {

    }

    @Override
    public void clearBanned() {

    }

    @Override
    public void disconnectNode() {

    }

    @Override
    public void getAddedNodeInfo() {

    }

    @Override
    public void getConnectionCount() {

    }

    @Override
    public void getNetTotals() {

    }

    @Override
    public void getNetworkInfo() {

    }

    @Override
    public void getNodeAddresses() {

    }

    @Override
    public void getPeerInfo() {

    }

    @Override
    public void listBanned() {

    }

    @Override
    public void ping() {

    }

    @Override
    public void setBan() {

    }

    @Override
    public void setNetworkActive() {

    }
    //end network rpc

    //raw transactions rpc
    @Override
    public void analyzePsbt() {

    }

    @Override
    public void combinePsbt() {

    }

    @Override
    public void combineRawTransaction() {

    }

    @Override
    public void convertToPsbt() {

    }

    @Override
    public void createPsbt() {

    }

    @Override
    public void createRawTransaction() {

    }

    @Override
    public void decodePsbt() {

    }

    @Override
    public void decodeRawTransaction() {

    }

    @Override
    public void decodeScript() {

    }

    @Override
    public void finalizePsbt() {

    }

    @Override
    public void fundRawTransaction() {

    }

    @Override
    public void getRawTransaction() {

    }

    @Override
    public void joinPsbts() {

    }

    @Override
    public void sendRawTransaction() {

    }

    @Override
    public void signRawTransactionWithKey() {

    }

    @Override
    public void testMempoolAccept() {

    }

    @Override
    public void utxoUpdatePsbt() {

    }
    //end raw transactions rpc

    //util rpc
    @Override
    public void createMultisig() {

    }

    @Override
    public void deriveAddresses() {

    }

    @Override
    public void estimateSmartFee() {

    }

    @Override
    public void getDescriptorInfo() {

    }

    @Override
    public void getIndexInfo() {

    }

    @Override
    public void signMessageWithPrivKey() {

    }

    @Override
    public void validateAddress() {

    }

    @Override
    public void verifyMessage() {

    }
    //end util rpc

    //wallet rpc
    @Override
    public void abandonTransaction() {

    }

    @Override
    public void abortRescan() {

    }

    @Override
    public void addMultisigAddress() {

    }

    @Override
    public void backupWallet() {

    }

    @Override
    public void bumpFee() {

    }

    @Override
    public void createWallet() {

    }

    @Override
    public void dumpPrivKey() {

    }

    @Override
    public void dumpWallet() {

    }

    @Override
    public void encryptWallet() {

    }

    @Override
    public void getAddressesByLabel() {

    }

    @Override
    public void getAddressInfo() {

    }

    @Override
    public void getBalance() {

    }

    @Override
    public void getBalances() {

    }

    @Override
    public void getNewAddress() {

    }

    @Override
    public void getRawChangeAddress() {

    }

    @Override
    public void getReceivedByAddress() {

    }

    @Override
    public void getReceivedByLabel() {

    }

    @Override
    public void getTransaction() {

    }

    @Override
    public void getUnconfirmedBalance() {

    }

    @Override
    public void getWalletInfo() {

    }

    @Override
    public void importAddress() {

    }

    @Override
    public void importDescriptors() {

    }

    @Override
    public void importMulti() {

    }

    @Override
    public void importPrivKey() {

    }

    @Override
    public void importPrunedFunds() {

    }

    @Override
    public void importPubKey() {

    }

    @Override
    public void importWallet() {

    }

    @Override
    public void keypoolRefill() {

    }

    @Override
    public void listAddressGroupings() {

    }

    @Override
    public void listLabels() {

    }

    @Override
    public void listLockUnspent() {

    }

    @Override
    public void listReceivedByAddress() {

    }

    @Override
    public void listReceivedByLabel() {

    }

    @Override
    public void listSinceBlock() {

    }

    @Override
    public void listTransactions() {

    }

    @Override
    public void listUnspent() {

    }

    @Override
    public void listWalletDir() {

    }

    @Override
    public void listWallets() {

    }

    @Override
    public void loadWallet() {

    }

    @Override
    public void lockUnspent() {

    }

    @Override
    public void psbtBumpFee() {

    }

    @Override
    public void removePrunedFunds() {

    }

    @Override
    public void rescanBlockchain() {

    }

    @Override
    public void send() {

    }

    @Override
    public void sendMany() {

    }

    @Override
    public void sendToAddress() {

    }

    @Override
    public void setHdSeed() {

    }

    @Override
    public void setLabel() {

    }

    @Override
    public void setTxFee() {

    }

    @Override
    public void setWalletFlag() {

    }

    @Override
    public void signMessage() {

    }

    @Override
    public void signRawTransactionWithWallet() {

    }

    @Override
    public void unloadWallet() {

    }

    @Override
    public void upgradeWallet() {

    }

    @Override
    public void walletCreateFundedPsbt() {

    }

    @Override
    public void walletLock() {

    }

    @Override
    public void walletPassphrase() {

    }

    @Override
    public void walletPassphraseChange() {

    }

    @Override
    public void walletProcessPsbt() {

    }
    //end wallet rpc

    private static final String GET_DIFFICULTY = """
            {"jsonrpc": "1.0", "id": "curltest", "method": "getdifficulty", "params": []}
            """;
}
