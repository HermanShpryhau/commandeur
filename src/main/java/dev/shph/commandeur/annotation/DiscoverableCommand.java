package dev.shph.commandeur.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation marks that implementation of {@link dev.shph.commandeur.Command}
 * must be found and loaded by {@link dev.shph.commandeur.container.AnnotationScanningContainer}
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface DiscoverableCommand {

    /**
     * Name of command.
     */
    public String value();

}
