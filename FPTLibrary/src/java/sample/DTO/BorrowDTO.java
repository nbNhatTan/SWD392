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
public class BorrowDTO {

    private String image;
    private String bookName;
    private int bookingTicketID;
    private AccountDTO userID;
    private String bookItemID;
    private Date borrowDate;
    private Date expiredDate;
    private Date returnDate;
    private String borrowStatus;
    private AccountDTO staffID;

    public BorrowDTO(String image, String bookName, int bookingTicketID, AccountDTO userID, String bookItemID, Date borrowDate, Date expiredDate, Date returnDate, String borrowStatus) {
        this.image = image;
        this.bookName = bookName;
        this.bookingTicketID = bookingTicketID;
        this.userID = userID;
        this.bookItemID = bookItemID;
        this.borrowDate = borrowDate;
        this.expiredDate = expiredDate;
        this.returnDate = returnDate;
        this.borrowStatus = borrowStatus;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getBookingTicketID() {
        return bookingTicketID;
    }

    public void setBookingTicketID(int bookingTicketID) {
        this.bookingTicketID = bookingTicketID;
    }

    public String getBookItemID() {
        return bookItemID;
    }

    public void setBookItemID(String bookItemID) {
        this.bookItemID = bookItemID;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public Date getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Date expiredDate) {
        this.expiredDate = expiredDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public String getBorrowStatus() {
        return borrowStatus;
    }

    public void setBorrowStatus(String borrowStatus) {
        this.borrowStatus = borrowStatus;
    }

    public AccountDTO getUserID() {
        return userID;
    }

    public void setUserID(AccountDTO userID) {
        this.userID = userID;
    }

    public AccountDTO getStaffID() {
        return staffID;
    }

    public void setStaffID(AccountDTO staffID) {
        this.staffID = staffID;
    }

}
