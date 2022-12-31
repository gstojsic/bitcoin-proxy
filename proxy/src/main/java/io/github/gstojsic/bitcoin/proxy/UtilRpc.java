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

public interface UtilRpc {

    /**
     * @see UtilRpcAsync#createMultisig(int, List, AddressType)
     */
    MultisigAddress createMultisig(int nRequired, List<String> keys, AddressType addressType);

    /**
     * @see UtilRpcAsync#deriveAddresses(String, Pair)
     */
    List<String> deriveAddresses(String descriptor, Pair<Integer, Integer> range);

    /**
     * @see UtilRpcAsync#estimateSmartFee(int, EstimateMode)
     */
    FeeEstimate estimateSmartFee(int confirmationTarget, EstimateMode estimateMode);

    /**
     * @see UtilRpcAsync#getDeploymentInfo(String)
     */
    DeploymentInfo getDeploymentInfo(String blockhash);

    /**
     * @see UtilRpcAsync#getDescriptorInfo(String)
     */
    DescriptorInfo getDescriptorInfo(String descriptor);

    /**
     * @see UtilRpcAsync#getIndexInfo(String)
     */
    Map<String, IndexInfo> getIndexInfo(String indexName);

    /**
     * @see UtilRpcAsync#getZmqNotifications()
     */
    List<ZmqNotificationInfo> getZmqNotifications();

    /**
     * @see UtilRpcAsync#signMessageWithPrivKey(String, String)
     */
    String signMessageWithPrivKey(String privateKey, String message);

    /**
     * @see UtilRpcAsync#validateAddress(String)
     */
    AddressValidation validateAddress(String address);

    /**
     * @see UtilRpcAsync#verifyMessage(String, String, String)
     */
    boolean verifyMessage(String address, String signature, String message);
}
