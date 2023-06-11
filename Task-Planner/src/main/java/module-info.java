module app.taskplanner {
    requires javafx.controls;
    requires javafx.fxml;
        requires javafx.web;
            
        //requires org.controlsfx.controls;
        //        requires net.synedra.validatorfx;
        //    requires org.kordamp.ikonli.javafx;
        //    requires org.kordamp.bootstrapfx.core;

    opens app.taskplanner to javafx.fxml;
    opens app.taskplanner.view.listview to javafx.fxml;
    opens app.taskplanner.view.noteview to javafx.fxml;
    opens app.taskplanner.view.boardView to javafx.fxml;
    opens app.taskplanner.view.calendarview to javafx.fxml;
    exports app.taskplanner.model;
    exports app.taskplanner;
    exports app.taskplanner.view.listview;
    exports app.taskplanner.view.calendarview;
    exports app.taskplanner.viewmodel.calendarviewmodel;
    exports app.taskplanner.view.noteview;
    exports app.taskplanner.view.boardView;
    exports app.taskplanner.view;
    exports app.taskplanner.viewmodel;
    exports app.taskplanner.viewmodel.noteviewmodel;
    opens app.taskplanner.view to javafx.fxml;
    opens app.taskplanner.viewmodel to javafx.fxml;
    exports app.taskplanner.model.notes;
}