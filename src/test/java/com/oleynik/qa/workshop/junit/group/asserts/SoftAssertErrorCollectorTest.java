package com.oleynik.qa.workshop.junit.group.asserts;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.closeTo;

public class SoftAssertErrorCollectorTest {

    @Rule
    public ErrorCollector collector = new ErrorCollector();

    @Test
    public void assert_all_test() {
        System.out.println("Assert All in JUnit4 test.");
        boolean variable = true;
        collector.checkThat("Multiplication result is wrong", 2*5,
                equalTo(10));
        collector.checkThat(10/5.0, closeTo(1.9, 0.2));
        collector.checkThat(variable, is(true));
        collector.checkThat(!variable, is(false));
    }
}
