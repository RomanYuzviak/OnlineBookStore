package com.example.onlinebookstore.service;

import com.example.onlinebookstore.dto.order.OrderDto;
import com.example.onlinebookstore.dto.order.OrderRequestDto;
import com.example.onlinebookstore.dto.order.OrderUpdateDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    List<OrderDto> findAll(Pageable pageable, Long userId);

    OrderDto save(OrderRequestDto requestDto, Long userId);

    OrderDto save(OrderUpdateDto updateDto, Long userId);

}
