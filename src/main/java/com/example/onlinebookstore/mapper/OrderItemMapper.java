package com.example.onlinebookstore.mapper;

import com.example.onlinebookstore.config.MapperConfig;
import com.example.onlinebookstore.model.CartItem;
import com.example.onlinebookstore.model.OrderItem;
import java.util.Set;
import org.mapstruct.Mapper;
import org.springframework.web.bind.annotation.Mapping;

@Mapper(config = MapperConfig.class)
public interface OrderItemMapper {


    OrderItem toOrderItem(CartItem cartItem);
}
