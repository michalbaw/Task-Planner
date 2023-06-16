package app.taskplanner.view.boardView;

import app.taskplanner.StartApp;
import app.taskplanner.model.notes.Note;
import app.taskplanner.model.notes.SimpleNote;
import app.taskplanner.viewmodel.boardviewmodel.BoardViewModel;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import java.util.*;

public final class BoardViewController extends AnchorPane {

    private final ListProperty<Note> notesProperty = new SimpleListProperty<>(FXCollections.observableArrayList());
    private final List<SimpleNoteController> controllers = new LinkedList<>();
    private BoardViewModel boardVM;
    private AnchorPane board;
    private double startX;
    private double startY;
    private Button saveAll;

    public void init(BoardViewModel boardViewModel, AnchorPane board) {
        this.boardVM = boardViewModel;
        boardViewModel.setBoardViewController(this);
        this.board = board;
        notesProperty.bindBidirectional(this.boardVM.notesProperty());

        saveAll = new Button("Save all and mix");
        board.getChildren().add(saveAll);
        saveAll.setOnAction(event -> saveAllNotes());
        refreshNotes();
    }

    private void saveAllNotes() {
        for (SimpleNoteController snc : controllers) {
            snc.saveNote();
        }
        boardVM.useNotification();
    }

    public void refresh() {
        refreshNotes();
    }

    private void refreshNotes() {
        clearNotes();
        for (Note note : notesProperty) {
            addNote(note);
        }
    }

    void addNote(Note note) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(StartApp.class.getResource("simple-note-view.fxml"));

        try {
            Parent root = loader.load();
            root.setStyle("-fx-background-color: transparent;");
            SimpleNoteController snc = loader.getController();
            snc.init(note, boardVM);
            controllers.add(snc);
            board.getChildren().add(root);
            makeDraggable(board.getChildren().get(board.getChildren().size() - 1));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }

    void clearNotes() {
        ObservableList<Node> children = board.getChildren();
        children.removeIf(node -> (node != saveAll));
        controllers.clear();
    }

    void makeDraggable(Node n) {
        Random rand = new Random();
        double x = rand.nextDouble();
        double y = rand.nextDouble();
        n.setLayoutX(x * 500);
        n.setLayoutY(y * 300);
        n.setOnMousePressed(e -> {
            n.toFront();
            n.toFront();
            startX = e.getSceneX() - n.getTranslateX();
            startY = e.getSceneY() - n.getTranslateY();
        });
        n.setOnMouseDragged(e -> {
            n.setTranslateX(e.getSceneX() - startX);
            n.setTranslateY(e.getSceneY() - startY);
        });
    }
}
