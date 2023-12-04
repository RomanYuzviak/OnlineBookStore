package com.example.onlinebookstore.dto.order;

import com.example.onlinebookstore.model.Order;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public record OrderUpdateDto (
        @PositiveOrZero
        Long userId,
        @NotBlank
        String shippingAddress,
        @NotBlank
        Order.STATUS status
) {
}
