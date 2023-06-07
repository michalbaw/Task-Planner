package app.taskplanner.view.listview;
import app.taskplanner.model.notes.NoteMetadata;
import javafx.scene.control.ListCell;

public class NoteListCell extends ListCell<NoteMetadata> {
    @Override
    protected void updateItem(NoteMetadata item, boolean empty) {
        super.updateItem(item, empty);

        if (empty || item == null) {
            setText(null);
        } else {
            setText(item.getTitle());
        }
    }
}





