package io.github.gstojsic.bitcoin.proxy.json.model;

import io.github.gstojsic.bitcoin.proxy.json.annotation.JsonProperty;

public class MiningInfo {
    /**
     * (numeric) The current block
     */
    private int blocks;

    /**
     * (numeric, optional) The block weight of the last assembled block (only present if a block was ever assembled)
     */
    @JsonProperty("currentblockweight")
    private Integer currentBlockWeight;

    /**
     * (numeric, optional) The number of block transactions of the last assembled block (only present if a block was ever assembled)
     */
    @JsonProperty("currentblocktx")
    private Integer currentBlockTx;

    /**
     * (numeric) The current difficulty
     */
    private double difficulty;

    /**
     * (numeric) The network hashes per second
     */
    @JsonProperty("networkhashps")
    private double networkHashPs;

    /**
     * (numeric) The size of the mempool
     */
    @JsonProperty("pooledtx")
    private int pooledTx;

    /**
     * (string) current network name (main, test, signet, regtest)
     */
    private String chain;

    /**
     * (string) any network and blockchain warnings
     */
    private String warnings;

    public int getBlocks() {
        return blocks;
    }

    public void setBlocks(int blocks) {
        this.blocks = blocks;
    }

    public Integer getCurrentBlockWeight() {
        return currentBlockWeight;
    }

    public void setCurrentBlockWeight(Integer currentBlockWeight) {
        this.currentBlockWeight = currentBlockWeight;
    }

    public Integer getCurrentBlockTx() {
        return currentBlockTx;
    }

    public void setCurrentBlockTx(Integer currentBlockTx) {
        this.currentBlockTx = currentBlockTx;
    }

    public double getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(double difficulty) {
        this.difficulty = difficulty;
    }

    public double getNetworkHashPs() {
        return networkHashPs;
    }

    public void setNetworkHashPs(double networkHashPs) {
        this.networkHashPs = networkHashPs;
    }

    public int getPooledTx() {
        return pooledTx;
    }

    public void setPooledTx(int pooledTx) {
        this.pooledTx = pooledTx;
    }

    public String getChain() {
        return chain;
    }

    public void setChain(String chain) {
        this.chain = chain;
    }

    public String getWarnings() {
        return warnings;
    }

    public void setWarnings(String warnings) {
        this.warnings = warnings;
    }
}