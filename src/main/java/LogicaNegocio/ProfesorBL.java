/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LogicaNegocio;

import AccesoDatos.ProfesorDA;
import Entidades.Persona;
import Entidades.Profesor;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author PC
 */
public class ProfesorBL {

    /**
     * Recupera una lista de profesores registrados en el sistema.
     *
     * @return Una lista de objetos Profesor que representan los profesores
     * registrados.
     * @throws SQLException Si se produce un error al interactuar con la base de
     * datos.
     */
    public ArrayList<Profesor> listarRegistros() throws SQLException {
        ArrayList<Profesor> profesores;
        ProfesorDA profesorDA = new ProfesorDA();
        try {
            profesores = profesorDA.listarRegistros();
        } catch (Exception e) {
            throw e;
        }
        return profesores;
    }

}
