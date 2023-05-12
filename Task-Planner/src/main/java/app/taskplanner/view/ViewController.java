package app.taskplanner.view;

import app.taskplanner.model.DataModel;
import app.taskplanner.viewmodel.ViewHandler;

public interface ViewController {
    public void init(ViewHandler viewHandler, DataModel dataModel);
}
