package io.github.gstojsic.bitcoin.proxy;

import io.github.gstojsic.bitcoin.proxy.json.model.AddressValidation;
import io.github.gstojsic.bitcoin.proxy.json.model.DeploymentInfo;
import io.github.gstojsic.bitcoin.proxy.json.model.DescriptorInfo;
import io.github.gstojsic.bitcoin.proxy.json.model.FeeEstimate;
import io.github.gstojsic.bitcoin.proxy.json.model.IndexInfo;
import io.github.gstojsic.bitcoin.proxy.json.model.MultisigAddress;
import io.github.gstojsic.bitcoin.proxy.json.model.ZmqNotificationInfo;
import io.github.gstojsic.bitcoin.proxy.model.AddressType;
import io.github.gstojsic.bitcoin.proxy.model.EstimateMode;
import io.github.gstojsic.bitcoin.proxy.util.Pair;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public interface UtilRpcAsync {

    /**
     * <p>Calls createmultisig method on the bitcoin node which creates a multi-signature address with n signature
     * of m keys required.</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.createmultisig);</pre>
     *
     * @param nRequired   the number of required signatures out of the n keys
     * @param keys        the hex-encoded public keys
     * @param addressType the address type to use
     * @return an object representing the multisig address. See {@link MultisigAddress}
     */
    CompletableFuture<MultisigAddress> createMultisig(int nRequired, List<String> keys, AddressType addressType);

    /**
     * <p>Calls deriveaddresses method on the bitcoin node which derives one or more addresses corresponding to
     * an output descriptor</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.deriveaddresses);</pre>
     *
     * @param descriptor the descriptor
     * @param range      if a ranged descriptor is used, this specifies the end or the range (in [begin,end] notation)
     *                   to derive
     * @return a list of derived addresses
     */
    CompletableFuture<List<String>> deriveAddresses(String descriptor, Pair<Integer, Integer> range);

    /**
     * <p>Calls estimatesmartfee method on the bitcoin node which estimates the approximate fee per kilobyte needed
     * for a transaction to begin confirmation within confirmationTarget blocks</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.estimatesmartfee);</pre>
     *
     * @param confirmationTarget confirmation target in blocks (1 - 1008)
     * @param estimateMode       the fee estimate mode
     * @return an object representing the fee estimate. See {@link FeeEstimate}
     */
    CompletableFuture<FeeEstimate> estimateSmartFee(int confirmationTarget, EstimateMode estimateMode);

    /**
     * <p>Calls getdeploymentinfo method on the bitcoin node which returns an object containing various state info
     * regarding deployments of consensus changes</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.getdeploymentinfo);</pre>
     *
     * @param blockhash the block hash at which to query deployment state
     * @return an object representing the deployment information. See {@link DeploymentInfo}
     */
    CompletableFuture<DeploymentInfo> getDeploymentInfo(String blockhash);

    /**
     * <p>Calls getdescriptorinfo method on the bitcoin node which analyses a descriptor</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.getdescriptorinfo);</pre>
     *
     * @param descriptor the descriptor
     * @return an object with descriptor information. See {@link DescriptorInfo}
     */
    CompletableFuture<DescriptorInfo> getDescriptorInfo(String descriptor);

    /**
     * <p>Calls getindexinfo method on the bitcoin node which returns the status of one or all available indices
     * currently running in the node</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.getindexinfo);</pre>
     *
     * @param indexName filter results for an index with a specific name
     * @return a map for which the key is the index name and the value is the information on that index.
     * See {@link IndexInfo}
     */
    CompletableFuture<Map<String, IndexInfo>> getIndexInfo(String indexName);

    /**
     * <p>Calls getzmqnotifications method on the bitcoin node which returns information about the active ZeroMQ
     * notifications</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.getzmqnotifications);</pre>
     *
     * @return a list of ZeroMQ notification information objects. See {@link ZmqNotificationInfo}
     */
    CompletableFuture<List<ZmqNotificationInfo>> getZmqNotifications();

    /**
     * <p>Calls signmessagewithprivkey method on the bitcoin node which signs a message with the private key of an address</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.signmessagewithprivkey);</pre>
     *
     * @param privateKey the private key to sign the message with
     * @param message    the message to create a signature of
     * @return the signature of the message encoded in base 64
     */
    CompletableFuture<String> signMessageWithPrivKey(String privateKey, String message);

    /**
     * <p>Calls validateaddress method on the bitcoin node which returns information about the given bitcoin address</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.validateaddress);</pre>
     *
     * @param address the bitcoin address to validate
     * @return an address validation object. See {@link AddressValidation}
     */
    CompletableFuture<AddressValidation> validateAddress(String address);

    /**
     * <p>Calls verifymessage method on the bitcoin node which verifies a signed message</p>
     * Get more info with:<br/>
     * <pre>client.help(Command.verifymessage);</pre>
     *
     * @param address   the bitcoin address to use for the signature
     * @param signature the signature provided by the signer in base 64 encoding (see signmessage)
     * @param message   the message that was signed
     * @return If the signature is verified or not
     */
    CompletableFuture<Boolean> verifyMessage(String address, String signature, String message);
}
