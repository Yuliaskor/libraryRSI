package com.example.library.models.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthorResponseDto {

    @Schema(description = "author's id", example = "185218")
    private Long id;

    @Schema(description = "author's name", example = "Henryk Sienkiewicz")
    private String name;
}
