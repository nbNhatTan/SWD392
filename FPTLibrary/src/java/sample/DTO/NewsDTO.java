/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sample.DTO;

import java.sql.Date;

/**
 *
 * @author NhatTan
 */
public class NewsDTO {

    private int newsID;
    private String writerName;
    private String title;
    private String head;
    private String body;
    private String AccountID;
    private Date uploadDate;

    public NewsDTO() {
    }
    
    public NewsDTO(int newsID, String writerName, String title, String head, String body, String AccountID, Date uploadDate) {
        this.newsID = newsID;
        this.writerName = writerName;
        this.title = title;
        this.head = head;
        this.body = body;
        this.AccountID = AccountID;
        this.uploadDate = uploadDate;
    }

    public NewsDTO(String writerName, String title, String head, String body, String AccountID, Date uploadDate) {
        this.writerName = writerName;
        this.title = title;
        this.head = head;
        this.body = body;
        this.AccountID = AccountID;
        this.uploadDate = uploadDate;
    }

    
    public int getNewsID() {
        return newsID;
    }

    public void setNewsID(int newsID) {
        this.newsID = newsID;
    }

    public String getWriterName() {
        return writerName;
    }

    public void setWriterName(String writerName) {
        this.writerName = writerName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getAccountID() {
        return AccountID;
    }

    public void setAccountID(String AccountID) {
        this.AccountID = AccountID;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    @Override
    public String toString() {
        return "FPTLibrary.sample.DTO.Account[ id=" + newsID + " ]";
    }
}
