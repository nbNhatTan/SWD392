/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package sample.Controllers;

import java.io.IOException;

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
 * @author admin
 */
@WebServlet(name = "UpdateNewsController", urlPatterns = {"/UpdateNewsController"})
public class UpdateNewsController extends HttpServlet {
    private static final String ERROR = "addnews.jsp";
    private static final String SUCCESS = "NewsController";
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            AccountDTO loginAccount = (AccountDTO) session.getAttribute("LOGIN_ACCOUNT");
            if (loginAccount != null) {
                
                
                NewsDAO dao = new NewsDAO();
                int newsID = Integer.parseInt(request.getParameter("newsID"));
                String staffID = loginAccount.getAccountID();
                String writerName = request.getParameter("writerName");
                String title = request.getParameter("title");
                String head = request.getParameter("head");
                String body = request.getParameter("body");
                Date uploadDate = Date.valueOf(request.getParameter("uploadDate"));


                NewsDTO news = new NewsDTO(newsID, writerName, title, head, body, staffID, uploadDate);
                boolean checkUpdate = dao.updateNews(news);

                if (checkUpdate) {
                    url = SUCCESS;
                    
                }
            } else {
                url = ERROR;
            }
        } catch (Exception e) {
            log("ERROR at UpdateNewsController:" + e.toString());
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
