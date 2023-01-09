package io.github.gstojsic.bitcoin.proxy.json.model;

import io.github.gstojsic.bitcoin.proxy.json.annotation.JsonProperty;

import java.util.List;

public class TxInput {

    /**
     * (string, optional) The coinbase value (only if coinbase transaction)
     */
    private String coinbase;

    /**
     * (string, optional) The transaction id (if not coinbase transaction)
     */
    @JsonProperty("txid")
    private String txId;

    /**
     * (numeric, optional) The output number (if not coinbase transaction)
     */
    private int vout;

    /**
     * (json object) The script (if not coinbase transaction)
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
    private List<PrevOut> prevOut;

    public String getCoinbase() {
        return coinbase;
    }

    public void setCoinbase(String coinbase) {
        this.coinbase = coinbase;
    }

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

    public List<PrevOut> getPrevOut() {
        return prevOut;
    }

    public void setPrevOut(List<PrevOut> prevOut) {
        this.prevOut = prevOut;
    }
}