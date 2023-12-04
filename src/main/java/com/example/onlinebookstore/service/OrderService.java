package com.example.onlinebookstore.service;

import com.example.onlinebookstore.dto.order.OrderDto;
import com.example.onlinebookstore.dto.order.OrderRequestDto;
import com.example.onlinebookstore.dto.order.OrderUpdateDto;
import com.example.onlinebookstore.model.Order;
import java.util.List;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    List<OrderDto> findAll(Pageable pageable, Long userId);

    OrderDto save(OrderRequestDto requestDto, Long userId);

    OrderDto save(OrderUpdateDto updateDto, Long userId);

}