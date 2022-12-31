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

public interface RawTransactionsRpc {

    /**
     * @see RawTransactionsRpcAsync#analyzePsbt(String)
     */
    PsbtAnalysis analyzePsbt(String psbt);

    /**
     * @see RawTransactionsRpcAsync#combinePsbt(List)
     */
    String combinePsbt(List<String> txs);

    /**
     * @see RawTransactionsRpcAsync#combineRawTransaction(List)
     */
    String combineRawTransaction(List<String> txs);

    /**
     * @see RawTransactionsRpcAsync#convertToPsbt(String, Boolean, Boolean)
     */
    String convertToPsbt(String hexRawTrans, Boolean permitSigData, Boolean isWitness);

    /**
     * @see RawTransactionsRpcAsync#createPsbt(List, Map, String, Integer, Boolean)
     */
    String createPsbt(List<PsbtInput> inputs, Map<String, String> outputAddresses, String outputData, Integer lockTime, Boolean replaceable);

    /**
     * @see RawTransactionsRpcAsync#createRawTransaction(List, Map, String, Integer, Boolean)
     */
    String createRawTransaction(List<PsbtInput> inputs, Map<String, String> outputAddresses, String outputData, Integer lockTime, Boolean replaceable);

    /**
     * @see RawTransactionsRpcAsync#decodePsbt(String)
     */
    DecodedPsbt decodePsbt(String psbt);

    /**
     * @see RawTransactionsRpcAsync#decodeRawTransaction(String, Boolean)
     */
    RawTransaction decodeRawTransaction(String hexRawTrans, Boolean isWitness);

    /**
     * @see RawTransactionsRpcAsync#decodeScript(String)
     */
    DecodedScript decodeScript(String hexScript);

    /**
     * @see RawTransactionsRpcAsync#finalizePsbt(String, Boolean)
     */
    FinalizedPsbt finalizePsbt(String psbt, Boolean extract);

    /**
     * @see RawTransactionsRpcAsync#fundRawTransaction(String, FundTransactionOptions, Boolean)
     */
    TransactionFunding fundRawTransaction(String hexRawTrans, FundTransactionOptions options, Boolean isWitness);

    /**
     * @see RawTransactionsRpcAsync#getRawTransaction(String, String)
     */
    String getRawTransaction(String txId, String blockHash);

    /**
     * @see RawTransactionsRpcAsync#getRawTransactionVerbose(String, String)
     */
    RawTransaction getRawTransactionVerbose(String txId, String blockHash);

    /**
     * @see RawTransactionsRpcAsync#joinPsbts(List)
     */
    String joinPsbts(List<String> transactions);

    /**
     * @see RawTransactionsRpcAsync#sendRawTransaction(String, String)
     */
    String sendRawTransaction(String hexTrans, String maxFeeRate);

    /**
     * @see RawTransactionsRpcAsync#signRawTransactionWithKey(String, List, List, SigHashType)
     */
    SignTransactionResult signRawTransactionWithKey(String hexTrans, List<String> privateKeys, List<PrevTx> previousTxs, SigHashType sigHashType);

    /**
     * @see RawTransactionsRpcAsync#testMempoolAccept(List, String)
     */
    List<MempoolAccept> testMempoolAccept(List<String> rawTxs, String maxFeeRate);

    /**
     * @see RawTransactionsRpcAsync#utxoUpdatePsbt(String, List)
     */
    String utxoUpdatePsbt(String psbt, List<String> descriptors);

    /**
     * @see RawTransactionsRpcAsync#utxoUpdatePsbtDesc(String, List)
     */
    String utxoUpdatePsbtDesc(String psbt, List<PsbtDescriptor> descriptors);
}
