package app.taskplanner.view.boardView;

import app.taskplanner.StartApp;
import app.taskplanner.model.Note;
import app.taskplanner.view.ViewController;
import app.taskplanner.viewmodel.ViewModel;
import app.taskplanner.viewmodel.boardviewmodel.BoardViewModel;
import java.io.PrintStream;
import static javafx.application.Application.launch;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public final class BoardView extends AnchorPane {
    private BoardViewModel boardVM;
    public static ObservableList<Note> notes;

    public void init(ViewModel viewModel) {
        this.boardVM = (BoardViewModel) viewModel;
        setNotes();
    }

    void setNotes( ){
        for(Note note: notes){
            this.addNote();
        }
    }
    void addNote( ){
        try {
            Region root;
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(StartApp.class.getResource("note-view.fxml"));
            root = loader.load();
            root.setMinHeight(100);
            root.setMinSize(100,100);
            this.getChildren().add(root);
        }
        catch (IOException ioE)
        {
            System.out.println("welp");
        }
    }

}
