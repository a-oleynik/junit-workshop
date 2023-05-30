package com.oleynik.qa.workshop.junit.group.asserts;

import org.assertj.core.api.BDDSoftAssertions;
import org.assertj.core.api.junit.jupiter.InjectSoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(SoftAssertionsExtension.class)
public class SoftAssertionsAssertJBDDTest {

    @InjectSoftAssertions
    BDDSoftAssertions bdd;

    @Test
    void soft_assertions_extension_bdd_test() {
        System.out.println("Soft Assertions Extension test");
        bdd.then(2*5).as("Multiplication result is wrong").isEqualTo(10);
        bdd.then(10/5.0).as("Division 10/6 gives wrong result").isCloseTo(2.1, Percentage.withPercentage(5));
        boolean isFalse = false;
        bdd.then(isFalse).as("The variable is not false").isFalse();
        bdd.then(!isFalse).as("The !variable is not true").isTrue();
    }
}
