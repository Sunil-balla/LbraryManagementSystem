package com.example.LibraryManagement.com.LibraryManagement.Services;

import com.example.LibraryManagement.com.LibraryManagement.Models.Book;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookService {
    private final Map<String, Book> bookRepository = new HashMap<>();
    
    // Add a new book
    public Book addBook(Book book) {
        if (bookRepository.containsKey(book.getIsbn())) {
            throw new IllegalArgumentException("Book with ISBN " + book.getIsbn() + " already exists");
        }
        bookRepository.put(book.getIsbn(), book);
        return book;
    }
    
    // Get all books
    public List<Book> getAllBooks() {
        return new ArrayList<>(bookRepository.values());
    }
    
    // Get book by ISBN
    public Book getBookByIsbn(String isbn) {
        Book book = bookRepository.get(isbn);
        if (book == null) {
            throw new NoSuchElementException("Book with ISBN " + isbn + " not found");
        }
        return book;
    }
    
    // Update book
    public Book updateBook(String isbn, Book updatedBook) {
        if (!bookRepository.containsKey(isbn)) {
            throw new NoSuchElementException("Book with ISBN " + isbn + " not found");
        }
        updatedBook.setIsbn(isbn);
        bookRepository.put(isbn, updatedBook);
        return updatedBook;
    }
    
    // Delete book
    public void deleteBook(String isbn) {
        if (!bookRepository.containsKey(isbn)) {
            throw new NoSuchElementException("Book with ISBN " + isbn + " not found");
        }
        bookRepository.remove(isbn);
    }
    
    // Search books by title
    public List<Book> searchByTitle(String title) {
        return bookRepository.values().stream()
                .filter(book -> book.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
    }
    
    // Search books by author
    public List<Book> searchByAuthor(String author) {
        return bookRepository.values().stream()
                .filter(book -> book.getAuthor().toLowerCase().contains(author.toLowerCase()))
                .collect(Collectors.toList());
    }
    
    // Search books by category
    public List<Book> searchByCategory(String category) {
        return bookRepository.values().stream()
                .filter(book -> book.getCategory() != null && 
                        book.getCategory().toLowerCase().contains(category.toLowerCase()))
                .collect(Collectors.toList());
    }
    
    // Get available books
    public List<Book> getAvailableBooks() {
        return bookRepository.values().stream()
                .filter(book -> book.getAvailableCopies() > 0)
                .collect(Collectors.toList());
    }
    
    // Update book availability (for issue/return)
    public void updateAvailability(String isbn, int change) {
        Book book = getBookByIsbn(isbn);
        int newAvailable = book.getAvailableCopies() + change;
        if (newAvailable < 0 || newAvailable > book.getTotalCopies()) {
            throw new IllegalStateException("Invalid availability update");
        }
        book.setAvailableCopies(newAvailable);
    }
    
    // Check if book is available
    public boolean isBookAvailable(String isbn) {
        Book book = getBookByIsbn(isbn);
        return book.getAvailableCopies() > 0;
    }
    
    // Get books sorted by title
    public List<Book> getBooksSortedByTitle() {
        return bookRepository.values().stream()
                .sorted(Comparator.comparing(Book::getTitle))
                .collect(Collectors.toList());
    }
    
    // Get books sorted by author
    public List<Book> getBooksSortedByAuthor() {
        return bookRepository.values().stream()
                .sorted(Comparator.comparing(Book::getAuthor))
                .collect(Collectors.toList());
    }
}

