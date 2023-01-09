package io.github.gstojsic.bitcoin.proxy.json.model;

import io.github.gstojsic.bitcoin.proxy.json.annotation.JsonProperty;

public class TxOutUnspendables {

    /**
     * (numeric) The unspendable amount of the Genesis block subsidy
     */
    @JsonProperty("genesis_block")
    private double genesisBlock;

    /**
     * (numeric) Transactions overridden by duplicates (no longer possible with BIP30)
     */
    private double bip30;

    /**
     * (numeric) Amounts sent to scripts that are unspendable (for example OP_RETURN outputs)
     */
    private double scripts;

    /**
     * (numeric) Fee rewards that miners did not claim in their coinbase transaction
     */
    @JsonProperty("unclaimed_rewards")
    private double unclaimedRewards;

    public double getGenesisBlock() {
        return genesisBlock;
    }

    public void setGenesisBlock(double genesisBlock) {
        this.genesisBlock = genesisBlock;
    }

    public double getBip30() {
        return bip30;
    }

    public void setBip30(double bip30) {
        this.bip30 = bip30;
    }

    public double getScripts() {
        return scripts;
    }

    public void setScripts(double scripts) {
        this.scripts = scripts;
    }

    public double getUnclaimedRewards() {
        return unclaimedRewards;
    }

    public void setUnclaimedRewards(double unclaimedRewards) {
        this.unclaimedRewards = unclaimedRewards;
    }
}