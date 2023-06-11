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

public final class BoardViewController extends AnchorPane {
    private BoardViewModel boardVM;
    public ObservableList<Note> notes;
    private AnchorPane board;
    private ListProperty<Note> notesOnBoardProperty;
    private double maxX; //later reassignment of notes
    private double maxY;
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
        loader.setController(controllers.get(controllers.size() - 1));

        try {
            loader.load();
            System.out.println("setup");
            Parent root = loader.getRoot();
            board.getChildren().add(root);
        } catch (IOException ioException) {
           ioException.printStackTrace();
        }
    }

    void clearNotes() {
        ObservableList<Node> nodes = this.getChildren();
        this.getChildren().clear();
    }

}
