package io.github.gstojsic.bitcoin.proxy.json.model;

public class Signer {
    /**
     * (string) Master key fingerprint
     */
    private String fingerprint;

    /**
     * (string) Device name
     */
    private String name;

    public String getFingerprint() {
        return fingerprint;
    }

    public void setFingerprint(String fingerprint) {
        this.fingerprint = fingerprint;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
