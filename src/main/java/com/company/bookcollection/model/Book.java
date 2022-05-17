package com.company.bookcollection.model;

import java.util.Objects;

public class Book {

  private Long id;
  private String author;
  private String title;
  private Genre genre;
  private Integer rating;

  public Book() {
  }

  public Book(Long id, String author, String title, Genre genre, Integer rating) {
    this.id = id;
    this.author = author;
    this.title = title;
    this.genre = genre;
    this.rating = rating;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Genre getGenre() {
    return genre;
  }

  public void setGenre(Genre genre) {
    this.genre = genre;
  }

  public Integer getRating() {
    return rating;
  }

  public void setRating(Integer rating) {
    this.rating = rating;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Book)) {
      return false;
    }
    final Book book = (Book) o;
    return Objects.equals(id, book.id)
            && Objects.equals(author, book.author)
            && Objects.equals(title, book.title)
            && genre == book.genre
            && rating == book.rating;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, author, title, genre, rating);
  }

  @Override
  public String toString() {
    return "RolePlay{"
            + "id=" + id
            + ", author='" + author + '\''
            + ", title='" + title + '\''
            + ", genre=" + genre
            + ", rating=" + rating
            + '}';
  }
}
