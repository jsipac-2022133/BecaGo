<%-- 
    Document   : Login
    Created on : 12/09/2025, 04:17:49 PM
    Author     : SIPAC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>BecaGo - Login</title>
    </head>
    <body>
        <h1><a>Login</a></h1>
        <form action="Controlador?menu=Validar" method="POST">
            <label>Correo Electrónico</label>
            <input type="text" name="txtCorreo" placeholder="Correo Electrónico">
            <label>Password</label>
            <input type="password" name="txtPassword" placeholder="Password">
            <button type="submit" name="accion" value="Login">Login</button>
            <p>No tienes cuenta? <a href="Controlador?menu=Register">Registrarse</a></p>
            
        </form>
    </body>
</html>
