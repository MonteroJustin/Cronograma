/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AccesoDatos;

import Configuracion.Config;
import Entidades.Modulo;
import Entidades.Programa;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author PC
 */
public class ModuloDA {

    /**
     * Retorna una lista de objetos Modulo con los registros de la tabla
     * MODULOS.
     *
     * @return Una lista de objetos Modulo.
     * @throws SQLException Si ocurre un error durante la operación de base de
     * datos.
     */
    public ArrayList<Modulo> listarRegistros() throws SQLException {

        ArrayList<Modulo> modulos = new ArrayList<>();
        String query = "SELECT id_modulo, codigo, modulo, fecha_inicio, fecha_fin, total_horas, requisitos, id_programa, borrado FROM MODULOS";
        Modulo modulo;
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

            // Iteramos sobre el resultado y creamos objetos Modulo para cada fila
            while (resultSet.next()) {
                modulo = new Modulo();
                modulo.setId_modulo(resultSet.getInt("id_modulo"));
                modulo.setCodigo(resultSet.getString("codigo"));
                modulo.setModulo(resultSet.getString("modulo"));
                modulo.setFechaInicio(resultSet.getObject("fecha_inicio", LocalDate.class));
                modulo.setFechaFin(resultSet.getObject("fecha_fin", LocalDate.class));
                modulo.setTotalHoras(resultSet.getDouble("total_horas"));
                modulo.setRequisitos(resultSet.getString("requisitos"));
                modulo.setBorrado(resultSet.getBoolean("borrado"));

                Programa programa = new Programa();
                programa.setIdPrograma(resultSet.getInt("id_programa"));
                // Establecer otras propiedades de Programa aquí según las columnas del resultado

                modulo.setId_Programa(programa);

                modulos.add(modulo); // Agregamos el Modulo a la lista
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
        // Devolvemos la lista de Módulos
        return modulos;
    }

}
