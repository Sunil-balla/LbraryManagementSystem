package com.example.LibraryManagement.com.LibraryManagement.Controllers;

import com.example.LibraryManagement.com.LibraryManagement.Models.ApiResponse;
import com.example.LibraryManagement.com.LibraryManagement.Models.Book;
import com.example.LibraryManagement.com.LibraryManagement.Services.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@CrossOrigin(origins = "*")
public class BookController {
    
    @Autowired
    private BookService bookService;
    
    // Get all books
    @GetMapping
    public ResponseEntity<ApiResponse<List<Book>>> getAllBooks() {
        try {
            List<Book> books = bookService.getAllBooks();
            return ResponseEntity.ok(ApiResponse.success("Books retrieved successfully", books));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("Error retrieving books: " + e.getMessage()));
        }
    }
    
    // Get book by ISBN
    @GetMapping("/{isbn}")
    public ResponseEntity<ApiResponse<Book>> getBookByIsbn(@PathVariable String isbn) {
        try {
            Book book = bookService.getBookByIsbn(isbn);
            return ResponseEntity.ok(ApiResponse.success("Book retrieved successfully", book));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error("Book not found: " + e.getMessage()));
        }
    }
    
    // Add a new book
    @PostMapping
    public ResponseEntity<ApiResponse<Book>> addBook(@Valid @RequestBody Book book) {
        try {
            Book newBook = bookService.addBook(book);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(ApiResponse.success("Book added successfully", newBook));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.error("Error adding book: " + e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("Error adding book: " + e.getMessage()));
        }
    }
    
    // Update a book
    @PutMapping("/{isbn}")
    public ResponseEntity<ApiResponse<Book>> updateBook(@PathVariable String isbn, 
                                                         @Valid @RequestBody Book book) {
        try {
            Book updatedBook = bookService.updateBook(isbn, book);
            return ResponseEntity.ok(ApiResponse.success("Book updated successfully", updatedBook));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error("Error updating book: " + e.getMessage()));
        }
    }
    
    // Delete a book
    @DeleteMapping("/{isbn}")
    public ResponseEntity<ApiResponse<Void>> deleteBook(@PathVariable String isbn) {
        try {
            bookService.deleteBook(isbn);
            return ResponseEntity.ok(ApiResponse.success("Book deleted successfully", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error("Error deleting book: " + e.getMessage()));
        }
    }
    
    // Search books by title
    @GetMapping("/search/title/{title}")
    public ResponseEntity<ApiResponse<List<Book>>> searchByTitle(@PathVariable String title) {
        try {
            List<Book> books = bookService.searchByTitle(title);
            return ResponseEntity.ok(ApiResponse.success("Books found", books));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("Error searching books: " + e.getMessage()));
        }
    }
    
    // Search books by author
    @GetMapping("/search/author/{author}")
    public ResponseEntity<ApiResponse<List<Book>>> searchByAuthor(@PathVariable String author) {
        try {
            List<Book> books = bookService.searchByAuthor(author);
            return ResponseEntity.ok(ApiResponse.success("Books found", books));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("Error searching books: " + e.getMessage()));
        }
    }
    
    // Search books by category
    @GetMapping("/search/category/{category}")
    public ResponseEntity<ApiResponse<List<Book>>> searchByCategory(@PathVariable String category) {
        try {
            List<Book> books = bookService.searchByCategory(category);
            return ResponseEntity.ok(ApiResponse.success("Books found", books));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("Error searching books: " + e.getMessage()));
        }
    }
    
    // Get available books
    @GetMapping("/available")
    public ResponseEntity<ApiResponse<List<Book>>> getAvailableBooks() {
        try {
            List<Book> books = bookService.getAvailableBooks();
            return ResponseEntity.ok(ApiResponse.success("Available books retrieved", books));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("Error retrieving books: " + e.getMessage()));
        }
    }
    
    // Get books sorted by title
    @GetMapping("/sorted/title")
    public ResponseEntity<ApiResponse<List<Book>>> getBooksSortedByTitle() {
        try {
            List<Book> books = bookService.getBooksSortedByTitle();
            return ResponseEntity.ok(ApiResponse.success("Books sorted by title", books));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("Error sorting books: " + e.getMessage()));
        }
    }
}

