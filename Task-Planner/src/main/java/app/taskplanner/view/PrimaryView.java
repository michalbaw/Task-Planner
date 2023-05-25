package app.taskplanner.view;

import app.taskplanner.view.boardView.BoardView;
import app.taskplanner.view.listview.ListViewController;
import app.taskplanner.viewmodel.boardviewmodel.BoardViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

public class PrimaryView {

    @FXML
    private Tab boardTab;

    @FXML
    private AnchorPane boardView;

    @FXML
    private Tab listTab;
    @FXML
    private ListViewController listViewController;
    @FXML
    private BoardViewModel boardViewModel;

    @FXML
    private AnchorPane listView;
    @FXML
    private BoardView boardPane;
    @FXML
    private TabPane tabs;

    public void init(){
    listViewController = new ListViewController();
    boardViewModel = new BoardViewModel();
    boardPane = new BoardView();
    Tab board = new Tab("board",boardPane);
    tabs.getTabs().set(0,board);
    }

}