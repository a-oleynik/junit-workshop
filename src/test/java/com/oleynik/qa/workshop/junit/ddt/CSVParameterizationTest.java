package com.oleynik.qa.workshop.junit.ddt;

import com.oleynik.qa.workshop.junit.Factorial;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

public class CSVParameterizationTest {
    @ParameterizedTest
    @CsvSource({
            "1, 1",
            "2, 2",
            "3, 6",
            "4, 24",
            "5, 120"
    })
    public void csv_source_factorial_test(int number, int expected) {
        Assertions.assertEquals(expected, Factorial.factorial(number), "factorial function works wrong");
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/numbers.csv")
    public void csv_file_source_factorial_test(int number, int expected) {
        Assertions.assertEquals(expected, Factorial.factorial(number), "factorial function works wrong");
    }
}
