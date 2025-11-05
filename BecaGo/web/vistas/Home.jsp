<%-- 
    Document   : Home
    Created on : 12/09/2025, 11:42:30 PM
    Author     : SIPAC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/estiloH.css"/>
    </head>
    <body class = "bodyh">
        <nav>
            <ul>
                <li><a href="Controlador?menu=Home">Home</a></li>
                <li><a href="Controlador?menu=Actividades%20Estudiante&accion=Listar">Actividades</a></li>
                <li><a href="Controlador?menu=Inscripciones%20Estudiante&accion=Listar">Inscripciones</a></li>
                <li><a href="Controlador?menu=Resumen&accion=Listar">Resumen</a></li>
                <li><a href="Controlador?menu=Perfil&accion=Mostrar">Mi Perfil</a></li>
                <li><a href="Controlador?menu=Logout">Logout</a></li>
            </ul>
        </nav>
        <div class="main-content">
            <main>
                <section class = "sec1">
                    <h1 class = "titleh">Bienvenido a BecaGo</h1>
                    <p>Bienvenido a nuestra plataforma!!! Acá tendrás acceso
                        a un panel para gestionar tus actividades de horas beca de manera
                        sencilla y eficiente. Podrás ver tu progreso y enlistarte a las actividades
                        que más te interesen. Estamos para apoyarte en tu desarrollo académico y personal.
                        Cualquier duda no dudes en contactarnos.
                          <li><a href="Controlador?menu=Perfil&accion=Mostrar" class="profile-btn">Mi Perfil</a></li>
                    </p>
                   
                    </div>
                </section>
            </main> 
        </div>
    </body>
</html>
