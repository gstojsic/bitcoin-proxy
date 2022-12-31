package io.github.gstojsic.bitcoin.proxy.json.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotate an interface containing methods defining deserializers
 * use methods in format:<p>
 * <blockquote><pre> {type} parse(InputStream in); </pre></blockquote><p>
 * or
 * <blockquote><pre> {type} parse(String in); </pre></blockquote><p>
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface JsonParserFactory {
}
