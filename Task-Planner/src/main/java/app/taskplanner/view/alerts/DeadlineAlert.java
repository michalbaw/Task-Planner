package app.taskplanner.view.alerts;

import app.taskplanner.StartApp;
import app.taskplanner.model.notes.NoteMetadata;
import app.taskplanner.model.notes.SimpleNote;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;

public class DeadlineAlert {
    public void show(ObservableList<NoteMetadata> upcoming) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("UPCOMING DEADLINE");
        alert.setHeaderText("You are running out of time! Consider completing those tasks:");
        ListView<String> upcomingList = new ListView<>();
        ListView<String> upcomingDates = new ListView<>();
        GridPane gPane = new GridPane();
        gPane.add(upcomingList, 0, 1);

        //adapt to date in note
        upcomingDates.getItems().addAll(upcoming.stream().map(s -> "19.10.2023").toList());
        upcomingList.getItems().addAll(upcoming.stream().map(NoteMetadata::getTitle).toList());
        upcomingDates.setPrefHeight(15 + 35 * upcoming.size());
        upcomingList.setPrefHeight(15 + 35 * upcoming.size());
        alert.getDialogPane().setContent(gPane);
        alert.getDialogPane().getStylesheets().add(StartApp.class.getResource("styles.css").toExternalForm());
        alert.showAndWait();
    }
}
