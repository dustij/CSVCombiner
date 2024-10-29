package com.dustijohnson;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.File;
import java.nio.file.Path;

public class CSVModel
{
    private final StringProperty csvDirectory = new SimpleStringProperty("");

    public String getCsvDirectory()
    {
        return csvDirectory.get();
    }

    public StringProperty csvDirectoryProperty()
    {
        return csvDirectory;
    }

    public void setCsvDirectory(String directory)
    {
        csvDirectory.set(directory);
    }

    public void setCsvDirectory(File directory)
    {
        System.out.println(getCsvDirectory());
    }
}
