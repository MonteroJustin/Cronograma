/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LogicaNegocio;

import AccesoDatos.ModuloDA;
import Entidades.Modulo;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author PC
 */
public class ModuloBL {

    /**
     * Recupera una lista de módulos registrados en el sistema.
     *
     * @return Una lista de objetos Modulo que representan los módulos
     * registrados.
     * @throws SQLException Si se produce un error al interactuar con la base de
     * datos.
     */
    public ArrayList<Modulo> listarRegistros() throws SQLException {
        ArrayList<Modulo> modulos;
        ModuloDA moduloDA = new ModuloDA();
        try {
            modulos = moduloDA.listarRegistros();
        } catch (Exception e) {
            throw e;
        }
        return modulos;
    }

}
