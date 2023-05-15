package app.taskplanner.view.boardView;

import app.taskplanner.view.ViewController;
import app.taskplanner.viewmodel.ViewModel;
import app.taskplanner.viewmodel.boardviewmodel.BoardViewModel;

public class BoardViewController implements ViewController {
    private BoardViewModel boardVM;
    @Override
    public void init(ViewModel boardVM) {
        this.boardVM = (BoardViewModel) boardVM;
    }
}
