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
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class BoardView implements ViewController {
    private BoardViewModel boardVM;
    public static Stage board;
    public static ObservableList<Note> notes;

    @Override
    public void init(ViewModel viewModel) {
        this.boardVM = (BoardViewModel) viewModel;
        board = new Stage();
        Group root = new Group();
        Scene scene = new Scene(root,1000,600,Color.SKYBLUE);
        board.setTitle("Notes like to move it!");
        setNotes(board);
    }

    void setNotes(Stage board){
        for(Note note: notes){
            addNote(board);
        }
    }
    void addNote(Stage board){
        try {
            Region root;
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(StartApp.class.getResource("note-view.fxml"));
            root = loader.load();
            root.setMinHeight(100);
            root.setMinSize(100,100);
        }
        catch (IOException ioE)
        {
            System.out.println("welp");
        }
    }

}
