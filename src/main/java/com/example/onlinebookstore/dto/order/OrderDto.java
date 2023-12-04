package com.example.onlinebookstore.dto.order;

import com.example.onlinebookstore.model.Order;
import com.example.onlinebookstore.model.OrderItem;
import java.time.LocalDateTime;
import java.util.List;

public record OrderDto (
        Long id,
        Long userId,
        List<OrderItem> orderItems,
        LocalDateTime orderDate,
        Long total,
        String shippingAddress,
        Order.STATUS status
) {}
