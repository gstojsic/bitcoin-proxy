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
import java.util.concurrent.CompletableFuture;

public interface NetworkRpcAsync {

    /**
     * <p>Calls addnode method on the bitcoin node which attempts to add or remove a node from the addnode list</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.addnode);</pre>
     *
     * @param node    the node (see getpeerinfo for nodes)
     * @param command {@link AddNetworkCommand#add} to add a node to the list, {@link AddNetworkCommand#remove}
     *                to remove a node from the list, {@link AddNetworkCommand#onetry} to try a connection to the node once
     * @return void
     */
    CompletableFuture<Void> addNode(String node, AddNetworkCommand command);

    /**
     * <p>Calls clearbanned method on the bitcoin node which clears all banned IPs</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.clearbanned);</pre>
     *
     * @return void
     */
    CompletableFuture<Void> clearBanned();

    /**
     * <p>Calls disconnectnode method on the bitcoin node which immediately disconnects from the specified peer node.
     * Provide either address or nodeId</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.disconnectnode);</pre>
     *
     * @param address the IP address/port of the node
     * @param nodeId  the node ID (see getpeerinfo for node IDs)
     * @return void
     */
    CompletableFuture<Void> disconnectNode(String address, Integer nodeId);

    /**
     * <p>Calls getaddednodeinfo method on the bitcoin node which returns information about the given added node</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.getaddednodeinfo);</pre>
     *
     * @param node if provided, return information about this specific node, otherwise all nodes are returned
     * @return list of objects with information about the given added node(s). See {@link AddedNodeInfo}
     */
    CompletableFuture<List<AddedNodeInfo>> getAddedNodeInfo(String node);

    /**
     * <p>Calls getblockfrompeer method on the bitcoin node which attempts to fetch block from a given peer</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.getblockfrompeer);</pre>
     *
     * @param blockhash the block hash to try to fetch
     * @param peerId    the peer to fetch it from (see getpeerinfo for peer IDs)
     * @return void
     */
    CompletableFuture<Empty> getBlockFromPeer(String blockhash, int peerId);

    /**
     * <p>Calls getconnectioncount method on the bitcoin node which returns the number of connections to other nodes.</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.getconnectioncount);</pre>
     *
     * @return the number of connections to other nodes
     */
    CompletableFuture<Integer> getConnectionCount();

    /**
     * <p>Calls getnettotals method on the bitcoin node which returns information about network traffic, including
     * bytes in, bytes out, and current time</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.getnettotals);</pre>
     *
     * @return information about network traffic. See {@link NetTotals}
     */
    CompletableFuture<NetTotals> getNetTotals();

    /**
     * <p>Calls getnetworkinfo method on the bitcoin node which returns an object containing various state info
     * regarding P2P networking</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.getnetworkinfo);</pre>
     *
     * @return an object containing various state info regarding P2P networking. See {@link NetworkInfo}
     */
    CompletableFuture<NetworkInfo> getNetworkInfo();

    /**
     * <p>Calls getnodeaddresses method on the bitcoin node which returns known addresses which can potentially be used
     * to find new nodes in the network</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.getnodeaddresses);</pre>
     *
     * @param count       the maximum number of addresses to return. Specify 0 to return all known addresses
     * @param networkType return only addresses of the specified network
     * @return list of {@link NodeAddress}
     */
    CompletableFuture<List<NodeAddress>> getNodeAddresses(Integer count, NetworkType networkType);

    /**
     * <p>Calls getpeerinfo method on the bitcoin node which returns data about each connected network node</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.getpeerinfo);</pre>
     *
     * @return list of {@link PeerInfo}
     */
    CompletableFuture<List<PeerInfo>> getPeerInfo();

    /**
     * <p>Calls listbanned method on the bitcoin node which returns a list all manually banned IPs/Subnets</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.listbanned);</pre>
     *
     * @return a list all manually banned IPs/Subnets. See {@link BannedInfo}
     */
    CompletableFuture<List<BannedInfo>> listBanned();

    /**
     * <p>Calls ping method on the bitcoin node which requests that a ping be sent to all other nodes, to measure ping time</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.ping);</pre>
     *
     * @return void
     */
    CompletableFuture<Void> ping();

    /**
     * <p>Calls setban method on the bitcoin node which attempts to add or remove an IP/Subnet from the banned list</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.setban);</pre>
     *
     * @param subnet   the IP/Subnet (see getpeerinfo for nodes IP) with an optional netmask (default is /32 = single IP)
     * @param command  {@link SetBanCommand#add} or {@link SetBanCommand#remove} to add or remove ban respectively
     * @param banTime  time in seconds how long (or until when if [absolute] is set) the IP is banned
     * @param absolute If set, the bantime must be an absolute timestamp expressed in UNIX epoch time
     * @return void
     */
    CompletableFuture<Void> setBan(String subnet, SetBanCommand command, Integer banTime, Boolean absolute);

    /**
     * <p>Calls setnetworkactive method on the bitcoin node which disables/enables all p2p network activity.</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.setnetworkactive);</pre>
     *
     * @param state true to enable networking, false to disable
     * @return The value that was passed in the state parameter
     */
    CompletableFuture<Boolean> setNetworkActive(boolean state);

    /**
     * <p>Calls addpeeraddress method on the bitcoin node which adds the address of a potential peer to
     * the address manager. This RPC is for testing only.</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.addpeeraddress);</pre>
     *
     * @param host the IP address of the peer
     * @param port the port of the peer
     * @return a {@link AddPeerAddress} object
     */
    CompletableFuture<AddPeerAddress> addPeerAddress(String host, int port);
}
