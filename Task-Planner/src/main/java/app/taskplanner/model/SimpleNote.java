package app.taskplanner.model;

public class SimpleNote implements Note{
    private String note;
    private String title;
    public String getNote(){
        return note;
    }
    public String getTitle(){
        return title;
    }

    @Override
    public void setNote(String noteBody) {
        this.note = noteBody;
    }

    public void setTitle(String newTitle){
        this.title = newTitle;
    }
}
