package com.oleynik.qa.workshop.junit.ddt;

import com.oleynik.qa.workshop.junit.Factorial;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.*;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class BeforeAfterParameterizationTest {
    @Parameter
    public long number;
    @Parameter(value = 1)
    public long expected;

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{{1, 1}, {2, 2}, {3, 6}, {4, 24}, {5, 120}});
    }

    @BeforeParam
    public static void beforeTestsWithParameters(int number, int expected) {
        System.out.printf("Testing factorial of %s and expecting %s. Test is starting.%n", number, expected);
    }

    @Test
    public void junit4_ddt_before_after_factorial_test() {
        System.out.printf("Testing factorial of %s and expecting %s. Asserting the result.%n", number, expected);
        Assert.assertEquals("factorial function works wrong", expected, Factorial.factorial(number));
    }

    @AfterParam
    public static void afterTestsWithParameters(int number, int expected) {
        System.out.printf("Testing factorial of %s and expecting %s. Test is finished.%n", number, expected);
    }
}
