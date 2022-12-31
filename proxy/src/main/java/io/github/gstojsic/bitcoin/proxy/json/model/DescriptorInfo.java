package io.github.gstojsic.bitcoin.proxy.json.model;

import io.github.gstojsic.bitcoin.proxy.json.annotation.JsonProperty;

public class DescriptorInfo {
    /**
     * (string) The descriptor in canonical form, without private keys
     */
    private String descriptor;

    /**
     * (string) The checksum for the input descriptor
     */
    private String checksum;

    /**
     * (boolean) Whether the descriptor is ranged
     */
    @JsonProperty("isrange")
    private boolean range;

    /**
     * (boolean) Whether the descriptor is solvable
     */
    @JsonProperty("issolvable")
    private boolean solvable;

    /**
     * (boolean) Whether the input descriptor contained at least one private key
     */
    @JsonProperty("hasprivatekeys")
    private boolean privateKeys;

    public String getDescriptor() {
        return descriptor;
    }

    public void setDescriptor(String descriptor) {
        this.descriptor = descriptor;
    }

    public String getChecksum() {
        return checksum;
    }

    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }

    public boolean isRange() {
        return range;
    }

    public void setRange(boolean range) {
        this.range = range;
    }

    public boolean isSolvable() {
        return solvable;
    }

    public void setSolvable(boolean solvable) {
        this.solvable = solvable;
    }

    public boolean isPrivateKeys() {
        return privateKeys;
    }

    public void setPrivateKeys(boolean privateKeys) {
        this.privateKeys = privateKeys;
    }
}

