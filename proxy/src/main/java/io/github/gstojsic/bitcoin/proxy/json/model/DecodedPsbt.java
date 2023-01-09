package io.github.gstojsic.bitcoin.proxy.json.model;

import io.github.gstojsic.bitcoin.proxy.json.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

public class DecodedPsbt {
    /**
     * (json object) The decoded network-serialized unsigned transaction.
     * The layout is the same as the output of decoderawtransaction.
     */
    private RawTransaction tx;

    /**
     * (string) The extended public keys
     */
    @JsonProperty("global_xpubs")
    private List<ExtendedPublicKey> globalXPubs;

    /**
     * (numeric) The PSBT version number. Not to be confused with the unsigned transaction version
     */
    @JsonProperty("psbt_version")
    private long psbtVersion;

    /**
     * (json array) The global proprietary map
     */
    private List<PsbtProprietaryData> proprietary;

    /**
     * (json object) The unknown output fields
     */
    private Map<String, String> unknown;

    /**
     * see PsbtInputs
     */
    private List<PsbtInputs> inputs;

    /**
     * see PsbtOutputs
     */
    private List<PsbtOutputs> outputs;

    /**
     * (numeric, optional) The transaction fee paid if all UTXOs slots in the PSBT have been filled.
     */
    private Double fee;

    public RawTransaction getTx() {
        return tx;
    }

    public void setTx(RawTransaction tx) {
        this.tx = tx;
    }

    public List<ExtendedPublicKey> getGlobalXPubs() {
        return globalXPubs;
    }

    public void setGlobalXPubs(List<ExtendedPublicKey> globalXPubs) {
        this.globalXPubs = globalXPubs;
    }

    public long getPsbtVersion() {
        return psbtVersion;
    }

    public void setPsbtVersion(long psbtVersion) {
        this.psbtVersion = psbtVersion;
    }

    public List<PsbtProprietaryData> getProprietary() {
        return proprietary;
    }

    public void setProprietary(List<PsbtProprietaryData> proprietary) {
        this.proprietary = proprietary;
    }

    public Map<String, String> getUnknown() {
        return unknown;
    }

    public void setUnknown(Map<String, String> unknown) {
        this.unknown = unknown;
    }

    public List<PsbtInputs> getInputs() {
        return inputs;
    }

    public void setInputs(List<PsbtInputs> inputs) {
        this.inputs = inputs;
    }

    public List<PsbtOutputs> getOutputs() {
        return outputs;
    }

    public void setOutputs(List<PsbtOutputs> outputs) {
        this.outputs = outputs;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }
}