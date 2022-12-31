package io.github.gstojsic.bitcoin.proxy;

import io.github.gstojsic.bitcoin.proxy.json.model.BlockTemplate;
import io.github.gstojsic.bitcoin.proxy.json.model.MiningInfo;
import io.github.gstojsic.bitcoin.proxy.model.MiningTemplate;

public interface MiningRpc {

    /**
     * @see MiningRpcAsync#getBlockTemplate(MiningTemplate)
     */
    BlockTemplate getBlockTemplate(MiningTemplate templateRequest);

    /**
     * @see MiningRpcAsync#getBlockTemplateProposal(MiningTemplate)
     */
    String getBlockTemplateProposal(MiningTemplate templateRequest);

    /**
     * @see MiningRpcAsync#getMiningInfo()
     */
    MiningInfo getMiningInfo();

    /**
     * @see MiningRpcAsync#getNetworkHashPs(Integer, Integer)
     */
    double getNetworkHashPs(Integer nBlocks, Integer height);

    /**
     * @see MiningRpcAsync#prioritiseTransaction(String, int)
     */
    boolean prioritiseTransaction(String txId, int feeDelta);

    /**
     * @see MiningRpcAsync#submitBlock(String)
     */
    String submitBlock(String hexData);

    /**
     * @see MiningRpcAsync#submitHeader(String)
     */
    void submitHeader(String hexData);
}
