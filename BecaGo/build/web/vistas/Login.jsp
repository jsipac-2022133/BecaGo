<%-- 
    Document   : Login
    Created on : 12/09/2025, 04:17:49 PM
    Author     : SIPAC & YU-FONG
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>BecaGo - Login</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/estiloL.css"/>
    </head>
    <body class="bodyl">
        <form action="Controlador?menu=Validar" method="POST">
            <h1><a>Iniciar Sesión</a></h1>
            <label>Correo Electrónico</label>
            <input type="text" name="txtCorreo" placeholder="Correo Electrónico">
            <br>
            <label>Password</label>
            <input type="password" name="txtPassword" placeholder="Password">
            <br>
            
            <% 
            String error = (String) request.getAttribute("error");
            if (error != null) {
            %>
                <div style="color: red;">
                    <%= error %>
                </div><br>
            <% } %>
            
            <button type="submit" name="accion" value="Login">Login</button>
            <br>
            <p>¿No tienes cuenta? <a href="Controlador?menu=Register">Registrarse</a></p>
            
        </form>
    </body>
</html>
