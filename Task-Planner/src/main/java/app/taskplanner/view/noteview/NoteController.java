package app.taskplanner.view.noteview;
import app.taskplanner.model.DataModel;
import app.taskplanner.model.Note;
import app.taskplanner.view.ViewController;
import app.taskplanner.view.ViewHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.KeyEvent;

import java.util.List;

public class NoteController implements ViewController {

        ViewHandler viewHandler;
        DataModel dataModel;
        @FXML
        private MenuItem closeNoSave;

        @FXML
        private Menu openAnother;

        @FXML
        private ProgressBar progressBar;

        @FXML
        private Button saveAndClose;

        @FXML
        private Label statusMessage;


        @FXML
        void closeWithoutSaving(ActionEvent event) {
            viewHandler.closeNote(this);
        }

        @FXML
        void listOtherNotes(ActionEvent event) {
                List<Note> otherNotes = dataModel.getNotes();
//                otherNotes.remove();//tytul tej notatki
        }

        @FXML
        void saveAndClose(ActionEvent event) {
            this.saveContent(null);
            this.saveTitle(null);
//            this.closeWithoutSaving(event);
        }

        @FXML
        void saveContent(KeyEvent event) {
            if(event!= null){
                //do sth

            }
        }

        @FXML
        void saveTitle(KeyEvent event) {
            if(event != null){
                //do sth
            }
        }
    @Override
    public void init(ViewHandler viewHandler,DataModel dataModel) {
        this.viewHandler = viewHandler;
        this.dataModel = dataModel;

    }
//        public int getId(){
//            return noteId; //czyli po id czy tytulach
//        }

}
