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
import java.util.function.Consumer;

public class ViewBuilder implements Builder<Region>
{
    private final Model model;
    private final Consumer<Runnable> actionHandler;
    private final Map<String, String> cssClasses;

    public ViewBuilder(Model model, Consumer<Runnable> actionHandler)
    {
        cssClasses = new HashMap<>();
        cssClasses.put("headingLabel", "heading-label");
        cssClasses.put("directoryLabel", "directory-label");
        cssClasses.put("boundLabel", "bound-label");

        this.model = model;
        this.actionHandler = actionHandler;
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
        return new HBox(6, directoryLabel("Merge csv files located in:"), boundLabel(model.inDirectoryProperty()));
    }

    private Node filesBox()
    {
        return new HBox();
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

        Button mergeButton = new Button("Merge");

        HBox results = new HBox(10, chooseButton, mergeButton);
        results.setAlignment(Pos.CENTER);
        return results;
    }
}
