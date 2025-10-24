/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloDAO;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import modelo.Estudiante;

/**
 *
 * @author SIPAC
 */
public class EstudianteDAO {
    Conexion cn=new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int resp;
    
    public int Agregar(Estudiante estudiante){
        String sql="insert into Estudiante(nombreEstudiante, "
                + "apellidoEstudiante, telefono, correoEstudiante, "
                + "passwordEstudiante, carrera, horasAsignadas) values(?,?,?,?,?,?,?)";
        
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            ps.setString(1, estudiante.getNombreEstudiante());
            ps.setString(2, estudiante.getApellidoEstudiante());
            ps.setString(3, estudiante.getTelefono());
            ps.setString(4, estudiante.getCorreoEstudiante());
            ps.setString(5, estudiante.getPasswordEstudiante());
            ps.setString(6, estudiante.getCarrera());
            ps.setInt(7, estudiante.getHorasAsignadas());
            resp=ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resp;
    }
    
    public Estudiante validar(String correo, String password){
        Estudiante estudiante=new Estudiante();
        String sql="select * from Estudiante where correoEstudiante=? and passwordEstudiante=?";
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            ps.setString(1, correo);
            ps.setString(2, password);
            rs=ps.executeQuery();
            while(rs.next()){
                estudiante.setIdEstudiante(rs.getInt("idEstudiante"));
                estudiante.setNombreEstudiante(rs.getString("nombreEstudiante"));
                estudiante.setApellidoEstudiante(rs.getString("apellidoEstudiante"));
                estudiante.setTelefono(rs.getString("telefono"));
                estudiante.setCorreoEstudiante(rs.getString("correoEstudiante"));
                estudiante.setPasswordEstudiante(rs.getString("passwordEstudiante"));
                estudiante.setCarrera(rs.getString("carrera"));
                estudiante.setHorasAsignadas(rs.getInt("horasAsignadas"));
                estudiante.setHorasCumplidas(rs.getInt("horasCumplidas"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return estudiante;
    }
    
    public boolean existeCorreo(String correo) {
        String sql = "SELECT COUNT(*) FROM Estudiante WHERE correoEstudiante = ?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, correo);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Si el conteo es mayor a 0, existe
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean existeTel(String telefono) {
        String sql = "SELECT COUNT(*) FROM Estudiante WHERE telefono = ?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, telefono);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
}
