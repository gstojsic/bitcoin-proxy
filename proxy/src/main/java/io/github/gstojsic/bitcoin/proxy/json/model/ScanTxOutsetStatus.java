package io.github.gstojsic.bitcoin.proxy.json.model;

public class ScanTxOutsetStatus {

    /**
     * (numeric) Approximate percent complete
     */
    private double progress;

    public double getProgress() {
        return progress;
    }

    public void setProgress(double progress) {
        this.progress = progress;
    }
}