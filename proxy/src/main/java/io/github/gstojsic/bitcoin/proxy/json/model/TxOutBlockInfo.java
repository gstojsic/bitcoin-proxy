package io.github.gstojsic.bitcoin.proxy.json.model;

import io.github.gstojsic.bitcoin.proxy.json.annotation.JsonProperty;

public class TxOutBlockInfo {

    /**
     * (numeric) Total amount of all prevouts spent in this block
     */
    @JsonProperty("prevout_spent")
    private double prevoutSpent;

    /**
     * (numeric) Coinbase subsidy amount of this block
     */
    private double coinbase;

    /**
     * (numeric) Total amount of new outputs created by this block
     */
    @JsonProperty("new_outputs_ex_coinbase")
    private double newOutputsExCoinbase;

    /**
     * (numeric) Total amount of unspendable outputs created in this block
     */
    private double unspendable;

    /**
     * Detailed view of the unspendable categories
     */
    private TxOutUnspendables unspendables;

    public double getPrevoutSpent() {
        return prevoutSpent;
    }

    public void setPrevoutSpent(double prevoutSpent) {
        this.prevoutSpent = prevoutSpent;
    }

    public double getCoinbase() {
        return coinbase;
    }

    public void setCoinbase(double coinbase) {
        this.coinbase = coinbase;
    }

    public double getNewOutputsExCoinbase() {
        return newOutputsExCoinbase;
    }

    public void setNewOutputsExCoinbase(double newOutputsExCoinbase) {
        this.newOutputsExCoinbase = newOutputsExCoinbase;
    }

    public double getUnspendable() {
        return unspendable;
    }

    public void setUnspendable(double unspendable) {
        this.unspendable = unspendable;
    }

    public TxOutUnspendables getUnspendables() {
        return unspendables;
    }

    public void setUnspendables(TxOutUnspendables unspendables) {
        this.unspendables = unspendables;
    }
}