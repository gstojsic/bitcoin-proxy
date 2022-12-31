# bitcoin-proxy
bitcoin-proxy is a java library that allows access to bitcoin nodes from java applications. It features:

- bitcoin v23 API
- sync and async API
- small footprint (less than 600kB)
- no external dependencies

## requirements
The proxy requires a bitcoin node to connect to. For details on how to install a bitcoind node please refer
to the [Bitcoin documentation][bitcoinDoc].

The project tests use testcontainers to run a bitcoin node in a docker container so docker needs to be installed to run
tests.

## usage
To use the proxy first you have to add it as a dependency to your gradle project:
```gradle
implementation("io.github.gstojsic.bitcoin:proxy:0.9")
```
or maven project:
```maven
<dependency>
    <groupId>io.github.gstojsic.bitcoin</groupId>
    <artifactId>proxy</artifactId>
    <version>0.9</version>
</dependency>
```

A simple example on how to use the proxy in your project is: 
```java
var proxy = new BitcoinProxy(
      "localhost",
      8443,
      "user",
      "secret",
      null);
var uptime = proxy.uptime();
```
## acknowledgments
- to bitcoin core developers for the great service they provide to the community.
- to the good people at BlockchainCommons for the very good [book][bitcoinCliBook] on how to use bitcoin from the
command line from which I derived many of the tests in the project.

[bitcoinDoc]: https://github.com/bitcoin/bitcoin/tree/master/doc#setup
[bitcoinCliBook]: https://github.com/BlockchainCommons/Learning-Bitcoin-from-the-Command-Line