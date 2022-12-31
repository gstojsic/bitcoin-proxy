package io.github.gstojsic.bitcoin.proxy.json;

import io.github.gstojsic.bitcoin.proxy.json.annotation.JsonParserFactory;
import io.github.gstojsic.bitcoin.proxy.json.model.ListAddressGrouping;
import io.github.gstojsic.bitcoin.proxy.json.model.MempoolData;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

@JsonParserFactory
public interface TestParsers {
    JsonResp parseJr(InputStream stream);

    RpcResponse<String> parseJrs(InputStream stream);

    RpcResponse<Map<String, MempoolData>> parseJrMapMempoolData(InputStream stream);

    RpcResponse<List<List<List<String>>>> parseJrListAllTheWayDown(InputStream stream);

    RpcResponse<ListAddressGrouping> parseJrListAddressGrouping(InputStream stream);
}
