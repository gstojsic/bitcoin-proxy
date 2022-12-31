package io.github.gstojsic.bitcoin.proxy.json;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.text.NumberFormat;

import static org.junit.jupiter.api.Assertions.*;

class JsonInputStringTest {

    @Test
    public void readNull() {
        JsonInputString input = new JsonInputString(" null ");
        var n = input.readNumberOrNull();
        assertNull(n);
    }

    @Test
    public void readNumber() {
        JsonInputString input = new JsonInputString("123.221 ");
        var n = input.readNumberOrNull();
        assertNotNull(n);
        String num = (String) n[0];
        assertEquals("123.221", num);
        char c = (char) n[1];
        assertEquals(' ', c);
        double d = Double.parseDouble(num);
        assertEquals(123.221, d);
    }

    @Test
    public void parsing() throws Exception {
        var d = NumberFormat.getInstance().parse("-12.34234e-21");
        System.out.println(d.doubleValue());
        System.out.println(new BigDecimal("-12.34234e-21"));
        System.out.println(new BigDecimal("-1.34234"));
        System.out.println(new BigDecimal("-12.34234e+21"));
        System.out.println(new BigDecimal("12.3423421"));
        System.out.println(new BigDecimal("-1234.234e-21"));
        System.out.println(Double.parseDouble("-1234.234e-21"));
    }
}