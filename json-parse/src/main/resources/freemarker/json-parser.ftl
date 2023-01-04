package ${packageName};

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.github.gstojsic.bitcoin.proxy.json.BaseJsonInput;
import io.github.gstojsic.bitcoin.proxy.json.JsonInputStream;
import io.github.gstojsic.bitcoin.proxy.json.JsonInputString;

<#list additionalImports as import>
import ${import};
</#list>

public class ${parserName} {

    public static ${jsonObjName} parse(String input) {
        return parse(new JsonInputString(input));
    }

    public static ${jsonObjName} parse(InputStream input) {
        return parse(new JsonInputStream(input));
    }

    public static ${jsonObjName} parse(BaseJsonInput input) {
        Character c = input.startObject();
        if (c == null) return null;
        if (c == BaseJsonInput.ARRAY_END) return ARRAY_END_MARKER;
        ${jsonObjName} parsed = new ${jsonObjName}();
        char nextSeparator;
        do {
            String key = input.readStringOrObjectEnd();
            if (key == null) break;
            input.readColon();
            switch (key) {
                <#list fields as field>
                <#if field.type == "STRING">
                case "${field.name}" -> {
                    parsed.${field.setter}(input.readStringOrNull());
                    nextSeparator = input.readCommaOrObjectEnd();
                }
                <#elseif field.type == "BOOLEAN">
                case "${field.name}" -> {
                    parsed.${field.setter}(input.readBooleanOrNull());
                    nextSeparator = input.readCommaOrObjectEnd();
                }
                <#elseif field.type == "NUMBER">
                case "${field.name}" -> {
                    Object[] res = input.readNumberOrNull();
                    if (res != null) {
                        parsed.${field.setter}(${field.numberParser}((String) res[0]));
                        nextSeparator = (char) res[1];
                    } else
                        nextSeparator = input.readCommaOrObjectEnd();
                }
                <#elseif field.type == "LIST">
                case "${field.name}" -> {
                    parsed.${field.setter}(<@generateParseList field/>);
                    nextSeparator = input.readCommaOrObjectEnd();
                }
                <#elseif field.type == "MAP">
                case "${field.name}" -> {
                    parsed.${field.setter}(${field.listParsing});
                    nextSeparator = input.readCommaOrObjectEnd();
                }
                <#elseif field.type == "OBJECT">
                case "${field.name}" -> {
                    var o = ${field.parserName}.parse(input);
                    if (${field.parserName}.ARRAY_END_MARKER == o) throw new IllegalStateException("object expected, got ]");
                    parsed.${field.setter}(o);
                    nextSeparator = input.readCommaOrObjectEnd();
                }
                <#elseif field.type == "CUSTOM">
                case "${field.name}" -> {
                    parsed.${field.setter}(${field.parserName}.${field.listParsing}(input));
                    nextSeparator = input.readCommaOrObjectEnd();
                }
                </#if>
                </#list>
                default -> nextSeparator = input.skipValue();
            }
        } while (nextSeparator != BaseJsonInput.OBJECT_END);
        return parsed;
    }

    public static List<${jsonObjName}> parseList(BaseJsonInput input) {
        return BaseJsonInput.parseList(input, ${parserName}::parse, ARRAY_END_MARKER);
    }

    public static Map<String, ${jsonObjName}> parseMap(BaseJsonInput input) {
        return BaseJsonInput.parseMap(input, ${parserName}::parse);
    }

    public static final ${jsonObjName} ARRAY_END_MARKER = new ${jsonObjName}();
}

<#macro generateParseList field><#compress>
    <#if field.listDepth == 1 || field.listDepth == 0>
        <#if field.listItemType == "OBJECT">
        ${field.listParsing}.parseList(input)
        <#else>
        input.${field.listParsing}()
        </#if>
    <#else>
        BaseJsonInput.parseList(input,
        <@generateParseListRest field field.listDepth-1/>,
        null)
    </#if>
</#compress></#macro>

<#macro generateParseListRest field depth><#compress>
    <#if depth == field.listDepth>
    ${field.parserName}::${field.listParsing},
    <#else>
    i${depth} -> BaseJsonInput.parseList(i${depth},
    <@generateParseListRest field depth+1/>
    null)
    </#if>
</#compress></#macro>

