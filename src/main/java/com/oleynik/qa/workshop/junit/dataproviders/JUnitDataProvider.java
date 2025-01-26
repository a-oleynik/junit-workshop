package com.oleynik.qa.workshop.junit.dataproviders;

import com.oleynik.qa.workshop.junit.annotations.DataSource;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import com.tngtech.java.junit.dataprovider.DataProvider;
import org.junit.runners.model.FrameworkMethod;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class JUnitDataProvider {
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
            throw new RuntimeException(e);
        }
    }

    public static String getDataSourcePathFromTestInfo(FrameworkMethod frameworkMethod) {
        if (Arrays.stream(frameworkMethod.getMethod().getDeclaredAnnotations())
                .anyMatch(DataSource.class::isInstance)) {
            DataSource annotation = frameworkMethod.getMethod().getAnnotation(DataSource.class);
            return annotation.path();
        } else {
            throw new RuntimeException("DataSource annotation is not found");
        }
    }
}
