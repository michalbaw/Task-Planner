package app.taskplanner.view.listview;
import app.taskplanner.model.notes.NoteMetadata;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.VBox;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


public class NoteListCell extends ListCell<NoteMetadata> {
    private final VBox contentBox;
    private final Label titleLabel;
    private final Label dateLabel;

    public NoteListCell() {
        contentBox = new VBox();
        titleLabel = new Label();
        dateLabel = new Label();

        contentBox.getChildren().addAll(titleLabel, dateLabel);
        contentBox.setAlignment(Pos.CENTER_LEFT);
        contentBox.setSpacing(5);
    }

    @Override
    protected void updateItem(NoteMetadata item, boolean empty) {
        super.updateItem(item, empty);

        if (empty || item == null) {
            setGraphic(null);
        } else {
            titleLabel.setText(item.getTitle());
            dateLabel.setText(daysDisplay(item.getDate()));

            setGraphic(contentBox);

            setStyle("-fx-background-color: " + color(days(item.getDate())));
        }
    }

    private Long days(LocalDate date) {
        if (date == null) return null;
        LocalDate currentDate = LocalDate.now();
        return ChronoUnit.DAYS.between(currentDate, date);
    }
    private String daysDisplay(LocalDate date){
        Long daysNumber = days(date);
        if (daysNumber == null) return "no termin";
        if (daysNumber == 1 || daysNumber == -1) return daysNumber + " day";
        if (daysNumber == 0) return "today!";
        return daysNumber + " days";
    }
    private String color(Long number){

        if (number == null) return "#FFCCCC";
        if (number < -5) return "#FF66B2";
        if (number < 0) return "#FF3399";
        if (number < 2) return "#FF3333";
        if (number < 7) return "#FF6666";
        if (number < 15) return "#FF8888";
        if (number < 30) return "#FFAAAA";
        return "#FFCCCC";
    }
}





