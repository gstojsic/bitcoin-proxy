package io.github.gstojsic.bitcoin.proxy.json.model;

import io.github.gstojsic.bitcoin.proxy.json.annotation.JsonProperty;

import java.util.List;

public class PsbtAnalysis {
    /**
     * (json array, optional) psbt inputs
     */
    private List<PsbtAnalysisInput> inputs;

    /**
     * (numeric, optional) Estimated vsize of the final signed transaction
     */
    @JsonProperty("estimated_vsize")
    private Integer estimatedVsize;

    /**
     * (numeric, optional) Estimated feerate of the final signed transaction in BTC/kvB. Shown only if all UTXO slots in the PSBT have been filled
     */
    @JsonProperty("estimated_feerate")
    private Double estimatedFeeRate;

    /**
     * (numeric, optional) The transaction fee paid. Shown only if all UTXO slots in the PSBT have been filled
     */
    private Double fee;

    /**
     * (string) Role of the next person that this psbt needs to go to
     */
    private String next;

    /**
     * (string, optional) Error message (if there is one)
     */
    private String error;

    public List<PsbtAnalysisInput> getInputs() {
        return inputs;
    }

    public void setInputs(List<PsbtAnalysisInput> inputs) {
        this.inputs = inputs;
    }

    public Integer getEstimatedVsize() {
        return estimatedVsize;
    }

    public void setEstimatedVsize(Integer estimatedVsize) {
        this.estimatedVsize = estimatedVsize;
    }

    public Double getEstimatedFeeRate() {
        return estimatedFeeRate;
    }

    public void setEstimatedFeeRate(Double estimatedFeeRate) {
        this.estimatedFeeRate = estimatedFeeRate;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
