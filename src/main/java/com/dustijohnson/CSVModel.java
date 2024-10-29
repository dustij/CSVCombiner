package com.dustijohnson;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.File;

public class CSVModel
{
    private final StringProperty csvDirectory = new SimpleStringProperty("");
    // TODO: using this property, improve user experience by displaying message why it may not be ok to merge
    // Issue URL: https://github.com/dustij/CSVCombiner/issues/4
    private final BooleanProperty okToMerge = new SimpleBooleanProperty(false);

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
        csvDirectory.set(directory.toString());
    }

    public boolean isOkToMerge()
    {
        return okToMerge.get();
    }

    public BooleanProperty okToMergeProperty()
    {
        return okToMerge;
    }

    public void setOkToMerge(boolean isOK)
    {
        okToMerge.set(isOK);
    }
}
