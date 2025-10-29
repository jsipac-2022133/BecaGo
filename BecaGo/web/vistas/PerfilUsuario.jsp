<%-- 
    Document   : PerfilUsuario
    Created on : Oct 28, 2025, 6:18:36 PM
    Author     : Guti
--%>



<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="modelo.Estudiante, modelo.Administrador" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Mi Perfil - BecaGo</title>
    <link rel="stylesheet" href="../resources/css/estiloP.css">
</head>
<body>
    <%
        Estudiante estudianteEnSesion = (Estudiante) session.getAttribute("estudianteEnSesion");
        Administrador adminEnSesion = (Administrador) session.getAttribute("administradorEnSesion");
        boolean esEstudiante = (estudianteEnSesion != null);
        boolean esAdmin = (adminEnSesion != null);
        
        
        java.util.ArrayList<String> errores = (java.util.ArrayList<String>) request.getAttribute("errores");
        String mensajeExito = (String) request.getAttribute("mensajeExito");
    %>
    
    <div class="perfil-container">
        <div class="perfil-header">
            <h1>Mi Perfil</h1>
            <div class="user-type-badge">
                <%= esEstudiante ? "🎓 Estudiante" : "👨‍💼 Administrador" %>
            </div>
        </div>

        <% if (errores != null && !errores.isEmpty()) { %>
            <div class="alert alert-error">
                <strong>Errores:</strong>
                <ul>
                    <% for (String error : errores) { %>
                        <li><%= error %></li>
                    <% } %>
                </ul>
            </div>
        <% } %>
        
        <% if (mensajeExito != null) { %>
            <div class="alert alert-success">
                <%= mensajeExito %>
            </div>
        <% } %>

        <form action="Controlador" method="POST" class="perfil-form">
            <input type="hidden" name="menu" value="Perfil">
            <input type="hidden" name="accion" value="Actualizar">
            
            <div class="form-grid">
                <!-- Información Personal -->
                <div class="form-section">
                    <h3>Información Personal</h3>
                    
                    <div class="form-group">
                        <label for="txtNombre">Nombre</label>
                        <input type="text" id="txtNombre" name="txtNombre" 
                               value="<%= esEstudiante ? estudianteEnSesion.getNombreEstudiante() : adminEnSesion.getNombreAdmin() %>" 
                               required>
                    </div>
                    
                    <div class="form-group">
                        <label for="txtApellido">Apellido</label>
                        <input type="text" id="txtApellido" name="txtApellido" 
                               value="<%= esEstudiante ? estudianteEnSesion.getApellidoEstudiante() : adminEnSesion.getApellidoAdmin() %>" 
                               required>
                    </div>
                    
                    <div class="form-group">
                        <label for="txtTelefono">Teléfono</label>
                        <input type="tel" id="txtTelefono" name="txtTelefono" 
                               value="<%= esEstudiante ? estudianteEnSesion.getTelefono() : adminEnSesion.getTelefono() %>" 
                               required>
                    </div>
                    
                    <div class="form-group">
                        <label for="txtCorreo">Correo Electrónico</label>
                        <input type="email" id="txtCorreo" name="txtCorreo" 
                               value="<%= esEstudiante ? estudianteEnSesion.getCorreoEstudiante() : adminEnSesion.getCorreoAdmin() %>" 
                               required>
                    </div>
                </div>

                
                <div class="form-section">
                    <h3>Información <%= esEstudiante ? "Académica" : "Laboral" %></h3>
                    
                    <% if (esEstudiante) { %>
                        <div class="form-group">
                            <label for="txtCarrera">Carrera</label>
                            <input type="text" id="txtCarrera" name="txtCarrera" 
                                   value="<%= estudianteEnSesion.getCarrera() != null ? estudianteEnSesion.getCarrera() : "" %>" required>
                        </div>
                        
                        <div class="info-group">
                            <label>Horas Asignadas</label>
                            <div class="info-value"><%= estudianteEnSesion.getHorasAsignadas() %> horas</div>
                        </div>
                        
                        <div class="info-group">
                            <label>Horas Cumplidas</label>
                            <div class="info-value"><%= estudianteEnSesion.getHorasCumplidas() %> horas</div>
                        </div>
                        
                        <div class="info-group">
                            <label>Progreso</label>
                            <div class="progress-bar">
                                <%
                                    int horasAsignadas = estudianteEnSesion.getHorasAsignadas();
                                    int horasCumplidas = estudianteEnSesion.getHorasCumplidas();
                                    int porcentaje = horasAsignadas > 0 ? (horasCumplidas * 100) / horasAsignadas : 0;
                                %>
                                <div class="progress-fill" style="width: <%= porcentaje %>%;"></div>
                                <span class="progress-text"><%= porcentaje %>%</span>
                            </div>
                        </div>
                        
                    <% } else { %>
                        <div class="form-group">
                            <label for="txtDepartamento">Departamento/Unidad</label>
                            <input type="text" id="txtDepartamento" name="txtDepartamento" 
                                   value="<%= adminEnSesion.getNombreDepartamento() != null ? adminEnSesion.getNombreDepartamento() : "" %>" required>
                        </div>
                    <% } %>
                </div>

                
                <div class="form-section">
                    <h3>Seguridad</h3>
                    
                    <div class="form-group">
                        <label for="txtPasswordActual">Contraseña Actual *</label>
                        <input type="password" id="txtPasswordActual" name="txtPasswordActual" 
                               placeholder="Ingresa tu contraseña actual para confirmar cambios" required>
                    </div>
                    
                    <div class="form-group">
                        <label for="txtNuevaPassword">Nueva Contraseña</label>
                        <input type="password" id="txtNuevaPassword" name="txtNuevaPassword" 
                               placeholder="Dejar en blanco para mantener la actual">
                    </div>
                    
                    <div class="form-group">
                        <label for="txtConfirmarPassword">Confirmar Nueva Contraseña</label>
                        <input type="password" id="txtConfirmarPassword" name="txtConfirmarPassword" 
                               placeholder="Repite la nueva contraseña">
                    </div>
                </div>
            </div>

            <div class="form-actions">
                <button type="submit" class="btn btn-primary">Guardar Cambios</button>
                <a href="<%= esEstudiante ? "Controlador?menu=Actividades%20Estudiante&accion=Listar" : "Controlador?menu=Actividad" %>" 
                   class="btn btn-secondary">Cancelar</a>
            </div>
        </form>
    </div>
</body>
</html>