package io.github.gstojsic.bitcoin.proxy.json.model;

public class BlockFilter {

    /**
     * (string) the hex-encoded filter data
     */
    private String filter;

    /**
     * (string) the hex-encoded filter header
     */
    private String header;

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }
}
