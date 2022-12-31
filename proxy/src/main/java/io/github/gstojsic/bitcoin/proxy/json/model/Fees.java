package io.github.gstojsic.bitcoin.proxy.json.model;

public class Fees {
    /**
     * (numeric) transaction fee in BTC
     */
    private double base;

    /**
     * (numeric) transaction fee with fee deltas used for mining priority in BTC
     */
    private double modified;

    /**
     * (numeric) modified fees (see above) of in-mempool ancestors (including this one) in BTC
     */
    private double ancestor;

    /**
     * (numeric) modified fees (see above) of in-mempool descendants (including this one) in BTC
     */
    private double descendant;

    public double getBase() {
        return base;
    }

    public void setBase(double base) {
        this.base = base;
    }

    public double getModified() {
        return modified;
    }

    public void setModified(double modified) {
        this.modified = modified;
    }

    public double getAncestor() {
        return ancestor;
    }

    public void setAncestor(double ancestor) {
        this.ancestor = ancestor;
    }

    public double getDescendant() {
        return descendant;
    }

    public void setDescendant(double descendant) {
        this.descendant = descendant;
    }
}
