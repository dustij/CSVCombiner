package com.dustijohnson;

import javafx.stage.DirectoryChooser;
import javafx.stage.Popup;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class Interactor
{
    private final Model model;

    public Interactor(Model model)
    {
        this.model = model;
    }

    public void mergeFiles()
    {
    }

    public void updateModel()
    {
    }

    public void getFiles()
    {
        try (Stream<Path> stream = Files.list(Path.of(model.getInDirectory()))) {
            stream.filter(Files::isRegularFile)
                  .filter(path -> path.toString().endsWith(".csv"))
                  .forEach(path -> model.addFileStatus(new FileStatus(path.getFileName().toString(), "Pending")));
        } catch (IOException e) {
            System.err.println("Error will getting files in interactor");
            throw new RuntimeException(e);
        }
    }

    public void validateFiles()
    {
        boolean allValid = true;
        for (FileStatus fileStatus : model.getFileStatuses()) {
            boolean isValid = validateFile(fileStatus.getFileName());
            fileStatus.setStatus(isValid ? "Valid" : "Invalid");
            if (!isValid) allValid = false;
        }
        model.setOkToMerge(allValid);
    }

    private boolean validateFile(String fileName)
    {
        // Replace with actual validation logic.
        return fileName.endsWith(".csv");
    }

    public void chooseDirectory()
    {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setInitialDirectory(model.getInDirectory().isEmpty()
                                                     ? new File(System.getProperty("user.home"))
                                                     : new File(model.getInDirectory()));

        File dir = directoryChooser.showDialog(new Popup());
        if (dir != null) {
            model.setInDirectory(dir.toString());
            getFiles();
            validateFiles();
        }
    }

    public boolean isValid()
    {
        // implement this
        return true;
    }
}
