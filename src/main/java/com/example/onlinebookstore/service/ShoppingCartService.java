package com.example.onlinebookstore.service;

import com.example.onlinebookstore.dto.shoppingcart.CartItemDto;
import com.example.onlinebookstore.dto.shoppingcart.ShoppingCartDto;

public interface ShoppingCartService {
    ShoppingCartDto getShoppingCart(Long userId);

    ShoppingCartDto addCartItem(Long userId, CartItemDto cartItemDto);

    ShoppingCartDto updateCartItem(Long cartItemId, Integer quantity);

    void deleteCartItem(Long id);
}
