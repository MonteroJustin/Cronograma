/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LogicaNegocio;

import AccesoDatos.UsuarioDA;
import java.sql.SQLException;

/**
 *
 * @author PC
 */
public class UsuarioBL {

    private UsuarioDA usuarioDA;

    /**
     * Inicia sesión en el sistema verificando las credenciales de usuario y
     * contraseña.
     * @param usuario El nombre de usuario proporcionado.
     * @param contraseña La contraseña proporcionada.
     * @return Un valor entero 
     */
    public int inicioSistema(String usuario, String contraseña) throws Exception, SQLException {
        int result;
        try {
            usuarioDA = new UsuarioDA();
            result = usuarioDA.inicioSistema(usuario, contraseña);
        } catch (SQLException sqlE) {
            throw sqlE;
        } catch (Exception e) {
            throw e;
        }
        return result;
    }
}
