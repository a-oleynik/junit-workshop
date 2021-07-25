package com.oleynik.qa.workshop.junit.ddt;

import com.oleynik.qa.workshop.junit.Factorial;
import com.oleynik.qa.workshop.junit.annotations.DataSource;
import com.oleynik.qa.workshop.junit.dataproviders.JUnitDataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(DataProviderRunner.class)
public class DataProvider2Test {

    @Test
    @UseDataProvider(value = "read_numbers", location = JUnitDataProvider.class)
    @DataSource(path = "src/test/resources/numbers.csv")
    public void junit4_with_external_data_provider_test(long number, long expected) {
        Assert.assertEquals("Factorial function is wrong.", expected, Factorial.factorial(number));
    }
}
