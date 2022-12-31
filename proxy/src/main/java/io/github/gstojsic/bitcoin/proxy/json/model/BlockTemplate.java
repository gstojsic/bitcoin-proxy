package io.github.gstojsic.bitcoin.proxy.json.model;

import io.github.gstojsic.bitcoin.proxy.json.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

public class BlockTemplate {
    /**
     * (numeric) The preferred block version
     */
    private long version;

    /**
     * (json array) specific block rules that are to be enforced
     * (string) name of a rule the client must understand to some extent; see BIP 9 for format
     */
    private List<String> rules;

    /**
     * (json object) set of pending, supported versionbit (BIP 9) softfork deployments
     * (numeric) identifies the bit number as indicating acceptance and readiness for the named softfork rule
     */
    @JsonProperty("vbavailable")
    private Map<String, Long> vbAvailable;

    /**
     * (json array)
     * (string) A supported feature, for example 'proposal'
     */
    private List<String> capabilities;

    /**
     * (numeric) bit mask of versionbits the server requires set in submissions
     */
    @JsonProperty("vbrequired")
    private long vbRequired;

    /**
     * (string) The hash of current highest block
     */
    @JsonProperty("previousblockhash")
    private String previousBlockhash;

    /**
     * (json array) contents of non-coinbase transactions that should be included in the next block
     */
    private List<BlockTemplateTransaction> transactions;

    /**
     * (json object) data that should be included in the coinbase's scriptSig content
     * (string) values must be in the coinbase (keys may be ignored)
     */
    @JsonProperty("coinbaseaux")
    private Map<String, String> coinbaseAux;

    /**
     * (numeric) maximum allowable input to coinbase transaction, including the generation award and transaction fees (in satoshis)
     */
    @JsonProperty("coinbasevalue")
    private long coinbaseValue;

    /**
     * (string) an id to include with a request to longpoll on an update to this template
     */
    @JsonProperty("longpollid")
    private String longPollId;

    /**
     * (string) The hash target
     */
    private String target;

    /**
     * (numeric) The minimum timestamp appropriate for the next block time, expressed in UNIX epoch time
     */
    @JsonProperty("mintime")
    private long minTime;

    /**
     * (json array) list of ways the block template may be changed
     * (string) A way the block template may be changed, e.g. 'time', 'transactions', 'prevblock'
     */
    private List<String> mutable;

    /**
     * (string) A range of valid nonces
     */
    @JsonProperty("noncerange")
    private String nonceRange;

    /**
     * (numeric) limit of sigops in blocks
     */
    @JsonProperty("sigoplimit")
    private int sigOpLimit;

    /**
     * (numeric) limit of block size
     */
    @JsonProperty("sizelimit")
    private int sizeLimit;

    /**
     * (numeric, optional) limit of block weight
     */
    @JsonProperty("weightlimit")
    private Integer weightLimit;

    /**
     * (numeric) current timestamp in UNIX epoch time
     */
    @JsonProperty("curtime")
    private long curTime;

    /**
     * (string) compressed target of next block
     */
    private String bits;

    /**
     * (numeric) The height of the next block
     */
    private int height;

    /**
     * (string, optional) Only on signet
     */
    @JsonProperty("signet_challenge")
    private String signetChallenge;

    /**
     * (string, optional) a valid witness commitment for the unmodified block template
     */
    @JsonProperty("default_witness_commitment")
    private String defaultWitnessCommitment;

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public List<String> getRules() {
        return rules;
    }

    public void setRules(List<String> rules) {
        this.rules = rules;
    }

    public Map<String, Long> getVbAvailable() {
        return vbAvailable;
    }

    public void setVbAvailable(Map<String, Long> vbAvailable) {
        this.vbAvailable = vbAvailable;
    }

    public List<String> getCapabilities() {
        return capabilities;
    }

    public void setCapabilities(List<String> capabilities) {
        this.capabilities = capabilities;
    }

    public long getVbRequired() {
        return vbRequired;
    }

    public void setVbRequired(long vbRequired) {
        this.vbRequired = vbRequired;
    }

    public String getPreviousBlockhash() {
        return previousBlockhash;
    }

    public void setPreviousBlockhash(String previousBlockhash) {
        this.previousBlockhash = previousBlockhash;
    }

    public List<BlockTemplateTransaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<BlockTemplateTransaction> transactions) {
        this.transactions = transactions;
    }

    public Map<String, String> getCoinbaseAux() {
        return coinbaseAux;
    }

    public void setCoinbaseAux(Map<String, String> coinbaseAux) {
        this.coinbaseAux = coinbaseAux;
    }

    public long getCoinbaseValue() {
        return coinbaseValue;
    }

    public void setCoinbaseValue(long coinbaseValue) {
        this.coinbaseValue = coinbaseValue;
    }

    public String getLongPollId() {
        return longPollId;
    }

    public void setLongPollId(String longPollId) {
        this.longPollId = longPollId;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public long getMinTime() {
        return minTime;
    }

    public void setMinTime(long minTime) {
        this.minTime = minTime;
    }

    public List<String> getMutable() {
        return mutable;
    }

    public void setMutable(List<String> mutable) {
        this.mutable = mutable;
    }

    public String getNonceRange() {
        return nonceRange;
    }

    public void setNonceRange(String nonceRange) {
        this.nonceRange = nonceRange;
    }

    public int getSigOpLimit() {
        return sigOpLimit;
    }

    public void setSigOpLimit(int sigOpLimit) {
        this.sigOpLimit = sigOpLimit;
    }

    public int getSizeLimit() {
        return sizeLimit;
    }

    public void setSizeLimit(int sizeLimit) {
        this.sizeLimit = sizeLimit;
    }

    public Integer getWeightLimit() {
        return weightLimit;
    }

    public void setWeightLimit(Integer weightLimit) {
        this.weightLimit = weightLimit;
    }

    public long getCurTime() {
        return curTime;
    }

    public void setCurTime(long curTime) {
        this.curTime = curTime;
    }

    public String getBits() {
        return bits;
    }

    public void setBits(String bits) {
        this.bits = bits;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getSignetChallenge() {
        return signetChallenge;
    }

    public void setSignetChallenge(String signetChallenge) {
        this.signetChallenge = signetChallenge;
    }

    public String getDefaultWitnessCommitment() {
        return defaultWitnessCommitment;
    }

    public void setDefaultWitnessCommitment(String defaultWitnessCommitment) {
        this.defaultWitnessCommitment = defaultWitnessCommitment;
    }
}