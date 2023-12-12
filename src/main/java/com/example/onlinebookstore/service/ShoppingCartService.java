package com.example.onlinebookstore.service;

import com.example.onlinebookstore.dto.shoppingcart.CartItemRequestDto;
import com.example.onlinebookstore.dto.shoppingcart.ShoppingCartDto;
import com.example.onlinebookstore.model.User;

public interface ShoppingCartService {
    ShoppingCartDto getShoppingCart(User user);

    ShoppingCartDto addCartItem(User user, CartItemRequestDto cartItemRequestDto);

    ShoppingCartDto updateCartItem(Long cartItemId, Integer quantity);

    void deleteCartItem(Long id);
}
