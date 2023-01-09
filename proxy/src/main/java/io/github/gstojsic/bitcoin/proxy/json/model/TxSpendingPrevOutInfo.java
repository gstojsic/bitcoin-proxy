package io.github.gstojsic.bitcoin.proxy.json.model;

import io.github.gstojsic.bitcoin.proxy.json.annotation.JsonProperty;

public class TxSpendingPrevOutInfo {

    /**
     * (string) the transaction id of the checked output
     */
    @JsonProperty("txid")
    private String txId;

    /**
     * (numeric) the vout value of the checked output
     */
    private int vout;

    /**
     * (string, optional) the transaction id of the mempool transaction spending this output (omitted if unspent)
     */
    @JsonProperty("spendingtxid")
    private String spendingTxId;

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

    public String getSpendingTxId() {
        return spendingTxId;
    }

    public void setSpendingTxId(String spendingTxId) {
        this.spendingTxId = spendingTxId;
    }
}