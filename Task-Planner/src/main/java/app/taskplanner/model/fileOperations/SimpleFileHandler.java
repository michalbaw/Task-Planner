package app.taskplanner.model.fileOperations;

import app.taskplanner.model.notes.Note;
import app.taskplanner.model.notes.NoteBody;
import app.taskplanner.model.notes.NoteMetadata;
import app.taskplanner.model.notes.SimpleNoteBody;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

public class SimpleFileHandler implements FileHandler{
    private final String metadataLocation = "noteList";
    private List<NoteMetadata> noteMetadataList;
    private List<Note> openNotes;
    @Override
    public List<NoteMetadata> loadNotesMetadata() throws IOException {
        Path file = Paths.get(metadataLocation);
        if(! Files.exists(file)){
            noteMetadataList = new LinkedList<>();
            saveNotesMetadata();
            return noteMetadataList;
        }
        ObjectInputStream stream = new ObjectInputStream(Files.newInputStream(file));
        try {
            noteMetadataList = (List<NoteMetadata>) stream.readObject();
        } catch (ClassNotFoundException e) {
            noteMetadataList = new LinkedList<>();
            saveNotesMetadata();
        }
        return noteMetadataList;
    }

    @Override
    public void saveNotesMetadata() throws IOException {
        ObjectOutputStream stream = new ObjectOutputStream(Files.newOutputStream(Paths.get(metadataLocation)));
        stream.writeObject(noteMetadataList);
    }

    @Override
    public NoteBody loadBody(int key) throws IOException {
        NoteBody body;
        String location = "notes" + File.separator + Integer.valueOf(key).toString();
        Path file = Paths.get(location);
        if(! Files.exists(file)){
            return null;
        }
        ObjectInputStream stream = new ObjectInputStream(Files.newInputStream(file));
        try{
            body = (NoteBody) stream.readObject();
        } catch (ClassNotFoundException e){
            return null;
        }
        return body;
    }

    @Override
    public void saveBody(int key, NoteBody body) throws IOException {
        String location = "notes" + File.separator + Integer.valueOf(key).toString();
        ObjectOutputStream stream = new ObjectOutputStream(Files.newOutputStream((Paths.get(location))));
        stream.writeObject(body);
    }

    @Override
    public void removeBody(int key) throws IOException {
        String location = "notes" + File.separator + Integer.valueOf(key).toString();
        File file = new File(location);
        file.delete();
    }
}
