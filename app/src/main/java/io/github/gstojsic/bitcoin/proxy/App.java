package io.github.gstojsic.bitcoin.proxy;

import java.util.concurrent.ExecutionException;

public class App {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        BitcoinProxyAsync client = new BitcoinProxyAsync("127.0.0.1", 8332, args[0], args[1]);
        System.out.println(client.getPeerInfo().get());
    }
}
