package com.oleynik.qa.workshop.junit.grouping.tags;

import org.junit.jupiter.api.Tag;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.oleynik.qa.workshop.junit.grouping.tags.Tags.REGRESSION;

@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Tag(REGRESSION)
public @interface Regression {}
