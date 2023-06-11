package app.taskplanner.view.boardView;

import app.taskplanner.model.notes.NoteMetadata;
import app.taskplanner.viewmodel.boardviewmodel.BoardViewModel;
import javafx.beans.property.Property;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class SimpleNoteController {

    private NoteMetadata selfNote;
    private BoardViewController boardVC;

    private TextArea textArea;
    private Object vmObject;

    @FXML
    private AnchorPane contentPane;

    @FXML
    private TextArea noteContent;

    @FXML
    private TextField noteTitle;

    SimpleNoteController(NoteMetadata noteMetadata) {}
    public SimpleNoteController(){};

    public void init(NoteMetadata noteMetadata,BoardViewController boardVC,int i){
        this.boardVC = boardVC;
        selfNote = noteMetadata;
        noteTitle.setText(selfNote.getTitle());
        //noteTitle.textProperty().bindBidirectional((Property<String>) boardVC.notesProperty().get(i));
        noteContent.setText(boardVC.getNote(selfNote.getKey()).getNoteBody().getContent());
        contentPane.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        contentPane.setBackground(new Background(new BackgroundFill(Color.LAVENDER,CornerRadii.EMPTY, Insets.EMPTY)));
        //note.bindBidirectional(this.boardVM.notesProperty());
        //borderLine();
        noteContent.setWrapText(true);
    }

    @FXML
    void openInSeparateWindow(ActionEvent event) {
    }
    public NoteMetadata getSelfNote() {
        return selfNote;
    }

}

