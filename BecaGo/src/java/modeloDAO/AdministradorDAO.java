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
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int resp;

    public int Agregar(Administrador admin) {
        String sql = "INSERT INTO Administrador(nombreAdmin, apellidoAdmin, telefono, correoAdmin, passwordAdmin, nombreDepartamento) VALUES(?,?,?,?,?,?)";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
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

    public Administrador validar(String correo, String password) {
        Administrador admin = new Administrador();
        String sql = "SELECT * FROM Administrador WHERE correoAdmin=? AND passwordAdmin=?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, correo);
            ps.setString(2, password);
            rs = ps.executeQuery();
            while (rs.next()) {
                admin.setIdAdmin(rs.getInt("idAdmin"));
                admin.setNombreAdmin(rs.getString("nombreAdmin"));
                admin.setApellidoAdmin(rs.getString("apellidoAdmin"));
                admin.setTelefono(rs.getString("telefono"));
                admin.setCorreoAdmin(rs.getString("correoAdmin"));
                admin.setPasswordAdmin(rs.getString("passwordAdmin"));
                admin.setNombreDepartamento(rs.getString("nombreDepartamento"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return admin;
    }

    public boolean existeCorreo(String correo) {
        String sql = "SELECT COUNT(*) FROM Administrador WHERE correoAdmin = ?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, correo);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean existeTel(String telefono) {
        String sql = "SELECT COUNT(*) FROM Administrador WHERE telefono = ?";
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

    public boolean actualizar(Administrador admin) {
        String sql = "UPDATE Administrador SET nombreAdmin=?, apellidoAdmin=?, telefono=?, nombreDepartamento=?, passwordAdmin=? WHERE idAdmin=?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, admin.getNombreAdmin());
            ps.setString(2, admin.getApellidoAdmin());
            ps.setString(3, admin.getTelefono());
            ps.setString(4, admin.getNombreDepartamento());

            // Si la contraseña está vacía, mantener la actual
            if (admin.getPasswordAdmin() != null && !admin.getPasswordAdmin().isEmpty()) {
                ps.setString(5, admin.getPasswordAdmin());
            } else {
                String sqlPass = "SELECT passwordAdmin FROM Administrador WHERE idAdmin=?";
                PreparedStatement psPass = con.prepareStatement(sqlPass);
                psPass.setInt(1, admin.getIdAdmin());
                ResultSet rsPass = psPass.executeQuery();
                if (rsPass.next()) {
                    ps.setString(5, rsPass.getString("passwordAdmin"));
                }
                rsPass.close();
                psPass.close();
            }

            ps.setInt(6, admin.getIdAdmin());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Administrador buscarPorId(int id) {
        Administrador admin = new Administrador();
        String sql = "SELECT * FROM Administrador WHERE idAdmin = ?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                admin.setIdAdmin(rs.getInt("idAdmin"));
                admin.setNombreAdmin(rs.getString("nombreAdmin"));
                admin.setApellidoAdmin(rs.getString("apellidoAdmin"));
                admin.setTelefono(rs.getString("telefono"));
                admin.setCorreoAdmin(rs.getString("correoAdmin"));
                admin.setPasswordAdmin(rs.getString("passwordAdmin"));
                admin.setNombreDepartamento(rs.getString("nombreDepartamento"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return admin;
    }
}
