package io.github.gstojsic.bitcoin.proxy.json.model;

import io.github.gstojsic.bitcoin.proxy.json.annotation.JsonProperty;

public class SendInfo {
    /**
     * (boolean) If the transaction has a complete set of signatures
     */
    private boolean complete;

    /**
     * (string, optional) The transaction id for the send. Only 1 transaction is created regardless of the number of addresses.
     */
    @JsonProperty("txid")
    private String txId;

    /**
     * (string, optional) If add_to_wallet is false, the hex-encoded raw transaction with signature(s)
     */
    private String hex;

    /**
     * (string, optional) If more signatures are needed, or if add_to_wallet is false, the base64-encoded (partially) signed transaction
     */
    private String psbt;

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public String getTxId() {
        return txId;
    }

    public void setTxId(String txId) {
        this.txId = txId;
    }

    public String getHex() {
        return hex;
    }

    public void setHex(String hex) {
        this.hex = hex;
    }

    public String getPsbt() {
        return psbt;
    }

    public void setPsbt(String psbt) {
        this.psbt = psbt;
    }
}
