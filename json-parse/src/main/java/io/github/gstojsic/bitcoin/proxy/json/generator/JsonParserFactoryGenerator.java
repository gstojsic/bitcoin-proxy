package io.github.gstojsic.bitcoin.proxy.json.generator;

import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;
import io.github.gstojsic.bitcoin.proxy.json.annotation.CustomParser;
import io.github.gstojsic.bitcoin.proxy.json.annotation.JsonProperty;
import io.github.gstojsic.bitcoin.proxy.json.tool.Tools;

import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.TypeParameterElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.tools.JavaFileObject;
import javax.tools.StandardLocation;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import static io.github.gstojsic.bitcoin.proxy.json.tool.Tools.debugLog;
import static io.github.gstojsic.bitcoin.proxy.json.tool.Tools.errorLog;
import static java.util.Collections.emptyList;
import static java.util.Collections.emptyMap;
import static javax.lang.model.type.TypeKind.*;

public class JsonParserFactoryGenerator {

    private final ProcessingEnvironment processingEnv;
    private final Messager messager;
    private final Filer filer;
    private final TypeMirror STRING_TYPE;
    private final TypeMirror BOOLEAN_TYPE;
    private final TypeMirror INTEGER_TYPE;
    private final TypeMirror LONG_TYPE;
    private final TypeMirror DOUBLE_TYPE;
    private final TypeMirror BYTE_TYPE;
    private final TypeMirror SHORT_TYPE;
    private final TypeMirror FLOAT_TYPE;
    private final TypeMirror LIST_TYPE;
    private final TypeMirror MAP_TYPE;
    //private final TypeMirror COLLECTION_TYPE;
    private final TypeMirror INPUT_STREAM_TYPE;

    public JsonParserFactoryGenerator(ProcessingEnvironment processingEnv) {
        this.processingEnv = processingEnv;
        this.messager = processingEnv.getMessager();
        this.filer = processingEnv.getFiler();
        STRING_TYPE = getTypeElement(String.class.getCanonicalName()).asType();
        BOOLEAN_TYPE = getTypeElement(Boolean.class.getCanonicalName()).asType();
        INTEGER_TYPE = getTypeElement(Integer.class.getCanonicalName()).asType();
        LONG_TYPE = getTypeElement(Long.class.getCanonicalName()).asType();
        DOUBLE_TYPE = getTypeElement(Double.class.getCanonicalName()).asType();
        BYTE_TYPE = getTypeElement(Byte.class.getCanonicalName()).asType();
        SHORT_TYPE = getTypeElement(Short.class.getCanonicalName()).asType();
        FLOAT_TYPE = getTypeElement(Float.class.getCanonicalName()).asType();
        LIST_TYPE = getTypeElement(List.class.getCanonicalName()).asType();
        MAP_TYPE = getTypeElement(Map.class.getCanonicalName()).asType();
        //COLLECTION_TYPE = getTypeElement(Collection.class.getCanonicalName()).asType();
        INPUT_STREAM_TYPE = getTypeElement(InputStream.class.getCanonicalName()).asType();
    }

    public void generateParsers(Element parsers) throws Exception {
        PackageElement parsersPackage = (PackageElement) parsers.getEnclosingElement();

        String parsersInterface = parsers.getSimpleName().toString();
        String packageName = parsersPackage.getQualifiedName().toString();
        String parserFactoryName = parsersInterface + BUILDER_SUFFIX;

        if (exists(packageName, parserFactoryName)) return;

        final Set<String> additionalImports = new HashSet<>();
        Map<String, Object> templateData = new HashMap<>();
        templateData.put("packageName", packageName);
        templateData.put("parserFactoryName", parserFactoryName);
        templateData.put("parsersInterface", parsersInterface);
        templateData.put("additionalImports", additionalImports);

        List<ParserMethodData> methods = new ArrayList<>();
        for (Element enclosedElement : parsers.getEnclosedElements()) {
            if (ElementKind.METHOD.equals(enclosedElement.getKind())) {
                ExecutableElement method = (ExecutableElement) enclosedElement;
                ParserMethodData parserMethodData = processMethod(method);
                methods.add(parserMethodData);

                // additional imports
                if (!parserMethodData.getImports().isEmpty())
                    additionalImports.addAll(parserMethodData.getImports());
            }
        }
        templateData.put("methods", methods);
        write(packageName + "." + parserFactoryName, "json-parser-factory.ftl", templateData);
    }

    private ParserMethodData processMethod(ExecutableElement method) throws Exception {
        ParserMethodData parserMethodData = new ParserMethodData();
        parserMethodData.setName(method.getSimpleName().toString());

        var ctx = createCtx(method.getReturnType(), emptyMap());
        generateParser(ctx);
        parserMethodData.setReturnType(ctx.jsonObjName());
        parserMethodData.setImports(ctx.imports());

        //method parameters
        List<? extends VariableElement> methodParams = method.getParameters();
        if (methodParams.size() < 1) {
            errorLog(messager, "no input parameter");
        }

        if (methodParams.size() > 1) {
            errorLog(messager, "too many input parameters");
        }

        VariableElement param = methodParams.get(0);
        if (isSameType(param.asType(), INPUT_STREAM_TYPE)) {
            parserMethodData.setParameterType(InputStream.class.getSimpleName());
        } else if (isSameType(param.asType(), STRING_TYPE)) {
            parserMethodData.setParameterType(String.class.getSimpleName());
        } else {
            errorLog(messager, "Invalid InputType:%s".formatted(param.asType().toString()));
        }
        parserMethodData.setParserClass(ctx.parserName());
        return parserMethodData;
    }

    private void generateParser(ParserSourceContext context) throws Exception {
        if (exists(context.packageName(), context.parserName())) return;

        final Set<String> additionalImports = new HashSet<>();
        Map<String, Object> templateData = new HashMap<>();
        templateData.put("packageName", context.packageName());
        templateData.put("parserName", context.parserName());
        templateData.put("jsonObjName", context.jsonObjName());
        templateData.put("additionalImports", additionalImports);

        List<FieldData> fields = new ArrayList<>();
        for (Element enclosedElement : context.fields()) {
            String name = enclosedElement.getSimpleName().toString();
            if (enclosedElement.getKind().isField()
                    && !"DEFAULT_VALUE".equals(name)
                    && !enclosedElement.getModifiers().contains(Modifier.TRANSIENT)) {
                debugLog(messager, "processing field %s", name);
                ParserSourceContext ctx = createCtx(enclosedElement.asType(), context.genericToDeclaredMap());

                if (!ctx.imports().isEmpty())
                    additionalImports.addAll(ctx.imports());

                if (ctx.fieldType() == FieldType.OBJECT) {
                    generateParser(ctx);
                } else if (ctx.fieldType() == FieldType.LIST || ctx.fieldType() == FieldType.MAP) {
                    if (ctx.listType() != null) {
                        ParserSourceContext listArgCtx = createCtx(ctx.listType(), emptyMap());
                        generateParser(listArgCtx);
                    }
                }

                var propertyAnnotation = enclosedElement.getAnnotation(JsonProperty.class);
                String fieldName = propertyAnnotation != null ? propertyAnnotation.value() : name;
                FieldData data = new FieldData(
                        ctx.fieldType(),
                        fieldName,
                        "set" + Tools.firstLetterToUpper(name), //setter
                        ctx.parserName(),
                        ctx.listParsing(),
                        ctx.listItemType(),
                        ctx.listDepth(),
                        ctx.numberParser());
                fields.add(data);
            }
        }

        templateData.put("fields", fields);
        write(context.fileName(), "json-parser.ftl", templateData);
    }

    private ParserSourceContext createCtx(TypeMirror type, Map<TypeMirror, TypeMirror> parentGenericToDeclaredMap) {
        TypeMirror resolvedType = resolveType(type, parentGenericToDeclaredMap);
        TypeKind kind = resolvedType.getKind();
        // Basic types
        if (kind.isPrimitive()) {
            if (kind == BOOLEAN) {
                return createPrimitiveCtx(resolvedType, FieldType.BOOLEAN);
            } else if (kind == DOUBLE) {
                return createPrimitiveCtx(resolvedType, FieldType.NUMBER, resolveNumberParser(DOUBLE));
            } else if (kind == BYTE) {
                return createPrimitiveCtx(resolvedType, FieldType.NUMBER, resolveNumberParser(BYTE));
            } else if (kind == FLOAT) {
                return createPrimitiveCtx(resolvedType, FieldType.NUMBER, resolveNumberParser(FLOAT));
            } else if (kind == INT) {
                return createPrimitiveCtx(resolvedType, FieldType.NUMBER, resolveNumberParser(INT));
            } else if (kind == LONG) {
                return createPrimitiveCtx(resolvedType, FieldType.NUMBER, resolveNumberParser(LONG));
            } else if (kind == SHORT) {
                return createPrimitiveCtx(resolvedType, FieldType.NUMBER, resolveNumberParser(SHORT));
            } else if (kind == CHAR) {
                return createPrimitiveCtx(resolvedType, FieldType.STRING);
            } else
                throw new IllegalArgumentException("invalid kind:%s".formatted(kind));

        } else if (isSameType(resolvedType, BOOLEAN_TYPE)) {
            return createPrimitiveCtx(resolvedType, FieldType.BOOLEAN);
        } else if (isSameType(resolvedType, STRING_TYPE)) {
            return createPrimitiveCtx(resolvedType, FieldType.STRING);
        } else if (isSameType(resolvedType, DOUBLE_TYPE)) {
            return createPrimitiveCtx(resolvedType, FieldType.NUMBER, resolveNumberParser(DOUBLE));
        } else if (isSameType(resolvedType, BYTE_TYPE)) {
            return createPrimitiveCtx(resolvedType, FieldType.NUMBER, resolveNumberParser(BYTE));
        } else if (isSameType(resolvedType, INTEGER_TYPE)) {
            return createPrimitiveCtx(resolvedType, FieldType.NUMBER, resolveNumberParser(INT));
        } else if (isSameType(resolvedType, LONG_TYPE)) {
            return createPrimitiveCtx(resolvedType, FieldType.NUMBER, resolveNumberParser(LONG));
        } else if (isSameType(resolvedType, SHORT_TYPE)) {
            return createPrimitiveCtx(resolvedType, FieldType.NUMBER, resolveNumberParser(SHORT));
        } else if (isSameType(resolvedType, FLOAT_TYPE)) {
            return createPrimitiveCtx(resolvedType, FieldType.NUMBER, resolveNumberParser(FLOAT));
        }

        final TypeElement parsedObj = getTypeElement(resolvedType);
        final PackageElement packageElement = (PackageElement) parsedObj.getEnclosingElement();
        final String packageName = packageElement.getQualifiedName().toString();

        final Map<TypeMirror, TypeMirror> genericToDeclaredMap = new HashMap<>();
        final List<? extends TypeParameterElement> genericTypes = parsedObj.getTypeParameters(); // get real types (e.g. String)
        if (genericTypes.isEmpty()) {
            //raw non generic object
            String jsonObjName = parsedObj.getSimpleName().toString();

            for (Element enclosedElement : parsedObj.getEnclosedElements()) {
                if (ElementKind.METHOD.equals(enclosedElement.getKind())) {
                    var customParser = enclosedElement.getAnnotation(CustomParser.class);
                    if (customParser == null) continue;
                    var method = (ExecutableElement) enclosedElement;
                    return new ParserSourceContext(
                            resolvedType,
                            FieldType.CUSTOM,
                            packageName,
                            null,
                            jsonObjName,
                            method.getSimpleName().toString(),
                            null,
                            null,
                            0,
                            null,
                            null,
                            emptyMap(),
                            parsedObj.getEnclosedElements(),
                            Set.of(parsedObj.toString())
                    );
                }
            }
            String parserName = jsonObjName + PARSER_SUFFIX;
            return new ParserSourceContext(
                    resolvedType,
                    FieldType.OBJECT,
                    packageName,
                    jsonObjName,
                    parserName,
                    null,
                    null,
                    null,
                    0,
                    null,
                    packageName + "." + parserName,
                    emptyMap(),
                    parsedObj.getEnclosedElements(),
                    Set.of(parsedObj.toString(), packageName + "." + parserName)
            );
        } else {
            //generic object or list
            final DeclaredType declaredParseObj = (DeclaredType) resolvedType;

            final var declaredTypes = declaredParseObj.getTypeArguments(); // get T types
            if (genericTypes.size() != declaredTypes.size())
                errorLog(messager, "not matching number of generic types. g:%s, d:%s".formatted(genericTypes.size(), declaredTypes.size()));

            for (int i = 0; i < genericTypes.size(); i++) {
                TypeParameterElement generic = genericTypes.get(i);
                TypeMirror declared = declaredTypes.get(i);
                genericToDeclaredMap.put(generic.asType(), declared);
            }

            if (isList(resolvedType)) {
                var typeArguments = declaredParseObj.getTypeArguments();
                var listArg = typeArguments.get(0);
                //handle list within list
                int depth = 1;
                while (isList(listArg)) {
                    typeArguments = ((DeclaredType) listArg).getTypeArguments();
                    listArg = typeArguments.get(0);
                    depth++;
                }

                var c = createCtx(listArg, emptyMap());
                if (c.fieldType() == FieldType.OBJECT) {
                    var imports = getAdditionalImports(declaredParseObj);
                    if (!c.packageName().startsWith("java.lang"))
                        imports.add(c.packageName() + "." + c.parserName());

                    return new ParserSourceContext(
                            resolvedType,
                            FieldType.LIST,
                            packageName,
                            c.jsonObjName(),
                            null,
                            c.parserName(),
                            c.fieldType(),
                            listArg,
                            depth,
                            null,
                            packageName + "." + c.parserName(),
                            genericToDeclaredMap,
                            parsedObj.getEnclosedElements(),
                            imports
                    );
                } else if (c.fieldType() == FieldType.CUSTOM) {
                    var imports = getAdditionalImports(declaredParseObj);
                    if (!c.packageName().startsWith("java.lang"))
                        imports.add(c.packageName() + "." + c.parserName());

                    return new ParserSourceContext(
                            resolvedType,
                            FieldType.LIST,
                            null,
                            null,
                            c.parserName(),
                            c.listParsing(),
                            c.fieldType(),
                            null,
                            depth,
                            null,
                            null,
                            emptyMap(),
                            emptyList(),
                            imports
                    );
                } else if (c.fieldType() == FieldType.NUMBER) {
                    String listParsing = resolveListNumberParser(c.type());
                    return new ParserSourceContext(
                            resolvedType,
                            FieldType.LIST,
                            null,
                            null,
                            "BaseJsonInput",
                            listParsing,
                            c.fieldType(),
                            null,
                            depth,
                            null,
                            null,
                            emptyMap(),
                            emptyList(),
                            Set.of()
                    );
                } else if (c.fieldType() == FieldType.STRING) {
                    return new ParserSourceContext(
                            resolvedType,
                            FieldType.LIST,
                            null,
                            null,
                            "BaseJsonInput",
                            "parseStringList",
                            c.fieldType(),
                            null,
                            depth,
                            null,
                            null,
                            emptyMap(),
                            emptyList(),
                            Set.of()
                    );
                } else if (c.fieldType() == FieldType.BOOLEAN) {
                    return new ParserSourceContext(
                            resolvedType,
                            FieldType.LIST,
                            null,
                            null,
                            "BaseJsonInput",
                            "parseBooleanList",
                            c.fieldType(),
                            null,
                            depth,
                            null,
                            null,
                            emptyMap(),
                            emptyList(),
                            Set.of()
                    );
                } else {
                    throw new RuntimeException("invalid list filed type:%s".formatted(c.fieldType()));
                }
            } else if (isMap(resolvedType)) {
                debugLog(messager, "map type detected");
                var typeArguments = declaredParseObj.getTypeArguments();
                if (!isSameType(typeArguments.get(0), STRING_TYPE))
                    throw new RuntimeException("Illegal type arg in map, should be string, is:%s".formatted(typeArguments.get(0)));
                var mapArg = typeArguments.get(1);
                var c = createCtx(mapArg, emptyMap());
                if (c.fieldType() == FieldType.OBJECT) {
                    String listParsing = c.parserName() + ".parseMap(input)";
                    var imports = getAdditionalImports(declaredParseObj);
                    if (!c.packageName().startsWith("java.lang"))
                        imports.add(c.packageName() + "." + c.parserName());
                    return new ParserSourceContext(
                            resolvedType,
                            FieldType.MAP,
                            null,
                            c.jsonObjName(),
                            null,
                            listParsing,
                            null,
                            mapArg,
                            0,
                            null,
                            null,
                            emptyMap(),
                            emptyList(),
                            imports
                    );
                } else if (c.fieldType() == FieldType.STRING) {
                    return new ParserSourceContext(
                            resolvedType,
                            FieldType.MAP,
                            null,
                            null,
                            null,
                            "input.parseMapString()",
                            null,
                            null,
                            0,
                            null,
                            null,
                            emptyMap(),
                            emptyList(),
                            Set.of()
                    );
                } else if (c.fieldType() == FieldType.BOOLEAN) {
                    return new ParserSourceContext(
                            resolvedType,
                            FieldType.MAP,
                            null,
                            null,
                            null,
                            "input.parseMapBoolean()",
                            null,
                            null,
                            0,
                            null,
                            null,
                            emptyMap(),
                            emptyList(),
                            Set.of()
                    );
                } else if (c.fieldType() == FieldType.NUMBER) {
                    String mapParsing = resolveMapNumberParser(c.type());
                    return new ParserSourceContext(
                            resolvedType,
                            FieldType.LIST,
                            null,
                            null,
                            null,
                            mapParsing,
                            c.fieldType(),
                            null,
                            0,
                            null,
                            null,
                            emptyMap(),
                            emptyList(),
                            Set.of()
                    );
                } else {
                    throw new RuntimeException("invalid list filed type:%s".formatted(c.fieldType()));
                }
            } else {
                String jsonObjName = getJsonObjName(declaredParseObj);
                String parserName = jsonObjName
                        .replace(',', '$')
                        .replace('<', '_')
                        .replace('>', '_') + PARSER_SUFFIX;
                var imports = getAdditionalImports(declaredParseObj);
                return new ParserSourceContext(
                        resolvedType,
                        FieldType.OBJECT,
                        packageName,
                        jsonObjName,
                        parserName,
                        null,
                        null,
                        null,
                        0,
                        null,
                        packageName + "." + parserName,
                        genericToDeclaredMap,
                        parsedObj.getEnclosedElements(),
                        imports
                );
            }
        }
    }

    private ParserSourceContext createPrimitiveCtx(TypeMirror type, FieldType fieldType) {
        return createPrimitiveCtx(type, fieldType, null);
    }

    private ParserSourceContext createPrimitiveCtx(TypeMirror type, FieldType fieldType, String numberParser) {
        return new ParserSourceContext(
                type,
                fieldType,
                null,
                null,
                null,
                null,
                null,
                null,
                0,
                numberParser,
                null,
                emptyMap(),
                emptyList(),
                Set.of()
        );
    }

    private TypeMirror resolveType(TypeMirror type, Map<TypeMirror, TypeMirror> parentGenericToDeclaredMap) {
        return type.getKind() == TYPEVAR
                ? parentGenericToDeclaredMap.get(type)
                : type;
    }

    private String resolveNumberParser(TypeKind kind) {
        return switch (kind) {
            case INT -> "Integer.parseInt";
            case LONG -> "Long.parseLong";
            case DOUBLE -> "Double.parseDouble";
            case FLOAT -> "Float.parseFloat";
            case SHORT -> "Short.parseShort";
            case BYTE -> "Byte.parseByte";
            default -> throw new IllegalArgumentException("wrong kind:%s".formatted(kind));
        };
    }

    private String resolveListNumberParser(TypeMirror type) {
        if (isSameType(type, DOUBLE_TYPE)) {
            return "parseDoubleList";
        } else if (isSameType(type, BYTE_TYPE)) {
            return "parseByteList";
        } else if (isSameType(type, INTEGER_TYPE)) {
            return "parseIntegerList";
        } else if (isSameType(type, LONG_TYPE)) {
            return "parseLongList";
        } else if (isSameType(type, SHORT_TYPE)) {
            return "parseShortList";
        } else if (isSameType(type, FLOAT_TYPE)) {
            return "parseFloatList";
        } else {
            throw new IllegalArgumentException("illegal list number type:%s".formatted(type));
        }
    }

    private String resolveMapNumberParser(TypeMirror type) {
        if (isSameType(type, DOUBLE_TYPE)) {
            return "parseDoubleMap";
        } else if (isSameType(type, BYTE_TYPE)) {
            return "parseByteMap";
        } else if (isSameType(type, INTEGER_TYPE)) {
            return "parseIntegerMap";
        } else if (isSameType(type, LONG_TYPE)) {
            return "parseLongMap";
        } else if (isSameType(type, SHORT_TYPE)) {
            return "parseShortMap";
        } else if (isSameType(type, FLOAT_TYPE)) {
            return "parseFloatMap";
        } else {
            throw new IllegalArgumentException("illegal map number type:%s".formatted(type));
        }
    }

    private String getJsonObjName(DeclaredType declared) {
        var typeParameters = new ArrayList<String>();
        if (!declared.getTypeArguments().isEmpty()) {
            for (TypeMirror t : declared.getTypeArguments()) {
                typeParameters.add(getJsonObjName((DeclaredType) t));
            }
        }
        TypeElement paramElement = getTypeElement(declared);
        return typeParameters.isEmpty()
                ? paramElement.getSimpleName().toString()
                : "%s<%s>".formatted(paramElement.getSimpleName(), String.join(",", typeParameters));
    }

    private Set<String> getAdditionalImports(DeclaredType declared) {
        var imports = new HashSet<String>();
        if (!declared.getTypeArguments().isEmpty()) {
            for (TypeMirror t : declared.getTypeArguments()) {
                imports.addAll(getAdditionalImports((DeclaredType) t));
            }
        }

        TypeElement paramElement = getTypeElement(declared);
        var i = paramElement.toString();
        if (!i.startsWith("java.lang"))
            imports.add(i);
        return imports;
    }

    private TypeElement getTypeElement(CharSequence name) {
        return processingEnv.getElementUtils().getTypeElement(name);
    }

    private TypeElement getTypeElement(TypeMirror t) {
        return (TypeElement) processingEnv.getTypeUtils().asElement(t);
    }

    private void write(String filename, String templatePath, Map<String, Object> templateData) throws Exception {
        final JavaFileObject jfo = filer.createSourceFile(filename);
        //final FileObject d = filer.getResource(StandardLocation.SOURCE_OUTPUT, "", filename+"sfd");
        //Tools.debugLog(messager, "exists:%b".formatted(Files.exists(Paths.get(jfo.getName()))));
        try (PrintWriter writer = new PrintWriter(jfo.openWriter())) {
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_31);
            cfg.setClassForTemplateLoading(getClass(), "/freemarker");
            cfg.setDefaultEncoding("UTF-8");
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            cfg.setLogTemplateExceptions(false);
            cfg.setLocale(Locale.ROOT);

            freemarker.template.Template temp = cfg.getTemplate(templatePath);
            temp.process(templateData, writer);
        }
        //Tools.debugLog(messager, "exists:%b".formatted(Files.exists(Paths.get(jfo.getName()))));
    }

    private boolean exists(String packageName, String className) {
        try {
            filer.getResource(StandardLocation.SOURCE_OUTPUT, packageName, className + ".java");
            return false;
        } catch (Exception e) {
            return true;
        }
    }

    /**
     * maybe this is not the best way
     *
     * @return true if the field is a list
     */
    private boolean isList(TypeMirror type) {
        return (processingEnv.getTypeUtils().isSameType(
                processingEnv.getTypeUtils().erasure(type),
                processingEnv.getTypeUtils().erasure(LIST_TYPE)
        ));
    }

    private boolean isMap(TypeMirror type) {
        return (processingEnv.getTypeUtils().isSameType(
                processingEnv.getTypeUtils().erasure(type),
                processingEnv.getTypeUtils().erasure(MAP_TYPE)
        ));
    }

    private boolean isSameType(TypeMirror t1, TypeMirror t2) {
        return processingEnv.getTypeUtils().isSameType(t1, t2);
    }

    private static final String BUILDER_SUFFIX = "Builder";
    private static final String PARSER_SUFFIX = "JsonParser";
}
