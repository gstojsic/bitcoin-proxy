package io.github.gstojsic.bitcoin.proxy.json.model;

public class TxOutput {
    /**
     * (numeric) The value in BTC
     */
    private double value;

    /**
     * (numeric) index
     */
    private int n;

    /**
     * script public key
     */
    private ScriptPubKey scriptPubKey;

    /**
     * (boolean) Coinbase or not
     * see getblock help for verbose 3
     */
    private Boolean generated;

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public ScriptPubKey getScriptPubKey() {
        return scriptPubKey;
    }

    public void setScriptPubKey(ScriptPubKey scriptPubKey) {
        this.scriptPubKey = scriptPubKey;
    }

    public Boolean getGenerated() {
        return generated;
    }

    public void setGenerated(Boolean generated) {
        this.generated = generated;
    }
}