package ${packageName};

import java.io.InputStream;
import io.github.gstojsic.bitcoin.proxy.json.JsonInputStream;

<#list additionalImports as import>
import ${import};
</#list>

public class ${parserFactoryName} {
    public static final GeneratedParsers parsers = new GeneratedParsers();

    public static ${parsersInterface} get() {
        return parsers;
    }

    private static class GeneratedParsers implements ${parsersInterface} {
        <#list methods as method>
        @Override
        public ${method.returnType} ${method.name}(${method.parameterType} input) {
            return ${method.parserClass}.parse(input);
        }
        </#list>
    }
}