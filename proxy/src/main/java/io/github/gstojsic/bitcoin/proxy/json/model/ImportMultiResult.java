package io.github.gstojsic.bitcoin.proxy.json.model;

import io.github.gstojsic.bitcoin.proxy.json.RpcError;

import java.util.List;

public class ImportMultiResult {
    /**
     * (boolean)
     */
    private boolean success;
    /**
     * (json array, optional) (string)
     */
    private List<String> warnings;

    /**
     * (json object, optional) JSONRPC error
     */
    private RpcError error;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<String> getWarnings() {
        return warnings;
    }

    public void setWarnings(List<String> warnings) {
        this.warnings = warnings;
    }

    public RpcError getError() {
        return error;
    }

    public void setError(RpcError error) {
        this.error = error;
    }
}
