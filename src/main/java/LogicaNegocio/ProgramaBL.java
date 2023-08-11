/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LogicaNegocio;

import AccesoDatos.ProgramaDA;
import Entidades.Programa;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author PC
 */
public class ProgramaBL {

    /**
     * Recupera una lista de programas registrados en el sistema.
     *
     * @return Una lista de objetos Programa que representan los programas
     * registrados.
     * @throws SQLException Si se produce un error al interactuar con la base de
     * datos.
     */
    public ArrayList<Programa> listarRegistros() throws SQLException {
        ArrayList<Programa> programas;
        ProgramaDA programaDA = new ProgramaDA();
        try {
            programas = programaDA.listarRegistros();
        } catch (Exception e) {
            throw e;
        }
        return programas;
    }

}
