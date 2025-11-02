package modeloDAO;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modelo.Actividad;

public class ActividadDAO {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int resp;

    public boolean agregar(Actividad actividad) {
        System.out.println("DEBUG: Intentando agregar actividad - " + actividad.getNombreActividad());
        String sql = "INSERT INTO Actividad(nombreActividad, descripcion, fechaActividad, ubicacion, horasDadas, cuposDisponibles, idAdmin, imagen) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, actividad.getNombreActividad());
            ps.setString(2, actividad.getDescripcion());
            ps.setTimestamp(3, actividad.getFechaActividad());
            ps.setString(4, actividad.getUbicacion());
            ps.setDouble(5, actividad.getHorasDadas());
            ps.setInt(6, actividad.getCuposDisponibles());
            ps.setInt(7, actividad.getIdAdmin());

            System.out.println("DEBUG: Imagen es null: " + (actividad.getImagen() == null));

            if (actividad.getImagen() != null) {
                ps.setBytes(8, actividad.getImagen());
                System.out.println("DEBUG: Imagen tamaño: " + actividad.getImagen().length + " bytes");
            } else {
                ps.setNull(8, java.sql.Types.BLOB);
                System.out.println("DEBUG: Imagen es NULL en BD");
            }

            int resultado = ps.executeUpdate();
            System.out.println("DEBUG: Filas afectadas: " + resultado);
            return resultado > 0;
        } catch (Exception e) {
            System.out.println("DEBUG: Error en agregar: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    public List<Actividad> listar() {
        String sql = "SELECT idActividad, nombreActividad, descripcion, fechaActividad, "
           + "ubicacion, horasDadas, cuposDisponibles, idAdmin, imagen "
           + "FROM Actividad "
           + "ORDER BY fechaActividad ASC";
        // Ordena del más viejo al más reciente (ASC), pero puede ser con el más reciente primero (DESC)
        
        List<Actividad> listaActividad = new ArrayList<>();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Actividad actividad = new Actividad();
                actividad.setIdActividad(rs.getInt("idActividad"));
                actividad.setNombreActividad(rs.getString("nombreActividad"));
                actividad.setDescripcion(rs.getString("descripcion"));
                actividad.setFechaActividad(rs.getTimestamp("fechaActividad"));
                actividad.setUbicacion(rs.getString("ubicacion"));
                actividad.setHorasDadas(rs.getDouble("horasDadas"));
                actividad.setCuposDisponibles(rs.getInt("cuposDisponibles"));
                actividad.setIdAdmin(rs.getInt("idAdmin"));
                actividad.setImagen(rs.getBytes("imagen"));
                listaActividad.add(actividad);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaActividad;
    }
    
    public Actividad buscarActividad(int id){
        Actividad actividad = new Actividad();
        String sql = "SELECT idActividad, nombreActividad, descripcion, fechaActividad, "
                + "ubicacion, horasDadas, cuposDisponibles, idAdmin, imagen FROM Actividad WHERE idActividad = ?";       
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while(rs.next()){
                actividad.setIdActividad(rs.getInt("idActividad"));
                actividad.setNombreActividad(rs.getString("nombreActividad"));
                actividad.setDescripcion(rs.getString("descripcion"));
                actividad.setFechaActividad(rs.getTimestamp("fechaActividad"));
                actividad.setUbicacion(rs.getString("ubicacion"));
                actividad.setHorasDadas(rs.getDouble("horasDadas"));
                actividad.setCuposDisponibles(rs.getInt("cuposDisponibles"));
                actividad.setIdAdmin(rs.getInt("idAdmin"));
                actividad.setImagen(rs.getBytes("imagen"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return actividad;
    }

    public int actualizar(Actividad actividad){
        String sql = "UPDATE Actividad SET nombreActividad=?, descripcion=?, fechaActividad=?, ubicacion=?, "
                + "horasDadas=?, cuposDisponibles=?, idAdmin=?, imagen=? WHERE idActividad=?";
        
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, actividad.getNombreActividad());
            ps.setString(2, actividad.getDescripcion());
            ps.setTimestamp(3, actividad.getFechaActividad());
            ps.setString(4, actividad.getUbicacion());
            ps.setDouble(5, actividad.getHorasDadas());
            ps.setInt(6, actividad.getCuposDisponibles());
            ps.setInt(7, actividad.getIdAdmin());
            
            if (actividad.getImagen() != null) {
                ps.setBytes(8, actividad.getImagen());
            } else {
                ps.setNull(8, java.sql.Types.BLOB);
            }
            
            ps.setInt(9, actividad.getIdActividad());
            resp = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resp;
    }
    
    public void eliminar(int id){
        String sql = "DELETE FROM Actividad WHERE idActividad = ?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public int reducirCupo(Actividad actividad, int nuevoCupo, int idActividad){
        String sql="update Actividad set cuposDisponibles=? where idActividad=?";
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            ps.setInt(1, nuevoCupo);
            ps.setInt(2, idActividad);
            resp=ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resp;
    }
    
    public List<Actividad> listarPorInscripcionIndividual(int idEstudiante) {
        String sql = "select a.nombreActividad, a.fechaActividad, a.ubicacion \n"
                + "from Actividad as a \n"
                + "inner join Inscripcion as i\n"
                + "on i.idActividad=a.idActividad\n"
                + "where i.idEstudiante=?";
        
        List<Actividad> listaActividad=new ArrayList<>();
        
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            ps.setInt(1, idEstudiante);
            rs=ps.executeQuery();
            
            while(rs.next()){
                Actividad actividad=new Actividad();
                actividad.setNombreActividad(rs.getString("nombreActividad"));
                actividad.setFechaActividad(rs.getTimestamp("fechaActividad"));
                actividad.setUbicacion(rs.getString("ubicacion"));
                listaActividad.add(actividad);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaActividad;
    }
    
    
    
}
