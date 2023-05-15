package app.taskplanner.viewmodel;

import app.taskplanner.model.DataModel;

import java.io.IOException;

public class ViewModelFactory {

    private ViewHandler viewModelHandler;
    public ViewModelFactory(DataModel dataModel) throws IOException, ClassNotFoundException {
        this.viewModelHandler = new ViewHandler(dataModel);
        viewModelHandler.start();
    }
    public ViewHandler getHandler(){
        return viewModelHandler;
    }
}
