package io.github.gstojsic.bitcoin.proxy.json.model;

import io.github.gstojsic.bitcoin.proxy.json.annotation.JsonProperty;

import java.util.List;

public class AddressValidation {
    /**
     * (boolean) If the address is valid or not
     */
    @JsonProperty("isvalid")
    private boolean valid;

    /**
     * (string, optional) The bitcoin address validated
     */
    private String address;

    /**
     * (string, optional) The hex-encoded scriptPubKey generated by the address
     */
    private String scriptPubKey;

    /**
     * (boolean, optional) If the key is a script
     */
    @JsonProperty("isscript")
    private Boolean script;

    /**
     * (boolean, optional) If the address is a witness address
     */
    @JsonProperty("iswitness")
    private Boolean witness;

    /**
     * (numeric, optional) The version number of the witness program.
     */
    @JsonProperty("witness_version")
    private Integer witnessVersion;

    /**
     * (string, optional) The hex value of the witness program.
     */
    @JsonProperty("witness_program")
    private Integer witnessProgram;

    /**
     * (string, optional) Error message, if any
     */
    private String error;

    /**
     * (json array, optional) Indices of likely error locations in address, if known (e.g. Bech32 errors)
     */
    @JsonProperty("error_locations")
    private List<Integer> errorLocations;

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getScriptPubKey() {
        return scriptPubKey;
    }

    public void setScriptPubKey(String scriptPubKey) {
        this.scriptPubKey = scriptPubKey;
    }

    public Boolean getScript() {
        return script;
    }

    public void setScript(Boolean script) {
        this.script = script;
    }

    public Boolean getWitness() {
        return witness;
    }

    public void setWitness(Boolean witness) {
        this.witness = witness;
    }

    public Integer getWitnessVersion() {
        return witnessVersion;
    }

    public void setWitnessVersion(Integer witnessVersion) {
        this.witnessVersion = witnessVersion;
    }

    public Integer getWitnessProgram() {
        return witnessProgram;
    }

    public void setWitnessProgram(Integer witnessProgram) {
        this.witnessProgram = witnessProgram;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<Integer> getErrorLocations() {
        return errorLocations;
    }

    public void setErrorLocations(List<Integer> errorLocations) {
        this.errorLocations = errorLocations;
    }
}
