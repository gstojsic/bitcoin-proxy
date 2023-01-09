package io.github.gstojsic.bitcoin.proxy.json.model;

import io.github.gstojsic.bitcoin.proxy.json.annotation.JsonProperty;

public class SimulateRawTransactionInfo {

    /**
     * (numeric) The wallet balance change (negative means decrease)
     */
    @JsonProperty("balance_change")
    private double balanceChange;

    public double getBalanceChange() {
        return balanceChange;
    }

    public void setBalanceChange(double balanceChange) {
        this.balanceChange = balanceChange;
    }
}