/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

/**
 *
 * @author PC
 */
public class Persona {

    private String _nombre;
    private String _ape1;
    private String _ape2;
    private boolean _borrado;

    public Persona() {
        this._nombre = "";
        this._ape1 = "";
        this._ape2 = "";
        this._borrado = false;
    }

    public Persona(String _nombre, String _ape1, String _ape2, boolean _borrado) {
        this._nombre = _nombre;
        this._ape1 = _ape1;
        this._ape2 = _ape2;
        this._borrado = _borrado;
    }

    public String getNombre() {
        return _nombre;
    }

    public void setNombre(String _nombre) {
        this._nombre = _nombre;
    }

    public String getApe1() {
        return _ape1;
    }

    public void setApe1(String _ape1) {
        this._ape1 = _ape1;
    }

    public String getApe2() {
        return _ape2;
    }

    public void setApe2(String _ape2) {
        this._ape2 = _ape2;
    }

    public boolean isBorrado() {
        return _borrado;
    }

    public void setBorrado(boolean _borrado) {
        this._borrado = _borrado;
    }

    
    
    
    
    
    
    
    
}
