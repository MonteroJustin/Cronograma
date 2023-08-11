/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AccesoDatos;

import Configuracion.Config;
import Entidades.Profesor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author PC
 */
public class ProfesorDA {

    /**
     * Recupera una lista de objetos Profesor desde la base de datos.
     *
     * @return Una lista de objetos Profesor.
     * @throws SQLException Si ocurre un error al acceder a la base de datos.
     */
    public ArrayList<Profesor> listarRegistros() throws SQLException {

        ArrayList<Profesor> Profesores = new ArrayList<>();
        String query = "Select id_profesor, nombre, ape1, ape2 from PROFESORES";

        Profesor profesor;
        Statement statement = null;
        ResultSet resultSet = null;
        Connection conexionSQL = null;

        try {
            // Obtenemos la conexión a la base de datos utilizando la clase Conexion
            conexionSQL = DriverManager.getConnection(Config.getConnectionString());

            // Preparamos la consulta SQL con la sentencia y el valor del parámetro ced
            statement = conexionSQL.createStatement();
            //preparedStatement.setString(1);

            // Ejecutamos la consulta y obtenemos el resultado en un ResultSet
            resultSet = statement.executeQuery(query);

            // Iteramos sobre el resultado y creamos objetos Docente para cada fila
            while (resultSet.next()) {
                profesor = new Profesor();
                profesor.setId_profesor(resultSet.getInt(1));
                profesor.setNombre(resultSet.getString(2));
                profesor.setApe1(resultSet.getString(3));
                profesor.setApe2(resultSet.getString(4));

                Profesores.add(profesor); // Agregamos el Docente a la lista
            }
        } catch (SQLException ex) {
            // En caso de excepción, la relanzamos para que sea manejada en el nivel superior
            throw ex;
        } catch (Exception e) {
            // En caso de otra excepción, también la relanzamos
            throw e;
        } finally {
            // Cerramos los recursos para liberar memoria y conexiones
            if (resultSet != null) {
                resultSet.close();
            }
            if (conexionSQL != null) {
                conexionSQL.close();
            }
        }
        // Devolvemos la lista de Docentes asociados al Administrador
        return Profesores;
    }

}
