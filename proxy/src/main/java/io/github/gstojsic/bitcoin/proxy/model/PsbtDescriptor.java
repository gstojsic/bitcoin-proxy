package io.github.gstojsic.bitcoin.proxy.model;

import io.github.gstojsic.bitcoin.proxy.util.Pair;

/**
 * @param desc  (string, required) An output descriptor
 * @param range (numeric or array, optional, default=1000) Up to what index HD chains should be explored (either end or [begin,end])
 */
public record PsbtDescriptor(String desc, Pair<Integer, Integer> range) {
}

