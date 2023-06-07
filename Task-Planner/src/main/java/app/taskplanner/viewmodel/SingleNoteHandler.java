package app.taskplanner.viewmodel;

import app.taskplanner.StartApp;
import app.taskplanner.model.DataModel;
import app.taskplanner.model.notes.Note;
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
    private List<StageDescr> noteStages;
    private DataModel dataModel;
    private ViewHandler viewHandler;
    private String css;

    public SingleNoteHandler() {
        noteStages = new ArrayList<>();
    }

    public void init(DataModel dataModel, ViewHandler viewHandler, String css) {
        this.dataModel = dataModel;
        this.viewHandler = viewHandler;
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
//            nvm.resizeX(-160);
            Stage noteStage = new Stage();
            nvm.init(this, dataModel, noteStage);
            nc.init(nvm);
            noteStage.setTitle(note.getMetadata().getTitle());//tytul notatki
            Scene noteScene = new Scene(root);
            noteStage.setScene(noteScene);
//            noteStages.add(new StageDescr(noteStage, note));
            noteScene.getStylesheets().add(css);
            noteStage.show();
            StageDescr stageDescr =  new StageDescr(noteStage,note);
            noteStages.add(stageDescr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeNote(Note note) {
        System.out.println("SIEMANDERO");
        for (int i = 0; i < noteStages.size(); i++) {
            if (noteStages.get(i).note.equals(note)) {
                noteStages.get(i).stage.close();
                noteStages.remove(i);
                break;
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
