package com.dustijohnson;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CSVFileRow
{
    private final StringProperty fileName;
    private final StringProperty status;

    public CSVFileRow(String fileName, String status)
    {
        this.fileName = new SimpleStringProperty(fileName);
        this.status = new SimpleStringProperty(status);
    }

    public String getFileName()
    {
        return fileName.get();
    }

    public StringProperty fileNameProperty()
    {
        return fileName;
    }

    public String getStatus()
    {
        return status.get();
    }

    public StringProperty statusProperty()
    {
        return status;
    }

    public void setFileName(String fileName)
    {
        this.fileName.set(fileName);
    }

    public void setStatus(String status)
    {
        this.status.set(status);
    }
}
