package io.github.gstojsic.bitcoin.proxy;

import io.github.gstojsic.bitcoin.proxy.json.model.BlockFilter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static io.github.gstojsic.bitcoin.proxy.util.Const.*;
import static io.github.gstojsic.bitcoin.proxy.util.Util.getTestName;
import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
public class CustomBitcoindTest {

    @Test
    void pruneBlockchain() {
        createCustom(fromBase("-prune=1"), container -> {
            var c = createProxy("pruneBlockchain", container);
            var address = c.getNewAddress(null, null);
            c.generateToAddress(1000, address, null);

            var blockCount = c.getBlockCount();
            assertEquals(1000, blockCount);

            var pruned = c.pruneBlockchain(2);
            assertEquals(0, pruned);
        });
    }

    @Test
    void getBlockFilter() {
        createCustom(fromBase("-blockfilterindex=basic"), container -> {
            var c = createProxy("getBlockFilter", container);
            var address = c.getNewAddress(null, null);
            var hash = c.generateToAddress(1, address, null).get(0);
            BlockFilter filter = c.getBlockFilter(hash, null);
            assertNotNull(filter);
            assertNotNull(filter.getFilter());
            assertNotNull(filter.getHeader());
        });
    }

    @Test
    void getTxOutProof() {
        createCustom(fromBase("-txindex"), container -> {
            var c = createProxy("getTxOutProof", container);
            var address = c.getNewAddress(null, null);
            var hash = c.generateToAddress(1, address, null).get(0);
            var tx = c.listTransactions(null, null, null, null).get(0);
            var txOutProof = c.getTxOutProof(List.of(tx.getTxId()), hash);
            assertNotNull(txOutProof);

            var verified = c.verifyTxOutProof(txOutProof);
            assertNotNull(verified);
            assertEquals(1, verified.size());
            assertEquals(tx.getTxId(), verified.get(0));
        });
    }

    @Test
    void getZmqNotifications(TestInfo info) {
        final int ZMQ_PORT = 28332;
        final String ZMQ_ADDRESS = "tcp://*:%d".formatted(ZMQ_PORT);
        createCustom(fromBase(
                "-zmqpubhashtx=%s".formatted(ZMQ_ADDRESS),
                "-zmqpubhashblock=%s".formatted(ZMQ_ADDRESS),
                "-zmqpubrawblock=%s".formatted(ZMQ_ADDRESS),
                "-zmqpubrawtx=%s".formatted(ZMQ_ADDRESS),
                "-zmqpubsequence=%s".formatted(ZMQ_ADDRESS),
                "-debug=zmq"
        ), container -> {
            var proxy = createProxy(getTestName(info), container);
            var zmq = proxy.getZmqNotifications();
            assertFalse(zmq.isEmpty());
            var pubHashTx = zmq.stream().filter(z -> "pubhashtx".equals(z.getType())).findFirst().orElseThrow();
            assertEquals(ZMQ_ADDRESS, pubHashTx.getAddress());
            var pubHashBlock = zmq.stream().filter(z -> "pubhashblock".equals(z.getType())).findFirst().orElseThrow();
            assertEquals(ZMQ_ADDRESS, pubHashBlock.getAddress());
            var pubRawBlock = zmq.stream().filter(z -> "pubrawblock".equals(z.getType())).findFirst().orElseThrow();
            assertEquals(ZMQ_ADDRESS, pubRawBlock.getAddress());
            var pubRawTx = zmq.stream().filter(z -> "pubrawtx".equals(z.getType())).findFirst().orElseThrow();
            assertEquals(ZMQ_ADDRESS, pubRawTx.getAddress());
            var pubSequence = zmq.stream().filter(z -> "pubsequence".equals(z.getType())).findFirst().orElseThrow();
            assertEquals(ZMQ_ADDRESS, pubSequence.getAddress());
        });
    }

    private void createCustom(List<String> cmd, ContainerConsumer consumer) {
        try (GenericContainer<?> container = new GenericContainer<>(DOCKER_IMAGE).withCommand(cmd.toArray(String[]::new)).withExposedPorts(RPC_PORT, P2P_PORT);) {
            container.start();
            consumer.accept(container);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static BitcoinProxy createProxy(String wallet, GenericContainer<?> container) {
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

    private List<String> fromBase(String... rest) {
        var c = new ArrayList<>(BASE_CMD);
        Collections.addAll(c, rest);
        return c;
    }

    private static final List<String> BASE_CMD = List.of(
            "-regtest",
            "-printtoconsole",
            "-rpcallowip=0.0.0.0/0",
            "-rpcbind=0.0.0.0",
            "-rpcauth=%s".formatted(RPC_AUTH),
            "-fallbackfee=0.0001",
            "-maxtxfee=0.011"
    );

    @FunctionalInterface
    public interface ContainerConsumer {
        void accept(GenericContainer<?> a) throws IOException, InterruptedException;
    }
}
