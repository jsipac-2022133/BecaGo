<%-- 
    Document   : ActividadEstudiante
    Created on : 10/10/2025, 07:57:06 PM
    Author     : SIPAC
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Actividades - BecaGo</title>
    <link rel="stylesheet" type="text/css" href="resources/css/estilos.css?v=3">
</head>
    <body>
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
            <c:forEach var="actividad" items="${actividades}">
                <a href="Controlador?menu=InformacionActividad&accion=Info%20Individual&idActividad=${actividad.getIdActividad()}">
                    <div style="border: 1px solid #333;">
                        <h2>📌 ${actividad.getNombreActividad()}</h2>
                        <h3><fmt:formatDate value="${actividad.getFechaActividad()}" pattern="dd/MM/yy hh:mm a" /></h3>
                        <c:if test="${actividad.getImagen() != null}">
                            <img src="Controlador?menu=Actividad&accion=VerImagen&idActividad=${actividad.getIdActividad()}" 
                                 width="50" height="50" alt="Imagen actividad">
                        </c:if>
                    </div>
                </a>
            </c:forEach>
        </div>

    </body>
</html>
