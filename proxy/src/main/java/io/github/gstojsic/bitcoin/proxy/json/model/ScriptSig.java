package io.github.gstojsic.bitcoin.proxy.json.model;

public class ScriptSig {
    /**
     * (string) asm
     */
    private String asm;

    /**
     * (string) hex
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

