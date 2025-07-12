package com.oleynik.qa.workshop.junit.dataproviders;

import com.oleynik.qa.workshop.junit.annotations.ParameterizedDataSource;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.support.AnnotationConsumer;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

public class OneMoreFactorialDataProvider implements ArgumentsProvider, AnnotationConsumer<ParameterizedDataSource> {
    private String path;

    OneMoreFactorialDataProvider() {
    }

    @Override
    public void accept(final ParameterizedDataSource source) {
        path = source.path();
    }

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        try {
            CSVReader csvReader = new CSVReaderBuilder(new FileReader(path))
                    .build();
            List<String[]> allData = csvReader.readAll();
            return allData.stream().map(Arguments::arguments);
        } catch (IOException | NumberFormatException | CsvException e) {
            throw new RuntimeException(e);
        }
    }
}
