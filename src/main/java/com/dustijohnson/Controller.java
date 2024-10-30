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
        interactor = new Interactor(model, new Service());
        viewBuilder = new ViewBuilder(model, this::mergeFiles, this::chooseDirectory);
        setPropertyListener();
    }

    private void chooseDirectory(Runnable postActionGuiCleanup)
    {
        interactor.chooseDirectory();
        postActionGuiCleanup.run();
    }

    public Region getView()
    {
        return viewBuilder.build();
    }

    private void mergeFiles(Runnable postActionGuiCleanup)
    {
        Task<Void> mergeTask = new Task<Void>()
        {
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
        // When directory property changes, it calls a method in the interactor to do the work
        model.inDirectoryProperty().addListener(observable -> interactor.getFiles());
    }
}

