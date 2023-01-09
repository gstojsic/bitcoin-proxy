package io.github.gstojsic.bitcoin.proxy.json.model;

import io.github.gstojsic.bitcoin.proxy.json.annotation.JsonProperty;

public class Bip9 {
    /**
     * (numeric, optional) the bit (0-28) in the block version field used to signal this softfork (only for "started" and "locked_in" status)
     */
    private Integer bit;

    /**
     * (numeric) the minimum median time past of a block at which the bit gains its meaning
     */
    @JsonProperty("start_time")
    private long startTime;

    /**
     * (numeric) the median time past of a block at which the deployment is considered failed if not yet locked in
     */
    private long timeout;

    /**
     * (numeric) minimum height of blocks for which the rules may be enforced
     */
    @JsonProperty("min_activation_height")
    private int minActivationHeight;

    /**
     * (string) status of deployment at specified block (one of "defined", "started", "locked_in", "active", "failed")
     */
    private String status;

    /**
     * (numeric) height of the first block to which the status applies
     */
    private int since;

    /**
     * (string) status of deployment at the next block
     */
    @JsonProperty("status_next")
    private String statusNext;

    /**
     * (json object, optional) numeric statistics about signalling for a softfork (only for "started" and "locked_in" status)
     */
    private Bip9Statistics statistics;

    /**
     * (string, optional) indicates blocks that signalled with a # and blocks that did not with a -
     */
    private String signalling;

    public Integer getBit() {
        return bit;
    }

    public void setBit(Integer bit) {
        this.bit = bit;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getTimeout() {
        return timeout;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }

    public int getMinActivationHeight() {
        return minActivationHeight;
    }

    public void setMinActivationHeight(int minActivationHeight) {
        this.minActivationHeight = minActivationHeight;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getSince() {
        return since;
    }

    public void setSince(int since) {
        this.since = since;
    }

    public String getStatusNext() {
        return statusNext;
    }

    public void setStatusNext(String statusNext) {
        this.statusNext = statusNext;
    }

    public Bip9Statistics getStatistics() {
        return statistics;
    }

    public void setStatistics(Bip9Statistics statistics) {
        this.statistics = statistics;
    }

    public String getSignalling() {
        return signalling;
    }

    public void setSignalling(String signalling) {
        this.signalling = signalling;
    }
}
