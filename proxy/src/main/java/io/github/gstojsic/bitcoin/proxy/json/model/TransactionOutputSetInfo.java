package io.github.gstojsic.bitcoin.proxy.json.model;

import io.github.gstojsic.bitcoin.proxy.json.annotation.JsonProperty;

public class TransactionOutputSetInfo {
    /**
     * (numeric) The current block height (index)
     */
    private int height;

    /**
     * (string) The hash of the block at the tip of the chain
     */
    @JsonProperty("bestblock")
    private String bestBlock;

    /**
     * (numeric) The number of transactions with unspent outputs
     */
    private int transactions;

    /**
     * (numeric) The number of unspent transaction outputs
     */
    @JsonProperty("txouts")
    private int txOuts;

    /**
     * (numeric) A meaningless metric for UTXO set size
     */
    @JsonProperty("bogosize")
    private long bogoSize;

    /**
     * (string) The serialized hash (only present if 'hash_serialized_2' hash_type is chosen)
     */
    @JsonProperty("hash_serialized_2")
    private String hashSerialized;

    /**
     * (numeric) The estimated size of the chainstate on disk
     */
    @JsonProperty("disk_size")
    private long diskSize;

    /**
     * (numeric) The total amount
     */
    @JsonProperty("total_amount")
    private double totalAmount;

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getBestBlock() {
        return bestBlock;
    }

    public void setBestBlock(String bestBlock) {
        this.bestBlock = bestBlock;
    }

    public int getTransactions() {
        return transactions;
    }

    public void setTransactions(int transactions) {
        this.transactions = transactions;
    }

    public int getTxOuts() {
        return txOuts;
    }

    public void setTxOuts(int txOuts) {
        this.txOuts = txOuts;
    }

    public long getBogoSize() {
        return bogoSize;
    }

    public void setBogoSize(long bogoSize) {
        this.bogoSize = bogoSize;
    }

    public String getHashSerialized() {
        return hashSerialized;
    }

    public void setHashSerialized(String hashSerialized) {
        this.hashSerialized = hashSerialized;
    }

    public long getDiskSize() {
        return diskSize;
    }

    public void setDiskSize(long diskSize) {
        this.diskSize = diskSize;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}