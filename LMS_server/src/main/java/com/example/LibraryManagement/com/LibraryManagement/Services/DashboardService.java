package com.example.LibraryManagement.com.LibraryManagement.Services;

import com.example.LibraryManagement.com.LibraryManagement.Models.LibraryStats;
import com.example.LibraryManagement.com.LibraryManagement.Models.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardService {
    
    @Autowired
    private BookService bookService;
    
    @Autowired
    private MemberService memberService;
    
    @Autowired
    private TransactionService transactionService;
    
    public LibraryStats getLibraryStats() {
        LibraryStats stats = new LibraryStats();
        
        // Book statistics
        stats.setTotalBooks(bookService.getAllBooks().size());
        stats.setAvailableBooks(bookService.getAvailableBooks().size());
        
        // Member statistics
        stats.setTotalMembers(memberService.getAllMembers().size());
        stats.setActiveMembers(memberService.getActiveMembers().size());
        
        // Transaction statistics
        List<Transaction> allTransactions = transactionService.getAllTransactions();
        stats.setTotalTransactions(allTransactions.size());
        stats.setActiveTransactions(transactionService.getActiveTransactions().size());
        stats.setOverdueTransactions(transactionService.getOverdueTransactions().size());
        
        // Calculate issued books
        stats.setIssuedBooks(stats.getActiveTransactions());
        
        // Calculate total fines
        double totalFines = allTransactions.stream()
                .mapToDouble(t -> t.getFineAmount() != null ? t.getFineAmount() : 0.0)
                .sum();
        stats.setTotalFines(totalFines);
        
        return stats;
    }
}

