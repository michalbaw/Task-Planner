package app.taskplanner.service;

import app.taskplanner.model.DataModel;
import app.taskplanner.model.notes.Note;

import java.io.IOException;

public class ChangeModelService {
    DataModel dataModel;

    public void init(DataModel dataModel) {
        this.dataModel = dataModel;
    }

    public void addNote(String title) {
        try {
            dataModel.addNote(title);
        } catch (IOException ioException) {
            System.err.println("addNote Exception");
        }
    }

    public void removeNote(int key) {
        try {
            dataModel.removeNote(key);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveNote(Note note) {
        try {
            dataModel.saveNote(note);
        } catch(IOException e) {
            System.out.println("saveNote Exception");
            e.printStackTrace();
        }
    }
}
