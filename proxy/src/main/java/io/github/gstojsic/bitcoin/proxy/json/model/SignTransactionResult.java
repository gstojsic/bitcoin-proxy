package io.github.gstojsic.bitcoin.proxy.json.model;

import java.util.List;

public class SignTransactionResult {
    /**
     * (string) The hex-encoded raw transaction with signature(s)
     */
    private String hex;

    /**
     * (boolean) If the transaction has a complete set of signatures
     */
    private boolean complete;

    /**
     * (json array, optional) Script verification errors (if there are any)
     */
    private List<SignTransactionError> errors;

    public String getHex() {
        return hex;
    }

    public void setHex(String hex) {
        this.hex = hex;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public List<SignTransactionError> getErrors() {
        return errors;
    }

    public void setErrors(List<SignTransactionError> errors) {
        this.errors = errors;
    }
}