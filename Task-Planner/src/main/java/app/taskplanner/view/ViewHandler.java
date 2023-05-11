package com.example.taskplanner.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ViewHandler {
    private VMFactory vmFactory;
    private Stage primaryStage;

    private List<Stage> noteStages;
    public ViewHandler(VMFactory vmFactory){
        this.vmFactory = vmFactory;
        this.primaryStage = new Stage();
    }

    public void start(){
        openView("listView");
    }
    public void openView(String viewName)  {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(/*sciezka do klasy z odpowiednim view*/));
            Parent root = loader.load();
            ViewController vc = loader.getController();
            vc.init(/*params*/);
            Scene listScene = new Scene(root);
            primaryStage.setScene(listScene);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void openNote(int noteId){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(/*sciezka do klasy z odpowiednim view*/));
            Parent root = loader.load();
            Stage noteStage = new Stage();
            noteStage.setTitle("");//tytul notatki
            Scene noteScene = new Scene(root);
            noteStage.setScene(noteScene);
            noteStages.add(noteStage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void closeNote(int noteId)
    {
        for(Stage s : noteStages){
            if(s.getTitle().equals(""))//tytul notatki
                s.close();
        }
    }
    public List<String> listNotes()
    {
        //lista tytulow od noteview?
        return new ArrayList<>();
    }
}
