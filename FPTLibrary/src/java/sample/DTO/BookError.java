/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sample.DTO;

/**
 *
 * @author NhatTan
 */
public class BookError {
    private String bookID;
    private String bookNameError;
    private String quantityError;
    private String bookshelfError;
    private String languageError;
    private String descriptionError;
    private String DDCError;
    private String authorError;
    private String publisherError;
    private String publishYearError;
    private String imageError;
    private String packageNameError;
    private String priceError;
    private String importDateError;

    public BookError() {
        bookID="";
        bookNameError = "";
        quantityError = "";
        bookshelfError = "";
        languageError = "";
        descriptionError = "";
        DDCError = "";
        authorError = "";
        publisherError = "";
        publishYearError = "";
        imageError = "";
        packageNameError = "";
        priceError = "";
        importDateError = "";
    }

    public BookError(String bookID, String bookNameError, String quantityError, String bookshelfError, String languageError, String descriptionError, String DDCError, String authorError, String publisherError, String publishYearError, String imageError, String packageNameError, String priceError, String importDateError) {
        this.bookID = bookID;
        this.bookNameError = bookNameError;
        this.quantityError = quantityError;
        this.bookshelfError = bookshelfError;
        this.languageError = languageError;
        this.descriptionError = descriptionError;
        this.DDCError = DDCError;
        this.authorError = authorError;
        this.publisherError = publisherError;
        this.publishYearError = publishYearError;
        this.imageError = imageError;
        this.packageNameError = packageNameError;
        this.priceError = priceError;
        this.importDateError = importDateError;
    }
    
    public BookError(String bookNameError, String quantityError, String bookshelfError, String languageError, String descriptionError, String DDCError, String authorError, String publisherError, String publishYearError, String imageError, String packageNameError, String priceError, String importDateError) {
        this.bookNameError = bookNameError;
        this.quantityError = quantityError;
        this.bookshelfError = bookshelfError;
        this.languageError = languageError;
        this.descriptionError = descriptionError;
        this.DDCError = DDCError;
        this.authorError = authorError;
        this.publisherError = publisherError;
        this.publishYearError = publishYearError;
        this.imageError = imageError;
        this.packageNameError = packageNameError;
        this.priceError = priceError;
        this.importDateError = importDateError;
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }
    
    public String getBookNameError() {
        return bookNameError;
    }

    public void setBookNameError(String bookNameError) {
        this.bookNameError = bookNameError;
    }

    public String getQuantityError() {
        return quantityError;
    }

    public void setQuantityError(String quantityError) {
        this.quantityError = quantityError;
    }

    public String getBookshelfError() {
        return bookshelfError;
    }

    public void setBookshelfError(String bookshelfError) {
        this.bookshelfError = bookshelfError;
    }

    public String getLanguageError() {
        return languageError;
    }

    public void setLanguageError(String languageError) {
        this.languageError = languageError;
    }

    public String getDescriptionError() {
        return descriptionError;
    }

    public void setDescriptionError(String descriptionError) {
        this.descriptionError = descriptionError;
    }

    public String getDDCError() {
        return DDCError;
    }

    public void setDDCError(String DDCError) {
        this.DDCError = DDCError;
    }

    public String getAuthorError() {
        return authorError;
    }

    public void setAuthorError(String authorError) {
        this.authorError = authorError;
    }

    public String getPublisherError() {
        return publisherError;
    }

    public void setPublisherError(String publisherError) {
        this.publisherError = publisherError;
    }

    public String getPublishYearError() {
        return publishYearError;
    }

    public void setPublishYearError(String publishYearError) {
        this.publishYearError = publishYearError;
    }

    public String getImageError() {
        return imageError;
    }

    public void setImageError(String imageError) {
        this.imageError = imageError;
    }

    public String getPackageNameError() {
        return packageNameError;
    }

    public void setPackageNameError(String packageNameError) {
        this.packageNameError = packageNameError;
    }

    public String getPriceError() {
        return priceError;
    }

    public void setPriceError(String priceError) {
        this.priceError = priceError;
    }

    public String getImportDateError() {
        return importDateError;
    }

    public void setImportDateError(String importDateError) {
        this.importDateError = importDateError;
    }

}
