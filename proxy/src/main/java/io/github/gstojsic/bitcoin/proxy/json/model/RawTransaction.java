package io.github.gstojsic.bitcoin.proxy.json.model;

import io.github.gstojsic.bitcoin.proxy.json.annotation.JsonProperty;

import java.util.List;

public class RawTransaction {

    /**
     * (boolean, optional) Whether specified block is in the active chain or not (only present with explicit "blockhash" argument)
     */
    @JsonProperty("in_active_chain")
    private Boolean inActiveChain;

    /**
     * (string) The serialized, hex-encoded data for 'txid'
     */
    private String hex;

    /**
     * (string) The transaction id (same as provided)
     */
    @JsonProperty("txid")
    private String txId;

    /**
     * (string) The transaction hash (differs from txid for witness transactions)
     */
    private String hash;

    /**
     * (numeric) The serialized transaction size
     */
    private int size;

    /**
     * The virtual transaction size (differs from size for witness transactions)
     */
    @JsonProperty("vsize")
    private int virtualSize;

    /**
     * (numeric) The transaction's weight (between vsize*4-3 and vsize*4)
     */
    private int weight;

    /**
     * (numeric) The version
     */
    private int version;

    /**
     * (numeric) The lock time
     */
    @JsonProperty("locktime")
    private long lockTime;

    /**
     *
     */
    @JsonProperty("vin")
    private List<TxInput> inputs;

    /**
     *
     */
    @JsonProperty("vout")
    private List<TxOutput> outputs;

    /**
     * (string, optional) the block hash
     */
    private String blockhash;

    /**
     * (numeric, optional) The confirmations
     */
    private Integer confirmations;

    /**
     * (numeric, optional) The block time expressed in UNIX epoch time
     */
    @JsonProperty("blocktime")
    private Long blockTime;

    /**
     * (numeric, optional) Same as "blocktime"
     */
    private Long time;

    /**
     * (numeric) The transaction fee in BTC, omitted if block undo data is not available
     * see getblock help
     */
    private Double fee;

    public Boolean getInActiveChain() {
        return inActiveChain;
    }

    public void setInActiveChain(Boolean inActiveChain) {
        this.inActiveChain = inActiveChain;
    }

    public String getHex() {
        return hex;
    }

    public void setHex(String hex) {
        this.hex = hex;
    }

    public String getTxId() {
        return txId;
    }

    public void setTxId(String txId) {
        this.txId = txId;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getVirtualSize() {
        return virtualSize;
    }

    public void setVirtualSize(int virtualSize) {
        this.virtualSize = virtualSize;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public long getLockTime() {
        return lockTime;
    }

    public void setLockTime(long lockTime) {
        this.lockTime = lockTime;
    }

    public List<TxInput> getInputs() {
        return inputs;
    }

    public void setInputs(List<TxInput> inputs) {
        this.inputs = inputs;
    }

    public List<TxOutput> getOutputs() {
        return outputs;
    }

    public void setOutputs(List<TxOutput> outputs) {
        this.outputs = outputs;
    }

    public String getBlockhash() {
        return blockhash;
    }

    public void setBlockhash(String blockhash) {
        this.blockhash = blockhash;
    }

    public Integer getConfirmations() {
        return confirmations;
    }

    public void setConfirmations(Integer confirmations) {
        this.confirmations = confirmations;
    }

    public Long getBlockTime() {
        return blockTime;
    }

    public void setBlockTime(Long blockTime) {
        this.blockTime = blockTime;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }
}
