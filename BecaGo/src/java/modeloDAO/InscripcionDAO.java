/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeloDAO;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import modelo.Inscripcion;
import dto.ActividadEstudianteDTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author James
 */
public class InscripcionDAO {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int resp;

    public int Agregar(Inscripcion inscripcion) {
        String sql = "insert into Inscripcion(idActividad, idEstudiante, fechaInscripcion) values(?,?,?)";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, inscripcion.getIdActividad());
            ps.setInt(2, inscripcion.getIdEstudiante());
            ps.setTimestamp(3, inscripcion.getFechaInscripcion());
            resp = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resp;
    }

    public boolean estaInscrito(int idEstudiante, int idActividad) {
        boolean inscrito = false;
        String sql = "SELECT COUNT(*) FROM Inscripcion WHERE idEstudiante = ? AND idActividad = ?";

        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idEstudiante);
            ps.setInt(2, idActividad);
            rs = ps.executeQuery();

            if (rs.next()) {
                int cantidad = rs.getInt(1);
                if (cantidad > 0) {
                    inscrito = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return inscrito;
    }

    // Este método incorpora atributos de 3 entidades (NO TOCAR)
    public List<ActividadEstudianteDTO> listarPorInscripcionColectivo() {
        List<ActividadEstudianteDTO> lista = new ArrayList<>();

        String sql = "SELECT a.nombreActividad, e.nombreEstudiante, e.apellidoEstudiante, "
                + "e.correoEstudiante, i.estado, a.cuposDisponibles, a.fechaActividad, i.idInscripcion  "
                + "FROM Actividad AS a "
                + "INNER JOIN Inscripcion AS i ON i.idActividad = a.idActividad "
                + "INNER JOIN Estudiante AS e ON i.idEstudiante = e.idEstudiante "
                + "ORDER BY a.nombreActividad, e.apellidoEstudiante, e.nombreEstudiante";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                ActividadEstudianteDTO dto = new ActividadEstudianteDTO();
                dto.setNombreActividad(rs.getString("nombreActividad"));
                dto.setNombreEstudiante(rs.getString("nombreEstudiante"));
                dto.setApellidoEstudiante(rs.getString("apellidoEstudiante"));
                dto.setCorreoEstudiante(rs.getString("correoEstudiante"));
                dto.setEstado(rs.getBoolean("estado"));
                dto.setCuposDisponibles(rs.getInt("cuposDisponibles"));
                dto.setFechaActividad(rs.getTimestamp("fechaActividad"));
                dto.setIdInscripcion((rs.getInt("idInscripcion")));
                lista.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public int AgregarHorasAEstudiante(int idInscripcion) {
        String sql = "update Inscripcion set estado=true where idInscripcion=?";

        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idInscripcion);
            resp = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resp;
    }

    public List<ActividadEstudianteDTO> listarActividadesCompletadas(int idEstudiante) {
        List<ActividadEstudianteDTO> lista = new ArrayList<>();

        String sql = "select a.nombreActividad, a.fechaActividad, a.horasDadas\n"
                + "from Actividad as a\n"
                + "inner join Inscripcion as i\n"
                + "on a.idActividad=i.idActividad\n"
                + "inner join Estudiante as e\n"
                + "on e.idEstudiante=i.idEstudiante\n"
                + "where i.idEstudiante=? and estado=1";
        
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            ps.setInt(1, idEstudiante);
            rs=ps.executeQuery();
            
            while(rs.next()){
                ActividadEstudianteDTO actividadEstudianteDTO=new ActividadEstudianteDTO();
                actividadEstudianteDTO.setNombreActividad(rs.getString("nombreActividad"));
                actividadEstudianteDTO.setFechaActividad(rs.getTimestamp("fechaActividad"));
                actividadEstudianteDTO.setHorasDadas(rs.getInt("horasDadas"));
                lista.add(actividadEstudianteDTO);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
}
