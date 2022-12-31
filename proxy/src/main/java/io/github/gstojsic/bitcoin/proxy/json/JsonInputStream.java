package io.github.gstojsic.bitcoin.proxy.json;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

public class JsonInputStream extends BaseJsonInput {

    private final InputStream in;

    public JsonInputStream(InputStream in) {
        this.in = in;
    }

    @Override
    public char readChar() {
        try {
            int i = in.read();
            if (i == -1)
                throw new EOFException();
            return (char) i;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
