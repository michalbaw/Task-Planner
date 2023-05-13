package app.taskplanner.model.notes;

public class SimpleNoteMetadata implements NoteMetadata {
    private int key;
    private String title;

    @Override
    public int getKey() {
        return key;
    }

    SimpleNoteMetadata(int key){
        this.key = key;
    }
    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String newTitle) {
        this.title = newTitle;
    }
}
