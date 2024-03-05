package com.project.BookStore.services;

import com.project.BookStore.models.Book;
import com.project.BookStore.models.BookInput;
import com.project.BookStore.models.enums.Category;

import java.util.List;

public interface BookService {
    List<Book> getBooksByCategory(Category category);

    List<Book> getAllBooks();

    String deleteBook(String username, String title);

    List<Book> getAuthorsBooks(String username);

    String addBookToAuthor(String username, BookInput bookInput);
}
