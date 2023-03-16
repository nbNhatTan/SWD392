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
import java.sql.Statement;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import sample.DTO.BookDTO;
import sample.DTO.BookItemDTO;
import sample.DTO.CategoryDTO;
import sample.DTO.NewsDTO;
import sample.DTO.PackageDTO;
import sample.Utils.DBUtils;

/**
 *
 * @author NhatTan
 */
public class BookDAO {

    private static final String CREATE_BOOK = "INSERT INTO tblBook(bookID,bookName, quantity, bookshelf, languageID, [description], DDC, authorID, publisherID, publishYear, [image], status) VALUES (?,?,?,?,?,?,?,?,?,?,?,1)";
    private static final String EDIT_BOOK = "UPDATE tblBook SET bookID = ? quantity = ?, bookshelf = ?,[description] = ?, [image] = ? WHERE bookID = ?";
    private static final String DELETE = "UPDATE tblBook SET status='false' WHERE bookID=?";
    private static final String CREATE_PACKAGE = "INSERT INTO tblPackage(PackageName, price, importDate) VALUES (?,?,?)";
    private static final String CREATE_BOOKITEM = "INSERT INTO tblBookItem(bookItemID, bookID, bookStatus, packageID) VALUES (?,?,?,?)";
    private static final String GETLIST = "SELECT bookID, bookName, quantity, bookshelf, [image], [description], DDC, l.languageName, a.authorName, p.publisherName, publishYear FROM tblBook b JOIN tblLanguages l ON b.languageID = l.languageID JOIN tblAuthors a ON b.authorID = a.authorID JOIN tblPublishers p ON b.publisherID = p.publisherID WHERE b.status = 1";
    private static final String GETLIST_PACKAGE = "SELECT packageName, price, importDate FROM tblPackage";
    private static final String GETLIST_BOOKITEM = "SELECT bookItemID, bookID, bookStatus, packageID FROM tblBookItem";
    private static final String UPDATE_BOOKITEM = "UPDATE tblBookItem SET bookStatus=? WHERE bookItemID=? ";
    private static final String COUNT = "SELECT COUNT(bookItemID) FROM tblBookItem WHERE bookID = ?";
    private static final String COUNTQUANTITY = "SELECT COUNT(bookItemID) FROM tblBookItem WHERE bookID = ? AND bookStatus = 'On bookshelf'";
    private static final String GETBOOKBYID = "SELECT bookName, quantity, bookshelf, [image], [description], DDC, l.languageName, a.authorName, p.publisherName, publishYear FROM tblBook b JOIN tblLanguages l ON b.languageID = l.languageID JOIN tblAuthors a ON b.authorID = a.authorID JOIN tblPublishers p ON b.publisherID = p.publisherID where bookID=?";
    private static final String GETTOP5BOOK = "SELECT TOP(5) bookID, bookName, [image] FROM tblBook WHERE status =1 ORDER BY bookID DESC";
    private static final String GETTOPNEWS = "SELECT TOP(10) newsID, title, uploadDate FROM tblNews ORDER BY newsID DESC";
    private static final String GETNEWS = "SELECT TOP 1 * FROM tblNews ORDER BY newsID DESC";
    private static final String GETBOOKTAG = "SELECT c.categoryID, categoryName FROM tblCategories c JOIN tblBookTag t ON c.categoryID = t.categoryID WHERE t.bookID = ?";
    private static final String GETALLBOOKTAG = "SELECT categoryID, categoryName FROM tblCategories";
    private static final String GETLISTBOOK_BOOKTAG = "SELECT b.bookID, bookName, bookshelf, [image], [description], DDC, l.languageName, a.authorName, p.publisherName, publishYear "
            + "FROM tblBook b JOIN tblLanguages l ON b.languageID = l.languageID "
            + "JOIN tblAuthors a ON b.authorID = a.authorID "
            + "JOIN tblPublishers p ON b.publisherID = p.publisherID "
            + "JOIN tblBookTag t ON b.bookID = t.bookID "
            + "WHERE t.categoryID = ? AND b.status = 1";
    private static final String ADVANCE_SEARCH = "SELECT b.bookID, bookName, bookshelf, [image], [description], "
            + "DDC, l.languageName, a.authorName, p.publisherName, publishYear FROM tblBook b JOIN tblLanguages l "
            + "ON b.languageID = l.languageID JOIN tblAuthors a "
            + "ON b.authorID = a.authorID JOIN tblPublishers p "
            + "ON b.publisherID = p.publisherID "
            + "WHERE bookName like ? AND a.authorName like ? AND p.publisherName like ? AND l.languageName like ? AND b.status = 1 "
            + "ORDER BY bookID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
    private static final String ADVANCE_SEARCH_2 = "SELECT b.bookID, bookName, bookshelf, [image], [description], "
            + "DDC, l.languageName, a.authorName, p.publisherName, publishYear FROM tblBook b JOIN tblLanguages l "
            + "ON b.languageID = l.languageID JOIN tblAuthors a "
            + "ON b.authorID = a.authorID JOIN tblPublishers p "
            + "ON b.publisherID = p.publisherID JOIN tblBookTag t "
            + "ON b.bookID = t.bookID "
            + "WHERE bookName like ? AND a.authorName like ? AND p.publisherName like ? AND l.languageName like ?  AND t.categoryID = ? AND b.status = 1 "
            + "ORDER BY bookID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
    private static final String COUNT_ADVANCE_SEARCH1 = "SELECT Count(*) 'count' "
            + "FROM tblBook b "
            + "JOIN tblLanguages l ON b.languageID = l.languageID "
            + "JOIN tblAuthors a ON b.authorID = a.authorID "
            + "JOIN tblPublishers p ON b.publisherID = p.publisherID "
            + "WHERE bookName like ? AND a.authorName like ? AND p.publisherName like ? AND l.languageName like ? AND b.status = 1";
    private static final String COUNT_ADVANCE_SEARCH2 = "SELECT Count(*) 'count' "
            + "FROM tblBook b "
            + "JOIN tblLanguages l ON b.languageID = l.languageID "
            + "JOIN tblAuthors a ON b.authorID = a.authorID "
            + "JOIN tblPublishers p ON b.publisherID = p.publisherID "
            + "JOIN tblBookTag t ON b.bookID = t.bookID "
            + "WHERE bookName like ? AND a.authorName like ? AND p.publisherName like ? AND l.languageName like ? AND b.status = 1";
    private static final String GETBOOK_BOOKITEMID = "SELECT bookName, quantity, bookshelf, [image], [description], DDC, l.languageName, a.authorName, p.publisherName, publishYear \n"
            + "FROM tblBook b JOIN tblBookItem i ON b.bookID = i.bookID \n"
            + "JOIN tblLanguages l ON b.languageID = l.languageID \n"
            + "JOIN tblAuthors a ON b.authorID = a.authorID \n"
            + "JOIN tblPublishers p ON b.publisherID = p.publisherID \n"
            + "WHERE bookItemID = ? AND i.bookStatus = 'On bookshelf'";

    public String VNtoEN(String value) {
        try {
            String temp = Normalizer.normalize(value, Normalizer.Form.NFD);
            Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
            return pattern.matcher(temp).replaceAll("");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public String createBookID(String bookName, String author, String publishYear, String DDC, String publisher) {
        author = VNtoEN(author.toUpperCase());
        bookName = VNtoEN(bookName.toUpperCase());
        publisher = VNtoEN(publisher.toUpperCase());
        String bookID = DDC + author.charAt(0) + bookName.charAt(0) + publisher.charAt(0) + publishYear;
        return bookID;

    }

    // createBook -> Almost done!
    public boolean createBook(BookDTO book) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CREATE_BOOK);
                ptm.setString(1, book.getBookID());
                ptm.setString(2, book.getBookName());
                ptm.setInt(3, book.getQuantity());
                ptm.setString(4, book.getBookshelf());
                ptm.setInt(5, findInformationID(book.getLanguage(), "Language"));
                ptm.setString(6, book.getDescription());
                ptm.setString(7, book.getDDC());
                ptm.setInt(8, findInformationID(book.getAuthor(), "Author"));
                ptm.setInt(9, findInformationID(book.getPublisher(), "Publisher"));
                ptm.setString(10, book.getPublishYear());
                ptm.setString(11, book.getImage());
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

    public boolean editBook(BookDTO book) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(EDIT_BOOK);
                ptm.setString(1, book.getBookID());
                ptm.setInt(2, book.getQuantity());
                ptm.setString(3, book.getBookshelf());
                ptm.setString(4, book.getDescription());
                ptm.setString(5, book.getImage());
                ptm.setString(6, book.getBookID());
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

    public boolean deleteBook(String bookID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DELETE);
                ptm.setString(1, bookID);
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

    public int createPackage(PackageDTO pack) throws SQLException {
        int id = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CREATE_PACKAGE, Statement.RETURN_GENERATED_KEYS);
                ptm.setString(1, pack.getPackageName());
                ptm.setInt(2, pack.getPrice());
                ptm.setDate(3, pack.getImportDate());
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

    public int insertBookItem(String bookID, int packageID) throws SQLException {
        int total = 0;
        int count = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(COUNT);
                ptm.setString(1, bookID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    count = rs.getInt(1);
                }
                int quantity = getBookByID(bookID).getQuantity();
                for (int i = 0; i < quantity; i++) {
                    count++;
                    String bookItemID = bookID + "-" + count;
                    ptm = conn.prepareStatement(CREATE_BOOKITEM);
                    ptm.setString(1, bookItemID);
                    ptm.setString(2, bookID);
                    ptm.setString(3, "On bookshelf");
                    ptm.setInt(4, packageID);
                    ptm.executeUpdate();
                    total++;
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
        return total;
    }

    public List<BookDTO> getListBook() throws SQLException {
        List<BookDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GETLIST);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String bookID = rs.getString("bookID");
                    String bookName = rs.getString("bookName");
                    int quantity = rs.getInt("quantity");
                    String bookshelf = rs.getString("bookshelf");
                    String image = rs.getString("image");
                    String description = rs.getString("description");
                    String DDC = rs.getString("DDC");
                    String language = rs.getString("languageName");
                    String author = rs.getString("authorName");
                    String publisher = rs.getString("publisherName");
                    String publishYear = rs.getString("publishYear");
                    BookDTO book = new BookDTO(bookName, quantity, bookshelf, description, DDC, language, author, publisher, publishYear, image);
                    book.setBookID(bookID);
                    list.add(book);
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

    public List<PackageDTO> getListPackage() throws SQLException {
        List<PackageDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GETLIST_PACKAGE);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String packageName = rs.getString("packageName");
                    int price = rs.getInt("price");
                    Date importDate = rs.getDate("importDate");
                    list.add(new PackageDTO(packageName, price, importDate));
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

    public List<BookItemDTO> getListBookItem() throws SQLException {
        List<BookItemDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GETLIST_BOOKITEM);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String bookItemID = rs.getString("bookItemID");
                    String bookID = rs.getString("bookID");
                    String bookStatus = rs.getString("bookStatus");
                    int packageID = rs.getInt("packageID");
                    list.add(new BookItemDTO(bookItemID, bookID, bookStatus, packageID));
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

    public boolean updateBookItem(String bookItemID, String status) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_BOOKITEM);
                ptm.setString(1, status);
                ptm.setString(2, bookItemID);
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

    public BookDTO getBookByID(String bookID) throws SQLException {
        BookDTO book;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GETBOOKBYID);
                ptm.setString(1, bookID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String bookName = rs.getString("bookName");
                    int quantity = rs.getInt("quantity");
                    String bookshelf = rs.getString("bookshelf");
                    String image = rs.getString("image");
                    String description = rs.getString("description");
                    String DDC = rs.getString("DDC");
                    String language = rs.getString("languageName");
                    String author = rs.getString("authorName");
                    String publisher = rs.getString("publisherName");
                    String publishYear = rs.getString("publishYear");
                    book = new BookDTO(bookID, bookName, quantity, bookshelf, description, DDC, language, author, publisher, publishYear, image);
                    book.setBookID(bookID);
                    return book;
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

    public BookDTO getBookByBookItemID(String bookItemID) throws SQLException {
        BookDTO book;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GETBOOK_BOOKITEMID);
                ptm.setString(1, bookItemID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String bookName = rs.getString("bookName");
                    int quantity = rs.getInt("quantity");
                    String bookshelf = rs.getString("bookshelf");
                    String image = rs.getString("image");
                    String description = rs.getString("description");
                    String DDC = rs.getString("DDC");
                    String language = rs.getString("languageName");
                    String author = rs.getString("authorName");
                    String publisher = rs.getString("publisherName");
                    String publishYear = rs.getString("publishYear");
                    book = new BookDTO(bookName, quantity, bookshelf, description, DDC, language, author, publisher, publishYear, image);
                    return book;
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

    public int findInformationID(String name, String infor) throws SQLException {
        int id = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {

                String table = "tbl" + infor + "s";
                String labelName = infor.toLowerCase() + "Name";
                String labelID = infor.toLowerCase() + "ID";
                String sql = "SELECT " + labelID + " FROM " + table + " WHERE " + labelName + " LIKE N'" + name + "'";
                ptm = conn.prepareStatement(sql);
//                ptm.setString(1, labelID);
//                ptm.setString(2, table);
//                ptm.setString(3, labelName);
//                ptm.setString(4, name);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    id = rs.getInt(1);
                } else {
                    sql = "INSERT INTO " + table + "(" + labelName + ") VALUES (N'" + name + "')";
                    ptm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//                    ptm.setString(1, table);
//                    ptm.setString(2, labelName);
//                    ptm.setString(3, name);
                    ptm.executeUpdate();
                    rs = ptm.getGeneratedKeys();
                    if (rs.next()) {
                        id = rs.getInt(1);
                    }
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

    public List<BookDTO> getTop5Book() throws SQLException {
        List<BookDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GETTOP5BOOK);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String bookID = rs.getString("bookID");
                    String bookName = rs.getString("bookName");
                    String image = rs.getString("image");
                    BookDTO book = new BookDTO(bookName, 0, "", "", "", "", "", "", "", image);
                    book.setBookID(bookID);
                    list.add(book);
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

    public List<NewsDTO> getTopNews() throws SQLException {
        List<NewsDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GETTOPNEWS);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int newsID = rs.getInt("newsID");
                    String title = rs.getString("title");
                    Date uploadDate = rs.getDate("uploadDate");
                    NewsDTO news = new NewsDTO(newsID, "", title, "", "", "", uploadDate);
                    news.setNewsID(newsID);
                    list.add(news);
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

    public NewsDTO getNews() throws SQLException {
        NewsDTO news = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GETNEWS);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int newsID = rs.getInt("newsID");
                    String writeName = rs.getString("writerName");
                    String title = rs.getString("title");
                    String head = rs.getString("head");
                    String body = rs.getString("body");
                    String staffID = rs.getString("staffID");
                    Date uploadDate = rs.getDate("uploadDate");
                    news = new NewsDTO(newsID, writeName, title, head, body, staffID, uploadDate);
                    news.setNewsID(newsID);
                    return news;
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

    public List<BookDTO> getListBook(String bBookName, String bAuthor, String bPublisher, String bLanguage, int categoryId, int searchPage, int searchLimit) throws SQLException {
        List<BookDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                if (categoryId == 0) {
                    ptm = conn.prepareStatement(ADVANCE_SEARCH);
                    ptm.setNString(1, "%" + bBookName + "%");
                    ptm.setNString(2, "%" + bAuthor + "%");
                    ptm.setNString(3, "%" + bPublisher + "%");
                    ptm.setNString(4, "%" + bLanguage + "%");
                    ptm.setInt(5, searchPage);
                    ptm.setInt(6, searchLimit);
                } else {
                    ptm = conn.prepareStatement(ADVANCE_SEARCH_2);
                    ptm.setNString(1, "%" + bBookName + "%");
                    ptm.setNString(2, "%" + bAuthor + "%");
                    ptm.setNString(3, "%" + bPublisher + "%");
                    ptm.setNString(4, "%" + bLanguage + "%");
                    ptm.setInt(5, categoryId);
                    ptm.setInt(6, searchPage);
                    ptm.setInt(7, searchLimit);
                }
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String bookID = rs.getString("bookID");
                    String bookName = rs.getString("bookName");
                    String bookshelf = rs.getString("bookshelf");
                    String image = rs.getString("image");
                    String description = rs.getString("description");
                    String DDC = rs.getString("DDC");
                    String language = rs.getString("languageName");
                    String author = rs.getString("authorName");
                    String publisher = rs.getString("publisherName");
                    String publishYear = rs.getString("publishYear");
                    BookDTO book = new BookDTO(bookName, 0, bookshelf, description, DDC, language, author, publisher, publishYear, image);
                    int quantity = countQuantity(bookID);
                    book.setQuantity(quantity);
                    book.setBookID(bookID);
                    list.add(book);
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

    public List<BookDTO> getListBookByBookTag(int categoryId) throws SQLException {
        List<BookDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GETLISTBOOK_BOOKTAG);
                ptm.setInt(1, categoryId);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String bookID = rs.getString("bookID");
                    String bookName = rs.getString("bookName");
                    String bookshelf = rs.getString("bookshelf");
                    String image = rs.getString("image");
                    String description = rs.getString("description");
                    String DDC = rs.getString("DDC");
                    String language = rs.getString("languageName");
                    String author = rs.getString("authorName");
                    String publisher = rs.getString("publisherName");
                    String publishYear = rs.getString("publishYear");
                    BookDTO book = new BookDTO(bookName, 0, bookshelf, description, DDC, language, author, publisher, publishYear, image);
                    book.setBookID(bookID);
                    list.add(book);
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

    public List<CategoryDTO> getBookTag(String bookID) throws SQLException {
        List<CategoryDTO> list = new ArrayList<>();
        CategoryDTO category;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GETBOOKTAG);
                ptm.setString(1, bookID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int categoryID = Integer.parseInt(rs.getString("categoryID"));
                    String categoryName = rs.getString("categoryName");
                    category = new CategoryDTO(categoryID, categoryName, "");
                    list.add(category);
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

    public List<CategoryDTO> getAllBookTag() throws SQLException {
        List<CategoryDTO> list = new ArrayList<>();
        CategoryDTO category;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GETALLBOOKTAG);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int categoryID = Integer.parseInt(rs.getString("categoryID"));
                    String categoryName = rs.getString("categoryName");
                    category = new CategoryDTO(categoryID, categoryName, "");
                    list.add(category);
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

    public int countGetListBook_TotalPage(String bBookName, String bAuthor, String bPublisher, String bLanguage, int categoryId, int searchLimit) throws SQLException {
        int count = 0, totalPage = 0, extraPage = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                if (categoryId == 0) {
                    ptm = conn.prepareStatement(COUNT_ADVANCE_SEARCH1);
                    ptm.setString(1, "%" + bBookName + "%");
                    ptm.setString(2, "%" + bAuthor + "%");
                    ptm.setString(3, "%" + bPublisher + "%");
                    ptm.setString(4, "%" + bLanguage + "%");

                } else {
                    ptm = conn.prepareStatement(COUNT_ADVANCE_SEARCH2 + "  AND t.categoryID = ?");
                    ptm.setString(1, "%" + bBookName + "%");
                    ptm.setString(2, "%" + bAuthor + "%");
                    ptm.setString(3, "%" + bPublisher + "%");
                    ptm.setString(4, "%" + bLanguage + "%");
                    ptm.setInt(5, categoryId);

                }
                rs = ptm.executeQuery();

                while (rs.next()) {
                    count = rs.getInt("count");
                }
                if (count % searchLimit != 0) {
                    extraPage = 1;
                }
                totalPage = (count / searchLimit) + extraPage;

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

        return totalPage;
    }

    public int countQuantity(String bookID) throws SQLException {
        int count = -1;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(COUNTQUANTITY);
                ptm.setString(1, bookID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    count = rs.getInt(1);
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

        return count;
    }
}
