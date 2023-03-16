package com.megagold.mysqlennube_vuno.Model;

public class FichaStand {

    private int idFichaStand, poder, velocidad, resistencia, rango, potencial;

    public FichaStand() {
    }

    public FichaStand(int idFichaStand, int poder, int velocidad, int resistencia, int rango, int potencial) {
        this.idFichaStand = idFichaStand;
        this.poder = poder;
        this.velocidad = velocidad;
        this.resistencia = resistencia;
        this.rango = rango;
        this.potencial = potencial;
    }

    public int getIdFichaStand() {
        return idFichaStand;
    }

    public void setIdFichaStand(int idFichaStand) {
        this.idFichaStand = idFichaStand;
    }

    public int getPoder() {
        return poder;
    }

    public void setPoder(int poder) {
        this.poder = poder;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public int getResistencia() {
        return resistencia;
    }

    public void setResistencia(int resistencia) {
        this.resistencia = resistencia;
    }

    public int getRango() {
        return rango;
    }

    public void setRango(int rango) {
        this.rango = rango;
    }

    public int getPotencial() {
        return potencial;
    }

    public void setPotencial(int potencial) {
        this.potencial = potencial;
    }

}
