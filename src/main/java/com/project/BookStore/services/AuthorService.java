package com.project.BookStore.services;

import com.project.BookStore.models.Author;
import com.project.BookStore.models.AuthorInput;

import java.util.List;

public interface AuthorService {
    List<Author> getAllAuthors();

    Author getAuthorByBookTitle(String title);

    String createAuthor(AuthorInput authorInput);

    String deleteAuthor(String username);
}
