package app.taskplanner.model;

import app.taskplanner.model.fileOperations.FileHandler;
import app.taskplanner.model.fileOperations.SimpleFileHandler;

import java.io.IOException;

public class ModelFactory {
    private DataModel dataModel;
    private FileHandler fileHandler;

    public ModelFactory() throws IOException {
        fileHandler = new SimpleFileHandler();
        dataModel = new SimpleDataModel(fileHandler);

    }
    public DataModel getDataModel() throws IOException {
        return dataModel;
    }
}
