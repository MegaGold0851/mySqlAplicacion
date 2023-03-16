package com.megagold.mysqlennube_vuno.Core;

import com.megagold.mysqlennube_vuno.ConexionBD;
import com.megagold.mysqlennube_vuno.Model.FichaStand;
import com.megagold.mysqlennube_vuno.Model.Stands;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.chart.PieChart;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static com.megagold.mysqlennube_vuno.ConexionBD.obtenerStands;

public class ListaStandsCore implements Initializable {

    @FXML
    private TableColumn<FichaStand, PieChart> colFichaDeStand;

    @FXML
    private TableColumn<Stands, String> colNombre;

    @FXML
    private TableColumn<Stands, String> colParte;

    @FXML
    private TableColumn<Stands, String> colTipoDeStand;

    @FXML
    private TableView<Stands> tblListaStands;

    @FXML
    private TableColumn<Stands, Integer> colID;

    List<Stands> standsList = new ArrayList<>();

    private ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();


    public void refrescarTabla() {
        // Borrar datos antiguos
        tblListaStands.getItems().clear();

        // Crear las columnas personalizadas para los PieCharts
        TableColumn<Stands, PieChart> fichaStandColumn = new TableColumn<>("Ficha de Stand");
        fichaStandColumn.setPrefWidth(150);
        fichaStandColumn.setCellValueFactory(cellData -> {
            Stands stand = cellData.getValue();
            FichaStand fichaStand = stand.getFichaStand();
            PieChart pieChart = new PieChart();
            pieChart.getData().add(new PieChart.Data("Poder", fichaStand.getPoder()));
            pieChart.getData().add(new PieChart.Data("Velocidad", fichaStand.getVelocidad()));
            pieChart.getData().add(new PieChart.Data("Resistencia", fichaStand.getResistencia()));
            pieChart.getData().add(new PieChart.Data("Rango", fichaStand.getRango()));
            pieChart.getData().add(new PieChart.Data("Potencial", fichaStand.getPotencial()));
            return new ReadOnlyObjectWrapper<>(pieChart);
        });

        // Agregar las columnas personalizadas a la tabla
        tblListaStands.getColumns().clear();

        tblListaStands.getColumns().addAll(
                colNombre,
                colParte,
                colTipoDeStand,
                fichaStandColumn
        );

        // Agregar los datos a la tabla
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colParte.setCellValueFactory(new PropertyValueFactory<>("parte"));
        colTipoDeStand.setCellValueFactory(new PropertyValueFactory<>("tipoDeStand"));

        ObservableList<Stands> listaObservable = FXCollections.observableArrayList(standsList);
        tblListaStands.setItems(listaObservable);
    }
    @FXML
    void recargar(ActionEvent event) throws SQLException {
        standsList = obtenerStands();
        refrescarTabla();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            standsList = obtenerStands();
            refrescarTabla();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
