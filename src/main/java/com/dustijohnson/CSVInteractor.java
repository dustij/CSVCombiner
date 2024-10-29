package com.dustijohnson;

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
    }
}
