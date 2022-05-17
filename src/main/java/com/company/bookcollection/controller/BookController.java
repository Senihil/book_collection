package com.company.bookcollection.controller;

import com.company.bookcollection.model.Book;
import com.company.bookcollection.model.exception.NotFoundException;
import com.company.bookcollection.service.BookService;

import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/book-collection")
public class BookController {

  private static final String BOOK_LIST_TEMPLATE_NAME = "book/list";
  private static final String BOOK_EDIT_TEMPLATE_NAME = "book/edit";
  private static final String BOOK_ATTRIBUTE_NAME = "book";

  private final BookService bookService;

  public BookController(final BookService bookService) {
    this.bookService = bookService;
  }

  @GetMapping
  public String getAllBooks(final Model model) {
    final List<Book> book = bookService.getAllBooks();
    model.addAttribute("books", book);
    return BOOK_LIST_TEMPLATE_NAME;
  }

  @GetMapping("/{id}")
  public String getBook(final Model model, final @PathVariable Long id) {
    final Book book = bookService.getBook(id);
    model.addAttribute(BOOK_ATTRIBUTE_NAME, book);
    return BOOK_LIST_TEMPLATE_NAME;
  }


  @PostMapping(value = "/update", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  public String updateBook(final Model model,
                               final @RequestParam(value = "id", required = false) Long id,
                               final Book bookChanges) {
    final Book book = bookService.updateBook(id, bookChanges);
    model.addAttribute(BOOK_ATTRIBUTE_NAME, book);
    return BOOK_EDIT_TEMPLATE_NAME;
  }

  @GetMapping("/create")
  public String createBookForm(final Model model) {
    return "book/create";
  }

  @PostMapping("/create")
  public String createBook(final Model model, final Book book) {
    final Book savedBook = bookService.createBook(book);
    model.addAttribute(BOOK_ATTRIBUTE_NAME, savedBook);
    return BOOK_EDIT_TEMPLATE_NAME;
  }

  @GetMapping("/{id}/delete")
  public String deleteBook(final Model model, final @PathVariable("id") Long id) {
    try {
      bookService.deleteBook(id);
    } catch (NotFoundException e) {
      // Ignored
    }
    final List<Book> book = bookService.getAllBooks();
    model.addAttribute("books", book);
    return BOOK_LIST_TEMPLATE_NAME;
  }
}
