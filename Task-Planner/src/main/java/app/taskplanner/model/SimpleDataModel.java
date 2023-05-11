package app.taskplanner.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SimpleDataModel implements DataModel{
    List<Note> noteList;
    String location;
    @Override
    public void loadNotes(String location) throws IOException{
        this.location = location;
        ObjectInputStream objectIn = null;
        try {
            FileInputStream fileIn = new FileInputStream(location);
            objectIn = new ObjectInputStream(fileIn);
        }catch(FileNotFoundException e){
            File newFile = new File(location);
            newFile.createNewFile();
        }
        try {
            noteList = (List<Note>) objectIn;
        }catch(Exception e){
            noteList = null;
        }
        if (noteList == null){
            noteList = new ArrayList<>();
        }
    }

    @Override
    public void saveNotes() throws IOException {
        FileOutputStream fileOut = new FileOutputStream(location);
        ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
        objectOut.writeObject(noteList);
    }

    @Override
    public List<Note> getNotes(){
        return noteList;
    }
}
