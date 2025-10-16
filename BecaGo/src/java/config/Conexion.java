/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    Connection con;
    
    public Connection Conexion(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_becago?useSSL=false&serverTimezone=UTC", "root", "luis1234");
            System.out.println("DEBUG: Conexión a BD exitosa");
        } catch (Exception e) {
            System.out.println("DEBUG: Error de conexión a BD: " + e.getMessage());
            e.printStackTrace();
        }
        return con;
    }
}
