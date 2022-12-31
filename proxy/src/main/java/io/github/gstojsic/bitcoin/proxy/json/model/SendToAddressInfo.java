package io.github.gstojsic.bitcoin.proxy.json.model;

import io.github.gstojsic.bitcoin.proxy.json.annotation.JsonProperty;

public class SendToAddressInfo {
    /**
     * (string) The transaction id.
     */
    @JsonProperty("txid")
    private String txId;

    /**
     * (string) The transaction fee reason.
     */
    @JsonProperty("fee_reason")
    private String feeReason;

    public String getTxId() {
        return txId;
    }

    public void setTxId(String txId) {
        this.txId = txId;
    }

    public String getFeeReason() {
        return feeReason;
    }

    public void setFeeReason(String feeReason) {
        this.feeReason = feeReason;
    }
}