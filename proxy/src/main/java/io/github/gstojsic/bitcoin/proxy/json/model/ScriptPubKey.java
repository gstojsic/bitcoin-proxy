package io.github.gstojsic.bitcoin.proxy.json.model;

public class ScriptPubKey {
    /**
     * (string)
     */
    private String asm;

    /**
     * (string) Inferred descriptor for the output
     */
    private String desc;

    /**
     * (string)
     */
    private String hex;

    /**
     * (string, optional) The Bitcoin address (only if a well-defined address exists)
     */
    private String address;

    /**
     * (string) The type, eg pubkeyhash
     */
    private String type;

    public String getAsm() {
        return asm;
    }

    public void setAsm(String asm) {
        this.asm = asm;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getHex() {
        return hex;
    }

    public void setHex(String hex) {
        this.hex = hex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}