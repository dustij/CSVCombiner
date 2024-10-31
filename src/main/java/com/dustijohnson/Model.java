package com.dustijohnson;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Properties;

public class Model
{
    private final StringProperty inDirectory = new SimpleStringProperty(envInputDirectory());
    private final StringProperty outDirectory = new SimpleStringProperty(envOutputDirectory());
    private final BooleanProperty okToMerge = new SimpleBooleanProperty(false);
    private final ListProperty<FileStatus> fileStatuses = new SimpleListProperty<>(FXCollections.observableArrayList());

    private String envInputDirectory()
    {
        return System.getenv("INPUT_DIR") != null ? System.getenv("INPUT_DIR") : "";
    }

    private String envOutputDirectory()
    {
        return System.getenv("OUTPUT_DIR") != null ? System.getenv("OUTPUT_DIR") : "";
    }

//    public Model()
//    {
//        Properties properties = new Properties();
//        try (InputStream input = new FileInputStream("locations")) {
//            properties.load(input);
//            inDirectory = new SimpleStringProperty(properties.getProperty("input", ""));
//            outDirectory = new SimpleStringProperty(properties.getProperty("output",
//                                                                                 Path.of(System.getProperty("user.dir"),
//                                                                                         "output").toString()));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

    public String getOutDirectory()
    {
        return outDirectory.get();
    }

    public StringProperty outDirectoryProperty()
    {
        return outDirectory;
    }

    public void setOutDirectory(String directory)
    {
        outDirectory.set(directory);
    }

    public String getInDirectory()
    {
        return inDirectory.get();
    }

    public StringProperty inDirectoryProperty()
    {
        return inDirectory;
    }

    public void setInDirectory(String directory)
    {
        inDirectory.set(directory);
    }

    public boolean isOkToMerge()
    {
        return okToMerge.get();
    }

    public BooleanProperty okToMergeProperty()
    {
        return okToMerge;
    }

    public void setOkToMerge(boolean isValid)
    {
        okToMerge.set(isValid);
    }

    public ObservableList<FileStatus> getFileStatuses()
    {
        return fileStatuses.get();
    }

    public ListProperty<FileStatus> fileStatusesProperty()
    {
        return fileStatuses;
    }

    public void addFileStatus(FileStatus status)
    {
        fileStatuses.add(status);
    }
}
