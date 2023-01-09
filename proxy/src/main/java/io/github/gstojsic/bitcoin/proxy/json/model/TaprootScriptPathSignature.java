package io.github.gstojsic.bitcoin.proxy.json.model;

import io.github.gstojsic.bitcoin.proxy.json.annotation.JsonProperty;

public class TaprootScriptPathSignature {

    /**
     * (string) The x-only pubkey for this signature
     */
    @JsonProperty("pubkey")
    private String pubKey;

    /**
     * (string) The leaf hash for this signature
     */
    @JsonProperty("leaf_hash")
    private String leafHash;

    /**
     * (string) The signature itself
     */
    @JsonProperty("sig")
    private String signature;

    public String getPubKey() {
        return pubKey;
    }

    public void setPubKey(String pubKey) {
        this.pubKey = pubKey;
    }

    public String getLeafHash() {
        return leafHash;
    }

    public void setLeafHash(String leafHash) {
        this.leafHash = leafHash;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}