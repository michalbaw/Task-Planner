package app.taskplanner.viewmodel;

import app.taskplanner.model.DataModel;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewModelFactory {

    private final ViewHandler viewHandler;

    public ViewModelFactory(DataModel dataModel, Stage primaryStage) throws IOException, ClassNotFoundException {
        this.viewHandler = new ViewHandler(dataModel, primaryStage);
        viewHandler.start();
    }

    public ViewHandler getHandler() {
        return viewHandler;
    }
}
