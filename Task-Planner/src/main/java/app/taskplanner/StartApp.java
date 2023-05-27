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
        ModelFactory mf = new ModelFactory();
        ViewModelFactory viewModelFactory = new ViewModelFactory(mf.getDataModel(), primarystage);
        ViewFactory viewFactory = new ViewFactory(viewModelFactory.getHandler());
    }

    public static void main(String[] args) {
        launch();
    }
}