package com.dustijohnson;

import javafx.stage.DirectoryChooser;
import javafx.stage.Popup;
import javafx.stage.PopupWindow;
import javafx.stage.Window;

import java.io.File;

public class CSVInteractor
{
    private CSVModel model;

    public CSVInteractor(CSVModel model)
    {
        this.model = model;
    }

    public void mergeFiles()
    {
        System.out.println("Merging files...");
    }

    public void chooseDirectory()
    {
        System.out.println("Waiting for directory choice...");
        DirectoryChooser directoryChooser = new DirectoryChooser();
        if (model.getCsvDirectory().isEmpty()) {
            // Default location is user home
            directoryChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        } else {
            directoryChooser.setInitialDirectory(new File(model.getCsvDirectory()));
        }
        File dir = directoryChooser.showDialog(new Popup());
        if (dir != null) {
            model.setCsvDirectory(dir);
        }
    }
}
