package com.oleynik.qa.workshop.junit.dataproviders;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import org.junit.jupiter.params.provider.Arguments;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class FactorialDataProvider {
    private static Stream<Arguments> factorials() {
        return Stream.of(
                arguments(1, 1),
                arguments(2, 2),
                arguments(3, 6),
                arguments(4, 24),
                arguments(5, 120)
        );
    }

    private static Stream<Arguments> factorialsFromFile() {
        try {
            CSVReader csvReader = new CSVReaderBuilder(new FileReader("src/test/resources/numbers.csv"))
                    .build();
            List<String[]> allData = csvReader.readAll();
            return allData.stream().map(Arguments::arguments);
        } catch (IOException | NumberFormatException | CsvException e) {
            throw new RuntimeException(e);
        }
    }
}
