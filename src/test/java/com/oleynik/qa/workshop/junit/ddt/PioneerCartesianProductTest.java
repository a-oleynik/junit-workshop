package com.oleynik.qa.workshop.junit.ddt;

import com.oleynik.qa.workshop.junit.model.MyDoubleWrapper;
import org.junitpioneer.jupiter.cartesian.CartesianTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PioneerCartesianProductTest {

    @CartesianTest
    void cartesian_product_test(@CartesianTest.Values(ints = {1, 2}) int x,
                                @CartesianTest.Values(ints = {3, 4}) int y) {
        System.out.printf("Multiplying %s on %s%n", x, y);
        assertEquals(x * y, new MyDoubleWrapper(x).multiply(y), 0);
    }
}
