package io.github.gstojsic.bitcoin.proxy.json.model;

import io.github.gstojsic.bitcoin.proxy.json.annotation.JsonProperty;

import java.util.List;

public class TaprootScript {

    /**
     * (string) A leaf script
     */
    private String script;

    /**
     * (numeric) The version number for the leaf script
     */
    @JsonProperty("leaf_ver")
    private int leafVer;

    /**
     * The control blocks for this script, hex-encoded
     */
    @JsonProperty("control_blocks")
    private List<String> controlBlocks;

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }

    public int getLeafVer() {
        return leafVer;
    }

    public void setLeafVer(int leafVer) {
        this.leafVer = leafVer;
    }

    public List<String> getControlBlocks() {
        return controlBlocks;
    }

    public void setControlBlocks(List<String> controlBlocks) {
        this.controlBlocks = controlBlocks;
    }
}