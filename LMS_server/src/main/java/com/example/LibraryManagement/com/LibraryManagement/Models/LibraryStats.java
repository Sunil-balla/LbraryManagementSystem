package com.example.LibraryManagement.com.LibraryManagement.Models;

public class LibraryStats {
    private int totalBooks;
    private int availableBooks;
    private int issuedBooks;
    private int totalMembers;
    private int activeMembers;
    private int totalTransactions;
    private int activeTransactions;
    private int overdueTransactions;
    private double totalFines;
    
    // Constructors
    public LibraryStats() {
    }
    
    public LibraryStats(int totalBooks, int availableBooks, int issuedBooks, 
                        int totalMembers, int activeMembers, int totalTransactions, 
                        int activeTransactions, int overdueTransactions, double totalFines) {
        this.totalBooks = totalBooks;
        this.availableBooks = availableBooks;
        this.issuedBooks = issuedBooks;
        this.totalMembers = totalMembers;
        this.activeMembers = activeMembers;
        this.totalTransactions = totalTransactions;
        this.activeTransactions = activeTransactions;
        this.overdueTransactions = overdueTransactions;
        this.totalFines = totalFines;
    }
    
    // Getters and Setters
    public int getTotalBooks() {
        return totalBooks;
    }
    
    public void setTotalBooks(int totalBooks) {
        this.totalBooks = totalBooks;
    }
    
    public int getAvailableBooks() {
        return availableBooks;
    }
    
    public void setAvailableBooks(int availableBooks) {
        this.availableBooks = availableBooks;
    }
    
    public int getIssuedBooks() {
        return issuedBooks;
    }
    
    public void setIssuedBooks(int issuedBooks) {
        this.issuedBooks = issuedBooks;
    }
    
    public int getTotalMembers() {
        return totalMembers;
    }
    
    public void setTotalMembers(int totalMembers) {
        this.totalMembers = totalMembers;
    }
    
    public int getActiveMembers() {
        return activeMembers;
    }
    
    public void setActiveMembers(int activeMembers) {
        this.activeMembers = activeMembers;
    }
    
    public int getTotalTransactions() {
        return totalTransactions;
    }
    
    public void setTotalTransactions(int totalTransactions) {
        this.totalTransactions = totalTransactions;
    }
    
    public int getActiveTransactions() {
        return activeTransactions;
    }
    
    public void setActiveTransactions(int activeTransactions) {
        this.activeTransactions = activeTransactions;
    }
    
    public int getOverdueTransactions() {
        return overdueTransactions;
    }
    
    public void setOverdueTransactions(int overdueTransactions) {
        this.overdueTransactions = overdueTransactions;
    }
    
    public double getTotalFines() {
        return totalFines;
    }
    
    public void setTotalFines(double totalFines) {
        this.totalFines = totalFines;
    }
}

