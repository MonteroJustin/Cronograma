/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AccesoDatos;

import Configuracion.Config;
import Entidades.Personal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

public class PersonalDA {

    /**
     * Recupera una lista de objetos Personal asociados a un profesor según su
     * ID.
     *
     * @param idProfe El ID del profesor para el cual se desean obtener los
     * registros de personal.
     * @return Una lista de objetos Personal asociados al profesor.
     * @throws SQLException Si ocurre un error al acceder a la base de datos.
     */
    public ArrayList<Personal> listarRegistros(int idProfe) throws SQLException {

        ArrayList<Personal> personales = new ArrayList<>();
        String query = "SELECT p.fecha_inicio, p.fecha_fin FROM PERSONALES p "
                + "INNER JOIN PERSONALES_PROFESORES pp ON p.id_personal = pp.id_personal "
                + "INNER JOIN PROFESORES pr ON pr.id_profesor = pp.id_profesor "
                + "WHERE pr.id_profesor = " + idProfe;

        Personal personal;
        Statement statement = null;
        ResultSet resultSet = null;
        Connection conexionSQL = null;

        try {
            // Obtenemos la conexión a la base de datos utilizando la clase Conexion
            conexionSQL = DriverManager.getConnection(Config.getConnectionString());

            // Preparamos la consulta SQL con la sentencia
            statement = conexionSQL.createStatement();

            // Ejecutamos la consulta y obtenemos el resultado en un ResultSet
            resultSet = statement.executeQuery(query);

            // Iteramos sobre el resultado y creamos objetos Personal para cada fila
            while (resultSet.next()) {
                personal = new Personal();
                personal.setFechaInicio(resultSet.getObject("fecha_inicio", LocalDate.class));
                personal.setFechaFin(resultSet.getObject("fecha_fin", LocalDate.class));

                personales.add(personal); // Agregamos el Personal a la lista
            }

        } catch (SQLException ex) {
            // En caso de excepción, la relanzamos para que sea manejada en el nivel superior
            throw ex;
        } finally {
            // Cerramos los recursos para liberar memoria y conexiones
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (conexionSQL != null) {
                conexionSQL.close();
            }
        }
        // Devolvemos la lista de Personales
        return personales;
    }
}
