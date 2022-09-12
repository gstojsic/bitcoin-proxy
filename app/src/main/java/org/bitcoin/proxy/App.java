package org.bitcoin.proxy;

public class App {

    public static void main(String[] args) {
        RpcClient client = new RpcClient(args[0], args[1]);
        System.out.println(client.getDifficulty());
    }
}
