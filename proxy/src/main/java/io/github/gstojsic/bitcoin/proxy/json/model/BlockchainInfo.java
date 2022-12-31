package io.github.gstojsic.bitcoin.proxy.json.model;

import io.github.gstojsic.bitcoin.proxy.json.annotation.JsonProperty;

public class BlockchainInfo {
    /**
     * (string) current network name (main, test, signet, regtest)
     */
    private String chain;
    /**
     * (numeric) the height of the most-work fully-validated chain. The genesis block has height 0
     */
    private int blocks;
    /**
     * (numeric) the current number of headers we have validated
     */
    private int headers;
    /**
     * (string) the hash of the currently best block
     */
    @JsonProperty("bestblockhash")
    private String bestBlockhash;

    /**
     * (numeric) the current difficulty
     */
    private double difficulty;

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
     * (numeric) estimate of verification progress [0..1]
     */
    @JsonProperty("verificationprogress")
    private double verificationProgress;

    /**
     * (boolean) (debug information) estimate of whether this node is in Initial Block Download mode
     */
    @JsonProperty("initialblockdownload")
    private boolean initialBlockDownload;

    /**
     * (string) total amount of work in active chain, in hexadecimal
     */
    @JsonProperty("chainwork")
    private String chainWork;

    /**
     * (numeric) the estimated size of the block and undo files on disk
     */
    @JsonProperty("size_on_disk")
    private long sizeOnDisk;

    /**
     * (boolean) if the blocks are subject to pruning
     */
    private boolean pruned;

    /**
     * (numeric, optional) lowest-height complete block stored (only present if pruning is enabled)
     */
    @JsonProperty("pruneheight")
    private long pruneHeight;

    /**
     * (boolean, optional) whether automatic pruning is enabled (only present if pruning is enabled)
     */
    @JsonProperty("automatic_pruning")
    private boolean automaticPruning;

    /**
     * (numeric, optional) the target size used by pruning (only present if automatic pruning is enabled)
     */
    @JsonProperty("prune_target_size")
    private long pruneTargetSize;

    //  "softforks" : { (json object) (DEPRECATED, returned only if config option -deprecatedrpc=softforks is passed) status of softforks

    /**
     * (string) any network and blockchain warnings
     */
    private String warnings;

    public String getChain() {
        return chain;
    }

    public void setChain(String chain) {
        this.chain = chain;
    }

    public int getBlocks() {
        return blocks;
    }

    public void setBlocks(int blocks) {
        this.blocks = blocks;
    }

    public int getHeaders() {
        return headers;
    }

    public void setHeaders(int headers) {
        this.headers = headers;
    }

    public String getBestBlockhash() {
        return bestBlockhash;
    }

    public void setBestBlockhash(String bestBlockhash) {
        this.bestBlockhash = bestBlockhash;
    }

    public double getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(double difficulty) {
        this.difficulty = difficulty;
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

    public double getVerificationProgress() {
        return verificationProgress;
    }

    public void setVerificationProgress(double verificationProgress) {
        this.verificationProgress = verificationProgress;
    }

    public boolean isInitialBlockDownload() {
        return initialBlockDownload;
    }

    public void setInitialBlockDownload(boolean initialBlockDownload) {
        this.initialBlockDownload = initialBlockDownload;
    }

    public String getChainWork() {
        return chainWork;
    }

    public void setChainWork(String chainWork) {
        this.chainWork = chainWork;
    }

    public long getSizeOnDisk() {
        return sizeOnDisk;
    }

    public void setSizeOnDisk(long sizeOnDisk) {
        this.sizeOnDisk = sizeOnDisk;
    }

    public boolean isPruned() {
        return pruned;
    }

    public void setPruned(boolean pruned) {
        this.pruned = pruned;
    }

    public long getPruneHeight() {
        return pruneHeight;
    }

    public void setPruneHeight(long pruneHeight) {
        this.pruneHeight = pruneHeight;
    }

    public boolean isAutomaticPruning() {
        return automaticPruning;
    }

    public void setAutomaticPruning(boolean automaticPruning) {
        this.automaticPruning = automaticPruning;
    }

    public long getPruneTargetSize() {
        return pruneTargetSize;
    }

    public void setPruneTargetSize(long pruneTargetSize) {
        this.pruneTargetSize = pruneTargetSize;
    }

    public String getWarnings() {
        return warnings;
    }

    public void setWarnings(String warnings) {
        this.warnings = warnings;
    }
}
