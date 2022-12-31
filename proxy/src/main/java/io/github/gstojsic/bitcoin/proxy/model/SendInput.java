package io.github.gstojsic.bitcoin.proxy.model;

/**
 * @param txId     (string, required) The transaction id
 * @param vOut     (numeric, required) The output number
 * @param sequence (numeric, required) The sequence number
 * @param weight   (numeric, optional, default=Calculated from wallet and solving data) The maximum weight for
 *                 this input, including the weight of the outpoint and sequence number.
 */
public record SendInput(String txId, int vOut, int sequence, Integer weight) {
}
