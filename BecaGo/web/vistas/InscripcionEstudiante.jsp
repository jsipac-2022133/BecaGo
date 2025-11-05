<%-- 
    Document   : InscripcionEstudiante
    Created on : 27/10/2025, 18:32:27
    Author     : James
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inscripciones</title>
        <link rel="stylesheet" type="text/css" href="resources/css/estiloIE.css">
    </head>
    <body class="bodine">
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

        <div>
            <section class="sec2">
            <c:forEach var="inscripcion" items="${inscripcionesIndividuales}">
                <div style="border: 1px solid #333;">
                    <h1>${inscripcion.getNombreActividad()}</h1>
                    <h3><b>Fecha: </b>${inscripcion.getFechaActividad()}</h3>
                    <h3><b>Ubicación: </b>${inscripcion.getUbicacion()}</h3>
                </div>
            </section>
            </c:forEach>
        </div>
    </body>
</html>
