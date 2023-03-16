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
import sample.DAO.BookDAO;
import sample.DTO.BookDTO;
import sample.DTO.BookError;

/**
 *
 * @author NhatTan
 */
@WebServlet(name = "EditBookController", urlPatterns = {"/EditBookController"})
public class EditBookController extends HttpServlet {

    private static final String ERROR = "editBook.jsp";
    private static final String SUCCESS = "LoadListBookController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String bookID = request.getParameter("bookID");
            String bookName = request.getParameter("bookName");
            String quantity = request.getParameter("quantity");
            String bookshelf = request.getParameter("bookshelf");
            String language = request.getParameter("language");
            String author = request.getParameter("author");
            String publisher = request.getParameter("publisher");
            String description = request.getParameter("description");
            String DDC = request.getParameter("DDC");
            String publishYear = request.getParameter("publishYear");
            String image = request.getParameter("image");

            boolean checkValidation = true;
            BookError bookError = new BookError();
            BookDAO dao = new BookDAO();

            if (!Pattern.matches("\\d{1,}", quantity)) {
                bookError.setQuantityError("quantity must be number!");
                checkValidation = false;
            }
            if (bookshelf.length() < 2 || bookshelf.length() > 100) {
                bookError.setBookshelfError("Bookshelf must be in [2, 100]");
                checkValidation = false;
            }
            if (description.length() < 2 || description.length() > 500) {
                bookError.setDescriptionError("Description must be in [2, 500]");
                checkValidation = false;
            }

            BookDTO book = new BookDTO(bookName, Integer.parseInt(quantity), bookshelf, description, DDC, language, author, publisher, publishYear, image);
            book.setBookID(bookID);
            if (checkValidation) {
                boolean check = dao.editBook(book);
                if (check) {
                    url = SUCCESS;
                    request.setAttribute("message", "Edit book");
                }
            } else {
                request.setAttribute("BOOK_ERROR", bookError);
                request.setAttribute("BOOK", book);
            }

        } catch (Exception e) {
            log("Error at CreateController: " + e.toString());
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
