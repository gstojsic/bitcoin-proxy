package io.github.gstojsic.bitcoin.proxy.json.model;

import io.github.gstojsic.bitcoin.proxy.json.annotation.JsonProperty;

import java.util.List;

public class PsbtBumpFee {
    /**
     * (string) The base64-encoded unsigned PSBT of the new transaction.
     */
    private String psbt;

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

    public String getPsbt() {
        return psbt;
    }

    public void setPsbt(String psbt) {
        this.psbt = psbt;
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