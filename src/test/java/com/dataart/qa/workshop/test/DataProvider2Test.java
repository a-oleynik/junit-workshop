package com.dataart.qa.workshop.test;

import com.dataart.qa.workshop.Factorial;
import com.dataart.qa.workshop.junit.dataproviders.MyDataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(DataProviderRunner.class)
public class DataProvider2Test {

    @Test
    @UseDataProvider(value = "read_numbers", location = MyDataProvider.class)
    public void test1(long number, long expected) {
        Assert.assertEquals("Factorial function is wrong.", expected, Factorial.factorial(number));
    }
}
