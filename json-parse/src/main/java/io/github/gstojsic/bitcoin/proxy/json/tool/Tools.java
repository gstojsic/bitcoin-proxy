package io.github.gstojsic.bitcoin.proxy.json.tool;

import javax.annotation.processing.Messager;
import javax.tools.Diagnostic;

public enum Tools {
    ;
    private static final Boolean DEBUG_LOG;

    static {
        String env = System.getenv("json.parse.debug.level");
        env = env != null ? env.toUpperCase() : "";
        DEBUG_LOG = "DEBUG".equals(env);
    }

    public static void debugLog(Messager messager, String msg, Object... args) {
        if (!DEBUG_LOG)
            return;

        messager.printMessage(Diagnostic.Kind.NOTE, String.format(msg, args));
    }

    public static void errorLog(Messager messager, String msg, Object... args) {
        messager.printMessage(Diagnostic.Kind.ERROR, String.format(msg, args));
    }

    public static String firstLetterToUpper(String item) {
        return Character.toUpperCase(item.charAt(0)) + item.substring(1);
    }

    public static String firstLetterToLower(String item) {
        return Character.toLowerCase(item.charAt(0)) + item.substring(1);
    }
}
