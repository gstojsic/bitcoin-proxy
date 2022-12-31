package io.github.gstojsic.bitcoin.proxy.model;

import java.util.List;

/**
 * @param pubKeys     Public keys involved in this transaction.
 * @param scripts     Scripts involved in this transaction.
 * @param descriptors Descriptors that provide solving data for this transaction.
 */
public record SolveData(List<String> pubKeys, List<String> scripts, List<String> descriptors) {
}