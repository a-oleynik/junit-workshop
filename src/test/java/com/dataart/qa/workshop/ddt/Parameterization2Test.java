package com.dataart.qa.workshop.ddt;

import com.dataart.qa.workshop.Factorial;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class Parameterization2Test {
    @Parameter
    public long number;
    @Parameter(value = 1)
    public long expected;

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{{1, 1}, {2, 2}, {3, 6}, {4, 24}, {5, 120}});
    }

    @Test
    public void test_factorial() {
        Assert.assertEquals("factorial function works wrong", expected, Factorial.factorial(number));
    }
}
