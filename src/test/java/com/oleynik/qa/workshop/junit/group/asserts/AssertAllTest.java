package com.oleynik.qa.workshop.junit.group.asserts;

import com.oleynik.qa.workshop.junit.rules.AssertAll;
import org.junit.Rule;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class AssertAllTest {

    @Test
    public void assert_all_test() {
        System.out.println("Assert All in JUnit4 test.");
        boolean variable = true;
        assertAll(
                () -> assertThat("Multiplication result is wrong", 2 * 5,
                        equalTo(10)),
                () -> assertThat(10 / 5.0, closeTo(1.9, 0.2)),
                () -> assertThat(variable, is(true)),
                () -> assertThat(!variable, is(false))
        );
    }
}