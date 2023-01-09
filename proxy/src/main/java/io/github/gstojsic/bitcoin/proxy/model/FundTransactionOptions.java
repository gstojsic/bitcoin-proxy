package io.github.gstojsic.bitcoin.proxy.model;

import java.util.List;

/**
 * Used to invoke fundrawtransaction method
 *
 * @param addInputs              (boolean, optional, default=true) For a transaction with existing inputs, automatically
 *                               include more if they are not enough.
 * @param includeUnsafe          (boolean, optional, default=false) Include inputs that are not safe to spend
 *                               (unconfirmed transactions from outside keys and unconfirmed replacement transactions)
 * @param changeAddress          (string, optional, default=automatic) The bitcoin address to receive the change
 * @param changePosition         (numeric, optional, default=random) The index of the change output
 * @param changeType             (string, optional, default=set by -changetype) The output type to use. Only valid if
 *                               changeAddress is not specified. Options are "legacy", "p2sh-segwit", "bech32", and "bech32m".
 * @param includeWatching        (boolean, optional, default=true for watch-only wallets, otherwise false) Also select
 *                               inputs which are watch only.
 * @param lockUnspent            (boolean, optional, default=false) Lock selected unspent outputs
 * @param feeRateSat             (numeric or string, optional, default=not set, fall back to wallet fee estimation)
 *                               Specify a fee rate in sat/vB.
 * @param feeRateBtc             (numeric or string, optional, default=not set, fall back to wallet fee estimation)
 *                               Specify a fee rate in BTC/kvB.
 * @param subtractFeeFromOptions list of vout indices from which to subtract the fee. The fee will be equally deducted
 *                               from the amount of each specified output.
 * @param inputWeights           Inputs and their corresponding weights. See {@link InputWeight}
 * @param confTarget             (numeric, optional, default=wallet -txconfirmtarget) Confirmation target in blocks
 * @param estimateMode           (string, optional, default="unset") The fee estimate mode
 * @param replaceable            (boolean, optional, default=wallet default) Marks this transaction as BIP125-replaceable.
 * @param solvingData            Keys and scripts needed for producing a final transaction with a dummy signature.
 *                               See {@link SolveData}
 */
public record FundTransactionOptions(
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
        List<InputWeight> inputWeights,
        Integer confTarget,
        EstimateMode estimateMode,
        Boolean replaceable,
        SolveData solvingData
) {
}

