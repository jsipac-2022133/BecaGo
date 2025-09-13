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
    
    
}
