package app.taskplanner.viewmodel.noteview;
import app.taskplanner.model.DataModel;
import app.taskplanner.model.Note;
import app.taskplanner.view.ViewController;
import app.taskplanner.viewmodel.ViewHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.KeyEvent;

import java.io.IOException;
import java.util.List;

public class NoteController implements ViewController {

        private ViewHandler viewHandler;
        private DataModel dataModel;
        private Note currentNote;
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
            viewHandler.closeNote(currentNote);
        }

        @FXML
        void listOtherNotes(ActionEvent event) {
                List<Note> otherNotes = dataModel.getNotes();
//                otherNotes.remove();//tytul tej notatki
        }

        @FXML
        void saveAndClose(ActionEvent event)  {
//            this.saveContent(null);
//            this.saveTitle(null);
            try {
                dataModel.saveNotes();
            }
            catch (IOException e){
                System.out.println("saving failed");
            }
            this.closeWithoutSaving(event);
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

    public void setup(Note currentNote){
            this.currentNote = currentNote;
    }
//        public int getId(){
//            return noteId; //czyli po id czy tytulach
//        }

}
