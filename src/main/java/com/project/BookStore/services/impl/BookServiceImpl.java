package com.project.BookStore.services.impl;

import com.project.BookStore.models.Author;
import com.project.BookStore.models.Book;
import com.project.BookStore.models.BookInput;
import com.project.BookStore.models.enums.Category;
import com.project.BookStore.repositories.AuthorRepository;
import com.project.BookStore.repositories.BookRepository;
import com.project.BookStore.services.BookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Override
    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    @Override
    public List<Book> getBooksByCategory(Category category) {
        return bookRepository.findBookByCategory(category);
    }

    @Override
    public List<Book> getAuthorsBooks(String username) {
        return findAuthorByUsername(username).getBooks();
    }

    @Override
    public String addBookToAuthor(String username, BookInput bookInput) {
        Author author = findAuthorByUsername(username);
        Book book = Book.builder()
                .title(bookInput.getTitle())
                .category(bookInput.getCategory())
                .author(author)
                .build();

        try{
            //Reassigned to put into author with ID
            book = bookRepository.save(book);
        }catch (Exception e){
            return "Cant add book to author with username " + username;
        }

        author.getBooks().add(book);
        authorRepository.save(author);

        return "Book was added to author with username " + username;
    }

    @Override
    public String deleteBook(String username, String title) {
        if(checkIfAuthorHasBookAndDeleteIfPresent(username, title)){
            return "Book was successfully deleted!";
        }
        return "Something went wrong. Book was not deleted!";
    }

    private boolean checkIfAuthorHasBookAndDeleteIfPresent(String username, String title){
        Author author;
        Book book;
        try {
            author = findAuthorByUsername(username);
            book = findBookByTitle(title);
        }catch (RuntimeException e){
            return false;
        }

        if(author.getBooks().contains(book)){
            bookRepository.delete(book);
            author.getBooks().remove(book);
            authorRepository.save(author);
            return true;
        }

        return false;
    }

    private Book findBookByTitle(String title){
        return bookRepository.findBookByTitle(title)
                .orElseThrow(RuntimeException::new);
    }

    private Author findAuthorByUsername(String username){
        return authorRepository.findAuthorByUsername(username)
                .orElseThrow(RuntimeException::new);
    }
}
