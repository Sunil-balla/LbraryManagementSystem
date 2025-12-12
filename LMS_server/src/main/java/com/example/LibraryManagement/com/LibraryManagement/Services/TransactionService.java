package com.example.LibraryManagement.com.LibraryManagement.Services;

import com.example.LibraryManagement.com.LibraryManagement.Models.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TransactionService {
    private final Map<String, Transaction> transactionRepository = new HashMap<>();
    private final BookService bookService;
    private final MemberService memberService;
    
    private static final int DEFAULT_LOAN_PERIOD_DAYS = 14;
    private static final double FINE_PER_DAY = 5.0;
    
    @Autowired
    public TransactionService(BookService bookService, MemberService memberService) {
        this.bookService = bookService;
        this.memberService = memberService;
    }
    
    // Issue a book
    public Transaction issueBook(String memberId, String isbn) {
        // Validate member
        if (!memberService.isMemberActive(memberId)) {
            throw new IllegalStateException("Member is not active");
        }
        
        // Validate book availability
        if (!bookService.isBookAvailable(isbn)) {
            throw new IllegalStateException("Book is not available");
        }
        
        // Create transaction
        String transactionId = UUID.randomUUID().toString();
        LocalDate issueDate = LocalDate.now();
        LocalDate dueDate = issueDate.plusDays(DEFAULT_LOAN_PERIOD_DAYS);
        
        Transaction transaction = new Transaction(transactionId, memberId, isbn, issueDate, dueDate);
        transactionRepository.put(transactionId, transaction);
        
        // Update book availability
        bookService.updateAvailability(isbn, -1);
        
        return transaction;
    }
    
    // Return a book
    public Transaction returnBook(String transactionId) {
        Transaction transaction = getTransactionById(transactionId);
        
        if (transaction.getStatus() == Transaction.TransactionStatus.RETURNED) {
            throw new IllegalStateException("Book already returned");
        }
        
        LocalDate returnDate = LocalDate.now();
        transaction.setReturnDate(returnDate);
        transaction.setStatus(Transaction.TransactionStatus.RETURNED);
        
        // Calculate fine if overdue
        if (returnDate.isAfter(transaction.getDueDate())) {
            long daysOverdue = ChronoUnit.DAYS.between(transaction.getDueDate(), returnDate);
            double fine = daysOverdue * FINE_PER_DAY;
            transaction.setFineAmount(fine);
        }
        
        // Update book availability
        bookService.updateAvailability(transaction.getIsbn(), 1);
        
        return transaction;
    }
    
    // Get all transactions
    public List<Transaction> getAllTransactions() {
        return new ArrayList<>(transactionRepository.values());
    }
    
    // Get transaction by ID
    public Transaction getTransactionById(String transactionId) {
        Transaction transaction = transactionRepository.get(transactionId);
        if (transaction == null) {
            throw new NoSuchElementException("Transaction with ID " + transactionId + " not found");
        }
        return transaction;
    }
    
    // Get transactions by member
    public List<Transaction> getTransactionsByMember(String memberId) {
        return transactionRepository.values().stream()
                .filter(t -> t.getMemberId().equals(memberId))
                .collect(Collectors.toList());
    }
    
    // Get transactions by book
    public List<Transaction> getTransactionsByBook(String isbn) {
        return transactionRepository.values().stream()
                .filter(t -> t.getIsbn().equals(isbn))
                .collect(Collectors.toList());
    }
    
    // Get active transactions (issued books)
    public List<Transaction> getActiveTransactions() {
        return transactionRepository.values().stream()
                .filter(t -> t.getStatus() == Transaction.TransactionStatus.ISSUED)
                .collect(Collectors.toList());
    }
    
    // Get overdue transactions
    public List<Transaction> getOverdueTransactions() {
        LocalDate today = LocalDate.now();
        return transactionRepository.values().stream()
                .filter(t -> t.getStatus() == Transaction.TransactionStatus.ISSUED && 
                        t.getDueDate().isBefore(today))
                .collect(Collectors.toList());
    }
}

