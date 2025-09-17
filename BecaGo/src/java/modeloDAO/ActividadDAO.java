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
import java.util.ArrayList;
import java.util.List;
import modelo.Actividad;

/**
 *
 * @author SIPAC
 */
public class ActividadDAO {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int resp;

    public int agregar(Actividad actividad) {
        String sql = "insert into Actividad(nombreActividad, descripcion, "
                + "fechaActividad, ubicacion, horasDadas, cuposDisponibles, idAdmin) values(?,?,?,?,?,?,?)";

        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, actividad.getNombreActividad());
            ps.setString(2, actividad.getDescripcion());
            ps.setTimestamp(3, actividad.getFechaActividad());
            ps.setString(4, actividad.getUbicacion());
            ps.setDouble(5, actividad.getHorasDadas());
            ps.setInt(6, actividad.getCuposDisponibles());
            ps.setInt(7, actividad.getIdAdmin());
            resp = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resp;
    }

    
    public List<Actividad> listar() {
        String sql = "select idActividad, nombreActividad, descripcion, fechaActividad, "
                + "ubicacion, horasDadas, cuposDisponibles, idAdmin from Actividad";        
        
        List<Actividad> listaActividad = new ArrayList<>();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Actividad actividad = new Actividad();
                actividad.setIdActividad(rs.getInt("idActividad"));
                actividad.setNombreActividad(rs.getString("nombreActividad"));
                actividad.setDescripcion(rs.getString("descripcion"));
                actividad.setFechaActividad(rs.getTimestamp("fechaActividad"));
                actividad.setUbicacion(rs.getString("ubicacion"));
                actividad.setHorasDadas(rs.getDouble("horasDadas"));
                actividad.setCuposDisponibles(rs.getInt("cuposDisponibles"));
                actividad.setIdAdmin(rs.getInt("idAdmin"));
                listaActividad.add(actividad);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaActividad;
    }
    
    public Actividad buscarActividad(int id){
        Actividad actividad=new Actividad();
        String sql = "select idActividad, nombreActividad, descripcion, fechaActividad, "
                + "ubicacion, horasDadas, cuposDisponibles, idAdmin from Actividad where idActividad="+id;       
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                actividad.setIdActividad(rs.getInt("idActividad"));
                actividad.setNombreActividad(rs.getString("nombreActividad"));
                actividad.setDescripcion(rs.getString("descripcion"));
                actividad.setFechaActividad(rs.getTimestamp("fechaActividad"));
                actividad.setUbicacion(rs.getString("ubicacion"));
                actividad.setHorasDadas(rs.getDouble("horasDadas"));
                actividad.setCuposDisponibles(rs.getInt("cuposDisponibles"));
                actividad.setIdAdmin(rs.getInt("idAdmin"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return actividad;
    }

    public int actualizar(Actividad actividad){
        String sql="Update Actividad set nombreActividad=?, descripcion=?, fechaActividad=?, ubicacion=?, "
                + "horasDadas=?, cuposDisponibles=?, idAdmin=? where idActividad=?";
        
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
            ps.setInt(8, actividad.getIdActividad());
            resp=ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resp;
    }
    
    public void eliminar(int id){
        String sql="delete from Actividad where idActividad="+id;
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
