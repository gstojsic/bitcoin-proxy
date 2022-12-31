package io.github.gstojsic.bitcoin.proxy.json.model;

import io.github.gstojsic.bitcoin.proxy.json.annotation.JsonProperty;

public class UpgradeWallet {
    /**
     * (string) Name of wallet this operation was performed on
     */
    @JsonProperty("wallet_name")
    private String walletName;

    /**
     * (numeric) Version of wallet before this operation
     */
    @JsonProperty("previous_version")
    private int previousVersion;

    /**
     * (numeric) Version of wallet after this operation
     */
    @JsonProperty("current_version")
    private int currentVersion;

    /**
     * (string, optional) Description of result, if no error
     */
    private String result;

    /**
     * (string, optional) Error message (if there is one)
     */
    private String error;

    public String getWalletName() {
        return walletName;
    }

    public void setWalletName(String walletName) {
        this.walletName = walletName;
    }

    public int getPreviousVersion() {
        return previousVersion;
    }

    public void setPreviousVersion(int previousVersion) {
        this.previousVersion = previousVersion;
    }

    public int getCurrentVersion() {
        return currentVersion;
    }

    public void setCurrentVersion(int currentVersion) {
        this.currentVersion = currentVersion;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
