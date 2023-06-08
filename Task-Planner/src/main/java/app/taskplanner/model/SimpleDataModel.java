package app.taskplanner.model;

import app.taskplanner.model.fileOperations.FileHandler;
import app.taskplanner.model.notes.*;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class SimpleDataModel implements DataModel {
    private FileHandler fileHandler;
    private List<NoteMetadata> noteMetadataList;
    private List<Note> openNotes;

    private int nextKey() {
        if (noteMetadataList.size() == 0) {
            return 1;
        }
        return noteMetadataList.get(noteMetadataList.size() - 1).getKey() + 1;
    }

    SimpleDataModel(FileHandler fileHandler) throws IOException {
        this.fileHandler = fileHandler;
        //fileHandler.initialize();
        noteMetadataList = fileHandler.loadNotesMetadata();
        openNotes = new LinkedList<>();
    }

    @Override
    public List<NoteMetadata> getNotesMetadata() {
        return noteMetadataList;
    }

    @Override
    public NoteMetadata getMetadata(int key) {
        for (NoteMetadata noteMetadata : noteMetadataList) {
            if (noteMetadata.getKey() == key) {
                return noteMetadata;
            }
        }
        return null;
    }

    @Override
    public NoteBody getBody(int key) throws IOException {
        NoteBody body = fileHandler.loadBody(key);
        return body;
    }

    private Note getNote(int key) {
        for (Note note : openNotes) {
            if (note.getMetadata().getKey() == key) {
                return note;
            }
        }
        return new SimpleNote(getMetadata(key));
    }


    public void saveAll() throws IOException {
        fileHandler.saveNotesMetadata(noteMetadataList);
        for (Note n : openNotes) {
            fileHandler.saveBody(n.getMetadata().getKey(), n.getNoteBody());
        }
    }
    //@Override
    //public void saveNote(int key) throws IOException {
    //    fileHandler.saveBody(key, getNote(key).getNoteBody());
    //    fileHandler.saveNotesMetadata(noteMetadataList);
    //}
    @Override
    public void saveNote(Note note) throws IOException {
        int key = note.getMetadata().getKey();
        fileHandler.saveBody(key, note.getNoteBody());
        noteMetadataList.removeIf(nm -> nm.getKey() == key);
        noteMetadataList.add(note.getMetadata());
        fileHandler.saveNotesMetadata(noteMetadataList);
    }

    @Override
    public void addNote(String title) throws IOException {
        int key = nextKey();
        NoteMetadata metadata = new SimpleNoteMetadata(key);
        metadata.setTitle(title);
        noteMetadataList.add(metadata);
        fileHandler.saveNotesMetadata(noteMetadataList);
    }

    @Override
    public void removeNote(int key) throws IOException {
        closeNote(key);//useless now
        noteMetadataList.removeIf(noteMetadata -> noteMetadata.getKey() == key);
        fileHandler.saveNotesMetadata(noteMetadataList);
        fileHandler.removeBody(key);
    }

    @Override
    public Note openNote(int key) throws IOException {
        NoteMetadata metadata = getMetadata(key);
        Note note = new SimpleNote(metadata);
        NoteBody body = fileHandler.loadBody(key);
        if (body == null) {
            System.out.println("loaded body in null");
            body = new SimpleNoteBody();
        }
        note.setNoteBody(body);
        return note;
    }

   
    public void closeNote(int key) throws IOException {
        //saveNote(key);
        openNotes.removeIf(note -> note.getMetadata().getKey() == key);
    }
}
