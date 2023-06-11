package app.taskplanner.view.boardView;

import app.taskplanner.StartApp;
import app.taskplanner.model.notes.Note;
//import app.taskplanner.viewmodel.SimpleNote;
//import app.taskplanner.viewmodel.ViewModel;
import app.taskplanner.model.notes.NoteMetadata;
import app.taskplanner.model.notes.SimpleNote;
import app.taskplanner.viewmodel.ViewModel;
import app.taskplanner.viewmodel.boardviewmodel.BoardViewModel;

import static javafx.application.Application.launch;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class BoardViewController extends AnchorPane {
    int counter=0;
    private BoardViewModel boardVM;
    public ObservableList<Note> notes;
    private AnchorPane board;
    private ListProperty<Note> notesOnBoardProperty;
    private double maxX; //later reassignment of notes
    private double maxY;
    private double startX;
    private double startY;
    private List<SimpleNoteController> controllers;

    public void init(ViewModel viewModel, AnchorPane board) {
        this.boardVM = (BoardViewModel) viewModel;
        this.board = board;
        controllers = new ArrayList<>();
        notesOnBoardProperty = new SimpleListProperty<>();
        notesOnBoardProperty.bindBidirectional(this.boardVM.boardNotes());
        refresh();
        setNotes();
    }

    public void refresh() {
        ObservableList<Note> currentNotes;
        currentNotes = boardVM.getNotes();
        notes = currentNotes;
        clearNotes();
//        setNotes();
    }

    void setNotes() {
        System.out.println("ustawiam" + notesOnBoardProperty.get().size());
        for (Note note : notesOnBoardProperty) {
            this.addNote(note.getMetadata());
        }
    }

    void addNote(NoteMetadata noteInfo) {
        controllers.add(new SimpleNoteController(noteInfo));
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(StartApp.class.getResource("simple-note-view.fxml"));

        try {
            Parent root = loader.load();
            root.setStyle("-fx-background-color: transparent;");
            SimpleNoteController snc = loader.getController();
            snc.init(noteInfo,boardVM);
            System.out.println("setup");
            board.getChildren().add(root);
            makeDraggable(board.getChildren().get(board.getChildren().size()-1));
        } catch (IOException ioException) {
           ioException.printStackTrace();
        }
//        controllers.get(controllers.size() - 1).borderLine();
    }
    void makeDraggable(Node n){
        Random rand = new Random();
        double x = rand.nextDouble();
        double y = rand.nextDouble();
        System.out.println(x + " " + y);
        n.setLayoutX(x * 500);
        n.setLayoutY(y * 300);
        n.setOnMousePressed(e -> {
            n.toFront();
            n.toFront();
            startX = e.getSceneX() - n.getTranslateX();
            startY = e.getSceneY() - n.getTranslateY();
        });
        n.setOnMouseDragged(e -> {
            n.setTranslateX(e.getSceneX()-startX);
            n.setTranslateY(e.getSceneY() - startY);
        });
    }
    void clearNotes() {
        ObservableList<Node> nodes = this.getChildren();
        this.getChildren().clear();
    }

}
