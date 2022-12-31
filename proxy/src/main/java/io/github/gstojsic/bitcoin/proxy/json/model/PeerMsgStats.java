package io.github.gstojsic.bitcoin.proxy.json.model;

import io.github.gstojsic.bitcoin.proxy.json.annotation.JsonProperty;

public class PeerMsgStats {
    private Long addrv2;

    @JsonProperty("blocktxn")
    private Long blockTxn;

    @JsonProperty("cmpctblock")
    private Long cmpctBlock;

    @JsonProperty("feefilter")
    private Long feeFilter;

    @JsonProperty("getaddr")
    private Long getAddr;

    @JsonProperty("getblocktxn")
    private Long getBlockTxn;

    @JsonProperty("getdata")
    private Long getData;

    @JsonProperty("getheaders")
    private Long getHeaders;
    private Long headers;
    private Long inv;

    @JsonProperty("notfound")
    private Long notFound;
    private Long ping;
    private Long pong;

    @JsonProperty("sendaddrv2")
    private Long sendAddrV2;

    @JsonProperty("sendcmpct")
    private Long sendCmpct;

    @JsonProperty("sendheaders")
    private Long sendHeaders;
    private Long tx;

    @JsonProperty("verack")
    private Long verAck;
    private Long version;

    @JsonProperty("wtxidrelay")
    private Long wtxIdRelay;

    public Long getAddrv2() {
        return addrv2;
    }

    public void setAddrv2(Long addrv2) {
        this.addrv2 = addrv2;
    }

    public Long getBlockTxn() {
        return blockTxn;
    }

    public void setBlockTxn(Long blockTxn) {
        this.blockTxn = blockTxn;
    }

    public Long getCmpctBlock() {
        return cmpctBlock;
    }

    public void setCmpctBlock(Long cmpctBlock) {
        this.cmpctBlock = cmpctBlock;
    }

    public Long getFeeFilter() {
        return feeFilter;
    }

    public void setFeeFilter(Long feeFilter) {
        this.feeFilter = feeFilter;
    }

    public Long getGetAddr() {
        return getAddr;
    }

    public void setGetAddr(Long getAddr) {
        this.getAddr = getAddr;
    }

    public Long getGetBlockTxn() {
        return getBlockTxn;
    }

    public void setGetBlockTxn(Long getBlockTxn) {
        this.getBlockTxn = getBlockTxn;
    }

    public Long getGetData() {
        return getData;
    }

    public void setGetData(Long getData) {
        this.getData = getData;
    }

    public Long getGetHeaders() {
        return getHeaders;
    }

    public void setGetHeaders(Long getHeaders) {
        this.getHeaders = getHeaders;
    }

    public Long getHeaders() {
        return headers;
    }

    public void setHeaders(Long headers) {
        this.headers = headers;
    }

    public Long getInv() {
        return inv;
    }

    public void setInv(Long inv) {
        this.inv = inv;
    }

    public Long getNotFound() {
        return notFound;
    }

    public void setNotFound(Long notFound) {
        this.notFound = notFound;
    }

    public Long getPing() {
        return ping;
    }

    public void setPing(Long ping) {
        this.ping = ping;
    }

    public Long getPong() {
        return pong;
    }

    public void setPong(Long pong) {
        this.pong = pong;
    }

    public Long getSendAddrV2() {
        return sendAddrV2;
    }

    public void setSendAddrV2(Long sendAddrV2) {
        this.sendAddrV2 = sendAddrV2;
    }

    public Long getSendCmpct() {
        return sendCmpct;
    }

    public void setSendCmpct(Long sendCmpct) {
        this.sendCmpct = sendCmpct;
    }

    public Long getSendHeaders() {
        return sendHeaders;
    }

    public void setSendHeaders(Long sendHeaders) {
        this.sendHeaders = sendHeaders;
    }

    public Long getTx() {
        return tx;
    }

    public void setTx(Long tx) {
        this.tx = tx;
    }

    public Long getVerAck() {
        return verAck;
    }

    public void setVerAck(Long verAck) {
        this.verAck = verAck;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Long getWtxIdRelay() {
        return wtxIdRelay;
    }

    public void setWtxIdRelay(Long wtxIdRelay) {
        this.wtxIdRelay = wtxIdRelay;
    }
}