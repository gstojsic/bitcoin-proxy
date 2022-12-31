package io.github.gstojsic.bitcoin.proxy.json.model;

import io.github.gstojsic.bitcoin.proxy.json.annotation.JsonProperty;

import java.util.List;

public class SignTransactionError {
    /**
     * (string) The hash of the referenced, previous transaction
     */
    @JsonProperty("txid")
    private String txId;

    /**
     * (numeric) The index of the output to spent and used as input
     */
    private int vout;

    /**
     * (json array)
     */
    private List<String> witness;

    /**
     * (string) The hex-encoded signature script
     */
    private String scriptSig;

    /**
     * (numeric) Script sequence number
     */
    private long sequence;

    /**
     * (string) Verification or signing error related to the input
     */
    private String error;

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

    public List<String> getWitness() {
        return witness;
    }

    public void setWitness(List<String> witness) {
        this.witness = witness;
    }

    public String getScriptSig() {
        return scriptSig;
    }

    public void setScriptSig(String scriptSig) {
        this.scriptSig = scriptSig;
    }

    public long getSequence() {
        return sequence;
    }

    public void setSequence(long sequence) {
        this.sequence = sequence;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
