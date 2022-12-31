package io.github.gstojsic.bitcoin.proxy.model;

/**
 * @param txId (string, required) The transaction id
 * @param vOut (numeric, required) The output number
 */
public record Transaction(String txId, int vOut) {
}

