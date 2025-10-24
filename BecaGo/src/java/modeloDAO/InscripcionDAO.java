/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeloDAO;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import modelo.Inscripcion;

/**
 *
 * @author James
 */
public class InscripcionDAO {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int resp;

    public int Agregar(Inscripcion inscripcion) {
        String sql = "insert into Inscripcion(idActividad, idEstudiante, fechaInscripcion) values(?,?,?)";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, inscripcion.getIdActividad());
            ps.setInt(2, inscripcion.getIdEstudiante());
            ps.setTimestamp(3, inscripcion.getFechaInscripcion());
            resp = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resp;
    }

    public boolean estaInscrito(int idEstudiante, int idActividad) {
        boolean inscrito = false;
        String sql = "SELECT COUNT(*) FROM Inscripcion WHERE idEstudiante = ? AND idActividad = ?";

        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idEstudiante);
            ps.setInt(2, idActividad);
            rs = ps.executeQuery();

            if (rs.next()) {
                int cantidad = rs.getInt(1);
                if (cantidad > 0) {
                    inscrito = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return inscrito;
    }

}
