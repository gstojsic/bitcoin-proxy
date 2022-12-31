package io.github.gstojsic.bitcoin.proxy.json.model;

public class AddedNodeAddress {
    /**
     * (string) The bitcoin server IP and port we're connected to
     */
    private String address;

    /**
     * (string) connection, inbound or outbound
     */
    private String connected;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getConnected() {
        return connected;
    }

    public void setConnected(String connected) {
        this.connected = connected;
    }
}
