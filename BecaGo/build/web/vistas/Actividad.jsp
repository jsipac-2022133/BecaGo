<%-- 
    Document   : Actividad
    Created on : 16/09/2025, 12:26:15 PM
    Author     : SIPAC
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Actividad - BecaGo</title>
    </head>
    <body>
        <h1><a>Agregar Actividad</a></h1>
        <form action="Controlador?menu=Actividad" method="POST">
            <label>Nombre actividad</label>
            <input value="${actividadEncontrada.getNombreActividad()}" type="text" name="txtNombreActividad" placeholder="Nombre Actividad">
            <br>
            <label>Descripción</label>
            <input value="${actividadEncontrada.getDescripcion()}" name="txtDescripcion" type="text" placeholder="Escribe la descripción de la actividad">            
            <br>
            <label>Fecha</label>
            <input value="${actividadEncontrada.getFechaActividad()}" type="datetime-local" name="txtFechaActividad">
            <br>
            <label>Ubicación</label>
            <input value="${actividadEncontrada.getUbicacion()}" type="text" name="txtUbicacion" placeholder="Ubicación de encuentro">
            <br>
            <label>Horas dadas</label>
            <input value="${actividadEncontrada.getHorasDadas()}" type="number" name="txtHorasDadas" placeholder="Horas dadas al finalizar actividad" step="0.01">
            <br>
            <label>Cupos disponibles</label>
            <input value="${actividadEncontrada.getCuposDisponibles()}" type="number" name="txtCuposDisponibles" placeholder="Cupos disponibles">
            <br>
            <button type="submit" name="accion" value="Agregar">Agregar</button>
            <button type="submit" name="accion" value="Actualizar">Actualizar</button>
        </form>
        <br>
        <br>
        <br>
        <table>
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Nombre</th>
                    <th>Descripción</th>
                    <th>Fecha</th>
                    <th>Ubicación</th>
                    <th>Horas Dadas</th>
                    <th>Cupos Disponibles</th>
                    <th>Id Admin</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="actividad" items="${actividades}">
                    <tr>
                        <td>${actividad.getIdActividad()}</td>
                        <td>${actividad.getNombreActividad()}</td>
                        <td>${actividad.getDescripcion()}</td>
                        <td>${actividad.getFechaActividad()}</td>
                        <td>${actividad.getUbicacion()}</td>
                        <td>${actividad.getHorasDadas()}</td>
                        <td>${actividad.getCuposDisponibles()}</td>
                        <td>${actividad.getIdAdmin()}</td>                        
                        <td>
                            <a href="Controlador?menu=Actividad&accion=Editar&idActividad=${actividad.getIdActividad()}">Editar</a>
                            <a>Eliminar</a>
                        </td>
                    </tr>
                </c:forEach>

            </tbody>
        </table>
    </body>
</html>
