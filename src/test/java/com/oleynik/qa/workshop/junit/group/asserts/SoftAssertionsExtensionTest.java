package com.oleynik.qa.workshop.junit.group.asserts;

import org.assertj.core.api.BDDSoftAssertions;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.InjectSoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(SoftAssertionsExtension.class)
public class SoftAssertionsExtensionTest {

    @InjectSoftAssertions
    BDDSoftAssertions bdd;

    @Test
    public void soft_assertions_extension_test(SoftAssertions softly) {
        System.out.println("Soft Assertions Extension test.");
        softly.assertThat(2 * 5).as("Multiplication result is wrong").isEqualTo(10);
        softly.assertThat(10 / 5.0).isCloseTo(2.1, Percentage.withPercentage(5));
        boolean isFalse = false;
        softly.assertThat(isFalse).as("The variable is not false.").isFalse();
        softly.assertThat(!isFalse).as("The !variable is not true").isTrue();
    }

    @Test
    public void soft_assertions_extension_another_test(SoftAssertions softly) {
        System.out.println("Soft Assertions Extension test.");
        bdd.then(2*5).as("Multiplication result is wrong").isEqualTo(10);
        bdd.then(10/5.0).as("Division 10/6 gives wrong result").isCloseTo(2.1, Percentage.withPercentage(5));
        boolean isFalse = false;
        bdd.then(isFalse).as("The variable is not false.").isFalse();
        bdd.then(!isFalse).as("The !variable is not true").isTrue();
    }
}
