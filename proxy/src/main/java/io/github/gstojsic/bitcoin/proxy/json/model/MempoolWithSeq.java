package io.github.gstojsic.bitcoin.proxy.json.model;

import io.github.gstojsic.bitcoin.proxy.json.annotation.JsonProperty;

import java.util.List;

public class MempoolWithSeq {
    /**
     * (json array) (string) The transaction id
     */
    @JsonProperty("txids")
    private List<String> txIds;

    /**
     * (numeric) The mempool sequence value.
     */
    @JsonProperty("mempool_sequence")
    private int mempoolSequence;

    public List<String> getTxIds() {
        return txIds;
    }

    public void setTxIds(List<String> txIds) {
        this.txIds = txIds;
    }

    public int getMempoolSequence() {
        return mempoolSequence;
    }

    public void setMempoolSequence(int mempoolSequence) {
        this.mempoolSequence = mempoolSequence;
    }
}
