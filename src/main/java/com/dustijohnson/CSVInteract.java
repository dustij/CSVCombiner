package com.dustijohnson;

import javafx.beans.binding.Bindings;
import javafx.stage.DirectoryChooser;
import javafx.stage.Popup;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CSVInteract
{
    private CSVModel model;
    private List<Path> csvFiles;

    public CSVInteract(CSVModel model)
    {
        this.model = model;
    }

    public void chooseDirectory()
    {
        System.out.println("Waiting for directory choice...");
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setInitialDirectory(model.getCsvDirectory().isEmpty()
                                                     ? new File(System.getProperty("user.home"))
                                                     : new File(model.getCsvDirectory()));

        File dir = directoryChooser.showDialog(new Popup());
        if (dir != null) {
            model.setCsvDirectory(dir);
            csvFiles = getCsvFiles(dir.toPath());
            model.setOkToMerge(isSameStructure());
        }
    }

    private List<Path> getCsvFiles(Path dir)
    {
        try (Stream<Path> stream = Files.list(dir)) {
            return stream.filter(Files::isRegularFile)
                         .filter(path -> path.toString().endsWith(".csv"))
                         .collect(Collectors.toList());
        } catch (IOException e) {
            System.err.println("This error occurred in the getCsvFiles method");
            e.printStackTrace();
            // Return an empty list if there's an error
            return List.of();
        }
    }

    private boolean isSameStructure()
    {
        if (csvFiles == null || csvFiles.isEmpty()) return false;

        // Logic to validate structure of all CSV files in `csvFiles` list
        // Example: load headers of each file and compare
        // Return true if structure is consistent, otherwise false
        return true; // Placeholder, replace with actual validation logic
    }

    public void mergeFiles()
    {
        System.out.println("Merging files...");
    }

}
