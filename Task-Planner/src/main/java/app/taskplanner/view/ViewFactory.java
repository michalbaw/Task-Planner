package app.taskplanner.view;

import app.taskplanner.view.boardView.BoardViewController;
import app.taskplanner.viewmodel.ViewHandler;
import app.taskplanner.viewmodel.noteviewmodel.NoteViewModel;

public class ViewFactory {
    ViewHandler viewHandler;
    BoardViewController boardView;

    public ViewFactory(ViewHandler viewHandler) {
        this.viewHandler = viewHandler;
        boardView = new BoardViewController();
//     place for creating view classes
    }

    BoardViewController getBoardView() {
        return boardView;
    }
}
