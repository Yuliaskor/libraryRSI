package com.example.library.services;

import com.example.library.converters.AuthorConverter;
import com.example.library.exception.AuthorAlreadyExistException;
import com.example.library.models.Author;
import com.example.library.models.dto.AuthorRequestDto;
import com.example.library.models.dto.AuthorResponseDto;
import com.example.library.repository.AuthorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;
    private final AuthorConverter authorConverter;


    public AuthorService(AuthorRepository authorRepository, AuthorConverter authorConverter) {
        this.authorRepository = authorRepository;
        this.authorConverter = authorConverter;
    }

    public List<AuthorResponseDto> getAuthors() {
        return authorRepository.findAll()
                .stream()
                .map(authorConverter::convertToDTO)
                .toList();
    }

    public AuthorResponseDto addAuthor(AuthorRequestDto authorRequestDto) {
        Author author = authorConverter.convertToEntity(authorRequestDto);
        Optional<Author> author2= authorRepository.findByName(authorRequestDto.getName());
        if (author2.isEmpty())  {
            Author author1 = authorRepository.save(author);
            return authorConverter.convertToDTO(author1);
        } else{
            throw new AuthorAlreadyExistException();
        }
    }

    public void deleteAuthor(Integer id) {
        if (authorRepository.existsById(id)) {
            authorRepository.deleteById(id);
        }
    }

    public AuthorResponseDto updateAuthor(int id, AuthorRequestDto authorRequestDto) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Author not found"));
        author.setName(authorRequestDto.getName());
        return authorConverter.convertToDTO(authorRepository.save(author));
    }

    public AuthorResponseDto getAuthor(int id) {
        return authorRepository.findById(id)
                .map(authorConverter::convertToDTO)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Author not found"));

    }
}