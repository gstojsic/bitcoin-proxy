package io.github.gstojsic.bitcoin.proxy.json.model;

public class FinalizedPsbt {
    /**
     * (string, optional) The base64-encoded partially signed transaction if not extracted
     */
    private String psbt;

    /**
     * (string, optional) The hex-encoded network transaction if extracted
     */
    private String hex;

    /**
     * (boolean) If the transaction has a complete set of signatures
     */
    private boolean complete;

    public String getPsbt() {
        return psbt;
    }

    public void setPsbt(String psbt) {
        this.psbt = psbt;
    }

    public String getHex() {
        return hex;
    }

    public void setHex(String hex) {
        this.hex = hex;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }
}