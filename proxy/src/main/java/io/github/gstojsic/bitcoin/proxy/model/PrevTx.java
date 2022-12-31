package io.github.gstojsic.bitcoin.proxy.model;

/**
 * @param txId          (string, required) the transaction id
 * @param vOut          (numeric, required) the output number
 * @param scriptPubKey  (string, required) script key
 * @param redeemScript  (string) (required for P2SH) redeem script
 * @param witnessScript (string) (required for P2WSH or P2SH-P2WSH) witness script
 * @param amount        (numeric or string) (required for Segwit inputs) the amount spent
 */
public record PrevTx(
        String txId,
        int vOut,
        String scriptPubKey,
        String redeemScript,
        String witnessScript,
        String amount
) {
}