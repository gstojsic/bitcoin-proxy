package io.github.gstojsic.bitcoin.proxy.exception;

import io.github.gstojsic.bitcoin.proxy.json.RpcError;

public class RpcException extends RuntimeException {
    private final RpcError error;

    public RpcException(String message, RpcError error) {
        super(message + " : " + error);
        this.error = error;
    }

    public RpcError getError() {
        return error;
    }
}
