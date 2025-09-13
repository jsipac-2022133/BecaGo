<%-- 
    Document   : Register
    Created on : 12/09/2025, 04:18:09 PM
    Author     : SIPAC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1><a>Register</a></h1>
        <form action="Controlador?menu=Administrador" method="POST">
            <label>Nombre</label>
            <input type="text" name="txtNombre" placeholder="Nombre">
            <br>
            <label>Apellido</label>
            <input type="text" name="txtApellido" placeholder="Apellido">
            <br>
            <label>Teléfono</label>
            <input type="text" name="txtTelefono" placeholder="Teléfono">
            <br>            
            <label>Correo</label>
            <input type="text" name="txtCorreo" placeholder="Correo electrónico">
            <br>
            <label>Password</label>
            <input type="password" name="txtPassword" placeholder="Password">
            <br>
            <label>Nombre Departamento</label>
            <input type="text" name="txtDepartamento" placeholder="Departamento UVG">
            <button type="submit" name="accion" value="Agregar">Crear cuenta</button>
        </form>
    </body>
</html>
