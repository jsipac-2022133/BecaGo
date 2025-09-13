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
import modelo.Administrador;

/**
 *
 * @author SIPAC
 */
public class AdministradorDAO {
    Conexion cn=new Conexion();
    Connection con; 
    PreparedStatement ps;
    ResultSet rs;
    int resp;
    
    
    public int Agregar(Administrador admin){
       String sql="insert into Administrador(nombreAdmin, apellidoAdmin, telefono, correoAdmin, passwordAdmin, nombreDepartamento) values(?,?,?,?,?,?)"; 
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            ps.setString(1, admin.getNombreAdmin());
            ps.setString(2, admin.getApellidoAdmin());
            ps.setString(3, admin.getTelefono());
            ps.setString(4, admin.getCorreoAdmin());
            ps.setString(5, admin.getPasswordAdmin());
            ps.setString(6, admin.getNombreDepartamento());
            resp = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resp;
    }
   
}
