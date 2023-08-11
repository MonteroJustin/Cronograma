/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AccesoDatos;

import Configuracion.Config;
import Entidades.Feriado;
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
public class FeriadoDA {

    /**
     * Recupera una lista de objetos Feriado a partir de los registros
     * almacenados en la base de datos.
     *
     * @return Una lista de objetos Feriado con los datos recuperados de la base
     * de datos.
     * @throws SQLException Si ocurre un error al interactuar con la base de
     * datos.
     */
    public ArrayList<Feriado> listarRegistros() throws SQLException {

        ArrayList<Feriado> feriados = new ArrayList<>();
        String query = "SELECT id_feriado, motivo, fecha_inicio, fecha_fin, borrado FROM FERIADOS";

        Feriado feriado;
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

            // Iteramos sobre el resultado y creamos objetos Feriado para cada fila
            while (resultSet.next()) {
                feriado = new Feriado();
                feriado.setId_feriado(resultSet.getInt(1));
                feriado.setMotivo(resultSet.getString(2));
                feriado.setFechaInicio(resultSet.getDate(3).toLocalDate());
                feriado.setFechaFin(resultSet.getDate(4).toLocalDate());
                feriado.setBorrado(resultSet.getBoolean(5));

                feriados.add(feriado); // Agregamos el Feriado a la lista
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
        // Devolvemos la lista de Feriados
        return feriados;
    }

}
