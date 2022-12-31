package io.github.gstojsic.bitcoin.proxy;

/**
 * This is the sync interface for the bitcoin RPC. It is composed of several interfaces which group methods by their
 * purpose.<p>
 * <a href="https://developer.bitcoin.org/reference/rpc/index.html">source docs</a>
 */
public interface BtcRpc extends BlockchainRpc, ControlRpc, GeneratingRpc, MiningRpc, NetworkRpc, RawTransactionsRpc,
        UtilRpc, WalletRpc {
}
