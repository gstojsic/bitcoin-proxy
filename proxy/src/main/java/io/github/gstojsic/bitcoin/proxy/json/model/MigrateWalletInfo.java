package io.github.gstojsic.bitcoin.proxy.json.model;

import io.github.gstojsic.bitcoin.proxy.json.annotation.JsonProperty;

public class MigrateWalletInfo {

    /**
     * (string) The name of the primary migrated wallet
     */
    @JsonProperty("wallet_name")
    private String walletName;

    /**
     * (string, optional) The name of the migrated wallet containing the watchonly scripts
     */
    @JsonProperty("watchonly_name")
    private String watchOnlyName;

    /**
     * (string, optional) The name of the migrated wallet containing solvable but not watched scripts
     */
    @JsonProperty("solvables_name")
    private String solvablesName;

    /**
     * (string) The location of the backup of the original wallet
     */
    @JsonProperty("backup_path")
    private String backupPath;

    public String getWalletName() {
        return walletName;
    }

    public void setWalletName(String walletName) {
        this.walletName = walletName;
    }

    public String getWatchOnlyName() {
        return watchOnlyName;
    }

    public void setWatchOnlyName(String watchOnlyName) {
        this.watchOnlyName = watchOnlyName;
    }

    public String getSolvablesName() {
        return solvablesName;
    }

    public void setSolvablesName(String solvablesName) {
        this.solvablesName = solvablesName;
    }

    public String getBackupPath() {
        return backupPath;
    }

    public void setBackupPath(String backupPath) {
        this.backupPath = backupPath;
    }
}