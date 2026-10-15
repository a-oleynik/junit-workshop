package com.oleynik.qa.workshop.junit.scenarios.calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculatorParameterizedTest {

    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    // {0}, {1}, {2} are the two inputs and expected sum from each CSV row.
    // Keep numeric CSV values unquoted in the invocation name (JUnit 5).
    @ParameterizedTest(name = "{0} + {1} = {2}")
    @CsvSource({
            "2, 3, 5",
            "3, -2, 1",
            "-2, -3, -5",
            "5, 0, 5",
            "0, 0, 0"
    })
    void shouldAddNumbers(int firstNumber, int secondNumber, int expected) {
        assertEquals(expected, calculator.add(firstNumber, secondNumber));
    }
}
