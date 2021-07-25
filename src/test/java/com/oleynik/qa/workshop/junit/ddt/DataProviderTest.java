package com.oleynik.qa.workshop.junit.ddt;

import com.oleynik.qa.workshop.junit.Factorial;
import com.oleynik.qa.workshop.junit.annotations.DataSource;
import com.oleynik.qa.workshop.junit.dataproviders.JUnitDataProvider;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import com.tngtech.junit.dataprovider.DataProvider;
import com.tngtech.junit.dataprovider.UseDataProvider;
//import com.tngtech.junit.dataprovider.UseDataProviderExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.TestInfo;
//import org.junit.jupiter.api.TestTemplate;
//import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static com.oleynik.qa.workshop.junit.dataproviders.JUnitDataProvider.getDataSourcePathFromTestInfo;

//@ExtendWith(UseDataProviderExtension.class)
public class DataProviderTest {
    // The data provider requires annotation @DataSource(path = "src/test/resources/numbers.csv")
    // added to the test method
    @DataProvider
    public static Object[][] read_numbers(TestInfo testInfo) {
        try {
            String dataSource = getDataSourcePathFromTestInfo(testInfo);
            CSVReader csvReader = new CSVReaderBuilder(new FileReader(dataSource))
                    .build();
            List<String[]> allData = csvReader.readAll();
            return allData
                    .stream()
                    .map(e -> Arrays.stream(e)
                            .map(num -> (Object) Long.parseLong(num))
                            .toArray())
                    .toArray(Object[][]::new);
        } catch (IOException | NumberFormatException | CsvException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    //@TestTemplate
    @ParameterizedTest
    @UseDataProvider("read_numbers")
    @DataSource(path = "src/test/resources/numbers.csv")
    public void junit5_with_data_provider_test(long number, long expected) {
        Assertions.assertEquals(expected, Factorial.factorial(number), "Factorial function is wrong.");
    }

    //@TestTemplate
    @ParameterizedTest
    @UseDataProvider(value = "read_numbers", location = JUnitDataProvider.class)
    @DataSource(path = "src/test/resources/numbers.csv")
    public void junit5_with_external_data_provider_test(long number, long expected) {
        Assertions.assertEquals(expected, Factorial.factorial(number), "Factorial function is wrong.");
    }
}
