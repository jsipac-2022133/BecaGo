<%-- 
    Document   : Home
    Created on : 12/09/2025, 11:42:30 PM
    Author     : SIPAC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="modelo.Estudiante"%>
<%@page import="modelo.Administrador"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/estiloH.css"/>
    </head>
    <body class = "bodyh">
        <%
            Estudiante estudianteEnSesion = (Estudiante) session.getAttribute("estudianteEnSesion");
            Administrador adminEnSesion = (Administrador) session.getAttribute("administradorEnSesion");
            boolean esEstudiante = (estudianteEnSesion != null);
        %>
        
        <nav>
            <ul>
                <li><a href="Controlador?menu=Home">Home</a></li>
                <li><a href="Controlador?menu=Actividades%20Estudiante&accion=Listar">Actividades</a></li>
                <li><a href="Controlador?menu=Inscripciones%20Estudiante&accion=Listar">Inscripciones</a></li>
                <li><a href="Controlador?menu=Resumen&accion=Listar">Resumen</a></li>
                <li><a href="Controlador?menu=Perfil&accion=Mostrar">Mi Perfil</a></li>
                <li><a href="Controlador?menu=Logout">Logout</a></li>
            </ul>
        </nav>
        
        <div class="main-content">
            <main>
                <!-- Barra de Progreso para Estudiantes -->
                <% if (esEstudiante) { %>
                <div class="progress-home-section">
                    <div class="progress-home-header">
                        <h3 class="progress-home-title">Mi Progreso de Horas</h3>
                        <div class="progress-home-stats">
                            <%= estudianteEnSesion.getHorasCumplidas() %>/<%= estudianteEnSesion.getHorasAsignadas() %> horas
                        </div>
                    </div>
                    
                    <div class="progress-home-container">
                        <%
                            int horasAsignadas = estudianteEnSesion.getHorasAsignadas();
                            int horasCumplidas = estudianteEnSesion.getHorasCumplidas();
                            double porcentaje = 0;
                            String nivelProgreso = "progress-bajo";
                            
                            if (horasAsignadas > 0) {
                                porcentaje = (double) horasCumplidas / horasAsignadas * 100;
                                if (porcentaje > 100) porcentaje = 100;
                                
                                // Determinar nivel de progreso
                                if (porcentaje >= 80) {
                                    nivelProgreso = "progress-alto";
                                } else if (porcentaje >= 50) {
                                    nivelProgreso = "progress-medio";
                                } else if (porcentaje >= 25) {
                                    nivelProgreso = "progress-bajo";
                                } else {
                                    nivelProgreso = "progress-muy-bajo";
                                }
                            }
                        %>
                        
                        <div class="progress-home-bar <%= nivelProgreso %>">
                            <div class="progress-home-fill" style="width: <%= porcentaje %>%;">
                                <span class="progress-home-text"><%= String.format("%.1f", porcentaje) %>%</span>
                            </div>
                        </div>
                    </div>
                </div>
                <% } %>
                
                <section class = "sec1">
                    <h1 class = "titleh">Bienvenido a BecaGo</h1>
                    <p>Bienvenido a nuestra plataforma!!! Acá tendrás acceso
                        a un panel para gestionar tus actividades de horas beca de manera
                        sencilla y eficiente. Podrás ver tu progreso y enlistarte a las actividades
                        que más te interesen. Estamos para apoyarte en tu desarrollo académico y personal.
                        Cualquier duda no dudes en contactarnos.
                    </p>
                    <div class="profile-link-container">
                        <a href="Controlador?menu=Perfil&accion=Mostrar" class="profile-btn">Mi Perfil</a>
                    </div>
                </section>
            </main> 
        </div>
    </body>
</html>