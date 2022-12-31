package io.github.gstojsic.bitcoin.proxy.json.model;

import io.github.gstojsic.bitcoin.proxy.json.annotation.JsonProperty;

public class NetworkData {
    /**
     * (string) network (ipv4, ipv6, onion, i2p, cjdns)
     */
    private String name;

    /**
     * (boolean) is the network limited using -onlynet?
     */
    private boolean limited;

    /**
     * (boolean) is the network reachable?
     */
    private boolean reachable;

    /**
     * (string) ("host:port") the proxy that is used for this network, or empty if none
     */
    private String proxy;

    /**
     * (boolean) Whether randomized credentials are used
     */
    @JsonProperty("proxy_randomize_credentials")
    private boolean proxyRandomizeCredentials;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isLimited() {
        return limited;
    }

    public void setLimited(boolean limited) {
        this.limited = limited;
    }

    public boolean isReachable() {
        return reachable;
    }

    public void setReachable(boolean reachable) {
        this.reachable = reachable;
    }

    public String getProxy() {
        return proxy;
    }

    public void setProxy(String proxy) {
        this.proxy = proxy;
    }

    public boolean isProxyRandomizeCredentials() {
        return proxyRandomizeCredentials;
    }

    public void setProxyRandomizeCredentials(boolean proxyRandomizeCredentials) {
        this.proxyRandomizeCredentials = proxyRandomizeCredentials;
    }
}