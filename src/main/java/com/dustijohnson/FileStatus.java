package com.dustijohnson;

import javafx.beans.property.SimpleStringProperty;

public class FileStatus
{
    private final String fileName;
    private final SimpleStringProperty status;

    public FileStatus(String fileName, String status)
    {
        this.fileName = fileName;
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
}
