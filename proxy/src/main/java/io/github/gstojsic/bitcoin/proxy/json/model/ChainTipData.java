package io.github.gstojsic.bitcoin.proxy.json.model;

import io.github.gstojsic.bitcoin.proxy.json.annotation.JsonProperty;

public class ChainTipData {
    /**
     * (numeric) height of the chain tip
     */
    private int height;

    /**
     * (string) block hash of the tip
     */
    private String hash;

    /**
     * (numeric) zero for main chain, otherwise length of branch connecting the tip to the main chain
     */
    @JsonProperty("branchlen")
    private int branchLen;

    /**
     * (string) status of the chain, "active" for the main chain
     * Possible values for status:
     * 1.  "invalid"               This branch contains at least one invalid block
     * 2.  "headers-only"          Not all blocks for this branch are available, but the headers are valid
     * 3.  "valid-headers"         All blocks are available for this branch, but they were never fully validated
     * 4.  "valid-fork"            This branch is not part of the active chain, but is fully validated
     * 5.  "active"                This is the tip of the active main chain, which is certainly valid
     */
    private String status;

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public int getBranchLen() {
        return branchLen;
    }

    public void setBranchLen(int branchLen) {
        this.branchLen = branchLen;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
