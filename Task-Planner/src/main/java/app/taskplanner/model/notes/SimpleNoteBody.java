package app.taskplanner.model.notes;

public class SimpleNoteBody implements NoteBody {
    String content;

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public void setContent(String content) {
        this.content = content;
    }
}
