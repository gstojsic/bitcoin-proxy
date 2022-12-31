package io.github.gstojsic.bitcoin.proxy.json.model;

import io.github.gstojsic.bitcoin.proxy.json.annotation.JsonProperty;

import java.util.List;

public class FeeEstimate {
    /**
     * (numeric, optional) estimate fee rate in BTC/kvB (only present if no errors were encountered)
     */
    @JsonProperty("feerate")
    private Double feeRate;

    /**
     * (json array, optional) Errors encountered during processing (if there are any)
     */
    private List<String> errors;

    /**
     * (numeric) block number where estimate was found
     * The request target will be clamped between 2 and the highest target
     * fee estimation is able to return based on how long it has been running.
     * An error is returned if not enough transactions and blocks
     * have been observed to make an estimate for any number of blocks.
     */
    private int blocks;

    public Double getFeeRate() {
        return feeRate;
    }

    public void setFeeRate(Double feeRate) {
        this.feeRate = feeRate;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public int getBlocks() {
        return blocks;
    }

    public void setBlocks(int blocks) {
        this.blocks = blocks;
    }
}
