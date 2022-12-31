package io.github.gstojsic.bitcoin.proxy.json.model;

import java.util.List;

public class Wallets {
    /**
     * array of wallet names
     */
    private List<WalletName> wallets;

    public List<WalletName> getWallets() {
        return wallets;
    }

    public void setWallets(List<WalletName> wallets) {
        this.wallets = wallets;
    }
}
