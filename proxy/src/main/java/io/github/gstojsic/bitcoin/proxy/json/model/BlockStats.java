package io.github.gstojsic.bitcoin.proxy.json.model;

import io.github.gstojsic.bitcoin.proxy.json.annotation.JsonProperty;

import java.util.List;

public class BlockStats {
    /**
     * (numeric) Average fee in the block
     */
    @JsonProperty("avgfee")
    private int avgFee;

    /**
     * (numeric) Average feerate (in satoshis per virtual byte)
     */
    @JsonProperty("avgfeerate")
    private int avgFeeRate;

    /**
     * (numeric) Average transaction size
     */
    @JsonProperty("avgtxsize")
    private int avgTxSize;

    /**
     * string) The block hash (to check for potential reorgs)
     */
    private String blockhash;

    /**
     * (json array) Feerates at the 10th, 25th, 50th, 75th, and 90th percentile weight unit (in satoshis per virtual byte)
     */
    @JsonProperty("feerate_percentiles")
    private List<Integer> feeRatePercentiles;

    /**
     * (numeric) The height of the block
     */
    private int height;

    /**
     * (numeric) The number of inputs (excluding coinbase)
     */
    private int ins;

    /**
     * (numeric) Maximum fee in the block
     */
    @JsonProperty("maxfee")
    private int maxFee;

    /**
     * (numeric) Maximum feerate (in satoshis per virtual byte)
     */
    @JsonProperty("maxfeerate")
    private int maxFeeRate;

    /**
     * (numeric) Maximum transaction size
     */
    @JsonProperty("maxtxsize")
    private int maxTxSize;

    /**
     * (numeric) Truncated median fee in the block
     */
    @JsonProperty("medianfee")
    private int medianFee;

    /**
     * (numeric) The block median time past
     */
    @JsonProperty("mediantime")
    private long medianTime;

    /**
     * (numeric) Truncated median transaction size
     */
    @JsonProperty("mediantxsize")
    private int medianTxSize;

    /**
     * (numeric) Minimum fee in the block
     */
    @JsonProperty("minfee")
    private int minFee;

    /**
     * (numeric) Minimum feerate (in satoshis per virtual byte)
     */
    @JsonProperty("minfeerate")
    private int minFeeRate;

    /**
     * (numeric) Minimum transaction size
     */
    @JsonProperty("mintxsize")
    private int minTxSize;

    /**
     * (numeric) The number of outputs
     */
    private int outs;

    /**
     * (numeric) The block subsidy
     */
    private long subsidy;

    /**
     * (numeric) Total size of all segwit transactions
     */
    @JsonProperty("swtotal_size")
    private int swTotalSize;

    /**
     * (numeric) Total weight of all segwit transactions
     */
    @JsonProperty("swtotal_weight")
    private int swTotalWeight;

    /**
     * (numeric) The number of segwit transactions
     */
    @JsonProperty("swtxs")
    private int swTxs;

    /**
     * (numeric) The block time
     */
    private long time;

    /**
     * (numeric) Total amount in all outputs (excluding coinbase and thus reward [ie subsidy + totalfee])
     */
    @JsonProperty("total_out")
    private long totalOut;

    /**
     * (numeric) Total size of all non-coinbase transactions
     */
    @JsonProperty("total_size")
    private int totalSize;

    /**
     * (numeric) Total weight of all non-coinbase transactions
     */
    @JsonProperty("total_weight")
    private int totalWeight;

    /**
     * (numeric) The fee total
     */
    @JsonProperty("totalfee")
    private int totalFee;

    /**
     * (numeric) The number of transactions (including coinbase)
     */
    private int txs;

    /**
     * (numeric) The increase/decrease in the number of unspent outputs
     */
    @JsonProperty("utxo_increase")
    private int utxoIncrease;

    /**
     * (numeric) The increase/decrease in size for the utxo index (not discounting op_return and similar)
     */
    @JsonProperty("utxo_size_inc")
    private int utxoSizeInc;

    public int getAvgFee() {
        return avgFee;
    }

    public void setAvgFee(int avgFee) {
        this.avgFee = avgFee;
    }

    public int getAvgFeeRate() {
        return avgFeeRate;
    }

    public void setAvgFeeRate(int avgFeeRate) {
        this.avgFeeRate = avgFeeRate;
    }

    public int getAvgTxSize() {
        return avgTxSize;
    }

    public void setAvgTxSize(int avgTxSize) {
        this.avgTxSize = avgTxSize;
    }

    public String getBlockhash() {
        return blockhash;
    }

    public void setBlockhash(String blockhash) {
        this.blockhash = blockhash;
    }

    public List<Integer> getFeeRatePercentiles() {
        return feeRatePercentiles;
    }

    public void setFeeRatePercentiles(List<Integer> feeRatePercentiles) {
        this.feeRatePercentiles = feeRatePercentiles;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getIns() {
        return ins;
    }

    public void setIns(int ins) {
        this.ins = ins;
    }

    public int getMaxFee() {
        return maxFee;
    }

    public void setMaxFee(int maxFee) {
        this.maxFee = maxFee;
    }

    public int getMaxFeeRate() {
        return maxFeeRate;
    }

    public void setMaxFeeRate(int maxFeeRate) {
        this.maxFeeRate = maxFeeRate;
    }

    public int getMaxTxSize() {
        return maxTxSize;
    }

    public void setMaxTxSize(int maxTxSize) {
        this.maxTxSize = maxTxSize;
    }

    public int getMedianFee() {
        return medianFee;
    }

    public void setMedianFee(int medianFee) {
        this.medianFee = medianFee;
    }

    public long getMedianTime() {
        return medianTime;
    }

    public void setMedianTime(long medianTime) {
        this.medianTime = medianTime;
    }

    public int getMedianTxSize() {
        return medianTxSize;
    }

    public void setMedianTxSize(int medianTxSize) {
        this.medianTxSize = medianTxSize;
    }

    public int getMinFee() {
        return minFee;
    }

    public void setMinFee(int minFee) {
        this.minFee = minFee;
    }

    public int getMinFeeRate() {
        return minFeeRate;
    }

    public void setMinFeeRate(int minFeeRate) {
        this.minFeeRate = minFeeRate;
    }

    public int getMinTxSize() {
        return minTxSize;
    }

    public void setMinTxSize(int minTxSize) {
        this.minTxSize = minTxSize;
    }

    public int getOuts() {
        return outs;
    }

    public void setOuts(int outs) {
        this.outs = outs;
    }

    public long getSubsidy() {
        return subsidy;
    }

    public void setSubsidy(long subsidy) {
        this.subsidy = subsidy;
    }

    public int getSwTotalSize() {
        return swTotalSize;
    }

    public void setSwTotalSize(int swTotalSize) {
        this.swTotalSize = swTotalSize;
    }

    public int getSwTotalWeight() {
        return swTotalWeight;
    }

    public void setSwTotalWeight(int swTotalWeight) {
        this.swTotalWeight = swTotalWeight;
    }

    public int getSwTxs() {
        return swTxs;
    }

    public void setSwTxs(int swTxs) {
        this.swTxs = swTxs;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getTotalOut() {
        return totalOut;
    }

    public void setTotalOut(long totalOut) {
        this.totalOut = totalOut;
    }

    public int getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }

    public int getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(int totalWeight) {
        this.totalWeight = totalWeight;
    }

    public int getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(int totalFee) {
        this.totalFee = totalFee;
    }

    public int getTxs() {
        return txs;
    }

    public void setTxs(int txs) {
        this.txs = txs;
    }

    public int getUtxoIncrease() {
        return utxoIncrease;
    }

    public void setUtxoIncrease(int utxoIncrease) {
        this.utxoIncrease = utxoIncrease;
    }

    public int getUtxoSizeInc() {
        return utxoSizeInc;
    }

    public void setUtxoSizeInc(int utxoSizeInc) {
        this.utxoSizeInc = utxoSizeInc;
    }
}