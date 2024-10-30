package com.dustijohnson;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
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
        for (Path filePath : files) {
            try (Reader reader = new FileReader(filePath.toFile())) {
                // This will  parse the header names from the first record and skip the first record when iterating
                Iterable<CSVRecord> records = CSVFormat.DEFAULT.builder()
                                                               .setHeader()
                                                               .setSkipHeaderRecord(true)
                                                               .build()
                                                               .parse(reader);
                for (CSVRecord record : records) {
                    System.out.println(record);
                    // Get by header or index
                    System.out.print("Get by header: ");
                    System.out.println(record.get("colOne"));
                    System.out.print("Get by index: ");
                    System.out.println(record.get(0));
                }
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
}
