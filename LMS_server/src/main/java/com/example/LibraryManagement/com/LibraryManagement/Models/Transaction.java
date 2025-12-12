package com.example.LibraryManagement.com.LibraryManagement.Models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public class Transaction {
    @NotBlank(message = "Transaction ID is required")
    private String transactionId;
    
    @NotBlank(message = "Member ID is required")
    private String memberId;
    
    @NotBlank(message = "ISBN is required")
    private String isbn;
    
    @NotNull(message = "Issue date is required")
    private LocalDate issueDate;
    
    @NotNull(message = "Due date is required")
    private LocalDate dueDate;
    
    private LocalDate returnDate;
    
    @NotNull(message = "Transaction status is required")
    private TransactionStatus status;
    
    private Double fineAmount;
    
    // Enum for transaction status
    public enum TransactionStatus {
        ISSUED, RETURNED, OVERDUE
    }
    
    // Constructors
    public Transaction() {
        this.fineAmount = 0.0;
    }
    
    public Transaction(String transactionId, String memberId, String isbn, 
                       LocalDate issueDate, LocalDate dueDate) {
        this.transactionId = transactionId;
        this.memberId = memberId;
        this.isbn = isbn;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
        this.status = TransactionStatus.ISSUED;
        this.fineAmount = 0.0;
    }
    
    // Getters and Setters
    public String getTransactionId() {
        return transactionId;
    }
    
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
    
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
    
    public LocalDate getIssueDate() {
        return issueDate;
    }
    
    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }
    
    public LocalDate getDueDate() {
        return dueDate;
    }
    
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
    
    public LocalDate getReturnDate() {
        return returnDate;
    }
    
    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
    
    public TransactionStatus getStatus() {
        return status;
    }
    
    public void setStatus(TransactionStatus status) {
        this.status = status;
    }
    
    public Double getFineAmount() {
        return fineAmount;
    }
    
    public void setFineAmount(Double fineAmount) {
        this.fineAmount = fineAmount;
    }
    
    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId='" + transactionId + '\'' +
                ", memberId='" + memberId + '\'' +
                ", isbn='" + isbn + '\'' +
                ", issueDate=" + issueDate +
                ", dueDate=" + dueDate +
                ", returnDate=" + returnDate +
                ", status=" + status +
                ", fineAmount=" + fineAmount +
                '}';
    }
}

