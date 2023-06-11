package app.taskplanner.model;

import app.taskplanner.model.fileOperations.FileHandler;
import app.taskplanner.model.notes.*;

import java.io.IOException;
import java.util.List;

public class SimpleDataModel implements DataModel {
    private final FileHandler fileHandler;
    private final List<NoteMetadata> noteMetadataList;


    SimpleDataModel(FileHandler fileHandler) throws IOException {
        this.fileHandler = fileHandler;
        //noteMetadataList = new LinkedList<>();
        //handInit();
        noteMetadataList = fileHandler.loadNotesMetadata();
    }

    //private void handInit() throws IOException {
    //    addNote("tytulek");
    //    noteMetadataList = fileHandler.loadNotesMetadata();
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
        NoteBody body = new SimpleNoteBody();
        fileHandler.saveBody(metadata.getKey(), body);
        fileHandler.saveNotesMetadata(noteMetadataList);
    }

    @Override
    public void removeNote(int key) throws IOException {
        noteMetadataList.removeIf(noteMetadata -> noteMetadata.getKey() == key);
        fileHandler.saveNotesMetadata(noteMetadataList);
        fileHandler.removeBody(key);
    }

    @Override
    public Note getNote(int key) throws IOException {
        NoteMetadata metadata = getMetadata(key);
        Note note = new SimpleNote(metadata);
        NoteBody body = fileHandler.loadBody(key);
        if (body == null) {
//            System.out.println("loaded body in null");
            body = new SimpleNoteBody();
        }
        note.setNoteBody(body);
        return note;
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
        return fileHandler.loadBody(key);
    }

    @Override
    public List<NoteMetadata> getNotesMetadata() {
        return noteMetadataList;
    }

    private int nextKey() {
        if (noteMetadataList == null)
            return 1;
        if (noteMetadataList.size() == 0) {
            return 1;
        }
        return noteMetadataList.get(noteMetadataList.size() - 1).getKey() + 1;
    }

}
