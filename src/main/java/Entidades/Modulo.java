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
public class Modulo {

    private int _id_modulo;
    private String _codigo;
    private String _modulo;
    private LocalDate _fechaInicio; // Cambio de tipo Date a LocalDate
    private LocalDate _fechaFin;    // Cambio de tipo Date a LocalDate
    private double _totalHoras;
    private String _requisitos;
    private Programa _id_Programa;
    private boolean _borrado;

    public Modulo() {
        this._id_modulo = 0;
        this._codigo = "";
        this._modulo = "";
        this._fechaInicio = null;
        this._fechaFin = null;
        this._totalHoras = 0;
        this._requisitos = "";
        this._id_Programa = new Programa();
        this._borrado = false;
    }

    public Modulo(int _id_modulo, String _codigo, String _modulo, LocalDate _fechaInicio, LocalDate _fechaFin, double _totalHoras, String _requisitos, Programa _id_Programa, boolean _borrado) {
        this._id_modulo = _id_modulo;
        this._codigo = _codigo;
        this._modulo = _modulo;
        this._fechaInicio = _fechaInicio;
        this._fechaFin = _fechaFin;
        this._totalHoras = _totalHoras;
        this._requisitos = _requisitos;
        this._id_Programa = _id_Programa;
        this._borrado = _borrado;
    }

    public int getId_modulo() {
        return _id_modulo;
    }

    public void setId_modulo(int _id_modulo) {
        this._id_modulo = _id_modulo;
    }

    public String getCodigo() {
        return _codigo;
    }

    public void setCodigo(String _codigo) {
        this._codigo = _codigo;
    }

    public String getModulo() {
        return _modulo;
    }

    public void setModulo(String _modulo) {
        this._modulo = _modulo;
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

    public double getTotalHoras() {
        return _totalHoras;
    }

    public void setTotalHoras(double _totalHoras) {
        this._totalHoras = _totalHoras;
    }

    public String getRequisitos() {
        return _requisitos;
    }

    public void setRequisitos(String _requisitos) {
        this._requisitos = _requisitos;
    }

    public Programa getId_Programa() {
        return _id_Programa;
    }

    public void setId_Programa(Programa _id_Programa) {
        this._id_Programa = _id_Programa;
    }

    public boolean isBorrado() {
        return _borrado;
    }

    public void setBorrado(boolean _borrado) {
        this._borrado = _borrado;
    }

    @Override
    public String toString() {
        return "Modulo{" + "_id_modulo=" + _id_modulo + ", _codigo=" + _codigo + ", _modulo=" + _modulo + ", _fechaInicio=" + _fechaInicio + ", _fechaFin=" + _fechaFin + ", _totalHoras=" + _totalHoras + ", _requisitos=" + _requisitos + ", _id_Programa=" + _id_Programa + ", _borrado=" + _borrado + '}';
    }

    

}
