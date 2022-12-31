package io.github.gstojsic.bitcoin.proxy.json.processor;

import io.github.gstojsic.bitcoin.proxy.json.annotation.JsonParserFactory;
import io.github.gstojsic.bitcoin.proxy.json.generator.JsonParserFactoryGenerator;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.util.Set;

import static io.github.gstojsic.bitcoin.proxy.json.tool.Tools.debugLog;

public class JsonParserFactoryProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnvironment) {
        for (Element annotatedElement : roundEnvironment.getElementsAnnotatedWith(JsonParserFactory.class)) {
            debugLog(processingEnv.getMessager(), "start");
            // Check if a class has been annotated with @JsonParserFactory
            if (annotatedElement.getKind() != ElementKind.INTERFACE) {
                error(annotatedElement,
                        "Only interfaces can be annotated with @%s",
                        JsonParserFactory.class.getSimpleName());
                return true; // Exit processing
            }

            try {
                new JsonParserFactoryGenerator(processingEnv).generateParsers(annotatedElement);
            } catch (Exception e) {
                e.printStackTrace(System.err);
                error(annotatedElement, "error while processing %s, msg:%s", annotatedElement, e.getMessage());
                throw new RuntimeException(e);
            }
            debugLog(processingEnv.getMessager(), "done");
        }
        return false;
    }


    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return Set.of(
                JsonParserFactory.class.getCanonicalName()
        );
    }

    private void error(Element e, String msg, Object... args) {
        processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, String.format(msg, args), e);
    }
}