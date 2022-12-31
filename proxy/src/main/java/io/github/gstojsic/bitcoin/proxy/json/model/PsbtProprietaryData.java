package io.github.gstojsic.bitcoin.proxy.json.model;

public class PsbtProprietaryData {
    /**
     * (string) The hex string for the proprietary identifier
     */
    private String identifier;

    /**
     * (numeric) The number for the subtype
     */
    private int subtype;

    /**
     * (string) The hex for the key
     */
    private String key;

    /**
     * (string) The hex for the value
     */
    private String value;

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public int getSubtype() {
        return subtype;
    }

    public void setSubtype(int subtype) {
        this.subtype = subtype;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
