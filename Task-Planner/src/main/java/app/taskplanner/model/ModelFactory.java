package app.taskplanner.model;

import java.io.IOException;

public class ModelFactory {
    private DataModel dataModel;

    public ModelFactory() throws IOException {
        //dataModel = new SimpleDataModel();
    }
    public DataModel getDataModel() throws IOException {
        if (dataModel == null) {
            //dataModel = new SimpleDataModel();
        }
        return dataModel;
    }
}
