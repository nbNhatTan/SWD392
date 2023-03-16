/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package sample.Controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.DAO.NewsDAO;
import sample.DTO.AccountDTO;
import sample.DTO.NewsDTO;
import sample.DTO.Paging;

/**
 *
 * @author admin
 */
@WebServlet(name = "NewsController", urlPatterns = {"/NewsController"})
public class NewsController extends HttpServlet {
    private static final String STAFF = "manageNews.jsp";
    private static final String NORMAL = "newsList.jsp";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = NORMAL;
        try {
            
            int currentNews = 1;
            int searchLimit = 20;
            int currentPage = 1;
            NewsDAO dao = new NewsDAO();
            String currentPage_txt = request.getParameter("currentPage");
            if (currentPage_txt != null) {
                currentPage = Integer.parseInt(currentPage_txt);
            }
            String searchLimit_txt = request.getParameter("searchLimit");
            if (searchLimit_txt != null) {
                searchLimit = Integer.parseInt(searchLimit_txt);
            }
            int totalPage = dao.getTotalPagesNews(searchLimit);
            
            currentNews = searchLimit * currentPage - searchLimit;
            Paging page = new Paging(currentPage, totalPage);

            List<NewsDTO> listNews = dao.getListNews_withPage(currentNews, searchLimit);
            
            if (!listNews.isEmpty()) {
                request.setAttribute("listNews", listNews);
                request.setAttribute("listNews_Page", page);
            } else {
                request.setAttribute("message", "No result!");
            }
            HttpSession session = request.getSession();
            AccountDTO acc = (AccountDTO) session.getAttribute("LOGIN_ACCOUNT");
            if(acc.getRoleID()==2){
                url=STAFF;
            }
        } catch (Exception e) {
            log("Error at NewsController: " + e.toString());
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
