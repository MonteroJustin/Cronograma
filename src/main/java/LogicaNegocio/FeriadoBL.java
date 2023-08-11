/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LogicaNegocio;

import AccesoDatos.FeriadoDA;
import Entidades.Feriado;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author PC
 */
public class FeriadoBL {

    /**
     * Recupera una lista de días feriados registrados en el sistema.
     *
     * @return Una lista de objetos Feriado que representan los días feriados
     * registrados.
     * @throws SQLException Si se produce un error al interactuar con la base de
     * datos.
     */
    public ArrayList<Feriado> listarRegistros() throws SQLException {
        ArrayList<Feriado> Feriados;
        FeriadoDA feriadoDA = new FeriadoDA(); // Cambio de ModuloDA a FeriadoDA
        try {
            Feriados = feriadoDA.listarRegistros(); // Cambio de modulos a Feriados
        } catch (Exception e) {
            throw e;
        }
        return Feriados;
    }
}
