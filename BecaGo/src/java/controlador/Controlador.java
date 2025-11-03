package controlador;

import dto.ActividadEstudianteDTO;
import java.io.IOException;
import java.io.InputStream;
import java.io.ByteArrayOutputStream;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.servlet.annotation.MultipartConfig;
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
    int idActividad;
    Inscripcion inscripcion = new Inscripcion();
    InscripcionDAO inscripcionDAO = new InscripcionDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String menu = request.getParameter("menu");
        String accion = request.getParameter("accion");

        HttpSession sessionActiva = request.getSession();
        Administrador adminEnSesion = (Administrador) sessionActiva.getAttribute("administradorEnSesion");
        Estudiante estudianteEnSesion = (Estudiante) sessionActiva.getAttribute("estudianteEnSesion");

        if (menu == null || menu.equals("Login")) {
            request.getRequestDispatcher("vistas/Login.jsp").forward(request, response);

        } else if (menu.equals("Register")) {
            request.getRequestDispatcher("vistas/Register.jsp").forward(request, response);

        } else if (menu.equals("Admin-Estudiante")) {
            if (accion.equals("Agregar Admin")) {
                String nombreAdmin = request.getParameter("txtNombre");
                String apellidoAdmin = request.getParameter("txtApellido");
                String telefonoAdmin = request.getParameter("txtTelefono");
                String correoAdmin = request.getParameter("txtCorreo");
                String passwordAdmin = request.getParameter("txtPassword");
                String departamentoAdmin = request.getParameter("txtUnidadDepto");

                java.util.ArrayList<String> errores = new java.util.ArrayList<>();

                // Verifica si el correo ya existe
                if (administradorDAO.existeCorreo(correoAdmin) || estudianteDAO.existeCorreo(correoAdmin)) {
                    errores.add("El correo ya está registrado");
                }
                // Verifica si el teléfono ya existe
                if (administradorDAO.existeTel(telefonoAdmin) || estudianteDAO.existeTel(telefonoAdmin)) {
                    errores.add("El teléfono ya está registrado");
                }

                // Muestra si hay errores
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

            } else if (accion.equals("Agregar Estudiante")) {
                String nombreEstudiante = request.getParameter("txtNombre");
                String apellidoEstudiante = request.getParameter("txtApellido");
                String telefonoEstudiante = request.getParameter("txtTelefono");
                String correoEstudiante = request.getParameter("txtCorreo");
                String passwordEstudiante = request.getParameter("txtPassword");
                String carreraEstudiante = request.getParameter("txtCarrera");
                int horasAsignadas = Integer.parseInt(request.getParameter("txtHorasAsignadas"));

                java.util.ArrayList<String> errores = new java.util.ArrayList<>();

                // Verifica si el correo ya existe
                if (estudianteDAO.existeCorreo(correoEstudiante) || administradorDAO.existeCorreo(correoEstudiante)) {
                    errores.add("El correo ya está registrado");
                }
                // Verifica si el teléfono ya existe
                if (estudianteDAO.existeTel(telefonoEstudiante) || administradorDAO.existeTel(telefonoEstudiante)) {
                    errores.add("El teléfono ya está registrado");
                }

                // Muestra si hay errores
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
            }

        } else if (menu.equals("Validar")) {
            if (accion.equals("Login")) {
                String correo = request.getParameter("txtCorreo");
                String password = request.getParameter("txtPassword");

                if (correo.matches(".*\\d.*")) {
                    // Es estudiante
                    estudiante = estudianteDAO.validar(correo, password);
                    if (estudiante.getCorreoEstudiante() != null) {
                        // Login exitoso
                        HttpSession session = request.getSession();
                        session.setAttribute("estudianteEnSesion", estudiante);
                        response.sendRedirect("Controlador?menu=Actividades%20Estudiante&accion=Listar");
                    } else {
                        // Login fallido
                        request.setAttribute("error", "Correo y/o contraseña incorrecto(s)");
                        request.getRequestDispatcher("vistas/Login.jsp").forward(request, response);
                    }

                } else {
                    // Es administrador
                    administrador = administradorDAO.validar(correo, password);
                    if (administrador.getCorreoAdmin() != null) {
                        // Login exitoso
                        HttpSession session = request.getSession();
                        session.setAttribute("administradorEnSesion", administrador);

                        // Lista actividades
                        List<Actividad> listaActividad = actividadDAO.listar();
                        request.setAttribute("actividades", listaActividad);

                        request.getRequestDispatcher("vistas/Actividad.jsp").forward(request, response);
                    } else {
                        // Login fallido
                        request.setAttribute("error", "Correo y/o contraseña incorrecto(s)");
                        request.getRequestDispatcher("vistas/Login.jsp").forward(request, response);
                    }
                }
            }

        } else if (menu.equals("Actividad")) {

            if (accion != null) {
                if (accion.equals("Agregar")) {
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
                        try (InputStream inputStream = filePart.getInputStream(); ByteArrayOutputStream buffer = new ByteArrayOutputStream()) {

                            byte[] data = new byte[1024];
                            int bytesRead;
                            while ((bytesRead = inputStream.read(data, 0, data.length)) != -1) {
                                buffer.write(data, 0, bytesRead);
                            }
                            imagen = buffer.toByteArray();

                        } catch (IOException e) {
                            e.printStackTrace();
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

                } else if (accion.equals("Editar")) {
                    idActividad = Integer.parseInt(request.getParameter("idActividad"));
                    Actividad actividadEncontrada = actividadDAO.buscarActividad(idActividad);
                    request.setAttribute("actividadEncontrada", actividadEncontrada);

                    List<Actividad> listaActividad = actividadDAO.listar();
                    request.setAttribute("actividades", listaActividad);
                    request.getRequestDispatcher("vistas/Actividad.jsp").forward(request, response);
                    return;

                } else if (accion.equals("Actualizar")) {
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
                        try (InputStream inputStream = filePart.getInputStream(); ByteArrayOutputStream buffer = new ByteArrayOutputStream()) {

                            byte[] data = new byte[1024];
                            int bytesRead;
                            while ((bytesRead = inputStream.read(data, 0, data.length)) != -1) {
                                buffer.write(data, 0, bytesRead);
                            }
                            imagen = buffer.toByteArray();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    actividad.setNombreActividad(nombreActividad);
                    actividad.setDescripcion(descripcion);
                    actividad.setFechaActividad(fechaActividad);
                    actividad.setUbicacion(ubicacion);
                    actividad.setHorasDadas(horasDadas);
                    actividad.setCuposDisponibles(cuposDisponibles);
                    actividad.setIdAdmin(idAdmin);
                    actividad.setIdActividad(idActividad);
                    actividad.setImagen(imagen);

                    actividadDAO.actualizar(actividad);

                    List<Actividad> listaActividad = actividadDAO.listar();
                    request.setAttribute("actividades", listaActividad);
                    request.getRequestDispatcher("vistas/Actividad.jsp").forward(request, response);
                    return;

                } else if (accion.equals("Eliminar")) {
                    idActividad = Integer.parseInt(request.getParameter("idActividad"));
                    actividadDAO.eliminar(idActividad);

                    List<Actividad> listaActividad = actividadDAO.listar();
                    request.setAttribute("actividades", listaActividad);
                    request.getRequestDispatcher("vistas/Actividad.jsp").forward(request, response);
                    return;

                } else if (accion.equals("VerImagen")) {
                    int id = Integer.parseInt(request.getParameter("idActividad"));
                    Actividad act = actividadDAO.buscarActividad(id);
                    byte[] imgData = act.getImagen();
                    if (imgData != null) {
                        response.setContentType("image/jpeg");
                        response.getOutputStream().write(imgData);
                    }
                    return;
                }
            }

            List<Actividad> listaActividad = actividadDAO.listar();
            request.setAttribute("actividades", listaActividad);
            request.getRequestDispatcher("vistas/Actividad.jsp").forward(request, response);
        } else if (menu.equals("Actividades Estudiante")) {
            if (accion.equals("Listar")) {
                List<Actividad> listaActividad = actividadDAO.listar();
                request.setAttribute("actividades", listaActividad);
                request.getRequestDispatcher("vistas/ActividadEstudiante.jsp").forward(request, response);
            }
        } else if (menu.equals("Home")) {
            request.getRequestDispatcher("vistas/Home.jsp").forward(request, response);
        } else if (menu.equals("InformacionActividad")) {
            if (accion.equals("Info Individual")) {
                idActividad = Integer.parseInt(request.getParameter("idActividad"));
                Actividad actividad = actividadDAO.buscarActividad(idActividad);
                request.setAttribute("actividadIndividual", actividad);
                //esto es para bloquear el botón en el jsp xd
                boolean yaInscrito = false;
                if (estudianteEnSesion != null) {
                    yaInscrito = inscripcionDAO.estaInscrito(estudianteEnSesion.getIdEstudiante(), idActividad);
                }
                request.setAttribute("yaInscrito", yaInscrito);

                request.getRequestDispatcher("vistas/InformacionActividad.jsp").forward(request, response);
            }
        } else if (menu.equals("Inscripcion")) {
            if (accion.equals("Agregar")) {
                idActividad = Integer.parseInt(request.getParameter("idActividad"));
                int idEstudiante = estudianteEnSesion.getIdEstudiante();
                Timestamp fechaInscripcion = Timestamp.valueOf(LocalDateTime.now());
                inscripcion.setIdActividad(idActividad);
                inscripcion.setIdEstudiante(idEstudiante);
                inscripcion.setFechaInscripcion(fechaInscripcion);
                inscripcionDAO.Agregar(inscripcion);
                //actualizar cupoooo
                ActividadDAO actividadCupo = new ActividadDAO();
                Actividad actividad = actividadCupo.buscarActividad(idActividad);
                int nuevoCupo = actividad.getCuposDisponibles() - 1;
                actividadCupo.reducirCupo(actividad, nuevoCupo, idActividad);

                request.getRequestDispatcher("Controlador?menu=InformacionActividad&accion=Info%20Individual&idActividad=" + idActividad)
                        .forward(request, response);
            } else if (accion.equals("Listar")) {
                List<ActividadEstudianteDTO> lista = inscripcionDAO.listarPorInscripcionColectivo();
                request.setAttribute("inscripcionesColectivas", lista);
                request.getRequestDispatcher("vistas/Inscripcion.jsp").forward(request, response);
            }
        } else if (menu.equals("Inscripciones Estudiante")) {
            if (accion.equals("Listar")) {
                int idEstudiante = estudianteEnSesion.getIdEstudiante();
                List<Actividad> listaActividad = actividadDAO.listarPorInscripcionIndividual(idEstudiante);
                request.setAttribute("inscripcionesIndividuales", listaActividad);
                request.getRequestDispatcher("vistas/InscripcionEstudiante.jsp").forward(request, response);
            }
        } else if (menu.equals("Horas")) {
            if (accion.equals("Agregar")) {
                int idInscripcion = Integer.parseInt(request.getParameter("txtIdInscripcion"));
                inscripcionDAO.AgregarHorasAEstudiante(idInscripcion);
                response.sendRedirect("Controlador?menu=Inscripcion&accion=Listar");
            }
        } else if (menu.equals("Resumen")) {
            if (accion.equals("Listar")) {
                int idEstudiante = estudianteEnSesion.getIdEstudiante();
                List<ActividadEstudianteDTO> lista = inscripcionDAO.listarActividadesCompletadas(idEstudiante);
                request.setAttribute("listaResumen", lista);

                int totalHoras = 0;
                for (ActividadEstudianteDTO a : lista) {
                    totalHoras += a.getHorasDadas();
                }
                
                estudianteDAO.actualizarHorasCumplidas(totalHoras, idEstudiante);
                request.setAttribute("totalHoras", totalHoras);
                request.getRequestDispatcher("vistas/Resumen.jsp").forward(request, response);
            }
        } else if (menu.equals("Perfil")) {
            if (accion.equals("Mostrar")) {
                // esto muestra el formulario del perfil
                request.getRequestDispatcher("vistas/PerfilUsuario.jsp").forward(request, response);

            } else if (accion.equals("Actualizar")) {
                java.util.ArrayList<String> errores = new java.util.ArrayList<>();
                String mensajeExito = null;

                String nombre = request.getParameter("txtNombre");
                String apellido = request.getParameter("txtApellido");
                String telefono = request.getParameter("txtTelefono");
                String correo = request.getParameter("txtCorreo");
                String passwordActual = request.getParameter("txtPasswordActual");
                String nuevaPassword = request.getParameter("txtNuevaPassword");
                String confirmarPassword = request.getParameter("txtConfirmarPassword");

                // para validar la contraseña 
                if (passwordActual == null || passwordActual.trim().isEmpty()) {
                    errores.add("La contraseña actual es requerida para confirmar cambios");
                } else {
                    if (estudianteEnSesion != null) {
                        if (!estudianteEnSesion.getPasswordEstudiante().equals(passwordActual)) {
                            errores.add("La contraseña actual es incorrecta");
                        }
                    } else if (adminEnSesion != null) {
                        if (!adminEnSesion.getPasswordAdmin().equals(passwordActual)) {
                            errores.add("La contraseña actual es incorrecta");
                        }
                    }
                }

                // valida la nueva contra si el usuario si la actualiza 
                if (nuevaPassword != null && !nuevaPassword.trim().isEmpty()) {
                    if (!nuevaPassword.equals(confirmarPassword)) {
                        errores.add("Las nuevas contraseñas no coinciden");
                    }
                    if (nuevaPassword.length() < 6) {
                        errores.add("La nueva contraseña debe tener al menos 6 caracteres");
                    }
                }

                if (estudianteEnSesion != null) {
                    if (!correo.equals(estudianteEnSesion.getCorreoEstudiante())
                            && (estudianteDAO.existeCorreo(correo) || administradorDAO.existeCorreo(correo))) {
                        errores.add("El correo ya está registrado");
                    }
                    if (!telefono.equals(estudianteEnSesion.getTelefono())
                            && (estudianteDAO.existeTel(telefono) || administradorDAO.existeTel(telefono))) {
                        errores.add("El teléfono ya está registrado");
                    }
                } else if (adminEnSesion != null) {
                    if (!correo.equals(adminEnSesion.getCorreoAdmin())
                            && (administradorDAO.existeCorreo(correo) || estudianteDAO.existeCorreo(correo))) {
                        errores.add("El correo ya está registrado");
                    }
                    if (!telefono.equals(adminEnSesion.getTelefono())
                            && (administradorDAO.existeTel(telefono) || estudianteDAO.existeTel(telefono))) {
                        errores.add("El teléfono ya está registrado");
                    }
                }

                if (errores.isEmpty()) {
                    // actualiza datos 
                    if (estudianteEnSesion != null) {
                        // actualiza al estudiante
                        estudianteEnSesion.setNombreEstudiante(nombre);
                        estudianteEnSesion.setApellidoEstudiante(apellido);
                        estudianteEnSesion.setTelefono(telefono);
                        estudianteEnSesion.setCorreoEstudiante(correo);
                        estudianteEnSesion.setCarrera(request.getParameter("txtCarrera"));

                        if (nuevaPassword != null && !nuevaPassword.trim().isEmpty()) {
                            estudianteEnSesion.setPasswordEstudiante(nuevaPassword);
                        }

                        // lo actualiza en la bd 
                        int resultado = estudianteDAO.actualizar(estudianteEnSesion);
                        if (resultado > 0) {
                            mensajeExito = "Perfil actualizado correctamente";

                            sessionActiva.setAttribute("estudianteEnSesion", estudianteEnSesion);
                        } else {
                            errores.add("Error al actualizar el perfil en la base de datos");
                        }

                    } else if (adminEnSesion != null) {
                        // actualiza el administrador
                        adminEnSesion.setNombreAdmin(nombre);
                        adminEnSesion.setApellidoAdmin(apellido);
                        adminEnSesion.setTelefono(telefono);
                        adminEnSesion.setCorreoAdmin(correo);
                        adminEnSesion.setNombreDepartamento(request.getParameter("txtDepartamento"));

                        if (nuevaPassword != null && !nuevaPassword.trim().isEmpty()) {
                            adminEnSesion.setPasswordAdmin(nuevaPassword);
                        }

                        // actualiza en la bd 
                        int resultado = administradorDAO.actualizar(adminEnSesion);
                        if (resultado > 0) {
                            mensajeExito = "Perfil actualizado correctamente";

                            sessionActiva.setAttribute("administradorEnSesion", adminEnSesion);
                        } else {
                            errores.add("Error al actualizar el perfil en la base de datos");
                        }
                    }
                }

                if (!errores.isEmpty()) {
                    request.setAttribute("errores", errores);
                }
                if (mensajeExito != null) {
                    request.setAttribute("mensajeExito", mensajeExito);
                }

                request.getRequestDispatcher("vistas/PerfilUsuario.jsp").forward(request, response);
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
