package app.taskplanner.view;

import app.taskplanner.model.DataModel;

public interface ViewController {
    public void init(ViewHandler viewHandler, DataModel dataModel);
}
