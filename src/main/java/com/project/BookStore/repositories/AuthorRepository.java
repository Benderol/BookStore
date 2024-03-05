package com.project.BookStore.repositories;

import com.project.BookStore.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface AuthorRepository extends JpaRepository<Author, Integer> {
    Optional<Author> findAuthorByUsername(String username);
}
