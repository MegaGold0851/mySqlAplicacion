package com.megagold.mysqlennube_vuno;

import com.megagold.mysqlennube_vuno.Model.FichaStand;
import com.megagold.mysqlennube_vuno.Model.Stands;
import javafx.fxml.Initializable;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ConexionBD implements Initializable {
    public static Connection getConexion() throws SQLException {
        String url = "jdbc:mysql://byflscedlummka506z3r-mysql.services.clever-cloud.com:3306/byflscedlummka506z3r?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String usuario = "u44oi5gkhooyrph7";
        String contrasena = "QugOhVJKFSkq6k483W0q";

        return DriverManager.getConnection(url, usuario, contrasena);
    }

    public static void insertarStand(Stands stands) throws SQLException {
        Connection conexion = ConexionBD.getConexion();
        CallableStatement cs = conexion.prepareCall("{ call insertarStand(?, ?, ?, ?, ?, ?, ?, ?, ?) }");
        cs.setInt(1, stands.getFichaStand().getPoder());
        cs.setInt(2, stands.getFichaStand().getVelocidad());
        cs.setInt(3, stands.getFichaStand().getResistencia());
        cs.setInt(4, stands.getFichaStand().getRango());
        cs.setInt(5, stands.getFichaStand().getPotencial());
        cs.setString(6, stands.getNombre());
        cs.setString(7, stands.getParte());
        cs.setString(8, stands.getTipoDeStand());
        cs.registerOutParameter(9, Types.INTEGER);
        cs.execute();
        int idFichaStand = cs.getInt(9);
        System.out.println("Se ha insertado una nueva fila en la tabla Stands con el idFichaStand = " + idFichaStand);
    }

    public static List<Stands> obtenerStands() throws SQLException {
        List<Stands> listaStands = new ArrayList<>();

        try (Connection conexion = getConexion()) {
            String sql = "SELECT * FROM v_stands";
            PreparedStatement consulta = conexion.prepareStatement(sql);

            ResultSet resultados = consulta.executeQuery();

            while (resultados.next()) {
                Stands stands = new Stands();
                FichaStand fichaStand = new FichaStand();

                fichaStand.setIdFichaStand(resultados.getInt("idFichaStand"));
                fichaStand.setPoder(resultados.getInt("poder"));
                fichaStand.setVelocidad(resultados.getInt("velocidad"));
                fichaStand.setResistencia(resultados.getInt("resistencia"));
                fichaStand.setRango(resultados.getInt("rango"));
                fichaStand.setPotencial(resultados.getInt("potencial"));

                stands.setIdStands(resultados.getInt("idStands"));
                stands.setNombre(resultados.getString("nombre"));
                stands.setParte(resultados.getString("parte"));
                stands.setTipoDeStand(resultados.getString("tipoDeStand"));
                stands.setFichaStand(fichaStand);

                listaStands.add(stands);
            }
        }
        return listaStands;
    }

    public void actualizarFila(Stands stands) {
        try {
            Connection conexion = ConexionBD.getConexion();
            CallableStatement statement = conexion.prepareCall("{CALL actualizarStand(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
            statement.setInt(1, stands.getFichaStand().getPoder());
            statement.setInt(2, stands.getFichaStand().getVelocidad());
            statement.setInt(3, stands.getFichaStand().getResistencia());
            statement.setInt(4, stands.getFichaStand().getRango());
            statement.setInt(5, stands.getFichaStand().getPotencial());
            statement.setString(6, stands.getNombre());
            statement.setString(7, stands.getParte());
            statement.setString(8, stands.getTipoDeStand());
            statement.setInt(9, stands.getFichaStand().getIdFichaStand());
            statement.setInt(10, stands.getIdStands());
            statement.executeUpdate();
            System.out.println("Fila actualizada correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void eliminarStand(int idStand, int idFichaStand) {
        try (Connection conn = getConexion();
             CallableStatement stmt = conn.prepareCall("{CALL eliminarStand(?, ?)}")) {
            stmt.setInt(1, idStand);
            stmt.setInt(2, idFichaStand);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            ConexionBD.getConexion();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

