<%-- 
    Document   : Register
    Created on : 12/09/2025, 04:18:09 PM
    Author     : SIPAC, YU-FONG
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registrarse</title>
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
                const correoCompleto = document.getElementById("correoCompleto");
                const deptoField = document.getElementById("unidadDepto");
                const estudiante = document.getElementById("estudiante");
                const errorCorreo = document.getElementById("errorCorreo");
                
                // Valida que no esté vacío y no tenga espacios
                if (correo.value && /\s/.test(correo.value)) {
                    errorCorreo.style.display = "block";
                    correo.style.borderColor = "red";
                    return;
                } else {
                    errorCorreo.style.display = "none";
                    correo.style.borderColor = "";
                }
                
                // Actualizar el correo completo
                correoCompleto.value = correo.value + "@uvg.edu.gt";
                
                // Verifica si tiene números o no
                const tieneNumeros = /\d/.test(correo.value);
                if (!tieneNumeros && correo.value) {
                    deptoField.style.display = "block";
                    estudiante.style.display = "none";
                } else if (tieneNumeros) {
                    deptoField.style.display = "none";
                    estudiante.style.display = "block";
                } else {
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
            
            // Capitaliza la primera letra de cada palabra (Para nombres y apellidos)
            function capPrimeraLetra(campoId) {
                const campo = document.getElementById(campoId);
    
                campo.value = campo.value
                    .toLowerCase()
                    .split(' ')
                    .map(palabra => palabra.charAt(0).toUpperCase() + palabra.slice(1))
                    .join(' ');
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
                        "Electrónica",
                        "Civil",
                        "Civil Arquitectónica",
                        "Industrial",
                        "Mecánica",
                        "Química",
                    ],
                    "Educación": [
                        "Profesorado en Educación Inclusiva"
                    ],
                    "Ciencias y Humanidades": [
                        "Biología",
                        "Bioquímica y Microbiología",
                        "Física",
                        "Nutrición"
                    ],
                    "Ciencias Sociales": [
                        "Antropología",
                        "Arqueología"
                    ],
                    "Bridge Business School": [
                        "Administración de Empresas",
                    ],
                    "Design Innovation & Arts School": [
                        "Composición y Producción Musical",
                        "Diseño de Producto e Innovación"
                    ],
                    "Colegio Universitario": [
                        "Baccalaureatus en Artibus",
                        "Baccalaureatus en Scientiis¨"
                    ],
                    "Escuela de Arquitectura": [
                        "Arquitectura"
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
    <body>
        
        <a href="Controlador?menu=Login" >← Volver al Iniciar Sesión</a>
        
        <h1>Registrarse</h1>
        <form action="Controlador?menu=Admin-Estudiante" method="POST" onsubmit="return validarForm()">
            
            <label>Nombre</label>
            <input type="text" id="nombre" name="txtNombre" placeholder="Nombre" onblur="validarNombreApellido('nombre', 'errorNombre')"><br>
            <span id="errorNombre" style="display:none; color:red;">Sólo se permiten letras y guiones</span><br>

            <label>Apellido</label>
            <input type="text" id="apellido" name="txtApellido" placeholder="Apellido" onblur="validarNombreApellido('apellido', 'errorApellido')"><br>
            <span id="errorApellido" style="display:none; color:red;">Sólo se permiten letras y guiones</span><br>
            
            <label>Teléfono</label>
            <input type="text" id="telefono" name="txtTelefono" placeholder="Teléfono" onblur="validarTel()"><br>
            <span id="errorTelefono" style="display:none; color:red;"></span><br>
            
            <label>Correo</label>
            <input type="text" id="correo" name="txtCorreoPrincipio" placeholder="Correo Electrónico" onkeyup="validarCorreo()" style="width: 200px;" style="margin: 5px;"> @uvg.edu.gt<br>
            <input type="hidden" id="correoCompleto" name="txtCorreo">
            <span id="errorCorreo" style="display:none; color:red;">No se permiten espacios en el correo</span><br>
            
            <label>Contraseña</label>
            <input type="password" id="password" name="txtPassword" placeholder="Password" onblur="validarPassword()"><br>
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
                    <option value="Administrativosó">Administrativos</option>
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
