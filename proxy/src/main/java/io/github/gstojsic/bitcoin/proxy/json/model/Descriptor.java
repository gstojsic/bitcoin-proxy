package io.github.gstojsic.bitcoin.proxy.json.model;

import java.util.List;

public class Descriptor {
    /**
     * (string) Descriptor string representation
     */
    private String desc;

    /**
     * (numeric) The creation time of the descriptor
     */
    private long timestamp;

    /**
     * (boolean) Whether this descriptor is currently used to generate new addresses
     */
    private boolean active;

    /**
     * (boolean, optional) True if this descriptor is used to generate change addresses. False if this descriptor
     * is used to generate receiving addresses; defined only for active descriptors
     */
    private Boolean internal;

    /**
     * (json array, optional) Defined only for ranged descriptors
     * (numeric) Range start inclusive
     * (numeric) Range end inclusive
     */
    private List<Integer> range;

    /**
     * (numeric, optional) The next index to generate addresses from; defined only for ranged descriptors
     */
    private Integer next;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Boolean getInternal() {
        return internal;
    }

    public void setInternal(Boolean internal) {
        this.internal = internal;
    }

    public List<Integer> getRange() {
        return range;
    }

    public void setRange(List<Integer> range) {
        this.range = range;
    }

    public Integer getNext() {
        return next;
    }

    public void setNext(Integer next) {
        this.next = next;
    }
}