package com.oleynik.qa.workshop.junit.topics.extensions.basic;

import com.oleynik.qa.workshop.junit.scenarios.calculator.Calculator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;

/** Register the extension and observe its message before the test body. */
@ExtendWith(LoggingExtension.class)
public class LoggingExtensionTest {
    @Test
    void adds_two_numbers() {
        System.out.println("Running the test body");
        assertThat(new Calculator().add(2, 3)).isEqualTo(5);
    }
}
