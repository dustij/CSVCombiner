package com.dustijohnson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CSVService
{
    public static List<Path> getFiles(String directory)
    {
        Path dir = Paths.get(directory);

        try (Stream<Path> stream = Files.list(dir)) {
            return stream.filter(Files::isRegularFile)
                         .filter(path -> path.toString().endsWith(".csv"))
                         .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
