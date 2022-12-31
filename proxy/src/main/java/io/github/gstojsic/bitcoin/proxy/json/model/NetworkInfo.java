package io.github.gstojsic.bitcoin.proxy.json.model;

import io.github.gstojsic.bitcoin.proxy.json.annotation.JsonProperty;

import java.util.List;

public class NetworkInfo {
    /**
     * (numeric) the server version
     */
    private int version;

    /**
     * (string) the server subversion string
     */
    private String subversion;

    /**
     * (numeric) the protocol version
     */
    @JsonProperty("protocolversion")
    private int protocolVersion;

    /**
     * (string) the services we offer to the network
     */
    @JsonProperty("localservices")
    private String localServices;

    /**
     * (json array) the services we offer to the network, in human-readable form
     * (string) the service name
     */
    @JsonProperty("localservicesnames")
    private List<String> localServicesNames;

    /**
     * (boolean) true if transaction relay is requested from peers
     */
    @JsonProperty("localrelay")
    private boolean localRelay;

    /**
     * (numeric) the time offset
     */
    @JsonProperty("timeoffset")
    private long timeOffset;

    /**
     * (numeric) the total number of connections
     */
    private int connections;

    /**
     * (numeric) the number of inbound connections
     */
    @JsonProperty("connections_in")
    private int connectionsIn;

    /**
     * (numeric) the number of outbound connections
     */
    @JsonProperty("connections_out")
    private int connectionsOut;

    /**
     * (boolean) whether p2p networking is enabled
     */
    @JsonProperty("networkactive")
    private boolean networkActive;

    /**
     * (json array) information per network
     */
    private List<NetworkData> networks;

    /**
     * (numeric) minimum relay fee rate for transactions in BTC/kvB
     */
    @JsonProperty("relayfee")
    private double relayFee;

    /**
     * (numeric) minimum fee rate increment for mempool limiting or BIP 125 replacement in BTC/kvB
     */
    @JsonProperty("incrementalfee")
    private double incrementalFee;

    /**
     * (json array) list of local addresses
     */
    @JsonProperty("localaddresses")
    private List<LocalAddress> localAddresses;

    /**
     * (string) any network and blockchain warnings
     */
    private String warnings;

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getSubversion() {
        return subversion;
    }

    public void setSubversion(String subversion) {
        this.subversion = subversion;
    }

    public int getProtocolVersion() {
        return protocolVersion;
    }

    public void setProtocolVersion(int protocolVersion) {
        this.protocolVersion = protocolVersion;
    }

    public String getLocalServices() {
        return localServices;
    }

    public void setLocalServices(String localServices) {
        this.localServices = localServices;
    }

    public List<String> getLocalServicesNames() {
        return localServicesNames;
    }

    public void setLocalServicesNames(List<String> localServicesNames) {
        this.localServicesNames = localServicesNames;
    }

    public boolean isLocalRelay() {
        return localRelay;
    }

    public void setLocalRelay(boolean localRelay) {
        this.localRelay = localRelay;
    }

    public long getTimeOffset() {
        return timeOffset;
    }

    public void setTimeOffset(long timeOffset) {
        this.timeOffset = timeOffset;
    }

    public int getConnections() {
        return connections;
    }

    public void setConnections(int connections) {
        this.connections = connections;
    }

    public int getConnectionsIn() {
        return connectionsIn;
    }

    public void setConnectionsIn(int connectionsIn) {
        this.connectionsIn = connectionsIn;
    }

    public int getConnectionsOut() {
        return connectionsOut;
    }

    public void setConnectionsOut(int connectionsOut) {
        this.connectionsOut = connectionsOut;
    }

    public boolean isNetworkActive() {
        return networkActive;
    }

    public void setNetworkActive(boolean networkActive) {
        this.networkActive = networkActive;
    }

    public List<NetworkData> getNetworks() {
        return networks;
    }

    public void setNetworks(List<NetworkData> networks) {
        this.networks = networks;
    }

    public double getRelayFee() {
        return relayFee;
    }

    public void setRelayFee(double relayFee) {
        this.relayFee = relayFee;
    }

    public double getIncrementalFee() {
        return incrementalFee;
    }

    public void setIncrementalFee(double incrementalFee) {
        this.incrementalFee = incrementalFee;
    }

    public List<LocalAddress> getLocalAddresses() {
        return localAddresses;
    }

    public void setLocalAddresses(List<LocalAddress> localAddresses) {
        this.localAddresses = localAddresses;
    }

    public String getWarnings() {
        return warnings;
    }

    public void setWarnings(String warnings) {
        this.warnings = warnings;
    }
}