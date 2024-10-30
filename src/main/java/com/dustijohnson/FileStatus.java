package com.dustijohnson;

import javafx.beans.property.SimpleStringProperty;

import java.nio.file.Path;

public class FileStatus
{
    private final Path filePath;
    private final String fileName;
    private final SimpleStringProperty status;

    public FileStatus(Path filePath, String status)
    {
        this.filePath = filePath;
        this.fileName = filePath.getFileName().toString();
        this.status = new SimpleStringProperty(status);
    }

    public String getFileName()
    {
        return fileName;
    }

    public String getStatus()
    {
        return status.get();
    }

    public SimpleStringProperty statusProperty()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status.set(status);
    }

    public Path getFilePath()
    {
        return filePath;
    }
}
