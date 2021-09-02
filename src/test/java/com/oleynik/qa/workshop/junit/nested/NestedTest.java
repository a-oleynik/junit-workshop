package com.oleynik.qa.workshop.junit.nested;

//import com.nitorcreations.junit.runners.NestedRunner;
import com.oleynik.qa.workshop.junit.model.MyDoubleWrapper;
import de.bechte.junit.runners.context.HierarchicalContextRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(HierarchicalContextRunner.class)
//@RunWith(NestedRunner.class)
public class NestedTest {
    public class Multiply {
        @Test
        public void multiplication_with_zero_test() {
            int x = 0;
            int y = 10;
            System.out.printf("Multiplying %s on %s%n", x, y);
            assertEquals(x * y, new MyDoubleWrapper(x).multiply(y), 0);
        }

        @Test
        public void multiplication_with_two_negatives_test() {
            int x = -1;
            int y = -10;
            System.out.printf("Multiplying %s on %s%n", x, y);
            assertEquals(x * y, new MyDoubleWrapper(x).multiply(y), 0);
        }

        @Test
        public void multiplication_with_two_fractions_test() {
            double x = 0.5;
            double y = 0.0005;
            System.out.printf("Multiplying %s on %s%n", x, y);
            assertEquals(x * y, new MyDoubleWrapper(x).multiply(y), 0);
        }
    }

    public class Divide {
        @Test
        public void division_with_two_fractions_test() {
            double x = 0.5;
            double y = 0.0005;
            System.out.printf("Dividing %s by %s%n", x, y);
            assertEquals(x / y, new MyDoubleWrapper(x).divide(y), 0);
        }

        @Test
        public void division_with_two_negatives_test() {
            double x = -1;
            double y = -10;
            System.out.printf("Dividing %s by %s%n", x, y);
            assertEquals(x / y, new MyDoubleWrapper(x).divide(y), 0);
        }
    }

    public class Add {
        @Test
        public void addition_with_two_negatives_test() {
            double x = -1;
            double y = -10;
            System.out.printf("Adding %s to %s%n", x, y);
            assertEquals(x + y, new MyDoubleWrapper(x).add(y), 0);
        }

        @Test
        public void addition_with_two_fractions_test() {
            double x = 0.5;
            double y = 0.0005;
            System.out.printf("Adding %s to %s%n", x, y);
            assertEquals(x + y, new MyDoubleWrapper(x).add(y), 0);
        }
    }
}
