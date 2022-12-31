package io.github.gstojsic.bitcoin.proxy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.testcontainers.containers.BindMode;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static io.github.gstojsic.bitcoin.proxy.util.Const.*;
import static org.junit.jupiter.api.Assertions.*;


@Testcontainers
public class WalletTest {
    protected static final String CONTAINER_BITCOIN_PATH = "/home/bitcoin/.bitcoin";
    protected static final String temp;

    static {
        try {
            temp = Files.createTempDirectory("DockerTest").toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

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
            .withExposedPorts(RPC_PORT, P2P_PORT)
            .withFileSystemBind(temp, CONTAINER_BITCOIN_PATH, BindMode.READ_WRITE);

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

    @Test
    void dumpWallet(TestInfo info) throws IOException, InterruptedException {
        //bitcoinDaemon.withFileSystemBind(temp, CONTAINER_BITCOIN_PATH, BindMode.READ_WRITE);
        var r = bitcoinDaemon.execInContainer("chmod", "777", CONTAINER_BITCOIN_PATH);
        if (r.getExitCode() != 0)
            throw new RuntimeException("couldnt execute chmod in container");

        proxy.createWallet(
                "dumpWallet",
                null,
                null,
                null,
                true,
                null,
                null,
                null);
        var c = createProxy("dumpWallet");
        var walletPath = CONTAINER_BITCOIN_PATH + "/dumpWallet.txt";
        var dumpWallet = c.dumpWallet(walletPath);
        assertEquals(walletPath, dumpWallet.getFilename());

        //check that file exists
        var execRes = bitcoinDaemon.execInContainer("chmod", "777", walletPath);
        assertEquals(0, execRes.getExitCode());
        var f = Files.readAllLines(Path.of(temp, "dumpWallet.txt"));
        //Files.list(Path.of(temp)).toList(); //list files in dir
        assertTrue(f.size() > 100);
        assertTrue(f.get(0).contains("Wallet dump created by Bitcoin Core"));
    }

    @Test
    void backupWallet() {
        proxy.createWallet(
                "backupWallet",
                null,
                null,
                null,
                null,
                null,
                null,
                null);

        var c = createProxy("backupWallet");
        assertDoesNotThrow(() -> c.backupWallet(CONTAINER_BITCOIN_PATH + "/backupWallet.dat"));
    }

    @Test
    void restoreWallet() {
        proxy.createWallet(
                "restoreWallet",
                null,
                null,
                null,
                null,
                null,
                null,
                null);

        var c = createProxy("restoreWallet");
        var filename = CONTAINER_BITCOIN_PATH + "/restoreWallet.dat";
        assertDoesNotThrow(() -> c.backupWallet(filename));

        var wallet = c.restoreWallet("restoredWallet", filename, null);
        assertEquals("restoredWallet", wallet.getName());
        assertEquals("", wallet.getWarning());
    }

    @Test
    void importWallet() {
        proxy.createWallet(
                "importWallet",
                null,
                null,
                null,
                true,
                null,
                null,
                null);
        var c = createProxy("importWallet");
        var walletPath = CONTAINER_BITCOIN_PATH + "/importWallet.txt";
        var dumpWallet = c.dumpWallet(walletPath);
        c.importWallet(dumpWallet.getFilename());
        assertDoesNotThrow(() -> c.importWallet(dumpWallet.getFilename()));
    }

    @Test
    void loadWallet() {
        proxy.createWallet(
                "loadWallet",
                null,
                null,
                null,
                null,
                null,
                null,
                null);

        var c = createProxy("loadWallet");
        c.unloadWallet(null, null);
        var filename = CONTAINER_BITCOIN_PATH + "/regtest/wallets/loadWallet";
        var wallet = proxy.loadWallet(filename, false);
        assertTrue(wallet.getName().contains("loadWallet"));
        assertEquals("", wallet.getWarning());
    }
}