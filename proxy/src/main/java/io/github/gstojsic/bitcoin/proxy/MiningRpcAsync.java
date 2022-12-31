package io.github.gstojsic.bitcoin.proxy;

import io.github.gstojsic.bitcoin.proxy.json.model.BlockTemplate;
import io.github.gstojsic.bitcoin.proxy.json.model.MiningInfo;
import io.github.gstojsic.bitcoin.proxy.model.MiningTemplate;

import java.util.concurrent.CompletableFuture;

public interface MiningRpcAsync {

    /**
     * <p>Calls getblocktemplate method on the bitcoin node which returns data needed to construct a block to work on</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.getblocktemplate);</pre>
     *
     * @param templateRequest the template request. for details check bitcoin node docs.
     * @return data needed to construct a block to work on. See {@link BlockTemplate}
     */
    CompletableFuture<BlockTemplate> getBlockTemplate(MiningTemplate templateRequest);

    /**
     * <p>Calls getblocktemplate method on the bitcoin node which returns data needed to construct a block to work on</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.getblocktemplate);</pre>
     *
     * @param templateRequest the template request. for details check bitcoin node docs.
     * @return null if proposal was accepted, else a string according to BIP22
     */
    CompletableFuture<String> getBlockTemplateProposal(MiningTemplate templateRequest);

    /**
     * <p>Calls getmininginfo method on the bitcoin node which returns mining-related information./p>
     * Get more info with:<br/>
     * <pre>client.help(Command.getmininginfo);</pre>
     *
     * @return object containing mining-related information. See {@link MiningInfo}
     */
    CompletableFuture<MiningInfo> getMiningInfo();

    /**
     * <p>Calls getnetworkhashps method on the bitcoin node which returns the estimated network hashes per second
     * based on the last n blocks</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.getnetworkhashps);</pre>
     *
     * @param nBlocks the number of blocks, or -1 for blocks since last difficulty change.
     * @param height  to estimate at the time of the given height
     * @return hashes per second estimated
     */
    CompletableFuture<Double> getNetworkHashPs(Integer nBlocks, Integer height);

    /**
     * <p>Calls prioritisetransaction method on the bitcoin node which accepts the transaction into mined blocks at
     * a higher (or lower) priority</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.prioritisetransaction);</pre>
     *
     * @param txId     the id of the transaction
     * @param feeDelta the fee value (in satoshis) to add (or subtract, if negative)
     * @return true if successful
     */
    CompletableFuture<Boolean> prioritiseTransaction(String txId, int feeDelta);

    /**
     *  <p>Calls submitheader method on the bitcoin node which attempts to submit new block to network.</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.submitblock);</pre>
     *
     * @param hexData the hex-encoded block data to submit
     * @return null if valid, a string according to BIP22 otherwise
     */
    CompletableFuture<String> submitBlock(String hexData);

    /**
     * <p>Calls submitheader method on the bitcoin node which decodes the given hexdata as a header and submits it
     * as a candidate chain tip if valid</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.submitheader);</pre>
     *
     * @param hexData the hex-encoded block header data
     * @return void
     */
    CompletableFuture<Void> submitHeader(String hexData);
}
