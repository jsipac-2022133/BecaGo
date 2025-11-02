/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.sql.Timestamp;

/**
 *
 * @author James
 */
public class ActividadEstudianteDTO {

    private String nombreActividad;
    private String nombreEstudiante;
    private String apellidoEstudiante;
    private String correoEstudiante;
    private boolean estado;
    private int cuposDisponibles;
    private Timestamp fechaActividad;
    private int idInscripcion;

    public ActividadEstudianteDTO() {
    }

    public ActividadEstudianteDTO(String nombreActividad, String nombreEstudiante, String apellidoEstudiante, String correoEstudiante, boolean estado, int cuposDisponibles, Timestamp fechaActividad, int idInscripcion) {
        this.nombreActividad = nombreActividad;
        this.nombreEstudiante = nombreEstudiante;
        this.apellidoEstudiante = apellidoEstudiante;
        this.correoEstudiante = correoEstudiante;
        this.estado = estado;
        this.cuposDisponibles = cuposDisponibles;
        this.fechaActividad = fechaActividad;
        this.idInscripcion = idInscripcion;
    }       

    public String getNombreActividad() {
        return nombreActividad;
    }

    public void setNombreActividad(String nombreActividad) {
        this.nombreActividad = nombreActividad;
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

    public String getCorreoEstudiante() {
        return correoEstudiante;
    }

    public void setCorreoEstudiante(String correoEstudiante) {
        this.correoEstudiante = correoEstudiante;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public int getCuposDisponibles() {
        return cuposDisponibles;
    }

    public void setCuposDisponibles(int cuposDisponibles) {
        this.cuposDisponibles = cuposDisponibles;
    }

    public Timestamp getFechaActividad() {
        return fechaActividad;
    }

    public void setFechaActividad(Timestamp fechaActividad) {
        this.fechaActividad = fechaActividad;
    }

    public int getIdInscripcion() {
        return idInscripcion;
    }

    public void setIdInscripcion(int idInscripcion) {
        this.idInscripcion = idInscripcion;
    }

    
}
