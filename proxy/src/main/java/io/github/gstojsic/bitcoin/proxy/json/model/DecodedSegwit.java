package io.github.gstojsic.bitcoin.proxy.json.model;

import io.github.gstojsic.bitcoin.proxy.json.annotation.JsonProperty;

public class DecodedSegwit {
    /**
     * (string) String representation of the script public key
     */
    private String asm;

    /**
     * (string) Hex string of the script public key
     */
    private String hex;

    /**
     * (string) The type of the script public key (e.g. witness_v0_keyhash or witness_v0_scripthash)
     */
    private String type;

    /**
     * (string, optional) The Bitcoin address (only if a well-defined address exists)
     */
    private String address;

    /**
     * (string) Inferred descriptor for the script
     */
    private String desc;

    /**
     * (string) address of the P2SH script wrapping this witness redeem script
     */
    @JsonProperty("p2sh-segwit")
    private String p2shSegwit;

    public String getAsm() {
        return asm;
    }

    public void setAsm(String asm) {
        this.asm = asm;
    }

    public String getHex() {
        return hex;
    }

    public void setHex(String hex) {
        this.hex = hex;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getP2shSegwit() {
        return p2shSegwit;
    }

    public void setP2shSegwit(String p2shSegwit) {
        this.p2shSegwit = p2shSegwit;
    }
}
