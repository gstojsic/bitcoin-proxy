package io.github.gstojsic.bitcoin.proxy.json.model;

public class Bip9Statistics {
    /**
     * (numeric) the length in blocks of the signalling period
     */
    private int period;

    /**
     * (numeric, optional) the number of blocks with the version bit set required to activate the feature (only for "started" status)
     */
    private Integer threshold;

    /**
     * (numeric) the number of blocks elapsed since the beginning of the current period
     */
    private int elapsed;

    /**
     * (numeric) the number of blocks with the version bit set in the current period
     */
    private int count;

    /**
     * (boolean, optional) returns false if there are not enough blocks left in this period to pass activation threshold (only for "started" status)
     */
    private Boolean possible;

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public Integer getThreshold() {
        return threshold;
    }

    public void setThreshold(Integer threshold) {
        this.threshold = threshold;
    }

    public int getElapsed() {
        return elapsed;
    }

    public void setElapsed(int elapsed) {
        this.elapsed = elapsed;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Boolean getPossible() {
        return possible;
    }

    public void setPossible(Boolean possible) {
        this.possible = possible;
    }
}
