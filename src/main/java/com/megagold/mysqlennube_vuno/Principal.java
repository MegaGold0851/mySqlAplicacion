package com.megagold.mysqlennube_vuno;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
public class Principal extends Application {

    @FXML
    private static Tab tabStands;

    @FXML
    private static Tab tabListaStands;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Principal.class.getResource("Principal.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 850, 680);
        stage.setTitle("Jojo's En la nube");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
        cargarTabs(fxmlLoader);
    }

    public void cargarTabs(FXMLLoader fxmlLoader){
        //Tab Stands
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("Stands.fxml"));
        AnchorPane content1 = null;
        try {
            content1 = loader1.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ((Tab) fxmlLoader.getNamespace().get("tabStands")).setContent(content1);

        //Tab ListaStands
        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("ListaStands.fxml"));
        AnchorPane content2 = null;
        try {
            content2 = loader2.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ((Tab) fxmlLoader.getNamespace().get("tabListaStands")).setContent(content2);
    }



    public static void main(String[] args) {
        launch();
    }
}