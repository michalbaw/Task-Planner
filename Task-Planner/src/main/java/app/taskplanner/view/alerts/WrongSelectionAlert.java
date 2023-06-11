package app.taskplanner.view.alerts;

import app.taskplanner.StartApp;
import app.taskplanner.model.notes.NoteMetadata;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;

public class WrongSelectionAlert {
    public void show(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Select correct item");
        alert.setHeaderText("  You selected outside of list of notes.");
        alert.setContentText("This is a STRONG WARNING.");
        alert.getDialogPane().setPrefHeight(0);
        alert.getDialogPane().getStylesheets().add(StartApp.class.getResource("styles.css").toExternalForm());
        Label content = (Label) alert.getDialogPane().lookup(".content.label");
        content.setStyle("-fx-background-color: red");
        alert.showAndWait();
    }
}
