package com.example.LibraryManagement.com.LibraryManagement.Models;

import jakarta.validation.constraints.NotBlank;

public class IssueBookRequest {
    @NotBlank(message = "Member ID is required")
    private String memberId;
    
    @NotBlank(message = "ISBN is required")
    private String isbn;
    
    // Constructors
    public IssueBookRequest() {
    }
    
    public IssueBookRequest(String memberId, String isbn) {
        this.memberId = memberId;
        this.isbn = isbn;
    }
    
    // Getters and Setters
    public String getMemberId() {
        return memberId;
    }
    
    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }
    
    public String getIsbn() {
        return isbn;
    }
    
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}

