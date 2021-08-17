package com.oleynik.qa.workshop.junit.dataproviders;

import com.oleynik.qa.workshop.junit.annotations.DataSource;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import com.tngtech.junit.dataprovider.DataProvider;
import org.junit.jupiter.api.TestInfo;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class JUnitDataProvider {
    @DataProvider
    public static List<List<Object>> read_numbers(TestInfo testInfo) {
        try {
            String dataSource = getDataSourcePathFromTestInfo(testInfo);
            CSVReader csvReader = new CSVReaderBuilder(new FileReader(dataSource))
                    .build();
            List<String[]> allData = csvReader.readAll();
            return allData
                    .stream()
                    .map(e -> Arrays.stream(e)
                            .map(num -> (Object) Long.parseLong(num))
                            .collect(Collectors.toList()))
                    .collect(Collectors.toList());
        } catch (IOException | NumberFormatException | CsvException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static String getDataSourcePathFromTestInfo(TestInfo testInfo) {
        if (testInfo.getTestMethod().isPresent() &&
                Arrays.stream(testInfo.getTestMethod().get().getDeclaredAnnotations())
                        .anyMatch(DataSource.class::isInstance)) {
            DataSource annotation = testInfo.getTestMethod().get().getAnnotation(DataSource.class);
            return annotation.path();
        } else {
            throw new RuntimeException("DataSource annotation is not found");
        }
    }
}
