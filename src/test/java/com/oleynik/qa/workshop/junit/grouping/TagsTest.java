package com.oleynik.qa.workshop.junit.grouping;

import com.oleynik.qa.workshop.junit.grouping.tags.Regression;
import com.oleynik.qa.workshop.junit.grouping.tags.Smoke;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.oleynik.qa.workshop.junit.grouping.tags.Tags.SMOKE;

public class TagsTest {

    @Tag(SMOKE)
    @Test
    public void tags_multiplication_test() {
        System.out.println("Multiplication test");
        Assertions.assertEquals(10, 2 * 5, "Multiplication result is wrong");
    }

    @Smoke
    @Regression
    @Test
    public void tags_division_test() {
        System.out.println("Division test");
        Assertions.assertEquals(2.0, 10 / 5.0, 0);
    }

    @Tag("Smoke")
    @Tag("Regression")
    @Test
    public void tags_boolean_test() {
        boolean variable = true;
        System.out.println("Boolean test");
        Assertions.assertTrue(variable, "variable is not true");
        Assertions.assertFalse(!variable, "!variable is not false");
    }
}
