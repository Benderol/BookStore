package com.project.BookStore.controllers;

import com.project.BookStore.models.Author;
import com.project.BookStore.models.AuthorInput;
import com.project.BookStore.models.Book;
import com.project.BookStore.models.BookInput;
import com.project.BookStore.models.enums.Category;
import com.project.BookStore.services.AuthorService;
import com.project.BookStore.services.BookService;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor
public class MainController {
    private final AuthorService authorService;
    private final BookService bookService;

    @QueryMapping
    public List<Author> getAllAuthors(){
        return authorService.getAllAuthors();
    }

    @QueryMapping
    public List<Book> getAllBooks(){
        return bookService.getAllBooks();
    }

    @QueryMapping
    public Author getAuthorByBookTitle(@Argument String title){
        try {
            return authorService.getAuthorByBookTitle(title);
        }catch (RuntimeException e){
            return null;
        }
    }

    @QueryMapping
    public List<Book> getAuthorsBooks(@Argument String username){
        return bookService.getAuthorsBooks(username);
    }

    @QueryMapping
    public List<Book> getBooksByCategory(@Argument Category category){
        return bookService.getBooksByCategory(category);
    }

    @MutationMapping
    public String createAuthor(@Argument(name = "author") AuthorInput authorInput){
        return authorService.createAuthor(authorInput);
    }

    @MutationMapping
    public String addBookToAuthor(@Argument String username, @Argument(name = "book") BookInput bookInput){
        return bookService.addBookToAuthor(username, bookInput);
    }

    @MutationMapping
    public String deleteAuthorsBook(@Argument String username, @Argument String title){
        return bookService.deleteBook(username, title);
    }

    @MutationMapping
    public String deleteAuthor(@Argument String username){
        return authorService.deleteAuthor(username);
    }
}
