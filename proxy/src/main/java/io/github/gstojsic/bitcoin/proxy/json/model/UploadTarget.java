package io.github.gstojsic.bitcoin.proxy.json.model;

import io.github.gstojsic.bitcoin.proxy.json.annotation.JsonProperty;

public class UploadTarget {
    /**
     * (numeric) Length of the measuring timeframe in seconds
     */
    private int timeframe;

    /**
     * (numeric) Target in bytes
     */
    private int target;

    /**
     * (boolean) True if target is reached
     */
    @JsonProperty("target_reached")
    private boolean targetReached;

    /**
     * (boolean) True if serving historical blocks
     */
    @JsonProperty("serve_historical_blocks")
    private boolean serveHistoricalBlocks;

    /**
     * (numeric) Bytes left in current time cycle
     */
    @JsonProperty("bytes_left_in_cycle")
    private long bytesLeftInCycle;

    /**
     * (numeric) Seconds left in current time cycle
     */
    @JsonProperty("time_left_in_cycle")
    private long timeLeftInCycle;

    public int getTimeframe() {
        return timeframe;
    }

    public void setTimeframe(int timeframe) {
        this.timeframe = timeframe;
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }

    public boolean isTargetReached() {
        return targetReached;
    }

    public void setTargetReached(boolean targetReached) {
        this.targetReached = targetReached;
    }

    public boolean isServeHistoricalBlocks() {
        return serveHistoricalBlocks;
    }

    public void setServeHistoricalBlocks(boolean serveHistoricalBlocks) {
        this.serveHistoricalBlocks = serveHistoricalBlocks;
    }

    public long getBytesLeftInCycle() {
        return bytesLeftInCycle;
    }

    public void setBytesLeftInCycle(long bytesLeftInCycle) {
        this.bytesLeftInCycle = bytesLeftInCycle;
    }

    public long getTimeLeftInCycle() {
        return timeLeftInCycle;
    }

    public void setTimeLeftInCycle(long timeLeftInCycle) {
        this.timeLeftInCycle = timeLeftInCycle;
    }
}

