package com.example.library.controller;

import com.example.library.models.dto.AuthorRequestDto;
import com.example.library.models.dto.AuthorResponseDto;
import com.example.library.services.AuthorService;
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
@RequestMapping("/api/v1/authors")
@Tag(name = "Authors Controller")
@CrossOrigin("http://localhost:3000")
public class AuthorController {

    private final AuthorService service;

    @GetMapping
    @Operation(summary = "Get authors", description = "Get all authors")
    public ResponseEntity<List<AuthorResponseDto>> getAuthors(){
        return ResponseEntity.ok(service.getAuthors());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Add new author", description = "Add new author")
    public ResponseEntity<AuthorResponseDto> addAuthor(
            @RequestBody AuthorRequestDto author
    ) throws URISyntaxException {

        AuthorResponseDto savedAuthor = service.addAuthor(author);
        URI location = new URI("/api/v1/authors" + savedAuthor.getId());
        return ResponseEntity.created(location).body(savedAuthor);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete author", description = "Delete author by id")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Integer id) {
        service.deleteAuthor(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update author", description = "Update author")
    public ResponseEntity<AuthorResponseDto> updateAuthor(@PathVariable int id,
                                                          @RequestBody AuthorRequestDto author) {
        AuthorResponseDto authorResponseDto = service.updateAuthor(id, author);
        return ResponseEntity.ok(authorResponseDto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get author", description = "Get author by id")
    public ResponseEntity<AuthorResponseDto> getAuthor(@PathVariable int id) {
        return ResponseEntity.ok(service.getAuthor(id));
    }
}
