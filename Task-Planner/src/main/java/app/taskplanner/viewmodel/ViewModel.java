package app.taskplanner.viewmodel;

import app.taskplanner.model.DataModel;

public interface ViewModel {
    public void init(ViewHandler viewHandler, DataModel dataModel);
    public void closeWindow(); //signature?
}
