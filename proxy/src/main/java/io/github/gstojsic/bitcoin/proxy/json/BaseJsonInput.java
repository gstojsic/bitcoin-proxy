package io.github.gstojsic.bitcoin.proxy.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.Collections.emptyList;

abstract public class BaseJsonInput {

    abstract public char readChar();

    public char readNonWsChar() {
        char c;
        do {
            c = readChar();
        } while (Character.isWhitespace(c));
        return c;
    }

    public Character startObject() {
        char c = readNonWsChar();

        switch (c) {
            case OBJECT_START, ARRAY_END -> {
                return c;
            }
            case N -> {
                parseSequence("ull");
                return null;
            }
            default -> throw new IllegalStateException(OBJECT_START_OR_NULL_ERROR.formatted(c));
        }
    }

    public void readColon() {
        char c = readNonWsChar();
        if (c != COLON)
            throw new IllegalStateException(WRONG_CHAR_ERROR.formatted(COLON, c));
    }

    public char readCommaOrObjectEnd() {
        char c = readNonWsChar();
        if (c != COMMA && c != OBJECT_END)
            throw new IllegalStateException(COMMA_OR_OBJECT_END_ERROR.formatted(c));
        return c;
    }

    private String getString() {
        char c;
        StringBuilder sb = new StringBuilder();
        do {
            c = readChar();
            if (c == STRING_DELIMITER)
                break;
            else if (c == BACKSLASH) {
                sb.append(readEscaped());
            } else
                sb.append(c);
        } while (true);
        return sb.toString();
    }

    public String readStringOrNull() {
        char c = readNonWsChar();

        switch (c) {
            case STRING_DELIMITER -> {
                return getString();
            }
            case N -> {
                parseSequence("ull");
                return null;
            }
            default -> throw new IllegalStateException(STRING_OR_NULL_ERROR.formatted(c));
        }
    }

    public Boolean readBooleanOrNull() {
        char c = readNonWsChar();

        switch (c) {
            case T -> {
                parseSequence("rue");
                return true;
            }
            case F -> {
                parseSequence("alse");
                return false;
            }
            case N -> {
                parseSequence("ull");
                return null;
            }
            default -> throw new IllegalStateException(BOOLEAN_OR_NULL_ERROR.formatted(c));
        }
    }

    public Object[] readNumberOrNull() {
        char c = readNonWsChar();

        if (c == N) {
            parseSequence("ull");
            return null;
        }

        if (c != NEGATIVE && !Character.isDigit(c))
            throw new IllegalStateException(NUMBER_ERROR.formatted(c));

        StringBuilder sb = new StringBuilder();
        while (Character.isDigit(c) || c == 'e' || c == 'E' || c == NEGATIVE || c == POSITIVE || c == DOT) {
            sb.append(c);
            c = readChar();
        }

        return new Object[]{sb.toString(), c};
    }

    public String readStringOrObjectEnd() {
        char c = readNonWsChar();

        switch (c) {
            case STRING_DELIMITER -> {
                return getString();
            }
            case OBJECT_END -> {
                return null;
            }
            default -> throw new IllegalStateException(STRING_OR_OBJECT_END_ERROR.formatted(c));
        }
    }

    public char skipValue() {
        char c = readNonWsChar();
        switch (c) {
            case STRING_DELIMITER -> {
                do {
                    c = readChar();
                    if (c == STRING_DELIMITER)
                        break;
                    else if (c == BACKSLASH)
                        readEscaped();
                } while (true);
                return readCommaOrObjectEnd();
            }
            case OBJECT_START -> {
                return skipEntity(OBJECT_START, OBJECT_END);
            }
            case ARRAY_START -> {
                return skipEntity(ARRAY_START, ARRAY_END);
            }
            case T -> {
                parseSequence("rue");
                return readCommaOrObjectEnd();
            }
            case F -> {
                parseSequence("alse");
                return readCommaOrObjectEnd();
            }
            case N -> {
                parseSequence("ull");
                return readCommaOrObjectEnd();
            }
            case NEGATIVE, '1', '2', '3', '4', '5', '6', '7', '8', '9', '0' -> { //wtf
                while (Character.isDigit(c) || c == 'e' || c == 'E' || c == NEGATIVE || c == POSITIVE || c == DOT) {
                    c = readChar();
                }
                return c;
            }
            default -> throw new IllegalStateException(WRONG_VALUE_START_ERROR.formatted(c));
        }
    }

    public Character startListOrNull() {
        char c = readNonWsChar();

        switch (c) {
            case ARRAY_START -> {
                return c;
            }
            case N -> {
                parseSequence("ull");
                return null;
            }
            default -> throw new IllegalStateException(ARRAY_START_OR_NULL_ERROR.formatted(c));
        }
    }

    public char readCommaOrListEnd() {
        char c = readNonWsChar();
        if (c != COMMA && c != ARRAY_END)
            throw new IllegalStateException(COMMA_OR_LIST_END_ERROR.formatted(c));
        return c;
    }

    private char skipEntity(char entityStart, char entityEnd) {
        int count = 1;
        do {
            char c = readChar();
            if (c == entityStart)
                count++;
            else if (c == entityEnd)
                count--;
        } while (count > 0);
        return readCommaOrObjectEnd();
    }

    private void parseSequence(CharSequence sequence) {
        for (int i = 0; i < sequence.length(); i++) {
            char c = sequence.charAt(i);
            char rc = readChar();
            if (rc != c) throw new IllegalStateException(WRONG_CHAR_ERROR.formatted(c, rc));
        }
    }

    private char[] readEscaped() {
        char c = readChar();
        switch (c) {
            case STRING_DELIMITER -> {
                return STRING_DELIMITER_CHARS;
            }
            case SLASH -> {
                return SLASH_CHARS;
            }
            case BACKSLASH -> {
                return BACKSLASH_CHARS;
            }
            case B -> {
                return B_CHARS;
            }
            case F -> {
                return F_CHARS;
            }
            case N -> {
                return N_CHARS;
            }
            case R -> {
                return R_CHARS;
            }
            case T -> {
                return T_CHARS;
            }
            case U -> {
                String s = "%c%c%c%c".formatted(readChar(), readChar(), readChar(), readChar());
                int i = Integer.parseInt(s, 16);
                return Character.toChars(i);
            }
            default -> throw new IllegalStateException(WRONG_ESCAPE_SEQUENCE_ERROR.formatted(c));
        }
    }

    public List<String> parseStringList() {
        if (startListOrNull() == null) return null;
        List<String> list = new ArrayList<>();
        char nextSeparator;
        do {
            char c = readNonWsChar();
            switch (c) {
                case STRING_DELIMITER -> {
                    list.add(getString());
                    nextSeparator = readCommaOrListEnd();
                }
                case ARRAY_END -> nextSeparator = ARRAY_END;
                case N -> {
                    parseSequence("ull");
                    list.add(null);
                    nextSeparator = readCommaOrListEnd();
                }
                default -> throw new IllegalStateException(STRING_OR_NULL_OR_ARRAY_END_ERROR.formatted(c));
            }
        } while (nextSeparator != ARRAY_END);
        return list;
    }

    public List<Boolean> parseBooleanList() {
        if (startListOrNull() == null) return null;
        List<Boolean> list = new ArrayList<>();
        char nextSeparator;
        do {
            char c = readNonWsChar();
            switch (c) {
                case T -> {
                    parseSequence("rue");
                    list.add(true);
                    nextSeparator = readCommaOrListEnd();
                }
                case F -> {
                    parseSequence("alse");
                    list.add(false);
                    nextSeparator = readCommaOrListEnd();
                }
                case N -> {
                    parseSequence("ull");
                    list.add(null);
                    nextSeparator = readCommaOrListEnd();
                }
                case ARRAY_END -> nextSeparator = ARRAY_END;
                default -> throw new IllegalStateException(BOOLEAN_OR_NULL_ERROR.formatted(c));
            }
        } while (nextSeparator != ARRAY_END);
        return list;
    }

    public List<Integer> parseIntegerList() {
        return parseNumberList(Integer::parseInt);
    }

    public List<Long> parseLongList() {
        return parseNumberList(Long::parseLong);
    }

    public List<Double> parseDoubleList() {
        return parseNumberList(Double::parseDouble);
    }

    public List<Float> parseFloatList() {
        return parseNumberList(Float::parseFloat);
    }

    public List<Short> parseShortList() {
        return parseNumberList(Short::parseShort);
    }

    public List<Byte> parseByteList() {
        return parseNumberList(Byte::parseByte);
    }

    public <T> List<T> parseNumberList(Function<String, T> parser) {
        if (startListOrNull() == null) return null;
        List<T> list = new ArrayList<>();
        char nextSeparator;
        do {
            char c = readNonWsChar();
            if (c == N) {
                parseSequence("ull");
                list.add(null);
                nextSeparator = readCommaOrListEnd();
            } else if (c == ARRAY_END) {
                nextSeparator = ARRAY_END;
            } else if (c == NEGATIVE || Character.isDigit(c)) {
                StringBuilder sb = new StringBuilder();
                while (Character.isDigit(c) || c == 'e' || c == 'E' || c == NEGATIVE || c == POSITIVE || c == DOT) {
                    sb.append(c);
                    c = readChar();
                }
                list.add(parser.apply(sb.toString()));
                nextSeparator = c;
            } else {
                throw new IllegalStateException(NUMBER_ERROR.formatted(c));
            }
        } while (nextSeparator != ARRAY_END);
        return list;
    }

    public static <T> List<T> parseList(BaseJsonInput input, Function<BaseJsonInput, T> parser, T listEndObj) {
        if (input.startListOrNull() == null) return null;
        List<T> list = new ArrayList<>();
        T o = parser.apply(input);
        if (o == listEndObj) return emptyList();
        list.add(o);
        char nextSeparator = input.readCommaOrListEnd();
        while (nextSeparator != ARRAY_END) {
            list.add(parser.apply(input));
            nextSeparator = input.readCommaOrListEnd();
        }
        return list;
    }

    public Map<String, Boolean> parseMapBoolean() {
        return parseMap(this, BaseJsonInput::readBooleanOrNull);
    }

    public Map<String, String> parseMapString() {
        return parseMap(this, BaseJsonInput::readStringOrNull);
    }

    public <T> Map<String, T> parseMapNumber(Function<String, T> parser) {
        Character c = startObject();
        if (c == null) return null;
        //if (c == BaseJsonInput.ARRAY_END) return ARRAY_END_MARKER;
        Map<String, T> parsed = new HashMap<>();
        char nextSeparator;
        do {
            String key = readStringOrObjectEnd();
            if (key == null) break;
            readColon();
            var r = readNumberOrNull();
            parsed.put(key, parser.apply((String) r[0]));
            nextSeparator = (char) r[1];
        } while (nextSeparator != OBJECT_END);
        return parsed;
    }

    public Map<String, Integer> parseIntegerMap() {
        return parseMapNumber(Integer::parseInt);
    }

    public Map<String, Long> parseLongMap() {
        return parseMapNumber(Long::parseLong);
    }

    public Map<String, Double> parseDoubleMap() {
        return parseMapNumber(Double::parseDouble);
    }

    public Map<String, Float> parseFloatMap() {
        return parseMapNumber(Float::parseFloat);
    }

    public Map<String, Short> parseShortMap() {
        return parseMapNumber(Short::parseShort);
    }

    public Map<String, Byte> parseByteMap() {
        return parseMapNumber(Byte::parseByte);
    }

    public static <T> Map<String, T> parseMap(BaseJsonInput input, Function<BaseJsonInput, T> parser) {
        Character c = input.startObject();
        if (c == null) return null;
        //if (c == BaseJsonInput.ARRAY_END) return ARRAY_END_MARKER;
        Map<String, T> parsed = new HashMap<>();
        char nextSeparator;
        do {
            String key = input.readStringOrObjectEnd();
            if (key == null) break;
            input.readColon();
            T item = parser.apply(input);
            parsed.put(key, item);
            nextSeparator = input.readCommaOrObjectEnd();
        } while (nextSeparator != OBJECT_END);
        return parsed;
    }

    private static final char ARRAY_START = '[';
    public static final char ARRAY_END = ']';
    private static final char OBJECT_START = '{';
    public static final char OBJECT_END = '}';
    private static final char COMMA = ',';
    private static final char STRING_DELIMITER = '"';
    private static final char[] STRING_DELIMITER_CHARS = {STRING_DELIMITER};
    private static final char COLON = ':';
    private static final char N = 'n';
    private static final char[] N_CHARS = {'\n'};
    private static final char T = 't';
    private static final char[] T_CHARS = {'\t'};
    private static final char F = 'f';
    private static final char[] F_CHARS = {'\f'};
    private static final char B = 'b';
    private static final char[] B_CHARS = {'\b'};
    private static final char R = 'r';
    private static final char[] R_CHARS = {'\r'};
    private static final char U = 'u';
    private static final char NEGATIVE = '-';
    private static final char POSITIVE = '+';
    private static final char DOT = '.';
    private static final char SLASH = '/';
    private static final char[] SLASH_CHARS = {SLASH};
    private static final char BACKSLASH = '\\';
    private static final char[] BACKSLASH_CHARS = {BACKSLASH};

    private static final String WRONG_CHAR_ERROR = """
            json '%c' expected but found '%c'
            """;
    private static final String COMMA_OR_OBJECT_END_ERROR = """
            ',' or '}' expected but found '%c'
            """;

    private static final String COMMA_OR_LIST_END_ERROR = """
            ',' or ']' expected but found '%c'
            """;

    private static final String STRING_OR_NULL_ERROR = """
            '"' or 'n' expected but found '%c'
            """;

    private static final String STRING_OR_NULL_OR_ARRAY_END_ERROR = """
            '"', ']' or 'n' expected but found '%c'
            """;

    private static final String STRING_OR_OBJECT_END_ERROR = """
            '"' or '}' expected but found '%c'
            """;

    private static final String ARRAY_START_OR_NULL_ERROR = """
            '[' or 'n' expected but found '%c'
            """;

    private static final String OBJECT_START_OR_NULL_ERROR = """
            '{' or 'n' expected but found '%c'
            """;

    private static final String BOOLEAN_OR_NULL_ERROR = """
            true, false or null expected but found '%c'
            """;

    private static final String NUMBER_ERROR = """
            start of number (- or digit) expected but found '%c'
            """;

    private static final String WRONG_ESCAPE_SEQUENCE_ERROR = """
            one of ",\\,/,b,f,n,r,t,u  expected but found '%c'
            """;

    private static final String WRONG_VALUE_START_ERROR = """
            one of ",{,[,t,f,n,- or digit expected but found '%c'
            """;

    private static final String STR_ARRAY_END = "STR_ARRAY_END";
}
