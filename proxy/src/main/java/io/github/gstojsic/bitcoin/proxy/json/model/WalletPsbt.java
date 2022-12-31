package io.github.gstojsic.bitcoin.proxy.json.model;

public class WalletPsbt {
    /**
     * (string) The base64-encoded partially signed transaction
     */
    private String psbt;

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

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }
}
