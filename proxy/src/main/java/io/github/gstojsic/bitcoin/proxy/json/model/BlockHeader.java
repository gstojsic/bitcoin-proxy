package io.github.gstojsic.bitcoin.proxy.json.model;

import io.github.gstojsic.bitcoin.proxy.json.annotation.JsonProperty;

public class BlockHeader {
    /**
     * (string) the block hash (same as provided)
     */
    private String hash;

    /**
     * (numeric) The number of confirmations, or -1 if the block is not on the main chain
     */
    private int confirmations;

    /**
     * (numeric) The block height or index
     */
    private int height;

    /**
     * (numeric) The block version
     */
    private long version;

    /**
     * (string) The block version formatted in hexadecimal
     */
    private String versionHex;

    /**
     * (string) The merkle root
     */
    @JsonProperty("merkleroot")
    private String merkleRoot;

    /**
     * (numeric) The block time expressed in UNIX epoch time
     */
    private long time;

    /**
     * (numeric) The median block time expressed in UNIX epoch time
     */
    @JsonProperty("mediantime")
    private long medianTime;

    /**
     * (numeric) The nonce
     */
    private int nonce;

    /**
     * (string) The bits
     */
    private String bits;

    /**
     * (numeric) The difficulty
     */
    private double difficulty;

    /**
     * (string) Expected number of hashes required to produce the current chain
     */
    @JsonProperty("chainwork")
    private String chainWork;

    /**
     * (numeric) The number of transactions in the block
     */
    private int nTx;

    /**
     * (string, optional) The hash of the previous block (if available)
     */
    @JsonProperty("previousblockhash")
    private String previousBlockhash;

    /**
     * (string, optional) The hash of the next block (if available)
     */
    @JsonProperty("nextblockhash")
    private String nextBlockhash;

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public int getConfirmations() {
        return confirmations;
    }

    public void setConfirmations(int confirmations) {
        this.confirmations = confirmations;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public String getVersionHex() {
        return versionHex;
    }

    public void setVersionHex(String versionHex) {
        this.versionHex = versionHex;
    }

    public String getMerkleRoot() {
        return merkleRoot;
    }

    public void setMerkleRoot(String merkleRoot) {
        this.merkleRoot = merkleRoot;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getMedianTime() {
        return medianTime;
    }

    public void setMedianTime(long medianTime) {
        this.medianTime = medianTime;
    }

    public int getNonce() {
        return nonce;
    }

    public void setNonce(int nonce) {
        this.nonce = nonce;
    }

    public String getBits() {
        return bits;
    }

    public void setBits(String bits) {
        this.bits = bits;
    }

    public double getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(double difficulty) {
        this.difficulty = difficulty;
    }

    public String getChainWork() {
        return chainWork;
    }

    public void setChainWork(String chainWork) {
        this.chainWork = chainWork;
    }

    public int getNTx() {
        return nTx;
    }

    public void setNTx(int nTx) {
        this.nTx = nTx;
    }

    public String getPreviousBlockhash() {
        return previousBlockhash;
    }

    public void setPreviousBlockhash(String previousBlockhash) {
        this.previousBlockhash = previousBlockhash;
    }

    public String getNextBlockhash() {
        return nextBlockhash;
    }

    public void setNextBlockhash(String nextBlockhash) {
        this.nextBlockhash = nextBlockhash;
    }
}