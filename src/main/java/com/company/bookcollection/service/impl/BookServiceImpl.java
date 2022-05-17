package com.company.bookcollection.service.impl;

import com.company.bookcollection.model.Book;
import com.company.bookcollection.model.Genre;
import com.company.bookcollection.model.exception.NotFoundException;
import com.company.bookcollection.service.BookService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

  private final List<Book> dataBase = new ArrayList<>();

  @Autowired
  public BookServiceImpl() {
    dataBase.add(new Book(1L, "Colleen Hoover", "It ends with us", Genre.ROMANCE, 5));
    dataBase.add(new Book(2L, "Paulo Coelho", "The winner stands alone", Genre.FICTION, 3));
    dataBase.add(new Book(3L, "Agatha Christie", "Sparkling Cyanide", Genre.THRILLER, 4));
  }

  public BookServiceImpl(final List<Book> books) {
    dataBase.addAll(books);
  }

  @Override
  public List<Book> getAllBooks() {
    return Collections.unmodifiableList(dataBase);
  }

  @Override
  public Book getBook(final Long id) {
    return dataBase.stream()
            .filter(book -> book.getId().equals(id))
            .findFirst()
            .orElseThrow(NotFoundException::new);
  }

  @Override
  public Book createBook(final Book book) {
    book.setId(getNextId());
    dataBase.add(book);
    return book;
  }

  @Override
  public Book updateBook(final Long id, final Book bookChange) {
    final Book book = getBook(id);
    book.setAuthor(bookChange.getAuthor());
    book.setTitle(bookChange.getTitle());
    book.setGenre(bookChange.getGenre());
    book.setRating(bookChange.getRating());
    return book;
  }

  @Override
  public void deleteBook(final Long id) {
    final Book book = getBook(id);
    dataBase.remove(book);
  }

  private long getNextId() {
    return getLastId() + 1L;
  }

  private long getLastId() {
    return dataBase.stream()
            .mapToLong(Book::getId)
            .max()
            .orElse(0);
  }
}
