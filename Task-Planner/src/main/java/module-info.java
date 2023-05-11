module app.taskplanner {
    requires javafx.controls;
    requires javafx.fxml;
        requires javafx.web;
            
        requires org.controlsfx.controls;
                requires net.synedra.validatorfx;
            requires org.kordamp.ikonli.javafx;
            requires org.kordamp.bootstrapfx.core;

    opens app.taskplanner to javafx.fxml;
    exports app.taskplanner;
    exports app.taskplanner.viewmodel;
    opens app.taskplanner.viewmodel to javafx.fxml;
}