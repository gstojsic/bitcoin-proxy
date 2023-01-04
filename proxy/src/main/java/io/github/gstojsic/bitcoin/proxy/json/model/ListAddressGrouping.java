package io.github.gstojsic.bitcoin.proxy.json.model;

import io.github.gstojsic.bitcoin.proxy.json.BaseJsonInput;
import io.github.gstojsic.bitcoin.proxy.json.annotation.CustomParser;

import static io.github.gstojsic.bitcoin.proxy.json.BaseJsonInput.ARRAY_END;


public class ListAddressGrouping {

    /**
     * The bitcoin address
     */
    private String address;

    /**
     * The amount in BTC
     */
    private double amount;

    /**
     * The label if set
     */
    private String label;

    public String getAddress() {
        return address;
    }

    public double getAmount() {
        return amount;
    }

    public String getLabel() {
        return label;
    }

    @CustomParser
    public static ListAddressGrouping parse(BaseJsonInput input) {
        ListAddressGrouping grouping = new ListAddressGrouping();
        Character c = input.startListOrNull();
        if (c == null)
            return grouping;

        grouping.address = input.readStringOrNull();
        c = input.readCommaOrListEnd();
        if (c == ARRAY_END)
            return grouping;

        Object[] res = input.readNumberOrNull();
        if (res != null) {
            grouping.amount = Double.parseDouble((String) res[0]);
            c = (char) res[1];
        } else
            c = input.readCommaOrObjectEnd();

        if (c == ARRAY_END)
            return grouping;
        grouping.label = input.readStringOrNull();
        c = input.readCommaOrListEnd();
        if (c != ARRAY_END)
            throw new IllegalStateException("invalid ListAddressGrouping format");
        return grouping;
    }
}