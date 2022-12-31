package io.github.gstojsic.bitcoin.proxy.json.model;

import io.github.gstojsic.bitcoin.proxy.json.annotation.JsonProperty;

public class IndexInfo {
    /**
     * (boolean) Whether the index is synced or not
     */
    private boolean synced;

    /**
     * (numeric) The block height to which the index is synced
     */
    @JsonProperty("best_block_height")
    private int bestBlockHeight;

    public boolean isSynced() {
        return synced;
    }

    public void setSynced(boolean synced) {
        this.synced = synced;
    }

    public int getBestBlockHeight() {
        return bestBlockHeight;
    }

    public void setBestBlockHeight(int bestBlockHeight) {
        this.bestBlockHeight = bestBlockHeight;
    }
}