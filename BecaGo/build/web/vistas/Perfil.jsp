<%-- 
    Document   : Perfil
    Created on : Oct 26, 2025, 1:59:51 PM
    Author     : Guti
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mi Perfil - BecaGo</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/estiloPerfil.css"/>
    </head>
    <body class="body-perfil">
        <nav class="nav-perfil">
            <ul>
                <li><a href="Controlador?menu=Home">Home</a></li>
                <li><a href="Controlador?menu=Actividades%20Estudiante&accion=Listar">Actividades</a></li>
                <li><a href="Controlador?menu=Perfil&accion=Ver">Mi Perfil</a></li>
                <li><a href="Controlador?menu=Login">Logout</a></li>
            </ul>
        </nav>
        
        <div class="container-perfil">
            <h1>Mi Perfil</h1>
            
            <!-- Mensajes de éxito/error -->
            <c:if test="${not empty mensaje}">
                <div class="mensaje-exito">${mensaje}</div>
            </c:if>
            <c:if test="${not empty error}">
                <div class="mensaje-error">${error}</div>
            </c:if>
            
            <!-- Perfil de Estudiante -->
            <c:if test="${not empty estudianteEnSesion}">
                <div class="perfil-card">
                    <h2>Información Personal</h2>
                    <div class="perfil-info">
                        <p><strong>Nombre:</strong> ${estudianteEnSesion.nombreEstudiante} ${estudianteEnSesion.apellidoEstudiante}</p>
                        <p><strong>Correo:</strong> ${estudianteEnSesion.correoEstudiante}</p>
                        <p><strong>Teléfono:</strong> ${estudianteEnSesion.telefono}</p>
                        <p><strong>Carrera:</strong> ${estudianteEnSesion.carrera}</p>
                        <p><strong>Horas Asignadas:</strong> ${estudianteEnSesion.horasAsignadas}</p>
                        <p><strong>Horas Cumplidas:</strong> ${estudianteEnSesion.horasCumplidas}</p>
                    </div>
                    <a href="Controlador?menu=Perfil&accion=Editar" class="btn-editar">Editar Perfil</a>
                </div>
            </c:if>
            
            <!-- Perfil de Administrador -->
            <c:if test="${not empty administradorEnSesion}">
                <div class="perfil-card">
                    <h2>Información Personal</h2>
                    <div class="perfil-info">
                        <p><strong>Nombre:</strong> ${administradorEnSesion.nombreAdmin} ${administradorEnSesion.apellidoAdmin}</p>
                        <p><strong>Correo:</strong> ${administradorEnSesion.correoAdmin}</p>
                        <p><strong>Teléfono:</strong> ${administradorEnSesion.telefono}</p>
                        <p><strong>Departamento:</strong> ${administradorEnSesion.nombreDepartamento}</p>
                    </div>
                    <a href="Controlador?menu=Perfil&accion=Editar" class="btn-editar">Editar Perfil</a>
                </div>
            </c:if>
        </div>
    </body>
</html>