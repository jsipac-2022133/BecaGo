package controlador;

import java.io.IOException;
import java.io.InputStream;
import java.io.ByteArrayOutputStream;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import modelo.Actividad;
import modelo.Administrador;
import modelo.Estudiante;
import modelo.Inscripcion;
import modeloDAO.ActividadDAO;
import modeloDAO.AdministradorDAO;
import modeloDAO.EstudianteDAO;
import modeloDAO.InscripcionDAO;

@WebServlet(name = "Controlador", urlPatterns = {"/Controlador"})
@MultipartConfig(maxFileSize = 16177215)
public class Controlador extends HttpServlet {

    Administrador administrador = new Administrador();
    AdministradorDAO administradorDAO = new AdministradorDAO();
    Estudiante estudiante = new Estudiante();
    EstudianteDAO estudianteDAO = new EstudianteDAO();
    Actividad actividad = new Actividad();
    ActividadDAO actividadDAO = new ActividadDAO();
    Inscripcion inscripcion = new Inscripcion();
    InscripcionDAO inscripcionDAO = new InscripcionDAO();
    int idActividad;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String menu = request.getParameter("menu");
        String accion = request.getParameter("accion");

        HttpSession sessionActiva = request.getSession();
        Administrador adminEnSesion = (Administrador) sessionActiva.getAttribute("administradorEnSesion");
        Estudiante estudianteEnSesion = (Estudiante) sessionActiva.getAttribute("estudianteEnSesion");

        if (menu == null || menu.equals("Login")) {
            request.getRequestDispatcher("vistas/Login.jsp").forward(request, response);
            return;
        }

        // -------------------- REGISTRO --------------------
        if (menu.equals("Register")) {
            if (accion == null) {
                request.getRequestDispatcher("vistas/Register.jsp").forward(request, response);
                return;
            }

            if (accion.equals("Agregar Admin")) {
                String nombreAdmin = request.getParameter("txtNombre");
                String apellidoAdmin = request.getParameter("txtApellido");
                String telefonoAdmin = request.getParameter("txtTelefono");
                String correoAdmin = request.getParameter("txtCorreo");
                String passwordAdmin = request.getParameter("txtPassword");
                String departamentoAdmin = request.getParameter("txtUnidadDepto");

                java.util.ArrayList<String> errores = new java.util.ArrayList<>();

                if (administradorDAO.existeCorreo(correoAdmin) || estudianteDAO.existeCorreo(correoAdmin)) {
                    errores.add("El correo ya está registrado");
                }
                if (administradorDAO.existeTel(telefonoAdmin) || estudianteDAO.existeTel(telefonoAdmin)) {
                    errores.add("El teléfono ya está registrado");
                }

                if (!errores.isEmpty()) {
                    request.setAttribute("errores", errores);
                    request.getRequestDispatcher("vistas/Register.jsp").forward(request, response);
                    return;
                }

                administrador.setNombreAdmin(nombreAdmin);
                administrador.setApellidoAdmin(apellidoAdmin);
                administrador.setTelefono(telefonoAdmin);
                administrador.setCorreoAdmin(correoAdmin);
                administrador.setPasswordAdmin(passwordAdmin);
                administrador.setNombreDepartamento(departamentoAdmin);
                administradorDAO.Agregar(administrador);

                request.getRequestDispatcher("vistas/Login.jsp").forward(request, response);
                return;
            }

            if (accion.equals("Agregar Estudiante")) {
                String nombreEstudiante = request.getParameter("txtNombre");
                String apellidoEstudiante = request.getParameter("txtApellido");
                String telefonoEstudiante = request.getParameter("txtTelefono");
                String correoEstudiante = request.getParameter("txtCorreo");
                String passwordEstudiante = request.getParameter("txtPassword");
                String carreraEstudiante = request.getParameter("txtCarrera");
                int horasAsignadas = Integer.parseInt(request.getParameter("txtHorasAsignadas"));

                java.util.ArrayList<String> errores = new java.util.ArrayList<>();

                if (estudianteDAO.existeCorreo(correoEstudiante) || administradorDAO.existeCorreo(correoEstudiante)) {
                    errores.add("El correo ya está registrado");
                }
                if (estudianteDAO.existeTel(telefonoEstudiante) || administradorDAO.existeTel(telefonoEstudiante)) {
                    errores.add("El teléfono ya está registrado");
                }

                if (!errores.isEmpty()) {
                    request.setAttribute("errores", errores);
                    request.getRequestDispatcher("vistas/Register.jsp").forward(request, response);
                    return;
                }

                estudiante.setNombreEstudiante(nombreEstudiante);
                estudiante.setApellidoEstudiante(apellidoEstudiante);
                estudiante.setTelefono(telefonoEstudiante);
                estudiante.setCorreoEstudiante(correoEstudiante);
                estudiante.setPasswordEstudiante(passwordEstudiante);
                estudiante.setCarrera(carreraEstudiante);
                estudiante.setHorasAsignadas(horasAsignadas);
                estudianteDAO.Agregar(estudiante);

                request.getRequestDispatcher("vistas/Login.jsp").forward(request, response);
                return;
            }
        }

        // -------------------- LOGIN --------------------
        if (menu.equals("Validar") && "Login".equals(accion)) {
            String correo = request.getParameter("txtCorreo");
            String password = request.getParameter("txtPassword");

            administrador = administradorDAO.validar(correo, password);
            estudiante = estudianteDAO.validar(correo, password);

            if (administrador.getCorreoAdmin() != null) {
                HttpSession session = request.getSession();
                session.setAttribute("administradorEnSesion", administrador);

                List<Actividad> listaActividad = actividadDAO.listar();
                request.setAttribute("actividades", listaActividad);
                request.getRequestDispatcher("vistas/Actividad.jsp").forward(request, response);
                return;

            } else if (estudiante.getCorreoEstudiante() != null) {
                HttpSession session = request.getSession();
                session.setAttribute("estudianteEnSesion", estudiante);
                response.sendRedirect("Controlador?menu=Actividades%20Estudiante&accion=Listar");
                return;
            } else {
                request.setAttribute("error", "Correo y/o contraseña incorrectos");
                request.getRequestDispatcher("vistas/Login.jsp").forward(request, response);
                return;
            }
        }

        // -------------------- ACTIVIDADES (ADMIN) --------------------
        if (menu.equals("Actividad")) {
            if (accion == null) accion = "";

            switch (accion) {
                case "Agregar": {
                    String nombreActividad = request.getParameter("txtNombreActividad");
                    String descripcion = request.getParameter("txtDescripcion");
                    String fechaString = request.getParameter("txtFechaActividad");
                    LocalDateTime ldt = LocalDateTime.parse(fechaString);
                    Timestamp fechaActividad = Timestamp.valueOf(ldt);
                    String ubicacion = request.getParameter("txtUbicacion");
                    double horasDadas = Double.parseDouble(request.getParameter("txtHorasDadas"));
                    int cuposDisponibles = Integer.parseInt(request.getParameter("txtCuposDisponibles"));
                    int idAdmin = adminEnSesion.getIdAdmin();

                    Part filePart = request.getPart("txtImagen");
                    byte[] imagen = null;
                    if (filePart != null && filePart.getSize() > 0) {
                        try (InputStream inputStream = filePart.getInputStream();
                             ByteArrayOutputStream buffer = new ByteArrayOutputStream()) {
                            byte[] data = new byte[1024];
                            int bytesRead;
                            while ((bytesRead = inputStream.read(data, 0, data.length)) != -1) {
                                buffer.write(data, 0, bytesRead);
                            }
                            imagen = buffer.toByteArray();
                        }
                    }

                    actividad.setNombreActividad(nombreActividad);
                    actividad.setDescripcion(descripcion);
                    actividad.setFechaActividad(fechaActividad);
                    actividad.setUbicacion(ubicacion);
                    actividad.setHorasDadas(horasDadas);
                    actividad.setCuposDisponibles(cuposDisponibles);
                    actividad.setIdAdmin(idAdmin);
                    actividad.setImagen(imagen);

                    actividadDAO.agregar(actividad);

                    List<Actividad> listaActividad = actividadDAO.listar();
                    request.setAttribute("actividades", listaActividad);
                    request.getRequestDispatcher("vistas/Actividad.jsp").forward(request, response);
                    return;
                }

                case "Editar": {
                    idActividad = Integer.parseInt(request.getParameter("idActividad"));
                    Actividad actividadEncontrada = actividadDAO.buscarActividad(idActividad);
                    request.setAttribute("actividadEncontrada", actividadEncontrada);
                    request.setAttribute("actividades", actividadDAO.listar());
                    request.getRequestDispatcher("vistas/Actividad.jsp").forward(request, response);
                    return;
                }

                case "Actualizar": {
                    int idActividadActualizar = Integer.parseInt(request.getParameter("idActividad"));
                    String nombreActividad = request.getParameter("txtNombreActividad");
                    String descripcion = request.getParameter("txtDescripcion");
                    String fechaString = request.getParameter("txtFechaActividad");
                    LocalDateTime ldt = LocalDateTime.parse(fechaString);
                    Timestamp fechaActividad = Timestamp.valueOf(ldt);
                    String ubicacion = request.getParameter("txtUbicacion");
                    double horasDadas = Double.parseDouble(request.getParameter("txtHorasDadas"));
                    int cuposDisponibles = Integer.parseInt(request.getParameter("txtCuposDisponibles"));
                    int idAdmin = adminEnSesion.getIdAdmin();

                    Part filePart = request.getPart("txtImagen");
                    byte[] imagen;
                    if (filePart != null && filePart.getSize() > 0) {
                        try (InputStream inputStream = filePart.getInputStream();
                             ByteArrayOutputStream buffer = new ByteArrayOutputStream()) {
                            byte[] data = new byte[1024];
                            int bytesRead;
                            while ((bytesRead = inputStream.read(data, 0, data.length)) != -1) {
                                buffer.write(data, 0, bytesRead);
                            }
                            imagen = buffer.toByteArray();
                        }
                    } else {
                        imagen = actividadDAO.buscarActividad(idActividadActualizar).getImagen();
                    }

                    actividad.setIdActividad(idActividadActualizar);
                    actividad.setNombreActividad(nombreActividad);
                    actividad.setDescripcion(descripcion);
                    actividad.setFechaActividad(fechaActividad);
                    actividad.setUbicacion(ubicacion);
                    actividad.setHorasDadas(horasDadas);
                    actividad.setCuposDisponibles(cuposDisponibles);
                    actividad.setIdAdmin(idAdmin);
                    actividad.setImagen(imagen);

                    actividadDAO.actualizar(actividad);
                    request.setAttribute("actividades", actividadDAO.listar());
                    request.getRequestDispatcher("vistas/Actividad.jsp").forward(request, response);
                    return;
                }

                case "Eliminar": {
                    idActividad = Integer.parseInt(request.getParameter("idActividad"));
                    actividadDAO.eliminar(idActividad);
                    request.setAttribute("actividades", actividadDAO.listar());
                    request.getRequestDispatcher("vistas/Actividad.jsp").forward(request, response);
                    return;
                }

                case "VerImagen": {
                    int id = Integer.parseInt(request.getParameter("idActividad"));
                    Actividad act = actividadDAO.buscarActividad(id);
                    byte[] imgData = act.getImagen();
                    if (imgData != null) {
                        response.setContentType("image/jpeg");
                        response.getOutputStream().write(imgData);
                    }
                    return;
                }

                default:
                    request.setAttribute("actividades", actividadDAO.listar());
                    request.getRequestDispatcher("vistas/Actividad.jsp").forward(request, response);
                    return;
            }
        }

        // -------------------- ACTIVIDADES (ESTUDIANTE) --------------------
        if (menu.equals("Actividades Estudiante") && "Listar".equals(accion)) {
            List<Actividad> listaActividad = actividadDAO.listar();
            request.setAttribute("actividades", listaActividad);
            request.getRequestDispatcher("vistas/ActividadEstudiante.jsp").forward(request, response);
            return;
        }

        // -------------------- HOME --------------------
        if (menu.equals("Home")) {
            request.getRequestDispatcher("vistas/Home.jsp").forward(request, response);
            return;
        }

        // -------------------- INFORMACIÓN DE ACTIVIDAD --------------------
        if (menu.equals("InformacionActividad") && "Info Individual".equals(accion)) {
            idActividad = Integer.parseInt(request.getParameter("idActividad"));
            Actividad actividadInfo = actividadDAO.buscarActividad(idActividad);
            request.setAttribute("actividadIndividual", actividadInfo);
            boolean yaInscrito = estudianteEnSesion != null &&
                    inscripcionDAO.estaInscrito(estudianteEnSesion.getIdEstudiante(), idActividad);
            request.setAttribute("yaInscrito", yaInscrito);
            request.getRequestDispatcher("vistas/InformacionActividad.jsp").forward(request, response);
            return;
        }

        // -------------------- INSCRIPCIÓN --------------------
        if (menu.equals("Inscripcion") && "Agregar".equals(accion)) {
            idActividad = Integer.parseInt(request.getParameter("idActividad"));
            int idEstudiante = estudianteEnSesion.getIdEstudiante();
            Timestamp fechaInscripcion = Timestamp.valueOf(LocalDateTime.now());

            inscripcion.setIdActividad(idActividad);
            inscripcion.setIdEstudiante(idEstudiante);
            inscripcion.setFechaInscripcion(fechaInscripcion);
            inscripcionDAO.Agregar(inscripcion);

            Actividad actividadCupo = actividadDAO.buscarActividad(idActividad);
            int nuevoCupo = actividadCupo.getCuposDisponibles() - 1;
            actividadDAO.reducirCupo(actividadCupo, nuevoCupo, idActividad);

            request.getRequestDispatcher("Controlador?menu=InformacionActividad&accion=Info%20Individual&idActividad=" + idActividad)
                    .forward(request, response);
            return;
        }

        // -------------------- PERFIL --------------------
        if (menu.equals("Perfil")) {
            if (accion == null) {
                response.sendRedirect("Controlador?menu=Perfil&accion=Ver");
                return;
            }

            switch (accion) {
                case "Ver":
                    if (estudianteEnSesion != null) {
                        Estudiante estudianteActualizado = estudianteDAO.buscarPorId(estudianteEnSesion.getIdEstudiante());
                        sessionActiva.setAttribute("estudianteEnSesion", estudianteActualizado);
                    } else if (adminEnSesion != null) {
                        Administrador adminActualizado = administradorDAO.buscarPorId(adminEnSesion.getIdAdmin());
                        sessionActiva.setAttribute("administradorEnSesion", adminActualizado);
                    }
                    request.getRequestDispatcher("vistas/Perfil.jsp").forward(request, response);
                    return;

                case "Editar":
                    request.getRequestDispatcher("vistas/EditarPerfil.jsp").forward(request, response);
                    return;

                case "ActualizarEstudiante": {
                    int idEstudiante = Integer.parseInt(request.getParameter("idEstudiante"));
                    String nombre = request.getParameter("txtNombre");
                    String apellido = request.getParameter("txtApellido");
                    String telefono = request.getParameter("txtTelefono");
                    String carrera = request.getParameter("txtCarrera");
                    String password = request.getParameter("txtPassword");

                    Estudiante estudianteActualizar = new Estudiante();
                    estudianteActualizar.setIdEstudiante(idEstudiante);
                    estudianteActualizar.setNombreEstudiante(nombre);
                    estudianteActualizar.setApellidoEstudiante(apellido);
                    estudianteActualizar.setTelefono(telefono);
                    estudianteActualizar.setCarrera(carrera);
                    estudianteActualizar.setPasswordEstudiante(password);

                    boolean actualizado = estudianteDAO.actualizar(estudianteActualizar);
                    if (actualizado) {
                        Estudiante estudianteNuevo = estudianteDAO.buscarPorId(idEstudiante);
                        sessionActiva.setAttribute("estudianteEnSesion", estudianteNuevo);
                        request.setAttribute("mensaje", "Perfil actualizado correctamente");
                    } else {
                        request.setAttribute("error", "Error al actualizar el perfil");
                    }

                    request.getRequestDispatcher("vistas/Perfil.jsp").forward(request, response);
                    return;
                }

                case "ActualizarAdmin": {
                    int idAdmin = Integer.parseInt(request.getParameter("idAdmin"));
                    String nombre = request.getParameter("txtNombre");
                    String apellido = request.getParameter("txtApellido");
                    String telefono = request.getParameter("txtTelefono");
                    String departamento = request.getParameter("txtDepartamento");
                    String password = request.getParameter("txtPassword");

                    Administrador adminActualizar = new Administrador();
                    adminActualizar.setIdAdmin(idAdmin);
                    adminActualizar.setNombreAdmin(nombre);
                    adminActualizar.setApellidoAdmin(apellido);
                    adminActualizar.setTelefono(telefono);
                    adminActualizar.setNombreDepartamento(departamento);
                    adminActualizar.setPasswordAdmin(password);

                    boolean actualizado = administradorDAO.actualizar(adminActualizar);
                    if (actualizado) {
                        Administrador adminNuevo = administradorDAO.buscarPorId(idAdmin);
                        sessionActiva.setAttribute("administradorEnSesion", adminNuevo);
                        request.setAttribute("mensaje", "Perfil actualizado correctamente");
                    } else {
                        request.setAttribute("error", "Error al actualizar el perfil");
                    }

                    request.getRequestDispatcher("vistas/Perfil.jsp").forward(request, response);
                    return;
                }

                default:
                    response.sendRedirect("Controlador?menu=Perfil&accion=Ver");
                    return;
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
