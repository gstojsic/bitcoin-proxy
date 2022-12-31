package io.github.gstojsic.bitcoin.proxy;

import io.github.gstojsic.bitcoin.proxy.json.model.GeneratedBlock;

import java.util.List;

public interface GeneratingRpc {

    /**
     * @see GeneratingRpcAsync#generateBlock(String, List)
     */
    GeneratedBlock generateBlock(String output, List<String> transactionIds);

    /**
     * @see GeneratingRpcAsync#generateToAddress(int, String, Integer)
     */
    List<String> generateToAddress(int nBlocks, String address, Integer maxTries);

    /**
     * @see GeneratingRpcAsync#generateToDescriptor(int, String, Integer)
     */
    List<String> generateToDescriptor(int nBlocks, String descriptor, Integer maxTries);
}