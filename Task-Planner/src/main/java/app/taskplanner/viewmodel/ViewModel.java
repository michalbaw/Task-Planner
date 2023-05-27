package app.taskplanner.viewmodel;

import app.taskplanner.model.DataModel;
import javafx.stage.Stage;

public interface ViewModel {
    public void init(ViewHandler viewHandler, DataModel dataModel);

    void init(ViewHandler viewHandler, DataModel dataModel, Stage currentView);

    public void closeWindow(); //signature?
}
