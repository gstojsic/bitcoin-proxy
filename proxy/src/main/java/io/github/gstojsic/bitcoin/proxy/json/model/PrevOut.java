package io.github.gstojsic.bitcoin.proxy.json.model;

public class PrevOut {
    /**
     * (boolean) Coinbase or not
     */
    private boolean generated;

    /**
     * (numeric) The height of the prevout
     */
    private int height;

    /**
     * (numeric) The value in BTC
     */
    private double value;

    /**
     * script public key
     */
    private ScriptPubKey scriptPubKey;

    public boolean isGenerated() {
        return generated;
    }

    public void setGenerated(boolean generated) {
        this.generated = generated;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
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
}