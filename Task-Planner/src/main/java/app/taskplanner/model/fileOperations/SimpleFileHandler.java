package app.taskplanner.model.fileOperations;

import app.taskplanner.model.notes.NoteBody;
import app.taskplanner.model.notes.NoteMetadata;

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
        return  noteMetadataList;
    }

    @Override
    public void saveNotesMetadata() throws IOException {
        ObjectOutputStream stream = new ObjectOutputStream(Files.newOutputStream(Paths.get(metadataLocation)));
        stream.writeObject(noteMetadataList);
    }

    @Override
    public NoteBody loadBody(int key) throws IOException {
        return null;
    }

    @Override
    public void saveBody(int key) throws IOException {

    }

    @Override
    public void removeBody(int key) throws IOException {

    }
}
