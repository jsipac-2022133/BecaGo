<%-- 
    Document   : InformacionActividad
    Created on : 12/10/2025, 06:03:33 AM
    Author     : SIPAC
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>

<script>
document.addEventListener("DOMContentLoaded", function() {
  const inscribirseBtn = document.querySelector(".btn-inscribirse:not(.disabled)");
  const modal = document.getElementById("confirmModal");
  const confirmBtn = document.getElementById("confirmBtn");
  const cancelBtn = document.getElementById("cancelBtn");

  if (inscribirseBtn) {
    inscribirseBtn.addEventListener("click", function(event) {
      event.preventDefault(); // Evita la redirección inmediata
      modal.style.display = "block";
    });
  }

  confirmBtn.addEventListener("click", function() {
    window.location.href = inscribirseBtn.getAttribute("href");
  });

  cancelBtn.addEventListener("click", function() {
    modal.style.display = "none";
  });

  // Cierra si se hace clic fuera del modal
  window.onclick = function(event) {
    if (event.target == modal) {
      modal.style.display = "none";
    }
  };
});
</script>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>${actividadIndividual.getNombreActividad()}</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/estiloIA.css"/>
        <style>
            /* Diego agrega estos estilos a tu documetno de css */
            .btn-inscribirse.disabled {
                pointer-events: none;
                opacity: 0.6;
                cursor: not-allowed;
            }
        </style>
    </head>
    <body class="bodya">
        <nav>
            <ul>
                <li><a href="Controlador?menu=Home">Home</a></li>
                <li><a href="Controlador?menu=Actividades%20Estudiante&accion=Listar">Actividades</a></li>
                <li><a href="#">Inscripciones</a></li>
                <li><a href="#">Resumen</a></li>
                <li><a href="Controlador?menu=Logout">Logout</a></li>
            </ul>
        </nav>

        <div class="container-principal">
            <div class="card-info">
                <input type="hidden" name="idActividad" value="${actividadIndividual.getIdActividad()}"/>                
                <h1>📌${actividadIndividual.getNombreActividad()}</h1>
                <h3>📅 <b>Fecha y Hora: </b><fmt:formatDate value="${actividadIndividual.getFechaActividad()}" pattern="dd/MM/yy hh:mm a" /></h3>
                <h3>📍 <b>Ubicación: </b>${actividadIndividual.getUbicacion()}</h3>
                <h3>⏱ <b>Horas Dadas: </b><fmt:formatNumber value="${actividadIndividual.getHorasDadas()}" maxFractionDigits="0" /></h3>
                <h3>👥 <b>Cupos Disponibles: </b>${actividadIndividual.getCuposDisponibles()}</h3>   
                
                <div class="btn-container">
                    <c:choose>
                        <c:when test="${yaInscrito}">
                            <a class="btn-inscribirse disabled">✅ Inscrito</a>
                        </c:when>
                        <c:otherwise>
                            <a href="Controlador?menu=Inscripcion&accion=Agregar&idActividad=${actividadIndividual.getIdActividad()}" 
                               class="btn-inscribirse">Inscribirse</a>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>

            <div class="container-imagen">
                <c:if test="${actividadIndividual.getImagen() != null}">
                    <img src="Controlador?menu=Actividad&accion=VerImagen&idActividad=${actividadIndividual.getIdActividad()}" 
                         class="imagen-actividad" alt="Imagen actividad"/>
                </c:if>
            </div>
        </div>
                
        <!-- Modal de confirmación -->
            <div id="confirmModal" class="modal">
                <div class="modal-content"> <br>
                    <h2>Confirmar inscripción</h2> <br>
                    <p>¿Estás seguro/a de que deseas</p>
                    <p>inscribirte en esta actividad?</p> <br>
                    <div class="modal-buttons">
                        <button id="confirmBtn">Confirmar</button>
                        <button id="cancelBtn">Cancelar</button>
                    </div>
                </div>
            </div>        
    </body>
</html>
