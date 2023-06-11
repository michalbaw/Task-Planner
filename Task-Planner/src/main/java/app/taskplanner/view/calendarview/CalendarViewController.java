package app.taskplanner.view.calendarview;

import app.taskplanner.StartApp;
import app.taskplanner.model.notes.Note;
import app.taskplanner.view.alerts.SelectionAlert;
import app.taskplanner.viewmodel.calendarviewmodel.CalendarViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.time.ZonedDateTime;
import java.util.*;

public class CalendarViewController {

    ZonedDateTime dateFocus;
    ZonedDateTime today;
    CalendarViewModel calendarVM;
    String css;

    @FXML
    private Text year;

    @FXML
    private Text month;

    @FXML
    private FlowPane calendar;

    public void init(CalendarViewModel calendarVM) {
        css = Objects.requireNonNull(StartApp.class.getResource("styles.css")).toExternalForm();
        this.calendarVM = calendarVM;
        dateFocus = ZonedDateTime.now();
        today = ZonedDateTime.now();
        displayMonth();
    }

    @FXML
    void prevMonth(ActionEvent event) {
        dateFocus = dateFocus.minusMonths(1);
        calendar.getChildren().clear();
        displayMonth();
    }

    @FXML
    void nextMonth(ActionEvent event) {
        dateFocus = dateFocus.plusMonths(1);
        calendar.getChildren().clear();
        displayMonth();
    }

    private void displayMonth(){
        year.setText(String.valueOf(dateFocus.getYear()));
        month.setText(String.valueOf(dateFocus.getMonth()));

        double calendarWidth = calendar.getPrefWidth();
        double calendarHeight = calendar.getPrefHeight();
        double strokeWidth = 1;
        double spacingH = calendar.getHgap();
        double spacingV = calendar.getVgap();

        Map<Integer, List<Note>> notesMap = calendarVM.getNotesForCurrentMonth(dateFocus);

        int monthMaxDate = dateFocus.getMonth().maxLength();
        //bueheheh luty może być przestępczy
        if(dateFocus.getYear() % 4 != 0 && monthMaxDate == 29){
            monthMaxDate = 28;
        }
        int dateOffset = ZonedDateTime.of(dateFocus.getYear(), dateFocus.getMonthValue(), 1,0,0,0,0,dateFocus.getZone()).getDayOfWeek().getValue();

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                StackPane stackPane = new StackPane();

                double rectangleHeight = (calendarHeight/6) - strokeWidth - spacingV;
                double rectangleWidth =(calendarWidth/7) - strokeWidth - spacingH;
                Rectangle rectangle = new Rectangle();
                rectangle.setFill(Color.TRANSPARENT);
                rectangle.setStroke(Color.BLACK);
                rectangle.setStrokeWidth(strokeWidth);
                rectangle.setWidth(rectangleWidth);
                rectangle.setHeight(rectangleHeight);
                stackPane.getChildren().add(rectangle);

                int calculatedDate = (j+1)+(7*i);
                if(calculatedDate > dateOffset){
                    int currentDate = calculatedDate - dateOffset;
                    if(currentDate <= monthMaxDate){
                        Text date = new Text(String.valueOf(currentDate));
                        double textTranslationY = - (rectangleHeight / 2) * 0.75;
                        date.setTranslateY(textTranslationY);
                        stackPane.getChildren().add(date);

                        List<Note> currentNotes = notesMap.get(currentDate);
                        if(currentNotes != null){
                            createDayUnit(currentNotes, rectangleHeight, rectangleWidth, stackPane);
                        }
                    }
                    if(today.getYear() == dateFocus.getYear() && today.getMonth() == dateFocus.getMonth() && today.getDayOfMonth() == currentDate){
                        rectangle.setStroke(Color.BLUE);
                    }
                }
                calendar.getChildren().add(stackPane);
            }
        }
    }

    private void createDayUnit(List<Note> noteList, double rectangleHeight, double rectangleWidth, StackPane stackPane) {
        VBox dayUnitBox = new VBox();
        for (int k = 0; k < noteList.size(); k++) {
            if(k >= 2) {
                Text moreActivities = new Text("...");
                dayUnitBox.getChildren().add(moreActivities);
                moreActivities.setOnMouseClicked(mouseEvent -> {
                    new SelectionAlert();
                });
                break;
            }
            TextArea text = new TextArea(noteList.get(k).getMetadata().getTitle());
            text.setWrapText(true);
            dayUnitBox.getChildren().add(text);
            text.setOnMouseClicked(mouseEvent -> {
                System.out.println(text.getText());
            });
        }
        dayUnitBox.setTranslateY((rectangleHeight / 2) * 0.20);
        dayUnitBox.setMaxWidth(rectangleWidth * 0.8);
        dayUnitBox.setMaxHeight(rectangleHeight * 0.65);
        dayUnitBox.setStyle("-fx-background-color:GRAY");
        stackPane.getChildren().add(dayUnitBox);
    }




}