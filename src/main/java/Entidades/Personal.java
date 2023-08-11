/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.time.LocalDate;

/**
 *
 * @author PC
 */

public class Personal {

    private int _id_personal;
    private String _motivo;
    private LocalDate _fechaInicio; // Cambio de tipo Date a LocalDate
    private LocalDate _fechaFin;    // Cambio de tipo Date a LocalDate
    private boolean _borrado;

    public Personal() {
        this._id_personal = 0;
        this._motivo = "";
        this._fechaInicio = null;
        this._fechaFin = null;
        this._borrado = false;
    }

    public Personal(int _id_personal, String _motivo, LocalDate _fechaInicio, LocalDate _fechaFin, boolean _borrado) { // Cambio de tipo Date a LocalDate
        this._id_personal = _id_personal;
        this._motivo = _motivo;
        this._fechaInicio = _fechaInicio;
        this._fechaFin = _fechaFin;
        this._borrado = _borrado;
    }

    public int getId_personal() {
        return _id_personal;
    }

    public void setId_personal(int _id_personal) {
        this._id_personal = _id_personal;
    }

    public String getMotivo() {
        return _motivo;
    }

    public void setMotivo(String _motivo) {
        this._motivo = _motivo;
    }

    public LocalDate getFechaInicio() {
        return _fechaInicio;
    }

    public void setFechaInicio(LocalDate _fechaInicio) {
        this._fechaInicio = _fechaInicio;
    }

    public LocalDate getFechaFin() {
        return _fechaFin;
    }

    public void setFechaFin(LocalDate _fechaFin) {
        this._fechaFin = _fechaFin;
    }

    public boolean isBorrado() {
        return _borrado;
    }

    public void setBorrado(boolean _borrado) {
        this._borrado = _borrado;
    }

    @Override
    public String toString() {
        return "Personal{" + "_id_personal=" + _id_personal + ", _motivo=" + _motivo + ", _fechaInicio=" + _fechaInicio + ", _fechaFin=" + _fechaFin + ", _borrado=" + _borrado + '}';
    }
}

