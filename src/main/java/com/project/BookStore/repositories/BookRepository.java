package com.project.BookStore.repositories;

import com.project.BookStore.models.Author;
import com.project.BookStore.models.Book;
import com.project.BookStore.models.enums.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Integer> {
    Optional<Book> findBookByTitle(String title);

    List<Book> findBookByCategory(Category category);

    void deleteByTitleAndAuthor(String title, Author author);
}
