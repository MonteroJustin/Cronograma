/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.time.LocalDate;

public class Cronograma {

    private int _id_cronograma;
    private Profesor _id_profesor;
    private Programa _id_programa;
    private Modulo _id_modulo;
    private Grupo _id_grupo;
    private LocalDate _anio;
    private double _horasDia;
    private LocalDate _fechaInicio;
    private LocalDate _fechaFin;
    private boolean _L;
    private boolean _K;
    private boolean _M;
    private boolean _J;
    private boolean _V;
    private boolean _S;
    private boolean _D;
    private boolean _estado;
    private boolean _borrado;

    public Cronograma() {
        this._id_cronograma = 0;
        this._id_profesor = new Profesor();
        this._id_programa = new Programa();
        this._id_modulo = new Modulo();
        this._id_grupo = new Grupo();
        this._anio = null;
        this._horasDia = 0;
        this._fechaInicio = null;
        this._fechaFin = null;
        this._L = false;
        this._K = false;
        this._M = false;
        this._J = false;
        this._V = false;
        this._S = false;
        this._D = false;
        this._estado = false;
        this._borrado = false;

    }

    public Cronograma(int _id_cronograma, Profesor _id_profesor, Programa _id_programa, Modulo _id_modulo, Grupo _id_grupo, LocalDate _anio, double _horasDia, LocalDate _fechaInicio, LocalDate _fechaFin, boolean _L, boolean _K, boolean _M, boolean _J, boolean _V, boolean _S, boolean _D, boolean _estado, boolean _borrado) {
        this._id_cronograma = _id_cronograma;
        this._id_profesor = _id_profesor;
        this._id_programa = _id_programa;
        this._id_modulo = _id_modulo;
        this._id_grupo = _id_grupo;
        this._anio = _anio;
        this._horasDia = _horasDia;
        this._fechaInicio = _fechaInicio;
        this._fechaFin = _fechaFin;
        this._L = _L;
        this._K = _K;
        this._M = _M;
        this._J = _J;
        this._V = _V;
        this._S = _S;
        this._D = _D;
        this._estado = _estado;
        this._borrado = _borrado;
    }

    public int getId_cronograma() {
        return _id_cronograma;
    }

    public void setId_cronograma(int _id_cronograma) {
        this._id_cronograma = _id_cronograma;
    }

    public Profesor getId_profesor() {
        return _id_profesor;
    }

    public void setId_profesor(Profesor _id_profesor) {
        this._id_profesor = _id_profesor;
    }

    public Programa getId_programa() {
        return _id_programa;
    }

    public void setId_programa(Programa _id_programa) {
        this._id_programa = _id_programa;
    }

    public Modulo getId_modulo() {
        return _id_modulo;
    }

    public void setId_modulo(Modulo _id_modulo) {
        this._id_modulo = _id_modulo;
    }

    public Grupo getId_grupo() {
        return _id_grupo;
    }

    public void setId_grupo(Grupo _id_grupo) {
        this._id_grupo = _id_grupo;
    }

    public LocalDate getAnio() {
        return _anio;
    }

    public void setAnio(LocalDate _anio) {
        this._anio = _anio;
    }

    public double getHorasDia() {
        return _horasDia;
    }

    public void setHorasDia(double _horasDia) {
        this._horasDia = _horasDia;
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

    public boolean isL() {
        return _L;
    }

    public void setL(boolean _L) {
        this._L = _L;
    }

    public boolean isK() {
        return _K;
    }

    public void setK(boolean _K) {
        this._K = _K;
    }

    public boolean isM() {
        return _M;
    }

    public void setM(boolean _M) {
        this._M = _M;
    }

    public boolean isJ() {
        return _J;
    }

    public void setJ(boolean _J) {
        this._J = _J;
    }

    public boolean isV() {
        return _V;
    }

    public void setV(boolean _V) {
        this._V = _V;
    }

    public boolean isS() {
        return _S;
    }

    public void setS(boolean _S) {
        this._S = _S;
    }

    public boolean isD() {
        return _D;
    }

    public void setD(boolean _D) {
        this._D = _D;
    }

    public boolean isEstado() {
        return _estado;
    }

    public void setEstado(boolean _estado) {
        this._estado = _estado;
    }

    public boolean isBorrado() {
        return _borrado;
    }

    public void setBorrado(boolean _borrado) {
        this._borrado = _borrado;
    }

    @Override
    public String toString() {
        return "Cronograma{" + "_id_cronograma=" + _id_cronograma + ", _id_profesor=" + _id_profesor + ", _id_programa=" + _id_programa + ", _id_modulo=" + _id_modulo + ", _id_grupo=" + _id_grupo + ", _anio=" + _anio + ", _horasDia=" + _horasDia + ", _fechaInicio=" + _fechaInicio + ", _fechaFin=" + _fechaFin + ", _L=" + _L + ", _K=" + _K + ", _M=" + _M + ", _J=" + _J + ", _V=" + _V + ", _S=" + _S + ", _D=" + _D + ", _estado=" + _estado + ", _borrado=" + _borrado + '}';
    }

}
