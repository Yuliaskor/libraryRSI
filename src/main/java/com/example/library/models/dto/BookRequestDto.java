package com.example.library.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class BookRequestDto {
    @JsonProperty
    @Schema(description = "book's title", example = "Potop")
    @NotBlank(message = "Title is required")
    private String title;
    @JsonProperty
    @Schema(description = "book's author", example = "[\"Henryk Sienkiewicz\"]")
    @NotNull(message = "Authors list is required")
    private List<@NotBlank(message = "Author name cannot be blank") String> authors;
    @JsonProperty
    @Schema(description = "book's publisher", example = "PWN")
    @NotBlank(message = "Publisher is required")
    private String publisher;
    @JsonProperty
    @Schema(description = "book's publication year", example = "1886")
    @Positive(message = "Publication year must be a positive number")
    private int publicationYear;

}
