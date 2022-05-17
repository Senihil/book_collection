package com.company.bookcollection.controller;

import com.company.bookcollection.model.Book;
import com.company.bookcollection.model.exception.NotFoundException;
import com.company.bookcollection.service.BookService;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/book-collection")
public class BookRestController {

  private final BookService bookService;

  public BookRestController(final BookService bookService) {
    this.bookService = bookService;
  }

  @GetMapping
  public List<Book> getAllBooks() {
    return bookService.getAllBooks();
  }

  @GetMapping("/{id}")
  Book getBook(final @PathVariable Long id) {
    return bookService.getBook(id);
  }

  @PostMapping
  Book createBook(final @RequestBody Book book) {
    return bookService.createBook(book);
  }

  @PutMapping("/{id}")
  Book updateBook(final @PathVariable Long id, final @RequestBody Book bookChange) {
    return bookService.updateBook(id, bookChange);
  }

  @DeleteMapping("/{id}")
  ResponseEntity<Void> deleteBook(final @PathVariable Long id) {
    try {
      bookService.deleteBook(id);
    } catch (NotFoundException e) {
      // Ignored
    }
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
