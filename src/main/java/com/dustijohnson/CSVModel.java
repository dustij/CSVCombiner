package com.dustijohnson;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

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

    public void setCsvDirectory(Path directory)
    {
        csvDirectory.set(directory.toString());
    }
}
