package com.dustijohnson;

import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.Builder;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class CSVVIewBuilder implements Builder<Region>
{
    private final CSVModel model;
    private final Runnable mergeHandler;
    private final Runnable chooseHandler;
    private final Map<String, String> cssClasses;
    private final TableView<CSVFileRow> tableView;

    public CSVVIewBuilder(CSVModel model, Runnable mergeHandler, Runnable chooseHandler)
    {
        cssClasses = new HashMap<>();
        cssClasses.put("headingLabel", "heading-label");
        cssClasses.put("directoryLabel", "directory-label");
        cssClasses.put("boundLabel", "bound-label");

        this.model = model;
        this.mergeHandler = mergeHandler;
        this.chooseHandler = chooseHandler;

        tableView = new TableView<>();
    }

    @Override
    public Region build()
    {
        BorderPane results = new BorderPane();
        results.setPrefSize(680, 600);
        results.setPadding(new Insets(20));
        results.getStylesheets().add(Objects.requireNonNull(this.getClass().getResource("/css/csv-view.css")).toExternalForm());
        results.setTop(headingLabel("Combine CSV Files"));
        results.setCenter(createCenter());
        results.setBottom(createButtons());
        return results;
    }

    public void setFiles(List<Path> files)
    {
        tableView.getItems().clear();
        files.forEach(f -> tableView.getItems().add(new CSVFileRow(f.getFileName().toString(), "Pending")));
    }

    private Node headingLabel(String contents)
    {
        return styledLabel(contents, cssClasses.get("headingLabel"));
    }

    private Node styledLabel(String contents, String styleClass)
    {
        Label label = new Label(contents);
        label.getStyleClass().add(styleClass);
        return label;
    }

    private Node createCenter()
    {
        VBox results = new VBox(6, directoryBox(), filesBox());
        results.setPadding(new Insets(20));
        return results;
    }

    private Node directoryBox()
    {
        return new HBox(6, directoryLabel("Merge csv files located in:"), boundLabel(model.csvDirectoryProperty()));
    }

    private Node filesBox()
    {
        HBox hBox = new HBox(6);

        TableColumn<CSVFileRow, String> fileNameColumn = new TableColumn<>("File Name");
        fileNameColumn.setPrefWidth(400);
        fileNameColumn.setCellValueFactory(new PropertyValueFactory<>("fileName"));

        TableColumn<CSVFileRow, String> statusColumn = new TableColumn<>("Status");
        statusColumn.setPrefWidth(150);
        fileNameColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        tableView.getColumns().add(fileNameColumn);
        tableView.getColumns().add(statusColumn);
        hBox.getChildren().add(tableView);
        return hBox;
    }

    private Node directoryLabel(String contents)
    {
        return styledLabel(contents, cssClasses.get("directoryLabel"));
    }

    private Label boundLabel(StringProperty boundProperty)
    {
        Label label = (Label) styledLabel("", cssClasses.get("boundLabel"));
        label.textProperty().bind(boundProperty);
        return label;
    }

    private Node createButtons()
    {
        Button chooseButton = new Button("Choose");
        chooseButton.setOnAction(e -> chooseHandler.run());

        Button mergeButton = new Button("Merge");
        mergeButton.setOnAction(e -> mergeHandler.run());
        mergeButton.disableProperty().bind(model.okToMergeProperty().not());

        HBox results = new HBox(10, chooseButton, mergeButton);
        results.setAlignment(Pos.CENTER);
        return results;
    }
}
