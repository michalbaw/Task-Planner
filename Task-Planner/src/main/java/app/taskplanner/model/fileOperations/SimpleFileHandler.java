package app.taskplanner.model.fileOperations;

import app.taskplanner.model.notes.NoteBody;
import app.taskplanner.model.notes.NoteMetadata;
import app.taskplanner.model.notes.SimpleNoteBody;
import app.taskplanner.model.notes.SimpleNoteMetadata;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

public class SimpleFileHandler implements FileHandler {
    private final String prefixDir = "src/main/resources/app/taskplanner";
    private final String prefixNotes = prefixDir + "/notes/notes";
    private final String metadataLocation = prefixDir + "/noteList";

    public SimpleFileHandler() throws IOException {
        Path dir = Paths.get(prefixDir + "/notes");
        if (Files.notExists(dir)) {
            Files.createDirectory(dir);
        }
    }

    @Override
    public List<NoteMetadata> loadNotesMetadata() throws IOException {
        System.out.print("LoadNotedMetadata, ");
        List<NoteMetadata> noteMetadataList = new LinkedList<>();
        Path file = Paths.get(metadataLocation);
        if (!Files.exists(file)) {
            Files.createFile(file);
            saveNotesMetadata(noteMetadataList);
            return noteMetadataList;
        }
        try {
            ObjectInputStream stream = new ObjectInputStream(Files.newInputStream(file));
            noteMetadataList = (List<NoteMetadata>) stream.readObject();
        } catch (ClassNotFoundException e) {
            noteMetadataList = new LinkedList<>();
            saveNotesMetadata(noteMetadataList);
        }
        return noteMetadataList;
    }

    @Override
    public void saveNotesMetadata(List<NoteMetadata> noteMetadataList) throws IOException {
        ObjectOutputStream stream = new ObjectOutputStream(Files.newOutputStream(Paths.get(metadataLocation)));
        stream.writeObject(noteMetadataList);
    }

    @Override
    public NoteBody loadBody(int key) throws IOException {
        NoteBody body;
        String location = prefixNotes + Integer.valueOf(key).toString();
        Path file = Paths.get(location);
        if (!Files.exists(file)) {
            return null;
        }
        ObjectInputStream stream = new ObjectInputStream(Files.newInputStream(file));
        try {
            body = (NoteBody) stream.readObject();
        } catch (ClassNotFoundException e) {
            return null;
        }
        return body;
    }

    @Override
    public void saveBody(int key, NoteBody body) throws IOException {
        System.out.println("saveBody " + key + " " + body.getContent());
        String location = prefixNotes + Integer.valueOf(key).toString();
        System.out.println("location " + location);
        ObjectOutputStream stream = new ObjectOutputStream(Files.newOutputStream((Paths.get(location))));
        stream.writeObject(body);
        stream.flush();
    }

    @Override
    public void removeBody(int key) throws IOException {
        String location = prefixNotes + Integer.valueOf(key).toString();
        Path file = Paths.get(location);
        Files.deleteIfExists(file);
    }

    @Override
    public void initialize() throws IOException {
        System.out.print("initialize, ");
        int key = 0;
        NoteBody body = new SimpleNoteBody();
        body.setContent("Initial content");
        saveBody(0,body);
        loadBody(0);

        NoteMetadata metadata = new SimpleNoteMetadata(key);
        metadata.setTitle("title");
        List<NoteMetadata> initialList = new LinkedList<>();
        initialList.add(metadata);
        saveNotesMetadata(initialList);
    }
}
