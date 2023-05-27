package app.taskplanner.view;

import app.taskplanner.view.boardView.BoardViewController;
import app.taskplanner.viewmodel.ViewHandler;
import app.taskplanner.viewmodel.noteviewmodel.NoteViewModel;

public class ViewFactory {
    ViewHandler viewHandler;
    BoardViewController boardView;

    public ViewFactory(ViewHandler viewHandler) {
        this.viewHandler = viewHandler;
//     place for creating view classes
        //nothing to do here
    }
}
