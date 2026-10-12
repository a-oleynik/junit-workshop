package com.oleynik.qa.workshop.junit.topics.asserts;

import org.assertj.core.api.JUnitBDDSoftAssertions;
import org.assertj.core.data.Percentage;
import org.junit.Rule;
import org.junit.Test;

public class SoftAssertionsAssertJBDDTest {

    // The rule collects failures from then(...) assertions and reports them after each test.
    @Rule
    public final JUnitBDDSoftAssertions bdd = new JUnitBDDSoftAssertions();

    @Test
    public void soft_assertions_rule_bdd_test() {
        bdd.then(2 * 5).as("Multiplication result").isEqualTo(10);
        bdd.then(10 / 5.0).as("Division result").isCloseTo(2.1, Percentage.withPercentage(5));
        boolean isFalse = false;
        bdd.then(isFalse).as("The variable").isFalse();
        bdd.then(!isFalse).as("The negated variable").isTrue();
    }
}
