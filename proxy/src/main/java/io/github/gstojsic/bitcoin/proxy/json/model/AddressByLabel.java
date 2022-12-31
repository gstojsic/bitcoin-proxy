package io.github.gstojsic.bitcoin.proxy.json.model;

public class AddressByLabel {
    /**
     * (string) Purpose of address ("send" for sending address, "receive" for receiving address)
     */
    private String purpose;

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }
}
