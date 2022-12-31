package io.github.gstojsic.bitcoin.proxy.json.model;

import io.github.gstojsic.bitcoin.proxy.json.annotation.JsonProperty;

import java.util.List;

public class AddressInfo {
    /**
     * (string) The bitcoin address validated.
     */
    private String address;

    /**
     * (string) The hex-encoded scriptPubKey generated by the address.
     */
    private String scriptPubKey;

    /**
     * (boolean) If the address is yours.
     */
    @JsonProperty("ismine")
    private boolean mine;

    /**
     * (boolean) If the address is watchonly.
     */
    @JsonProperty("iswatchonly")
    private boolean watchOnly;

    /**
     * (boolean) If we know how to spend coins sent to this address, ignoring the possible lack of private keys.
     */
    private boolean solvable;

    /**
     * (string, optional) A descriptor for spending coins sent to this address (only when solvable).
     */
    private String desc;

    /**
     * (string, optional) The descriptor used to derive this address if this is a descriptor wallet
     */
    @JsonProperty("parent_desc")
    private String parentDesc;

    /**
     * (boolean) If the key is a script.
     */
    @JsonProperty("isscript")
    private boolean isScript;

    /**
     * (boolean) If the address was used for change output.
     */
    @JsonProperty("ischange")
    private boolean change;

    /**
     * (boolean) If the address is a witness address.
     */
    @JsonProperty("iswitness")
    private boolean witness;

    /**
     * (numeric, optional) The version number of the witness program.
     */
    @JsonProperty("witness_version")
    private Integer witnessVersion;

    /**
     * (string, optional) The hex value of the witness program.
     */
    @JsonProperty("witness_program")
    private String witnessProgram;

    /**
     * (string, optional) The output script type. Only if isscript is true and the redeemscript is known. Possible
     * types: nonstandard, pubkey, pubkeyhash, scripthash, multisig, nulldata, witness_v0_keyhash,
     * witness_v0_scripthash, witness_unknown.
     */
    private String script;

    /**
     * (string, optional) The redeemscript for the p2sh address.
     */
    private String hex;

    /**
     * (json array, optional) Array of pubkeys associated with the known redeemscript (only if script is multisig).
     */
    @JsonProperty("pubkeys")
    private List<String> pubKeys;

    /**
     * (numeric, optional) The number of signatures required to spend multisig output (only if script is multisig).
     */
    @JsonProperty("sigsrequired")
    private Integer sigsRequired;

    /**
     * (string, optional) The hex value of the raw public key for single-key addresses (possibly embedded in P2SH or P2WSH).
     */
    @JsonProperty("pubkey")
    private String pubKey;

    /**
     * (json object, optional) Information about the address embedded in P2SH or P2WSH, if relevant and known.
     *   Includes all getaddressinfo output fields for the embedded address, excluding metadata (timestamp, hdkeypath, hdseedid)
     *   and relation to the wallet (ismine, iswatchonly).
     */
    //private Object embeded; //TODO

    /**
     * (boolean, optional) If the pubkey is compressed.
     */
    @JsonProperty("iscompressed")
    private Boolean compressed;

    /**
     * (numeric, optional) The creation time of the key, if available, expressed in UNIX epoch time.
     */
    private Long timestamp;

    /**
     * (string, optional) The HD keypath, if the key is HD and available.
     */
    @JsonProperty("hdkeypath")
    private String hdKeypath;

    /**
     * (string, optional) The Hash160 of the HD seed.
     */
    @JsonProperty("hdseedid")
    private String hdSeedId;

    /**
     * (string, optional) The fingerprint of the master key.
     */
    @JsonProperty("hdmasterfingerprint")
    private String hdMasterFingerprint;

    /**
     * (json array) Array of labels associated with the address. Currently limited to one label but returned
     * as an array to keep the API stable if multiple labels are enabled in the future.
     * (string) Label name (defaults to "").
     */
    private List<String> labels;

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

    public boolean isMine() {
        return mine;
    }

    public void setMine(boolean mine) {
        this.mine = mine;
    }

    public boolean isWatchOnly() {
        return watchOnly;
    }

    public void setWatchOnly(boolean watchOnly) {
        this.watchOnly = watchOnly;
    }

    public boolean isSolvable() {
        return solvable;
    }

    public void setSolvable(boolean solvable) {
        this.solvable = solvable;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getParentDesc() {
        return parentDesc;
    }

    public void setParentDesc(String parentDesc) {
        this.parentDesc = parentDesc;
    }

    public boolean isScript() {
        return isScript;
    }

    public void setIsScript(boolean script) {
        isScript = script;
    }

    public boolean isChange() {
        return change;
    }

    public void setChange(boolean change) {
        this.change = change;
    }

    public boolean isWitness() {
        return witness;
    }

    public void setWitness(boolean witness) {
        this.witness = witness;
    }

    public Integer getWitnessVersion() {
        return witnessVersion;
    }

    public void setWitnessVersion(Integer witnessVersion) {
        this.witnessVersion = witnessVersion;
    }

    public String getWitnessProgram() {
        return witnessProgram;
    }

    public void setWitnessProgram(String witnessProgram) {
        this.witnessProgram = witnessProgram;
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }

    public String getHex() {
        return hex;
    }

    public void setHex(String hex) {
        this.hex = hex;
    }

    public List<String> getPubKeys() {
        return pubKeys;
    }

    public void setPubKeys(List<String> pubKeys) {
        this.pubKeys = pubKeys;
    }

    public Integer getSigsRequired() {
        return sigsRequired;
    }

    public void setSigsRequired(Integer sigsRequired) {
        this.sigsRequired = sigsRequired;
    }

    public String getPubKey() {
        return pubKey;
    }

    public void setPubKey(String pubKey) {
        this.pubKey = pubKey;
    }

    public Boolean getCompressed() {
        return compressed;
    }

    public void setCompressed(Boolean compressed) {
        this.compressed = compressed;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getHdKeypath() {
        return hdKeypath;
    }

    public void setHdKeypath(String hdKeypath) {
        this.hdKeypath = hdKeypath;
    }

    public String getHdSeedId() {
        return hdSeedId;
    }

    public void setHdSeedId(String hdSeedId) {
        this.hdSeedId = hdSeedId;
    }

    public String getHdMasterFingerprint() {
        return hdMasterFingerprint;
    }

    public void setHdMasterFingerprint(String hdMasterFingerprint) {
        this.hdMasterFingerprint = hdMasterFingerprint;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }
}
