package modelo;

import java.io.Serializable;
import java.sql.Timestamp;

public class Actividad implements Serializable {
    private int idActividad;
    private String nombreActividad;
    private String descripcion;
    private Timestamp fechaActividad;
    private String ubicacion;
    private double horasDadas;
    private int cuposDisponibles;
    private int idAdmin;
    private byte[] imagen;

    public Actividad() {
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

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }
}
