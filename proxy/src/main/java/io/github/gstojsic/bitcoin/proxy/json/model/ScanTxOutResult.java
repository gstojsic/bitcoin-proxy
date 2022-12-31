package io.github.gstojsic.bitcoin.proxy.json.model;

import io.github.gstojsic.bitcoin.proxy.json.annotation.JsonProperty;

import java.util.List;

public class ScanTxOutResult {
    /**
     * (boolean) Whether the scan was completed
     */
    private boolean success;

    /**
     * (numeric) The number of unspent transaction outputs scanned
     */
    @JsonProperty("txouts")
    private int txOuts;

    /**
     * (numeric) The current block height (index)
     */
    private int height;

    /**
     * (string) The hash of the block at the tip of the chain
     */
    @JsonProperty("bestblock")
    private String bestBlock;

    /**
     * utxo-s
     */
    private List<ScanUtxo> unspents;

    /**
     * (numeric) The total amount of all found unspent outputs in BTC
     */
    @JsonProperty("total_amount")
    private double totalAmount;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getTxOuts() {
        return txOuts;
    }

    public void setTxOuts(int txOuts) {
        this.txOuts = txOuts;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getBestBlock() {
        return bestBlock;
    }

    public void setBestBlock(String bestBlock) {
        this.bestBlock = bestBlock;
    }

    public List<ScanUtxo> getUnspents() {
        return unspents;
    }

    public void setUnspents(List<ScanUtxo> unspents) {
        this.unspents = unspents;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
