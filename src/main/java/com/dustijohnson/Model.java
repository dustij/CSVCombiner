package com.dustijohnson;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Model
{
    private final StringProperty inDirectory = new SimpleStringProperty("");
    private final BooleanProperty okToMerge = new SimpleBooleanProperty(false);

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
}
