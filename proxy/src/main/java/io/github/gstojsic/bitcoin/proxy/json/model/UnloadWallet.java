package io.github.gstojsic.bitcoin.proxy.json.model;

public class UnloadWallet {
    /**
     * (string) Warning message if wallet was not unloaded cleanly.
     */
    private String warning;

    public String getWarning() {
        return warning;
    }

    public void setWarning(String warning) {
        this.warning = warning;
    }
}
