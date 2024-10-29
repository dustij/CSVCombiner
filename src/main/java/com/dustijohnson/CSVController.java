package com.dustijohnson;

import javafx.scene.layout.Region;
import javafx.util.Builder;

public class CSVController
{
    private final Builder<Region> viewBuilder;
    private final CSVInteract interact;

    public CSVController()
    {
        CSVModel model = new CSVModel();
        interact = new CSVInteract(model);
        viewBuilder = new CSVVIewBuilder(model, interact::mergeFiles, interact::chooseDirectory);
    }

    public Region getView()
    {
        return viewBuilder.build();
    }
}
