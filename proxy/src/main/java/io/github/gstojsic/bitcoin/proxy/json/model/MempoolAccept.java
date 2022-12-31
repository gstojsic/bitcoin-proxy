package io.github.gstojsic.bitcoin.proxy.json.model;

import io.github.gstojsic.bitcoin.proxy.json.annotation.JsonProperty;

public class MempoolAccept {
    /**
     * (string) The transaction hash in hex
     */
    @JsonProperty("txid")
    private String txId;

    /**
     * (string) The transaction witness hash in hex
     */
    @JsonProperty("wtxid")
    private String wtxId;

    /**
     * (string, optional) Package validation error, if any (only possible if rawtxs had more than 1 transaction).
     */
    @JsonProperty("package-error")
    private String packageError;

    /**
     * (boolean, optional) Whether this tx would be accepted to the mempool and pass client-specified maxfeerate.
     * If not present, the tx was not fully validated due to a failure in another tx in the list.
     */
    private Boolean allowed;

    /**
     * (numeric, optional) Virtual transaction size as defined in BIP 141. This is different from actual serialized size
     * for witness transactions as witness data is discounted (only present when 'allowed' is true)
     */
    private Integer vsize;

    /**
     * (json object, optional) Transaction fees (only present if 'allowed' is true)
     */
    private Fees fees;

    /**
     * (string, optional) Rejection string (only present when 'allowed' is false)
     */
    @JsonProperty("reject-reason")
    private String rejectReason;

    public String getTxId() {
        return txId;
    }

    public void setTxId(String txId) {
        this.txId = txId;
    }

    public String getWtxId() {
        return wtxId;
    }

    public void setWtxId(String wtxId) {
        this.wtxId = wtxId;
    }

    public String getPackageError() {
        return packageError;
    }

    public void setPackageError(String packageError) {
        this.packageError = packageError;
    }

    public Boolean getAllowed() {
        return allowed;
    }

    public void setAllowed(Boolean allowed) {
        this.allowed = allowed;
    }

    public Integer getVsize() {
        return vsize;
    }

    public void setVsize(Integer vsize) {
        this.vsize = vsize;
    }

    public Fees getFees() {
        return fees;
    }

    public void setFees(Fees fees) {
        this.fees = fees;
    }

    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }
}
