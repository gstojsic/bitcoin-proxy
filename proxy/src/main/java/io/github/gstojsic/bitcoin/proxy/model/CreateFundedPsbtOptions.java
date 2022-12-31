package io.github.gstojsic.bitcoin.proxy.model;

import java.util.List;

/**
 * @param addInputs              (boolean, optional, default=false) If inputs are specified, automatically include more
 *                               if they are not enough.
 * @param includeUnsafe          (boolean, optional, default=false) Include inputs that are not safe to spend
 * @param changeAddress          (string, optional, default=pool address) The bitcoin address to receive the change
 * @param changePosition         (numeric, optional, default=random) The index of the change output
 * @param changeType             (string, optional, default=set by -changetype) The output type to use
 * @param includeWatching        (boolean, optional, default=true for watch-only wallets, otherwise false) Also select
 *                               inputs which are watch only
 * @param lockUnspent            (boolean, optional, default=false) Lock selected unspent outputs
 * @param feeRateSat             (numeric or string, optional, default=not set, fall back to wallet fee estimation)
 *                               Specify a fee rate in sat/vB.
 * @param feeRateBtc             (numeric or string, optional, default=not set, fall back to wallet fee estimation)
 *                               Specify a fee rate in BTC/kvB.
 * @param subtractFeeFromOptions The outputs to subtract the fee from.
 * @param confTarget             (numeric, optional, default=wallet -txconfirmtarget) Confirmation target in blocks
 * @param estimateMode           (string, optional, default="unset") The fee estimate mode
 * @param replaceable            (boolean, optional, default=wallet default) Marks this transaction as BIP125-replaceable
 * @param solvingData            Keys and scripts needed for producing a final transaction with a dummy signature. see {@link SolveData}
 */
public record CreateFundedPsbtOptions(
        Boolean addInputs,
        Boolean includeUnsafe,
        String changeAddress,
        Integer changePosition,
        AddressType changeType,
        Boolean includeWatching,
        Boolean lockUnspent,
        String feeRateSat,
        String feeRateBtc,
        List<Integer> subtractFeeFromOptions,
        Integer confTarget,
        EstimateMode estimateMode,
        Boolean replaceable,
        SolveData solvingData
) {
}

