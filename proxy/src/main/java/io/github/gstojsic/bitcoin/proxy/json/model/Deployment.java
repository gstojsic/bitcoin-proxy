package io.github.gstojsic.bitcoin.proxy.json.model;

public class Deployment {
    /**
     * (string) one of "buried", "bip9"
     */
    private String type;

    /**
     * (numeric, optional) height of the first block which the rules are or will be enforced (only for "buried" type, or "bip9" type with "active" status)
     */
    private Integer height;

    /**
     * (boolean) true if the rules are enforced for the mempool and the next block
     */
    private boolean active;

    /**
     * (json object, optional) status of bip9 softforks (only for "bip9" type)
     */
    private Bip9 bip9;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Bip9 getBip9() {
        return bip9;
    }

    public void setBip9(Bip9 bip9) {
        this.bip9 = bip9;
    }
}