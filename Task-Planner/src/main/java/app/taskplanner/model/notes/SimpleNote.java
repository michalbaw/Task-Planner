package app.taskplanner.model.notes;

import app.taskplanner.model.fileOperations.FileHandler;

import java.io.IOException;

public class SimpleNote implements Note {
    private NoteBody body;
    private NoteMetadata metadata;
    public SimpleNote(NoteMetadata metadata) {
        this.metadata = metadata;
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
    public NoteBody getNoteBody(){
        return body;
    }

    @Override
    public void setNoteBody(NoteBody noteBody) {

    }
}
