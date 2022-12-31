package io.github.gstojsic.bitcoin.proxy.json.model;

import java.util.List;

public class MultisigAddress {
    /**
     * (string) The value of the new multisig address.
     */
    private String address;

    /**
     * (string) The string value of the hex-encoded redemption script.
     */
    private String redeemScript;

    /**
     * (string) The descriptor for this multisig
     */
    private String descriptor;

    /**
     * (json array, optional) Any warnings resulting from the creation of this multisig
     */
    private List<String> warnings;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRedeemScript() {
        return redeemScript;
    }

    public void setRedeemScript(String redeemScript) {
        this.redeemScript = redeemScript;
    }

    public String getDescriptor() {
        return descriptor;
    }

    public void setDescriptor(String descriptor) {
        this.descriptor = descriptor;
    }

    public List<String> getWarnings() {
        return warnings;
    }

    public void setWarnings(List<String> warnings) {
        this.warnings = warnings;
    }
}