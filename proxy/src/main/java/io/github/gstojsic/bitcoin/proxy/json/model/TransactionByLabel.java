package io.github.gstojsic.bitcoin.proxy.json.model;

import io.github.gstojsic.bitcoin.proxy.json.annotation.JsonProperty;

public class TransactionByLabel {
    /**
     * (boolean, optional) Only returns true if imported addresses were involved in transaction
     */
    @JsonProperty("involvesWatchonly")
    private boolean involvesWatchOnly;

    /**
     * (numeric) The total amount received by addresses with this label
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

    public boolean isInvolvesWatchOnly() {
        return involvesWatchOnly;
    }

    public void setInvolvesWatchOnly(boolean involvesWatchOnly) {
        this.involvesWatchOnly = involvesWatchOnly;
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
}
