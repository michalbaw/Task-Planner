package app.taskplanner.model;

import app.taskplanner.model.fileOperations.FileHandler;
import app.taskplanner.model.fileOperations.SimpleFileHandler;

import java.io.IOException;

public class ModelFactory {
    private final DataModel dataModel;

    public ModelFactory() throws IOException {
        FileHandler fileHandler = new SimpleFileHandler();
        dataModel = new SimpleDataModel(fileHandler);
    }

    public DataModel getDataModel() {
        return dataModel;
    }
}
