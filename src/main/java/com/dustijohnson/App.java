package com.dustijohnson;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;

/**
 * JavaFX App
 */
public class App extends Application
{

    private final static StringProperty sourceDirectory = new SimpleStringProperty("");
    private Stage stage;

    public static void main(String[] args)
    {
        launch();
    }

    @Override
    public void start(Stage stage)
    {
        this.stage = stage;

        var javaVersion = SystemInfo.javaVersion();
        var javafxVersion = SystemInfo.javafxVersion();

        var scene = new Scene(createContents(), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    private Region createContents()
    {
        HBox results = new HBox(20, createSourceInput(), createSourceButton());
        results.setAlignment(Pos.CENTER);
        return results;
    }

    private Node createSourceInput()
    {
        Label label = new Label("Source Directory: ");
        Label chosen = new Label("");
        chosen.setPrefWidth(200);
        chosen.textProperty().bind(sourceDirectory);
        HBox hBox = new HBox(6, label, chosen);
        hBox.setAlignment(Pos.CENTER);
        return hBox;
    }

    private Node createSourceButton()
    {
        Button button = new Button("Choose Folder");
        button.setOnAction(e -> {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            directoryChooser.setTitle("Select Source Directory");
            if (sourceDirectory.isEmpty().get()) {
                // Default location is user home
                directoryChooser.setInitialDirectory(new File(System.getProperty("user.home")));
            } else {
                directoryChooser.setInitialDirectory(new File(sourceDirectory.get()));
            }
            File dir = directoryChooser.showDialog(stage);
            if (dir != null) sourceDirectory.set(dir.toString());
        });
        return button;
    }
}