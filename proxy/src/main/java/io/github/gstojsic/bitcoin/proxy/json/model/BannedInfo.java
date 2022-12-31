package io.github.gstojsic.bitcoin.proxy.json.model;

import io.github.gstojsic.bitcoin.proxy.json.annotation.JsonProperty;

public class BannedInfo {
    /**
     * (string) The IP/Subnet of the banned node
     */
    private String address;

    @JsonProperty("ban_created")
    private long banCreated;

    @JsonProperty("banned_until")
    private long bannedUntil;

    @JsonProperty("ban_duration")
    private long banDuration;

    @JsonProperty("time_remaining")
    private long timeRemaining;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getBanCreated() {
        return banCreated;
    }

    public void setBanCreated(long banCreated) {
        this.banCreated = banCreated;
    }

    public long getBannedUntil() {
        return bannedUntil;
    }

    public void setBannedUntil(long bannedUntil) {
        this.bannedUntil = bannedUntil;
    }

    public long getBanDuration() {
        return banDuration;
    }

    public void setBanDuration(long banDuration) {
        this.banDuration = banDuration;
    }

    public long getTimeRemaining() {
        return timeRemaining;
    }

    public void setTimeRemaining(long timeRemaining) {
        this.timeRemaining = timeRemaining;
    }
}