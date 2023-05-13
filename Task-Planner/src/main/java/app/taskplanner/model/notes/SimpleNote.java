package app.taskplanner.model.notes;

public class SimpleNote implements Note {
    private String note;
    private NoteMetadata metadata;
    SimpleNote(int key){
        metadata = new SimpleNoteMetadata(key);
    }
    @Override
    public NoteMetadata getMetadata() {
        return metadata;
    }
    @Override
    public String getNoteBody(){
        return note;
    }
    @Override
    public void setNoteBody(String noteBody) {
        this.note = noteBody;
    }
}
