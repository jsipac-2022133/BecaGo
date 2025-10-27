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
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/estiloR.css"/>
        <script>
            
            // Validación del nombre y apellido (sólo letras del alfabeto y guiones)
            function validarNombreApellido(campoId, errorId) {
                const campo = document.getElementById(campoId);
                const error = document.getElementById(errorId);
                
                // Permite letras (incluyendo acentos y ñ) y guiones
                const nombrePermitidos = /^[a-zA-ZáéíóúÁÉÍÓÚñÑ\-\s]+$/;
                
                if (campo.value && !nombrePermitidos.test(campo.value)) {
                    error.style.display = "block";
                    campo.style.borderColor = "red";
                    return false;
                } else {
                    error.style.display = "none";
                    campo.style.borderColor = "";
                    return true;
                }
            }
            
            // Validación del teléfono (sólo números y 8 de largo)
            function validarTel() {
                const telefono = document.getElementById("telefono");
                const errorTelefono = document.getElementById("errorTelefono");
                
                // Elimina espacios y guiones
                telefono.value = telefono.value.replace(/[\s\-]/g, '');
    
                // Verifica que sólo contenga números
                const soloNumeros = /^\d+$/;
                
                if (telefono.value) {
                    if (!soloNumeros.test(telefono.value)) {
                        errorTelefono.textContent = "Sólo se permiten números";
                        errorTelefono.style.display = "block";
                        telefono.style.borderColor = "red";
                        return false;
                    } else if (telefono.value.length !== 8) {
                        errorTelefono.textContent = "Debe tener 8 dígitos";
                        errorTelefono.style.display = "block";
                        telefono.style.borderColor = "red";
                        return false;
                    } else {
                        errorTelefono.style.display = "none";
                        telefono.style.borderColor = "";
                        return true;
                    }
                } else {
                    errorTelefono.style.display = "none";
                    telefono.style.borderColor = "";
                    return true;
                }
            }
            
            // Validación del correo (elimina espacios y guarda el correo completo (con @uvg.edu.gt))
            // Mantiene la validación de Administrador / Alumno basado en si tiene o no números
            function validarCorreo() {
                const correo = document.getElementById("correo");
                const deptoField = document.getElementById("unidadDepto");
                const estudiante = document.getElementById("estudiante");
                const errorCorreo = document.getElementById("errorCorreo");
                
                if (correo.value) {
                    // Valida que no tenga espacios
                    if (/\s/.test(correo.value)) {
                        errorCorreo.textContent = "No se permiten espacios en el correo";
                        errorCorreo.style.display = "block";
                        correo.style.borderColor = "red";
                        return;
                    }
        
                    // Valida que termine con @uvg.edu.gt
                    if (!correo.value.endsWith("@uvg.edu.gt")) {
                        errorCorreo.textContent = "El correo debe terminar con @uvg.edu.gt";
                        errorCorreo.style.display = "block";
                        correo.style.borderColor = "red";
                        return;
                    }
        
                    // Si pasa las validaciones
                    errorCorreo.style.display = "none";
                    correo.style.borderColor = "";
        
                    // Extraer la parte antes del @ para verificar si tiene números
                    const correoInicio = correo.value.split("@")[0];
                    const tieneNumeros = /\d/.test(correoInicio);
        
                    if (!tieneNumeros) {
                        deptoField.style.display = "block";
                        estudiante.style.display = "none";
                    } else {
                        deptoField.style.display = "none";
                        estudiante.style.display = "block";
                    }
                } else {
                    errorCorreo.style.display = "none";
                    correo.style.borderColor = "";
                    deptoField.style.display = "none";
                    estudiante.style.display = "none";
                }
            }
            
            // Valida la contraseña (Al menos 8 de largo, 1 letra mayúscula, 1 número y 1 símbolo especial)
            function validarPassword() {
                const password = document.getElementById("password");
                const errorPassword = document.getElementById("errorPassword");
    
                if (password.value) {

                    // Verifica la longitud mínima de 8
                    if (password.value.length < 8) {
                        errorPassword.textContent = "La contraseña debe tener al menos 8 caracteres";
                        errorPassword.style.display = "block";
                        password.style.borderColor = "red";
                        return false;
                    }
                    // Verifica que tenga al menos una letra mayúscula
                    if (!/[A-Z]/.test(password.value)) {
                        errorPassword.textContent = "Debe contener al menos una letra mayúscula";
                        errorPassword.style.display = "block";
                        password.style.borderColor = "red";
                        return false;
                    }
                    // Verifica que tenga al menos un número
                    if (!/\d/.test(password.value)) {
                        errorPassword.textContent = "Debe contener al menos un número";
                        errorPassword.style.display = "block";
                        password.style.borderColor = "red";
                        return false;
                    }
                    // Verifica que tenga al menos un símbolo especial
                    if (!/[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]/.test(password.value)) {
                        errorPassword.textContent = "Debe contener al menos un símbolo especial (!@#$%^&*...)";
                        errorPassword.style.display = "block";
                        password.style.borderColor = "red";
                        return false;
                    }
                    // Si pasa todas las validaciones...
                    errorPassword.style.display = "none";
                    password.style.borderColor = "green";
                    return true;
                    
                } else {
                    errorPassword.style.display = "none";
                    password.style.borderColor = "";
                    return true;
                }
            }
            
            // Botón para mostrar/ocultar la contraseña
            function togglePassword() {
                const passwordField = document.querySelector('input[name="txtPassword"]');
                const toggleButton = document.querySelector('.toggle-password');
                
                if (passwordField.type === "password") {
                    passwordField.type = "text";
                    toggleButton.style.opacity = "1";
                } else {
                    passwordField.type = "password";
                    toggleButton.style.opacity = "0.5";
                }
            }
            
            function mostrarCarreras() {
                const facultad = document.getElementById("facultad").value;
                const carreraField = document.getElementById("carreraField");
                const carreraSelect = document.getElementById("carrera");
    
                // Limpiar opciones anteriores
                carreraSelect.innerHTML = '<option value="">Seleccione una carrera</option>';
    
                // Definir carreras por facultad
                const carreras = {
                    "Ingeniería": [
                        "Biomédica",
                        "Biotecnología Industrial",
                        "Ciencia de la Administración",
                        "Ciencias de Alimentos",
                        "Ciencias de Alimentos Industrial",
                        "Civil",
                        "Civil Arquitectónica",
                        "Ciencia de la Computación y Tecnologías de la Información",
                        "Sistemas de Información Computacional",
                        "Electrónica",
                        "Industrial",
                        "Mecánica",
                        "Mecánica Industrial",
                        "Mecatrónica",
                        "Química",
                        "Química Industrial"
                    ],
                    "Bridge Business School": [
                        "Ingeniería en Ciencia de la Administración",
                        "Administración de Empresas",
                        "International Marketing and Business Analytics",
                        "Comunicación Estratégica"
                    ],
                    "Educación": [
                        "Profesorado de Enseñanza Media especializado en Educación Musical",
                        "Profesorado de Enseñanza Media especializado en English Language Teaching (ELT)",
                        "Profesorado Especializado en Educación Inclusiva",
                        "Profesorado Especializado en Educación Primaria (100% virtual)",
                        "Profesorado Especializado en Problemas del Aprendizaje",
                        "Profesorado de Enseñanza Media Especializado en Matemática y Ciencias Físicas",
                        "Profesorado de Enseñanza Media Especializado en Ciencias Químicas y Biológicas",
                        "Profesorado de Enseñanza Media Especializado en Ciencias Sociales",
                        "Profesorado de Enseñanza Media Especializado en Comunicación y Lenguaje"
                    ],
                    "Colegio Universitario": [
                        "Baccalaureatus en Artibus",
                        "Baccalaureatus en Scientiis"
                    ],
                    "Escuela de Arquitectura": [
                        "Arquitectura"
                    ],
                    "Ciencias y Humanidades": [
                        "Biología",
                        "Bioquímica y Microbiología",
                        "Biotecnología Molecular",
                        "Física",
                        "Matemática Aplicada",
                        "Nutrición",
                        "Química",
                        "Química Farmacéutica"
                    ],
                    "Ciencias Sociales": [
                        "Antropología",
                        "Arqueología",
                        "Psicología",
                        "Relaciones Internacionales"
                    ],
                    "Design Innovation & Arts School": [
                        "Composición y Producción Musical",
                        "Diseño de Producto e Innovación"
                    ]
                };
    
                // Si hay una facultad seleccionada, mostrar sus carreras
                if (facultad && carreras[facultad]) {
                    carreraField.style.display = "block";
                    carreras[facultad].forEach(carrera => {
                        const option = document.createElement("option");
                        option.value = carrera;
                        option.textContent = carrera;
                        carreraSelect.appendChild(option);
                    });
                } else {
                    carreraField.style.display = "none";
                }
            }
            
            // Capitaliza la primera letra de cada palabra (Para nombres y apellidos)
            function capPrimerasLetras(campoId) {
                const campo = document.getElementById(campoId);
    
                // Capitaliza la primera letra de cada palabra y después de guiones
                campo.value = campo.value
                    .toLowerCase()
                    .split(/(\s|-)/g)  // Divide por espacios y guiones, pero mantiene los separadores
                    .map(parte => {
                        if (parte === ' ' || parte === '-') {
                            return parte;  // Mantiene espacios y guiones como están
                        }
                        return parte.charAt(0).toUpperCase() + parte.slice(1);
                    })
                    .join('');
            }
            
            // Valida las horas ingresadas (no sean negativas o se pasen de 50)
            function validarHoras() {
                const horas = document.getElementById("horas");
                const errorHoras = document.getElementById("errorHoras");
                
                // Eliminar cualquier carácter que no sea número
                horas.value = horas.value.replace(/[^0-9]/g, '');
    
                if (horas.value) {
                    const valorHoras = parseInt(horas.value);
        
                    if (valorHoras < 0) {
                        errorHoras.textContent = "Las horas no pueden ser negativas";
                        errorHoras.style.display = "block";
                        horas.style.borderColor = "red";
                        return false;
                    } else if (valorHoras > 50) {
                        errorHoras.textContent = "Las horas no pueden ser mayores a 50";
                        errorHoras.style.display = "block";
                        horas.style.borderColor = "red";
                        return false;
                    } else {
                        errorHoras.style.display = "none";
                        horas.style.borderColor = "";
                        return true;
                    }
                } else {
                    errorHoras.style.display = "none";
                    horas.style.borderColor = "";
                    return true;
                }
            }
            
            // Capitaliza el nombre y apellido antes de que se envíe el formulario
            // Verifica que el formulario esté lleno antes de mandarlo
            function validarForm() {
                capPrimeraLetra('nombre');
                capPrimeraLetra('apellido');
                
                // Obtiene los valores comunes
                const nombre = document.getElementById("nombre").value.trim();
                const apellido = document.getElementById("apellido").value.trim();
                const telefono = document.getElementById("telefono").value.trim();
                const correo = document.getElementById("correo").value.trim();
                const password = document.getElementById("password").value;
    
                if (!nombre) {
                    alert("Por favor ingrese su nombre");
                    return false;
                }
                if (!apellido) {
                    alert("Por favor ingrese su apellido");
                    return false;
                }
                if (!telefono) {
                    alert("Por favor ingrese su teléfono");
                    return false;
                }
                if (!correo) {
                    alert("Por favor ingrese su correo");
                    return false;
                }
                if (!password) {
                    alert("Por favor ingrese su contraseña");
                    return false;
                }
    
                // Verifica si es admin o estudiante
                const tieneNumeros = /\d/.test(correo);
    
                if (!tieneNumeros) {
                    
                    // Para Administración
                    const unidadDepto = document.querySelector('select[name="txtUnidadDepto"]').value;
                    if (!unidadDepto) {
                        alert("Por favor seleccione una unidad o departamento");
                        return false;
                    }
                } else {
                    // Para Estudiante
                    const facultad = document.getElementById("facultad").value;
                    const carrera = document.getElementById("carrera").value;
                    const horas = document.getElementById("horas").value;
        
                    if (!facultad) {
                        alert("Por favor seleccione su facultad");
                        return false;
                    }
                    if (!carrera) {
                        alert("Por favor seleccione su carrera");
                        return false;
                    }
                    if (!horas) {
                        alert("Por favor ingrese las horas asignadas");
                        return false;
                    }
                }
                return true; // Permite enviar el formulario si todo está bien
            }

        </script>
    </head>
    
    <%@ page import="java.util.ArrayList" %>
    
    <body class="bodyr">
        
        <form action="Controlador?menu=Admin-Estudiante" method="POST" onsubmit="return validarForm()">
            <h1 class="title">Registrarse</h1>
            
            <% 
            ArrayList<String> errores = (ArrayList<String>) request.getAttribute("errores");
            if (errores != null && !errores.isEmpty()) {
            %>
                <div style="color: red; padding: 10px;">
                    <% for (String error : errores) { %>
                        <%= error %><br>
                    <% } %>
                </div><br>
            <% } %>
            
            <label>Nombre</label>
            <input type="text" id="nombre" name="txtNombre" placeholder="" onblur="validarNombreApellido('nombre', 'errorNombre')"><br>
            <span id="errorNombre" style="display:none; color:red;">Sólo se permiten letras y guiones</span><br>

            <label>Apellido</label>
            <input type="text" id="apellido" name="txtApellido" placeholder="" onblur="validarNombreApellido('apellido', 'errorApellido')"><br>
            <span id="errorApellido" style="display:none; color:red;">Sólo se permiten letras y guiones</span><br>

            <label>Teléfono</label><br>
            <div style="display: flex; align-items: center; gap: 5px;">
                <span style="font-size: 14px; color: #555555;">+502</span>
                <input type="text" id="telefono" name="txtTelefono" placeholder="" oninput="validarTel()" onblur="validarTel()">
            </div>
            <span id="errorTelefono" style="display:none; color:red;"></span>
            <span id="errorTelefonoExistente" style="display:none; color:red;"></span><br>
            
            <label>Correo Electrónico</label><br>
            <input type="email" id="correo" name="txtCorreo" placeholder="usuario@uvg.edu.gt" onblur="validarCorreo()"">
            <span id="errorCorreo" style="display:none; color:red;"></span><br><br>
            
            <label>Contraseña</label>
            <div class="password-container">
                <input type="password" id="password" name="txtPassword" placeholder="" onblur="validarPassword()">
                <button type="button" class="toggle-password" onclick="togglePassword()">
                    👁
                </button>
            </div>
            <span id="errorPassword" style="display:none; color:red;"></span><br>

            <div id="unidadDepto" style="display:none;">
                <label>Unidad / Departamento</label>
                <select name="txtUnidadDepto">
                    <option value="">Seleccione una unidad o departamento</option>
                    <option value="Consejo Directivo">Consejo Directivo</option>
                    <option value="Decanatura de Admisiones">Decanatura de Admisiones</option>
                    <option value="Facultad de Ingeniería">Facultad de Ingeniería</option>
                    <option value="Facultad de Ciencias y Humanidades">Facultad de Ciencias y Humanidades</option>
                    <option value="Facultad de Ciencias Sociales">Facultad de Ciencias Sociales</option>
                    <option value="Facultad de Educación">Facultad de Educación</option>
                    <option value="Bridge Business School">Bridge Business School</option>
                    <option value="Escuela de Artquitectura">Escuela de Artquitectura</option>
                    <option value="Design Innovation & Arts">Design Innovation & Arts</option>
                    <option value="Colegio Universitario y Asuntos Estudiantiles">Colegio Universitario y Asuntos Estudiantiles</option>
                    <option value="Dirección General de Estudios">Dirección General de Estudios</option>
                    <option value="Decanos">Decanos</option>
                    <option value="Instituto de Investigaciones">Instituto de Investigaciones</option>
                    <option value="Administrativos">Administrativos</option>
                </select><br>
                <br>
                
                <button type="submit" name="accion" value="Agregar Admin">Crear cuenta</button>
            </div>
            
            <div id="estudiante" style="display:none;">
                <label>Facultad</label>
                    <select id="facultad" name="txtFacultad" onchange="mostrarCarreras()">
                        <option value="">Seleccione una facultad</option>
                        <option value="Ingeniería">Ingeniería</option>
                        <option value="Educación">Educación</option>
                        <option value="Ciencias y Humanidades">Ciencias y Humanidades</option>
                        <option value="Ciencias Sociales">Ciencias Sociales</option>
                        <option value="Bridge Business School">Bridge Business School</option>
                        <option value="Design Innovation & Arts School">Design Innovation & Arts School</option>
                        <option value="Colegio Universitario">Colegio Universitario</option>
                        <option value="Escuela de Arquitectura">Escuela de Arquitectura</option>
                    </select><br>
                
                <div id="carreraField" style="display:none;">
                    <br>
                    <label>Carrera</label>
                    <select id="carrera" name="txtCarrera">
                        <option value="">Seleccione una carrera</option>
                    </select><br>
                </div>
                <br>
                
                <label>Horas Asignadas</label>
                <input type="number" id="horas" name="txtHorasAsignadas" placeholder="" onblur="validarHoras()" min="0" max="50">
                <span id="errorHoras" style="display:none; color:red;"></span><br>
                <br>
                
                <button type="submit" name="accion" value="Agregar Estudiante">Crear cuenta</button>
            </div>


        </form>
    </body>
</html>
