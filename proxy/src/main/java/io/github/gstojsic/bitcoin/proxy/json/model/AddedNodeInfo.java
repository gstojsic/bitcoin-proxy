package io.github.gstojsic.bitcoin.proxy.json.model;

import io.github.gstojsic.bitcoin.proxy.json.annotation.JsonProperty;

import java.util.List;

public class AddedNodeInfo {
    /**
     * (string) The node IP address or name (as provided to addnode)
     */
    @JsonProperty("addednode")
    private String addedNode;

    /**
     * (boolean) If connected
     */
    private boolean connected;

    private List<AddedNodeAddress> addresses;

    public String getAddedNode() {
        return addedNode;
    }

    public void setAddedNode(String addedNode) {
        this.addedNode = addedNode;
    }

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    public List<AddedNodeAddress> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<AddedNodeAddress> addresses) {
        this.addresses = addresses;
    }
}