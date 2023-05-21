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
    exports app.taskplanner.viewmodel;
    exports app.taskplanner.model;
    exports app.taskplanner;
    exports app.taskplanner.view.listview;
    exports app.taskplanner.view.noteview;
    opens app.taskplanner.viewmodel to javafx.fxml;
}