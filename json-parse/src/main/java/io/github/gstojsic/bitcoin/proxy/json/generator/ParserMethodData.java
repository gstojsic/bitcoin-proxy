package io.github.gstojsic.bitcoin.proxy.json.generator;

import java.util.Set;

public class ParserMethodData {
    private String name;
    private String returnType;
    private String parameterType;
    private String parserClass;
    private Set<String> imports;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public String getParameterType() {
        return parameterType;
    }

    public void setParameterType(String parameterType) {
        this.parameterType = parameterType;
    }

    public String getParserClass() {
        return parserClass;
    }

    public void setParserClass(String parserClass) {
        this.parserClass = parserClass;
    }

    public Set<String> getImports() {
        return imports;
    }

    public void setImports(Set<String> imports) {
        this.imports = imports;
    }
}
