package app.taskplanner;

import javafx.application.Application;
import javafx.stage.Stage;
import app.taskplanner.model.ModelFactory;
import app.taskplanner.viewmodel.ViewModelFactory;

import java.io.IOException;

public class StartApp extends Application {
    @Override
    public void start(Stage primarystage) throws IOException {
        ModelFactory mf = new ModelFactory();
        ViewModelFactory vf = new ViewModelFactory(mf.getDataModel(), primarystage);
    }

    public static void main(String[] args) {
        launch();
    }
}