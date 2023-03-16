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
import sample.DAO.BookDAO;
import sample.DTO.BookDTO;
import sample.DTO.CategoryDTO;
import sample.DTO.Paging;

/**
 *
 * @author NhatTan
 */
@WebServlet(name = "AdvanceSearchController", urlPatterns = {"/AdvanceSearchController"})
public class AdvanceSearchController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String bBookName = request.getParameter("bookName");
            String bAuthor = request.getParameter("author");
            String bPublisher = request.getParameter("publisher");
            String bLanguage = request.getParameter("language");
            String categoryId = request.getParameter("categoryId");
            request.setAttribute("bookName", bBookName);
            request.setAttribute("author", bAuthor);
            request.setAttribute("publisher", bPublisher);
            request.setAttribute("language", bLanguage);
            request.setAttribute("categoryId", categoryId);

            int currentBook = 0;
            int searchLimit = 10;
            int currentPage = 1;

            BookDAO dao = new BookDAO();
            List<CategoryDTO> listCategory = dao.getAllBookTag();
            if (!listCategory.isEmpty()) {
                request.setAttribute("LIST_CATEGORY", listCategory);
            }

            String currentPage_txt = request.getParameter("currentPage");
            if (currentPage_txt != null) {
                currentPage = Integer.parseInt(currentPage_txt);
            }
            String searchLimit_txt = request.getParameter("searchLimit");
            if (searchLimit_txt != null) {
                searchLimit = Integer.parseInt(searchLimit_txt);
            }
            int totalPage;
            if (!(categoryId != null) || categoryId.equals("")) {
                totalPage = dao.countGetListBook_TotalPage(bBookName == null ? "" : bBookName, bAuthor == null ? "" : bAuthor, bPublisher == null ? "" : bPublisher, bLanguage == null ? "" : bLanguage, 0, searchLimit);
            } else {
                totalPage = dao.countGetListBook_TotalPage(bBookName == null ? "" : bBookName, bAuthor == null ? "" : bAuthor, bPublisher == null ? "" : bPublisher, bLanguage == null ? "" : bLanguage, Integer.parseInt(categoryId), searchLimit);
            }
            currentBook = searchLimit * currentPage - searchLimit;
            Paging page = new Paging(currentPage, totalPage);

            List<BookDTO> listBook;
            if (!(categoryId != null) || categoryId.equals("")) {
                listBook = dao.getListBook(bBookName == null ? "" : bBookName, bAuthor == null ? "" : bAuthor, bPublisher == null ? "" : bPublisher, bLanguage == null ? "" : bLanguage, 0, currentBook, searchLimit);
            } else {
                listBook = dao.getListBook(bBookName == null ? "" : bBookName, bAuthor == null ? "" : bAuthor, bPublisher == null ? "" : bPublisher, bLanguage == null ? "" : bLanguage, Integer.parseInt(categoryId), currentBook, searchLimit);
            }

            if (!listBook.isEmpty()) {
                request.setAttribute("ADVANCE_LIST_BOOK", listBook);
                request.setAttribute("ADVANCE_LIST_BOOK_PAGE", page);
            } else {
                request.setAttribute("message", "No result!");
            }
        } catch (Exception e) {
            log("Error at AdvanceSearchController: " + e.toString());
        } finally {
            request.getRequestDispatcher("advancedSearch.jsp").forward(request, response);
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
