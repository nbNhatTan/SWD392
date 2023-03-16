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
import sample.DAO.NewsDAO;
import sample.DTO.AccountDTO;
import sample.DTO.NewsDTO;

/**
 *
 * @author admin
 */
@WebServlet(name = "LoadNewsController", urlPatterns = {"/LoadNewsController"})
public class LoadNewsController extends HttpServlet {
    private static final String STAFF = "editNews.jsp";
    private static final String NORMAL = "newsInformation.jsp";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = NORMAL;
        try {
            
            NewsDAO dao = new NewsDAO();
            int newsID = Integer.parseInt(request.getParameter("newsID"));

            NewsDTO news = dao.getNewsInformation(newsID);
            if (news != null) {
                request.setAttribute("news", news);
            } else {
                request.setAttribute("message", "No result!");
            }
            HttpSession session = request.getSession();
            AccountDTO acc = (AccountDTO) session.getAttribute("LOGIN_ACCOUNT");
            if(acc.getRoleID()==2){
                url=STAFF;
            }
        } catch (Exception e) {
            log("Error at LoadNewsController: " + e.toString());
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
