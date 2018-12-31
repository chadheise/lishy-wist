package io.chadheise.lishyWist.guice.annotations;

import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.google.inject.BindingAnnotation;

@Target({ METHOD, CONSTRUCTOR, FIELD, PARAMETER })
@Retention(RUNTIME)
@Documented
@BindingAnnotation
public @interface ItemsTable {
}
