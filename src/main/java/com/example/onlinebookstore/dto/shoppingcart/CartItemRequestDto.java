package com.example.onlinebookstore.dto.shoppingcart;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CartItemRequestDto(
        @Positive @NotNull Long bookId,
        @Positive @NotNull Integer quantity) {
}
