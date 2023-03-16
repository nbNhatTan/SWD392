/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package sample.Controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.DAO.AccountDAO;
import sample.DTO.AccountDTO;

/**
 *
 * @author NhatTan
 */
@WebServlet(name = "LoginController", urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet {

    private static final String ERROR = "login.jsp";
    private static final String ADMIN_PAGE = "ViewAccountController";
    private static final String STAFF_PAGE = "bookingOffline.jsp";
    private static final String USER_PAGE = "HomeController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String accID = request.getParameter("accountID");
            String password = request.getParameter("password");
            AccountDAO dao = new AccountDAO();
            AccountDTO loginAccount = dao.checkLogin(accID, password);
            if (loginAccount != null) {
                boolean status = loginAccount.getStatus();
                if (status) {
                    HttpSession session = request.getSession();
                    session.setAttribute("LOGIN_ACCOUNT", loginAccount);
                    int roleID = loginAccount.getRoleID();
                    if (roleID == 1) {
                        url = ADMIN_PAGE;
                    request.setAttribute("message", "Login");
                    } else if (roleID == 2) {
                        url = STAFF_PAGE;
                    request.setAttribute("message", "Login");
                    }else if (roleID == 3) {
                        url = USER_PAGE;
                    request.setAttribute("message", "Login");
                    } else {
                        request.setAttribute("ERROR", "Your role is not support!");
                    }
                } else {
                    request.setAttribute("ERROR", "Account have been deleted!");
                }
            } else {
                request.setAttribute("ERROR", "Incorrect userID or password!");
            }

        } catch (Exception e) {
            log("Error at LoginController: " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
