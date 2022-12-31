package io.github.gstojsic.bitcoin.proxy.json.model;

import io.github.gstojsic.bitcoin.proxy.json.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

public class PsbtOutputs {

    /**
     * redeem_script
     */
    @JsonProperty("redeem_script")
    private ScriptPubKey redeemScript;

    /**
     * witness_script
     */
    @JsonProperty("witness_script")
    private ScriptPubKey witnessScript;

    /**
     * bip32_derivs
     */
    @JsonProperty("bip32_derivs")
    private List<Bip32Data> bip32Derivs;

    /**
     * (json object, optional) The unknown global fields
     */
    private Map<String, String> unknown;

    /**
     * (json array, optional) The output proprietary map
     */
    private List<PsbtProprietaryData> proprietary;

    public ScriptPubKey getRedeemScript() {
        return redeemScript;
    }

    public void setRedeemScript(ScriptPubKey redeemScript) {
        this.redeemScript = redeemScript;
    }

    public ScriptPubKey getWitnessScript() {
        return witnessScript;
    }

    public void setWitnessScript(ScriptPubKey witnessScript) {
        this.witnessScript = witnessScript;
    }

    public List<Bip32Data> getBip32Derivs() {
        return bip32Derivs;
    }

    public void setBip32Derivs(List<Bip32Data> bip32Derivs) {
        this.bip32Derivs = bip32Derivs;
    }

    public Map<String, String> getUnknown() {
        return unknown;
    }

    public void setUnknown(Map<String, String> unknown) {
        this.unknown = unknown;
    }

    public List<PsbtProprietaryData> getProprietary() {
        return proprietary;
    }

    public void setProprietary(List<PsbtProprietaryData> proprietary) {
        this.proprietary = proprietary;
    }
}
