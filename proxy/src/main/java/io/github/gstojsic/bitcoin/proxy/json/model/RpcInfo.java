package io.github.gstojsic.bitcoin.proxy.json.model;

import io.github.gstojsic.bitcoin.proxy.json.annotation.JsonProperty;

import java.util.List;

public class RpcInfo {
    /**
     * (json array) All active commands
     */
    @JsonProperty("active_commands")
    private List<ActiveCommandInfo> activeCommands;

    /**
     * (string) The complete file path to the debug log
     */
    @JsonProperty("logpath")
    private String logPath;

    public List<ActiveCommandInfo> getActiveCommands() {
        return activeCommands;
    }

    public void setActiveCommands(List<ActiveCommandInfo> activeCommands) {
        this.activeCommands = activeCommands;
    }

    public String getLogPath() {
        return logPath;
    }

    public void setLogPath(String logPath) {
        this.logPath = logPath;
    }
}