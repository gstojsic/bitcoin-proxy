package io.github.gstojsic.bitcoin.proxy.json.model;

public class WalletDisplayAddress {
    /**
     * (string) The address as confirmed by the signer
     */
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
