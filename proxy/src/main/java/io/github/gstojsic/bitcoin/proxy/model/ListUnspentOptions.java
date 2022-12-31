package io.github.gstojsic.bitcoin.proxy.model;

/**
 * @param minAmount    (numeric or string, optional, default="0.00") Minimum value of each UTXO in BTC
 * @param maxAmount    (numeric or string, optional, default=unlimited) Maximum value of each UTXO in BTC
 * @param maxCount     (numeric, optional, default=unlimited) Maximum number of UTXOs
 * @param minSumAmount (numeric or string, optional, default=unlimited) Minimum sum value of all UTXOs in BTC
 */
public record ListUnspentOptions(String minAmount, String maxAmount, Integer maxCount, String minSumAmount) {
}

