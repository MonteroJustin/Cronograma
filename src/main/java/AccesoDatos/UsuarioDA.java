/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AccesoDatos;

import Configuracion.Config;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author PC
 */
public class UsuarioDA {

    private Connection _cnn = null;//Representa la Conexión a la base de datos!

    /**
     * Clase de acceso a datos para la entidad Usuario. Maneja la conexión a la
     * base de datos y las operaciones relacionadas con usuarios.
     *
     * @throws SQLException Si ocurre un error de SQL al establecer la conexión
     * con la base de datos.
     * @throws Exception Si ocurre una excepción no controlada durante la
     * creación del objeto.
     */
    public UsuarioDA() throws SQLException, Exception {
        String cad = Config.getConnectionString();

        try {
            _cnn = DriverManager.getConnection(cad);
        } catch (SQLException sqlE) {
            throw sqlE;
        } catch (Exception e) {
            throw new Exception("Exepción Generica CREADA: " + e.getMessage());
        }
    }

    /**
     * Realiza el inicio de sesión en el sistema utilizando un nombre de usuario
     * y contraseña.
     *
     * @param usuario El nombre de usuario proporcionado para el inicio de
     * sesión.
     * @param contraseña La contraseña
     * @throws SQLException Si ocurre un error de SQL al interactuar con la base
     * de datos.
     * @throws Exception Si ocurre una excepción no controlada durante el
     * proceso.
     */
    public int inicioSistema(String usuario, String contraseña) throws SQLException, Exception {
        boolean bandera;
        ResultSet rs = null;
        int result;

        String query = "Select rol from USUARIOS";
        query = String.format("%s Where usuario = %s and contraseña = %s", query, usuario, contraseña);

        try {
            Statement st = _cnn.createStatement(); // Abrir la conexión
            rs = st.executeQuery(query);

            if (rs.next()) {
                boolean rolValue = rs.getBoolean("rol"); // Obtener el valor del campo "rol"
                bandera = rolValue; // Asignar el valor de "rol" a la bandera
                if (bandera == true) {
                    result = 2;
                } else {
                    result = 1;
                }
            } else {
                result = 3;
            }
        } catch (SQLException sqlE) {
            throw sqlE;
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (rs != null) {
                    rs.close(); // Cerrar el ResultSet si no es nulo
                }
                if (_cnn != null) {
                    _cnn.close(); // Cerrar la conexión si no es nula
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

}
