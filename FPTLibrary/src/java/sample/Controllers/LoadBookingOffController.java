/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package sample.Controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.DAO.AccountDAO;
import sample.DAO.BookDAO;
import sample.DTO.AccountDTO;
import sample.DTO.BookDTO;

/**
 *
 * @author NhatTan
 */
@WebServlet(name = "LoadBookingOffController", urlPatterns = {"/LoadBookingOffController"})
public class LoadBookingOffController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = "bookingOffline.jsp";
        try {
            HttpSession session = request.getSession();
            AccountDTO loginAccount = (AccountDTO) session.getAttribute("LOGIN_ACCOUNT");
            if (loginAccount != null) {
                String userID = request.getParameter("userID");
                String bookItemID = request.getParameter("bookID");
                request.setAttribute("userID", userID);
                request.setAttribute("bookID", bookItemID);

                AccountDAO accDAO = new AccountDAO();
                AccountDTO acc = accDAO.getAccountByID(userID);
                if (acc != null) {
                    request.setAttribute("student", acc);
                } else {
                    request.setAttribute("userIdError", "StudentID is not exist");
                }
                BookDAO bookDAO = new BookDAO();
                BookDTO book = bookDAO.getBookByBookItemID(bookItemID);
                if (book != null) {
                    request.setAttribute("book", book);
                } else {
                    request.setAttribute("bookIdError", "This book is not already");
                }
            } else {
                url = "login.jsp";
                request.setAttribute("warning", "You need to login to use this funcion!");
            }
        } catch (Exception e) {
            log("Error at LoadBookingOffController: " + e.toString());
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
