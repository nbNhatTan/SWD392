/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package sample.Controllers;

import java.io.IOException;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.DAO.AccountDAO;
import sample.DTO.AccountDTO;
import sample.DTO.AccountError;

/**
 *
 * @author NhatTan
 */
@WebServlet(name = "UpdateAccountController", urlPatterns = {"/UpdateAccountController"})
public class UpdateAccountController extends HttpServlet {

    private static final String ERROR = "updateAccount.jsp";
    private static final String SUCCESS = "HomeController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String accountID = request.getParameter("accountID");
            String fullName = request.getParameter("fullName");
            String roleID = request.getParameter("roleID");
            String password = request.getParameter("password");
            String confirm = request.getParameter("confirm");
            String email = request.getParameter("email");
            String address = request.getParameter("address");
            String phone = request.getParameter("phone");

            boolean checkValidation = true;
            AccountError accountError = new AccountError();
            AccountDAO dao = new AccountDAO();

            
            if (fullName.length() < 5 || fullName.length() > 20) {
                accountError.setFullNameError("FullName must be in [5, 20]");
                checkValidation = false;
            }
            if (!password.equals(confirm)) {
                accountError.setConfirmError("Password must equals!");
                checkValidation = false;
            }
             
            if (!Pattern.matches("^[a-zA-Z][\\w-]+@fpt.edu.vn$", email)) {
                accountError.setEmailError("Email not correct!");
                checkValidation = false;
            }
            if (address.length() < 5 || address.length() > 40) {
                accountError.setAddressError("Address must be in [5, 40]");
                checkValidation = false;
            }
            if (!Pattern.matches("\\d{10,12}", phone)) {
                accountError.setPhoneError("Phone must be number and in [10, 12]");
                checkValidation = false;
            }
            AccountDTO account = new AccountDTO(accountID, fullName, password, Integer.parseInt(roleID), email, address, phone, true);
            if (checkValidation) {                
                boolean checkUpdate = dao.update(account);
                if (checkUpdate) {
                    url = SUCCESS;
                    request.setAttribute("message", "Update");
                    HttpSession session = request.getSession();
                    session.setAttribute("LOGIN_ACCOUNT", account);
                }
            } else {
                HttpSession session = request.getSession();
                request.setAttribute("ACCOUNT_DETAIL", account);
                request.setAttribute("ACCOUNT_ERROR", accountError);
            }
        } catch (Exception e) {
            log("Error at UpdateController: " + e.toString());
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
