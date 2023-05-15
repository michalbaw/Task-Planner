package app.taskplanner.model;

import javafx.collections.ModifiableObservableListBase;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SimpleDataModel implements DataModel{
    ObservableList<Note> noteList = null;
    String location = "noteList";
    @Override
    public void loadNotes() throws IOException{
        ObjectInputStream objectIn = null;
        try {
            FileInputStream fileIn = new FileInputStream(location);
            objectIn = new ObjectInputStream(fileIn);
        }catch(FileNotFoundException e){
            File newFile = new File(location);
            newFile.createNewFile();
        }catch(EOFException ignored){}
        if(objectIn != null) {
            try {
                noteList = (ObservableList<Note>) objectIn;
            } catch (Exception ignored) {}
        }
        if (noteList == null){
            noteList = new SimpleObservableList<>();
        }
    }

    @Override
    public void saveNotes() throws IOException {
        FileOutputStream fileOut = new FileOutputStream(location);
        ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
        objectOut.writeObject(noteList);
    }

    @Override
    public ObservableList<Note> getNotes(){
        return noteList;
    }

    @Override
    public Note addNote (String title){
        System.out.println("tworze");
        Note noteToAdd = new SimpleNote();
        noteToAdd.setTitle(title);
        noteList.add(noteToAdd);
        return noteToAdd;
    }

    @Override
    public void removeNote(Note toRemove){
        noteList.remove(toRemove);
    }
}
