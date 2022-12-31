package io.github.gstojsic.bitcoin.proxy.model;

/**
 * @param txId     (string, required) The transaction id
 * @param vOut     (numeric, required) The output number
 * @param sequence (numeric, optional, default=depends on the value of the 'locktime' and 'options.replaceable' arguments)
 *                 The sequence number
 * @param weight   (numeric, optional, default=Calculated from wallet and solving data) The maximum weight for this input,
 *                including the weight of the outpoint and sequence number.
 */
public record PsbtInput(String txId, int vOut, Integer sequence, Integer weight) {
}