package com.oleynik.qa.workshop.junit.nested;

import com.oleynik.qa.workshop.junit.model.MyDoubleWrapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NestedTest {
    @Nested
    @DisplayName("Multiplication tests")
    class Multiply {
        @Test
        void multiplication_with_zero_test() {
            int x = 0;
            int y = 10;
            System.out.printf("Multiplying %s on %s%n", x, y);
            assertEquals(x * y, new MyDoubleWrapper(x).multiply(y), 0);
        }

        @Test
        void multiplication_with_two_negatives_test() {
            int x = -1;
            int y = -10;
            System.out.printf("Multiplying %s on %s%n", x, y);
            assertEquals(x * y, new MyDoubleWrapper(x).multiply(y), 0);
        }

        @Test
        void multiplication_with_two_fractions_test() {
            double x = 0.5;
            double y = 0.0005;
            System.out.printf("Multiplying %s on %s%n", x, y);
            assertEquals(x * y, new MyDoubleWrapper(x).multiply(y), 0);
        }
    }

    @Nested
    @DisplayName("Division tests")
    class Divide {
        @Test
        void division_with_two_fractions_test() {
            double x = 0.5;
            double y = 0.0005;
            System.out.printf("Dividing %s by %s%n", x, y);
            assertEquals(x / y, new MyDoubleWrapper(x).divide(y), 0);
        }

        @Test
        void division_with_two_negatives_test() {
            double x = -1;
            double y = -10;
            System.out.printf("Dividing %s by %s%n", x, y);
            assertEquals(x / y, new MyDoubleWrapper(x).divide(y), 0);
        }
    }

    @Nested
    @DisplayName("Addition tests")
    class Add {
        @Test
        void addition_with_two_negatives_test() {
            double x = -1;
            double y = -10;
            System.out.printf("Adding %s to %s%n", x, y);
            assertEquals(x + y, new MyDoubleWrapper(x).add(y), 0);
        }

        @Test
        void addition_with_two_fractions_test() {
            double x = 0.5;
            double y = 0.0005;
            System.out.printf("Adding %s to %s%n", x, y);
            assertEquals(x + y, new MyDoubleWrapper(x).add(y), 0);
        }
    }
}
