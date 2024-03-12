package dev.garvit.cartservices.Controllers;

import dev.garvit.cartservices.Models.Cart;
import dev.garvit.cartservices.Models.Product;
import dev.garvit.cartservices.Service.CartService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CartController {

    private CartService cartService;

    public CartController(CartService cartService){
        this.cartService = cartService;
    }
    @GetMapping("/carts")
    public List<Cart> getAllCarts(){
        return cartService.getAllCarts();
    }

    @GetMapping("/carts/{id}")
    public Cart getSingleCart(@PathVariable("id") Long id){
        return cartService.getSingleCart(id);
    }

    @PostMapping("/carts")
    public Cart createCart(@RequestBody Cart cart){
        return cartService.createCart(cart);
    }
    @DeleteMapping("/carts/{id}")
    public void deleteCart(@PathVariable("id") Long id){
        cartService.deleteCart(id);
    }
    @PutMapping("/carts/{id}")
    public void updateCart(@RequestBody Cart cart, @PathVariable("id") Long id){
        cartService.updateCart(cart,id);
    }
    @GetMapping("/carts/user/{id}")
    public List<Cart> getUserCarts(@PathVariable("id") Long id){
        return cartService.getUserCarts(id);
    }


}
