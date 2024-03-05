package com.project.BookStore.services.impl;

import com.project.BookStore.models.Author;
import com.project.BookStore.models.AuthorInput;
import com.project.BookStore.models.Book;
import com.project.BookStore.models.BookInput;
import com.project.BookStore.models.enums.Category;
import com.project.BookStore.repositories.AuthorRepository;
import com.project.BookStore.repositories.BookRepository;
import com.project.BookStore.services.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    @Override
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Author getAuthorByBookTitle(String title) {
        return bookRepository.findBookByTitle(title)
                .orElseThrow(RuntimeException::new).getAuthor();
    }

    @Override
    public String createAuthor(AuthorInput authorInput) {

        Author author = Author.builder()
                .username(authorInput.getUsername())
                .password(authorInput.getPassword())
                .role(authorInput.getRole())
                .build();

        try {
            authorRepository.save(author);
        }catch (Exception e){
            return "Can't add author with username " + authorInput.getUsername();
        }

        return "Author with username " + authorInput.getUsername() + " was created!";
    }

    @Override
    public String deleteAuthor(String username) {
        try{
            authorRepository.delete(findAuthorByUsername(username));
        }catch (RuntimeException e){
            return "Something went wrong. Author was not deleted!";
        }

        return "Author was successfully deleted!";
    }

    private Author findAuthorByUsername(String username){
        return authorRepository.findAuthorByUsername(username)
                .orElseThrow(RuntimeException::new);
    }
}
