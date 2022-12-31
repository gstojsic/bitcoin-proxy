package io.github.gstojsic.bitcoin.proxy.json.model;

import io.github.gstojsic.bitcoin.proxy.json.annotation.JsonProperty;

public class WalletInfo {
    /**
     * (string) the wallet name
     */
    @JsonProperty("walletname")
    private String walletName;

    /**
     * (numeric) the wallet version
     */
    @JsonProperty("walletversion")
    private int walletVersion;

    /**
     * (string) the database format (bdb or sqlite)
     */
    private String format;

    /**
     * (numeric) DEPRECATED. Identical to getbalances().mine.trusted
     */
    @Deprecated
    private double balance;

    /**
     * (numeric) DEPRECATED. Identical to getbalances().mine.untrusted_pending
     */
    @JsonProperty("unconfirmed_balance")
    @Deprecated
    private double unconfirmedBalance;

    /**
     * (numeric) DEPRECATED. Identical to getbalances().mine.immature
     */
    @JsonProperty("immature_balance")
    @Deprecated
    private double immatureBalance;

    /**
     * (numeric) the total number of transactions in the wallet
     */
    @JsonProperty("txcount")
    private int txCount;

    /**
     * (numeric, optional) the UNIX epoch time of the oldest pre-generated key in the key pool. Legacy wallets only.
     */
    @JsonProperty("keypoololdest")
    private Long keypoolOldest;

    /**
     * (numeric) how many new keys are pre-generated (only counts external keys)
     */
    @JsonProperty("keypoolsize")
    private int keypoolSize;

    /**
     * (numeric, optional) how many new keys are pre-generated for internal use (used for change outputs, only appears if the wallet is using this feature, otherwise external keys are used)
     */
    @JsonProperty("keypoolsize_hd_internal")
    private Integer keypoolSizeHdInternal;

    /**
     * (numeric, optional) the UNIX epoch time until which the wallet is unlocked for transfers, or 0 if the wallet is locked (only present for passphrase-encrypted wallets)
     */
    @JsonProperty("unlocked_until")
    private Long unlockedUntil;

    /**
     * (numeric) the transaction fee configuration, set in BTC/kvB
     */
    @JsonProperty("paytxfee")
    private double payTxFee;

    /**
     * (string, optional) the Hash160 of the HD seed (only present when HD is enabled)
     */
    @JsonProperty("hdseedid")
    private String hdSeedId;

    /**
     * (boolean) false if privatekeys are disabled for this wallet (enforced watch-only wallet)
     */
    @JsonProperty("private_keys_enabled")
    private boolean privateKeysEnabled;

    /**
     * (boolean) whether this wallet tracks clean/dirty coins in terms of reuse
     */
    @JsonProperty("avoid_reuse")
    private boolean avoidReuse;

    /**
     * WTF? TODO fix this somehow
     * (json object) current scanning details, or false if no scan is in progress
     */
    private transient boolean scanning;

    /**
     * (boolean) whether this wallet uses descriptors for scriptPubKey management
     */
    private boolean descriptors;

    /**
     * (boolean) whether this wallet is configured to use an external signer such as a hardware wallet
     */
    @JsonProperty("external_signer")
    private boolean externalSigner;

    public String getWalletName() {
        return walletName;
    }

    public void setWalletName(String walletName) {
        this.walletName = walletName;
    }

    public int getWalletVersion() {
        return walletVersion;
    }

    public void setWalletVersion(int walletVersion) {
        this.walletVersion = walletVersion;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getUnconfirmedBalance() {
        return unconfirmedBalance;
    }

    public void setUnconfirmedBalance(double unconfirmedBalance) {
        this.unconfirmedBalance = unconfirmedBalance;
    }

    public double getImmatureBalance() {
        return immatureBalance;
    }

    public void setImmatureBalance(double immatureBalance) {
        this.immatureBalance = immatureBalance;
    }

    public int getTxCount() {
        return txCount;
    }

    public void setTxCount(int txCount) {
        this.txCount = txCount;
    }

    public Long getKeypoolOldest() {
        return keypoolOldest;
    }

    public void setKeypoolOldest(Long keypoolOldest) {
        this.keypoolOldest = keypoolOldest;
    }

    public int getKeypoolSize() {
        return keypoolSize;
    }

    public void setKeypoolSize(int keypoolSize) {
        this.keypoolSize = keypoolSize;
    }

    public Integer getKeypoolSizeHdInternal() {
        return keypoolSizeHdInternal;
    }

    public void setKeypoolSizeHdInternal(Integer keypoolSizeHdInternal) {
        this.keypoolSizeHdInternal = keypoolSizeHdInternal;
    }

    public Long getUnlockedUntil() {
        return unlockedUntil;
    }

    public void setUnlockedUntil(Long unlockedUntil) {
        this.unlockedUntil = unlockedUntil;
    }

    public double getPayTxFee() {
        return payTxFee;
    }

    public void setPayTxFee(double payTxFee) {
        this.payTxFee = payTxFee;
    }

    public String getHdSeedId() {
        return hdSeedId;
    }

    public void setHdSeedId(String hdSeedId) {
        this.hdSeedId = hdSeedId;
    }

    public boolean isPrivateKeysEnabled() {
        return privateKeysEnabled;
    }

    public void setPrivateKeysEnabled(boolean privateKeysEnabled) {
        this.privateKeysEnabled = privateKeysEnabled;
    }

    public boolean isAvoidReuse() {
        return avoidReuse;
    }

    public void setAvoidReuse(boolean avoidReuse) {
        this.avoidReuse = avoidReuse;
    }

    public boolean isScanning() {
        return scanning;
    }

    public void setScanning(boolean scanning) {
        this.scanning = scanning;
    }

    public boolean isDescriptors() {
        return descriptors;
    }

    public void setDescriptors(boolean descriptors) {
        this.descriptors = descriptors;
    }

    public boolean isExternalSigner() {
        return externalSigner;
    }

    public void setExternalSigner(boolean externalSigner) {
        this.externalSigner = externalSigner;
    }
}