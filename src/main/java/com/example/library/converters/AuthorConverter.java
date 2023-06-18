package com.example.library.converters;

import com.example.library.models.Author;
import com.example.library.models.dto.AuthorRequestDto;
import com.example.library.models.dto.AuthorResponseDto;
import org.springframework.stereotype.Component;

@Component
public class AuthorConverter {

    public AuthorResponseDto convertToDTO(Author author) {
        AuthorResponseDto authorDTO = new AuthorResponseDto();
        authorDTO.setId(author.getId());
        authorDTO.setName(author.getName());
        return authorDTO;
    }

    public Author convertToEntity(AuthorRequestDto authorRequestDto) {
        Author author = new Author();
        author.setName(authorRequestDto.getName());
        return author;
    }
}
