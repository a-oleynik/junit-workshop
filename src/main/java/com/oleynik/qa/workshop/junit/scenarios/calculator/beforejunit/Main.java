package com.oleynik.qa.workshop.junit.scenarios.calculator.beforejunit;

import com.oleynik.qa.workshop.junit.scenarios.calculator.Calculator;

/**
 * Starting point for the Calculator article and workshop before introducing JUnit.
 * Demonstrates checking {@link Calculator#add(int, int)} from a plain Java
 * {@code main} method by printing the expected value, actual value, and comparison result.
 *
 * <p>Run this class directly and inspect the console output. A mismatch only prints
 * {@code false}; it does not report a failed test or cause a non-zero exit status.
 * This motivates the next step in {@code CalculatorTest}: using JUnit to discover
 * and run tests, assert expected results, and report failures automatically.
 */
public class Main {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();

        int expected = 5;
        int actual = calculator.add(2, 3);

        System.out.println("Expected: " + expected);
        System.out.println("Actual:   " + actual);

        System.out.println(expected == actual);
    }
}
