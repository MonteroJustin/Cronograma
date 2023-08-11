/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LogicaNegocio;

import AccesoDatos.PersonalDA;
import Entidades.Personal;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author PC
 */
public class PersonalBL {

    /**
     * Recupera una lista de registros de personal relacionados con un profesor
     * específico.
     * @param idProfe El identificador del profesor del que se desean obtener
     * los registros de personal.
     * @return Una lista de objetos Personal que representan los registros de
     * personal asociados al profesor.
     * @throws SQLException Si se produce un error al interactuar con la base de
     * datos.
     */
    public ArrayList<Personal> listarRegistros(int idProfe) throws SQLException {
        ArrayList<Personal> personales;
        PersonalDA personalDA = new PersonalDA();
        try {
            personales = personalDA.listarRegistros(idProfe);
        } catch (Exception e) {
            throw e;
        }
        return personales;
    }

}
