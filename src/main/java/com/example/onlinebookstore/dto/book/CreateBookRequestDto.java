package com.example.onlinebookstore.dto.book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.util.Set;

public record CreateBookRequestDto(
        @NotBlank
        String title,
        @NotBlank
        String author,
        @NotBlank
        String isbn,
        @PositiveOrZero
        BigDecimal price,
        String description,
        String coverImage,
        Set<Long> categoryIdSet
) {
}
