package app.taskplanner.view;

import app.taskplanner.StartApp;
import app.taskplanner.view.boardView.BoardViewController;
import app.taskplanner.view.calendarview.CalendarViewController;
import app.taskplanner.view.listview.ListViewController;
import app.taskplanner.viewmodel.boardviewmodel.BoardViewModel;
import app.taskplanner.viewmodel.calendarviewmodel.CalendarViewModel;
import app.taskplanner.viewmodel.listviewmodel.ListViewModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class PrimaryViewController {
    @FXML
    private TabPane tabs;

    @FXML
    private Tab listTab;

    @FXML
    private Tab boardTab;
    @FXML
    private Tab calendarTab;

    @FXML
    private AnchorPane listView;

    @FXML
    private AnchorPane boardPane;
    @FXML
    private AnchorPane calendarView;

    public void initialize() {
        boardPane = new AnchorPane();
    }

    public void init(ListViewModel listVM, BoardViewModel boardVM, CalendarViewModel calendarVM) {

        BoardViewController boardController = new BoardViewController();
        boardController.init(boardVM, boardPane);

        FXMLLoader listLoader = new FXMLLoader(StartApp.class.getResource("list-view.fxml"));
        try {
            listView = listLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ListViewController listController = listLoader.getController();
        listController.init(listVM);
        FXMLLoader calendarLoader = new FXMLLoader(StartApp.class.getResource("calendar-view.fxml"));
        try {
            calendarView = calendarLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        calendarView.setStyle("-fx-background-color: aquamarine;");
        CalendarViewController calendarController = calendarLoader.getController();
        calendarController.init(calendarVM,calendarView);
        listTab.setContent(listView);
        boardTab.setContent(boardPane);
        calendarTab.setContent(calendarView);
        tabs.getTabs().set(0, listTab);
        tabs.getTabs().set(1, boardTab);
        tabs.getTabs().set(2, calendarTab);
    }
}