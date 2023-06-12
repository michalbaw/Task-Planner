package app.taskplanner.model.notes;
import java.time.LocalDate;

public interface NoteMetadata {
    int getKey();

    String getTitle();

    void setTitle(String newTitle);

    LocalDate getDate();

    void setDate(LocalDate date);
}
