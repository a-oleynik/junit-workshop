package com.oleynik.qa.workshop.junit.ddt;

import com.oleynik.qa.workshop.junit.Factorial;
import com.oleynik.qa.workshop.junit.annotations.DataSource;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.model.FrameworkMethod;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static com.oleynik.qa.workshop.junit.dataproviders.JUnitDataProvider.getDataSourcePathFromTestInfo;

@RunWith(DataProviderRunner.class)
public class DataProviderTest {
    @DataProvider
    public static Object[][] read_numbers(FrameworkMethod frameworkMethod) {
        try {
            String dataSource = getDataSourcePathFromTestInfo(frameworkMethod);
            CSVReader csvReader = new CSVReaderBuilder(new FileReader(dataSource))
                    .build();
            List<String[]> allData = csvReader.readAll();
            return allData
                    .stream()
                    .map(e -> Arrays.stream(e)
                            .map(num -> (Object) Long.parseLong(num)).toArray())
                    .toArray(Object[][]::new);
        } catch (IOException | NumberFormatException | CsvException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Test
    @UseDataProvider("read_numbers")
    @DataSource(path = "src/test/resources/numbers.csv")
    public void junit4_with_data_provider_test(long number, long expected) {
        Assert.assertEquals("Factorial function is wrong", expected, Factorial.factorial(number));
    }

    @Test
    public void non_parametrised_test() {
        System.out.println("test2");
    }
}
