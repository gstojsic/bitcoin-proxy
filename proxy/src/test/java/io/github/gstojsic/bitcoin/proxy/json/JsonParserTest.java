package io.github.gstojsic.bitcoin.proxy.json;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class JsonParserTest {

    @Test
    void genParseTest() {
        InputStream is = new ByteArrayInputStream(RESPONSE.getBytes());
        JsonResp response = TestParsersBuilder.get().parseJr(is);

        assertNotNull(response);
        assertEquals("0000000000000000000596d35991ff1f865ba29d71a1241ba002cb7ed569f4fb", response.getResult());
        assertNull(response.getError());
        assertEquals("curltest", response.getId());
        assertFalse(response.isActive());
        assertEquals(123.4343, response.getCount());
        //assertTrue(response.isActive());
    }

    @Test
    void generatorParseTest() {
        InputStream is = new ByteArrayInputStream(RPC_RESPONSE.getBytes());
        RpcResponse<String> response = ParsersBuilder.get().parseJrString(is);

        assertNotNull(response);
        assertEquals("0000000000000000000596d35991ff1f865ba29d71a1241ba002cb7ed569f4fb", response.getResult());
        assertNull(response.getError());
        assertEquals("curltest", response.getId());
        //assertTrue(response.isActive());
    }

    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);

        System.out.println("Enter name, age and salary:");

        // String input
        String name = myObj.nextLine();

        // Numerical input
        int age = myObj.nextInt();
        double salary = myObj.nextDouble();

        // Output input by user
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Salary: " + salary);
    }

    private static final String RESPONSE = """
            {
                "result": "0000000000000000000596d35991ff1f865ba29d71a1241ba002cb7ed569f4fb",
                "error": null,
                "id": "curltest",
                "active": false,
                "count": 123.4343
            }
            """;

    private static final String RPC_RESPONSE = """
            {
                "result": "0000000000000000000596d35991ff1f865ba29d71a1241ba002cb7ed569f4fb",
                "error": null,
                "id": "curltest",
                "active": false
            }
            """;
}