package io.github.gstojsic.bitcoin.proxy.json.model;

import io.github.gstojsic.bitcoin.proxy.json.annotation.JsonProperty;

import java.util.List;

public class MempoolData {
    /**
     * (numeric) virtual transaction size as defined in BIP 141. This is different from actual serialized size for witness transactions as witness data is discounted.
     */
    private int vsize;

    /**
     * (numeric) transaction weight as defined in BIP 141.
     */
    private int weight;

    /**
     * (numeric) local time transaction entered pool in seconds since 1 Jan 1970 GMT
     */
    private long time;

    /**
     * (numeric) block height when transaction entered pool
     */
    private int height;

    /**
     * (numeric) number of in-mempool descendant transactions (including this one)
     */
    @JsonProperty("descendantcount")
    private int descendantCount;

    /**
     * (numeric) virtual transaction size of in-mempool descendants (including this one)
     */
    @JsonProperty("descendantsize")
    private int descendantSize;

    /**
     * (numeric) number of in-mempool ancestor transactions (including this one)
     */
    @JsonProperty("ancestorcount")
    private int ancestorCount;

    /**
     * (numeric) virtual transaction size of in-mempool ancestors (including this one)
     */
    @JsonProperty("ancestorsize")
    private int ancestorSize;

    /**
     * (string) hash of serialized transaction, including witness data
     */
    @JsonProperty("wtxid")
    private String wTxId;

    /**
     * fees
     */
    private Fees fees;

    /**
     * (json array) unconfirmed transactions used as inputs for this transaction
     * contains parent transaction Ids
     */
    private List<String> depends;

    /**
     * (json array) unconfirmed transactions spending outputs from this transaction
     * contains child transaction Ids
     */
    @JsonProperty("spentby")
    private List<String> spentBy;

    /**
     * (boolean) Whether this transaction signals BIP125 replaceability or has an unconfirmed ancestor signaling BIP125
     * replaceability.
     */
    @JsonProperty("bip125-replaceable")
    private boolean bip125Replaceable;

    /**
     * (boolean) Whether this transaction is currently unbroadcast (initial broadcast not yet acknowledged by any peers)
     */
    @JsonProperty("unbroadcast")
    private boolean unBroadcast;

    public int getVsize() {
        return vsize;
    }

    public void setVsize(int vsize) {
        this.vsize = vsize;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getDescendantCount() {
        return descendantCount;
    }

    public void setDescendantCount(int descendantCount) {
        this.descendantCount = descendantCount;
    }

    public int getDescendantSize() {
        return descendantSize;
    }

    public void setDescendantSize(int descendantSize) {
        this.descendantSize = descendantSize;
    }

    public int getAncestorCount() {
        return ancestorCount;
    }

    public void setAncestorCount(int ancestorCount) {
        this.ancestorCount = ancestorCount;
    }

    public int getAncestorSize() {
        return ancestorSize;
    }

    public void setAncestorSize(int ancestorSize) {
        this.ancestorSize = ancestorSize;
    }

    public String getWTxId() {
        return wTxId;
    }

    public void setWTxId(String wTxId) {
        this.wTxId = wTxId;
    }

    public Fees getFees() {
        return fees;
    }

    public void setFees(Fees fees) {
        this.fees = fees;
    }

    public List<String> getDepends() {
        return depends;
    }

    public void setDepends(List<String> depends) {
        this.depends = depends;
    }

    public List<String> getSpentBy() {
        return spentBy;
    }

    public void setSpentBy(List<String> spentBy) {
        this.spentBy = spentBy;
    }

    public boolean isBip125Replaceable() {
        return bip125Replaceable;
    }

    public void setBip125Replaceable(boolean bip125Replaceable) {
        this.bip125Replaceable = bip125Replaceable;
    }

    public boolean isUnBroadcast() {
        return unBroadcast;
    }

    public void setUnBroadcast(boolean unBroadcast) {
        this.unBroadcast = unBroadcast;
    }
}