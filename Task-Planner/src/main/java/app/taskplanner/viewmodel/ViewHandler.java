package app.taskplanner.viewmodel;

import app.taskplanner.StartApp;
import app.taskplanner.model.notes.Note;
import app.taskplanner.model.SimpleObservableList;
import app.taskplanner.model.notes.NoteMetadata;
import app.taskplanner.view.PrimaryViewController;
import app.taskplanner.view.ViewController;
import app.taskplanner.model.DataModel;
import app.taskplanner.viewmodel.noteview.NoteController;
import app.taskplanner.viewmodel.noteviewmodel.NoteViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Parent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ViewHandler {
    private final DataModel dataModel;
    private final Stage primaryStage;
    private final String css;
    private List<StageDescr> noteStages;

    public ViewHandler(DataModel dataModel, Stage primaryStage) {
        this.dataModel = dataModel;
        this.primaryStage = primaryStage;
        css = Objects.requireNonNull(StartApp.class.getResource("styles.css")).toExternalForm();
    }

    public void start() {
        openPrimaryView();
        noteStages = new ArrayList<>();
    }

    public void openPrimaryView() {
        try {
            FXMLLoader loader = new FXMLLoader(StartApp.class.getResource("primary-view.fxml"));
            Parent root = loader.load();//error -> loader is null
            PrimaryViewController tc = loader.getController();
            tc.init(dataModel);
            Scene mainScene = new Scene(root);
            mainScene.getStylesheets().add(css);
            primaryStage.setScene(mainScene);
            primaryStage.resizableProperty().set(false);
            primaryStage.setHeight(mainScene.getHeight() + 450);
            Image icon = new Image(Objects.requireNonNull(StartApp.class.getResourceAsStream("main.png")));
            primaryStage.getIcons().add(icon);
            primaryStage.show();
            SimpleNote s = new SimpleNote();
            s.setTitle("siemson");
            ObservableList<SimpleNote> l = FXCollections.observableArrayList();
            l.add(s);
//            if(dataModel.ifUpcomingTask())
//            {
                showDeadlineAlert(l);
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Note getNoteFromID(int id){
        List<NoteMetadata> notes = dataModel.getNotesMetadata();
        for(NoteMetadata note : notes)
        {
            if(note.getKey() == id)
                try{

                return dataModel.openNote(id);
                }
                catch (IOException ioException) {
                    System.err.println("wait,no");
                }
        }
        return null;
    }

    public void openNote(Note note) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(StartApp.class.getResource("note-view.fxml"));
            Parent root = loader.load();
            NoteViewModel nvm = new NoteViewModel();
            Stage noteStage = new Stage();
            nvm.init(this, dataModel, noteStage);
            nvm.setupNote(note);
            NoteController nc = loader.getController();
            nc.init(this,dataModel);
            nc.setup(note);
            noteStage.setTitle(note.getMetadata().getTitle());//tytul notatki
            Scene noteScene = new Scene(root);
            noteScene.getStylesheets().add(css);
            noteStage.setScene(noteScene);
            noteStages.add(new StageDescr(noteStage, note));
            noteStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeNote(Note note) {
        for (int i = 0; i < noteStages.size(); i++) {
            if (noteStages.get(i).note.equals(note)) {
                noteStages.get(i).stage.close();
                noteStages.remove(i);
                break;
            }
        }
    }
    private void showDeadlineAlert(ObservableList<SimpleNote> upcoming){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("UPCOMING DEADLINE");
        alert.setHeaderText("You are running out of time! Consider completing those tasks:");
        ListView<String> upcomingList = new ListView<>();
        ListView<String> upcomingDates = new ListView<>();
        GridPane gPane = new GridPane();
        gPane.add(upcomingList,0,1);
        gPane.add(upcomingDates,1,1);
        upcomingDates.getItems().addAll(upcoming.stream().map(s -> "19.10.2023").toList());
        upcomingList.getItems().addAll(upcoming.stream().map(SimpleNote::getTitle).toList());
        upcomingDates.setPrefHeight(10 +25*upcoming.size());
        upcomingList.setPrefHeight(10 +25*upcoming.size());
        alert.getDialogPane().setContent(gPane);
        alert.getDialogPane().getStylesheets().add(StartApp.class.getResource("styles.css").toExternalForm());
        alert.showAndWait();
    }
    public ObservableList<String> listNotes() {
        ObservableList<NoteMetadata> notes = (ObservableList<NoteMetadata>) dataModel.getNotesMetadata();
        ObservableList<String> titles = new SimpleObservableList<>();
        for(NoteMetadata n : notes){
            titles.add(n.getTitle());
        }
        return titles;
    }
    public void changeTitle(Note note, String title)
    {
         List<NoteMetadata> notes = dataModel.getNotesMetadata();
         int key = note.getMetadata().getKey();
         for(NoteMetadata n : notes)
         {
             if(key == n.getKey())
             {
                 note.getMetadata().setTitle(title);
                 return;
             }
         }
    }
    public void changeContent(Note note, String content)
    {
        List<NoteMetadata> notes = dataModel.getNotesMetadata();
        int key = note.getMetadata().getKey();
        for(NoteMetadata n : notes)
        {
            if(key == n.getKey())
            {
                note.getNoteBody().setContent(content);
                return;
            }
        }
    }

    private static class StageDescr {
        Stage stage;
        Note note;

        StageDescr(Stage stage, Note note) {
            this.stage = stage;
            this.note = note;
        }
    }
}
