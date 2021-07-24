package com.oleynik.qa.workshop.junit.extensions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assumptions.assumeTrue;

@ExtendWith(TestWatcherExtension.class)
public class TestWatcherExtensionTest {

    @Test
    public void test_watcher_multiplication_test(){
        System.out.println("Multiplication test");
        Assertions.assertEquals(10, 2*5, "Multiplication result is wrong");
    }

    @Test
    public void test_watcher_division_test(){
        System.out.println("Division test");
        Assertions.assertEquals(1.9, 10/5.0, 0.2);// 0
    }

    @Test
    @Disabled("XXX-111")
    public void test_watcher_boolean_test(){
        System.out.println("Boolean test");
        boolean variable = true;
        Assertions.assertTrue(variable, "variable is not true");
        Assertions.assertFalse(!variable, "!variable is not false");
    }

    @Test
    public void test_watcher_assume_test(){
        System.out.println("Assume test");
        String os = "Windows 10";
        assumeTrue(os.contains("HP-UX"),
                () -> "Detected os is not HP-UX");
    }
}
