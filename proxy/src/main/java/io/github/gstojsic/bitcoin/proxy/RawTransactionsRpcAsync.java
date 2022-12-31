package io.github.gstojsic.bitcoin.proxy;

import io.github.gstojsic.bitcoin.proxy.json.model.DecodedPsbt;
import io.github.gstojsic.bitcoin.proxy.json.model.DecodedScript;
import io.github.gstojsic.bitcoin.proxy.json.model.FinalizedPsbt;
import io.github.gstojsic.bitcoin.proxy.json.model.MempoolAccept;
import io.github.gstojsic.bitcoin.proxy.json.model.PsbtAnalysis;
import io.github.gstojsic.bitcoin.proxy.json.model.RawTransaction;
import io.github.gstojsic.bitcoin.proxy.json.model.SignTransactionResult;
import io.github.gstojsic.bitcoin.proxy.json.model.TransactionFunding;
import io.github.gstojsic.bitcoin.proxy.model.FundTransactionOptions;
import io.github.gstojsic.bitcoin.proxy.model.PrevTx;
import io.github.gstojsic.bitcoin.proxy.model.PsbtDescriptor;
import io.github.gstojsic.bitcoin.proxy.model.PsbtInput;
import io.github.gstojsic.bitcoin.proxy.model.SigHashType;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public interface RawTransactionsRpcAsync {

    /**
     * <p>Calls analyzepsbt method on the bitcoin node which analyzes and provides information about the current status
     * of a PSBT (partially signed bitcoin transaction) and its inputs</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.analyzepsbt);</pre>
     *
     * @param psbt a base64 string of a PSBT
     * @return info about the psbt. See {@link PsbtAnalysis}
     */
    CompletableFuture<PsbtAnalysis> analyzePsbt(String psbt);

    /**
     * <p>Calls combinepsbt method on the bitcoin node which combines multiple partially signed Bitcoin transactions
     * into one transaction</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.combinepsbt);</pre>
     *
     * @param txs the base64 strings of partially signed transactions
     * @return The base64-encoded partially signed transaction
     */
    CompletableFuture<String> combinePsbt(List<String> txs);

    /**
     * <p>Calls combinerawtransaction method on the bitcoin node which combines multiple partially signed transactions
     * into one transaction</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.combinerawtransaction);</pre>
     *
     * @param txs the hex strings of partially signed transactions
     * @return The hex-encoded raw transaction with signature(s)
     */
    CompletableFuture<String> combineRawTransaction(List<String> txs);

    /**
     * <p>Calls converttopsbt method on the bitcoin node which converts a network serialized transaction to a PSBT</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.converttopsbt);</pre>
     *
     * @param hexRawTrans   the hex string of a raw transaction
     * @param permitSigData if true, any signatures in the input will be discarded and conversion
     *                      will continue. If false, RPC will fail if any signatures are present.
     * @param isWitness     whether the transaction hex is a serialized witness transaction
     * @return The resulting raw transaction (base64-encoded string)
     */
    CompletableFuture<String> convertToPsbt(String hexRawTrans, Boolean permitSigData, Boolean isWitness);

    /**
     * <p>Calls createpsbt method on the bitcoin node which creates a transaction in the Partially Signed Transaction format</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.createpsbt);</pre>
     *
     * @param inputs          tx inputs
     * @param outputAddresses tx outputs, address to amount map
     * @param outputData      hex-encoded data, please check docs.
     * @param lockTime        raw locktime. Non-0 value also locktime-activates inputs
     * @param replaceable     Marks this transaction as BIP125-replaceable.
     * @return The resulting raw transaction (base64-encoded string)
     */
    CompletableFuture<String> createPsbt(
            List<PsbtInput> inputs,
            Map<String, String> outputAddresses,
            String outputData,
            Integer lockTime,
            Boolean replaceable);

    /**
     * <p>Calls createrawtransaction method on the bitcoin node which creates a transaction spending the given inputs
     * and creating new outputs</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.createrawtransaction);</pre>
     *
     * @param inputs          tx inputs
     * @param outputAddresses tx outputs, address to amount map
     * @param outputData      hex-encoded data, please check docs.
     * @param lockTime        raw locktime. Non-0 value also locktime-activates inputs
     * @param replaceable     Marks this transaction as BIP125-replaceable.
     * @return hex string of the transaction
     */
    CompletableFuture<String> createRawTransaction(
            List<PsbtInput> inputs,
            Map<String, String> outputAddresses,
            String outputData,
            Integer lockTime,
            Boolean replaceable);

    /**
     * <p>Calls decodepsbt method on the bitcoin node which returns an object representing the serialized,
     * base64-encoded partially signed Bitcoin transaction</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.decodepsbt);</pre>
     *
     * @param psbt the PSBT base64 string
     * @return an object representing the psbt. See {@link DecodedPsbt}
     */
    CompletableFuture<DecodedPsbt> decodePsbt(String psbt);

    /**
     * <p>Calls decoderawtransaction method on the bitcoin node which returns an object representing the serialized,
     * hex-encoded transaction</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.decoderawtransaction);</pre>
     *
     * @param hexRawTrans the transaction hex string
     * @param isWitness   whether the transaction hex is a serialized witness transaction
     * @return an object representing the transaction. See {@link RawTransaction}
     */
    CompletableFuture<RawTransaction> decodeRawTransaction(String hexRawTrans, Boolean isWitness);

    /**
     * <p>Calls decodescript method on the bitcoin node which decodes a hex-encoded script</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.decodescript);</pre>
     *
     * @param hexScript the hex-encoded script
     * @return an object representing the script. See {@link DecodedScript}
     */
    CompletableFuture<DecodedScript> decodeScript(String hexScript);

    /**
     * <p>Calls finalizepsbt method on the bitcoin node which finalizes the inputs of a PSBT</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.finalizepsbt);</pre>
     *
     * @param psbt    a base64 string of a PSBT
     * @param extract if true and the transaction is complete, extract and return the complete transaction
     *                in normal network serialization instead of the PSBT
     * @return an object representing the finalized psbt. See {@link FinalizedPsbt}
     */
    CompletableFuture<FinalizedPsbt> finalizePsbt(String psbt, Boolean extract);

    /**
     * <p>Calls fundrawtransaction method on the bitcoin node which creates a raw transaction, and funds it with inputs
     * from the wallet</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.fundrawtransaction);</pre>
     *
     * @param hexRawTrans the hex string of the raw transaction
     * @param options     transaction options. See {@link FundTransactionOptions}
     * @param isWitness   whether the transaction hex is a serialized witness transaction
     * @return an object representing the funded transaction. See {@link TransactionFunding}
     */
    CompletableFuture<TransactionFunding> fundRawTransaction(
            String hexRawTrans,
            FundTransactionOptions options,
            Boolean isWitness);

    /**
     * <p>Calls getrawtransaction method on the bitcoin node which returns the raw transaction data</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.getrawtransaction);</pre>
     *
     * @param txId      the transaction id
     * @param blockHash the block in which to look for the transaction
     * @return the serialized, hex-encoded data for the transaction
     */
    CompletableFuture<String> getRawTransaction(String txId, String blockHash);

    /**
     * <p>Calls getrawtransaction method on the bitcoin node which returns the raw transaction data in verbose form</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.getrawtransaction);</pre>
     *
     * @param txId      the transaction id
     * @param blockHash the block in which to look for the transaction
     * @return the raw transaction data. See {@link RawTransaction}
     */
    CompletableFuture<RawTransaction> getRawTransactionVerbose(String txId, String blockHash);

    /**
     * <p>Calls joinpsbts method on the bitcoin node which joins multiple distinct PSBTs with different inputs and
     * outputs into one PSBT with inputs and outputs from all of the PSBTs</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.joinpsbts);</pre>
     *
     * @param transactions The base64 strings of partially signed transactions
     * @return The base64-encoded partially signed transaction
     */
    CompletableFuture<String> joinPsbts(List<String> transactions);

    /**
     * <p>Calls sendrawtransaction method on the bitcoin node which submits a raw transaction (serialized, hex-encoded)
     * to local node and network</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.sendrawtransaction);</pre>
     *
     * @param hexTrans   the hex string of the raw transaction
     * @param maxFeeRate reject transactions whose fee rate is higher than the specified value, expressed in BTC/kvB.
     * @return The transaction hash in hex
     */
    CompletableFuture<String> sendRawTransaction(String hexTrans, String maxFeeRate);

    /**
     * <p>Calls signrawtransactionwithkey method on the bitcoin node which signs inputs for raw transaction (serialized,
     * hex-encoded)</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.signrawtransactionwithkey);</pre>
     *
     * @param hexTrans    the transaction hex string
     * @param privateKeys the base58-encoded private keys for signing
     * @param previousTxs The previous dependent transaction outputs
     * @param sigHashType The signature hash type
     * @return an object representing the signed transaction. See {@link SignTransactionResult}
     */
    CompletableFuture<SignTransactionResult> signRawTransactionWithKey(
            String hexTrans,
            List<String> privateKeys,
            List<PrevTx> previousTxs,
            SigHashType sigHashType);

    /**
     * <p>Calls testmempoolaccept method on the bitcoin node which returns result of mempool acceptance tests indicating
     * if raw transaction(s) (serialized, hex-encoded) would be accepted by mempool</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.testmempoolaccept);</pre>
     *
     * @param rawTxs     a list of hex strings of raw transactions
     * @param maxFeeRate reject transactions whose fee rate is higher than the specified value, expressed in BTC/kvB
     * @return The result of the mempool acceptance test. See {@link MempoolAccept}
     */
    CompletableFuture<List<MempoolAccept>> testMempoolAccept(List<String> rawTxs, String maxFeeRate);

    /**
     * <p>Calls utxoupdatepsbt method on the bitcoin node which updates all segwit inputs and outputs in a PSBT with
     * data from output descriptors, the UTXO set or the mempool</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.utxoupdatepsbt);</pre>
     *
     * @param psbt        a base64 string of a PSBT
     * @param descriptors a list of output descriptors
     * @return The base64-encoded partially signed transaction with inputs updated
     */
    CompletableFuture<String> utxoUpdatePsbt(String psbt, List<String> descriptors);

    /**
     * <p>Calls utxoupdatepsbt method on the bitcoin node which updates all segwit inputs and outputs in a PSBT with
     * data from output descriptors, the UTXO set or the mempool
     * Get more info with:<br/>
     * <pre>client.help(Command.utxoupdatepsbt);</pre>
     *
     * @param psbt        a base64 string of a PSBT
     * @param descriptors a list of output descriptors
     * @return The base64-encoded partially signed transaction with inputs updated
     */
    CompletableFuture<String> utxoUpdatePsbtDesc(String psbt, List<PsbtDescriptor> descriptors);
}
