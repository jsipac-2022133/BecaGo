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
public class Estudiante {

    private int idEstudiante;
    private String nombreEstudiante;
    private String apellidoEstudiante;
    private String telefono;
    private String correoEstudiante;
    private String passwordEstudiante;
    private String carrera;
    private int horasAsignadas;
    private int horasCumplidas;

    public Estudiante() {
    }

    public Estudiante(int idEstudiante, String nombreEstudiante, String apellidoEstudiante, String telefono, String correoEstudiante, String passwordEstudiante, String carrera, int horasAsignadas, int horasCumplidas) {
        this.idEstudiante = idEstudiante;
        this.nombreEstudiante = nombreEstudiante;
        this.apellidoEstudiante = apellidoEstudiante;
        this.telefono = telefono;
        this.correoEstudiante = correoEstudiante;
        this.passwordEstudiante = passwordEstudiante;
        this.carrera = carrera;
        this.horasAsignadas = horasAsignadas;
        this.horasCumplidas = horasCumplidas;
    }

    public int getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public String getNombreEstudiante() {
        return nombreEstudiante;
    }

    public void setNombreEstudiante(String nombreEstudiante) {
        this.nombreEstudiante = nombreEstudiante;
    }

    public String getApellidoEstudiante() {
        return apellidoEstudiante;
    }

    public void setApellidoEstudiante(String apellidoEstudiante) {
        this.apellidoEstudiante = apellidoEstudiante;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreoEstudiante() {
        return correoEstudiante;
    }

    public void setCorreoEstudiante(String correoEstudiante) {
        this.correoEstudiante = correoEstudiante;
    }

    public String getPasswordEstudiante() {
        return passwordEstudiante;
    }

    public void setPasswordEstudiante(String passwordEstudiante) {
        this.passwordEstudiante = passwordEstudiante;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public int getHorasAsignadas() {
        return horasAsignadas;
    }

    public void setHorasAsignadas(int horasAsignadas) {
        this.horasAsignadas = horasAsignadas;
    }

    public int getHorasCumplidas() {
        return horasCumplidas;
    }

    public void setHorasCumplidas(int horasCumplidas) {
        this.horasCumplidas = horasCumplidas;
    }

    
    
    
}
