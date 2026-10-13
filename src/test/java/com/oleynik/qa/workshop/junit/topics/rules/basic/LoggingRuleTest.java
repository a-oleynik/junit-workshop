package com.oleynik.qa.workshop.junit.topics.rules.basic;

import com.oleynik.qa.workshop.junit.scenarios.calculator.Calculator;
import org.junit.Rule;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/** Register the rule and observe its message before the test body. */
public class LoggingRuleTest {
    @Rule
    public final LoggingRule loggingRule = new LoggingRule();

    @Test
    public void adds_two_numbers() {
        System.out.println("Running the test body");
        assertThat(new Calculator().add(2, 3)).isEqualTo(5);
    }
}
