package com.megagold.mysqlennube_vuno.Model;

import javafx.scene.chart.PieChart;

public class Stands {

    private int idStands;
    private String nombre, parte, tipoDeStand;
    private FichaStand fichaStand;
    private PieChart pieChart;

    public Stands() {
    }

    public Stands(int idStands, String nombre, String parte, String tipoDeStand, FichaStand fichaStand) {
        this.idStands = idStands;
        this.nombre = nombre;
        this.parte = parte;
        this.tipoDeStand = tipoDeStand;
        this.fichaStand = fichaStand;
    }

    public int getIdStands() {
        return idStands;
    }

    public void setIdStands(int idStands) {
        this.idStands = idStands;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getParte() {
        return parte;
    }

    public void setParte(String parte) {
        this.parte = parte;
    }

    public String getTipoDeStand() {
        return tipoDeStand;
    }

    public void setTipoDeStand(String tipoDeStand) {
        this.tipoDeStand = tipoDeStand;
    }

    public FichaStand getFichaStand() {
        return fichaStand;
    }

    public void setFichaStand(FichaStand fichaStand) {
        this.fichaStand = fichaStand;
    }

    public PieChart getPieChart() {
        return pieChart;
    }

    public void setPieChart(PieChart pieChart) {
        this.pieChart = pieChart;
    }
}
