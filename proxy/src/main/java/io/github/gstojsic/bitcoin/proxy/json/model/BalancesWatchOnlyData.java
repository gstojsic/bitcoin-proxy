package io.github.gstojsic.bitcoin.proxy.json.model;

import io.github.gstojsic.bitcoin.proxy.json.annotation.JsonProperty;

public class BalancesWatchOnlyData {
    /**
     * (numeric) trusted balance (outputs created by the wallet or confirmed outputs)
     */
    private double trusted;

    /**
     * (numeric) untrusted pending balance (outputs created by others that are in the mempool)
     */
    @JsonProperty("untrusted_pending")
    private double untrustedPending;

    /**
     * (numeric) balance from immature coinbase outputs
     */
    private double immature;

    public double getTrusted() {
        return trusted;
    }

    public void setTrusted(double trusted) {
        this.trusted = trusted;
    }

    public double getUntrustedPending() {
        return untrustedPending;
    }

    public void setUntrustedPending(double untrustedPending) {
        this.untrustedPending = untrustedPending;
    }

    public double getImmature() {
        return immature;
    }

    public void setImmature(double immature) {
        this.immature = immature;
    }
}
