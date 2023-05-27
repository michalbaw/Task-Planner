package app.taskplanner.model;

import app.taskplanner.model.fileOperations.FileHandler;
import app.taskplanner.model.notes.Note;
import app.taskplanner.model.notes.NoteMetadata;
import app.taskplanner.model.notes.SimpleNote;
import app.taskplanner.model.notes.SimpleNoteMetadata;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class SimpleDataModel implements DataModel{
    private FileHandler fileHandler;
    private List<NoteMetadata> noteMetadataList;
    private List<Note> openNotes;
    private int nextKey(){
        if(noteMetadataList.size() == 0){
            return 1;
        }
        return noteMetadataList.get(noteMetadataList.size()-1).getKey()+1;
    }

    SimpleDataModel(FileHandler fileHandler) throws IOException{
        this.fileHandler = fileHandler;
        noteMetadataList = fileHandler.loadNotesMetadata();
        openNotes = new LinkedList<>();
    }
    @Override
    public List<NoteMetadata> getNotesMetadata() {
        return noteMetadataList;
    }
    private NoteMetadata getMetadata(int key){
        for (NoteMetadata noteMetadata : noteMetadataList) {
            if (noteMetadata.getKey() == key) {
                return noteMetadata;
            }
        }
        return null;
    }
    private Note getNote(int key){
        for(Note note : openNotes){
            if(note.getMetadata().getKey() == key){
                return note;
            }
        }
        return new SimpleNote(getMetadata(key));
    }

    @Override
    public void saveAll() throws IOException {
        fileHandler.saveNotesMetadata();
        for(Note n : openNotes){
            fileHandler.saveBody(n.getMetadata().getKey(), n.getNoteBody());
        }
    }

    @Override
    public void saveNote(int key) throws IOException {
        fileHandler.saveBody(key, getNote(key).getNoteBody());
        fileHandler.saveNotesMetadata();
    }

    @Override
    public void addNote(String title) throws IOException {
        int key = nextKey();
        NoteMetadata metadata = new SimpleNoteMetadata(key);
        metadata.setTitle(title);
        noteMetadataList.add(metadata);
        fileHandler.saveNotesMetadata();
    }

    @Override
    public void removeNote(int key) throws IOException {
        closeNote(key);
        fileHandler.removeBody(key);
        noteMetadataList.removeIf(noteMetadata -> noteMetadata.getKey() == key);
    }

    @Override
    public Note openNote(int key) throws IOException {
        NoteMetadata metadata = getMetadata(key);
        Note note = new SimpleNote(metadata);
        NoteBody body = fileHandler.loadBody(key);
        if(body == null){
            body = new SimpleNoteBody();
        }
        note.setNoteBody(body);
        return note;
    }

    @Override
    public void closeNote(int key) throws IOException {
        saveNote(key);
        openNotes.removeIf(note -> note.getMetadata().getKey() == key);
    }
}
