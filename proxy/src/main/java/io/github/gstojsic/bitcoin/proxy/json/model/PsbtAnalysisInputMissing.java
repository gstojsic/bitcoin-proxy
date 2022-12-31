package io.github.gstojsic.bitcoin.proxy.json.model;

import io.github.gstojsic.bitcoin.proxy.json.annotation.JsonProperty;

import java.util.List;

public class PsbtAnalysisInputMissing {
    /**
     * (json array, optional)
     * (string) Public key ID, hash160 of the public key, of a public key whose BIP 32 derivation path is missing
     */
    @JsonProperty("pubkeys")
    private List<String> pubKeys;

    /**
     * (json array, optional)
     * (string) Public key ID, hash160 of the public key, of a public key whose signature is missing
     */
    private List<String> signatures;

    /**
     * (string, optional) Hash160 of the redeemScript that is missing
     */
    @JsonProperty("redeemscript")
    private String redeemScript;

    /**
     * (string, optional) SHA256 of the witnessScript that is missing
     */
    @JsonProperty("witnessscript")
    private String witnessScript;

    public List<String> getPubKeys() {
        return pubKeys;
    }

    public void setPubKeys(List<String> pubKeys) {
        this.pubKeys = pubKeys;
    }

    public List<String> getSignatures() {
        return signatures;
    }

    public void setSignatures(List<String> signatures) {
        this.signatures = signatures;
    }

    public String getRedeemScript() {
        return redeemScript;
    }

    public void setRedeemScript(String redeemScript) {
        this.redeemScript = redeemScript;
    }

    public String getWitnessScript() {
        return witnessScript;
    }

    public void setWitnessScript(String witnessScript) {
        this.witnessScript = witnessScript;
    }
}
