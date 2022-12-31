package io.github.gstojsic.bitcoin.proxy.json.model;

import io.github.gstojsic.bitcoin.proxy.json.annotation.JsonProperty;

public class WalletFundedPsbt {
    /**
     * (string) The resulting raw transaction (base64-encoded string
     */
    private String psbt;

    /**
     * (numeric) Fee in BTC the resulting transaction pays
     */
    private double fee;

    /**
     * (numeric) The position of the added change output, or -1
     */
    @JsonProperty("changepos")
    private int changePos;

    public String getPsbt() {
        return psbt;
    }

    public void setPsbt(String psbt) {
        this.psbt = psbt;
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
