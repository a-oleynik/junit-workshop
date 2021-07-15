package com.dataart.qa.workshop.test;

import com.dataart.qa.workshop.Factorial;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

@RunWith(DataProviderRunner.class)
public class DataProviderTest {

    @DataProvider
    public static Object[][] read_numbers() throws IOException, NumberFormatException, CsvValidationException {
        ArrayList<Object[]> outData = new ArrayList<>();
        try {
            CSVReader reader = new CSVReader(new FileReader("src/test/resources/numbers.csv"));
            String[] data;
            while ((data = reader.readNext()) != null) {
                Object[] row = new Object[data.length];
                for (int i = 0; i < data.length; i++) {
                    row[i] = Long.parseLong(data[i].trim());
                }
                outData.add(row);
            }
        } catch (IOException | NumberFormatException | CsvValidationException e) {
            e.printStackTrace();
            throw e;
        }
        return outData.toArray(new Object[outData.size()][]);
    }

    @Test
    @UseDataProvider("read_numbers")
    public void test1(long number, long expected) {
        Assert.assertEquals("Factorial function is wrong.", expected, Factorial.factorial(number));
    }

    @Test
    public void test2() {
        System.out.println("test2");
    }
}
