package io.github.gstojsic.bitcoin.proxy.json.model;

import io.github.gstojsic.bitcoin.proxy.json.annotation.JsonProperty;

import java.util.List;

public class PeerInfo {
    /**
     * (numeric) Peer index
     */
    private int id;

    /**
     * (string) (host:port) The IP address and port of the peer
     */
    private String addr;

    /**
     * (string, optional) (ip:port) Bind address of the connection to the peer
     */
    @JsonProperty("addrbind")
    private String addrBind;

    /**
     * (string, optional) (ip:port) Local address as reported by the peer
     */
    @JsonProperty("addrlocal")
    private String addrLocal;

    /**
     * (string) Network (ipv4, ipv6, onion, i2p, cjdns, not_publicly_routable)
     */
    private String network;

    /**
     * (numeric, optional) The AS in the BGP route to the peer used for diversifying peer selection (only available
     * if the asmap config flag is set)
     */
    @JsonProperty("mapped_as")
    private String mappedAs;

    /**
     * (string) The services offered
     */
    private String services;

    /**
     * (json array) the services offered, in human-readable form
     */
    @JsonProperty("servicesnames")
    private List<String> servicesNames;

    /**
     * (boolean, optional) Whether peer has asked us to relay transactions to it
     */
    @JsonProperty("relaytxes")
    private Boolean relayTxes;

    /**
     * (numeric) The UNIX epoch time of the last send
     */
    @JsonProperty("lastsend")
    private long lastSend;

    /**
     * (numeric) The UNIX epoch time of the last receive
     */
    @JsonProperty("lastrecv")
    private long lastRecv;

    /**
     * (numeric) The UNIX epoch time of the last valid transaction received from this peer
     */
    @JsonProperty("last_transaction")
    private long lastTransaction;

    /**
     * (numeric) The UNIX epoch time of the last block received from this peer
     */
    @JsonProperty("last_block")
    private long lastBlock;

    /**
     * (numeric) The total bytes sent
     */
    @JsonProperty("bytessent")
    private long bytesSent;

    /**
     * (numeric) The total bytes received
     */
    @JsonProperty("bytesrecv")
    private long bytesRecv;

    /**
     * (numeric) The UNIX epoch time of the connection
     */
    @JsonProperty("conntime")
    private long connTime;

    /**
     * (numeric) The time offset in seconds
     */
    @JsonProperty("timeoffset")
    private int timeOffset;

    /**
     * (numeric, optional) ping time (if available)
     */
    @JsonProperty("pingtime")
    private Double pingTime;

    /**
     * (numeric, optional) minimum observed ping time (if any at all)
     */
    @JsonProperty("minping")
    private Double minPing;

    /**
     * (numeric, optional) ping wait (if non-zero)
     */
    @JsonProperty("pingwait")
    private Double pingWait;

    /**
     * (numeric) The peer version, such as 70001
     */
    private int version;

    /**
     * (string) The string version
     */
    @JsonProperty("subver")
    private String subVer;

    /**
     * (boolean) Inbound (true) or Outbound (false)
     */
    private boolean inbound;

    /**
     * (boolean) Whether we selected peer as (compact blocks) high-bandwidth peer
     */
    @JsonProperty("bip152_hb_to")
    private boolean bip152HbTo;

    /**
     * (boolean) Whether peer selected us as (compact blocks) high-bandwidth peer
     */
    @JsonProperty("bip152_hb_from")
    private boolean bip152HbFrom;

    /**
     * (numeric, optional) The starting height (block) of the peer
     */
    @JsonProperty("startingheight")
    private Integer startingHeight;

    /**
     * (numeric, optional) The current height of header pre-synchronization with this peer, or -1 if no low-work sync is in progress
     */
    @JsonProperty("presynced_headers")
    private Integer presyncedHeaders;

    /**
     * (numeric, optional) The last header we have in common with this peer
     */
    @JsonProperty("synced_headers")
    private Integer syncedHeaders;

    /**
     * (numeric, optional) The last block we have in common with this peer
     */
    @JsonProperty("synced_blocks")
    private Integer syncedBlocks;

    /**
     * (numeric) The heights of blocks we're currently asking from this peer
     */
    private List<Integer> inflight;

    /**
     * (boolean, optional) Whether we participate in address relay with this peer
     */
    @JsonProperty("addr_relay_enabled")
    private Boolean addrRelayEnabled;

    /**
     * (numeric, optional) The total number of addresses processed, excluding those dropped due to rate limiting
     */
    @JsonProperty("addr_processed")
    private Integer addrProcessed;

    /**
     * (numeric, optional) The total number of addresses dropped due to rate limiting
     */
    @JsonProperty("addr_rate_limited")
    private Integer addrRateLimited;

    /**
     * (json array) Any special permissions that have been granted to this peer
     * (string) bloomfilter (allow requesting BIP37 filtered blocks and transactions),
     * noban (do not ban for misbehavior; implies download),
     * forcerelay (relay transactions that are already in the mempool; implies relay),
     * relay (relay even in -blocksonly mode, and unlimited transaction announcements),
     * mempool (allow requesting BIP35 mempool contents),
     * download (allow getheaders during IBD, no disconnect after maxuploadtarget limit),
     * addr (responses to GETADDR avoid hitting the cache and contain random records with the most up-to-date info).
     */
    private List<String> permissions;

    /**
     * (numeric, optional) The minimum fee rate for transactions this peer accepts
     */
    @JsonProperty("minfeefilter")
    private Double minFeeFilter;

    /**
     * (numeric) The total bytes sent aggregated by message type
     * When a message type is not listed in this json object, the bytes sent are 0.
     * Only known message types can appear as keys in the object.
     */
    @JsonProperty("bytessent_per_msg")
    private PeerMsgStats bytesSentPerMsg;

    /**
     * (numeric) The total bytes received aggregated by message type
     * When a message type is not listed in this json object, the bytes received are 0.
     * Only known message types can appear as keys in the object and all bytes received
     * of unknown message types are listed under '*other*'.
     */
    @JsonProperty("bytesrecv_per_msg")
    private PeerMsgStats bytesRecvPerMsg;

    /**
     * (string) Type of connection:
     * outbound-full-relay (default automatic connections),
     * block-relay-only (does not relay transactions or addresses),
     * inbound (initiated by the peer),
     * manual (added via addnode RPC or -addnode/-connect configuration options),
     * addr-fetch (short-lived automatic connection for soliciting addresses),
     * feeler (short-lived automatic connection for testing addresses).
     * Please note this output is unlikely to be stable in upcoming releases as we iterate to
     * best capture connection behaviors.
     */
    @JsonProperty("connection_type")
    private String connectionType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getAddrBind() {
        return addrBind;
    }

    public void setAddrBind(String addrBind) {
        this.addrBind = addrBind;
    }

    public String getAddrLocal() {
        return addrLocal;
    }

    public void setAddrLocal(String addrLocal) {
        this.addrLocal = addrLocal;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public String getMappedAs() {
        return mappedAs;
    }

    public void setMappedAs(String mappedAs) {
        this.mappedAs = mappedAs;
    }

    public String getServices() {
        return services;
    }

    public void setServices(String services) {
        this.services = services;
    }

    public List<String> getServicesNames() {
        return servicesNames;
    }

    public void setServicesNames(List<String> servicesNames) {
        this.servicesNames = servicesNames;
    }

    public Boolean getRelayTxes() {
        return relayTxes;
    }

    public void setRelayTxes(Boolean relayTxes) {
        this.relayTxes = relayTxes;
    }

    public long getLastSend() {
        return lastSend;
    }

    public void setLastSend(long lastSend) {
        this.lastSend = lastSend;
    }

    public long getLastRecv() {
        return lastRecv;
    }

    public void setLastRecv(long lastRecv) {
        this.lastRecv = lastRecv;
    }

    public long getLastTransaction() {
        return lastTransaction;
    }

    public void setLastTransaction(long lastTransaction) {
        this.lastTransaction = lastTransaction;
    }

    public long getLastBlock() {
        return lastBlock;
    }

    public void setLastBlock(long lastBlock) {
        this.lastBlock = lastBlock;
    }

    public long getBytesSent() {
        return bytesSent;
    }

    public void setBytesSent(long bytesSent) {
        this.bytesSent = bytesSent;
    }

    public long getBytesRecv() {
        return bytesRecv;
    }

    public void setBytesRecv(long bytesRecv) {
        this.bytesRecv = bytesRecv;
    }

    public long getConnTime() {
        return connTime;
    }

    public void setConnTime(long connTime) {
        this.connTime = connTime;
    }

    public int getTimeOffset() {
        return timeOffset;
    }

    public void setTimeOffset(int timeOffset) {
        this.timeOffset = timeOffset;
    }

    public Double getPingTime() {
        return pingTime;
    }

    public void setPingTime(Double pingTime) {
        this.pingTime = pingTime;
    }

    public Double getMinPing() {
        return minPing;
    }

    public void setMinPing(Double minPing) {
        this.minPing = minPing;
    }

    public Double getPingWait() {
        return pingWait;
    }

    public void setPingWait(Double pingWait) {
        this.pingWait = pingWait;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getSubVer() {
        return subVer;
    }

    public void setSubVer(String subVer) {
        this.subVer = subVer;
    }

    public boolean isInbound() {
        return inbound;
    }

    public void setInbound(boolean inbound) {
        this.inbound = inbound;
    }

    public boolean isBip152HbTo() {
        return bip152HbTo;
    }

    public void setBip152HbTo(boolean bip152HbTo) {
        this.bip152HbTo = bip152HbTo;
    }

    public boolean isBip152HbFrom() {
        return bip152HbFrom;
    }

    public void setBip152HbFrom(boolean bip152HbFrom) {
        this.bip152HbFrom = bip152HbFrom;
    }

    public Integer getStartingHeight() {
        return startingHeight;
    }

    public Integer getPresyncedHeaders() {
        return presyncedHeaders;
    }

    public void setPresyncedHeaders(Integer presyncedHeaders) {
        this.presyncedHeaders = presyncedHeaders;
    }

    public void setStartingHeight(Integer startingHeight) {
        this.startingHeight = startingHeight;
    }

    public Integer getSyncedHeaders() {
        return syncedHeaders;
    }

    public void setSyncedHeaders(Integer syncedHeaders) {
        this.syncedHeaders = syncedHeaders;
    }

    public Integer getSyncedBlocks() {
        return syncedBlocks;
    }

    public void setSyncedBlocks(Integer syncedBlocks) {
        this.syncedBlocks = syncedBlocks;
    }

    public List<Integer> getInflight() {
        return inflight;
    }

    public void setInflight(List<Integer> inflight) {
        this.inflight = inflight;
    }

    public Boolean getAddrRelayEnabled() {
        return addrRelayEnabled;
    }

    public void setAddrRelayEnabled(Boolean addrRelayEnabled) {
        this.addrRelayEnabled = addrRelayEnabled;
    }

    public Integer getAddrProcessed() {
        return addrProcessed;
    }

    public void setAddrProcessed(Integer addrProcessed) {
        this.addrProcessed = addrProcessed;
    }

    public Integer getAddrRateLimited() {
        return addrRateLimited;
    }

    public void setAddrRateLimited(Integer addrRateLimited) {
        this.addrRateLimited = addrRateLimited;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }

    public Double getMinFeeFilter() {
        return minFeeFilter;
    }

    public void setMinFeeFilter(Double minFeeFilter) {
        this.minFeeFilter = minFeeFilter;
    }

    public PeerMsgStats getBytesSentPerMsg() {
        return bytesSentPerMsg;
    }

    public void setBytesSentPerMsg(PeerMsgStats bytesSentPerMsg) {
        this.bytesSentPerMsg = bytesSentPerMsg;
    }

    public PeerMsgStats getBytesRecvPerMsg() {
        return bytesRecvPerMsg;
    }

    public void setBytesRecvPerMsg(PeerMsgStats bytesRecvPerMsg) {
        this.bytesRecvPerMsg = bytesRecvPerMsg;
    }

    public String getConnectionType() {
        return connectionType;
    }

    public void setConnectionType(String connectionType) {
        this.connectionType = connectionType;
    }
}