package io.github.gstojsic.bitcoin.proxy.json.model;

public class PsbtTxOutput {
    /**
     * (numeric) The value in BTC
     */
    private double amount;

    /**
     * script public key
     */
    private ScriptPubKey scriptPubKey;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public ScriptPubKey getScriptPubKey() {
        return scriptPubKey;
    }

    public void setScriptPubKey(ScriptPubKey scriptPubKey) {
        this.scriptPubKey = scriptPubKey;
    }
}
