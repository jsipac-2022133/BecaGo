/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author SIPAC
 */
public class Conexion {
    Connection conexion;
    
    public Connection Conexion(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_becago?useSSL=false", "root", "botdiego1");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conexion;
    }
}
