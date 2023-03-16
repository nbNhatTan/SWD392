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
import sample.DTO.PackageDTO;

/**
 *
 * @author NhatTan
 */
@WebServlet(name = "AddBookController", urlPatterns = {"/AddBookController"})
public class AddBookController extends HttpServlet {

    private static final String ERROR = "addBook.jsp";
    private static final String SUCCESS = "LoadListBookController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String bookID = request.getParameter("bookID"); if(bookID==null){ bookID="" ;}
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
            String packageName = request.getParameter("packageName");
            String price = request.getParameter("price");

            boolean checkValidation = true;
            BookError bookError = new BookError();
            BookDAO dao = new BookDAO();

//            boolean checkDuplicate = dao.checkDuplicate(accountID);
//            if (checkDuplicate) {
//                accountError.setAccountIDError("Duplicate UserID!");
//                checkValidation = false;
//            }
            if (bookID.length() < 2 || bookID.length() > 10) {
                bookError.setBookID("Automatic Created!");
                
            }
            if (bookName.length() < 2 || bookName.length() > 100) {
                bookError.setBookNameError("Book Name must be in [2, 100]");
                checkValidation = false;
            }
            if (!Pattern.matches("\\d{1,}", quantity)) {
                bookError.setQuantityError("quantity must be number!");
                checkValidation = false;
            }
            if (bookshelf.length() < 2 || bookshelf.length() > 100) {
                bookError.setBookshelfError("Bookshelf must be in [2, 100]");
                checkValidation = false;
            }
            if (language.length() < 2 || language.length() > 20) {
                bookError.setLanguageError("Language must be in [2, 20]");
                checkValidation = false;
            }
            if (author.length() < 2 || author.length() > 50) {
                bookError.setAuthorError("Author must be in [2, 50]");
                checkValidation = false;
            }
            if (publisher.length() < 2 || publisher.length() > 50) {
                bookError.setPublisherError("Publisher must be in [2, 50]");
                checkValidation = false;
            }
            if (description.length() < 2 || description.length() > 500) {
                bookError.setDescriptionError("Description must be in [2, 500]");
                checkValidation = false;
            }
            if (DDC.length() < 2 || DDC.length() > 20) {
                bookError.setDDCError("DDC must be in [2, 20]");
                checkValidation = false;
            }
            if (packageName.length() < 2 || packageName.length() > 20) {
                bookError.setPackageNameError("Package Name must be in [2, 20]");
                checkValidation = false;
            }
            if (!Pattern.matches("\\d{4}", publishYear)) {
                bookError.setPublishYearError("Publish Year must be number and 4 characters");
                checkValidation = false;
            }
            if (!Pattern.matches("(\\d{2,})00", price)) {
                bookError.setPriceError("Price must be number and is a multiple of 100");
                checkValidation = false;
            }
            long millis = System.currentTimeMillis();
            java.sql.Date importDate = new java.sql.Date(millis);
            BookDTO book = new BookDTO(bookID, bookName, Integer.parseInt(quantity), bookshelf,  description, DDC,language,  author, publisher, publishYear, image);
            PackageDTO Package = new PackageDTO(packageName, Integer.parseInt(price), importDate);
            if (checkValidation) {
                if (bookID.equals("")) {
                    bookID = dao.createBookID(bookName, author, publishYear, DDC, publisher);
                    book.setBookID(bookID);
                    request.setAttribute("message", "BookID will be: '" + bookID + "'. Add again to confirm.");
                } else {
                    int packageID = dao.createPackage(Package);
                    if (packageID != 0) {

                        if (dao.createBook(book)) {
                            int check = dao.insertBookItem(bookID, packageID);
                            if (check == Integer.parseInt(quantity)) {
                                url = SUCCESS;
                                request.setAttribute("message", "Add new book");
                                return;
                            }
                        }

                    }
                }
            }
            request.setAttribute("BOOK_ERROR", bookError);
            request.setAttribute("BOOK", book);
            request.setAttribute("PACKAGE", Package);

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
