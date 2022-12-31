package io.github.gstojsic.bitcoin.proxy.json.model;

import io.github.gstojsic.bitcoin.proxy.json.annotation.JsonProperty;

import java.util.List;

public class BalanceByAddress {
    /**
     * (boolean) Only returns true if imported addresses were involved in transaction
     */
    @JsonProperty("involvesWatchonly")
    private boolean involvesWatchOnly;

    /**
     * (string) The receiving address
     */
    private String address;

    /**
     * (numeric) The total amount in BTC received by the address
     */
    private double amount;

    /**
     * (numeric) The number of confirmations of the most recent transaction included
     */
    private int confirmations;

    /**
     * (string) The label of the receiving address. The default label is ""
     */
    private String label;

    @JsonProperty("txids")
    private List<String> txIds;

    public boolean isInvolvesWatchOnly() {
        return involvesWatchOnly;
    }

    public void setInvolvesWatchOnly(boolean involvesWatchOnly) {
        this.involvesWatchOnly = involvesWatchOnly;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getConfirmations() {
        return confirmations;
    }

    public void setConfirmations(int confirmations) {
        this.confirmations = confirmations;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<String> getTxIds() {
        return txIds;
    }

    public void setTxIds(List<String> txIds) {
        this.txIds = txIds;
    }
}