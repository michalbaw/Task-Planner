package app.taskplanner.viewmodel;

import app.taskplanner.model.DataModel;

import java.io.IOException;

public class ViewModelFactory {

    public ViewModelFactory(DataModel dataModel) throws IOException, ClassNotFoundException {
        ViewHandler viewModelHandler = new ViewHandler(dataModel);
        viewModelHandler.start();
    }
}
