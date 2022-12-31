package io.github.gstojsic.bitcoin.proxy.json.model;

import io.github.gstojsic.bitcoin.proxy.json.annotation.JsonProperty;

public class ScanInfo {
    /**
     * (numeric) The block height where the rescan started (the requested height or 0)
     */
    @JsonProperty("start_height")
    private int startHeight;

    /**
     * (numeric) The height of the last rescanned block. May be null in rare cases if there was a reorg and
     * the call didn't scan any blocks because they were already scanned in the background.
     */
    @JsonProperty("stop_height")
    private Integer stopHeight;

    public int getStartHeight() {
        return startHeight;
    }

    public void setStartHeight(int startHeight) {
        this.startHeight = startHeight;
    }

    public Integer getStopHeight() {
        return stopHeight;
    }

    public void setStopHeight(Integer stopHeight) {
        this.stopHeight = stopHeight;
    }
}
