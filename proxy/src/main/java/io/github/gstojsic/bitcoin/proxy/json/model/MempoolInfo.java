package io.github.gstojsic.bitcoin.proxy.json.model;

import io.github.gstojsic.bitcoin.proxy.json.annotation.JsonProperty;

public class MempoolInfo {

    /**
     * (boolean) True if the mempool is fully loaded
     */
    private boolean loaded;

    /**
     * (numeric) Current tx count
     */
    private int size;

    /**
     * (numeric) Sum of all virtual transaction sizes as defined in BIP 141. Differs from actual serialized size because witness data is discounted
     */
    private int bytes;

    /**
     * (numeric) Total memory usage for the mempool
     */
    private int usage;

    /**
     * (numeric) Maximum memory usage for the mempool
     */
    @JsonProperty("maxmempool")
    private int maxMempool;

    /**
     * (numeric) Minimum fee rate in BTC/kB for tx to be accepted. Is the maximum of minrelaytxfee and minimum mempool fee
     */
    @JsonProperty("mempoolminfee")
    private double mempoolMinFee;

    /**
     * (numeric) Current minimum relay fee for transactions
     */
    @JsonProperty("minrelaytxfee")
    private double minRelayTxFee;

    /**
     * (numeric) minimum fee rate increment for mempool limiting or replacement in BTC/kvB
     */
    @JsonProperty("incrementalrelayfee")
    private double incrementalRelayFee;

    /**
     * (numeric) Current number of transactions that haven't passed initial broadcast yet
     */
    @JsonProperty("unbroadcastcount")
    private int unbroadcastCount;

    /**
     * (boolean) True if the mempool accepts RBF without replaceability signaling inspection
     */
    @JsonProperty("fullrbf")
    private boolean fullRbf;

    public boolean isLoaded() {
        return loaded;
    }

    public void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getBytes() {
        return bytes;
    }

    public void setBytes(int bytes) {
        this.bytes = bytes;
    }

    public int getUsage() {
        return usage;
    }

    public void setUsage(int usage) {
        this.usage = usage;
    }

    public int getMaxMempool() {
        return maxMempool;
    }

    public void setMaxMempool(int maxMempool) {
        this.maxMempool = maxMempool;
    }

    public double getMempoolMinFee() {
        return mempoolMinFee;
    }

    public void setMempoolMinFee(double mempoolMinFee) {
        this.mempoolMinFee = mempoolMinFee;
    }

    public double getMinRelayTxFee() {
        return minRelayTxFee;
    }

    public void setMinRelayTxFee(double minRelayTxFee) {
        this.minRelayTxFee = minRelayTxFee;
    }

    public double getIncrementalRelayFee() {
        return incrementalRelayFee;
    }

    public void setIncrementalRelayFee(double incrementalRelayFee) {
        this.incrementalRelayFee = incrementalRelayFee;
    }

    public int getUnbroadcastCount() {
        return unbroadcastCount;
    }

    public void setUnbroadcastCount(int unbroadcastCount) {
        this.unbroadcastCount = unbroadcastCount;
    }

    public boolean isFullRbf() {
        return fullRbf;
    }

    public void setFullRbf(boolean fullRbf) {
        this.fullRbf = fullRbf;
    }
}
