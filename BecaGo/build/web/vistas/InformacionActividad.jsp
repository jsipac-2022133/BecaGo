<%-- 
    Document   : InformacionActividad
    Created on : 12/10/2025, 06:03:33 AM
    Author     : SIPAC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${actividadIndividual.getNombreActividad()}</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/estiloIA.css"/>
    </head>
    <body class="bodya">
        <nav>
            <ul>
                <li><a href="Controlador?menu=Home">Home</a></li>
                <li><a href="Controlador?menu=Actividades%20Estudiante&accion=Listar">Actividades</a></li>
                <li><a href="#">Inscripciones</a></li>
                <li><a href="#">Resumen</a></li>
                <li><a href="#">Logout</a></li>
            </ul>
        </nav>

        <div class="container-principal">
            <div class="card-info">
                <input type="hidden" name="idActividad" value="${actividadIndividual.getIdActividad()}">                
                <h1>📌${actividadIndividual.getNombreActividad()}</h1>
                <h3>📅 <b>Fecha y Hora: </b>${actividadIndividual.getFechaActividad()}</h3>
                <h3>📍 <b>Ubicación: </b>${actividadIndividual.getUbicacion()}</h3>
                <h3>⏱ <b>Horas Dadas: </b>${actividadIndividual.getHorasDadas()}</h3>
                <h3>👥 <b>Cupos Disponibles: </b>${actividadIndividual.getCuposDisponibles()}</h3>   
                <div class="btn-container">
                    <a href="Controlador?menu=Inscripcion&accion=Agregar&idActividad=${actividadIndividual.getIdActividad()}" 
                       class="btn-inscribirse">Inscribirse</a>

                </div>
            </div>
            <div class="container-imagen">
                <c:if test="${actividad.getImagen() != null}">
                    <img src="Controlador?menu=Actividad&accion=VerImagen&idActividad=${actividadIndividual.getIdActividad()}" 
                         class="imagen-actividad" alt="Imagen actividad">
                </c:if>
            </div>
        </div>

    </body>
</html>
