package io.github.gstojsic.bitcoin.proxy.json.model;

import io.github.gstojsic.bitcoin.proxy.json.annotation.JsonProperty;

public class ExtendedPublicKey {
    /**
     * (string) The extended public key this path corresponds to
     */
    private String xpub;

    /**
     * (string) The extended public key this path corresponds to
     */
    @JsonProperty("master_fingerprint")
    private String masterFingerprint;

    /**
     * (string) The path
     */
    private String path;

    public String getXpub() {
        return xpub;
    }

    public void setXpub(String xpub) {
        this.xpub = xpub;
    }

    public String getMasterFingerprint() {
        return masterFingerprint;
    }

    public void setMasterFingerprint(String masterFingerprint) {
        this.masterFingerprint = masterFingerprint;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}