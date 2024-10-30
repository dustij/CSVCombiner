package com.dustijohnson;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

/**
 * JavaFX App
 */
public class App extends Application
{
    public static void main(String[] args)
    {
        launch();
    }

    @Override
    public void start(Stage stage)
    {
        stage.setTitle("CSV Combiner");
        stage.setScene(new Scene(new CSVController().getView()));
        stage.show();
    }
}