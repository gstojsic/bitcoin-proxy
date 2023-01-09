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
import static io.github.gstojsic.bitcoin.proxy.util.Util.getTestName;
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
                    "-rpcauth=%s".formatted(RPC_AUTH)
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

    protected BitcoinProxy createWalletProxy(String wallet) {
        BitcoinProxy proxy = new BitcoinProxy(bitcoinDaemon.getHost(), bitcoinDaemon.getMappedPort(RPC_PORT), RPC_USER, RPC_PWD, wallet);
        proxy.createWallet(
                wallet,
                null,
                null,
                null,
                true,
                false,
                null,
                null);
        return proxy;
    }

    @Test
    void dumpWallet(TestInfo info) throws IOException, InterruptedException {
        //bitcoinDaemon.withFileSystemBind(temp, CONTAINER_BITCOIN_PATH, BindMode.READ_WRITE);
        var r = bitcoinDaemon.execInContainer("chmod", "777", CONTAINER_BITCOIN_PATH);
        if (r.getExitCode() != 0)
            throw new RuntimeException("couldnt execute chmod in container");

        var c = createWalletProxy(getTestName(info));
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
    void backupWallet(TestInfo info) {
        var c = createWalletProxy(getTestName(info));
        assertDoesNotThrow(() -> c.backupWallet(CONTAINER_BITCOIN_PATH + "/backupWallet.dat"));
    }

    @Test
    void restoreWallet(TestInfo info) {
        var c = createWalletProxy(getTestName(info));
        var filename = CONTAINER_BITCOIN_PATH + "/restoreWallet.dat";
        assertDoesNotThrow(() -> c.backupWallet(filename));

        var wallet = c.restoreWallet("restoredWallet", filename, null);
        assertEquals("restoredWallet", wallet.getName());
        assertEquals("", wallet.getWarning());
    }

    @Test
    void importWallet(TestInfo info) {
        var c = createWalletProxy(getTestName(info));
        var walletPath = CONTAINER_BITCOIN_PATH + "/importWallet.txt";
        var dumpWallet = c.dumpWallet(walletPath);
        c.importWallet(dumpWallet.getFilename());
        assertDoesNotThrow(() -> c.importWallet(dumpWallet.getFilename()));
    }

    @Test
    void loadWallet(TestInfo info) {
        var c = createWalletProxy(getTestName(info));
        c.unloadWallet(null, null);
        var filename = CONTAINER_BITCOIN_PATH + "/regtest/wallets/loadWallet";
        var wallet = proxy.loadWallet(filename, false);
        assertTrue(wallet.getName().contains("loadWallet"));
        assertEquals("", wallet.getWarning());
    }

    @Test
    void migrateWallet(TestInfo info) {
        var name = getTestName(info);
        var c = createWalletProxy(name);
        var migrateWalletInfo = c.migrateWallet();
        assertEquals(name, migrateWalletInfo.getWalletName());
        assertFalse(migrateWalletInfo.getBackupPath().isEmpty());
    }
}