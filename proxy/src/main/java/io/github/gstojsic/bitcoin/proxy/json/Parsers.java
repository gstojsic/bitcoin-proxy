package io.github.gstojsic.bitcoin.proxy.json;

import io.github.gstojsic.bitcoin.proxy.json.annotation.JsonParserFactory;
import io.github.gstojsic.bitcoin.proxy.json.model.AddPeerAddress;
import io.github.gstojsic.bitcoin.proxy.json.model.AddedNodeInfo;
import io.github.gstojsic.bitcoin.proxy.json.model.AddressByLabel;
import io.github.gstojsic.bitcoin.proxy.json.model.AddressInfo;
import io.github.gstojsic.bitcoin.proxy.json.model.AddressValidation;
import io.github.gstojsic.bitcoin.proxy.json.model.BalanceByAddress;
import io.github.gstojsic.bitcoin.proxy.json.model.BalancesData;
import io.github.gstojsic.bitcoin.proxy.json.model.BannedInfo;
import io.github.gstojsic.bitcoin.proxy.json.model.Block1;
import io.github.gstojsic.bitcoin.proxy.json.model.Block2;
import io.github.gstojsic.bitcoin.proxy.json.model.BlockFilter;
import io.github.gstojsic.bitcoin.proxy.json.model.BlockHeader;
import io.github.gstojsic.bitcoin.proxy.json.model.BlockStats;
import io.github.gstojsic.bitcoin.proxy.json.model.BlockTemplate;
import io.github.gstojsic.bitcoin.proxy.json.model.BlockchainInfo;
import io.github.gstojsic.bitcoin.proxy.json.model.BumpFee;
import io.github.gstojsic.bitcoin.proxy.json.model.ChainTipData;
import io.github.gstojsic.bitcoin.proxy.json.model.ChainTxStats;
import io.github.gstojsic.bitcoin.proxy.json.model.DecodedPsbt;
import io.github.gstojsic.bitcoin.proxy.json.model.DecodedScript;
import io.github.gstojsic.bitcoin.proxy.json.model.DeploymentInfo;
import io.github.gstojsic.bitcoin.proxy.json.model.DescriptorInfo;
import io.github.gstojsic.bitcoin.proxy.json.model.Descriptors;
import io.github.gstojsic.bitcoin.proxy.json.model.DumpFile;
import io.github.gstojsic.bitcoin.proxy.json.model.Empty;
import io.github.gstojsic.bitcoin.proxy.json.model.FeeEstimate;
import io.github.gstojsic.bitcoin.proxy.json.model.FinalizedPsbt;
import io.github.gstojsic.bitcoin.proxy.json.model.GeneratedBlock;
import io.github.gstojsic.bitcoin.proxy.json.model.ImportMultiResult;
import io.github.gstojsic.bitcoin.proxy.json.model.IndexInfo;
import io.github.gstojsic.bitcoin.proxy.json.model.ListAddressGrouping;
import io.github.gstojsic.bitcoin.proxy.json.model.MemoryInfo;
import io.github.gstojsic.bitcoin.proxy.json.model.MempoolAccept;
import io.github.gstojsic.bitcoin.proxy.json.model.MempoolData;
import io.github.gstojsic.bitcoin.proxy.json.model.MempoolInfo;
import io.github.gstojsic.bitcoin.proxy.json.model.MempoolWithSeq;
import io.github.gstojsic.bitcoin.proxy.json.model.MiningInfo;
import io.github.gstojsic.bitcoin.proxy.json.model.MultisigAddress;
import io.github.gstojsic.bitcoin.proxy.json.model.NetTotals;
import io.github.gstojsic.bitcoin.proxy.json.model.NetworkInfo;
import io.github.gstojsic.bitcoin.proxy.json.model.NodeAddress;
import io.github.gstojsic.bitcoin.proxy.json.model.PeerInfo;
import io.github.gstojsic.bitcoin.proxy.json.model.PsbtAnalysis;
import io.github.gstojsic.bitcoin.proxy.json.model.PsbtBumpFee;
import io.github.gstojsic.bitcoin.proxy.json.model.RawTransaction;
import io.github.gstojsic.bitcoin.proxy.json.model.RpcInfo;
import io.github.gstojsic.bitcoin.proxy.json.model.ScanInfo;
import io.github.gstojsic.bitcoin.proxy.json.model.ScanTxOutResult;
import io.github.gstojsic.bitcoin.proxy.json.model.SendInfo;
import io.github.gstojsic.bitcoin.proxy.json.model.SendToAddressInfo;
import io.github.gstojsic.bitcoin.proxy.json.model.SignTransactionResult;
import io.github.gstojsic.bitcoin.proxy.json.model.Signers;
import io.github.gstojsic.bitcoin.proxy.json.model.TransactionByLabel;
import io.github.gstojsic.bitcoin.proxy.json.model.TransactionFunding;
import io.github.gstojsic.bitcoin.proxy.json.model.TransactionOutput;
import io.github.gstojsic.bitcoin.proxy.json.model.TransactionOutputSetInfo;
import io.github.gstojsic.bitcoin.proxy.json.model.TransactionSinceBlock;
import io.github.gstojsic.bitcoin.proxy.json.model.UnloadWallet;
import io.github.gstojsic.bitcoin.proxy.json.model.UnspentInfo;
import io.github.gstojsic.bitcoin.proxy.json.model.UpgradeWallet;
import io.github.gstojsic.bitcoin.proxy.json.model.UtxoKey;
import io.github.gstojsic.bitcoin.proxy.json.model.Wallet;
import io.github.gstojsic.bitcoin.proxy.json.model.WalletDisplayAddress;
import io.github.gstojsic.bitcoin.proxy.json.model.WalletFlagInfo;
import io.github.gstojsic.bitcoin.proxy.json.model.WalletFundedPsbt;
import io.github.gstojsic.bitcoin.proxy.json.model.WalletInfo;
import io.github.gstojsic.bitcoin.proxy.json.model.WalletPsbt;
import io.github.gstojsic.bitcoin.proxy.json.model.WalletTransaction;
import io.github.gstojsic.bitcoin.proxy.json.model.WalletTransactionInfo;
import io.github.gstojsic.bitcoin.proxy.json.model.Wallets;
import io.github.gstojsic.bitcoin.proxy.json.model.ZmqNotificationInfo;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

@JsonParserFactory
public interface Parsers {
    RpcResponse<String> parseJrString(InputStream stream);

    RpcResponse<Boolean> parseJrBoolean(InputStream stream);

    RpcResponse<List<String>> parseJrStringList(InputStream stream);

    RpcResponse<BlockchainInfo> parseJrBlockchainInfo(InputStream stream);

    RpcResponse<Integer> parseJrInteger(InputStream stream);

    RpcResponse<Double> parseJrDouble(InputStream stream);

    RpcResponse<BlockFilter> parseJrBlockFilter(InputStream stream);

    RpcResponse<BlockStats> parseJrBlockStats(InputStream stream);

    RpcResponse<List<ChainTipData>> parseJrChainTipList(InputStream stream);

    RpcResponse<ChainTxStats> parseJrChainTxStats(InputStream stream);

    RpcResponse<MempoolData> parseJrMempoolData(InputStream stream);

    RpcResponse<Map<String, MempoolData>> parseJrMapMempoolData(InputStream stream);

    RpcResponse<MempoolInfo> parseJrMempoolInfo(InputStream stream);

    RpcResponse<MempoolWithSeq> parseJrMempoolWithSeq(InputStream stream);

    RpcResponse<TransactionOutput> parseJrTransactionOutput(InputStream stream);

    RpcResponse<TransactionOutputSetInfo> parseJrTransactionOutputSetInfo(InputStream stream);

    RpcResponse<MemoryInfo> parseJrMemoryInfo(InputStream stream);

    RpcResponse<RpcInfo> parseJrRpcInfo(InputStream stream);

    RpcResponse<List<PeerInfo>> parseJrPeerInfoList(InputStream stream);

    RpcResponse<Wallet> parseJrWallet(InputStream stream);

    RpcResponse<List<BalanceByAddress>> parseJrBalanceByAddressList(InputStream stream);

    RpcResponse<List<TransactionByLabel>> parseJrBalanceByLabelList(InputStream stream);

    RpcResponse<List<WalletTransactionInfo>> parseJrWalletTransactionInfoList(InputStream stream);

    RpcResponse<WalletTransaction> parseJrWalletTransaction(InputStream stream);

    RpcResponse<RawTransaction> parseJrRawTransaction(InputStream stream);

    RpcResponse<BalancesData> parseJrBalancesData(InputStream stream);

    RpcResponse<DumpFile> parseJrDumpFile(InputStream stream);

    RpcResponse<WalletInfo> parseJrWalletInfo(InputStream stream);

    RpcResponse<List<UnspentInfo>> parseJrUnspentInfoList(InputStream stream);

    RpcResponse<MiningInfo> parseJrMiningInfo(InputStream stream);

    RpcResponse<NetworkInfo> parseJrNetworkInfo(InputStream stream);

    RpcResponse<NetTotals> parseJrNetTotals(InputStream stream);

    RpcResponse<DescriptorInfo> parseJrDescriptorInfo(InputStream stream);

    RpcResponse<DeploymentInfo> parseJrDeploymentInfo(InputStream stream);

    RpcResponse<Map<String, IndexInfo>> parseJrIndexInfoMap(InputStream stream);

    RpcResponse<Wallets> parseJrWallets(InputStream stream);

    RpcResponse<UpgradeWallet> parseJrUpgradeWallet(InputStream stream);

    RpcResponse<UnloadWallet> parseJrUnloadWallet(InputStream stream);

    RpcResponse<AddressInfo> parseJrAddressInfo(InputStream stream);

    RpcResponse<Map<String, AddressByLabel>> parseJrMapAddressByLabel(InputStream stream);

    RpcResponse<BlockHeader> parseJrBlockHeader(InputStream stream);

    RpcResponse<Block1> parseJrBlock1(InputStream stream);

    RpcResponse<Block2> parseJrBlock2(InputStream stream);

    RpcResponse<Map<String, Boolean>> parseJrMapBoolean(InputStream stream);

    RpcResponse<Descriptors> parseJrDescriptors(InputStream stream);

    RpcResponse<List<UtxoKey>> parseJrUtxoKeyList(InputStream stream);

    RpcResponse<WalletFlagInfo> parseJrWalletFlagInfo(InputStream stream);

    RpcResponse<List<NodeAddress>> parseJrNodeAddressList(InputStream stream);

    RpcResponse<AddressValidation> parseJrAddressValidation(InputStream stream);

    RpcResponse<SignTransactionResult> parseJrSignTransactionResult(InputStream stream);

    RpcResponse<List<MempoolAccept>> parseJrMempoolAcceptList(InputStream stream);

    RpcResponse<TransactionFunding> parseJrTransactionFunding(InputStream stream);

    RpcResponse<BumpFee> parseJrBumpFee(InputStream stream);

    RpcResponse<MultisigAddress> parseJrMultisigAddress(InputStream stream);

    RpcResponse<List<ImportMultiResult>> parseJrImportMultiResultList(InputStream stream);

    RpcResponse<DecodedPsbt> parseJrDecodedPsbt(InputStream stream);

    RpcResponse<PsbtAnalysis> parseJrPsbtAnalysis(InputStream stream);

    RpcResponse<WalletPsbt> parseJrWalletPsbt(InputStream stream);

    RpcResponse<WalletFundedPsbt> parseJrWalletFundedPsbt(InputStream stream);

    RpcResponse<FinalizedPsbt> parseJrFinalizedPsbt(InputStream stream);

    RpcResponse<GeneratedBlock> parseJrGeneratedBlock(InputStream stream);

    RpcResponse<DecodedScript> parseJrDecodedScript(InputStream stream);

    RpcResponse<TransactionSinceBlock> parseJrTransactionSinceBlock(InputStream stream);

    RpcResponse<ScanInfo> parseJrScanInfo(InputStream stream);

    RpcResponse<SendToAddressInfo> parseJrSendToAddressInfo(InputStream stream);

    RpcResponse<SendInfo> parseJrSendInfo(InputStream stream);

    RpcResponse<List<ZmqNotificationInfo>> parseJrZmqNotificationInfoList(InputStream stream);

    RpcResponse<Signers> parseJrSigners(InputStream stream);

    RpcResponse<List<List<ListAddressGrouping>>> parseJrListAddressGrouping(InputStream stream);

    RpcResponse<ScanTxOutResult> parseJrScanTxOutResult(InputStream stream);

    RpcResponse<BlockTemplate> parseJrBlockTemplate(InputStream stream);

    RpcResponse<WalletDisplayAddress> parseJrWalletDisplayAddress(InputStream stream);

    RpcResponse<PsbtBumpFee> parseJrPsbtBumpFee(InputStream stream);

    RpcResponse<List<AddedNodeInfo>> parseJrAddedNodeInfoList(InputStream stream);

    RpcResponse<List<BannedInfo>> parseJrBannedInfoList(InputStream stream);

    RpcResponse<Empty> parseJrEmpty(InputStream stream);

    RpcResponse<AddPeerAddress> parseJrAddPeerAddress(InputStream stream);

    RpcResponse<FeeEstimate> parseJrFeeEstimate(InputStream stream);
}
