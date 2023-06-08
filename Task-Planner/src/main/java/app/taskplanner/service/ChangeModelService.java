package app.taskplanner.service;

import app.taskplanner.model.DataModel;

import java.io.IOException;

public class ChangeModelService {
    DataModel dataModel;
    NotificationService notificationService;

    public void init(DataModel dataModel, NotificationService notificationService) {
        this.dataModel = dataModel;
        this.notificationService = notificationService;
    }

    public void addNoteWithTitle(String title) {
        try {
            dataModel.addNote(title);
            notificationService.notifyViewModels();
        } catch (IOException ioException) {
            System.err.println("addNoteWithTitle Exception");
        }
    }
    public void removeNote(int key) {
        try {
            dataModel.removeNote(key);

            notificationService.notifyViewModels(key);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}
