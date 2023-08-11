/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

/**
 *
 * @author PC
 */

public class Profesor extends Persona {

    private int _id_profesor;
    private Usuario _id_usuario;

    public Profesor() {
        this._id_profesor = 0;
        this._id_usuario = new Usuario(); // Crea una instancia de Usuario
    }

    public Profesor(int _id_profesor, Usuario _id_usuario) { // Cambio de tipo de parámetro
        this._id_profesor = _id_profesor;
        this._id_usuario = _id_usuario;
    }

    public int getId_profesor() {
        return _id_profesor;
    }

    public void setId_profesor(int _id_profesor) {
        this._id_profesor = _id_profesor;
    }

    public Usuario getId_usuario() { // Cambio de tipo de retorno
        return _id_usuario;
    }

    public void setId_usuario(Usuario _id_usuario) { // Cambio de tipo de parámetro
        this._id_usuario = _id_usuario;
    }

    @Override
    public String toString() {
        return "Profesor{" + "_id_profesor=" + _id_profesor + ", _id_usuario=" + _id_usuario + '}';
    }

}

