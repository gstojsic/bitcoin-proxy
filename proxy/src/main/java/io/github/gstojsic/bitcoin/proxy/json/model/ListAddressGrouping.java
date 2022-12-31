package io.github.gstojsic.bitcoin.proxy.json.model;

import io.github.gstojsic.bitcoin.proxy.json.BaseJsonInput;
import io.github.gstojsic.bitcoin.proxy.json.annotation.CustomParser;

import static io.github.gstojsic.bitcoin.proxy.json.BaseJsonInput.ARRAY_END;


public class ListAddressGrouping {

    private String address;
    private double amount;
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
    public void parse(BaseJsonInput input) {
        Character c = input.startListOrNull();
        if (c == null)
            return;

        address = input.readStringOrNull();
        c = input.readCommaOrListEnd();
        if (c == ARRAY_END)
            return;

        Object[] res = input.readNumberOrNull();
        if (res != null) {
            amount = Double.parseDouble((String) res[0]);
            c = (char) res[1];
        } else
            c = input.readCommaOrObjectEnd();

        if (c == ARRAY_END)
            return;
        label = input.readStringOrNull();
        c = input.readCommaOrListEnd();
        if (c != ARRAY_END)
            throw new IllegalStateException("invalid ListAddressGrouping format");
    }
    /*
        [
          "bcrt1q8h8z7f8xxytj9f65v6wx4vfuud48v3vs6kglw7",
          "50.00000000",
          "aliceMining"
        ]
     */
}