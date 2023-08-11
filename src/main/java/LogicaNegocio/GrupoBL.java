/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LogicaNegocio;

import AccesoDatos.GrupoDA;
import Entidades.Grupo;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author PC
 */
public class GrupoBL {

    /**
     * Recupera una lista de módulos registrados en el sistema.
     *
     * @return Una lista de objetos Modulo que representan los módulos
     * registrados.
     * @throws SQLException Si se produce un error al interactuar con la base de
     * datos.
     */
    public ArrayList<Grupo> listarRegistros() throws SQLException {
        ArrayList<Grupo> grupos;
        GrupoDA grupoDA = new GrupoDA();
        try {
            grupos = grupoDA.listarRegistros();
        } catch (Exception e) {
            throw e;
        }
        return grupos;
    }

}
