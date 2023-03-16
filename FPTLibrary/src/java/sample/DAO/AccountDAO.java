/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sample.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import sample.DTO.AccountDTO;
import sample.Utils.DBUtils;
import sample.Utils.SecurityUtils;

/**
 *
 * @author NhatTan
 */
public class AccountDAO {

    private static final String LOGIN = "SELECT accountID, fullName, roleID, email, address, phone, status FROM tblAccounts WHERE accountID=? AND password=?";
    private static final String LOGINGG = "SELECT accountID, fullName, roleID, email, address, phone, status FROM tblAccounts WHERE email=?";
    private static final String SEARCH = "SELECT accountID, fullName, roleID, email, address, phone, status FROM tblAccounts WHERE fullName like ?";
    private static final String DELETE = "UPDATE tblAccounts SET status='false' WHERE accountID=?";
    private static final String DELETE1 = "DELETE tblAccounts WHERE accountID=?";

    private static final String UPDATE = "UPDATE tblAccounts SET fullName=?, roleID=?, email=?, address=?, phone=? WHERE accountID=?";
    private static final String UPDATE_PASS = "UPDATE tblAccounts SET fullName=?, password=?, roleID=?, email=?, address=?, phone=? WHERE accountID=?";
    private static final String CHECK_DUPLICATE = "SELECT accountID FROM tblAccounts WHERE accountID=?";
    private static final String CHECK_DUPLICATEMAIL = "SELECT email FROM tblAccounts WHERE email=?";
    private static final String CREATE = "INSERT INTO tblAccounts(accountID, fullName, password, roleID, email, address, phone, status) VALUES (?,?,?,?,?,?,?,?)";
    private static final String GET = "SELECT fullName, roleID, email, address, phone, status FROM tblAccounts WHERE accountID = ?";
    private static final String GETALL = "SELECT accountID, fullName, roleID, email, address, phone, status FROM tblAccounts";
    private static final String GETMAIL = "SELECT a.email FROM tblViolationTicket v JOIN tblBookingTicket b ON v.bookingTicketID = b.bookingTicketID JOIN tblAccounts a ON a.AccountID = b.userID WHERE v.violationTicketID=?";
    private static final String GETMAILBOROW = "SELECT a.email FROM tblBookingTicket b JOIN tblAccounts a ON b.userID = a.AccountID WHERE b.bookingTicketID=?";

    public AccountDTO checkLogin(String accountID, String password) throws SQLException {
        AccountDTO acc = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            String hashPassword = SecurityUtils.createHash(password, accountID);
            if (conn != null) {
                ptm = conn.prepareStatement(LOGIN);
                ptm.setString(1, accountID);
                ptm.setString(2, hashPassword);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    String fullName = rs.getString("fullName");
                    int roleID = rs.getInt("roleID");
                    String email = rs.getString("email");
                    String address = rs.getString("address");
                    String phone = rs.getString("phone");
                    Boolean status = rs.getBoolean("status");
                    acc = new AccountDTO(accountID, fullName, "", roleID, email, address, phone, status);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return acc;
    }

    public AccountDTO checkLoginGG(String email) throws SQLException {
        AccountDTO acc = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            
            if (conn != null) {
                ptm = conn.prepareStatement(LOGINGG);
                ptm.setString(1, email);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    String accountID = rs.getString("accountID");
                    String fullName = rs.getString("fullName");
                    int roleID = rs.getInt("roleID");
                    String address = rs.getString("address");
                    String phone = rs.getString("phone");
                    Boolean status = rs.getBoolean("status");
                    acc = new AccountDTO(accountID, fullName, "", roleID, email, address, phone, status);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return acc;
    }

    public AccountDTO getAccountByID(String AccountID) throws SQLException {
        AccountDTO account;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET);
                ptm.setString(1, AccountID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String fullName = rs.getString("fullName");
                    int roleID = rs.getInt("roleID");
                    String email = rs.getString("email");
                    String address = rs.getString("address");
                    String phone = rs.getString("phone");
                    Boolean status = rs.getBoolean("status");
                    account = new AccountDTO(AccountID, fullName, "", roleID, email, address, phone, status);
                    return account;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return null;
    }

    public List<AccountDTO> getListAccount(String search) throws SQLException {
        List<AccountDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SEARCH);
                ptm.setString(1, "%" + search + "%");
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String accountID = rs.getString("accountID");
                    String fullName = rs.getString("fullName");
                    int roleID = rs.getInt("roleID");
                    String email = rs.getString("email");
                    String address = rs.getString("address");
                    String phone = rs.getString("phone");
                    Boolean status = rs.getBoolean("status");
                    list.add(new AccountDTO(accountID, fullName, "", roleID, email, address, phone, status));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }

    public List<AccountDTO> getAllAccount() throws SQLException {
        List<AccountDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GETALL);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String accountID = rs.getString("accountID");
                    String fullName = rs.getString("fullName");
                    int roleID = rs.getInt("roleID");
                    String email = rs.getString("email");
                    String address = rs.getString("address");
                    String phone = rs.getString("phone");
                    Boolean status = rs.getBoolean("status");
                    list.add(new AccountDTO(accountID, fullName, "", roleID, email, address, phone, status));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }

    public boolean deleteAccount(String accountID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DELETE);
                ptm.setString(1, accountID);
                check = ptm.executeUpdate() > 0 ? true : false;
            }
        } catch (Exception e) {
            e.toString();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public boolean update(AccountDTO acc) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                if (acc.getPassword().equals("***")) {
                    ptm = conn.prepareStatement(UPDATE);
                    ptm.setString(1, acc.getFullName());
                    ptm.setInt(2, acc.getRoleID());
                    ptm.setString(3, acc.getEmail());
                    ptm.setString(4, acc.getAddress());
                    ptm.setString(5, acc.getPhone());
                    ptm.setString(6, acc.getAccountID());
                } else {
                    String hashPassword = SecurityUtils.createHash(acc.getPassword(), acc.getAccountID());
                    ptm = conn.prepareStatement(UPDATE_PASS);
                    ptm.setString(1, acc.getFullName());
                    ptm.setString(2, hashPassword);
                    ptm.setInt(3, acc.getRoleID());
                    ptm.setString(4, acc.getEmail());
                    ptm.setString(5, acc.getAddress());
                    ptm.setString(6, acc.getPhone());
                    ptm.setString(7, acc.getAccountID());
                }
                check = ptm.executeUpdate() > 0 ? true : false;
            }
        } catch (Exception e) {
            e.toString();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public boolean checkDuplicate(String accountID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_DUPLICATE);
                ptm.setString(1, accountID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    check = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public boolean checkDuplicateMail(String email) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_DUPLICATEMAIL);
                ptm.setString(1, email);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    check = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public boolean create(AccountDTO acc) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            String hashPassword = SecurityUtils.createHash(acc.getPassword(), acc.getAccountID());
            if (conn != null) {
                ptm = conn.prepareStatement(CREATE);
                ptm.setString(1, acc.getAccountID());
                ptm.setString(2, acc.getFullName());
                ptm.setString(3, hashPassword);
                ptm.setInt(4, acc.getRoleID());
                ptm.setString(5, acc.getEmail());
                ptm.setString(6, acc.getAddress());
                ptm.setString(7, acc.getPhone());
                ptm.setBoolean(8, acc.getStatus());
                check = ptm.executeUpdate() > 0 ? true : false;
            }
        } catch (Exception e) {
            e.toString();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public String GetMail(String ViolationTicketID) throws SQLException {
        String email;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GETMAIL);
                ptm.setString(1, ViolationTicketID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    email = rs.getString("email");
                    return email;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return null;
    }

    public String GetMailBorrow(String bookingTicketID) throws SQLException {
        String email;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GETMAILBOROW);
                ptm.setString(1, bookingTicketID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    email = rs.getString("email");
                    return email;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return null;
    }

    public static void SendMail(String to, String sub,
            String msg, final String user, final String pass) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, pass);
            }
        });
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(sub);
            message.setContent(msg, "text/html");
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
