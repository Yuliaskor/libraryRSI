package com.example.library.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
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
    private String title;
    @JsonProperty
    @Schema(description = "book's author", example = "[\"Henryk Sienkiewicz\"]")
    private List<String> authors;
    @JsonProperty
    @Schema(description = "book's publisher", example = "PWN")
    private String publisher;
    @JsonProperty
    @Schema(description = "book's publication year", example = "1886")
    private int publicationYear;

}
