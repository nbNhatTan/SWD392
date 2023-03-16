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
public class BookingTicketDTO {

    private int bookingTicketID;
    private String userID;
    private String bookItemID;
    private Date borrowDate;
    private Date expiredDate;
    private Date returnDate;
    private String borrowStatus;

    public BookingTicketDTO(String userID, String bookItemID, Date borrowDate, Date expiredDate, Date returnDate, String borrowStatus) {
        this.userID = userID;
        this.bookItemID = bookItemID;
        this.borrowDate = borrowDate;
        this.expiredDate = expiredDate;
        this.returnDate = returnDate;
        this.borrowStatus = borrowStatus;
    }

    public int getBookingTicketID() {
        return bookingTicketID;
    }

    public void setBookingTicketID(int bookingTicketID) {
        this.bookingTicketID = bookingTicketID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
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

    @Override
    public String toString() {
        return "FPTLibrary.sample.DTO.Account[ id=" + bookingTicketID + " ]";
    }
}
