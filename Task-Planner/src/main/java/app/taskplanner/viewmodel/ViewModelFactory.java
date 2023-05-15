package app.taskplanner.viewmodel;

import app.taskplanner.model.DataModel;

import java.io.IOException;

public class ViewModelFactory {

    private ViewHandler viewHandler;
    public ViewModelFactory(DataModel dataModel) throws IOException, ClassNotFoundException {
        this.viewHandler = new ViewHandler(dataModel);
        viewHandler.start();
    }
    public ViewHandler getHandler(){
        return viewHandler;
    }
}
