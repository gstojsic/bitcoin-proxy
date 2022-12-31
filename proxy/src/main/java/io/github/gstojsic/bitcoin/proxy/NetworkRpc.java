package io.github.gstojsic.bitcoin.proxy;

import io.github.gstojsic.bitcoin.proxy.json.model.AddPeerAddress;
import io.github.gstojsic.bitcoin.proxy.json.model.AddedNodeInfo;
import io.github.gstojsic.bitcoin.proxy.json.model.BannedInfo;
import io.github.gstojsic.bitcoin.proxy.json.model.Empty;
import io.github.gstojsic.bitcoin.proxy.json.model.NetTotals;
import io.github.gstojsic.bitcoin.proxy.json.model.NetworkInfo;
import io.github.gstojsic.bitcoin.proxy.json.model.NodeAddress;
import io.github.gstojsic.bitcoin.proxy.json.model.PeerInfo;
import io.github.gstojsic.bitcoin.proxy.model.AddNetworkCommand;
import io.github.gstojsic.bitcoin.proxy.model.NetworkType;
import io.github.gstojsic.bitcoin.proxy.model.SetBanCommand;

import java.util.List;

public interface NetworkRpc {

    /**
     * @see NetworkRpcAsync#addNode(String, AddNetworkCommand)
     */
    void addNode(String node, AddNetworkCommand command);

    /**
     * @see NetworkRpcAsync#clearBanned()
     */
    void clearBanned();

    /**
     * @see NetworkRpcAsync#disconnectNode(String, Integer)
     */
    void disconnectNode(String address, Integer nodeId);

    /**
     * @see NetworkRpcAsync#getAddedNodeInfo(String)
     */
    List<AddedNodeInfo> getAddedNodeInfo(String node);

    /**
     * @see NetworkRpcAsync#getBlockFromPeer(String, int)
     */
    Empty getBlockFromPeer(String blockhash, int peerId);

    /**
     * @see NetworkRpcAsync#getConnectionCount()
     */
    int getConnectionCount();

    /**
     * @see NetworkRpcAsync#getNetTotals()
     */
    NetTotals getNetTotals();

    /**
     * @see NetworkRpcAsync#getNetworkInfo()
     */
    NetworkInfo getNetworkInfo();

    /**
     * @see NetworkRpcAsync#getNodeAddresses(Integer, NetworkType)
     */
    List<NodeAddress> getNodeAddresses(Integer count, NetworkType networkType);

    /**
     * @see NetworkRpcAsync#getPeerInfo()
     */
    List<PeerInfo> getPeerInfo();

    /**
     * @see NetworkRpcAsync#listBanned()
     */
    List<BannedInfo> listBanned();

    /**
     * @see NetworkRpcAsync#ping()
     */
    void ping();

    /**
     * @see NetworkRpcAsync#setBan(String, SetBanCommand, Integer, Boolean)
     */
    void setBan(String subnet, SetBanCommand command, Integer banTime, Boolean absolute);

    /**
     * @see NetworkRpcAsync#setNetworkActive(boolean)
     */
    boolean setNetworkActive(boolean state);

    /**
     * @see NetworkRpcAsync#addPeerAddress(String, int)
     */
    AddPeerAddress addPeerAddress(String host, int port);
}
