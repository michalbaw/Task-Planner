package app.taskplanner.view.alerts;

import app.taskplanner.StartApp;
import app.taskplanner.model.DataModel;
import app.taskplanner.model.notes.NoteMetadata;
import app.taskplanner.model.notes.SimpleNote;
import app.taskplanner.view.ViewFunctions;
import app.taskplanner.view.listview.NoteListCell;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.util.LinkedList;
import java.util.List;

public class DeadlineAlert {
    DataModel dataModel;
    public DeadlineAlert(DataModel dataModel) {
        this.dataModel = dataModel;
    }

    public void showAlert() {
        List<NoteMetadata> upcoming = getUpcomingDeadlines();
        if (upcoming.size() == 0)
            return;
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("UPCOMING DEADLINE");
        alert.setHeaderText("You are running out of time! Consider completing those tasks:");
        ListView<String> upcomingList = new ListView<>();
        ListView<String> upcomingDates = new ListView<>();
        GridPane gPane = new GridPane();
        VBox vBox = new VBox();
        vBox.getChildren().add(upcomingList);
                //adapt to date in note
                        upcomingDates.getItems().addAll(upcoming.stream().map(s -> s.getDate().toString()).toList());
        upcomingList.getItems().addAll(upcoming.stream().map(NoteMetadata::getTitle).toList());
        upcomingDates.setPrefHeight(15 + 35 * upcoming.size());
        upcomingList.setPrefHeight(15 + 35 * upcoming.size());
        alert.getDialogPane().setContent(vBox);
        alert.getDialogPane().getStylesheets().add(StartApp.class.getResource("styles.css").toExternalForm());
        alert.showAndWait();
    }

    private List<NoteMetadata> getUpcomingDeadlines() {
        List<NoteMetadata> upcoming = new LinkedList<>();
        List<NoteMetadata> noteMetadataList = dataModel.getNotesMetadata();
        for (NoteMetadata nm : noteMetadataList){
            if (nm.getDate() != null && ViewFunctions.getDaysToDeadline(nm.getDate())<7)
                upcoming.add(nm);
        }
        return upcoming;
    }
}
