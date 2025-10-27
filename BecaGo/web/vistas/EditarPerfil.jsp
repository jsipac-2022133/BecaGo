<%-- 
    Document   : EditarPerfil
    Created on : Oct 26, 2025, 2:09:02 PM
    Author     : Guti
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editar Perfil - BecaGo</title>
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
            <h1>Editar Perfil</h1>
            
            <!-- Formulario Editar Estudiante -->
            <c:if test="${not empty estudianteEnSesion}">
                <form action="Controlador?menu=Perfil" method="POST" class="form-editar">
                    <input type="hidden" name="idEstudiante" value="${estudianteEnSesion.idEstudiante}">
                    
                    <div class="form-group">
                        <label>Nombre:</label>
                        <input type="text" name="txtNombre" value="${estudianteEnSesion.nombreEstudiante}" required>
                    </div>
                    
                    <div class="form-group">
                        <label>Apellido:</label>
                        <input type="text" name="txtApellido" value="${estudianteEnSesion.apellidoEstudiante}" required>
                    </div>
                    
                    <div class="form-group">
                        <label>Teléfono:</label>
                        <input type="text" name="txtTelefono" value="${estudianteEnSesion.telefono}" required>
                    </div>
                    
                    <div class="form-group">
                        <label>Carrera:</label>
                        <input type="text" name="txtCarrera" value="${estudianteEnSesion.carrera}" required>
                    </div>
                    
                    <div class="form-group">
                        <label>Nueva Contraseña (dejar vacío para no cambiar):</label>
                        <input type="password" name="txtPassword" placeholder="Nueva contraseña">
                    </div>
                    
                    <button type="submit" name="accion" value="ActualizarEstudiante" class="btn-actualizar">Actualizar Perfil</button>
                    <a href="Controlador?menu=Perfil&accion=Ver" class="btn-cancelar">Cancelar</a>
                </form>
            </c:if>
            
            <!-- Formulario Editar Administrador -->
            <c:if test="${not empty administradorEnSesion}">
                <form action="Controlador?menu=Perfil" method="POST" class="form-editar">
                    <input type="hidden" name="idAdmin" value="${administradorEnSesion.idAdmin}">
                    
                    <div class="form-group">
                        <label>Nombre:</label>
                        <input type="text" name="txtNombre" value="${administradorEnSesion.nombreAdmin}" required>
                    </div>
                    
                    <div class="form-group">
                        <label>Apellido:</label>
                        <input type="text" name="txtApellido" value="${administradorEnSesion.apellidoAdmin}" required>
                    </div>
                    
                    <div class="form-group">
                        <label>Teléfono:</label>
                        <input type="text" name="txtTelefono" value="${administradorEnSesion.telefono}" required>
                    </div>
                    
                    <div class="form-group">
                        <label>Departamento:</label>
                        <input type="text" name="txtDepartamento" value="${administradorEnSesion.nombreDepartamento}" required>
                    </div>
                    
                    <div class="form-group">
                        <label>Nueva Contraseña (dejar vacío para no cambiar):</label>
                        <input type="password" name="txtPassword" placeholder="Nueva contraseña">
                    </div>
                    
                    <button type="submit" name="accion" value="ActualizarAdmin" class="btn-actualizar">Actualizar Perfil</button>
                    <a href="Controlador?menu=Perfil&accion=Ver" class="btn-cancelar">Cancelar</a>
                </form>
            </c:if>
        </div>
    </body>
</html>