/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sample.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sample.DTO.AccountDTO;
import sample.DTO.BookingTicketDTO;
import sample.DTO.BorrowDTO;
import sample.DTO.ViolationTicketDTO;
import sample.DTO.WishListDTO;
import sample.Utils.DBUtils;

/**
 *
 * @author NhatTan
 */
public class TicketDAO {

//    private static final String GETLISTTICKET_USERID = "SELECT bookingTicketID, bookItemID, borrowDate, expiredDate, returnDate, borrowStatus FROM tblBookingTicket WHERE userID = ?";//user
//    private static final String GETLISTTICKET_STATUS = "SELECT bookingTicketID, bookItemID, borrowDate, expiredDate, returnDate FROM tblBookingTicket WHERE userID = ? AND borrowStatus = ?";
    private static final String GETLISTBOOKNAME_USERID = "SELECT b.[image], b.bookName, t.bookingTicketID, t.bookItemID, t.borrowDate, t.expiredDate, t.returnDate, t.borrowStatus FROM tblBook b JOIN tblBookItem i ON b.bookID = i.bookID JOIN tblBookingTicket t ON t.bookItemID = i.bookItemID WHERE t.userID = ?";//user
    private static final String GETLISTBOOKNAME_STATUS = "SELECT b.[image], b.bookName, t.bookingTicketID, t.userID, t.bookItemID, t.borrowDate, t.expiredDate, t.returnDate, t.borrowStatus \n"
            + "FROM tblBook b JOIN tblBookItem i ON b.bookID = i.bookID \n"
            + "JOIN tblBookingTicket t ON t.bookItemID = i.bookItemID\n"
            + "JOIN tblStaffTicket s ON t.bookingTicketID = s.ticketID\n"
            + "WHERE s.staffID like ? AND t.borrowStatus like ?";
    private static final String GETLISTBOOKTICKET_PENDING = "SELECT b.[image], b.bookName, t.bookingTicketID, t.userID, t.bookItemID, t.borrowDate, t.expiredDate, t.returnDate, t.borrowStatus \n"
            + "FROM tblBook b JOIN tblBookItem i ON b.bookID = i.bookID \n"
            + "JOIN tblBookingTicket t ON t.bookItemID = i.bookItemID \n"
            + "WHERE t.borrowStatus like 'Pending'";
    private static final String GETLISTVIOLATIONTICKET_STAFFID = "SELECT violationTicketID, bookingTicketID, createDate, description, ticketStatus FROM tblViolationTicket WHERE staffID like ?";//staff
    private static final String GETLISTVIOLATIONTICKET_STAFFID_STATUS = "SELECT violationTicketID, bookingTicketID, createDate, description, ticketStatus FROM tblViolationTicket WHERE staffID like ? AND ticketStatus = ?";//staff
    private static final String GETVIOLATIONTICKET_BOOKINGTICKETID = "SELECT violationTicketID, bookingTicketID, createDate, description, ticketStatus, staffID FROM tblViolationTicket WHERE bookingTicketID = ?";//user
    private static final String CREATE_BOOKINGTICKET = "INSERT INTO tblBookingTicket(userID, bookItemID, borrowDate, expiredDate, borrowStatus) VALUES (?,?,?,?,?)";
    private static final String CREATE_VIOLATIONTICKET = "INSERT INTO tblViolationTicket(bookingTicketID, createDate, description, ticketStatus, staffID) VALUES (?,?,?,?,?)";
    private static final String GETLISTBOOKNAME_STAFFID = "SELECT b.[image], b.bookName, t.bookingTicketID, t.userID, t.bookItemID, t.borrowDate, t.expiredDate, t.returnDate, t.borrowStatus \n"
            + "FROM tblBook b JOIN tblBookItem i ON b.bookID = i.bookID \n"
            + "JOIN tblBookingTicket t ON t.bookItemID = i.bookItemID\n"
            + "JOIN tblStaffTicket s ON t.bookingTicketID = s.ticketID\n"
            + "WHERE s.staffID like ?";
    private static final String GETBORROWDETAIL = "SELECT b.[image], b.bookName, t.userID, t.bookItemID, t.borrowDate, t.expiredDate, t.returnDate, t.borrowStatus \n"
            + "FROM tblBook b JOIN tblBookItem i ON b.bookID = i.bookID \n"
            + "JOIN tblBookingTicket t ON t.bookItemID = i.bookItemID\n"
            + "WHERE t.bookingTicketID = ?";
    private static final String GETSTAFFBORROWDETAIL = "SELECT staffID FROM tblStaffTicket WHERE ticketID = ?";
    private static final String GETVIOLATIONDETAIL = "SELECT violationTicketID, staffID, createDate, description, ticketStatus FROM tblViolationTicket WHERE bookingTicketID = ?";
    private static final String RETURNBOOK = "UPDATE tblBookingTicket SET returnDate=?, borrowStatus=? WHERE bookingTicketID=?";
    private static final String UPDATEBOOKINGTICKET_STATUS = "UPDATE tblBookingTicket SET borrowStatus=? WHERE bookingTicketID=?";
    private static final String UPDATEBOOKSTATUS = "UPDATE tblBookItem SET bookStatus=? WHERE bookItemID = ?";
    private static final String UPDATEBOOKSTATUS_TICKETID = "UPDATE i SET i.bookStatus=? FROM tblBookItem i INNER JOIN tblBookingTicket t ON i.bookItemID =t.bookItemID WHERE bookingTicketID = ?";
    private static final String UPDATEVIOLATIONSTATUS = "UPDATE tblViolationTicket SET ticketStatus=? WHERE violationTicketID=?";
    private static final String GETBOOKITEMID = "SELECT TOP 1 bookItemID FROM tblBookItem WHERE bookID = ? AND bookStatus = 'On bookshelf' ORDER BY bookItemID ASC";
    private static final String CREATESTAFFTICKET = "INSERT INTO tblStaffTicket(staffID, ticketID) VALUES (?,?)";
    private static final String GETBOOKINGTICKETID_VIOLATIONID = "SELECT bookingTicketID FROM tblViolationTicket WHERE violationTicketID=?";
    private static final String CREATEWISHLIST = "INSERT INTO tblWishList(bookID, userID) VALUES (?,?)";
    private static final String WISHLIST = "DELETE FROM tblWishList WHERE EXISTS \n"
            + "(SELECT * FROM tblWishList w \n"
            + "JOIN tblBookItem i ON w.bookID = i.bookID \n"
            + "JOIN tblBookingTicket t ON i.bookItemID = t.bookItemID \n"
            + "WHERE t.bookingTicketID = ?) ";
    private static final String GETEMAIL_WISHLIST = "SELECT email  FROM tblAccounts a \n"
            + "JOIN tblWishList w ON a.AccountID = w.userID \n"
            + "JOIN tblBookItem i ON w.bookID = i.bookID \n"
            + "JOIN tblBookingTicket t ON i.bookItemID = t.bookItemID \n"
            + "WHERE t.bookingTicketID = ? ";

//    public List<BookingTicketDTO> GetListTicket_UserID(String userID) throws SQLException {
//        List<BookingTicketDTO> list = new ArrayList<>();
//        Connection conn = null;
//        PreparedStatement ptm = null;
//        ResultSet rs = null;
//        try {
//            conn = DBUtils.getConnection();
//            if (conn != null) {
//                ptm = conn.prepareStatement(GETLISTTICKET_USERID);
//                ptm.setString(1, userID);
//                rs = ptm.executeQuery();
//                while (rs.next()) {
//                    int bookingTicketID = rs.getInt("bookingTicketID");
//                    String bookItemID = rs.getString("bookItemID");
//                    Date borrowDate = rs.getDate("borrowDate");
//                    Date expiredDate = rs.getDate("expiredDate");
//                    Date returnDate = rs.getDate("returnDate");
//                    String borrowStatus = rs.getString("borrowStatus");
//                    BookingTicketDTO bookingTicket = new BookingTicketDTO(userID, bookItemID, borrowDate, expiredDate, returnDate, borrowStatus);
//                    bookingTicket.setBookingTicketID(bookingTicketID);
//                    list.add(bookingTicket);
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (rs != null) {
//                rs.close();
//            }
//            if (ptm != null) {
//                ptm.close();
//            }
//            if (conn != null) {
//                conn.close();
//            }
//        }
//        return list;
//    }
    public List<BorrowDTO> GetListTicket_UserID(String userID) throws SQLException {
        List<BorrowDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GETLISTBOOKNAME_USERID);
                ptm.setString(1, userID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String image = rs.getString("image");
                    String bookName = rs.getString("bookName");
                    int bookingTicketID = rs.getInt("bookingTicketID");
                    AccountDAO accDAO = new AccountDAO();
                    AccountDTO user = accDAO.getAccountByID(userID);
                    String bookItemID = rs.getString("bookItemID");
                    Date borrowDate = rs.getDate("borrowDate");
                    Date expiredDate = rs.getDate("expiredDate");
                    Date returnDate = rs.getDate("returnDate");
                    String borrowStatus = rs.getString("borrowStatus");
                    long now = System.currentTimeMillis();
                    long expire = expiredDate.getTime();
                    if (now > expire && !borrowStatus.equals("HandleViolation")) {
                        borrowStatus = "Expired";
                    }
                    list.add(new BorrowDTO(image, bookName, bookingTicketID, user, bookItemID, borrowDate, expiredDate, returnDate, borrowStatus));
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

    public List<BorrowDTO> GetListTicket_StaffID(String staffID) throws SQLException {
        List<BorrowDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GETLISTBOOKNAME_STAFFID);
                ptm.setString(1, staffID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String image = rs.getString("image");
                    String bookName = rs.getString("bookName");
                    int bookingTicketID = rs.getInt("bookingTicketID");
                    AccountDAO accDAO = new AccountDAO();
                    AccountDTO user = accDAO.getAccountByID(rs.getString("userID"));
                    String bookItemID = rs.getString("bookItemID");
                    Date borrowDate = rs.getDate("borrowDate");
                    Date expiredDate = rs.getDate("expiredDate");
                    Date returnDate = rs.getDate("returnDate");
                    String borrowStatus = rs.getString("borrowStatus");
                    long now = System.currentTimeMillis();
                    long expire = expiredDate.getTime();
                    if (now > expire && !borrowStatus.equals("HandleViolation")) {
                        borrowStatus = "Expired";
                    }
                    list.add(new BorrowDTO(image, bookName, bookingTicketID, user, bookItemID, borrowDate, expiredDate, returnDate, borrowStatus));
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
    //    public List<BookingTicketDTO> GetListTicket_Status(String userID, String status) throws SQLException {
    //        List<BookingTicketDTO> list = new ArrayList<>();
    //        Connection conn = null;
    //        PreparedStatement ptm = null;
    //        ResultSet rs = null;
    //        try {
    //            conn = DBUtils.getConnection();
    //            if (conn != null) {
    //                ptm = conn.prepareStatement(GETLISTTICKET_STATUS);
    //                ptm.setString(1, userID);
    //                ptm.setString(2, status);
    //                rs = ptm.executeQuery();
    //                while (rs.next()) {
    //                    int bookingTicketID = rs.getInt("bookingTicketID");
    //                    String bookItemID = rs.getString("bookItemID");
    //                    Date borrowDate = rs.getDate("borrowDate");
    //                    Date expiredDate = rs.getDate("expiredDate");
    //                    Date returnDate = rs.getDate("returnDate");
    //                    BookingTicketDTO bookingTicket = new BookingTicketDTO(userID, bookItemID, borrowDate, expiredDate, returnDate, status);
    //                    bookingTicket.setBookingTicketID(bookingTicketID);
    //                    list.add(bookingTicket);
    //                }
    //            }
    //        } catch (Exception e) {
    //            e.printStackTrace();
    //        } finally {
    //            if (rs != null) {
    //                rs.close();
    //            }
    //            if (ptm != null) {
    //                ptm.close();
    //            }
    //            if (conn != null) {
    //                conn.close();
    //            }
    //        }
    //        return list;
    //    }

    public List<BorrowDTO> GetListTicket_Status(String staffID, String status) throws SQLException {
        List<BorrowDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                if (staffID.equals("")) {
                    ptm = conn.prepareStatement(GETLISTBOOKTICKET_PENDING);
                } else {
                    ptm = conn.prepareStatement(GETLISTBOOKNAME_STATUS);
                    ptm.setString(1, staffID);
                    ptm.setString(2, status);
                }
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String image = rs.getString("image");
                    String bookName = rs.getString("bookName");
                    int bookingTicketID = rs.getInt("bookingTicketID");
                    AccountDAO accDAO = new AccountDAO();
                    AccountDTO user = accDAO.getAccountByID(rs.getString("userID"));
                    String bookItemID = rs.getString("bookItemID");
                    Date borrowDate = rs.getDate("borrowDate");
                    Date expiredDate = rs.getDate("expiredDate");
                    Date returnDate = rs.getDate("returnDate");
                    list.add(new BorrowDTO(image, bookName, bookingTicketID, user, bookItemID, borrowDate, expiredDate, returnDate, status));
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

    public List<ViolationTicketDTO> GetListViolationTicket_StaffID(String staffID, int status) throws SQLException {
        List<ViolationTicketDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                if (status == 2) {
                    ptm = conn.prepareStatement(GETLISTVIOLATIONTICKET_STAFFID);
                    ptm.setString(1, staffID);
                } else {
                    ptm = conn.prepareStatement(GETLISTVIOLATIONTICKET_STAFFID_STATUS);
                    ptm.setString(1, staffID);
                    ptm.setInt(2, status);
                }
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int violationTicketID = rs.getInt("violationTicketID");
                    int bookingTicketID = rs.getInt("bookingTicketID");
                    String description = rs.getString("description");
                    boolean ticketStatus = rs.getBoolean("ticketStatus");
                    Date createDate = rs.getDate("createDate");
                    ViolationTicketDTO violation = new ViolationTicketDTO(bookingTicketID, createDate, description, ticketStatus, staffID);
                    violation.setViolationTicketID(violationTicketID);
                    list.add(violation);
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

    public ViolationTicketDTO GetViolationTicket_BookingTicketID(int bookingTicketID) throws SQLException {
        ViolationTicketDTO violation = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GETVIOLATIONTICKET_BOOKINGTICKETID);
                ptm.setInt(1, bookingTicketID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int violationTicketID = rs.getInt("violationTicketID");
                    String description = rs.getString("description");
                    boolean ticketStatus = rs.getBoolean("ticketStatus");
                    String staffID = rs.getString("staffID");
                    Date createDate = rs.getDate("createDate");
                    violation = new ViolationTicketDTO(bookingTicketID, createDate, description, ticketStatus, staffID);
                    violation.setViolationTicketID(violationTicketID);
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
        return violation;
    }

    public int createBookingTicket(BookingTicketDTO ticket) throws SQLException {
        int id = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CREATE_BOOKINGTICKET);
                ptm.setString(1, ticket.getUserID());
                ptm.setString(2, ticket.getBookItemID());
                ptm.setDate(3, ticket.getBorrowDate());
                ptm.setDate(4, ticket.getExpiredDate());
                ptm.setString(5, ticket.getBorrowStatus());
                ptm.execute();
//                rs = ptm.getGeneratedKeys();
//                while (rs.next()) {
//                    id = rs.getInt(1);
//                }
                ptm = conn.prepareStatement(UPDATEBOOKSTATUS);
                ptm.setString(1, "Pending");
                ptm.setString(2, ticket.getBookItemID());
                ptm.execute();
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
        return id;
    }

    public int createBookingTicketStaff(BookingTicketDTO ticket) throws SQLException {
        int id = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CREATE_BOOKINGTICKET);
                ptm.setString(1, ticket.getUserID());
                ptm.setString(2, ticket.getBookItemID());
                ptm.setDate(3, ticket.getBorrowDate());
                ptm.setDate(4, ticket.getExpiredDate());
                ptm.setString(5, ticket.getBorrowStatus());
                ptm.execute();
//                rs = ptm.getGeneratedKeys();
//                while (rs.next()) {
//                    id = rs.getInt(1);
//                }
                ptm = conn.prepareStatement(UPDATEBOOKSTATUS);
                ptm.setString(1, "Borrowing");
                ptm.setString(2, ticket.getBookItemID());
                ptm.execute();
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
        return id;
    }

    public int createViolationTicket(ViolationTicketDTO ticket) throws SQLException {
        int id = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATEBOOKINGTICKET_STATUS);
                ptm.setString(1, "HandleViolation");
                ptm.setInt(2, ticket.getBookingTicketID());
                ptm.executeUpdate();
                ptm = conn.prepareStatement(CREATE_VIOLATIONTICKET);
                ptm.setInt(1, ticket.getBookingTicketID());
                ptm.setDate(2, ticket.getCreateDate());
                ptm.setString(3, ticket.getDescription());
                ptm.setBoolean(4, ticket.getTicketStatus());
                ptm.setString(5, ticket.getStaffID());
                ptm.execute();
                rs = ptm.getGeneratedKeys();
                while (rs.next()) {
                    id = rs.getInt(1);
                }
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
        return id;
    }

    public boolean returnBook(Date returnDate, int bookingTicketID) throws SQLException {
        boolean check = false;
        boolean check1 = false;
        boolean check2 = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(RETURNBOOK);
                ptm.setDate(1, returnDate);
                ptm.setString(2, "Returned");
                ptm.setInt(3, bookingTicketID);
                check1 = ptm.executeUpdate() > 0;
                ptm = conn.prepareStatement(UPDATEBOOKSTATUS_TICKETID);
                ptm.setString(1, "On Bookshelf");
                ptm.setInt(2, bookingTicketID);
                check2 = ptm.executeUpdate() > 0;
            }
            check = check1 && check2;
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

    public boolean confirmBookingTicket(int bookingTicketID) throws SQLException {
        boolean check = false;
        boolean check1 = false;
        boolean check2 = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATEBOOKINGTICKET_STATUS);
                ptm.setString(1, "Approved");
                ptm.setInt(2, bookingTicketID);
                check1 = ptm.executeUpdate() > 0;
                ptm = conn.prepareStatement(UPDATEBOOKSTATUS_TICKETID);
                ptm.setString(1, "Approved");
                ptm.setInt(2, bookingTicketID);
                check2 = ptm.executeUpdate() > 0;
            }
            check = check1 && check2;
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

    public boolean updateViolationStatus(int ticketStatus, int violationTicketID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATEVIOLATIONSTATUS);
                ptm.setInt(1, ticketStatus);
                ptm.setInt(2, violationTicketID);
                check = ptm.executeUpdate() > 0;
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

    public String GetBookItemID(String bookID) throws SQLException {
        String bookItemID = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GETBOOKITEMID);
                ptm.setString(1, bookID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    bookItemID = rs.getString("bookItemID");
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
        return bookItemID;
    }

    public boolean createStaffTicket(String staffID, int ticketID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CREATESTAFFTICKET);
                ptm.setString(1, staffID);
                ptm.setInt(2, ticketID);
                check = ptm.executeUpdate() > 0;
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

    public BorrowDTO getBorrowDetail(int bookingTicketID) throws SQLException {
        BorrowDTO borrow;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GETSTAFFBORROWDETAIL);
                ptm.setInt(1, bookingTicketID);
                rs = ptm.executeQuery();
                AccountDAO accDAO = new AccountDAO();
                AccountDTO staff = new AccountDTO();
                while (rs.next()) {
                    staff = accDAO.getAccountByID(rs.getString("staffID"));
                }
                ptm = conn.prepareStatement(GETBORROWDETAIL);
                ptm.setInt(1, bookingTicketID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String image = rs.getString("image");
                    String bookName = rs.getString("bookName");
                    AccountDTO user = accDAO.getAccountByID(rs.getString("userID"));
                    String bookItemID = rs.getString("bookItemID");
                    Date borrowDate = rs.getDate("borrowDate");
                    Date expiredDate = rs.getDate("expiredDate");
                    Date returnDate = rs.getDate("returnDate");
                    String borrowStatus = rs.getString("borrowStatus");
                    borrow = new BorrowDTO(image, bookName, bookingTicketID, user, bookItemID, borrowDate, expiredDate, returnDate, borrowStatus);
                    borrow.setStaffID(staff);
                    return borrow;
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

    public ViolationTicketDTO getViolationDetail(int bookingTicketID) throws SQLException {
        ViolationTicketDTO violation;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GETVIOLATIONDETAIL);
                ptm.setInt(1, bookingTicketID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int violationTicketID = rs.getInt("violationTicketID");
                    String staffID = rs.getString("staffID");
                    String description = rs.getString("description");
                    boolean ticketStatus = rs.getBoolean("ticketStatus");
                    Date createDate = rs.getDate("createDate");
                    violation = new ViolationTicketDTO(bookingTicketID, createDate, description, ticketStatus, staffID);
                    violation.setViolationTicketID(violationTicketID);
                    return violation;
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

    public Integer getBookingTicketID_ViolationID(int violationID) throws SQLException {
        ViolationTicketDTO violation;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GETBOOKINGTICKETID_VIOLATIONID);
                ptm.setInt(1, violationID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int bookingTicketID = rs.getInt("bookingTicketID");
                    return bookingTicketID;
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

    public int createWishList(WishListDTO wish) throws SQLException {
        int check = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CREATEWISHLIST);
                ptm.setString(1, wish.getBookID());
                ptm.setString(2, wish.getUserID());
                check = ptm.executeUpdate();
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

    public List<String> getWishList(String bookingTicketID) throws SQLException {
        List<String> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GETEMAIL_WISHLIST);
                ptm.setString(1, bookingTicketID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    list.add(rs.getString("email"));
                }
                ptm = conn.prepareStatement(WISHLIST);
                ptm.setString(1, bookingTicketID);
                ptm.executeUpdate();
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
        return list;
    }

    public boolean confirmUserRecivedBook(String bookingTicketID, String status) throws SQLException {
        boolean check = false;
        boolean check1 = false;
        boolean check2 = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATEBOOKINGTICKET_STATUS);
                ptm.setString(1, status);
                ptm.setString(2, bookingTicketID);
                check1 = ptm.executeUpdate() > 0;
                ptm = conn.prepareStatement(UPDATEBOOKSTATUS_TICKETID);
                ptm.setString(1, status);
                ptm.setString(2, bookingTicketID);
                check2 = ptm.executeUpdate() > 0;
            }
            check = check1 && check2;
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

    private static final String GETLISTBOOKTICKET_APPROVED = "SELECT b.[image], b.bookName, t.bookingTicketID, t.userID, t.bookItemID, t.borrowDate, t.expiredDate, t.returnDate, t.borrowStatus \n"
            + "FROM tblBook b JOIN tblBookItem i ON b.bookID = i.bookID \n"
            + "JOIN tblBookingTicket t ON t.bookItemID = i.bookItemID \n"
            + "WHERE t.borrowStatus like 'Approved' ";

    public List<BorrowDTO> GetListTicket_Approved(String AccountID) throws SQLException {
        List<BorrowDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                if (AccountID.equals("")) {
                    ptm = conn.prepareStatement(GETLISTBOOKTICKET_APPROVED);
                } else {
                    String Connect = GETLISTBOOKTICKET_APPROVED + "AND t.userID like = ? ";
                    ptm = conn.prepareStatement(Connect);
                    ptm.setString(1, AccountID);
                }
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String image = rs.getString("image");
                    String bookName = rs.getString("bookName");
                    int bookingTicketID = rs.getInt("bookingTicketID");
                    AccountDAO accDAO = new AccountDAO();
                    AccountDTO user = accDAO.getAccountByID(rs.getString("userID"));
                    String bookItemID = rs.getString("bookItemID");
                    Date borrowDate = rs.getDate("borrowDate");
                    Date expiredDate = rs.getDate("expiredDate");
                    Date returnDate = rs.getDate("returnDate");
                    String status = rs.getString("borrowStatus");
                    list.add(new BorrowDTO(image, bookName, bookingTicketID, user, bookItemID, borrowDate, expiredDate, returnDate, status));
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
}
