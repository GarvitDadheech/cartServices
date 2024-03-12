package dev.garvit.cartservices.Service;

import dev.garvit.cartservices.Models.Cart;

import java.util.List;

public interface CartService {
    List<Cart> getAllCarts();

    Cart getSingleCart(Long id);

    Cart createCart(Cart cart);
    void updateCart(Cart cart,Long id);
    void deleteCart(Long id);
    List<Cart> getUserCarts(Long id);
}
