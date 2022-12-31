package io.github.gstojsic.bitcoin.proxy.json;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static io.github.gstojsic.bitcoin.proxy.json.BaseJsonInput.parseList;
import static org.junit.jupiter.api.Assertions.*;

public class TestParsersTest {

    @Test
    void name() {
        InputStream is = new ByteArrayInputStream(RPC_RESPONSE.getBytes());
        JsonResp response = TestParsersBuilder.get().parseJr(is);
        assertNotNull(response);
        assertEquals("0000000000000000000596d35991ff1f865ba29d71a1241ba002cb7ed569f4fb", response.getResult());
        assertNull(response.getError());
        assertEquals("curltest", response.getId());
        assertNotNull(response.getTransaction());
        assertEquals("fsfsdfsdfsfdsfdsdf", response.getTransaction().getHash());
        assertEquals(2.32324, response.getTransaction().getAmount());
        assertEquals("the note", response.getTransaction().getNote());
        assertNotNull(response.getOldTransactions());
        assertEquals(2, response.getOldTransactions().size());
        Transaction t1 = response.getOldTransactions().get(0);
        assertEquals("2fsfsdfsdfsfdsfdsdf", t1.getHash());
        assertEquals(1.32324, t1.getAmount());
        assertEquals("the note1", t1.getNote());
        Transaction t2 = response.getOldTransactions().get(1);
        assertEquals("1fsfsdfsdfsfdsfdsdf", t2.getHash());
        assertEquals(2.3232434, t2.getAmount());
        assertEquals("3the note", t2.getNote());
    }

    @Test
    void parseListWithinList() {
        JsonInputString is = new JsonInputString(LIST_JSON);
        var parsed = parseList(is,
                __ -> parseList(is,
                        rew -> parseList(is,
                                BaseJsonInput::readStringOrNull
                                , null)
                        , null)
                , null);
        System.out.println(parsed);

        JsonInputString is2 = new JsonInputString(LIST_JSON);
        var alternative = parseList(is2,
                i -> parseList(i, BaseJsonInput::parseStringList, null)
                , null);
        System.out.println(alternative);
    }

    @Test
    void listAddressGroupings() {
        InputStream is = new ByteArrayInputStream(LIST_ADDRESS_GROUPINGS_JSON.getBytes());
        var p = TestParsersBuilder.get().parseJrListAddressGrouping(is);
        assertEquals("bcrt1q8h8z7f8xxytj9f65v6wx4vfuud48v3vs6kglw7", p.getResult().getAddress());
        assertEquals(50.0, p.getResult().getAmount());
        assertEquals("aliceMining", p.getResult().getLabel());
    }

    private static final String LIST_ADDRESS_GROUPINGS_JSON = """
            {
              "result": [
                  "bcrt1q8h8z7f8xxytj9f65v6wx4vfuud48v3vs6kglw7",
                  50.00000000,
                  "aliceMining"
              ],
              "error": null,
              "id": "1"
            }
            """;

    private static final String LIST_JSON = """
            [
              [
                [
                  "bcrt1q8h8z7f8xxytj9f65v6wx4vfuud48v3vs6kglw7",
                  "50.00000000",
                  "aliceMining"
                ],
                [
                  "bcrt1qvz2lrnedx0n7gtu0eqkte6yl3gjrdslz7kc670",
                  "48.99998590"
                ]
              ]
            ]
            """;

    private static final String RPC_RESPONSE = """
            {
                "result": "0000000000000000000596d35991ff1f865ba29d71a1241ba002cb7ed569f4fb",
                "error": null,
                "id": "curltest",
                "active": false,
                "transaction": { "hash": "fsfsdfsdfsfdsfdsdf", "amount": 2.32324, "note": "the note" },
                "oldTransactions": [{ "hash": "2fsfsdfsdfsfdsfdsdf", "amount": 1.32324, "note": "the note1" }, { "hash": "1fsfsdfsdfsfdsfdsdf", "amount": 2.3232434, "note": "3the note" }],
                "banana": false
            }
            """;
}
