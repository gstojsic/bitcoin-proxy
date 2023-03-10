package io.github.gstojsic.bitcoin.proxy.json.model;

import io.github.gstojsic.bitcoin.proxy.json.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

public class PsbtInputs {
    /**
     * (json object, optional) Decoded network transaction for non-witness UTXOs
     */
    @JsonProperty("non_witness_utxo")
    private RawTransaction nonWitnessUtxo;

    /**
     * (json object, optional) Transaction output for witness UTXOs
     */
    @JsonProperty("witness_utxo")
    private PsbtTxOutput witnessUtxo;

    /**
     * partial_signatures
     */
    @JsonProperty("partial_signatures")
    private Map<String, String> partialSignatures;

    /**
     * (string, optional) The sighash type to be used
     */
    @JsonProperty("sighash")
    private String sigHash;

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
     * final_scriptSig
     */
    @JsonProperty("final_scriptSig")
    private ScriptPubKey finalScriptSig;

    /**
     * final_scriptwitness
     */
    @JsonProperty("final_scriptwitness")
    private List<String> finalScriptWitness;

    /**
     * ripemd160_preimages
     */
    @JsonProperty("ripemd160_preimages")
    private Map<String, String> ripemd160Preimages;

    /**
     * sha256_preimages
     */
    @JsonProperty("sha256_preimages")
    private Map<String, String> sha256Preimages;

    /**
     * hash160_preimages
     */
    @JsonProperty("hash160_preimages")
    private Map<String, String> hash160Preimages;

    /**
     * hash256_preimages
     */
    @JsonProperty("hash256_preimages")
    private Map<String, String> hash256Preimages;

    /**
     * (string, optional) hex-encoded signature for the Taproot key path spend
     */
    @JsonProperty("taproot_key_path_sig")
    private String taprootKeyPathSig;

    /**
     * The signatures for the pubkey and leaf hash combination
     */
    @JsonProperty("taproot_script_path_sigs")
    private List<TaprootScriptPathSignature> taprootScriptPathSigs;

    /**
     * taproot scripts
     */
    @JsonProperty("taproot_scripts")
    private List<TaprootScript> taprootScripts;

    @JsonProperty("taproot_bip32_derivs")
    private List<TaprootBip32Deriv> taprootBip32Derivs;

    /**
     * (string, optional) The hex-encoded Taproot x-only internal key
     */
    @JsonProperty("taproot_internal_key")
    private String taprootInternalKey;

    /**
     * (string, optional) The hex-encoded Taproot merkle root
     */
    @JsonProperty("taproot_merkle_root")
    private String taprootMerkleRoot;

    /**
     * (json object, optional) The unknown input fields
     */
    private Map<String, String> unknown;

    /**
     * (json array, optional) The input proprietary map
     */
    private List<PsbtProprietaryData> proprietary;

    public RawTransaction getNonWitnessUtxo() {
        return nonWitnessUtxo;
    }

    public void setNonWitnessUtxo(RawTransaction nonWitnessUtxo) {
        this.nonWitnessUtxo = nonWitnessUtxo;
    }

    public PsbtTxOutput getWitnessUtxo() {
        return witnessUtxo;
    }

    public void setWitnessUtxo(PsbtTxOutput witnessUtxo) {
        this.witnessUtxo = witnessUtxo;
    }

    public Map<String, String> getPartialSignatures() {
        return partialSignatures;
    }

    public void setPartialSignatures(Map<String, String> partialSignatures) {
        this.partialSignatures = partialSignatures;
    }

    public String getSigHash() {
        return sigHash;
    }

    public void setSigHash(String sigHash) {
        this.sigHash = sigHash;
    }

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

    public ScriptPubKey getFinalScriptSig() {
        return finalScriptSig;
    }

    public void setFinalScriptSig(ScriptPubKey finalScriptSig) {
        this.finalScriptSig = finalScriptSig;
    }

    public List<String> getFinalScriptWitness() {
        return finalScriptWitness;
    }

    public void setFinalScriptWitness(List<String> finalScriptWitness) {
        this.finalScriptWitness = finalScriptWitness;
    }

    public Map<String, String> getRipemd160Preimages() {
        return ripemd160Preimages;
    }

    public void setRipemd160Preimages(Map<String, String> ripemd160Preimages) {
        this.ripemd160Preimages = ripemd160Preimages;
    }

    public Map<String, String> getSha256Preimages() {
        return sha256Preimages;
    }

    public void setSha256Preimages(Map<String, String> sha256Preimages) {
        this.sha256Preimages = sha256Preimages;
    }

    public Map<String, String> getHash160Preimages() {
        return hash160Preimages;
    }

    public void setHash160Preimages(Map<String, String> hash160Preimages) {
        this.hash160Preimages = hash160Preimages;
    }

    public Map<String, String> getHash256Preimages() {
        return hash256Preimages;
    }

    public void setHash256Preimages(Map<String, String> hash256Preimages) {
        this.hash256Preimages = hash256Preimages;
    }

    public String getTaprootKeyPathSig() {
        return taprootKeyPathSig;
    }

    public void setTaprootKeyPathSig(String taprootKeyPathSig) {
        this.taprootKeyPathSig = taprootKeyPathSig;
    }

    public List<TaprootScriptPathSignature> getTaprootScriptPathSigs() {
        return taprootScriptPathSigs;
    }

    public void setTaprootScriptPathSigs(List<TaprootScriptPathSignature> taprootScriptPathSigs) {
        this.taprootScriptPathSigs = taprootScriptPathSigs;
    }

    public List<TaprootScript> getTaprootScripts() {
        return taprootScripts;
    }

    public void setTaprootScripts(List<TaprootScript> taprootScripts) {
        this.taprootScripts = taprootScripts;
    }

    public List<TaprootBip32Deriv> getTaprootBip32Derivs() {
        return taprootBip32Derivs;
    }

    public void setTaprootBip32Derivs(List<TaprootBip32Deriv> taprootBip32Derivs) {
        this.taprootBip32Derivs = taprootBip32Derivs;
    }

    public String getTaprootInternalKey() {
        return taprootInternalKey;
    }

    public void setTaprootInternalKey(String taprootInternalKey) {
        this.taprootInternalKey = taprootInternalKey;
    }

    public String getTaprootMerkleRoot() {
        return taprootMerkleRoot;
    }

    public void setTaprootMerkleRoot(String taprootMerkleRoot) {
        this.taprootMerkleRoot = taprootMerkleRoot;
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
