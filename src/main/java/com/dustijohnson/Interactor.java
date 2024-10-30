package com.dustijohnson;

import javafx.stage.DirectoryChooser;
import javafx.stage.Popup;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class Interactor
{
    private final Model model;
    private final Service service;

    public Interactor(Model model, Service csvService)
    {
        this.model = model;
        this.service = csvService;
    }

    public void updateModel()
    {
    }

    public void getFiles()
    {
        try {
            List<Path> files = service.getCsvFiles(model.getInDirectory());
            model.getFileStatuses().clear();
            files.forEach(path -> model.addFileStatus(new FileStatus(path, "Pending")));
        } catch (IOException e) {
            System.err.println("Error will getting files in interactor");
            throw new RuntimeException(e);
        }
    }

    public void validateFiles()
    {
        boolean allValid = true;
        for (FileStatus fileStatus : model.getFileStatuses()) {
            boolean isValid = service.validateCsvFile(fileStatus.getFilePath());
            fileStatus.setStatus(isValid ? "Valid" : "Invalid");
            if (!isValid) allValid = false;
        }
        model.setOkToMerge(allValid);
    }

    public void mergeFiles()
    {
        try {
            List<Path> validFiles = service.getCsvFiles(model.getInDirectory())
                                           .stream()
                                           .filter(path -> model.getFileStatuses()
                                                                   .stream()
                                                                   .anyMatch(fileStatus -> fileStatus.getFileName()
                                                                                                     .equals(path.getFileName()
                                                                                                                 .toString()) && "Valid".equals(
                                                                           fileStatus.getStatus())))
                                           .collect(Collectors.toList());
            service.mergeCsvFiles(
                    validFiles,
                    Path.of(System.getProperty("user.dir"),
                            "output",
                            String.format("merged_output_%s", LocalDate.now())));
            model.getFileStatuses().forEach(fileStatus -> fileStatus.setStatus("Complete"));
        } catch (IOException e) {
            System.err.println("Error merging files");
            throw new RuntimeException(e);
        }
    }

    public void chooseDirectory()
    {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setInitialDirectory(model.getInDirectory().isEmpty()
                                                     ? new File(System.getProperty("user.dir"))
                                                     : new File(model.getInDirectory()));

        File dir = directoryChooser.showDialog(new Popup());
        if (dir != null) {
            model.setInDirectory(dir.toString());
            service.resetExpectedHeaders();
            getFiles();
            validateFiles();
        }
    }
}
