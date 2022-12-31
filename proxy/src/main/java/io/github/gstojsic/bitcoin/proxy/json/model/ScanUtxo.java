package io.github.gstojsic.bitcoin.proxy.json.model;

import io.github.gstojsic.bitcoin.proxy.json.annotation.JsonProperty;

public class ScanUtxo {
    /**
     * (string) the transaction id
     */
    @JsonProperty("txid")
    private String txId;

    /**
     * (numeric) the vout value
     */
    private int vout;

    /**
     * (string) the script key
     */
    private String scriptPubKey;

    /**
     * (string) A specialized descriptor for the matched scriptPubKey
     */
    private String desc;

    /**
     * (numeric) The total amount in BTC of the unspent output
     */
    private double amount;

    /**
     * (numeric) Height of the unspent transaction output
     */
    private int height;

    public String getTxId() {
        return txId;
    }

    public void setTxId(String txId) {
        this.txId = txId;
    }

    public int getVout() {
        return vout;
    }

    public void setVout(int vout) {
        this.vout = vout;
    }

    public String getScriptPubKey() {
        return scriptPubKey;
    }

    public void setScriptPubKey(String scriptPubKey) {
        this.scriptPubKey = scriptPubKey;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
