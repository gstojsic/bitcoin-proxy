package io.github.gstojsic.bitcoin.proxy.model;

/**
 * @param confTarget   (numeric, optional, default=wallet -txconfirmtarget) Confirmation target in blocks
 * @param feeRate      (numeric or string, optional, default=not set, fall back to wallet fee estimation)
 *                     Specify a fee rate in sat/vB instead of relying on the built-in fee estimator.
 * @param replaceable  (boolean, optional, default=true) Whether the new transaction should still be
 *                     marked bip-125 replaceable
 * @param estimateMode (string, optional, default="unset") The fee estimate mode
 */
public record BumpFeeOptions(Integer confTarget, String feeRate, Boolean replaceable, EstimateMode estimateMode) {
}

