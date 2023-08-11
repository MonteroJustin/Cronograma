/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

/**
 *
 * @author PC
 */
public class Usuario extends Persona {
    
    private int _id_usuario;
    private String _usuario;
    private String _contraseña;
    private boolean _rol;

    public Usuario() {
        this._id_usuario = 0;
        this._usuario = "";
        this._contraseña = "";
        this._rol = false;
    }

    public Usuario(int _id_usuario, String _usuario, String _contraseña, boolean _rol) {
        this._id_usuario = _id_usuario;
        this._usuario = _usuario;
        this._contraseña = _contraseña;
        this._rol = _rol;
    }

    public int getId_usuario() {
        return _id_usuario;
    }

    public void setId_usuario(int _id_usuario) {
        this._id_usuario = _id_usuario;
    }

    public String getUsuario() {
        return _usuario;
    }

    public void setUsuario(String _usuario) {
        this._usuario = _usuario;
    }

    public String getContraseña() {
        return _contraseña;
    }

    public void setContraseña(String _contraseña) {
        this._contraseña = _contraseña;
    }

    public boolean isRol() {
        return _rol;
    }

    public void setRol(boolean _rol) {
        this._rol = _rol;
    }

    @Override
    public String toString() {
        return "Usuario{" + "_id_usuario=" + _id_usuario + ", _usuario=" + _usuario + ", _contrase\u00f1a=" + _contraseña + ", _rol=" + _rol + '}';
    }

    
    
    
}
