package io.github.gstojsic.bitcoin.proxy.json.model;

import io.github.gstojsic.bitcoin.proxy.json.annotation.JsonProperty;

public class TransactionFunding {
    /**
     * (string) The resulting raw transaction (hex-encoded string)
     */
    private String hex;

    /**
     * (numeric) Fee in BTC the resulting transaction pays
     */
    private double fee;

    /**
     * (numeric) The position of the added change output, or -1
     */
    @JsonProperty("changepos")
    private int changePos;

    public String getHex() {
        return hex;
    }

    public void setHex(String hex) {
        this.hex = hex;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public int getChangePos() {
        return changePos;
    }

    public void setChangePos(int changePos) {
        this.changePos = changePos;
    }
}
