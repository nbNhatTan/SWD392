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
public class ViolationTicketDTO {

    private int violationTicketID;
    private int bookingTicketID;
    private Date createDate;
    private String description;
    private boolean ticketStatus;
    private String staffID;

    public ViolationTicketDTO() {
    }

    public ViolationTicketDTO(int bookingTicketID, Date createDate, String description, boolean ticketStatus, String staffID) {
        this.bookingTicketID = bookingTicketID;
        this.createDate = createDate;
        this.description = description;
        this.ticketStatus = ticketStatus;
        this.staffID = staffID;
    }

    public int getViolationTicketID() {
        return violationTicketID;
    }

    public void setViolationTicketID(int violationTicketID) {
        this.violationTicketID = violationTicketID;
    }

    public int getBookingTicketID() {
        return bookingTicketID;
    }

    public void setBookingTicketID(int bookingTicketID) {
        this.bookingTicketID = bookingTicketID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(boolean ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "FPTLibrary.sample.DTO.Account[ id=" + violationTicketID + " ]";
    }
}
