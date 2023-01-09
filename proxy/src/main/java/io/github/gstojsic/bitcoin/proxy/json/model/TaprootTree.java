package io.github.gstojsic.bitcoin.proxy.json.model;

import io.github.gstojsic.bitcoin.proxy.json.annotation.JsonProperty;

public class TaprootTree {

    /**
     * (numeric) The depth of this element in the tree
     */
    private int depth;

    /**
     * (numeric) The version of this leaf
     */
    @JsonProperty("leaf_ver")
    private int leafVer;

    /**
     * (string) The hex-encoded script itself
     */
    private String script;

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public int getLeafVer() {
        return leafVer;
    }

    public void setLeafVer(int leafVer) {
        this.leafVer = leafVer;
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }
}