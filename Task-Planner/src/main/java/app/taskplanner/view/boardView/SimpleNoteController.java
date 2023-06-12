package app.taskplanner.view.boardView;

import app.taskplanner.model.notes.Note;
import app.taskplanner.viewmodel.boardviewmodel.BoardViewModel;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class SimpleNoteController {

    private Note selfNote;
    private BoardViewModel boardVM;

    @FXML
    private AnchorPane contentPane;

    @FXML
    private TextArea noteContent;

    @FXML
    private TextField noteTitle;

    public SimpleNoteController(){}

    public void init(Note note, BoardViewModel boardVM){
        this.boardVM = boardVM;
        selfNote = note;

        noteTitle.setText(selfNote.getMetadata().getTitle());
        noteContent.setText(selfNote.getNoteBody().getContent());

        contentPane.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        contentPane.setBackground(new Background(new BackgroundFill(Color.LAVENDER,CornerRadii.EMPTY, Insets.EMPTY)));

        noteContent.setWrapText(true);
    }

    @FXML
    void openInSeparateWindow() {
        boardVM.openInSeparateWindow(selfNote.getMetadata().getKey());
    }

}

