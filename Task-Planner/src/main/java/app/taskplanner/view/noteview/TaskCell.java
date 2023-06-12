package app.taskplanner.view.noteview;

import app.taskplanner.model.notes.SimpleTask;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListCell;


public class TaskCell extends ListCell<SimpleTask> {
    private final CheckBox checkBox;

    public TaskCell() {
        checkBox = new CheckBox();
        checkBox.setOnAction(event -> {
            SimpleTask task = getItem();
            task.setStatus(checkBox.isSelected());
        });
    }

    @Override
    protected void updateItem(SimpleTask task, boolean empty) {
        super.updateItem(task, empty);

        if (empty || task == null) {
            setGraphic(null);
        } else {
            checkBox.setText(task.getTask());
            checkBox.setSelected(task.getStatus());
            setGraphic(checkBox);
        }
    }
}