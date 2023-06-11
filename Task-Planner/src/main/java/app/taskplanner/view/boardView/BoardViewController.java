package app.taskplanner.view.boardView;

import app.taskplanner.StartApp;
import app.taskplanner.model.DataModel;
import app.taskplanner.model.notes.Note;
//import app.taskplanner.viewmodel.SimpleNote;
//import app.taskplanner.viewmodel.ViewModel;
import app.taskplanner.model.notes.NoteMetadata;
import app.taskplanner.model.notes.SimpleNote;
import app.taskplanner.service.ChangeModelService;
import app.taskplanner.service.NotificationService;
import app.taskplanner.viewmodel.ViewHandler;
import app.taskplanner.viewmodel.ViewModel;
import app.taskplanner.viewmodel.boardviewmodel.BoardViewModel;

import static javafx.application.Application.launch;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

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
    private Button mixNotes;

    public void init(BoardViewModel boardViewModel, AnchorPane board) {
        this.boardVM = boardViewModel;
        boardViewModel.setBoardViewController(this);
        this.board = board;
        notesProperty.bindBidirectional(this.boardVM.notesProperty());

        saveAll = new Button("Save all");
        board.getChildren().add(saveAll);
        saveAll.setOnAction(event -> {
            saveAllNotes();
        });
        refreshNotes();
    }

    private void saveAllNotes() {
        boardVM.saveAllNotes();
    }

    public void refresh() {
        refreshNotes();
    }

    private void refreshNotes() {
        clearNotes();
        for (int i=0; i<notesProperty.size(); i++){
            addNote(notesProperty.get(i).getMetadata(),i);
        }
    }

    void addNote(NoteMetadata noteInfo, int i) {
        controllers.add(new SimpleNoteController(noteInfo));
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(StartApp.class.getResource("simple-note-view.fxml"));

        try {
            Parent root = loader.load();
            root.setStyle("-fx-background-color: transparent;");
            SimpleNoteController snc = loader.getController();
            snc.init(noteInfo,this, i);
            board.getChildren().add(root);
            makeDraggable(board.getChildren().get(board.getChildren().size()-1));
        } catch (IOException ioException) {
           ioException.printStackTrace();
        }
//        controllers.get(controllers.size() - 1).borderLine();
    }

    void clearNotes(){
        controllers.clear();
        //board.getChildren().clear();
        ObservableList<Node> children = board.getChildren();
        children.removeIf(node -> (node != saveAll && node != mixNotes));
    }

    void makeDraggable(Node n){
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

    public ListProperty<Note> notesProperty() {
        return notesProperty;
    }
    public Note getNote(int x) {
        return boardVM.getNote(x);
    }
}
