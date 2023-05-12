package app.taskplanner.view;

import app.taskplanner.model.Note;
import app.taskplanner.view.noteview.NoteController;
import app.taskplanner.viewmodel.ViewModelFactory;
import app.taskplanner.model.DataModel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ViewHandler {
    private DataModel dataModel;
    private ViewModelFactory viewModelFactory;
    private Stage primaryStage;

    private List<StageDescr> noteStages;
    public ViewHandler(ViewModelFactory vmFactory){
        this.viewModelFactory = vmFactory;
        this.primaryStage = new Stage();
    }

    public void start(){
        openPrimaryView("listView");
        noteStages = new ArrayList<>();
    }
    public void init(DataModel dataModel, ViewModelFactory viewModelFactor){
        this.dataModel = dataModel;
        this.viewModelFactory = viewModelFactor;
    }
    public void openPrimaryView(String viewName)  {
        try {
            FXMLLoader loader = new FXMLLoader();
            //wywala sie location
            System.out.println("tutaj");
            loader.setLocation(getClass().getResource("list-view.fxml"));//dlaczego to nie dziala?
            System.out.println(loader.getLocation());//nullptr exception
            Parent root = loader.load();
            ViewController vc = loader.getController();
            vc.init(this,dataModel);
            Scene listScene = new Scene(root);
            primaryStage.setScene(listScene);
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
            loader.setLocation(getClass().getResource("note-view.fxml"));
            Parent root = loader.load();
            NoteController nc = loader.getController();
            nc.init(this,dataModel);
            nc.setup(note);
            Stage noteStage = new Stage();
            noteStage.setTitle(note.getTitle());//tytul notatki
            Scene noteScene = new Scene(root);
            noteStage.setScene(noteScene);
            noteStages.add(new StageDescr(noteStage,note));
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
    public List<String> listNotes()
    {
        List<Note> notes = dataModel.getNotes();
        List<String> titles = new ArrayList<>();
        for(Note n : notes){
            titles.add(n.getTitle());
        }
        return titles;
    }

    private class StageDescr{
        Stage stage;
        Note note;
        StageDescr(Stage stage,Note note){
            this.stage = stage;
            this.note = note;
        }
    }
}
