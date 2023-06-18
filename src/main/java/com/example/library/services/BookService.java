package com.example.library.services;

import com.example.library.converters.BookConverter;
import com.example.library.exception.BookAlreadyExistException;
import com.example.library.models.Author;
import com.example.library.models.Book;
import com.example.library.models.dto.BookRequestDto;
import com.example.library.models.dto.BookResponseDto;
import com.example.library.repository.AuthorRepository;
import com.example.library.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final BookConverter bookConverter;

    public List<BookResponseDto> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        if (books == null || books.isEmpty()) {
            return new ArrayList<>();
        }
        return books
                .stream()
                .map(bookConverter::convertToDTO)
                .collect(Collectors.toList());
    }

    public BookResponseDto addBook(BookRequestDto bookRequestDto) {
        Book book = bookConverter.convertToEntity(bookRequestDto);
        var authorNames = bookRequestDto.getAuthors();
        var authors = getOrCreateAuthors(authorNames);

        book.setAuthors(authors);
        Optional<Book> book1= bookRepository.findByTitle(bookRequestDto.getTitle());
        if( book1.isEmpty() ){
            bookRepository.save(book);
            return bookConverter.convertToDTO(book);
        } else {
            throw new BookAlreadyExistException();
        }


    }

    public void deleteBook(Integer id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));

        bookRepository.delete(book);
    }

    public BookResponseDto updateBook(int id, BookRequestDto bookRequestDto) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));
        book.setTitle(bookRequestDto.getTitle());
        book.setPublisher(bookRequestDto.getPublisher());
        book.setPublicationYear(bookRequestDto.getPublicationYear());
        book.setAuthors(getOrCreateAuthors(bookRequestDto.getAuthors()));
        return bookConverter.convertToDTO(bookRepository.save(book));
    }

    private List<Author> getOrCreateAuthors(List<String> authorNames) {
        var authors = new ArrayList<Author>();
        for (String authorName: authorNames) {
            Author author = null;
            Optional<Author> author1= authorRepository.findByName(authorName);
            if (author1.isEmpty()) {
                Author a = new Author();
                a.setName(authorName);
                author = authorRepository.save(a);
            }
            authors.add(author);
        }
        return authors;
    }

    public BookResponseDto getBook(int id) {
        return bookRepository.findById(id)
                .map(bookConverter::convertToDTO)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));
    }
}
