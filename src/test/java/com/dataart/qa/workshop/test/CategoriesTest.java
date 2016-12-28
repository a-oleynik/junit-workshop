package com.dataart.qa.workshop.test;

import com.dataart.qa.workshop.categories.RegressionTests;
import com.dataart.qa.workshop.categories.SmokeTests;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class CategoriesTest {

    @Category(SmokeTests.class)
    @Test
    public void categories_multiplication_test(){
        System.out.println("Multiplication test.");
        Assert.assertEquals("Multiplication result is wrong", 10, 2*5);
    }

    @Category({SmokeTests.class, RegressionTests.class})
    @Test
    public void categories_division_test(){
        System.out.println("Division test.");
        Assert.assertEquals(2.0, 10/5.0, 0);
    }

    @Test
    public void categories_boolean_test(){
        boolean variable = true;
        System.out.println("Boolean test.");
        Assert.assertTrue("variable is not true", variable);
        Assert.assertFalse("!variable is not false", !variable);
    }

}
