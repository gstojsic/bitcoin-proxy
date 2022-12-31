package io.github.gstojsic.bitcoin.proxy.json;

public class RpcResponse<T> {
    private String id;
    private T result;
    private RpcError error;

    public RpcResponse() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public RpcError getError() {
        return error;
    }

    public void setError(RpcError error) {
        this.error = error;
    }
}
