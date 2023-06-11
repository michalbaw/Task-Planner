package app.taskplanner.viewmodel;

import app.taskplanner.StartApp;
import app.taskplanner.model.notes.Note;
import app.taskplanner.model.notes.SimpleNote;
import app.taskplanner.model.notes.NoteMetadata;
import app.taskplanner.model.DataModel;
import app.taskplanner.service.ChangeModelService;
import app.taskplanner.service.NotificationService;
import app.taskplanner.view.PrimaryViewController;
import app.taskplanner.view.alerts.DeadlineAlert;
import app.taskplanner.viewmodel.boardviewmodel.BoardViewModel;
import app.taskplanner.viewmodel.calendarviewmodel.CalendarViewModel;
import app.taskplanner.viewmodel.listviewmodel.ListViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.Parent;

import java.io.IOException;
import java.util.Objects;

public class ViewHandler implements Handler {
    private final DataModel dataModel;
    private final Stage primaryStage;
    private final String css;
    private final SingleNoteHandler singleNoteHandler;

    private final ListViewModel listVM;
    private final BoardViewModel boardVM;
    private final CalendarViewModel calendarVM;

    public ViewHandler(DataModel dataModel, Stage primaryStage, SingleNoteHandler singleNoteHandler) {
        this.dataModel = dataModel;
        this.primaryStage = primaryStage;
        this.singleNoteHandler = singleNoteHandler;

        listVM = new ListViewModel(dataModel, this);
        boardVM = new BoardViewModel(dataModel);
        calendarVM = new CalendarViewModel(dataModel,this);
        NotificationService notificationService = new NotificationService();
        notificationService.init(listVM,boardVM);
        ChangeModelService changeModelService = new ChangeModelService();
        changeModelService.init(dataModel);

        listVM.init(changeModelService, notificationService);
        boardVM.init(changeModelService, notificationService);
        calendarVM.init(changeModelService,notificationService);

        css = Objects.requireNonNull(StartApp.class.getResource("styles.css")).toExternalForm();
        singleNoteHandler.init(dataModel, notificationService, changeModelService, css);
    }

    public void start() {
        openPrimaryView();
    }

    public void openPrimaryView() {
        try {
            FXMLLoader loader = new FXMLLoader(StartApp.class.getResource("primary-view.fxml"));
            Parent root = loader.load();
            PrimaryViewController primaryVC = loader.getController();
            primaryVC.init (listVM, boardVM,calendarVM);
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
            //SimpleNote s = new SimpleNote();
            //s.getMetadata().setTitle("siemson");
            //ObservableList<NoteMetadata> l = FXCollections.observableArrayList();
            //l.add(s.getMetadata());

            //todo update to note structure
            DeadlineAlert deadlineAlert = new DeadlineAlert(dataModel);
            deadlineAlert.showAlert();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openNote(int key) {
        Note note = null;
        try {
            note = dataModel.getNote(key);
        } catch (IOException e){
            System.out.println("getNote Exception");
        }
        singleNoteHandler.openNote(note);
    }

    public void closeNote(int key) {
        singleNoteHandler.closeNoteByKey(key);
    }

    public void closeAllNotes() {
        singleNoteHandler.closeAllNotes();
    }
}
