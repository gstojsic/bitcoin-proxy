package io.github.gstojsic.bitcoin.proxy.json.model;

import io.github.gstojsic.bitcoin.proxy.json.annotation.JsonProperty;

import java.util.List;

public class BlockTemplateTransaction {
    /**
     * (string) transaction data encoded in hexadecimal (byte-for-byte)
     */
    private String data;

    /**
     * (string) transaction id encoded in little-endian hexadecimal
     */
    @JsonProperty("txid")
    private String txId;

    /**
     * (string) hash encoded in little-endian hexadecimal (including witness data)
     */
    private String hash;

    /**
     * (json array) array of numbers
     * (numeric) transactions before this one (by 1-based index in 'transactions' list) that must be present in the final block if this one is
     */
    private List<Integer> depends;

    /**
     * (numeric) difference in value between transaction inputs and outputs (in satoshis); for coinbase transactions,
     * this is a negative Number of the total collected block fees (ie, not including the block subsidy);
     * if key is not present, fee is unknown and clients MUST NOT assume there isn't one
     */
    private int fee;

    /**
     * (numeric) total SigOps cost, as counted for purposes of block limits; if key is not present, sigop cost is unknown and clients MUST NOT assume it is zero
     */
    @JsonProperty("sigops")
    private int sigOps;

    /**
     * (numeric) total transaction weight, as counted for purposes of block limits
     */
    private int weight;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTxId() {
        return txId;
    }

    public void setTxId(String txId) {
        this.txId = txId;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public List<Integer> getDepends() {
        return depends;
    }

    public void setDepends(List<Integer> depends) {
        this.depends = depends;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public int getSigOps() {
        return sigOps;
    }

    public void setSigOps(int sigOps) {
        this.sigOps = sigOps;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
