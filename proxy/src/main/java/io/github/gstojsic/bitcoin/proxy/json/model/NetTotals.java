package io.github.gstojsic.bitcoin.proxy.json.model;

import io.github.gstojsic.bitcoin.proxy.json.annotation.JsonProperty;

public class NetTotals {
    /**
     * (numeric) Total bytes received
     */
    @JsonProperty("totalbytesrecv")
    private long totalBytesRecv;

    /**
     * (numeric) Total bytes sent
     */
    @JsonProperty("totalbytessent")
    private long totalBytesSent;

    /**
     * (numeric) Current UNIX epoch time in milliseconds
     */
    @JsonProperty("timemillis")
    private long timeMillis;

    /**
     * upload target
     */
    @JsonProperty("uploadtarget")
    private UploadTarget uploadTarget;

    public long getTotalBytesRecv() {
        return totalBytesRecv;
    }

    public void setTotalBytesRecv(long totalBytesRecv) {
        this.totalBytesRecv = totalBytesRecv;
    }

    public long getTotalBytesSent() {
        return totalBytesSent;
    }

    public void setTotalBytesSent(long totalBytesSent) {
        this.totalBytesSent = totalBytesSent;
    }

    public long getTimeMillis() {
        return timeMillis;
    }

    public void setTimeMillis(long timeMillis) {
        this.timeMillis = timeMillis;
    }

    public UploadTarget getUploadTarget() {
        return uploadTarget;
    }

    public void setUploadTarget(UploadTarget uploadTarget) {
        this.uploadTarget = uploadTarget;
    }
}
