package io.github.gstojsic.bitcoin.proxy.util;

import org.testcontainers.utility.DockerImageName;

public enum Const {
    ;
    public static final DockerImageName DOCKER_IMAGE = DockerImageName.parse("stole/bitcoin-docker:24.0");
    public static final int RPC_PORT = 18443;
    public static final int P2P_PORT = 18444;
    public static final String RPC_USER = "btcUser";
    public static final String RPC_PWD = "wUrKTR9O5zr4WJBwJjWpUXfSppDnOzhZpwIFs1Soxyc=";
    public static final String RPC_AUTH = "btcUser:d013855531d4ffbd5fdece0c2a2080cf$01db22c9da8f30e17d471daa2f02e4e49605930750f8a6f73141320ea6a6a282";
}
