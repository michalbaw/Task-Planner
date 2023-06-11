package app.taskplanner.view.alerts;

import app.taskplanner.StartApp;
import app.taskplanner.model.notes.NoteMetadata;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;

public class SelectionAlert {
    private Alert alert;
    public SelectionAlert(){
         alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Please select an item");
        alert.setHeaderText("  To perform this action you need to select at least one note.");

    }
    public SelectionAlert(String header){
        alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Please don't do that here");
        alert.setHeaderText(header);
    }
    public void show(){
        alert.setContentText("This is a SOFT WARNING. The serious ones are yet to come...   ");
        alert.getDialogPane().setPrefHeight(0);
        alert.getDialogPane().getStylesheets().add(StartApp.class.getResource("styles.css").toExternalForm());
        alert.showAndWait();
    }
}
