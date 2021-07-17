package com.dataart.qa.workshop.group.asserts;

import org.assertj.core.api.SoftAssertions;
import org.assertj.core.data.Percentage;
import org.junit.Test;

public class SoftAssertTest {

    @Test
    public void soft_assert_test() {
        System.out.println("Soft Assertion test.");
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(2 * 5).as("Multiplication result is wrong").isEqualTo(10);
        softAssertions.assertThat(10 / 5.0).isCloseTo(2.1, Percentage.withPercentage(5));
        boolean isFalse = false;
        softAssertions.assertThat(isFalse).as("The variable is not false.").isFalse();
        softAssertions.assertThat(!isFalse).as("The !variable is not true").isTrue();
        softAssertions.assertAll();
    }
}
