package app.taskplanner.model.notes;

import app.taskplanner.model.fileOperations.FileHandler;

import java.io.IOException;
import java.util.List;

public class SimpleNote implements Note {
    private NoteBody body;
    private NoteMetadata metadata;
    public SimpleNote(){
       metadata = new SimpleNoteMetadata(0);
       //metadata = null;
    }
    public SimpleNote(NoteMetadata metadata) {
        this.metadata = metadata;
        //this.metadata = null;
    }
    @Override
    public NoteMetadata getMetadata() {
        return metadata;
    }
    @Override
    public void setMetadata(NoteMetadata metadata) {
        this.metadata = metadata;
    }
    @Override
    public NoteBody getNoteBody() {
        return body;
    }
    @Override
    public void setNoteBody(NoteBody noteBody) {
        this.body = noteBody;
    }
    @Override
    public String toString(){
        return metadata.getTitle();
    }
}
