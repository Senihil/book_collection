package com.company.bookcollection.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import com.company.bookcollection.model.Genre;
import com.company.bookcollection.model.Book;
import com.company.bookcollection.model.exception.NotFoundException;
import com.company.bookcollection.service.BookService;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BookServiceImplTest {

  private static final Book IT_ENDS_WITH_US = new Book(1L, "Colleen Hoover", "It ends with us", Genre.ROMANCE, 5);
  private static final Book THE_WINNER_STANDS_ALONE = new Book(2L, "Paulo Coelho", "The winner stands alon", Genre.FICTION, 3);
  private static final List<Book> BOOKS = List.of(
          IT_ENDS_WITH_US,
          THE_WINNER_STANDS_ALONE
  );
  public static final long UNKNOWN_BOOK_ID = -1L;
  public static final String SPARKLING_CYANIDE_AUTHOR =  "Agatha Christie";
  public static final String SPARKLING_CYANIDE_TITLE = "Sparkling cyanide";

  private BookService underTest;

  @BeforeEach
  void setUp() {
    underTest = new BookServiceImpl(BOOKS);
  }

  @Test
  void getAllBooksShouldReturnAllBooks() {
    // when
    final List<Book> actual = underTest.getAllBooks();
    // then
    Assertions.assertThat(actual).isEqualTo(BOOKS);
  }

  @Test
  void getBookShouldReturnBookWhenGivenIdOfExistingBook() {
    // when
    final Book actual = underTest.getBook(THE_WINNER_STANDS_ALONE.getId());
    // then
    assertThat(actual).isEqualTo(THE_WINNER_STANDS_ALONE);
  }

  @Test
  void getBookShouldThrowNotFoundExceptionWhenGivenIdOfNotExistingBook() {
    // when then
    assertThrows(NotFoundException.class, () -> underTest.getBook(UNKNOWN_BOOK_ID));
  }

  @Test
  void createBookShouldReturnBookWhenDelegateIt() {
    // given
    final Book cyanideBook = new Book(null, SPARKLING_CYANIDE_AUTHOR, SPARKLING_CYANIDE_TITLE, Genre.THRILLER, 3);
    final Book expectedCyanideBook = new Book(3L, SPARKLING_CYANIDE_AUTHOR, SPARKLING_CYANIDE_TITLE, Genre.THRILLER, 3);
    // when
    final Book actual = underTest.createBook(cyanideBook);
    // then
    assertThat(actual).isEqualTo(expectedCyanideBook);
  }

  @Test
  void updateBookShouldReturnUpdatedBookWhenGivenIdOfExistingBook() {
    // given
    final Book cyanideBook = new Book(null, SPARKLING_CYANIDE_AUTHOR, SPARKLING_CYANIDE_TITLE, Genre.THRILLER, 3);
    final Book expectedCyanideBook = new Book(THE_WINNER_STANDS_ALONE.getId(), SPARKLING_CYANIDE_AUTHOR, SPARKLING_CYANIDE_TITLE, Genre.THRILLER, 3);
    // when
    final Book actual = underTest.updateBook(THE_WINNER_STANDS_ALONE.getId(), cyanideBook);
    // then
    assertThat(actual).isEqualTo(expectedCyanideBook);
  }

  @Test
  void updateBookShouldThrowNotFoundExceptionWhenGivenIdOfNotExistingBook() {
	// given
	final Book cyanideBook = new Book(null, SPARKLING_CYANIDE_AUTHOR, SPARKLING_CYANIDE_TITLE, Genre.THRILLER, 3);
    // when then
    assertThrows(NotFoundException.class, () -> underTest.updateBook(UNKNOWN_BOOK_ID, cyanideBook));
  }

  @Test
  void deleteRolePlayShouldDeleteRolePlayWhenGivenIdOfRolePlay() {
    // given
    final List<Book> expectedBooks = List.of(THE_WINNER_STANDS_ALONE);
    // when
    underTest.deleteBook(IT_ENDS_WITH_US.getId());
    final List<Book> actual = underTest.getAllBooks();
    // then
    Assertions.assertThat(actual).isEqualTo(expectedBooks);
  }
}
