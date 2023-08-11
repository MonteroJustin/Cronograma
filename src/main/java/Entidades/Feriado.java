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

public class Feriado {

    private int _id_feriado;
    private String _motivo;
    private LocalDate _fechaInicio;
    private LocalDate _fechaFin;
    private boolean _borrado;

    public Feriado() {
        this._id_feriado = 0;
        this._motivo = "";
        this._fechaInicio = null;
        this._fechaFin = null;
        this._borrado = false;
    }

    public Feriado(int _id_feriado, String _motivo, LocalDate _fechaInicio, LocalDate _fechaFin, boolean _borrado) {
        this._id_feriado = _id_feriado;
        this._motivo = _motivo;
        this._fechaInicio = _fechaInicio;
        this._fechaFin = _fechaFin;
        this._borrado = _borrado;
    }

    public int getId_feriado() {
        return _id_feriado;
    }

    public void setId_feriado(int _id_feriado) {
        this._id_feriado = _id_feriado;
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
        return "Feriado{" + "_id_feriado=" + _id_feriado + ", _motivo=" + _motivo + ", _fechaInicio=" + _fechaInicio + ", _fechaFin=" + _fechaFin + ", _borrado=" + _borrado + '}';
    }
}

