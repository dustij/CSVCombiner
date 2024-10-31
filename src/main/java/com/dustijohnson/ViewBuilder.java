package com.dustijohnson;

import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.util.Builder;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

public class ViewBuilder implements Builder<Region>
{
    private final Model model;
    private final Consumer<Runnable> actionHandler;
    private final Consumer<Runnable> chooseHandler;
    private final Map<String, String> cssClasses;

    public ViewBuilder(Model model, Consumer<Runnable> mergeHandler, Consumer<Runnable> chooseHandler)
    {
        cssClasses = new HashMap<>();
        cssClasses.put("headingLabel", "heading-label");
        cssClasses.put("directoryLabel", "directory-label");
        cssClasses.put("boundLabel", "bound-label");

        this.model = model;
        this.actionHandler = mergeHandler;
        this.chooseHandler = chooseHandler;
    }

    @Override
    public Region build()
    {
        BorderPane results = new BorderPane();
        results.setPrefSize(680, 600);
        results.setPadding(new Insets(20));
        results.getStylesheets().add(Objects.requireNonNull(this.getClass().getResource("/css/csv-view.css"))
                                            .toExternalForm());
        results.setTop(headingLabel("Combine CSV Files"));
        results.setCenter(createCenter());
        results.setBottom(createMergeButton());
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
        VBox results = new VBox(6, directoryInfo(), tableView());
        results.setPadding(new Insets(20));
        return results;
    }

    private Node directoryInfo()
    {
        GridPane gridPane = new GridPane();
        gridPane.setHgap(12);
        gridPane.setVgap(6);

        Button chooseInButton = new Button("Input");
        chooseInButton.setPrefSize(100, 25);
        chooseInButton.setMinWidth(100);
        chooseInButton.setOnAction(e -> chooseHandler.accept(() -> {}));

        Button chooseOutButton = new Button("Output");
        chooseOutButton.setPrefSize(100, 25);
        chooseOutButton.setOnAction(e -> {});

        gridPane.add(chooseInButton, 0, 0);
        gridPane.add(new HBox(6,boundLabel(model.inDirectoryProperty())), 1, 0);
        gridPane.add(chooseOutButton, 0, 1);
        gridPane.add(new HBox(6, boundLabel(model.outDirectoryProperty())), 1, 1);

        return gridPane;
    }

    private Node tableView()
    {
        TableView<FileStatus> tableView = new TableView<>(model.getFileStatuses());
        TableColumn<FileStatus, String> fileColumn = new TableColumn<>("File Name");
        fileColumn.setPrefWidth(400);
        fileColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFileName()));

        TableColumn<FileStatus, String> statusColumn = new TableColumn<>("Status");
        statusColumn.setPrefWidth(150);
        statusColumn.setCellValueFactory(cellData -> cellData.getValue().statusProperty());

        tableView.getColumns().add(fileColumn);
        tableView.getColumns().add(statusColumn);
        return tableView;
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

    private Node createMergeButton()
    {
        Button mergeButton = new Button("Merge");
        mergeButton.setPrefSize(168, 77);
        BooleanProperty mergeRunning = new SimpleBooleanProperty(false);
        mergeButton.disableProperty().bind(Bindings.createBooleanBinding(
                () -> (!model.okToMergeProperty().get() || mergeRunning.get()),
                model.okToMergeProperty(),
                mergeRunning));
        mergeButton.setOnAction(e -> {
            mergeRunning.set(true);
            actionHandler.accept(() -> mergeRunning.set(false));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Merge Complete");
            alert.setHeaderText(null);
            alert.setContentText("The CSV files have been merged.");
            alert.showAndWait();
        });

        HBox results = new HBox(10, mergeButton);
        results.setAlignment(Pos.CENTER);
        return results;
    }
}
