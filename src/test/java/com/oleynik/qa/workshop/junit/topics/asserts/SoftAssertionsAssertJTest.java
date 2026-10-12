package com.oleynik.qa.workshop.junit.topics.asserts;

import org.assertj.core.api.JUnitSoftAssertions;
import org.assertj.core.data.Percentage;
import org.junit.Rule;
import org.junit.Test;

public class SoftAssertionsAssertJTest {

    // The rule reports collected failures after each test; no manual assertAll() is needed.
    @Rule
    public final JUnitSoftAssertions softly = new JUnitSoftAssertions();

    @Test
    public void soft_assertions_rule_test() {
        softly.assertThat(2 * 5).as("Multiplication result").isEqualTo(10);
        softly.assertThat(10 / 5.0).as("Division result").isCloseTo(2.1, Percentage.withPercentage(5));
        boolean isFalse = false;
        softly.assertThat(isFalse).as("The variable").isFalse();
        softly.assertThat(!isFalse).as("The negated variable").isTrue();
    }
}
