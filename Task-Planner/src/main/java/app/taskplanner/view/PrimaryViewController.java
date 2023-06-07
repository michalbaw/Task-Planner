package app.taskplanner.view;

import app.taskplanner.StartApp;
import app.taskplanner.view.boardView.BoardViewController;
import app.taskplanner.view.listview.ListViewController;
import app.taskplanner.viewmodel.boardviewmodel.BoardViewModel;
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
    private AnchorPane listView;

    @FXML
    private AnchorPane boardPane;

    public void initialize() {
        boardPane = new AnchorPane();
    }

    public void init(ListViewModel listViewModel, BoardViewModel boardViewModel) {

        BoardViewController boardController = new BoardViewController();
        boardController.init(boardViewModel, boardPane);

        FXMLLoader listLoader = new FXMLLoader(StartApp.class.getResource("list-view.fxml"));
        try {
            listView = listLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ListViewController listController = listLoader.getController();
        listController.init(listViewModel);

        listTab.setContent(listView);
        boardTab.setContent(boardPane);
        tabs.getTabs().set(0, listTab);
        tabs.getTabs().set(1, boardTab);

    }
}