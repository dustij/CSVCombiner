package com.dustijohnson;

import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.Builder;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CSVVIewBuilder implements Builder<Region>
{
    private final CSVModel model;
    private final Runnable mergeHandler;
    private final Runnable chooseHandler;
    private final Map<String, String> cssClasses;

    public CSVVIewBuilder(CSVModel model, Runnable mergeHandler, Runnable chooseHandler)
    {
        cssClasses = new HashMap<>();
        cssClasses.put("headingLabel", "heading-label");
        cssClasses.put("directoryLabel", "directory-label");
        cssClasses.put("boundLabel", "bound-label");

        this.model = model;
        this.mergeHandler = mergeHandler;
        this.chooseHandler = chooseHandler;
    }

    @Override
    public Region build()
    {
        BorderPane results = new BorderPane();
        results.getStylesheets().add(Objects.requireNonNull(this.getClass().getResource("/css/csv-view.css")).toExternalForm());
        results.setTop(headingLabel("Combine CSV Files"));
        results.setCenter(createCenter());
        results.setBottom(createButtons());
        return results;
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
        return new HBox(); // TODO: display files in chosen directory
    }

    private Node directoryLabel(String contents)
    {
        return styledLabel(contents, cssClasses.get("directoryLabel"));
    }

    private Node boundLabel(StringProperty boundProperty)
    {
        Node label = styledLabel("", cssClasses.get("boundLabel"));
        label.accessibleTextProperty().bind(boundProperty);
        return label;
    }

    private Node createButtons()
    {
        Button chooseButton = new Button("Choose");
        chooseButton.setOnAction(e -> chooseHandler.run());
        Button mergeButton = new Button("Merge");
        mergeButton.setOnAction(e -> mergeHandler.run());

        HBox results = new HBox(10, chooseButton, mergeButton);
        results.setAlignment(Pos.CENTER);
        return results;
    }
}
