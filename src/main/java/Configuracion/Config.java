/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Configuracion;

/**
 *
 * @author PC
 */
public class Config {

    /**
     * Genera y devuelve la cadena de conexión a la base de datos SQL Server.
     *
     * @return La cadena de conexión formateada para conectarse a la base de
     * datos.
     */
    public static String getConnectionString() {

        String user = "javaUser";
        String pass = "javaPass";

        return "jdbc:sqlserver://localhost:1433;"
                + "databaseName=CRONOGRAMA_DB;"
                + "user=javaUser;"
                + "password=javaPass;"
                + "TrustServerCertificate=True";

    }

}
