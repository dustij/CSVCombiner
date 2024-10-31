package com.dustijohnson;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.nio.file.Path;

public class Model
{
    private final StringProperty inDirectory = new SimpleStringProperty("");
    private final StringProperty outDirectory = new SimpleStringProperty(Path.of(System.getProperty("user.dir"),
                                                                                 "output").toString());
    private final BooleanProperty okToMerge = new SimpleBooleanProperty(false);
    public final ListProperty<FileStatus> fileStatuses = new SimpleListProperty<>(FXCollections.observableArrayList());

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
