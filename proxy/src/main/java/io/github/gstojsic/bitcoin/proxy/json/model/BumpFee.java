package io.github.gstojsic.bitcoin.proxy.json.model;

import io.github.gstojsic.bitcoin.proxy.json.annotation.JsonProperty;

import java.util.List;

public class BumpFee {
    /**
     * (string) The id of the new transaction.
     */
    @JsonProperty("txid")
    private String txId;

    /**
     * (numeric) The fee of the replaced transaction.
     */
    @JsonProperty("origfee")
    private double origFee;

    /**
     * (numeric) The fee of the new transaction.
     */
    private double fee;

    /**
     * (json array) Errors encountered during processing (may be empty).
     */
    private List<String> errors;

    public String getTxId() {
        return txId;
    }

    public void setTxId(String txId) {
        this.txId = txId;
    }

    public double getOrigFee() {
        return origFee;
    }

    public void setOrigFee(double origFee) {
        this.origFee = origFee;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
