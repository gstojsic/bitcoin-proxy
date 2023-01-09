package io.github.gstojsic.bitcoin.proxy;

import io.github.gstojsic.bitcoin.proxy.json.model.UnspentInfo;
import org.junit.jupiter.api.BeforeEach;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static io.github.gstojsic.bitcoin.proxy.util.Const.*;

@Testcontainers
abstract public class BitcoinDockerBase {
    @Container
    public final GenericContainer<?> bitcoinDaemon = new GenericContainer<>(DOCKER_IMAGE)
            .withCommand(
                    "-regtest",
                    "-printtoconsole",
                    "-rpcallowip=172.17.0.0/16",
                    "-rpcbind=0.0.0.0",
                    "-rpcauth=%s".formatted(RPC_AUTH),
                    "-txindex",
                    "-fallbackfee=0.0001",
                    "-maxtxfee=0.011"
            )
            .withExposedPorts(RPC_PORT, P2P_PORT);

    protected BitcoinProxy proxy;

    @BeforeEach
    public void setUp() {
        proxy = createProxy();
    }

    protected BitcoinProxy createProxy() {
        return createProxy(null);
    }

    protected BitcoinProxy createProxy(String wallet) {
        return new BitcoinProxy(bitcoinDaemon.getHost(), bitcoinDaemon.getMappedPort(RPC_PORT), RPC_USER, RPC_PWD, wallet);
    }

    protected BitcoinProxy createWalletProxy(String wallet) {
        return createWalletProxy(wallet, false);
    }

    protected BitcoinProxy createWalletProxy(String wallet, boolean descriptors) {
        BitcoinProxy proxy = new BitcoinProxy(bitcoinDaemon.getHost(), bitcoinDaemon.getMappedPort(RPC_PORT), RPC_USER, RPC_PWD, wallet);
        proxy.createWallet(
                wallet,
                null,
                null,
                null,
                true,
                descriptors,
                null,
                null);
        return proxy;
    }

    protected static List<UnspentInfo> initialFundWallet(BitcoinProxy proxy) {
        return fundWallet(proxy, 101);
    }

    protected static List<UnspentInfo> fundWallet(BitcoinProxy proxy, int nBlocks) {
        var address = proxy.getNewAddress(null, null);
        proxy.generateToAddress(nBlocks, address, null);
        return proxy.listUnspent(null, null, null, null, null);
    }
}
