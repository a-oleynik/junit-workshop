package com.oleynik.qa.workshop.junit.scenarios.calculator.beforejunit;

import com.oleynik.qa.workshop.junit.scenarios.calculator.Calculator;

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
