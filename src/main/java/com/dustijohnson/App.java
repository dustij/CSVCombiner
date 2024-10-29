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
        stage.setScene(new Scene(new CSVController().getView()));
        stage.show();
    }
//
//    private Region createContents()
//    {
//        HBox results = new HBox(20, createSourceLabels(), createSourceButton());
//        results.setAlignment(Pos.CENTER);
//        return results;
//    }
//
//    private Node createSourceLabels()
//    {
//        Label label = new Label("Source Directory: ");
//        Label chosen = new Label("");
//        chosen.setWrapText(true);
//        chosen.setPrefWidth(200);
//        chosen.textProperty().bind(sourceDirectory);
//        HBox hBox = new HBox(6, label, chosen);
//        hBox.setAlignment(Pos.CENTER);
//        return hBox;
//    }
//
//    private Node createSourceButton()
//    {
//        Button button = new Button("Choose Folder");
//        button.setOnAction(e -> {
//            DirectoryChooser directoryChooser = new DirectoryChooser();
//            directoryChooser.setTitle("Select Source Directory");
//            if (sourceDirectory.isEmpty().get()) {
//                // Default location is user home
//                directoryChooser.setInitialDirectory(new File(System.getProperty("user.home")));
//            } else {
//                directoryChooser.setInitialDirectory(new File(sourceDirectory.get()));
//            }
//            File dir = directoryChooser.showDialog(stage);
//            if (dir != null) {
//                sourceDirectory.set(dir.toString());
//                displayCSVFiles();
//            }
//        });
//        return button;
//    }
//
//    private void displayCSVFiles()
//    {
//        List<Path> csvFiles = CSVService.getFiles(sourceDirectory.get());
//        for (Path file : Objects.requireNonNull(csvFiles)) {
//            System.out.println(file);
//        }
//    }
}