package io.github.gstojsic.bitcoin.proxy.json.model;

public class NodeAddress {
    /**
     * (numeric) The UNIX epoch time when the node was last seen
     */
    private long time;

    /**
     * (numeric) The services offered by the node
     */
    private int services;

    /**
     * (string) The address of the node
     */
    private String address;

    /**
     * (numeric) The port number of the node
     */
    private int port;

    /**
     * (string) The network (ipv4, ipv6, onion, i2p, cjdns) the node connected through
     */
    private String network;

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getServices() {
        return services;
    }

    public void setServices(int services) {
        this.services = services;
    }

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

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }
}

