package io.github.gstojsic.bitcoin.proxy.json.model;

public class DecodedScript {
    /**
     * (string) Script public key
     */
    private String asm;

    /**
     * (string) Inferred descriptor for the script
     */
    private String desc;

    /**
     * (string) The output type (e.g. nonstandard, pubkey, pubkeyhash, scripthash, multisig, nulldata, witness_v0_scripthash, witness_v0_keyhash, witness_v1_taproot, witness_unknown)
     */
    private String type;

    /**
     * (string, optional) The Bitcoin address (only if a well-defined address exists)
     */
    private String address;

    /**
     * (string, optional) address of P2SH script wrapping this redeem script (not returned for types that should not be wrapped)
     */
    private String p2sh;

    /**
     * (json object, optional) Result of a witness script public key wrapping this redeem script (not returned for types that should not be wrapped)
     */
    private DecodedSegwit segwit;

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

    public String getP2sh() {
        return p2sh;
    }

    public void setP2sh(String p2sh) {
        this.p2sh = p2sh;
    }

    public DecodedSegwit getSegwit() {
        return segwit;
    }

    public void setSegwit(DecodedSegwit segwit) {
        this.segwit = segwit;
    }
}
