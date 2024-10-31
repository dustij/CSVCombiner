package com.dustijohnson;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public class Service
{
    private List<String> expectedHeaders;

    public void resetExpectedHeaders()
    {
        expectedHeaders = null;
    }

    public List<Path> getCsvFiles(String directoryPath) throws IOException
    {
        try (Stream<Path> stream = Files.list(Path.of(directoryPath))) {
            return stream.filter(Files::isRegularFile).filter(path -> path.toString().endsWith(".csv")).collect(
                    Collectors.toList());
        }
    }

    public boolean validateCsvFile(Path filePath)
    {
        try {
            List<String> headers = readHeaders(filePath);
            if (expectedHeaders == null) {
                // Set headers on the first file
                expectedHeaders = headers;
            }
            return headers.equals(expectedHeaders);
        } catch (IOException e) {
            System.err.println("Error reading CSV file " + filePath);
            return false;
        }
    }

    public void mergeCsvFiles(List<Path> files, Path outputFile) throws IOException
    {
        // Ensure output directory exists
        Files.createDirectories(outputFile.getParent());

        // Set to keep unique rows
        Set<Row> uniqueRows = new HashSet<>();

        // Read files
        for (Path filePath : files) {
            try (Reader reader = new FileReader(filePath.toFile())) {
                // This will  parse the header names from the first record and skip the first record when iterating
                Iterable<CSVRecord> records = CSVFormat.DEFAULT.builder()
                                                               .setHeader()
                                                               .setSkipHeaderRecord(true)
                                                               .build()
                                                               .parse(reader);
                for (CSVRecord record : records) {
                    Row row = new Row(record);
                    uniqueRows.add(row);
                }
            }
        }

        // Write file
        try (BufferedWriter writer = Files.newBufferedWriter(outputFile)) {
            writer.write(String.join(",", expectedHeaders));
            writer.newLine();
            for (Row row : uniqueRows) {
                writer.write(row.toString());
                writer.newLine();
            }
        }
    }

    private List<String> readHeaders(Path filePath) throws IOException
    {
        try (var reader = Files.newBufferedReader(filePath)) {
            String headerLine = reader.readLine();
            return headerLine != null ? List.of(headerLine.split(",")) : List.of();
        }
    }

    // Row class for unique comparison
    private class Row
    {
        private final List<String> columns;

        public Row(CSVRecord record)
        {
            columns = new ArrayList<>();
            record.forEach(columns::add);
        }

        @Override
        public boolean equals(Object o)
        {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Row row = (Row) o;
            return Objects.equals(columns, row.columns);
        }

        @Override
        public int hashCode()
        {
            return Objects.hashCode(columns);
        }

        @Override
        public String toString()
        {
            // Format row as csv line
            return String.join(",", columns);
        }
    }


}
