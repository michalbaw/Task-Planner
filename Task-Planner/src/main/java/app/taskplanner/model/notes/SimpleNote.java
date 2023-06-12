package app.taskplanner.model.notes;

public class SimpleNote implements Note {
    private NoteBody body;
    private NoteMetadata metadata;
    public SimpleNote(){
       metadata = new SimpleNoteMetadata(0);
    }
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
