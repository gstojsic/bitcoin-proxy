package io.github.gstojsic.bitcoin.proxy;

import io.github.gstojsic.bitcoin.proxy.json.model.ChainTipData;
import io.github.gstojsic.bitcoin.proxy.json.model.PeerInfo;
import io.github.gstojsic.bitcoin.proxy.model.AddNetworkCommand;
import io.github.gstojsic.bitcoin.proxy.model.SetBanCommand;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.Network;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static io.github.gstojsic.bitcoin.proxy.util.Const.*;
import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
public class PeerTest {
    private static final String ALICE_NAME = "alice";
    private static final String BOB_NAME = "bob";

    @Test
    public void peerTesting() {
        withPeers(this::peerTest);
    }

    private void peerTest(GenericContainer<?> a, GenericContainer<?> b) {
        var alice = createProxy("alice", a);
        var bobAddr = "%s:%d".formatted(BOB_NAME, P2P_PORT);
        alice.addNode(bobAddr, AddNetworkCommand.add);
        List<PeerInfo> peers;
        int i = 0;
        do {
            try {
                Thread.sleep(1_000);
                peers = alice.getPeerInfo();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            i++;
        } while (peers.size() == 0 && i < 10);
        assertEquals(1, peers.size());

        var bobInfo = peers.get(0);
        assertEquals(bobAddr, bobInfo.getAddr());
        var now = System.currentTimeMillis();
        assertTrue(bobInfo.getLastRecv() <= now);
        assertTrue(bobInfo.getLastSend() <= now);

        var addedNodes = alice.getAddedNodeInfo(bobAddr);
        assertEquals(1, addedNodes.size());
        var bobNode = addedNodes.get(0);
        assertEquals(bobAddr, bobNode.getAddedNode());
        assertTrue(bobNode.isConnected());

        var banIp = bobNode.getAddresses().get(0).getAddress().split(":")[0];
        alice.setBan(banIp, SetBanCommand.add, null, null);

        try {
            Thread.sleep(2_000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        peers = alice.getPeerInfo();
        assertTrue(peers.isEmpty());

        var banned = alice.listBanned();
        assertEquals(1, banned.size());
        var ban = banned.get(0);
        assertTrue(ban.getAddress().startsWith(banIp));

        alice.clearBanned();

        banned = alice.listBanned();
        assertTrue(banned.isEmpty());
    }

    @Test
    public void disconnectNode() {
        withPeers(this::disconnectNodeTest);
    }

    private void disconnectNodeTest(GenericContainer<?> a, GenericContainer<?> b) {
        var alice = createProxy("alice", a);
        var bobAddr = "%s:%d".formatted(BOB_NAME, P2P_PORT);
        alice.addNode(bobAddr, AddNetworkCommand.add);
        List<PeerInfo> peers;
        int i = 0;
        do {
            try {
                Thread.sleep(1_000);
                peers = alice.getPeerInfo();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            i++;
        } while (peers.size() == 0 && i < 10);
        assertEquals(1, peers.size());

        var bobInfo = peers.get(0);
        assertEquals(bobAddr, bobInfo.getAddr());

        assertDoesNotThrow(() -> alice.disconnectNode(null, bobInfo.getId()));
        i = 0;
        do {
            try {
                Thread.sleep(1_000);
                peers = alice.getPeerInfo();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            i++;
        } while (peers.size() > 0 && i < 10);
        assertTrue(peers.isEmpty());
    }

    @Test
    public void submitBlock() {
        withPeers(this::submitBlockTest);
    }

    private void submitBlockTest(GenericContainer<?> a, GenericContainer<?> b) {
        var alice = createProxy("alice", a);

        var address = alice.getNewAddress(null, null);
        var blockHash = alice.generateToAddress(1, address, null).get(0);
        var rawBlock = alice.getBlock(blockHash);

        var bob = createProxy("bob", b);
        var res = bob.submitBlock(rawBlock);
        assertNull(res);
        res = bob.submitBlock(rawBlock);
        assertEquals("duplicate", res);
    }

    @Test
    public void submitHeader() {
        withPeers(this::submitHeaderTest);
    }

    private void submitHeaderTest(GenericContainer<?> a, GenericContainer<?> b) {
        var alice = createProxy("alice", a);

        var address = alice.getNewAddress(null, null);
        var blockHash = alice.generateToAddress(1, address, null).get(0);
        var blockHeader = alice.getBlockHeader(blockHash);

        var bob = createProxy("bob", b);
        assertDoesNotThrow(() -> bob.submitHeader(blockHeader));
    }

    @Test
    public void getNodeAddresses() {
        withPeers(this::getNodeAddressesTest);
    }

    private void getNodeAddressesTest(GenericContainer<?> a, GenericContainer<?> b) throws IOException, InterruptedException {
        var alice = createProxy("alice", a);
        var host = "123.123.123.123";
        var port = 8333;
        var result = alice.addPeerAddress(host, port);
        assertTrue(result.isSuccess());

        var addresses = alice.getNodeAddresses(0, null);
        assertEquals(1, addresses.size());
        var addr = addresses.get(0);
        assertEquals("ipv4", addr.getNetwork());
        assertEquals(host, addr.getAddress());
        assertEquals(port, addr.getPort());

        result = alice.addPeerAddress(host, port);
        assertFalse(result.isSuccess());
    }

    @Test
    public void getBlockFromPeer() {
        withPeers(this::getBlockFromPeerTest);
    }

    private void getBlockFromPeerTest(GenericContainer<?> a, GenericContainer<?> b) {
        var alice = createProxy("alice", a);
        var aliceBh = alice.generateToAddress(5, alice.getNewAddress(null, null), null);
        var blockHash = aliceBh.get(0);

        var bob = createProxy("bob", b);
        var bobBh = bob.generateToAddress(10, bob.getNewAddress(null, null), null);

        var aliceAddr = "%s:%d".formatted(ALICE_NAME, P2P_PORT);
        bob.addNode(aliceAddr, AddNetworkCommand.add);
        List<PeerInfo> peers;
        int i = 0;
        do {
            try {
                Thread.sleep(1_000);
                peers = bob.getPeerInfo();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            i++;
        } while (peers.size() == 0 && i < 10);
        var aliceInfo = peers.get(0);

        var blockHeader = alice.getBlockHeader(blockHash);

        bob.submitHeader(blockHeader);
        var empty = bob.getBlockFromPeer(blockHash, aliceInfo.getId());
        assertNotNull(empty);

        List<ChainTipData> tips = alice.getChainTips();
        assertEquals(2, tips.size());
        var p = tips.stream().collect(Collectors.partitioningBy(c -> "active".equals(c.getStatus())));
        var active = p.get(true).get(0);
        assertEquals(10, active.getHeight());
        assertEquals(0, active.getBranchLen());
        assertEquals(bobBh.get(9), active.getHash());
        var fork = p.get(false).get(0);
        assertEquals(5, fork.getHeight());
        assertEquals(5, fork.getBranchLen());
        assertEquals(aliceBh.get(4), fork.getHash());
    }

    @Test
    public void containerNetwork() {
        withPeers(this::containerNetworkTest);
    }

    private void containerNetworkTest(GenericContainer<?> alice, GenericContainer<?> bob) throws IOException, InterruptedException {
        String response = bob.execInContainer("bitcoin-cli",
                "-rpcconnect=%s".formatted(ALICE_NAME),
                "-rpcport=%d".formatted(RPC_PORT),
                "-rpcuser=%s".formatted(RPC_USER),
                "-rpcpassword=%s".formatted(RPC_PWD),
                "uptime"
        ).getStdout();

        var uptime = Integer.parseInt(response.trim());
        assertTrue(uptime >= 0);
    }

    private void withPeers(PeerConsumer consumer) {
        try (
                Network network = Network.newNetwork();
                GenericContainer<?> alice = new GenericContainer<>(DOCKER_IMAGE)
                        .withCommand(
                                "-regtest",
                                "-printtoconsole",
                                "-rpcallowip=0.0.0.0/0",
                                "-rpcbind=0.0.0.0",
                                "-rpcauth=%s".formatted(RPC_AUTH),
                                "-fallbackfee=0.0001",
                                "-maxtxfee=0.011"
                        )
                        .withExposedPorts(RPC_PORT, P2P_PORT)
                        .withNetwork(network)
                        .withNetworkAliases(ALICE_NAME);

                GenericContainer<?> bob = new GenericContainer<>(DOCKER_IMAGE)
                        .withCommand(
                                "-regtest",
                                "-printtoconsole",
                                "-rpcallowip=0.0.0.0/0",
                                "-rpcbind=0.0.0.0",
                                "-rpcauth=%s".formatted(RPC_AUTH),
                                "-fallbackfee=0.0001",
                                "-maxtxfee=0.011"
                        )
                        .withExposedPorts(RPC_PORT, P2P_PORT)
                        .withNetwork(network)
                        .withNetworkAliases(BOB_NAME);
        ) {
            alice.start();
            bob.start();
            consumer.accept(alice, bob);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    protected static BitcoinProxy createProxy(String wallet, GenericContainer<?> container) {
        BitcoinProxy proxy = new BitcoinProxy(container.getHost(), container.getMappedPort(RPC_PORT), RPC_USER, RPC_PWD, wallet);
        proxy.createWallet(
                wallet,
                null,
                null,
                null,
                true,
                null,
                null,
                null);
        return proxy;
    }

    @FunctionalInterface
    public interface PeerConsumer {
        void accept(GenericContainer<?> a, GenericContainer<?> b) throws IOException, InterruptedException;
    }
}
