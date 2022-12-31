package io.github.gstojsic.bitcoin.proxy.json.model;

import io.github.gstojsic.bitcoin.proxy.json.annotation.JsonProperty;

public class TransactionOutput {
    /**
     * (string) The hash of the block at the tip of the chain
     */
    @JsonProperty("bestblock")
    private String bestBlock;

    /**
     * numeric) The number of confirmations
     */
    private int confirmations;

    /**
     * (numeric) The transaction value in BTC
     */
    private double value;

    /**
     * (json object)
     */
    private ScriptPubKey scriptPubKey;

    /**
     * (boolean) Coinbase or not
     */
    private boolean coinbase;

    public String getBestBlock() {
        return bestBlock;
    }

    public void setBestBlock(String bestBlock) {
        this.bestBlock = bestBlock;
    }

    public int getConfirmations() {
        return confirmations;
    }

    public void setConfirmations(int confirmations) {
        this.confirmations = confirmations;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public ScriptPubKey getScriptPubKey() {
        return scriptPubKey;
    }

    public void setScriptPubKey(ScriptPubKey scriptPubKey) {
        this.scriptPubKey = scriptPubKey;
    }

    public boolean isCoinbase() {
        return coinbase;
    }

    public void setCoinbase(boolean coinbase) {
        this.coinbase = coinbase;
    }
}