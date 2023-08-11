/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LogicaNegocio;

import AccesoDatos.CronogramaDA; // Asegúrate de importar la clase CronogramaDA
import Entidades.Cronograma; // Asegúrate de importar la clase Cronograma
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author PC
 */
public class CronogramaBL {

    /**
     * Recupera una lista de cronogramas registrados en el sistema.
     *
     * @return Una lista de objetos Cronograma que representan los cronogramas
     * registrados.
     * @throws SQLException Si se produce un error al interactuar con la base de
     * datos.
     */
    public ArrayList<Cronograma> listarRegistros() throws SQLException {
        ArrayList<Cronograma> cronogramas;
        CronogramaDA cronogramaDA = new CronogramaDA(); // Cambio de nombre de la instancia
        try {
            cronogramas = cronogramaDA.listarRegistros(); // Cambio de nombre del método
        } catch (Exception e) {
            throw e;
        }
        return cronogramas; // Cambio de nombre de la variable
    }

    /**
     * Inserta un nuevo cronograma en el sistema.
     *
     * @param cronograma El objeto Cronograma que se desea insertar.
     * @return El resultado de la operación de inserción.
     * @throws SQLException Si se produce un error al interactuar con la base de
     * datos.
     * @throws Exception Si ocurre una excepción general durante la operación.
     */
    public int insert(Cronograma cronograma) throws SQLException, Exception {
        int resultado = 0;
        CronogramaDA cronogramaDA = new CronogramaDA(); // Cambio de nombre de la instancia
        try {
            resultado = cronogramaDA.insert(cronograma); // Cambio de nombre del método
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }

}
