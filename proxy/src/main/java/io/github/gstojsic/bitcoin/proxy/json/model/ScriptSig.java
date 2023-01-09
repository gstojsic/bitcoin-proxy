package io.github.gstojsic.bitcoin.proxy.json.model;

public class ScriptSig {
    /**
     * (string) Disassembly of the signature script
     */
    private String asm;

    /**
     * (string) The raw signature script bytes, hex-encoded
     */
    private String hex;

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
}

