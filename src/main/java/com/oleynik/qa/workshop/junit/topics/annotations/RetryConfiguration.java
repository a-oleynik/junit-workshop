package com.oleynik.qa.workshop.junit.topics.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface RetryConfiguration {
    int maxTries() default 3;
}
