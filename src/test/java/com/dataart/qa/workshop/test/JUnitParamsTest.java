package com.dataart.qa.workshop.test;

import com.dataart.qa.workshop.Factorial;
import junitparams.JUnitParamsRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import junitparams.Parameters;

import static org.hamcrest.CoreMatchers.is;

@RunWith(JUnitParamsRunner.class)
public class JUnitParamsTest {
    public static Object[][] numbers() {
        return new Object[][] {
                {1, 1},
                {5, 120},
        };
    }

    @Test
    @Parameters(method = "numbers")
    public void test_factorial_with_junitparams(long number, long expected) {
        Assert.assertEquals("Factorial function is wrong.", expected, Factorial.factorial(number));
    }

    @Test
    @Parameters({
            "2,2,4",//3
            "2,2,4",
            "2,2,4"//5
    })
    public void test_multiplication_with_junitparams(int x, int y, int z) {
        Assert.assertThat(x * y, is(z));
        System.out.println("test2");
    }

    @Test
    public void non_parametrised_test(){
        System.out.println("Something here.");
    }
}
