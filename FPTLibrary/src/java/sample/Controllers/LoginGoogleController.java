/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import sample.DTO.AccountDTO;
import java.io.IOException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import sample.DTO.GoogleDTO;
import sample.Utils.Constants;

/**
 *
 * @author Admin
 */
@WebServlet(name = "LoginGoogleController", urlPatterns = {"/LoginGoogleController"})
public class LoginGoogleController extends HttpServlet {

    private static final String ERROR = "login.jsp";
    private static final String SUCCESS = "HomeController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String code = request.getParameter("code");
            String accessToken = getToken(code);
            GoogleDTO userGoogle = getUserInfo(accessToken);
            String accID = userGoogle.getEmail();
            String[] splits = accID.split("@");
            String checkemail = splits[1];

            if (checkemail.equals("fpt.edu.vn")) {

                String ID = splits[0];
                String AccountID = ID.substring(Math.max(0, ID.length() - 8));
                String email = userGoogle.getEmail();
                AccountDAO dao = new AccountDAO();
                AccountDTO loginAccount = dao.checkLoginGG(email);
                if (loginAccount == null) {
                    AccountDTO account = new AccountDTO(AccountID, "", "", 3, email, "", "", true);
                    boolean checkCreate = dao.create(account);
                    if (checkCreate) {
                        HttpSession session = request.getSession();
                        session.setAttribute("LOGIN_ACCOUNT", account);
                        url = "LoadAccountController?accountID=" + AccountID;
                        request.setAttribute("message", "Please complete all information!");
                    }
                } else {
                    boolean status = loginAccount.getStatus();
                    if (status) {
                        HttpSession session = request.getSession();
                        session.setAttribute("LOGIN_ACCOUNT", loginAccount);
                        url = SUCCESS;
                    } else {
                        request.setAttribute("ERROR", "Account have been deleted!!!");
                    }
                }
            } else{
                request.setAttribute("messageerror", "You must be use email @fpt.edu.vn");
            }

        } catch (Exception e) {
            log("Error at LoginController: " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    public static String getToken(final String code) throws ClientProtocolException, IOException {
        String response = Request.Post(Constants.GOOGLE_LINK_GET_TOKEN)
                .bodyForm(Form.form().add("client_id", Constants.GOOGLE_CLIENT_ID)
                        .add("client_secret", Constants.GOOGLE_CLIENT_SECRET)
                        .add("redirect_uri", Constants.GOOGLE_REDIRECT_URI).add("code", code)
                        .add("grant_type", Constants.GOOGLE_GRANT_TYPE).build())
                .execute().returnContent().asString();
        JsonObject jobj = new Gson().fromJson(response, JsonObject.class);
        String accessToken = jobj.get("access_token").toString().replaceAll("\"", "");
        return accessToken;
    }

    public static GoogleDTO getUserInfo(final String accessToken) throws ClientProtocolException, IOException {
        String link = Constants.GOOGLE_LINK_GET_USER_INFO + accessToken;
        String response = Request.Get(link).execute().returnContent().asString();
        GoogleDTO googlePojo = new Gson().fromJson(response, GoogleDTO.class);
        System.out.println(googlePojo);
        return googlePojo;
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
