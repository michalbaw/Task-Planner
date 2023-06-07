package app.taskplanner.viewmodel;

import app.taskplanner.model.DataModel;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewModelFactory {

    private final ViewHandler viewHandler;
    private final SingleNoteHandler singleNoteHandler;

    public ViewModelFactory(DataModel dataModel, Stage primaryStage) throws IOException, ClassNotFoundException {
        System.out.print("ViewModelFactory, ");
        this.singleNoteHandler = new SingleNoteHandler();
        this.viewHandler = new ViewHandler(dataModel, primaryStage, singleNoteHandler);
        viewHandler.start();
    }

    public SingleNoteHandler getSingleNoteHandler() {
        return singleNoteHandler;
    }

    public ViewHandler getHandler() {
        return viewHandler;
    }
}
