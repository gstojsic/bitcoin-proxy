package io.github.gstojsic.bitcoin.proxy.json.model;

import io.github.gstojsic.bitcoin.proxy.json.annotation.JsonProperty;

public class UtxoKey {
    /**
     * (string) The transaction id locked
     */
    @JsonProperty("txid")
    private String txId;

    /**
     * (numeric) The vout value
     */
    private int vout;

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
}
