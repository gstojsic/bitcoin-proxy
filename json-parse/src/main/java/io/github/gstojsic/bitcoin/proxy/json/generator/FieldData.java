package io.github.gstojsic.bitcoin.proxy.json.generator;

public final class FieldData {
    private final FieldType type;
    private final String name;
    private final String setter;
    private final String parserName;
    private final String listParsing;
    private final FieldType listItemType;
    private final int listDepth;
    private final String numberParser;

    public FieldData(
            FieldType type,
            String name,
            String setter,
            String parserName,
            String listParsing,
            FieldType listItemType,
            int listDepth,
            String numberParser) {
        this.type = type;
        this.name = name;
        this.setter = setter;
        this.parserName = parserName;
        this.listParsing = listParsing;
        this.listItemType = listItemType;
        this.listDepth = listDepth;
        this.numberParser = numberParser;
    }

    public FieldType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getSetter() {
        return setter;
    }

    public String getParserName() {
        return parserName;
    }

    public String getListParsing() {
        return listParsing;
    }

    public FieldType getListItemType() {
        return listItemType;
    }

    public int getListDepth() {
        return listDepth;
    }

    public String getNumberParser() {
        return numberParser;
    }
}
