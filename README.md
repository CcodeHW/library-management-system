# Library Management System

## Project Description
A comprehensive library management system built in Java that demonstrates 
object-oriented programming principles. The system manages library items
(books abd magazines) and users, supporting borrowing, returning and searching 
functionality. It implements a clean architecture with proper exception handling,
collections usage and testing.

## Usage Case
Scenario: A student wants to borrow a book from the library

1. Registration: Student "Bob" registers in the system with ID "S100".
2. Search: Student searches for "Harry Potter" by title.
3. Borrow: Student borrows the book with ID "B100".
4. System Actions: 
                - System verifies the book exists and is available
                - System checks the user is registered
                - System updates book status to "borrowed"
                - System sets due date to 2 weeks from now
                - System adds book to user's borrowed items list
5. Return: After 2 weeks, student returns the book, system updates status 
and removes from borrowed list.

## Technical Implementation
1. Inheritance: 
- 'Book extends LibraryItem', 
- 'Magazine extends LibraryItem' 
- 'ItemNotFoundException & UserNotFoundException' extends RuntimeException
2. Abstract Class:
- LibraryItem.java abstract class with abstract method - getDetails()
3. Interface: 
- Borrowable.java Interface with borrow(), returnItem() and getDueDate() contracts
4. Polymorphism:
- App.java lines 42-45: List of LibraryItem containing both Book and Magazine Objects, 
calling getDetails() polymorphically
5. Collection Framework: 
- Library.java: uses HashMao<String,LibraryItem> in LibraryUser
6. JUnit Tests: 
- Tests for borrowing, returning, searching and handling exception etc
7. Comparable Interface: 
- Book.java implements 'Comparable<Book>' for natural ordering by title (case-insensitive)
8. Comparator:
- BookTitleComparator.java: custom comparator for sorting books by title (case-insensitive)
9. Exception: 
- Custom Exceptions: ItemNotFoundException & UserNotFoundException
- Standard Exceptions: IllegalArgumentException, IllegalStateException
10. JavaDoc Documentation:
- Comprehensive Javadoc comments for all classes, methods and constructors
11. Algorithmic Aspect: 
- Library.java search methods: Java Stream API for filtering and searching items by title/author

## UML Class Diagram
![UML Class Diagram](docs/UML_Class_Diagram%20(PStA).png)