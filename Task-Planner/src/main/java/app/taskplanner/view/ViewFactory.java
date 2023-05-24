package app.taskplanner.view;

import app.taskplanner.view.boardView.BoardView;
import app.taskplanner.viewmodel.ViewHandler;
import app.taskplanner.viewmodel.noteviewmodel.NoteViewModel;

public class ViewFactory {
    ViewHandler viewHandler;
    BoardView boardView;
    public ViewFactory(ViewHandler viewHandler)
    {
     this.viewHandler = viewHandler;
     boardView = new BoardView();
//     place for creating view classes
    }
    BoardView getBoardView(){
        return boardView;
    }
}
