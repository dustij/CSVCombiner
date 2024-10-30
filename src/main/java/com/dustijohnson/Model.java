package com.dustijohnson;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Model
{
    private final StringProperty inDirectory = new SimpleStringProperty("");
    private final BooleanProperty okToMerge = new SimpleBooleanProperty(false);
    public final ListProperty<FileStatus> fileStatuses = new SimpleListProperty<>(FXCollections.observableArrayList());

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
