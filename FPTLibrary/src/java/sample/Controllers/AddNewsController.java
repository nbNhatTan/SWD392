/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.Controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.DAO.NewsDAO;
import sample.DTO.AccountDTO;
import sample.DTO.NewsDTO;

/**
 *
 * @author anhkhoa
 */
@WebServlet(name = "AddNewsController", urlPatterns = {"/AddNewsController"})
public class AddNewsController extends HttpServlet {

    private static final String ERROR = "addnews.jsp";
    private static final String SUCCESS = "HomeController";
    private static final String RETURN = "login.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;

        try {
            HttpSession session = request.getSession();
            AccountDTO loginAccount = (AccountDTO) session.getAttribute("LOGIN_ACCOUNT");
            if (loginAccount != null) {
                
                
           NewsDAO dao = new NewsDAO();
            
                String staffID = loginAccount.getAccountID();
                String writerName = request.getParameter("writerName");
                String title = request.getParameter("title");
                String head = request.getParameter("head");
                String body = request.getParameter("body");
                Date uploadDate = Date.valueOf(request.getParameter("uploadDate"));


                NewsDTO news = new NewsDTO( writerName, title, head, body, staffID, uploadDate);
                boolean checkAdd = dao.addNews(news);

                if (checkAdd) {
                    url = SUCCESS;
                    
                }
            } else {
                url = RETURN;
            }
        } catch (Exception e) {
            log("ERROR at AddNewsController:" + e.toString());
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
