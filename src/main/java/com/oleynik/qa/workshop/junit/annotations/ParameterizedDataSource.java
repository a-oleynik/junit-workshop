package com.oleynik.qa.workshop.junit.annotations;

import com.oleynik.qa.workshop.junit.dataproviders.OneMoreFactorialDataProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@ArgumentsSource(OneMoreFactorialDataProvider.class)
public @interface ParameterizedDataSource {
    String path();
}
