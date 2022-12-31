package io.github.gstojsic.bitcoin.proxy.json.model;

import io.github.gstojsic.bitcoin.proxy.json.annotation.JsonProperty;

public class BalancesData {
    /**
     * (json object) balances from outputs that the wallet can sign
     */
    private BalancesMineData mine;

    /**
     * (json object) watchonly balances (not present if wallet does not watch anything)
     */
    @JsonProperty("watchonly")
    private BalancesWatchOnlyData watchOnly;

    public BalancesMineData getMine() {
        return mine;
    }

    public void setMine(BalancesMineData mine) {
        this.mine = mine;
    }

    public BalancesWatchOnlyData getWatchOnly() {
        return watchOnly;
    }

    public void setWatchOnly(BalancesWatchOnlyData watchOnly) {
        this.watchOnly = watchOnly;
    }
}
