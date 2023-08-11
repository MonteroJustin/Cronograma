package AccesoDatos;

import Configuracion.Config;
import Entidades.Cronograma;
import Entidades.Profesor;
import Entidades.Programa;
import Entidades.Modulo;
import Entidades.Grupo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CronogramaDA {

    private Connection _cnn = null;//Representa la Conexión a la base de datos!

    /**
     * Recupera una lista de cronogramas registrados en el sistema.
     *
     * @return Una lista de objetos Cronograma que representan los cronogramas
     * registrados.
     * @throws SQLException Si se produce un error al interactuar con la base de
     * datos.
     */
    public ArrayList<Cronograma> listarRegistros() throws SQLException {

        ArrayList<Cronograma> Cronogramas = new ArrayList<>();
        String query = "SELECT c.id_cronograma, p.nombre, p.ape1, p.ape2, g.grupo, c.anio, pr.programa, m.modulo,"
                + " c.fecha_inicio, c.fecha_fin, c.horas_dia, c.L, c.K, c.M, c.J, c.V, c.S, c.D, c.estado"
                + " FROM CRONOGRAMAS c "
                + "INNER JOIN MODULOS m ON c.id_modulo = m.id_modulo "
                + "INNER JOIN PROFESORES p ON c.id_profesor = p.id_profesor "
                + "INNER JOIN GRUPOS g ON c.id_grupo = g.id_grupo "
                + "INNER JOIN PROGRAMAS pr ON c.id_programa = pr.id_programa";

        Cronograma cronograma;
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

            // Iteramos sobre el resultado y creamos objetos Cronograma para cada fila
            while (resultSet.next()) {
                cronograma = new Cronograma();
                cronograma.setId_cronograma(resultSet.getInt(1));

                Profesor profesor = new Profesor();
                profesor.setNombre(resultSet.getString(2));
                profesor.setApe1(resultSet.getString(3));
                profesor.setApe2(resultSet.getString(4));
                cronograma.setId_profesor(profesor);

                Grupo grupo = new Grupo();
                grupo.setGrupo(resultSet.getString(5));
                cronograma.setId_grupo(grupo);

                Programa programa = new Programa();
                programa.setPrograma(resultSet.getString(7)); // índice 6 saltado
                cronograma.setId_programa(programa);

                Modulo modulo = new Modulo();
                modulo.setModulo(resultSet.getString(8)); // índice 7 saltado

                cronograma.setAnio(resultSet.getDate(6).toLocalDate()); // índice 5
                cronograma.setFechaInicio(resultSet.getDate(9).toLocalDate());
                cronograma.setFechaFin(resultSet.getDate(10).toLocalDate());

                cronograma.setHorasDia(resultSet.getDouble(11));
                cronograma.setL(resultSet.getBoolean(12));
                cronograma.setK(resultSet.getBoolean(13));
                cronograma.setM(resultSet.getBoolean(14));
                cronograma.setJ(resultSet.getBoolean(15));
                cronograma.setV(resultSet.getBoolean(16));
                cronograma.setS(resultSet.getBoolean(17));
                cronograma.setD(resultSet.getBoolean(18));
                cronograma.setId_modulo(modulo);

                cronograma.setEstado(resultSet.getBoolean(19));

                Cronogramas.add(cronograma); // Agregamos el Cronograma a la lista
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
        // Devolvemos la lista de Cronogramas
        return Cronogramas;
    }

    /**
     * Inserta un nuevo registro de cronograma en la base de datos.
     *
     * @param cronograma El objeto Cronograma que se va a insertar en la base de
     * datos.
     * @return El ID del cronograma recién insertado, o -1 si no se pudo
     * guardar.
     * @throws SQLException Si ocurre un error de SQL al interactuar con la base
     * de datos.
     * @throws Exception Si ocurre una excepción no controlada durante el
     * proceso.
     */
    public int insert(Cronograma cronograma) throws SQLException, Exception {
        int result = -1; // Valor que se retorna en caso de que no se guarde

        String query = "INSERT INTO CRONOGRAMAS (id_profesor, id_programa, id_grupo, id_modulo, anio, horas_dia, fecha_inicio, fecha_fin, L, K, M, J, V, S, D, estado, borrado) "
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        ResultSet rs = null; // Solo para leer el ID autogenerado en SQL

        try ( Connection conexionSQL = DriverManager.getConnection(Config.getConnectionString());  PreparedStatement ps = conexionSQL.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            // Cargar los valores de los parámetros desde el objeto cronograma
            ps.setInt(1, cronograma.getId_profesor().getId_profesor());
            ps.setInt(2, cronograma.getId_programa().getIdPrograma());
            ps.setInt(3, cronograma.getId_grupo().getId_grupo());
            ps.setInt(4, cronograma.getId_modulo().getId_modulo());
            ps.setDate(5, java.sql.Date.valueOf(cronograma.getAnio()));
            ps.setDouble(6, cronograma.getHorasDia());
            ps.setDate(7, java.sql.Date.valueOf(cronograma.getFechaInicio()));
            ps.setDate(8, java.sql.Date.valueOf(cronograma.getFechaFin()));
            ps.setBoolean(9, cronograma.isL());
            ps.setBoolean(10, cronograma.isK());
            ps.setBoolean(11, cronograma.isM());
            ps.setBoolean(12, cronograma.isJ());
            ps.setBoolean(13, cronograma.isV());
            ps.setBoolean(14, cronograma.isS());
            ps.setBoolean(15, cronograma.isD());
            ps.setBoolean(16, cronograma.isEstado());
            ps.setBoolean(17, cronograma.isBorrado());

            // Ejecutar la operación del QUERY
            ps.executeUpdate();

            // Pasar el nuevo ID al ResultSet
            rs = ps.getGeneratedKeys();

            // Verificar y leer el ResultSet
            if (rs != null && rs.next()) {
                result = rs.getInt(1);
            }
        } catch (SQLException sqlE) {
            throw sqlE;
        } catch (Exception e) {
            throw e;
        } finally {
            if (rs != null) {
                rs.close();
            }
        }

        return result;
    }

}
