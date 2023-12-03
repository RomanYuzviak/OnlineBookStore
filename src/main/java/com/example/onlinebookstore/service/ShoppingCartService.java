package com.example.onlinebookstore.service;

import com.example.onlinebookstore.dto.shoppingcart.CartItemRequestDto;
import com.example.onlinebookstore.dto.shoppingcart.ShoppingCartDto;

public interface ShoppingCartService {
    ShoppingCartDto getShoppingCart(Long userId);

    ShoppingCartDto addCartItem(Long userId, CartItemRequestDto cartItemRequestDto);

    ShoppingCartDto updateCartItem(Long cartItemId, Integer quantity);

    void deleteCartItem(Long id);
}
