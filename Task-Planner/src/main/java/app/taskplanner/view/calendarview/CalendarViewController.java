package app.taskplanner.view.calendarview;

import app.taskplanner.StartApp;
import app.taskplanner.model.notes.Note;
import app.taskplanner.model.notes.NoteMetadata;
import app.taskplanner.view.alerts.SelectionAlert;
import app.taskplanner.viewmodel.calendarviewmodel.CalendarViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.time.ZonedDateTime;
import java.time.format.TextStyle;
import java.util.*;

public class CalendarViewController {

    ZonedDateTime dateFocus;
    ZonedDateTime today;
    CalendarViewModel calendarVM;
    String css;
    private Map<Integer,Integer> noteLibrary;
    @FXML
    private Text year;
    @FXML
    private HBox weekdays;

    @FXML
    private Text month;

    @FXML
    private FlowPane calendar;
    @FXML
    private AnchorPane calendarPane;

    public void init(CalendarViewModel calendarVM,AnchorPane anchorPane) {
        css = Objects.requireNonNull(StartApp.class.getResource("styles.css")).toExternalForm();
        this.calendarVM = calendarVM;
        calendarVM.feelCalendarViewController(this);
        dateFocus = ZonedDateTime.now();
        today = ZonedDateTime.now();
        noteLibrary = new HashMap<>();
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
        month.setText(String.valueOf(dateFocus.getMonth().getDisplayName(TextStyle.SHORT_STANDALONE,Locale.ENGLISH)));
        noteLibrary.clear();
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
                Rectangle day = new Rectangle();
                day.setFill(Color.TRANSPARENT);
                day.setStroke(Color.BLACK);
                day.setStrokeWidth(strokeWidth);
                day.setWidth(rectangleWidth);
                day.setFill(Color.LIGHTBLUE);
                day.setHeight(rectangleHeight);
                stackPane.getChildren().add(day);

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
                        day.setStroke(Color.BLUE);
                    }
                }
                calendar.getChildren().add(stackPane);
            }
        }
    }

    private void createDayUnit(List<Note> noteList, double rectangleHeight, double rectangleWidth, StackPane stackPane) {
        VBox dayUnitBox = new VBox();

        for (int k = 0; k < noteList.size(); k++) {
            if(noteList.size() >= 2) {
                TextArea moreNotes = new TextArea("Too many notes to display on this day.");
                moreNotes.setWrapText(true);
                dayUnitBox.getChildren().add(moreNotes);
                moreNotes.setOnMouseClicked(mouseEvent -> {
                    new SelectionAlert("  App is unable to decide which note to open. Use List instead").show();
                });
                break;
            }
            else {
                Integer day = noteList.get(k).getMetadata().getDate().getDayOfMonth();
                Integer key = noteList.get(k).getMetadata().getKey();
                noteLibrary.put(day,key);
                TextArea text = new TextArea(noteList.get(k).getMetadata().getTitle());
                text.setWrapText(true);
                dayUnitBox.getChildren().add(text);
                text.setOnMouseClicked(mouseEvent -> {
                    System.out.println(text.getText());
                    calendarVM.openNoteByKey(noteLibrary.get(day));
                });
            }
        }
        dayUnitBox.setTranslateY((rectangleHeight / 2) * 0.20);
        dayUnitBox.setMaxWidth(rectangleWidth * 0.8);
        dayUnitBox.setMaxHeight(rectangleHeight * 0.65);
        dayUnitBox.setStyle("-fx-background-color:PINK");
        stackPane.getChildren().add(dayUnitBox);
    }
    public void refresh(){
        calendar.getChildren().clear();
        displayMonth();
    }



}