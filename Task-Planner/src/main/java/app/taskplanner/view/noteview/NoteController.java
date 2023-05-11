package app.taskplanner.view.noteview;
import app.taskplanner.view.ViewHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.KeyEvent;

public class NoteController extends com.example.taskplanner.view.ViewController {

        private ViewHandler viewHandler;
        private Note note;
        private int noteId; //(potem w notatce bedzie jej id)
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


//        @FXML
//        void closeWithoutSaving(ActionEvent event) {
//            viewHandler.closeNote(noteId);
//        }
//
//        @FXML
//        void listOtherNotes(ActionEvent event) {
//                List<String> otherNotes = viewHandler.listNotes();
//                otherNotes.remove("");//tytul tej notatki
//        }

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
//        public void init(ViewHandler viewHandler, VMFactory vmFactory) {

//        }
        public int getId(){
            return noteId; //czyli po id czy tytulach
        }
    }
