package io.github.gstojsic.bitcoin.proxy.json.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.CLASS) //TODO try with RetentionPolicy.SOURCE
public @interface CustomParser {
}
