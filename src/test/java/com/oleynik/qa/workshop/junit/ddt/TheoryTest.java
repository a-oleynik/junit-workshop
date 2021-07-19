package com.oleynik.qa.workshop.junit.ddt;

import com.oleynik.qa.workshop.junit.model.MyDoubleWrapper;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.FromDataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(Theories.class)
public class TheoryTest {
    @DataPoints("a values")
    public static double[] firstNumbers() {
        return new double[]{1, 2.03, 1234567};
    }

    @DataPoints("b values")
    public static double[] secondNumbers() {
        return new double[]{1, 5.55, -10000, 1234567};
    }

    @Theory
    public void theory_my_double_wrapper_multiplication_test(@FromDataPoints("a values") double a,
                                                             @FromDataPoints("b values") double b) {
        System.out.printf("Multiplying %s on %s%n", a, b);
        assertEquals(a * b, new MyDoubleWrapper(a).multiply(b), 0);
    }

    @Theory
    public void theory_my_double_wrapper_division_test(@FromDataPoints("a values") double a,
                                                       @FromDataPoints("b values") double b) {
        System.out.printf("Dividing %s on %s%n", a, b);
        assertEquals(a / b, new MyDoubleWrapper(a).divide(b), 0);
    }

    @Theory
    public void theory_my_double_wrapper_addition_test(@FromDataPoints("a values") double a,
                                                       @FromDataPoints("b values") double b) {
        System.out.printf("Adding %s and %s%n", a, b);
        assertEquals(a + b, new MyDoubleWrapper(a).add(b), 0);
    }

    @Theory
    public void theory_my_double_wrapper_subtraction_test(@FromDataPoints("a values") double a,
                                                          @FromDataPoints("b values") double b) {
        System.out.printf("Subtracting %s from %s%n", b, a);
        assertEquals(a - b, new MyDoubleWrapper(a).subtract(b), 0);
    }
}
