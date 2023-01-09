package io.github.gstojsic.bitcoin.proxy.json.model;

import io.github.gstojsic.bitcoin.proxy.json.annotation.JsonProperty;

import java.util.List;

public class TaprootBip32Deriv {

    /**
     * (string) The x-only public key this path corresponds to
     */
    @JsonProperty("pubkey")
    private String pubKey;

    /**
     * (string) The fingerprint of the master key
     */
    @JsonProperty("master_fingerprint")
    private String masterFingerprint;

    /**
     * (string) The path
     */
    private String path;

    /**
     * The hashes of the leaves this pubkey appears in
     */
    @JsonProperty("leaf_hashes")
    private List<String> leafHashes;

    public String getPubKey() {
        return pubKey;
    }

    public void setPubKey(String pubKey) {
        this.pubKey = pubKey;
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

    public List<String> getLeafHashes() {
        return leafHashes;
    }

    public void setLeafHashes(List<String> leafHashes) {
        this.leafHashes = leafHashes;
    }
}