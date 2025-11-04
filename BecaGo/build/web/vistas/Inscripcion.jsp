<%-- 
    Document   : Inscripcion
    Created on : 27/10/2025, 20:59:16
    Author     : James
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inscripciones</title>
    </head>
    <body>

        <nav>
            <ul>
                <li><a href="Controlador?menu=Home">Home</a></li>
                <li><a href="Controlador?menu=Actividad">Actividades</a></li>
                <li><a href="Controlador?menu=Inscripcion&accion=Listar">Inscripciones</a></li>
                <li><a href="#">Resumen</a></li>
                <li><a href="Controlador?menu=Logout">Logout</a></li>
            </ul>
        </nav>

        <h1>Listado de Inscripciones</h1>

        <c:set var="ultimaActividad" value="" />
        <c:set var="ultimoCupo" value="0" />
        <c:set var="ultimaFecha" value="" />

        <c:forEach var="inscripcion" items="${inscripcionesColectivas}" varStatus="status">

            <c:if test="${inscripcion.nombreActividad ne ultimaActividad}">
                <c:if test="${!empty ultimaActividad}">
                </tbody></table>
            <p><b>Cupos disponibles:</b> ${ultimoCupo}</p>
            <p>
                <b>Fecha de actividad:</b> 
                <fmt:formatDate value="${ultimaFecha}" pattern="dd/MM/yy hh:mm a" />
            </p>
            <br>
        </c:if>

        <h2>${inscripcion.nombreActividad}</h2>
        <table border="1" cellspacing="0" cellpadding="4">
            <thead>
                <tr>
                    <th>Estudiante</th>
                    <th>Correo</th>
                    <th>Estado</th>
                </tr>
            </thead>
            <tbody>

                <c:set var="ultimaFecha" value="${inscripcion.fechaActividad}" />
            </c:if>

            <tr>
                <td>${inscripcion.nombreEstudiante} ${inscripcion.apellidoEstudiante}</td>
                <td>${inscripcion.correoEstudiante}</td>
                <td>
            <c:choose>
                 <c:when test="${inscripcion.estado}">
                    <span style="color: green; font-weight: bold;">✅ Cumplido</span>
            </c:when>
                <c:otherwise>
                    <span style="color: orange; font-weight: bold;">⏳ Pendiente</span>
            <form action="Controlador" method="POST" style="display:inline;">
                <input type="hidden" name="menu" value="Inscripcion">
                <input type="hidden" name="accion" value="CompletarActividad">
                <input type="hidden" name="idInscripcion" value="${inscripcion.idInscripcion}">
                <input type="hidden" name="idEstudiante" value="${inscripcion.idEstudiante}">
                <input type="hidden" name="horasDadas" value="${inscripcion.horasDadas}">
                <button type="submit" class="btn-completar">✅ Marcar como Cumplida</button>
            </form>
                </c:otherwise>
            </c:choose>
</td>
            </tr>

            <c:set var="ultimaActividad" value="${inscripcion.nombreActividad}" />
            <c:set var="ultimoCupo" value="${inscripcion.cuposDisponibles}" />

            <c:if test="${status.last}">
            </tbody></table>
        <p><b>Cupos disponibles:</b> ${ultimoCupo}</p>
        <p>
            <b>Fecha de actividad:</b> 
            <fmt:formatDate value="${ultimaFecha}" pattern="dd/MM/yy hh:mm a" />
        </p>
    </c:if>

</c:forEach>

</body>
</html>
