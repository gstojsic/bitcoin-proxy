package io.github.gstojsic.bitcoin.proxy.json.model;

import io.github.gstojsic.bitcoin.proxy.json.annotation.JsonProperty;

public class TransactionOutputSetInfo {
    /**
     * (numeric) The block height (index) of the returned statistics
     */
    private int height;

    /**
     * (string) The hash of the block at which these statistics are calculated
     */
    @JsonProperty("bestblock")
    private String bestBlock;

    /**
     * (numeric, optional) The number of transactions with unspent outputs (not available when coinstatsindex is used)
     */
    private Integer transactions;

    /**
     * (numeric) The number of unspent transaction outputs
     */
    @JsonProperty("txouts")
    private int txOuts;

    /**
     * (numeric) Database-independent, meaningless metric indicating the UTXO set size
     */
    @JsonProperty("bogosize")
    private long bogoSize;

    /**
     * (string, optional) The serialized hash (only present if 'hash_serialized_2' hash_type is chosen)
     */
    @JsonProperty("hash_serialized_2")
    private String hashSerialized;

    /**
     * (string, optional) The serialized hash (only present if 'muhash' hash_type is chosen)
     */
    @JsonProperty("muhash")
    private String muHash;

    /**
     * (numeric, optional) The estimated size of the chainstate on disk (not available when coinstatsindex is used)
     */
    @JsonProperty("disk_size")
    private Long diskSize;

    /**
     * (numeric) The total amount of coins in the UTXO set
     */
    @JsonProperty("total_amount")
    private double totalAmount;

    /**
     * (numeric, optional) The total amount of coins permanently excluded from the UTXO set (only available if coinstatsindex is used)
     */
    @JsonProperty("total_unspendable_amount")
    private Double totalUnspendableAmount;

    /**
     * Info on amounts in the block at this block height (only available if coinstatsindex is used)
     */
    @JsonProperty("block_info")
    private TxOutBlockInfo blockInfo;

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

    public Integer getTransactions() {
        return transactions;
    }

    public void setTransactions(Integer transactions) {
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

    public String getMuHash() {
        return muHash;
    }

    public void setMuHash(String muHash) {
        this.muHash = muHash;
    }

    public Long getDiskSize() {
        return diskSize;
    }

    public void setDiskSize(Long diskSize) {
        this.diskSize = diskSize;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getTotalUnspendableAmount() {
        return totalUnspendableAmount;
    }

    public void setTotalUnspendableAmount(Double totalUnspendableAmount) {
        this.totalUnspendableAmount = totalUnspendableAmount;
    }

    public TxOutBlockInfo getBlockInfo() {
        return blockInfo;
    }

    public void setBlockInfo(TxOutBlockInfo blockInfo) {
        this.blockInfo = blockInfo;
    }
}