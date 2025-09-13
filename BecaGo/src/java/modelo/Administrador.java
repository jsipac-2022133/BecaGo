/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author SIPAC
 */
public class Administrador {
    private int idAdmin;
    private String nombreAdmin;
    private String apellidoAdmin;
    private String telefono;
    private String correoAdmin;
    private String passwordAdmin;
    private String nombreDepartamento;

    public Administrador() {
    }

    public Administrador(int idAdmin, String nombreAdmin, String apellidoAdmin, String telefono, String correoAdmin, String passwordAdmin, String nombreDepartamento) {
        this.idAdmin = idAdmin;
        this.nombreAdmin = nombreAdmin;
        this.apellidoAdmin = apellidoAdmin;
        this.telefono = telefono;
        this.correoAdmin = correoAdmin;
        this.passwordAdmin = passwordAdmin;
        this.nombreDepartamento = nombreDepartamento;
    }

    public int getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
    }

    public String getNombreAdmin() {
        return nombreAdmin;
    }

    public void setNombreAdmin(String nombreAdmin) {
        this.nombreAdmin = nombreAdmin;
    }

    public String getApellidoAdmin() {
        return apellidoAdmin;
    }

    public void setApellidoAdmin(String apellidoAdmin) {
        this.apellidoAdmin = apellidoAdmin;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreoAdmin() {
        return correoAdmin;
    }

    public void setCorreoAdmin(String correoAdmin) {
        this.correoAdmin = correoAdmin;
    }

    public String getPasswordAdmin() {
        return passwordAdmin;
    }

    public void setPasswordAdmin(String passwordAdmin) {
        this.passwordAdmin = passwordAdmin;
    }

    public String getNombreDepartamento() {
        return nombreDepartamento;
    }

    public void setNombreDepartamento(String nombreDepartamento) {
        this.nombreDepartamento = nombreDepartamento;
    }
    
    
}
