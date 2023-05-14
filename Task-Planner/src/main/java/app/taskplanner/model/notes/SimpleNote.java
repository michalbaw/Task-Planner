package app.taskplanner.model.notes;

import app.taskplanner.model.fileOperations.fileHandler;

import java.io.IOException;

public class SimpleNote implements Note {
    private NoteBody body;
    private NoteMetadata metadata;
    static fileHandler handler;
    SimpleNote(int key) throws IOException {
        metadata = new SimpleNoteMetadata(key);
        body = handler.loadBody(key);
    }
    public static void setFileHandler(fileHandler handler){
        SimpleNote.handler = handler;
    }
    @Override
    public NoteMetadata getMetadata() {
        return metadata;
    }
    @Override
    public NoteBody getNoteBody(){
        return body;
    }
}
