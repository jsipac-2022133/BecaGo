<%-- 
    Document   : Resumen
    Created on : 2/11/2025, 04:03:05 PM
    Author     : SIPAC
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Resumen</title>
    </head>
    <body>
        <nav>
            <ul>
                <li><a href="Controlador?menu=Home">Home</a></li>
                <li><a href="Controlador?menu=Actividades%20Estudiante&accion=Listar">Actividades</a></li>
                <li><a href="Controlador?menu=Inscripciones%20Estudiante&accion=Listar">Inscripciones</a></li>
                <li><a href="Controlador?menu=Resumen&accion=Listar">Resumen</a></li>
                <li><a href="Controlador?menu=Logout">Logout</a></li>
            </ul>
        </nav>

        <h1>Actividades Completadas</h1>

        <div>
            <table>
                <thead>
                    <tr>
                        <th>Actividad</th>
                        <th>Fecha Realizada</th>
                        <th>Horas Dadas</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="resumen" items="${listaResumen}">
                        <tr>
                            <td>${resumen.getNombreActividad()}</td>
                            <td>${resumen.getFechaActividad()}</td>
                            <td>${resumen.getHorasDadas()}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <h2>Resumen</h2>
            <h3>Horas Requeridas: ${horasRequeridas}</h3>
            <h3>Horas Cumplidas: ${horasCumplidas}</h3>
            <h3 style="color: red">Horas Pendientes: ${horasRequeridas-horasCumplidas}</h3>

        </div>
    </body>
</html>
