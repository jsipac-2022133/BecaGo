/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;
import java.sql.Timestamp;
/**
 *
 * @author SIPAC
 */
public class Actividad {
    
    private int idActividad;
    private String nombreActividad;
    private String descripcion;    
    private Timestamp fechaActividad;
    private String ubicacion;
    private double horasDadas;
    private int cuposDisponibles;
    private int idAdmin;

    public Actividad() {
    }

    public Actividad(int idActividad, String nombreActividad, String descripcion, Timestamp fechaActividad, String ubicacion, double horasDadas, int cuposDisponibles, int idAdmin) {
        this.idActividad = idActividad;
        this.nombreActividad = nombreActividad;
        this.descripcion = descripcion;
        this.fechaActividad = fechaActividad;
        this.ubicacion = ubicacion;
        this.horasDadas = horasDadas;
        this.cuposDisponibles = cuposDisponibles;
        this.idAdmin = idAdmin;
    }

    public int getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(int idActividad) {
        this.idActividad = idActividad;
    }

    public String getNombreActividad() {
        return nombreActividad;
    }

    public void setNombreActividad(String nombreActividad) {
        this.nombreActividad = nombreActividad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Timestamp getFechaActividad() {
        return fechaActividad;
    }

    public void setFechaActividad(Timestamp fechaActividad) {
        this.fechaActividad = fechaActividad;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public double getHorasDadas() {
        return horasDadas;
    }

    public void setHorasDadas(double horasDadas) {
        this.horasDadas = horasDadas;
    }

    public int getCuposDisponibles() {
        return cuposDisponibles;
    }

    public void setCuposDisponibles(int cuposDisponibles) {
        this.cuposDisponibles = cuposDisponibles;
    }

    public int getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
    }
    
    
}
