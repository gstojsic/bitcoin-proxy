package org.bitcoin.proxy;

/**
 * source docs: <a href="https://developer.bitcoin.org/reference/rpc/index.html">...</a>
 */
public interface BtcRpc extends BlockchainRpc, ControlRpc, GeneratingRpc, MiningRpc, NetworkRpc, RawTransactionsRpc, UtilRpc, WalletRpc {
}
