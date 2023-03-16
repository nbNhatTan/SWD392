/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sample.DTO;

/**
 *
 * @author admin
 */
public class Paging {
    private int page;
    private int totalPages;
    private int visiblePages;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getVisiblePages() {
        return visiblePages;
    }

    public void setVisiblePages(int visiblePages) {
        this.visiblePages = visiblePages;
    }

    public Paging() {
        this.page=1;
        this.totalPages=10;
        this.visiblePages=10;
        
    }

    public Paging(int page, int totalPages) {
        this.page = page;
        this.totalPages = totalPages;
        
    }
    
    
}