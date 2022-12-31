package io.github.gstojsic.bitcoin.proxy.model;

import java.util.List;

/**
 * @param addInputs              (boolean, optional, default=false) If inputs are specified, automatically include more
 *                               if they are not enough.
 * @param includeUnsafe          (boolean, optional, default=false) Include inputs that are not safe to spend
 *                               (unconfirmed transactions from outside keys and unconfirmed replacement transactions).
 * @param addToWallet            (boolean, optional, default=true) When false, returns a serialized transaction which
 *                               will not be added to the wallet or broadcast
 * @param changeAddress          (string, optional, default=pool address) The bitcoin address to receive the change
 * @param changePosition         (numeric, optional, default=random) The index of the change output
 * @param changeType             (string, optional, default=set by -changetype) The output type to use.
 * @param feeRate                (numeric or string, optional, default=not set, fall back to wallet fee estimation)
 *                               Specify a fee rate in sat/vB.
 * @param includeWatching        (boolean, optional, default=true for watch-only wallets, otherwise false) Also select
 *                               inputs which are watch only.
 * @param inputs                 Specify inputs instead of adding them automatically
 * @param lockTime               (numeric, optional, default=0) Raw locktime. Non-0 value also locktime-activates inputs
 * @param lockUnspents           (boolean, optional, default=false) Lock selected unspent outputs
 * @param psbt                   (boolean, optional, default=automatic) Always return a PSBT, implies add_to_wallet=false.
 * @param subtractFeeFromOutputs Outputs to subtract the fee from, specified as integer indices
 * @param confTarget             (numeric, optional, default=wallet -txconfirmtarget) Confirmation target in blocks
 * @param estimateMode           (string, optional, default="unset") The fee estimate mode
 * @param replaceable            (boolean, optional, default=wallet default) Marks this transaction as BIP125-replaceable
 * @param solvingData            Keys and scripts needed for producing a final transaction with a dummy signature. see {@link SolveData}
 */
public record SendOptions(
        Boolean addInputs,
        Boolean includeUnsafe,
        Boolean addToWallet,
        String changeAddress,
        Integer changePosition,
        AddressType changeType,
        String feeRate,
        Boolean includeWatching,
        List<SendInput> inputs,
        Integer lockTime,
        Boolean lockUnspents,
        Boolean psbt,
        List<Integer> subtractFeeFromOutputs,
        Integer confTarget,
        EstimateMode estimateMode,
        Boolean replaceable,
        SolveData solvingData
) {
}
