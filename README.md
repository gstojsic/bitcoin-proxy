# bitcoin-proxy
bitcoin-proxy is a java JSON-RPC client that allows access to bitcoin nodes from java applications. It features:

- bitcoin v24 JSON-RPC API
- sync and async client
- small footprint (less than 700kB)
- no external dependencies

## requirements
The proxy requires a bitcoin node to connect to. For details on how to install a bitcoind node please refer
to the [Bitcoin documentation][bitcoinDoc].

The project tests use [testcontainers][testcontainers] to run a bitcoin node in a docker container so docker needs to be installed to run
tests.

## usage
To use the proxy first you have to add it as a dependency to your gradle project:
```gradle
implementation("io.github.gstojsic.bitcoin:proxy:2.0")
```
or maven project:
```maven
<dependency>
    <groupId>io.github.gstojsic.bitcoin</groupId>
    <artifactId>proxy</artifactId>
    <version>2.0</version>
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
## related projects
If you need to process zmq notifications from your bitcoin node in your java application, checkout [bitcoin-zmq][bitcoin-zmq]

## acknowledgments
- to bitcoin core developers for the great service they provide to the community.
- to the good people at BlockchainCommons for the very good [book][bitcoinCliBook] on how to use bitcoin from the
command line from which I derived many of the tests in the project.

[bitcoinDoc]: https://github.com/bitcoin/bitcoin/tree/master/doc#setup
[bitcoinCliBook]: https://github.com/BlockchainCommons/Learning-Bitcoin-from-the-Command-Line
[testcontainers]: https://www.testcontainers.org/
[bitcoin-zmq]: https://github.com/gstojsic/bitcoin-zmq