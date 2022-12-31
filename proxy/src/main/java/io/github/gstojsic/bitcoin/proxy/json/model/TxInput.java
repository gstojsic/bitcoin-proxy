package io.github.gstojsic.bitcoin.proxy.json.model;

import io.github.gstojsic.bitcoin.proxy.json.annotation.JsonProperty;

import java.util.List;

public class TxInput {

    /**
     * (string) The transaction id
     */
    @JsonProperty("txid")
    private String txId;

    /**
     * (numeric) The output number
     */
    private int vout;

    /**
     * (json object) The script
     */
    private ScriptSig scriptSig;

    /**
     * (numeric) The script sequence number
     */
    private long sequence;

    /**
     * (json array, optional)
     * contains (string) hex-encoded witness data (if any)
     */
    @JsonProperty("txinwitness")
    private List<String> txInWitness;

    /**
     * (json object) (Only if undo information is available)
     * see getblock help for verbose 3
     */
    @JsonProperty("prevout")
    private List<String> prevOut;

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

    public ScriptSig getScriptSig() {
        return scriptSig;
    }

    public void setScriptSig(ScriptSig scriptSig) {
        this.scriptSig = scriptSig;
    }

    public long getSequence() {
        return sequence;
    }

    public void setSequence(long sequence) {
        this.sequence = sequence;
    }

    public List<String> getTxInWitness() {
        return txInWitness;
    }

    public void setTxInWitness(List<String> txInWitness) {
        this.txInWitness = txInWitness;
    }

    public List<String> getPrevOut() {
        return prevOut;
    }

    public void setPrevOut(List<String> prevOut) {
        this.prevOut = prevOut;
    }
}