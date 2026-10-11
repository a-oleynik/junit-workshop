package com.oleynik.qa.workshop.junit.scenarios.calculator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class CalculatorParameterizedTest {

    private final int firstNumber;
    private final int secondNumber;
    private final int expected;
    private Calculator calculator;

    public CalculatorParameterizedTest(int firstNumber, int secondNumber, int expected) {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
        this.expected = expected;
    }

    @Parameterized.Parameters(name = "{index}: {0} + {1} = {2}")
    public static Collection<Object[]> addition_examples() {
        return Arrays.asList(new Object[][]{
                {2, 3, 5},
                {3, -2, 1},
                {-2, -3, -5},
                {5, 0, 5},
                {0, 0, 0}
        });
    }

    @Before
    public void set_up() {
        calculator = new Calculator();
    }

    @Test
    public void should_add_numbers() {
        assertEquals(expected, calculator.add(firstNumber, secondNumber));
    }
}
