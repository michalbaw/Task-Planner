package app.taskplanner.viewmodel;

import app.taskplanner.StartApp;
import app.taskplanner.model.Note;
import app.taskplanner.model.SimpleObservableList;
import app.taskplanner.view.PrimaryView;
import app.taskplanner.model.DataModel;
import app.taskplanner.view.noteview.NoteController;
import app.taskplanner.viewmodel.noteviewmodel.NoteViewModel;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
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
    public ViewHandler(DataModel dataModel,Stage primaryStage){
        this.dataModel = dataModel;
        this.primaryStage = primaryStage;
        css = Objects.requireNonNull(StartApp.class.getResource("styles.css")).toExternalForm();
    }

    public void start(){
        openPrimaryView();
        noteStages = new ArrayList<>();
    }
    public void openPrimaryView()  {
        try {
            FXMLLoader loader = new FXMLLoader(StartApp.class.getResource("test-view.fxml"));
            Parent root = loader.load();//error -> loader is null
//            ViewModel vm = new ListViewModel(dataModel);
//            vm.init(this,dataModel);
//            ViewController vc = loader.getController();
//            vc.init(vm);
            PrimaryView tc = loader.getController();
            tc.init();
            Scene listScene = new Scene(root);
            listScene.getStylesheets().add(css);
            primaryStage.setScene(listScene);
            primaryStage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Note noteFromTitle(String title){
        List<Note> notes= dataModel.getNotes();
        for(Note note : notes)
        {
            if(note.getTitle().equals(title))
                return note;
        }
        return null;
    }
    public void openNote(Note note){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(StartApp.class.getResource("note-view.fxml"));
            Parent root = loader.load();
            NoteViewModel nvm = new NoteViewModel();
            Stage noteStage = new Stage();
            nvm.init(this,dataModel,noteStage);
            nvm.setupNote(note);
            NoteController nc = loader.getController();
            nc.init(nvm);
            noteStage.setTitle(note.getTitle());//tytul notatki
            Scene noteScene = new Scene(root);
            noteScene.getStylesheets().add(css);
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
        ObservableList<Note> notes = dataModel.getNotes();
        ObservableList<String> titles = new SimpleObservableList<>();
        for(Note n : notes){
            titles.add(n.getTitle());
        }
        return titles;
    }
    public void changeTitle(Note note, String title)
    {
         ObservableList<Note> notes = dataModel.getNotes();
         for(Note n : notes)
         {
             if(n.equals(note))
             {
                 n.setTitle(title);
                 return;
             }
         }
    }
    public void changeContent(Note note, String content)
    {
        ObservableList<Note> notes = dataModel.getNotes();
        for(Note n : notes)
        {
            if(n.equals(note))
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
