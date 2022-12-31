package io.github.gstojsic.bitcoin.proxy.json.model;

import io.github.gstojsic.bitcoin.proxy.json.annotation.JsonProperty;

import java.util.List;

public class WalletTransactionInfo {
    /**
     * (boolean, optional) Only returns true if imported addresses were involved in transaction.
     */
    @JsonProperty("involvesWatchonly")
    private Boolean involvesWatchOnly;

    /**
     * (string) The bitcoin address of the transaction.
     */
    private String address;

    /**
     * (string) The transaction category.
     * "send"      Transactions sent.
     * "receive"   Non-coinbase transactions received.
     * "generate"  Coinbase transactions received with more than 100 confirmations.
     * "immature"  Coinbase transactions received with 100 or fewer confirmations.
     * "orphan"    Orphaned coinbase transactions received.
     */
    private String category;

    /**
     * (numeric) The amount in BTC. This is negative for the 'send' category, and is positive for all other categories
     */
    private double amount;

    /**
     * (string, optional) A comment for the address/transaction, if any
     */
    private String label;

    /**
     * (numeric) the vout value
     */
    @JsonProperty("vout")
    private int VOut;

    /**
     * (numeric, optional) The amount of the fee in BTC. This is negative and only available for the
     * 'send' category of transactions.
     */
    private Double fee;

    /**
     * (numeric) The number of confirmations for the transaction. Negative confirmations means the
     * transaction conflicted that many blocks ago.
     */
    private int confirmations;

    /**
     * (boolean, optional) Only present if the transaction's only input is a coinbase one.
     */
    private Boolean generated;

    /**
     * (boolean, optional) Whether we consider the transaction to be trusted and safe to spend from.
     * Only present when the transaction has 0 confirmations (or negative confirmations, if conflicted).
     */
    private Boolean trusted;

    /**
     * (string, optional) The block hash containing the transaction.
     */
    private String blockhash;

    /**
     * (numeric, optional) The block height containing the transaction.
     */
    @JsonProperty("blockheight")
    private Integer blockHeight;

    /**
     * (numeric, optional) The index of the transaction in the block that includes it.
     */
    @JsonProperty("blockindex")
    private Integer blockIndex;

    /**
     * (numeric, optional) The block time expressed in UNIX epoch time.
     */
    @JsonProperty("blocktime")
    private Long blockTime;

    /**
     * (string) The transaction id.
     */
    @JsonProperty("txid")
    private String txId;

    /**
     * (json array) Conflicting transaction ids.
     */
    @JsonProperty("walletconflicts")
    private List<String> walletConflicts;

    /**
     * (string, optional) The txid if this tx was replaced.
     */
    @JsonProperty("replaced_by_txid")
    private String replacedByTxId;

    /**
     * (string, optional) The txid if the tx replaces one.
     */
    @JsonProperty("replaces_txid")
    private String replacesTxId;

    /**
     * (string, optional) If a comment is associated with the transaction, only present if not empty.
     */
    private String comment;

    /**
     * (string, optional) If a comment to is associated with the transaction.
     */
    private String to;

    /**
     * (numeric) The transaction time expressed in UNIX epoch time.
     */
    private long time;

    /**
     * (numeric) The time received expressed in UNIX epoch time.
     */
    @JsonProperty("timereceived")
    private long timeReceived;

    /**
     * (string) ("yes|no|unknown") Whether this transaction could be replaced due to BIP125 (replace-by-fee);
     * may be unknown for unconfirmed transactions not in the mempool.
     */
    @JsonProperty("bip125-replaceable")
    private String bip125Replaceable;

    /**
     * (boolean, optional) 'true' if the transaction has been abandoned (inputs are respendable). Only available for the
     * 'send' category of transactions.
     */
    private Boolean abandoned;

    public Boolean getInvolvesWatchOnly() {
        return involvesWatchOnly;
    }

    public void setInvolvesWatchOnly(Boolean involvesWatchOnly) {
        this.involvesWatchOnly = involvesWatchOnly;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getVOut() {
        return VOut;
    }

    public void setVOut(int VOut) {
        this.VOut = VOut;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public int getConfirmations() {
        return confirmations;
    }

    public void setConfirmations(int confirmations) {
        this.confirmations = confirmations;
    }

    public Boolean getGenerated() {
        return generated;
    }

    public void setGenerated(Boolean generated) {
        this.generated = generated;
    }

    public Boolean getTrusted() {
        return trusted;
    }

    public void setTrusted(Boolean trusted) {
        this.trusted = trusted;
    }

    public String getBlockhash() {
        return blockhash;
    }

    public void setBlockhash(String blockhash) {
        this.blockhash = blockhash;
    }

    public Integer getBlockHeight() {
        return blockHeight;
    }

    public void setBlockHeight(Integer blockHeight) {
        this.blockHeight = blockHeight;
    }

    public Integer getBlockIndex() {
        return blockIndex;
    }

    public void setBlockIndex(Integer blockIndex) {
        this.blockIndex = blockIndex;
    }

    public Long getBlockTime() {
        return blockTime;
    }

    public void setBlockTime(Long blockTime) {
        this.blockTime = blockTime;
    }

    public String getTxId() {
        return txId;
    }

    public void setTxId(String txId) {
        this.txId = txId;
    }

    public List<String> getWalletConflicts() {
        return walletConflicts;
    }

    public void setWalletConflicts(List<String> walletConflicts) {
        this.walletConflicts = walletConflicts;
    }

    public String getReplacedByTxId() {
        return replacedByTxId;
    }

    public void setReplacedByTxId(String replacedByTxId) {
        this.replacedByTxId = replacedByTxId;
    }

    public String getReplacesTxId() {
        return replacesTxId;
    }

    public void setReplacesTxId(String replacesTxId) {
        this.replacesTxId = replacesTxId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getTimeReceived() {
        return timeReceived;
    }

    public void setTimeReceived(long timeReceived) {
        this.timeReceived = timeReceived;
    }

    public String getBip125Replaceable() {
        return bip125Replaceable;
    }

    public void setBip125Replaceable(String bip125Replaceable) {
        this.bip125Replaceable = bip125Replaceable;
    }

    public Boolean getAbandoned() {
        return abandoned;
    }

    public void setAbandoned(Boolean abandoned) {
        this.abandoned = abandoned;
    }
}
