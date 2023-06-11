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

    /*

    @Override
    protected void updateItem(SimpleTask task, boolean empty) {
        super.updateItem(task, empty);

        if (empty || task == null) {
            setGraphic(null);
            setText(null);
        } else {
            checkBox.setText(task.getTask());
            checkBox.setSelected(task.getStatus());
            setGraphic(checkBox);
            setText(null);

            // Customize the cell's text size and cell size
            setFont(Font.font("Arial", 18)); // Adjust the font and size as desired

            TextFlow textFlow = new TextFlow();
            textFlow.setPrefWidth(checkBox.getWidth());
            textFlow.setLineSpacing(5); // Adjust the line spacing as desired

            // Create a Text node for each line of the task text
            String[] lines = task.getTask().split("\\n");
            for (String line : lines) {
                Text text = new Text(line);
                text.setFont(getFont());
                textFlow.getChildren().add(text);
            }

            // Add the TextFlow to the cell
            setGraphic(textFlow);
        }
    }
    */

}