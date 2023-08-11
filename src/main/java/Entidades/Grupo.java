/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

/**
 *
 * @author PC
 */
public class Grupo {

    private int _id_grupo;
    private String _grupo;
    private Boolean _borrado;

    public Grupo() {
        this._id_grupo = 0;
        this._grupo = "";
        this._borrado = false;
    }

    public Grupo(int _id_grupo, String _grupo, Boolean _borrado) {
        this._id_grupo = _id_grupo;
        this._grupo = _grupo;
        this._borrado = _borrado;
    }

    public int getId_grupo() {
        return _id_grupo;
    }

    public void setId_grupo(int _id_grupo) {
        this._id_grupo = _id_grupo;
    }

    public String getGrupo() {
        return _grupo;
    }

    public void setGrupo(String _grupo) {
        this._grupo = _grupo;
    }

    public Boolean getBorrado() {
        return _borrado;
    }

    public void setBorrado(Boolean _borrado) {
        this._borrado = _borrado;
    }

    @Override
    public String toString() {
        return "Grupo{" + "_id_grupo=" + _id_grupo + ", _grupo=" + _grupo + ", _borrado=" + _borrado + '}';
    }

}
