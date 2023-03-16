/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.Controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sample.DAO.AccountDAO;
import sample.DTO.AccountDTO;
import sample.DTO.AccountError;

/**
 *
 * @author Admin
 */
@WebServlet(name = "AddAccountController", urlPatterns = {"/AddAccountController"})
public class AddAccountController extends HttpServlet {

    private static final String ERROR = "addAccount.jsp";
    private static final String SUCCESS = "ViewAccountController";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String accountID = request.getParameter("accountID").toLowerCase();
            String fullName = request.getParameter("fullName");
            String password = request.getParameter("password");
            String confirm = request.getParameter("confirm");
            String roleID = request.getParameter("roleID");
            String email = request.getParameter("email");
            String address = request.getParameter("address");
            String phone = request.getParameter("phone");
            request.setAttribute("accountID", accountID);
            request.setAttribute("fullName", fullName);
            request.setAttribute("password", password);
            request.setAttribute("confirm", confirm);
            request.setAttribute("roleID", roleID);
            request.setAttribute("email", email);
            request.setAttribute("address", address);
            request.setAttribute("phone", phone);
            boolean checkValidation = true;
            AccountError accountError = new AccountError();
            AccountDAO dao = new AccountDAO();
            
            boolean checkDuplicate = dao.checkDuplicate(accountID);
            boolean checkDuplicateMail = dao.checkDuplicateMail(email);
            if (checkDuplicate) {
                accountError.setAccountIDError("Duplicate UserID!");
                checkValidation = false;
            }if (accountID.length() < 2 || accountID.length() > 10) {
                accountError.setAccountIDError("AccountID must be in [2, 10]");
                checkValidation = false;
            }if (fullName.length() < 5 || fullName.length() > 20) {
                accountError.setFullNameError("FullName must be in [5, 20]");
                checkValidation = false;
            }if (!password.equals(confirm)) {
                accountError.setConfirmError("Password must equals!");
                checkValidation = false;
            }if (!Pattern.matches("[23]", roleID)) {
                accountError.setRoleIDError("RoleID must be in [2,3]");
                checkValidation = false;
            } if (checkDuplicateMail) {
                accountError.setEmailError("Duplicate Email!");
                checkValidation = false;
            }
            if (!Pattern.matches("^[a-zA-Z][\\w-]+@fpt.edu.vn$", email)) {
                accountError.setEmailError("Email not correct!");
                checkValidation = false;
            }if (address.length() < 5 || address.length() > 40) {
                accountError.setAddressError("Address must be in [5, 40]");
                checkValidation = false;
            }if (!Pattern.matches("\\d{10,12}", phone)) {
                accountError.setPhoneError("Phone must be number and in [10, 12]");
                checkValidation = false;
            }
            
            if(roleID.equals("3")){
                String[] splits = email.split("@");
            String checkemail = splits[0];
            String AccountID = checkemail.substring(Math.max(0, checkemail.length() - 8));
            if (!accountID.equals(AccountID)) {
                accountError.setAccountIDError("Student ID and email do not match");
                checkValidation = false;
            }
            }
            if (checkValidation) {
                AccountDTO account = new  AccountDTO(accountID, fullName, password, Integer.parseInt(roleID), email, address, phone, true);
                boolean checkCreate = dao.create(account);
                if (checkCreate) {
                    url = SUCCESS;
                }
            }else {
                    request.setAttribute("ACCOUNT_ERROR", accountError);
                }

        } catch (Exception e) {
            log("Error at AddAccountController: " + e.toString());
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
