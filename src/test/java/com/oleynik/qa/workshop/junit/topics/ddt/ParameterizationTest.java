package com.oleynik.qa.workshop.junit.topics.ddt;

import com.oleynik.qa.workshop.junit.topics.Factorial;
import com.oleynik.qa.workshop.junit.topics.annotations.ParameterizedDataSource;
import com.oleynik.qa.workshop.junit.topics.dataproviders.AnotherFactorialDataProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class ParameterizationTest {
    @ParameterizedTest(name = "Local method source: {0}! = {1}")
    @MethodSource("factorials")
    public void inner_method_source_factorial_test(int number, int expected) {
        Assertions.assertEquals(expected, Factorial.factorial(number), "factorial function works wrong");
    }

    private static Stream<Arguments> factorials() {
        return Stream.of(
                arguments(1, 1),
                arguments(2, 2),
                arguments(3, 6),
                arguments(4, 24),
                arguments(5, 120)
        );
    }

    @ParameterizedTest(name = "External method source: {0}! = {1}")
    @MethodSource("com.oleynik.qa.workshop.junit.topics.dataproviders.FactorialDataProvider#factorials")
    void outer_method_source_factorial_test(int number, int expected) {
        Assertions.assertEquals(expected, Factorial.factorial(number), "factorial function works wrong");
    }

    @ParameterizedTest(name = "Arguments provider: {0}! = {1}")
    @ArgumentsSource(AnotherFactorialDataProvider.class)
    void arguments_source_factorial_test(int number, int expected) {
        Assertions.assertEquals(expected, Factorial.factorial(number), "factorial function works wrong");
    }

    @ParameterizedTest(name = "CSV via method source: {0}! = {1}")
    @MethodSource("com.oleynik.qa.workshop.junit.topics.dataproviders.FactorialDataProvider#factorialsFromFile")
    void csv_from_outer_method_source_factorial_test(int number, int expected) {
        Assertions.assertEquals(expected, Factorial.factorial(number), "factorial function works wrong");
    }

    public static Object[][] anotherFactorials() {
        return new Object[][]{
                {1, 1},
                {2, 2},
                {3, 6},
                {4, 24},
                {5, 120}
        };
    }

    @ParameterizedTest(name = "Array method source: {0}! = {1}")
    @MethodSource("anotherFactorials")
    void another_inner_method_source_factorial_test(int number, int expected) {
        Assertions.assertEquals(expected, Factorial.factorial(number), "factorial function works wrong");
    }

    @ParameterizedTest(name = "Custom CSV source: {0}! = {1}")
    @ParameterizedDataSource(path = "src/test/resources/numbers.csv")
    void parameterized_arguments_source_factorial_test(int number, int expected) {
        Assertions.assertEquals(expected, Factorial.factorial(number), "factorial function works wrong");
    }
}
