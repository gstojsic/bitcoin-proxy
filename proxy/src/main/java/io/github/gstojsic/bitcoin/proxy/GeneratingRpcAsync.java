package io.github.gstojsic.bitcoin.proxy;

import io.github.gstojsic.bitcoin.proxy.json.model.GeneratedBlock;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface GeneratingRpcAsync {

    /**
     * <p>Calls generateblock method on the bitcoin node which mines a block with a set of ordered transactions</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.generateblock);</pre>
     *
     * @param output         the address or descriptor to send the newly generated bitcoin to.
     * @param transactionIds a list of hex strings which are either txids or raw transactions.
     * @return hash of generated block. See {@link GeneratedBlock}
     */
    CompletableFuture<GeneratedBlock> generateBlock(String output, List<String> transactionIds);

    /**
     * <p>Calls generatetoaddress method on the bitcoin node which mines blocks immediately to a specified address</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.generatetoaddress);</pre>
     *
     * @param nBlocks  number of blocks that are generated immediately
     * @param address  the address to send the newly generated bitcoin to
     * @param maxTries how many iterations to try
     * @return list of hashes of blocks generated
     */
    CompletableFuture<List<String>> generateToAddress(int nBlocks, String address, Integer maxTries);

    /**
     * <p>Calls generatetodescriptor method on the bitcoin node which mines blocks immediately to a specified descriptor</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.generatetodescriptor);</pre>
     *
     * @param nBlocks    number of blocks that are generated immediately
     * @param descriptor the descriptor to send the newly generated bitcoin to
     * @param maxTries   how many iterations to try
     * @return list of hashes of blocks generated
     */
    CompletableFuture<List<String>> generateToDescriptor(int nBlocks, String descriptor, Integer maxTries);
}
