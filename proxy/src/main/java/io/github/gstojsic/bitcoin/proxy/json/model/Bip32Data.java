package io.github.gstojsic.bitcoin.proxy.json.model;

import io.github.gstojsic.bitcoin.proxy.json.annotation.JsonProperty;

public class Bip32Data {
    /**
     * (string) The public key this path corresponds to
     */
    private String pubkey;

    /**
     * (string) The fingerprint of the master key
     */
    @JsonProperty("master_fingerprint")
    private String masterFingerprint;

    /**
     * (string) The path
     */
    private String path;

    public String getPubkey() {
        return pubkey;
    }

    public void setPubkey(String pubkey) {
        this.pubkey = pubkey;
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
