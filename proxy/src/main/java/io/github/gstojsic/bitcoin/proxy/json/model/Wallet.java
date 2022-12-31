package io.github.gstojsic.bitcoin.proxy.json.model;

public class Wallet {
    /**
     * (string) The wallet name if created successfully. If the wallet was created using a full path, the wallet_name will be the full path.
     */
    private String name;

    /**
     * (string) Warning message if wallet was not loaded cleanly.
     */
    private String warning;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWarning() {
        return warning;
    }

    public void setWarning(String warning) {
        this.warning = warning;
    }
}
