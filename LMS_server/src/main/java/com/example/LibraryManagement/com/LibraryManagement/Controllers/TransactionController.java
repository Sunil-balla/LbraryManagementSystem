package com.example.LibraryManagement.com.LibraryManagement.Controllers;

import com.example.LibraryManagement.com.LibraryManagement.Models.ApiResponse;
import com.example.LibraryManagement.com.LibraryManagement.Models.Transaction;
import com.example.LibraryManagement.com.LibraryManagement.Services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/transactions")
@CrossOrigin(origins = "*")
public class TransactionController {
    
    @Autowired
    private TransactionService transactionService;
    
    // Get all transactions
    @GetMapping
    public ResponseEntity<ApiResponse<List<Transaction>>> getAllTransactions() {
        try {
            List<Transaction> transactions = transactionService.getAllTransactions();
            return ResponseEntity.ok(ApiResponse.success("Transactions retrieved successfully", transactions));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("Error retrieving transactions: " + e.getMessage()));
        }
    }
    
    // Get transaction by ID
    @GetMapping("/{transactionId}")
    public ResponseEntity<ApiResponse<Transaction>> getTransactionById(@PathVariable String transactionId) {
        try {
            Transaction transaction = transactionService.getTransactionById(transactionId);
            return ResponseEntity.ok(ApiResponse.success("Transaction retrieved successfully", transaction));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error("Transaction not found: " + e.getMessage()));
        }
    }
    
    // Issue a book
    @PostMapping("/issue")
    public ResponseEntity<ApiResponse<Transaction>> issueBook(@RequestBody Map<String, String> request) {
        try {
            String memberId = request.get("memberId");
            String isbn = request.get("isbn");
            
            if (memberId == null || isbn == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(ApiResponse.error("Member ID and ISBN are required"));
            }
            
            Transaction transaction = transactionService.issueBook(memberId, isbn);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(ApiResponse.success("Book issued successfully", transaction));
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.error("Error issuing book: " + e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("Error issuing book: " + e.getMessage()));
        }
    }
    
    // Return a book
    @PatchMapping("/{transactionId}/return")
    public ResponseEntity<ApiResponse<Transaction>> returnBook(@PathVariable String transactionId) {
        try {
            Transaction transaction = transactionService.returnBook(transactionId);
            return ResponseEntity.ok(ApiResponse.success("Book returned successfully", transaction));
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.error("Error returning book: " + e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error("Error returning book: " + e.getMessage()));
        }
    }
    
    // Get transactions by member
    @GetMapping("/member/{memberId}")
    public ResponseEntity<ApiResponse<List<Transaction>>> getTransactionsByMember(@PathVariable String memberId) {
        try {
            List<Transaction> transactions = transactionService.getTransactionsByMember(memberId);
            return ResponseEntity.ok(ApiResponse.success("Transactions retrieved for member", transactions));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("Error retrieving transactions: " + e.getMessage()));
        }
    }
    
    // Get transactions by book
    @GetMapping("/book/{isbn}")
    public ResponseEntity<ApiResponse<List<Transaction>>> getTransactionsByBook(@PathVariable String isbn) {
        try {
            List<Transaction> transactions = transactionService.getTransactionsByBook(isbn);
            return ResponseEntity.ok(ApiResponse.success("Transactions retrieved for book", transactions));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("Error retrieving transactions: " + e.getMessage()));
        }
    }
    
    // Get active transactions
    @GetMapping("/active")
    public ResponseEntity<ApiResponse<List<Transaction>>> getActiveTransactions() {
        try {
            List<Transaction> transactions = transactionService.getActiveTransactions();
            return ResponseEntity.ok(ApiResponse.success("Active transactions retrieved", transactions));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("Error retrieving transactions: " + e.getMessage()));
        }
    }
    
    // Get overdue transactions
    @GetMapping("/overdue")
    public ResponseEntity<ApiResponse<List<Transaction>>> getOverdueTransactions() {
        try {
            List<Transaction> transactions = transactionService.getOverdueTransactions();
            return ResponseEntity.ok(ApiResponse.success("Overdue transactions retrieved", transactions));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("Error retrieving transactions: " + e.getMessage()));
        }
    }
}

