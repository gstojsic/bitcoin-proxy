package io.github.gstojsic.bitcoin.proxy.json.model;

import io.github.gstojsic.bitcoin.proxy.json.annotation.JsonProperty;

public class WalletFlagInfo {
    /**
     * (string) The name of the flag that was modified
     */
    @JsonProperty("flag_name")
    private String flagName;

    /**
     * (boolean) The new state of the flag
     */
    @JsonProperty("flag_state")
    private boolean flagState;

    /**
     * (string, optional) Any warnings associated with the change
     */
    private String warnings;

    public String getFlagName() {
        return flagName;
    }

    public void setFlagName(String flagName) {
        this.flagName = flagName;
    }

    public boolean isFlagState() {
        return flagState;
    }

    public void setFlagState(boolean flagState) {
        this.flagState = flagState;
    }

    public String getWarnings() {
        return warnings;
    }

    public void setWarnings(String warnings) {
        this.warnings = warnings;
    }
}
