package com.dustijohnson;

import javafx.scene.layout.Region;
import javafx.util.Builder;

public class CSVController
{
    private final Builder<Region> viewBuilder;
    private final CSVInteractor interactor;

    public CSVController()
    {
        CSVModel model = new CSVModel();
        interactor = new CSVInteractor(model);
        viewBuilder = new CSVVIewBuilder(model, interactor::mergeFiles, interactor::chooseDirectory);
    }

    public Region getView()
    {
        return viewBuilder.build();
    }
}
