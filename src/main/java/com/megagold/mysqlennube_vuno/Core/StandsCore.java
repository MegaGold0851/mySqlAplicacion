package com.megagold.mysqlennube_vuno.Core;

import com.megagold.mysqlennube_vuno.ConexionBD;
import com.megagold.mysqlennube_vuno.Model.FichaStand;
import com.megagold.mysqlennube_vuno.Model.Stands;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.collections.FXCollections;


import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class StandsCore implements Initializable {

    ConexionBD conexionBD = new ConexionBD();
    Stands stands;
    FichaStand fichaStand;

    @FXML
    private ComboBox<String> cbxTipoDeStand;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtParte;

    @FXML
    private TextField txtPoder;

    @FXML
    private TextField txtPotencial;

    @FXML
    private TextField txtRango;

    @FXML
    private TextField txtResistencia;

    @FXML
    private TextField txtVelocidad;

    @FXML
    private PieChart pieFichaDeStand;
    @FXML
    private TextField txtIdStand;

    @FXML
    private TextField txtFichaStand;

    ListaStandsCore listaStandsCore = new ListaStandsCore();

    @FXML
    void guardarStand(ActionEvent event) throws SQLException {
        Alert alertWarning = new Alert(Alert.AlertType.WARNING);
        Alert alertSuccess = new Alert(Alert.AlertType.INFORMATION);

        //Si los dos txtId estan vacios significa que sera un nuevo Stand
        if(txtIdStand.getText().isBlank() && txtFichaStand.getText().isBlank()){
            if(txtNombre.getText().isBlank() || txtParte.getText().isBlank() ){
                alertWarning.setTitle("Campos vacios");
                alertWarning.setHeaderText("No deje campos vacios.");
                alertWarning.showAndWait();
            }else{
                int poder = Integer.parseInt(txtPoder.getText());
                int velocidad = Integer.parseInt(txtVelocidad.getText());
                int resistencia = Integer.parseInt(txtResistencia.getText());
                int rango = Integer.parseInt(txtRango.getText());
                int potencial = Integer.parseInt(txtPotencial.getText());
                String nombre = txtNombre.getText();
                String parte = txtParte.getText();
                String tipoStand = cbxTipoDeStand.getValue();

                fichaStand = new FichaStand();
                stands = new Stands();

                fichaStand.setPoder(poder);fichaStand.setVelocidad(velocidad);fichaStand.setResistencia(resistencia);
                fichaStand.setRango(rango);fichaStand.setPotencial(potencial);

                stands.setNombre(nombre);stands.setParte(parte);stands.setTipoDeStand(tipoStand);
                stands.setFichaStand(fichaStand);

                generarPieChart(poder, velocidad, resistencia, rango, potencial, nombre);
                conexionBD.insertarStand(stands);
                limpiarDatos();
                alertSuccess.setTitle("Ingresados Correctamente");
                alertSuccess.setHeaderText("El Stand se a agregado correctamente.");
                alertSuccess.showAndWait();
            }
        }else{
            if(txtNombre.getText().isBlank() || txtParte.getText().isBlank() ){
                alertWarning.setTitle("Campos vacios");
                alertWarning.setHeaderText("No deje campos vacios.");
                alertWarning.showAndWait();
            }else{
                int idFichaStand = Integer.parseInt(txtFichaStand.getText());
                int poder = Integer.parseInt(txtPoder.getText());
                int velocidad = Integer.parseInt(txtVelocidad.getText());
                int resistencia = Integer.parseInt(txtResistencia.getText());
                int rango = Integer.parseInt(txtRango.getText());
                int potencial = Integer.parseInt(txtPotencial.getText());

                int idStand = Integer.parseInt(txtIdStand.getText());
                String nombre = txtNombre.getText();
                String parte = txtParte.getText();
                String tipoStand = cbxTipoDeStand.getValue();

                fichaStand = new FichaStand();
                stands = new Stands();

                fichaStand.setIdFichaStand(idFichaStand);fichaStand.setPoder(poder);fichaStand.setVelocidad(velocidad);
                fichaStand.setResistencia(resistencia);fichaStand.setRango(rango);fichaStand.setPotencial(potencial);

                stands.setIdStands(idStand);stands.setNombre(nombre);stands.setParte(parte);
                stands.setTipoDeStand(tipoStand);stands.setFichaStand(fichaStand);

                generarPieChart(poder, velocidad, resistencia, rango, potencial, nombre);
                conexionBD.actualizarFila(stands);
                limpiarDatos();
                alertSuccess.setTitle("Actualizado Correctamente");
                alertSuccess.setHeaderText("El Stand se a actualizado correctamente.");
                alertSuccess.showAndWait();
            }
        }
    }


    @FXML
    void eliminarStand(ActionEvent event) {
        Alert alertSuccess = new Alert(Alert.AlertType.INFORMATION);
        conexionBD.eliminarStand(Integer.parseInt(txtIdStand.getText()), Integer.parseInt(txtFichaStand.getText()));
        limpiarDatos();
        alertSuccess.setTitle("Eliminado Correctamente");
        alertSuccess.setHeaderText("El Stand a sido eliminado correctamente.");
        alertSuccess.showAndWait();
    }

    public void generarPieChart(int poder, int velocidad, int resistencia, int rango, int potencial, String nombre){
        ObservableList<PieChart.Data> datos = FXCollections.observableArrayList(
                new PieChart.Data("Poder", poder),
                new PieChart.Data("Velocidad", velocidad),
                new PieChart.Data("Resistencia", resistencia),
                new PieChart.Data("Rango", rango),
                new PieChart.Data("Potencial", potencial)
        );
        pieFichaDeStand.setData(datos);
        pieFichaDeStand.setTitle(nombre);
    }

    public void limpiarDatos(){
        txtPoder.setText("");
        txtVelocidad.setText("");
        txtResistencia.setText("");
        txtRango.setText("");
        txtPotencial.setText("");

        txtNombre.setText("");
        txtParte.setText("");
        cbxTipoDeStand.setValue("");

        txtIdStand.setText("");
        txtFichaStand.setText("");
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> tiposDeStand = FXCollections.observableArrayList(
                "Fisico", "Tirador", "Automatico", "Pasivo", "Independiente"
        );
        cbxTipoDeStand.setItems(tiposDeStand);
    }
}
