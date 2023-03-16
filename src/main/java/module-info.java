module com.megagold.mysqlennube_vuno {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.megagold.mysqlennube_vuno to javafx.fxml;
    exports com.megagold.mysqlennube_vuno;
    exports com.megagold.mysqlennube_vuno.Core to javafx.fxml;
    opens com.megagold.mysqlennube_vuno.Core to javafx.fxml;
    opens com.megagold.mysqlennube_vuno.Model to javafx.base;


}