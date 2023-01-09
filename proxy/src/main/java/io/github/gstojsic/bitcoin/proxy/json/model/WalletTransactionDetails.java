package io.github.gstojsic.bitcoin.proxy.json.model;

import io.github.gstojsic.bitcoin.proxy.json.annotation.JsonProperty;

import java.util.List;

public class WalletTransactionDetails {

    @JsonProperty("involvesWatchonly")
    private Boolean involvesWatchOnly;

    /**
     * (string) The bitcoin address of the transaction.
     */
    private String address;

    /**
     * (string) The transaction category.
     * "send"      Transactions sent.
     * "receive"   Non-coinbase transactions received.
     * "generate"  Coinbase transactions received with more than 100 confirmations.
     * "immature"  Coinbase transactions received with 100 or fewer confirmations.
     * "orphan"    Orphaned coinbase transactions received.
     */
    private String category;

    /**
     * (numeric) The amount in BTC. This is negative for the 'send' category, and is positive for all other categories
     */
    private double amount;

    /**
     * (string, optional) A comment for the address/transaction, if any
     */
    private String label;

    /**
     * (numeric) the vout value
     */
    @JsonProperty("vout")
    private int VOut;

    /**
     * (numeric, optional) The amount of the fee in BTC. This is negative and only available for the
     * 'send' category of transactions.
     */
    private Double fee;

    /**
     * (boolean, optional) 'true' if the transaction has been abandoned (inputs are respendable). Only available for the
     * 'send' category of transactions.
     */
    private Boolean abandoned;

    /**
     * Only if 'category' is 'received'. List of parent descriptors for the scriptPubKey of this coin.
     */
    @JsonProperty("parent_descs")
    private List<String> parentDescs;

    public Boolean getInvolvesWatchOnly() {
        return involvesWatchOnly;
    }

    public void setInvolvesWatchOnly(Boolean involvesWatchOnly) {
        this.involvesWatchOnly = involvesWatchOnly;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getVOut() {
        return VOut;
    }

    public void setVOut(int VOut) {
        this.VOut = VOut;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public Boolean getAbandoned() {
        return abandoned;
    }

    public void setAbandoned(Boolean abandoned) {
        this.abandoned = abandoned;
    }

    public List<String> getParentDescs() {
        return parentDescs;
    }

    public void setParentDescs(List<String> parentDescs) {
        this.parentDescs = parentDescs;
    }
}
