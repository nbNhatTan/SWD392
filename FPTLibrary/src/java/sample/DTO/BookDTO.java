/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sample.DTO;

/**
 *
 * @author NhatTan
 */
public class BookDTO {

    private String bookID;
    private String bookName;
    private int quantity;
    private String bookshelf;
    private String language;
    private String description;
    private String DDC;
    private String author;
    private String publisher;
    private String publishYear;
    private String image;

    public BookDTO(String bookID, String bookName, int quantity, String bookshelf,  String description, String DDC, String language, String author, String publisher, String publishYear, String image) {
        this.bookID = bookID;
        this.bookName = bookName;
        this.quantity = quantity;
        this.bookshelf = bookshelf;
        this.language = language;
        this.description = description;
        this.DDC = DDC;
        this.author = author;
        this.publisher = publisher;
        this.publishYear = publishYear;
        this.image = image;
    }
    
    
    public BookDTO(String bookName, int Quantity, String bookshelf, String description, String DDC, String language, String author, String publisher, String publishYear, String image) {
        this.bookName = bookName;
        this.quantity = Quantity;
        this.bookshelf = bookshelf;
        this.description = description;
        this.DDC = DDC;
        this.language = language;
        this.author = author;
        this.publisher = publisher;
        this.publishYear = publishYear;
        this.image = image;
    }
    public BookDTO(){
        this.bookID = "";
        this.bookName = "";
        this.quantity = 0;
        this.bookshelf = "";
        this.description = "";
        this.DDC = "";
        this.language = "";
        this.author = "";
        this.publisher = "";
        this.publishYear = "";
        this.image = "";
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }
   

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int Quantity) {
        this.quantity = Quantity;
    }

    public String getBookshelf() {
        return bookshelf;
    }

    public void setBookshelf(String bookshelf) {
        this.bookshelf = bookshelf;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDDC() {
        return DDC;
    }

    public void setDDC(String DDC) {
        this.DDC = DDC;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(String publishYear) {
        this.publishYear = publishYear;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "FPTLibrary.sample.DTO.Account[ id=" + bookID + " ]";
    }

}
