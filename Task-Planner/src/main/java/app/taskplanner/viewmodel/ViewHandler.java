package app.taskplanner.viewmodel;

import app.taskplanner.StartApp;
import app.taskplanner.model.notes.Note;
import app.taskplanner.model.notes.SimpleNote;
import app.taskplanner.model.SimpleObservableList;
import app.taskplanner.model.notes.NoteMetadata;
import app.taskplanner.model.DataModel;
import app.taskplanner.service.NotificationService;
import app.taskplanner.view.PrimaryViewController;
import app.taskplanner.view.alerts.DeadlineAlert;
import app.taskplanner.viewmodel.boardviewmodel.BoardViewModel;
import app.taskplanner.viewmodel.listviewmodel.ListViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.Parent;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class ViewHandler implements Handler {
    private final DataModel dataModel;
    private final Stage primaryStage;
    private final String css;
    private final SingleNoteHandler singleNoteHandler;

    ListViewModel listViewModel;
    BoardViewModel boardViewModel;
    NotificationService notificationService;

    public ViewHandler(DataModel dataModel, Stage primaryStage, SingleNoteHandler singleNoteHandler) {
        System.out.println("ViewHandler");
        this.dataModel = dataModel;
        this.primaryStage = primaryStage;
        this.singleNoteHandler = singleNoteHandler;
        listViewModel = new ListViewModel(dataModel, this);
        boardViewModel = new BoardViewModel(dataModel);
        notificationService = new NotificationService();
        notificationService.init(listViewModel,boardViewModel);
        css = Objects.requireNonNull(StartApp.class.getResource("styles.css")).toExternalForm();
        singleNoteHandler.init(dataModel, this, notificationService, css);
    }

    public void start() {
        openPrimaryView();
    }

    public void openPrimaryView() {
        try {
            FXMLLoader loader = new FXMLLoader(StartApp.class.getResource("primary-view.fxml"));
            Parent root = loader.load();
            PrimaryViewController primaryVC = loader.getController();
            primaryVC.init (listViewModel, boardViewModel);
            Scene mainScene = new Scene(root);
            mainScene.getStylesheets().add(css);
            primaryStage.setScene(mainScene);
            primaryStage.resizableProperty().set(false);
            primaryStage.setHeight(mainScene.getHeight()+600);
            primaryStage.setWidth(mainScene.getWidth()+1000);
            Image icon = new Image(Objects.requireNonNull(StartApp.class.getResourceAsStream("main.png")));
            primaryStage.getIcons().add(icon);
            primaryStage.show();
            //for the presentation's sake
            SimpleNote s = new SimpleNote();
            s.getMetadata().setTitle("siemson");
            ObservableList<NoteMetadata> l = FXCollections.observableArrayList();
            l.add(s.getMetadata());
//            if(dataModel.ifUpcomingTask())
//            {
            //todo update to note structure
            DeadlineAlert deadlineAlert = new DeadlineAlert();
            deadlineAlert.show(l);
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //public Note getNoteFromID(int id) {
    //    List<NoteMetadata> notes = dataModel.getNotesMetadata();
    //    for (NoteMetadata note : notes) {
    //        if (note.getKey() == id)
    //            try {
    //                return dataModel.openNote(id);
    //            } catch (IOException ioException) {
    //                System.err.println("wait,no");
    //            }
    //    }
    //    return null;
    //}

    //public void showDeadlineAllert() {
    //
    //
    //}

    public void openNote(Note note) {
        singleNoteHandler.openNote(note);
    }

    /*public ObservableList<String> listNotes() {
        ObservableList<NoteMetadata> notes = (ObservableList<NoteMetadata>) dataModel.getNotesMetadata();
        ObservableList<String> titles = new SimpleObservableList<>();
        for (NoteMetadata n : notes) {
            titles.add(n.getTitle());
        }
        return titles;
    }*/

    /*public void changeTitle(Note note, String title) {
        List<NoteMetadata> notes = dataModel.getNotesMetadata();
        int key = note.getMetadata().getKey();
        for (NoteMetadata n : notes) {
            if (key == n.getKey()) {
                note.getMetadata().setTitle(title);
                notifyVM(); //notification
                return;
            }
        }
    }
   */

   /* public void changeContent(Note note, String content) {
        List<NoteMetadata> notes = dataModel.getNotesMetadata();
        int key = note.getMetadata().getKey();
        for (NoteMetadata n : notes) {
            if (key == n.getKey()) {
                note.getNoteBody().setContent(content);
                notifyVM(); //notification
                return;
            }
        }
    }
    */

    public void addNoteWithTitle(String title) {
        try {
            dataModel.addNote(title);
            notifyVM();
        } catch (IOException ioException) {
            System.err.println("addNoteWithTitle - ioException");
        }
    }

    public void removeNote(int key) {
        try {
            singleNoteHandler.closeNote(key);
            dataModel.removeNote(key);
            notificationService.notifyViewModels(key);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void notifyVM() {
        listViewModel.refreshNotes();
        boardViewModel.refreshNotes();
    }

    public void closeAllNotes() {
        singleNoteHandler.closeAllNotes();
    }
}
