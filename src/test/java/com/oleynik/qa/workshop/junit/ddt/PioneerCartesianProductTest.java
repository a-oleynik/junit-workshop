package com.oleynik.qa.workshop.junit.ddt;

import com.oleynik.qa.workshop.junit.model.MyDoubleWrapper;
import org.junitpioneer.jupiter.CartesianProductTest;
import org.junitpioneer.jupiter.CartesianValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PioneerCartesianProductTest {

    @CartesianProductTest
    @CartesianValueSource(ints = { 1, 2 })
    @CartesianValueSource(ints = { 3, 4 })
    void cartesian_product_test(int x, int y) {
        System.out.printf("Multiplying %s on %s%n", x, y);
        assertEquals(x * y, new MyDoubleWrapper(x).multiply(y), 0);
    }
}
