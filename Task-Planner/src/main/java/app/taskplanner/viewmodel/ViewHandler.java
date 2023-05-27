package app.taskplanner.viewmodel;

import app.taskplanner.StartApp;
import app.taskplanner.model.notes.Note;
import app.taskplanner.model.SimpleObservableList;
import app.taskplanner.model.notes.NoteMetadata;
import app.taskplanner.view.ViewController;
import app.taskplanner.model.DataModel;
import app.taskplanner.viewmodel.noteview.NoteController;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ViewHandler {
    private final DataModel dataModel;
    private final Stage primaryStage;

    private List<StageDescr> noteStages;
    public ViewHandler(DataModel dataModel){
        this.dataModel = dataModel;
        this.primaryStage = new Stage();
    }

    public void start(){
        openPrimaryView();
        noteStages = new ArrayList<>();
    }
    public void openPrimaryView()  {
        try {
            FXMLLoader loader = new FXMLLoader(StartApp.class.getResource("list-view.fxml"));
            Parent root = loader.load();//error -> loader is null
            ViewController vc = loader.getController();
            vc.init(this,dataModel);
            Scene listScene = new Scene(root);
            primaryStage.setScene(listScene);
            primaryStage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Note noteFromTitle(String title){
        List<NoteMetadata> notes = dataModel.getNotesMetadata();
        for(NoteMetadata note : notes)
        {
            if(note.getTitle().equals(title))
                return dataModel.openNote();
        }
        return null;
    }
    public void openNote(Note note){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(StartApp.class.getResource("note-view.fxml"));
            Parent root = loader.load();
            NoteController nc = loader.getController();
            nc.init(this,dataModel);
            nc.setup(note);
            Stage noteStage = new Stage();
            noteStage.setTitle(note.getMetadata().getTitle());//tytul notatki
            Scene noteScene = new Scene(root);
            noteStage.setScene(noteScene);
            noteStages.add(new StageDescr(noteStage, note));
            noteStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void closeNote(Note note)
    {
        for(int i=0;i<noteStages.size();i++)
        {
            if(noteStages.get(i).note.equals(note)) {
                noteStages.get(i).stage.close();
                noteStages.remove(i);
                break;
            }
        }
    }
    public ObservableList<String> listNotes()
    {
        List<NoteMetadata> notes = dataModel.getNotesMetadata();
        ObservableList<String> titles = new SimpleObservableList<>();
        for(NoteMetadata n : notes){
            titles.add(n.getTitle());
        }
        return titles;
    }
    public void changeTitle(int noteKey, String title)
    {
         List<NoteMetadata> notes = dataModel.getNotesMetadata();
         for(NoteMetadata n : notes)
         {
             if(noteKey == n.getKey())
             {
                 n.setTitle(title);
                 return;
             }
         }
    }
    public void changeContent(int noteKey, String content)
    {
        List<NoteMetadata> notes = dataModel.getNotesMetadata();
        for(NoteMetadata n : notes)
        {
            if(noteKey == n.getKey())
            {
                n.setNote(content);
                return;
            }
        }
    }

    private static class StageDescr{
        Stage stage;
        Note note;
        StageDescr(Stage stage,Note note){
            this.stage = stage;
            this.note = note;
        }
    }
}
