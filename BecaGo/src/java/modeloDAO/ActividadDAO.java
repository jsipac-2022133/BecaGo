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
import modelo.Actividad;

/**
 *
 * @author SIPAC
 */
public class ActividadDAO {
    Conexion cn=new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int resp;
    
    public int Agregar(Actividad actividad){
        String sql="insert into Actividad(nombreActividad, descripcion, "
                + "fechaActividad, ubicacion, horasDadas, cuposDisponibles, idAdmin) values(?,?,?,?,?,?,?)";
        
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            ps.setString(1, actividad.getNombreActividad());
            ps.setString(2, actividad.getDescripcion());
            ps.setTimestamp(3, actividad.getFechaActividad());
            ps.setString(4, actividad.getUbicacion());
            ps.setDouble(5, actividad.getHorasDadas());
            ps.setInt(6, actividad.getCuposDisponibles());
            ps.setInt(7, actividad.getIdAdmin());
            resp=ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resp;
    }
    
}
