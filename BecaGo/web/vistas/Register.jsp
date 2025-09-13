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
        <title>Register</title>
        <script>
            function checkCorreo() {
                const correo = document.getElementById("correo").value;
                const deptoField = document.getElementById("deptoField");
                const extraFields = document.getElementById("extraFields");
                
                const tieneNumeros = /\d/.test(correo);

                if (!tieneNumeros) {
                    deptoField.style.display = "block";
                    extraFields.style.display = "none";
                } else {
                    deptoField.style.display = "none";
                    extraFields.style.display = "block";
                }
            }

        </script>
    </head>
    <body>
        <h1>Register</h1>
        <form action="Controlador?menu=Admin-Estudiante" method="POST">
            <label>Nombre</label>
            <input type="text" name="txtNombre" placeholder="Nombre"><br>

            <label>Apellido</label>
            <input type="text" name="txtApellido" placeholder="Apellido"><br>

            <label>Teléfono</label>
            <input type="text" name="txtTelefono" placeholder="Teléfono"><br>            

            <label>Correo</label>
            <input type="text" id="correo" name="txtCorreo" placeholder="Correo electrónico" onkeyup="checkCorreo()"><br>            

            <label>Password</label>
            <input type="password" name="txtPassword" placeholder="Password"><br>

            <div id="deptoField" style="display:none;">
                <label>Nombre Departamento</label>
                <input type="text" name="txtDepartamento" placeholder="Departamento UVG"><br>

                <button type="submit" name="accion" value="Agregar Admin">Crear cuenta</button>
            </div>

            <div id="extraFields" style="display:none;">
                <label>Carrera</label>
                <input type="text" name="txtCarrera" placeholder="Carrera"><br>
                <label>Horas Asignadas</label>
                <input type="number" name="txtHorasAsignadas" placeholder="Cantidad de horas Asignadas"><br>

                <button type="submit" name="accion" value="Agregar Estudiante">Crear cuenta</button>
            </div>


        </form>
    </body>
</html>
