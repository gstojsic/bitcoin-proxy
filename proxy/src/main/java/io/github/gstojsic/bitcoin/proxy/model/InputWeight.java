package io.github.gstojsic.bitcoin.proxy.model;

/**
 * @param txId   (string, required) The transaction id
 * @param vOut   (numeric, required) The output index
 * @param weight (numeric, required) The maximum weight for this input, including the weight of the outpoint and
 *               sequence number. Note that serialized signature sizes are not guaranteed to be consistent, so
 *               the maximum DER signatures size of 73 bytes should be used when considering ECDSA signatures.Remember
 *               to convert serialized sizes to weight units when necessary.
 */
public record InputWeight(String txId, int vOut, int weight) {
}

