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
import sample.DTO.NewsDTO;
import sample.Utils.DBUtils;




public class NewsDAO {
    private static final String GETTOPNEWS = "SELECT TOP(10) newsID, title, uploadDate FROM tblNews ORDER BY newsID DESC";
    private static final String GETNEWS = "SELECT TOP 1 * FROM tblNews ORDER BY newsID DESC";
    private static final String GET_LIST_NEWS = "SELECT newsID, title, uploadDate FROM tblNews";
    private static final String GET_LIST_NEWS_WITHPAGE = "SELECT newsID, writerName, title, uploadDate, staffID FROM tblNews ORDER BY newsID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY ";
    private static final String GET_NEWS_INFORMATION = "SELECT newsID, writerName, title, head, body, staffID, uploadDate FROM tblNews WHERE newsID= ? ";
    private static final String GET_COUNT_NEWS = "SELECT COUNT(*) 'count' FROM tblNews ";

    private static final String UPDATE_NEWS = "UPDATE tblNews SET writerName=?, title=?, head=?, body=?, staffID=?, uploadDate=? WHERE newsID=? ";
    private static final String DELETE_NEWS = "DETELE tblNews WHERE newsID=? ";
    private static final String ADD_NEWS = "INSERT INTO tblNews( writerName , title, head, body, staffID , uploadDate) VALUES (?,?,?,?,?,?)";
    private static final String VIEW_NEWS = "SELECT newsID, writerName , title, head, body, uploadDate FROM tblNews WHERE newsID = ?";
    private static final String CHECK_DUPLICATE_NEWSID = "SELECT writerName from tblNews WHERE newsID = ?";

    public boolean addNews(NewsDTO news) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(ADD_NEWS);

                ptm.setString(1, news.getWriterName());
                ptm.setString(2, news.getTitle());
                ptm.setString(3, news.getHead());
                ptm.setString(4, news.getBody());
                ptm.setString(5, news.getAccountID());
                ptm.setDate(6, news.getUploadDate());

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

    public List<NewsDTO> getFeedbackList(int bookID) throws SQLException, ClassNotFoundException {
        List<NewsDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(VIEW_NEWS);
                ptm.setInt(1, bookID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int newsID = rs.getInt("newsID");
                    String writerName = rs.getString("writerName");
                    String title = rs.getString("title");
                    String head = rs.getString("head");
                    String body = rs.getString("body");
                    String AccountID = rs.getString("AccountID");
                    Date uploadDate = rs.getDate("uploadDate");
                    NewsDTO news = new NewsDTO(newsID, writerName, title, head, body, AccountID, uploadDate);

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

    public boolean checkDuplicateNewsID(int newsID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_DUPLICATE_NEWSID);
                ptm.setInt(1, newsID);
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

    public int getTotalPagesNews(int limit) throws SQLException {
        int count = 0, totalPages = 0, extraPage = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_COUNT_NEWS);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    count = rs.getInt("count");
                }
                if (count % limit != 0) {
                    extraPage = 1;
                }
                totalPages = (count / limit) + extraPage;
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
        return totalPages;
    }

    public List<NewsDTO> getListNews() throws SQLException {
        List<NewsDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_LIST_NEWS);
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

    public List<NewsDTO> getListNews_withPage(int currentNews, int limit) throws SQLException {
        List<NewsDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_LIST_NEWS_WITHPAGE);
                ptm.setInt(1, currentNews);
                ptm.setInt(2, limit);
                
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int newsID = rs.getInt("newsID");
                    String writerName = rs.getString("writerName");
                    String title = rs.getString("title");
                    Date uploadDate = rs.getDate("uploadDate");
                    String staffID = rs.getString("staffID");
                    NewsDTO news = new NewsDTO(newsID, writerName, title, "", "", staffID, uploadDate);
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

    public NewsDTO getNewsInformation(int newsID) throws SQLException {
        NewsDTO news;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_NEWS_INFORMATION);
                ptm.setInt(1, newsID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String writer = rs.getString("writerName");
                    String title = rs.getString("title");
                    String head = rs.getString("head");
                    String body = rs.getString("body");
                    String staffID = rs.getString("staffID");
                    Date uploadDate = rs.getDate("uploadDate");

                    news = new NewsDTO(newsID, writer, title, head, body, staffID, uploadDate);

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

    public boolean deleteNews(int newID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DELETE_NEWS);
                ptm.setInt(1, newID);
                
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

    public boolean updateNews(NewsDTO news) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_NEWS);
                ptm.setString(1, news.getWriterName());
                ptm.setString(2, news.getTitle());
                ptm.setString(3, news.getHead());
                ptm.setString(4, news.getBody());
                ptm.setString(5, news.getAccountID());
                ptm.setDate(6, news.getUploadDate());
                ptm.setInt(7, news.getNewsID());
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
}
