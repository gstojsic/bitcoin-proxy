package io.github.gstojsic.bitcoin.proxy.model;

import io.github.gstojsic.bitcoin.proxy.util.Pair;

import java.util.List;

/**
 * @param type
 * @param desc          (string) Descriptor to import. If using descriptor, do not also provide address/scriptPubKey,
 *                      scripts, or pubkeys
 * @param scriptPubKey  (string / json, required) Type of scriptPubKey (string for script, json for address). Should not
 *                      be provided if using a descriptor
 * @param timestamp     (integer / string, required) Creation time of the key expressed in UNIX epoch time,
 *                      or the string "now" to substitute the current synced blockchain time
 * @param redeemScript  (string) Allowed only if the scriptPubKey is a P2SH or P2SH-P2WSH address/scriptPubKey
 * @param witnessScript (string) Allowed only if the scriptPubKey is a P2SH-P2WSH or P2WSH address/scriptPubKey
 * @param pubKeys       list of strings giving pubkeys to import
 * @param keys          list of strings giving private keys to import.
 * @param range         (numeric or array) If a ranged descriptor is used, this specifies the end or the range (in
 *                      the form [begin,end]) to import
 * @param internal      (boolean, optional, default=false) Stating whether matching outputs should be treated as not
 *                      incoming payments (also known as change)
 * @param watchOnly     (boolean, optional, default=false) Stating whether matching outputs should be considered watchonly.
 * @param label         (string, optional, default="") Label to assign to the address, only allowed with internal=false
 * @param keypool       (boolean, optional, default=false) Stating whether imported public keys should be added to
 *                      the keypool for when users request new addresses. Only allowed when wallet private keys are disabled
 */
public record ImportData(
        ImportType type,
        String desc,
        String scriptPubKey,
        String timestamp,
        String redeemScript,
        String witnessScript,
        List<String> pubKeys,
        List<String> keys,
        Pair<Integer, Integer> range,
        Boolean internal,
        Boolean watchOnly,
        String label,
        Boolean keypool
) {
}

