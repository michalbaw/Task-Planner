package app.taskplanner;

import app.taskplanner.view.ViewFactory;
import javafx.application.Application;
import javafx.stage.Stage;
import app.taskplanner.model.ModelFactory;
import app.taskplanner.viewmodel.ViewModelFactory;

import java.io.IOException;

public class StartApp extends Application {
    @Override
    public void start(Stage primarystage) throws IOException, ClassNotFoundException {
        System.out.print("Start App, ");
        ModelFactory mf = new ModelFactory();
        ViewModelFactory viewModelFactory = new ViewModelFactory(mf.getDataModel(), primarystage);
    }

    public static void main(String[] args) {
        launch();
    }
}