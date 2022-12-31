package io.github.gstojsic.bitcoin.proxy.json.model;

public class ActiveCommandInfo {
    /**
     * (string) The name of the RPC command
     */
    private String method;

    /**
     * (numeric) The running time in microseconds
     */
    private int duration;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}