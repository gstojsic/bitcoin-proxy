package io.github.gstojsic.bitcoin.proxy.model;

public enum SigHashType {
    ALL("ALL"),
    NONE("NONE"),
    SINGLE("SINGLE"),
    ALL_ANYONECANPAY("ALL|ANYONECANPAY"),
    NONE_ANYONECANPAY("NONE|ANYONECANPAY"),
    SINGLE_ANYONECANPAY("SINGLE|ANYONECANPAY");

    private final String val;

    SigHashType(String val) {
        this.val = val;
    }

    public String getVal() {
        return val;
    }
}
