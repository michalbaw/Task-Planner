package app.taskplanner.viewmodel;

import app.taskplanner.StartApp;
import app.taskplanner.model.DataModel;
import app.taskplanner.model.notes.Note;
import app.taskplanner.service.ChangeModelService;
import app.taskplanner.service.NotificationService;
import app.taskplanner.view.noteview.NoteController;
import app.taskplanner.viewmodel.noteviewmodel.NoteViewModel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SingleNoteHandler implements Handler {
    private final List<StageDescr> noteStages;
    private DataModel dataModel;
    private NotificationService notificationService;
    private ChangeModelService changeModelService;
    private String css;

    public SingleNoteHandler() {
        noteStages = new ArrayList<>();
    }

    public void init(DataModel dataModel, NotificationService ns, ChangeModelService cms, String css) {
        this.dataModel = dataModel;
        this.notificationService = ns;
        this.changeModelService = cms;
        this.css = css;
    }

    public void openNote(Note note) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(StartApp.class.getResource("note-view.fxml"));
            Parent root = loader.load();
            NoteController nc = loader.getController();
            NoteViewModel nvm = new NoteViewModel();
            nvm.setupNote(note);
            notificationService.addNoteViewModel(nvm);
//          nvm.resizeX(-160);
            Stage noteStage = new Stage();
            nvm.init(this, dataModel, changeModelService, notificationService, noteStage);
            nc.init(nvm);
            noteStage.setTitle(note.getMetadata().getTitle());//tytul notatki
            Scene noteScene = new Scene(root);
            noteStage.setScene(noteScene);
//          noteStages.add(new StageDescr(noteStage, note));
            noteScene.getStylesheets().add(css);
            noteStage.show();
            StageDescr stageDescr =  new StageDescr(noteStage,note);
            noteStages.add(stageDescr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeNote(Note note) {
        System.out.println("close note");
        for (int i = 0; i < noteStages.size(); i++) {
            if (noteStages.get(i).note.equals(note)) {
                noteStages.get(i).stage.close();
                noteStages.remove(i);
                break;
            }
        }
    }
    public void closeNote(int key) {
        System.out.println("close note");
        int i = 0;
        while (i<noteStages.size()){
            for (; i < noteStages.size(); i++) {
                if (noteStages.get(i).note.getMetadata().getKey() == key) {
                    noteStages.get(i).stage.close();
                    noteStages.remove(i);
                    break;
                }
            }
        }
    }

    public void closeAllNotes() {
        while (!noteStages.isEmpty()){
            noteStages.get(0).stage.close();
            noteStages.remove(0);
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
