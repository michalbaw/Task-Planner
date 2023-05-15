package app.taskplanner.view;

import app.taskplanner.viewmodel.ViewHandler;

public class ViewFactory {
    ViewHandler viewHandler;
    public ViewFactory(ViewHandler viewHandler)
    {
     this.viewHandler = viewHandler;
     //place for creating view classes
    }
}
