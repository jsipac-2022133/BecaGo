/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Administrador;
import modelo.Estudiante;
import modeloDAO.AdministradorDAO;
import modeloDAO.EstudianteDAO;

/**
 *
 * @author SIPAC
 */
@WebServlet(name = "Controlador", urlPatterns = ("/Controlador"))
@MultipartConfig
public class Controlador extends HttpServlet {

    Administrador administrador = new Administrador();
    AdministradorDAO administradorDAO = new AdministradorDAO();
    Estudiante estudiante = new Estudiante();
    EstudianteDAO estudianteDAO = new EstudianteDAO();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String menu = request.getParameter("menu");
        String accion = request.getParameter("accion");

        if (menu == null || menu.equals("Login")) {
            request.getRequestDispatcher("vistas/Login.jsp").forward(request, response);
        } else if (menu.equals("Register")) {
            request.getRequestDispatcher("vistas/Register.jsp").forward(request, response);
        } else if (menu.equals("Admin-Estudiante")) {
            if (accion.equals("Agregar Admin")) {
                String nombreAdmin = request.getParameter("txtNombre");
                String apellidoAdmin = request.getParameter("txtApellido");
                String telefonoAdmin = request.getParameter("txtTelefono");
                String correoAdmin = request.getParameter("txtCorreo");
                String passwordAdmin = request.getParameter("txtPassword");
                String departamentoAdmin = request.getParameter("txtDepartamento");

                administrador.setNombreAdmin(nombreAdmin);
                administrador.setApellidoAdmin(apellidoAdmin);
                administrador.setTelefono(telefonoAdmin);
                administrador.setCorreoAdmin(correoAdmin);
                administrador.setPasswordAdmin(passwordAdmin);
                administrador.setNombreDepartamento(departamentoAdmin);
                administradorDAO.Agregar(administrador);
                request.getRequestDispatcher("vistas/Login.jsp").forward(request, response);

            } else if (accion.equals("Agregar Estudiante")) {
                String nombreEstudiante = request.getParameter("txtNombre");
                String apellidoEstudiante = request.getParameter("txtApellido");
                String telefonoEstudiante = request.getParameter("txtTelefono");
                String correoEstudiante = request.getParameter("txtCorreo");
                String passwordEstudiante = request.getParameter("txtPassword");
                String carreraEstudiante = request.getParameter("txtCarrera");
                int horasAsignadas = Integer.parseInt(request.getParameter("txtHorasAsignadas"));

                estudiante.setNombreEstudiante(nombreEstudiante);
                estudiante.setApellidoEstudiante(apellidoEstudiante);
                estudiante.setTelefono(telefonoEstudiante);
                estudiante.setCorreoEstudiante(correoEstudiante);
                estudiante.setPasswordEstudiante(passwordEstudiante);
                estudiante.setCarrera(carreraEstudiante);
                estudiante.setHorasAsignadas(horasAsignadas);
                estudianteDAO.Agregar(estudiante);
                request.getRequestDispatcher("vistas/Login.jsp").forward(request, response);
            }
        } else if (menu.equals("Validar")) {
            if (accion.equals("Login")) {
                String correo = request.getParameter("txtCorreo");
                String password = request.getParameter("txtPassword");
                if (correo.matches(".*\\d.*")) {
                    estudiante=estudianteDAO.validar(correo, password);
                    if(estudiante.getCorreoEstudiante()!= null){
                       request.getRequestDispatcher("vistas/Home.jsp").forward(request, response);
                    }else{
                        request.getRequestDispatcher("vistas/Login.jsp").forward(request, response);
                    }
                } else {
                    administrador=administradorDAO.validar(correo, password);
                    if(administrador.getCorreoAdmin()!=null){
                        request.getRequestDispatcher("vistas/Home.jsp").forward(request, response);
                    }else{
                        request.getRequestDispatcher("vistas/Login.jsp").forward(request, response);
                    }
                }
            }
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
