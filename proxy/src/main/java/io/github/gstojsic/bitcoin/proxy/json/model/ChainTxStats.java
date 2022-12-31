package io.github.gstojsic.bitcoin.proxy.json.model;

import io.github.gstojsic.bitcoin.proxy.json.annotation.JsonProperty;

public class ChainTxStats {
    /**
     * (numeric) The timestamp for the final block in the window, expressed in UNIX epoch time
     */
    private long time;

    /**
     * (numeric) The total number of transactions in the chain up to that point
     */
    @JsonProperty("txcount")
    private int txCount;

    /**
     * (string) The hash of the final block in the window
     */
    @JsonProperty("window_final_block_hash")
    private String windowFinalBlockHash;

    /**
     * (numeric) The height of the final block in the window.
     */
    @JsonProperty("window_final_block_height")
    private int windowFinalBlockHeight;

    /**
     * (numeric) Size of the window in number of blocks
     */
    @JsonProperty("window_block_count")
    private int windowBlockCount;

    /**
     * (numeric) The number of transactions in the window. Only returned if "window_block_count" is > 0
     */
    @JsonProperty("window_tx_count")
    private int windowTxCount;

    /**
     * (numeric) The elapsed time in the window in seconds. Only returned if "window_block_count" is > 0
     */
    @JsonProperty("window_interval")
    private int windowInterval;

    /**
     * (numeric) The average rate of transactions per second in the window. Only returned if "window_interval" is > 0
     */
    @JsonProperty("txrate")
    private double txRate;

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getTxCount() {
        return txCount;
    }

    public void setTxCount(int txCount) {
        this.txCount = txCount;
    }

    public String getWindowFinalBlockHash() {
        return windowFinalBlockHash;
    }

    public void setWindowFinalBlockHash(String windowFinalBlockHash) {
        this.windowFinalBlockHash = windowFinalBlockHash;
    }

    public int getWindowFinalBlockHeight() {
        return windowFinalBlockHeight;
    }

    public void setWindowFinalBlockHeight(int windowFinalBlockHeight) {
        this.windowFinalBlockHeight = windowFinalBlockHeight;
    }

    public int getWindowBlockCount() {
        return windowBlockCount;
    }

    public void setWindowBlockCount(int windowBlockCount) {
        this.windowBlockCount = windowBlockCount;
    }

    public int getWindowTxCount() {
        return windowTxCount;
    }

    public void setWindowTxCount(int windowTxCount) {
        this.windowTxCount = windowTxCount;
    }

    public int getWindowInterval() {
        return windowInterval;
    }

    public void setWindowInterval(int windowInterval) {
        this.windowInterval = windowInterval;
    }

    public double getTxRate() {
        return txRate;
    }

    public void setTxRate(double txRate) {
        this.txRate = txRate;
    }
}
