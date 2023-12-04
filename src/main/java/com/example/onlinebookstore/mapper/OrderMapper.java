package com.example.onlinebookstore.mapper;

import com.example.onlinebookstore.config.MapperConfig;
import com.example.onlinebookstore.dto.category.CategoryDto;
import com.example.onlinebookstore.dto.category.CreateCategoryRequestDto;
import com.example.onlinebookstore.dto.order.OrderDto;
import com.example.onlinebookstore.dto.order.OrderRequestDto;
import com.example.onlinebookstore.dto.order.OrderUpdateDto;
import com.example.onlinebookstore.model.Category;
import com.example.onlinebookstore.model.Order;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface OrderMapper {
    OrderDto toDto(Order order);

    Order toOrder(OrderRequestDto requestDtoquestDto);

    Order toOrder(OrderUpdateDto updateDto);
}
