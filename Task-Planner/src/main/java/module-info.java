module app.taskplanner {
    requires javafx.controls;
    requires javafx.fxml;
        requires javafx.web;
            
        requires org.controlsfx.controls;
                requires net.synedra.validatorfx;
            requires org.kordamp.ikonli.javafx;
            requires org.kordamp.bootstrapfx.core;

    opens app.taskplanner to javafx.fxml;
    opens app.taskplanner.viewmodel.listview to javafx.fxml;
    opens app.taskplanner.viewmodel.noteview to javafx.fxml;
    exports app.taskplanner.viewmodel;
    exports app.taskplanner.model;
    exports app.taskplanner;
    exports app.taskplanner.viewmodel.listview;
    exports app.taskplanner.viewmodel.noteview;
    opens app.taskplanner.viewmodel to javafx.fxml;
    exports app.taskplanner.model.notes;
}