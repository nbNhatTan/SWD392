/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sample.DTO;

/**
 *
 * @author NhatTan
 */
public class BookTagDTO {

    private int categoryID;
    private String bookID;

    public BookTagDTO(int categoryID, String bookID) {
        this.categoryID = categoryID;
        this.bookID = bookID;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    

    @Override
    public String toString() {
        return "FPTLibrary.sample.DTO.Account[ id=" + categoryID + " ]";
    }
}
