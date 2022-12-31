package io.github.gstojsic.bitcoin.proxy.json.model;

import io.github.gstojsic.bitcoin.proxy.json.annotation.JsonProperty;

import java.util.List;

public class TransactionSinceBlock {
    /**
     * transactions
     */
    private List<WalletTransactionInfo> transactions;

    /**
     * <structure is the same as "transactions" above, only present if include_removed=true>
     * Note: transactions that were re-added in the active chain will appear as-is in this array, and may thus have
     * a positive confirmation count.
     */
    private List<WalletTransactionInfo> removed;

    /**
     * (string) The hash of the block (target_confirmations-1) from the best block on the main chain, or the genesis hash
     * if the referenced block does not exist yet. This is typically used to feed back into listsinceblock the next time
     * you call it. So you would generally use a target_confirmations of say 6, so you will be continually re-notified
     * of transactions until they've reached 6 confirmations plus any new ones
     */
    @JsonProperty("lastblock")
    private String lastBlock;

    public List<WalletTransactionInfo> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<WalletTransactionInfo> transactions) {
        this.transactions = transactions;
    }

    public List<WalletTransactionInfo> getRemoved() {
        return removed;
    }

    public void setRemoved(List<WalletTransactionInfo> removed) {
        this.removed = removed;
    }

    public String getLastBlock() {
        return lastBlock;
    }

    public void setLastBlock(String lastBlock) {
        this.lastBlock = lastBlock;
    }
}