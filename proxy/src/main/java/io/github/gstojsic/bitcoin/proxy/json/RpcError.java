package io.github.gstojsic.bitcoin.proxy.json;

public class RpcError {
    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return """
                RpcError{ code=%d, message=%s }
                """.formatted(code, message);
    }
}
