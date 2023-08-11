/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AccesoDatos;

import Configuracion.Config;
import Entidades.Grupo;
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
public class GrupoDA {

    /**
     * Recupera una lista de objetos Grupo a partir de los registros almacenados
     * en la base de datos.
     *
     * @return Una lista de objetos Grupo con los datos recuperados de la base
     * de datos.
     * @throws SQLException Si ocurre un error al interactuar con la base de
     * datos.
     */
    public ArrayList<Grupo> listarRegistros() throws SQLException {

        ArrayList<Grupo> Grupos = new ArrayList<>();
        String query = "Select id_grupo, grupo from GRUPOS";

        Grupo grupo;
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

            // Iteramos sobre el resultado y creamos objetos Grupo para cada fila
            while (resultSet.next()) {
                grupo = new Grupo();
                grupo.setId_grupo(resultSet.getInt(1));
                grupo.setGrupo(resultSet.getString(2));

                Grupos.add(grupo); // Agregamos el Grupo a la lista
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
        // Devolvemos la lista de Grupos
        return Grupos;
    }

}
