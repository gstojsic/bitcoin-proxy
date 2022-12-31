package io.github.gstojsic.bitcoin.proxy.model;

public enum AddressType {
    LEGACY("legacy"),
    P2SH_SEGWIT("p2sh-segwit"),
    BECH32("bech32");

    private final String val;

    AddressType(String val) {
        this.val = val;
    }

    public String getVal() {
        return val;
    }
}
