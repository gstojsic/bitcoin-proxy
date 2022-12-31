package io.github.gstojsic.bitcoin.proxy.util;

import org.junit.jupiter.api.TestInfo;

public enum Util {
    ;
    public static String getTestName(TestInfo info) {
        return info.getTestMethod().orElseThrow().getName();
    }
}
