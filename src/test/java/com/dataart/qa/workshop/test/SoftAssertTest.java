package com.dataart.qa.workshop.test;

import org.assertj.core.api.SoftAssertions;
import org.assertj.core.data.Percentage;
import org.junit.Test;

public class SoftAssertTest {

    @Test
    public void soft_assert_test(){
        System.out.println("Soft Assertion test.");
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(2*5).as("Multiplication result is wrong").isEqualTo(11);
        softAssertions.assertThat(10/5.0).isCloseTo(2.1, Percentage.withPercentage(0));
        boolean variable = true;
        softAssertions.assertThat(variable).as("The variable is not false.").isFalse();
        softAssertions.assertThat(!variable).as("The !variable is not true").isTrue();
        softAssertions.assertAll();
    }
}
