package app.taskplanner.viewmodel;

import app.taskplanner.model.DataModel;
import javafx.stage.Stage;

public class ViewModelFactory {

    public ViewModelFactory(DataModel dataModel, Stage primaryStage) {
        SingleNoteHandler singleNoteHandler = new SingleNoteHandler();
        ViewHandler viewHandler = new ViewHandler(dataModel, primaryStage, singleNoteHandler);
        viewHandler.start();
    }

}
