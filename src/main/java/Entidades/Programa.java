/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

/**
 *
 * @author PC
 */
public class Programa {

    private int _id_programa;
    private String _codigo;
    private String _programa;
    private double _totalHoras;
    private int _id_Subsector;
    private Boolean _borrado;

    public Programa() {
        this._id_programa = 0;
        this._codigo = "";
        this._programa = "";
        this._totalHoras = 0;
        this._id_Subsector = 0;
        this._borrado = false;
    }

    public Programa(int idPrograma, String codigo, String programa, double totalHoras, int idSubsector, Boolean borrado) {
        this._id_programa = idPrograma;
        this._codigo = codigo;
        this._programa = programa;
        this._totalHoras = totalHoras;
        this._id_Subsector = idSubsector;
        this._borrado = borrado;
    }

    public int getIdPrograma() {
        return _id_programa;
    }

    public void setIdPrograma(int idPrograma) {
        this._id_programa = idPrograma;
    }

    public String getCodigo() {
        return _codigo;
    }

    public void setCodigo(String codigo) {
        this._codigo = codigo;
    }

    public String getPrograma() {
        return _programa;
    }

    public void setPrograma(String programa) {
        this._programa = programa;
    }

    public double getTotalHoras() {
        return _totalHoras;
    }

    public void setTotalHoras(double totalHoras) {
        this._totalHoras = totalHoras;
    }

    public int getIdSubsector() {
        return _id_Subsector;
    }

    public void setIdSubsector(int idSubsector) {
        this._id_Subsector = idSubsector;
    }

    public Boolean getBorrado() {
        return _borrado;
    }

    public void setBorrado(Boolean borrado) {
        this._borrado = borrado;
    }

    @Override
    public String toString() {
        return "Programa{" + "_id_programa=" + _id_programa + ", _codigo=" + _codigo + ", _programa=" + _programa + ", _totalHoras=" + _totalHoras + ", _id_Subsector=" + _id_Subsector + ", _borrado=" + _borrado + '}';
    }
}
