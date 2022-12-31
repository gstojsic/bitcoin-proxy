package io.github.gstojsic.bitcoin.proxy;

/**
 * <p>This is the async interface for the bitcoin RPC. It is composed of several interfaces which group methods by their
 * purpose.</p>
 * <p>This documentation is not meant to explain in detail the nuances of bitcoin node operations, the client just calls
 * the equivalent method on the bitcoin node. For details on what each call does on the node please refer to the bitcoin
 * documentation</p>
 * You can access the documentation by invoking the following:
 * <br/>
 * <pre>
 *  var client = new BitcoinProxy(...);
 *  println(client.help(Command.[theCommand]));
 * </pre>
 * <a href="https://developer.bitcoin.org/reference/rpc/index.html">source docs</a>
 */
public interface BtcRpcAsync extends
        BlockchainRpcAsync,
        ControlRpcAsync,
        GeneratingRpcAsync,
        MiningRpcAsync,
        NetworkRpcAsync,
        RawTransactionsRpcAsync,
        UtilRpcAsync,
        WalletRpcAsync {
}
