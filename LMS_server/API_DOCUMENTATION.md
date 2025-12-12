# Library Management System - REST API Documentation

## Base URL
```
http://localhost:8080/api
```

## Response Format
All API responses follow this standard format:
```json
{
  "success": true/false,
  "message": "Description of the result",
  "data": { ... }
}
```

---

## 📚 Book Management APIs

### 1. Get All Books
- **Endpoint:** `GET /api/books`
- **Description:** Retrieve all books in the library
- **Response:** List of all books

### 2. Get Book by ISBN
- **Endpoint:** `GET /api/books/{isbn}`
- **Description:** Get details of a specific book
- **Parameters:** `isbn` (path parameter)

### 3. Add New Book
- **Endpoint:** `POST /api/books`
- **Description:** Add a new book to the library
- **Request Body:**
```json
{
  "isbn": "978-0-123456-78-9",
  "title": "Book Title",
  "author": "Author Name",
  "publisher": "Publisher Name",
  "publicationYear": 2024,
  "category": "Fiction",
  "totalCopies": 5,
  "availableCopies": 5
}
```

### 4. Update Book
- **Endpoint:** `PUT /api/books/{isbn}`
- **Description:** Update book details
- **Parameters:** `isbn` (path parameter)
- **Request Body:** Same as Add Book

### 5. Delete Book
- **Endpoint:** `DELETE /api/books/{isbn}`
- **Description:** Remove a book from the library
- **Parameters:** `isbn` (path parameter)

### 6. Search Books by Title
- **Endpoint:** `GET /api/books/search/title/{title}`
- **Description:** Search books by title (partial match)
- **Parameters:** `title` (path parameter)

### 7. Search Books by Author
- **Endpoint:** `GET /api/books/search/author/{author}`
- **Description:** Search books by author name (partial match)
- **Parameters:** `author` (path parameter)

### 8. Search Books by Category
- **Endpoint:** `GET /api/books/search/category/{category}`
- **Description:** Search books by category
- **Parameters:** `category` (path parameter)

### 9. Get Available Books
- **Endpoint:** `GET /api/books/available`
- **Description:** Get all books that are currently available for issue

### 10. Get Books Sorted by Title
- **Endpoint:** `GET /api/books/sorted/title`
- **Description:** Get all books sorted alphabetically by title

---

## 👥 Member Management APIs

### 1. Get All Members
- **Endpoint:** `GET /api/members`
- **Description:** Retrieve all library members

### 2. Get Member by ID
- **Endpoint:** `GET /api/members/{memberId}`
- **Description:** Get details of a specific member
- **Parameters:** `memberId` (path parameter)

### 3. Add New Member
- **Endpoint:** `POST /api/members`
- **Description:** Register a new library member
- **Request Body:**
```json
{
  "memberId": "M001",
  "name": "John Doe",
  "email": "john@example.com",
  "phone": "1234567890",
  "address": "123 Main St",
  "membershipDate": "2024-01-01",
  "memberType": "STUDENT"
}
```
- **Member Types:** `STUDENT`, `FACULTY`, `STAFF`, `PUBLIC`

### 4. Update Member
- **Endpoint:** `PUT /api/members/{memberId}`
- **Description:** Update member details
- **Parameters:** `memberId` (path parameter)
- **Request Body:** Same as Add Member

### 5. Delete Member
- **Endpoint:** `DELETE /api/members/{memberId}`
- **Description:** Remove a member from the system
- **Parameters:** `memberId` (path parameter)

### 6. Deactivate Member
- **Endpoint:** `PATCH /api/members/{memberId}/deactivate`
- **Description:** Deactivate a member account
- **Parameters:** `memberId` (path parameter)

### 7. Activate Member
- **Endpoint:** `PATCH /api/members/{memberId}/activate`
- **Description:** Activate a member account
- **Parameters:** `memberId` (path parameter)

### 8. Get Active Members
- **Endpoint:** `GET /api/members/active`
- **Description:** Get all active members

### 9. Search Members by Name
- **Endpoint:** `GET /api/members/search/name/{name}`
- **Description:** Search members by name (partial match)
- **Parameters:** `name` (path parameter)

### 10. Get Members by Type
- **Endpoint:** `GET /api/members/type/{memberType}`
- **Description:** Get members filtered by type
- **Parameters:** `memberType` (STUDENT, FACULTY, STAFF, PUBLIC)

---

## 📖 Transaction Management APIs (Issue/Return)

### 1. Get All Transactions
- **Endpoint:** `GET /api/transactions`
- **Description:** Retrieve all transactions

### 2. Get Transaction by ID
- **Endpoint:** `GET /api/transactions/{transactionId}`
- **Description:** Get details of a specific transaction
- **Parameters:** `transactionId` (path parameter)

### 3. Issue Book
- **Endpoint:** `POST /api/transactions/issue`
- **Description:** Issue a book to a member
- **Request Body:**
```json
{
  "memberId": "M001",
  "isbn": "978-0-123456-78-9"
}
```

