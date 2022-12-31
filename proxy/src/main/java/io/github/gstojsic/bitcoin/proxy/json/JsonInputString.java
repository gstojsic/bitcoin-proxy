package io.github.gstojsic.bitcoin.proxy.json;

public class JsonInputString extends BaseJsonInput {
    private final String in;
    private int position = 0;

    public JsonInputString(String in) {
        this.in = in;
    }

    @Override
    public char readChar() {
        return in.charAt(position++);
    }
}
