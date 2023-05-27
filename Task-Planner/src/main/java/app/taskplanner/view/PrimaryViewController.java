package app.taskplanner.view;

import app.taskplanner.model.DataModel;
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
    private AnchorPane boardView;


    public void init(DataModel dataModel) {
        // Create the view models
        ListViewModel listViewModel = new ListViewModel(dataModel);
        BoardViewModel boardViewModel = new BoardViewModel(dataModel);

        // Create the controllers and pass the view models
        ListViewController listController = new ListViewController();
        listController.init(listViewModel);
        BoardViewController boardController = new BoardViewController();
        boardController.init(boardViewModel);

        // Load the list view from its FXML file
        FXMLLoader listLoader = new FXMLLoader(getClass().getResource("list-view.fxml"));
        listLoader.setController(listController);
        try {
            listView = listLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Set the list view as the content of the listTab
        listTab.setContent(listView);

        // Set the board view as the content of the boardTab
        boardTab.setContent(boardView);

        // Create the board view from the BoardViewController
        boardView = boardController.createView();

        // Add the tabs to the TabPane
        tabs.getTabs().addAll(listTab, boardTab);
    }
}
/*
public class PrimaryViewController {

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
    private BoardViewController boardPane;
    @FXML
    private TabPane tabs;

    public void init() {
        listViewController = new ListViewController();
        boardViewModel = new BoardViewModel();
        boardPane = new BoardViewController();
        Tab board = new Tab("Board", boardPane);
        tabs.getTabs().set(1, board);
    }

}

 */