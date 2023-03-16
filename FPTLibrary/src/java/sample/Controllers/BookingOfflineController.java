/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package sample.Controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.DAO.AccountDAO;
import sample.DAO.BookDAO;
import sample.DAO.TicketDAO;
import sample.DTO.AccountDTO;
import sample.DTO.BookDTO;
import sample.DTO.BookingTicketDTO;

/**
 *
 * @author NhatTan
 */
@WebServlet(name = "BookingOfflineController", urlPatterns = {"/BookingOfflineController"})
public class BookingOfflineController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = "bookingOffline.jsp";
        try {
            String userID = request.getParameter("userID");
            String bookItemID = request.getParameter("bookID");

            HttpSession session = request.getSession();
            AccountDTO loginAccount = (AccountDTO) session.getAttribute("LOGIN_ACCOUNT");
            if (loginAccount != null) {
                long millis = System.currentTimeMillis();
                java.sql.Date borrowDate = new java.sql.Date(millis);

                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                df.setLenient(false);
                java.util.Date dat = df.parse("2022-6-14");
                java.util.Date dat1 = df.parse("2022-10-14");
                long date = dat.getTime();
                long date1 = dat1.getTime();
                long fourMonth = date1 - date;

                java.sql.Date expiredDate = new java.sql.Date(millis + fourMonth);
                BookingTicketDTO ticket = new BookingTicketDTO(userID, bookItemID, borrowDate, expiredDate, null, "Borrowing");
                TicketDAO dao = new TicketDAO();
                int bookingTicketID = dao.createBookingTicketStaff(ticket);
                if (bookingTicketID != 0) {
                    dao.createStaffTicket(loginAccount.getAccountID(), bookingTicketID);
                }

                request.setAttribute("message", "Borrow book");
            } else {
                url = "login.jsp";
                request.setAttribute("warning", "You need to login to use this funcion!");
            }
        } catch (Exception e) {
            log("Error at BookingOfflineController: " + e.toString());
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
