package com.dustijohnson;

import javafx.concurrent.Task;
import javafx.scene.layout.Region;

public class Controller
{
    private final Model model;
    private final Interactor interactor;
    private final ViewBuilder viewBuilder;

    public Controller()
    {
        model = new Model();
        interactor = new Interactor(model);
        viewBuilder = new ViewBuilder(model, this::mergeFiles);
        setPropertyListener();
    }

    public Region getView()
    {
        return viewBuilder.build();
    }

    private void mergeFiles(Runnable postActionGuiCleanup)
    {
        Task<Void> mergeTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception
            {
                interactor.mergeFiles();
                return null;
            }
        };
        mergeTask.setOnSucceeded(workerStateEvent -> {
            interactor.updateModel();
            postActionGuiCleanup.run();
        });
        Thread mergeThread = new Thread(mergeTask);
        mergeThread.start();
    }

    private void setPropertyListener()
    {
        model.inDirectoryProperty().addListener(observable -> interactor.getFiles());
    }
}

