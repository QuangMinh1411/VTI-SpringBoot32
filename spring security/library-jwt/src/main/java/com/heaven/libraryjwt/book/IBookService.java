package com.heaven.libraryjwt.book;

import java.util.List;
import java.util.Optional;

public interface IBookService {
    List<Book> getAllBooks();
    Book add(Book book);
    Optional<Book> findById(Long bookId);
    void delete(Long bookId);
    Book update(Long id,Book book);
}
