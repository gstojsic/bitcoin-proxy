package io.github.gstojsic.bitcoin.proxy.json.model;

public class LocalAddress {
    /**
     * (string) network address
     */
    private String address;

    /**
     * (numeric) network port
     */
    private int port;

    /**
     * (numeric) relative score
     */
    private int score;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
