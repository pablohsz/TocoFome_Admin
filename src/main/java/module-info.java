module com.pucgo.cliserver.admintocofome.tocofome_admin {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires okhttp;
    requires com.google.gson;

    opens com.pucgo.cliserver.tocofome_admin to javafx.fxml;
    exports com.pucgo.cliserver.tocofome_admin;
}