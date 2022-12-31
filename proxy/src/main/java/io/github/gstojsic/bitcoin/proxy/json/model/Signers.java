package io.github.gstojsic.bitcoin.proxy.json.model;

import java.util.List;

public class Signers {
    private List<Signer> signers;

    public List<Signer> getSigners() {
        return signers;
    }

    public void setSigners(List<Signer> signers) {
        this.signers = signers;
    }
}
