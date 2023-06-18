package com.example.library.controller;

import com.example.library.models.dto.BookRequestDto;
import com.example.library.models.dto.BookResponseDto;
import com.example.library.services.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/book")
@CrossOrigin("http://localhost:3000")
@Tag(name = "Book Controller")
public class BookController {

    private final BookService service;

    @GetMapping
    @Operation(summary = "Get books", description = "Get all books")
    public ResponseEntity<List<BookResponseDto>> getAllBook(){
        return ResponseEntity.ok(service.getAllBooks());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Add new book", description = "Add new book")
    public ResponseEntity<BookResponseDto> addBook(
            @RequestBody BookRequestDto book
    ) throws URISyntaxException {

        BookResponseDto savedBook = service.addBook(book);
        URI location = new URI("/api/v1/book" + savedBook.getId());
        return ResponseEntity.created(location).body(savedBook);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get book", description = "Get book by id")
    public ResponseEntity<BookResponseDto> getBook(@PathVariable int id) {
        return ResponseEntity.ok(service.getBook(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete book", description = "Delete book by id")
    public ResponseEntity<Void> deleteBook(@PathVariable Integer id) {
        service.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update book", description = "Update book")
    public ResponseEntity<BookResponseDto> updateBook(@PathVariable int id, @RequestBody BookRequestDto book) {
        BookResponseDto bookResponseDto = service.updateBook(id, book);
        return ResponseEntity.ok(bookResponseDto);
    }
}
