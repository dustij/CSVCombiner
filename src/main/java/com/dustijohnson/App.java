package com.dustijohnson;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application
{
    @Override
    public void start(Stage stage) throws Exception
    {
        stage.setScene(new Scene(new Controller().getView()));
        stage.show();
    }
}
