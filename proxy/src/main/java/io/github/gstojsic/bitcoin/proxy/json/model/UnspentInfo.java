package io.github.gstojsic.bitcoin.proxy.json.model;

import io.github.gstojsic.bitcoin.proxy.json.annotation.JsonProperty;

import java.util.List;

public class UnspentInfo {
    /**
     * (string) the transaction id
     */
    @JsonProperty("txid")
    private String txId;

    /**
     * (numeric) the vout value
     */
    private int vout;

    /**
     * (string, optional) the bitcoin address
     */
    private String address;

    /**
     * (string, optional) The associated label, or "" for the default label
     */
    private String label;

    /**
     * (string) the script key
     */
    private String scriptPubKey;

    /**
     * (numeric) the transaction output amount in BTC
     */
    private double amount;

    /**
     * (numeric) The number of confirmations
     */
    private int confirmations;

    /**
     * (numeric, optional) The number of in-mempool ancestor transactions, including this one (if transaction is in the mempool)
     */
    @JsonProperty("ancestorcount")
    private Integer ancestorCount;

    /**
     * (numeric, optional) The virtual transaction size of in-mempool ancestors, including this one (if transaction is in the mempool)
     */
    @JsonProperty("ancestorsize")
    private Integer ancestorSize;

    /**
     * (numeric, optional) The total fees of in-mempool ancestors (including this one) with fee deltas used for mining priority in sat (if transaction is in the mempool)
     */
    @JsonProperty("ancestorfees")
    private Double ancestorFees;

    /**
     * (string, optional) The redeemScript if scriptPubKey is P2SH
     */
    private String redeemScript;

    /**
     * (string, optional) witnessScript if the scriptPubKey is P2WSH or P2SH-P2WSH
     */
    private String witnessScript;

    /**
     * (boolean) Whether we have the private keys to spend this output
     */
    private boolean spendable;

    /**
     * (boolean) Whether we know how to spend this output, ignoring the lack of keys
     */
    private boolean solvable;

    /**
     * (boolean, optional) (only present if avoid_reuse is set) Whether this output is reused/dirty (sent to an address that was previously spent from)
     */
    private Boolean reused;

    /**
     * (string, optional) (only when solvable) A descriptor for spending this output
     */
    private String desc;

    /**
     * List of parent descriptors for the scriptPubKey of this coin.
     */
    @JsonProperty("parent_descs")
    private List<String> parentDescs;

    /**
     * (boolean) Whether this output is considered safe to spend. Unconfirmed transactions
     * from outside keys and unconfirmed replacement transactions are considered unsafe
     * and are not eligible for spending by fundrawtransaction and sendtoaddress.
     */
    private boolean safe;

    public String getTxId() {
        return txId;
    }

    public void setTxId(String txId) {
        this.txId = txId;
    }

    public int getVout() {
        return vout;
    }

    public void setVout(int vout) {
        this.vout = vout;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getScriptPubKey() {
        return scriptPubKey;
    }

    public void setScriptPubKey(String scriptPubKey) {
        this.scriptPubKey = scriptPubKey;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getConfirmations() {
        return confirmations;
    }

    public void setConfirmations(int confirmations) {
        this.confirmations = confirmations;
    }

    public Integer getAncestorCount() {
        return ancestorCount;
    }

    public void setAncestorCount(Integer ancestorCount) {
        this.ancestorCount = ancestorCount;
    }

    public Integer getAncestorSize() {
        return ancestorSize;
    }

    public void setAncestorSize(Integer ancestorSize) {
        this.ancestorSize = ancestorSize;
    }

    public Double getAncestorFees() {
        return ancestorFees;
    }

    public void setAncestorFees(Double ancestorFees) {
        this.ancestorFees = ancestorFees;
    }

    public String getRedeemScript() {
        return redeemScript;
    }

    public void setRedeemScript(String redeemScript) {
        this.redeemScript = redeemScript;
    }

    public String getWitnessScript() {
        return witnessScript;
    }

    public void setWitnessScript(String witnessScript) {
        this.witnessScript = witnessScript;
    }

    public boolean isSpendable() {
        return spendable;
    }

    public void setSpendable(boolean spendable) {
        this.spendable = spendable;
    }

    public boolean isSolvable() {
        return solvable;
    }

    public void setSolvable(boolean solvable) {
        this.solvable = solvable;
    }

    public Boolean getReused() {
        return reused;
    }

    public void setReused(Boolean reused) {
        this.reused = reused;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<String> getParentDescs() {
        return parentDescs;
    }

    public void setParentDescs(List<String> parentDescs) {
        this.parentDescs = parentDescs;
    }

    public boolean isSafe() {
        return safe;
    }

    public void setSafe(boolean safe) {
        this.safe = safe;
    }
}
