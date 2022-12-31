package io.github.gstojsic.bitcoin.proxy.json.generator;

import javax.lang.model.element.Element;
import javax.lang.model.type.TypeMirror;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

public record ParserSourceContext(
        TypeMirror type,
        FieldType fieldType,
        String packageName,
        String jsonObjName,
        String parserName,
        String listParsing,
        FieldType listItemType,
        TypeMirror listType,
        int listDepth,
        String numberParser,
        String fileName,
        Map<TypeMirror, TypeMirror> genericToDeclaredMap,
        List<? extends Element> fields,
        Set<String> imports
) {

    @Override
    public Map<TypeMirror, TypeMirror> genericToDeclaredMap() {
        return Collections.unmodifiableMap(genericToDeclaredMap);
    }

    @Override
    public Set<String> imports() {
        return Collections.unmodifiableSet(imports);
    }
}
