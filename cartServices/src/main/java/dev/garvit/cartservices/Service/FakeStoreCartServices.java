package dev.garvit.cartservices.Service;

import dev.garvit.cartservices.Models.Cart;
import dev.garvit.cartservices.Models.Category;
import dev.garvit.cartservices.Models.Product;
import dev.garvit.cartservices.dtos.FakeStoreCartDto;
import dev.garvit.cartservices.dtos.FakeStoreCategoryDto;
import dev.garvit.cartservices.dtos.FakeStoreProductDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreCartServices implements CartService {

    private RestTemplate restTemplate = new RestTemplate();

    public List<Cart> getAllCarts() {
        FakeStoreCartDto[] cartArray = restTemplate.getForObject(
                "https://fakestoreapi.com/carts",
                FakeStoreCartDto[].class
        );
        ArrayList<Cart> arrayOfCarts = new ArrayList<Cart>();
        for (int i = 0; i < cartArray.length; i++) {
            FakeStoreCartDto temp = cartArray[i];
            Cart newCart = new Cart();
            newCart.setId(temp.getId());
            newCart.setDate(temp.getDate());
            newCart.setUserId(temp.getUserId());
            newCart.setProducts(new Product[2]);
            newCart.getProducts()[0].setProductId(temp.getFakeStoreProductDtos()[0].getProductId());
            newCart.getProducts()[1].setQuantity(temp.getFakeStoreProductDtos()[1].getQuantity());
            arrayOfCarts.add(newCart);
        }
        return arrayOfCarts;
    }

    public Cart getSingleCart(Long id) {
        FakeStoreCartDto fakeStoreCartDto = restTemplate.getForObject(
                "https://fakestoreapi.com/carts/" + id,
                FakeStoreCartDto.class
        );
        Cart cart = new Cart();
        cart.setId(fakeStoreCartDto.getId());
        cart.setDate(fakeStoreCartDto.getDate());
        cart.setUserId(fakeStoreCartDto.getUserId());
        cart.setProducts(new Product[2]);
        cart.getProducts()[0].setProductId(fakeStoreCartDto.getFakeStoreProductDtos()[0].getProductId());
        cart.getProducts()[1].setQuantity(fakeStoreCartDto.getFakeStoreProductDtos()[1].getQuantity());
        return cart;
    }

    public void deleteCart(Long id) {
        restTemplate.delete(
                "https://fakestoreapi.com/carts/" + id
        );
    }

    public Cart createCart(Cart cart) {
        FakeStoreCartDto temp = new FakeStoreCartDto();
        temp.setDate(cart.getDate());
        temp.setId(cart.getId());
        temp.setUserId(cart.getUserId());
        temp.getFakeStoreProductDtos()[0].setProductId(cart.getProducts()[0].getProductId());
        temp.getFakeStoreProductDtos()[0].setQuantity(cart.getProducts()[1].getQuantity());
        FakeStoreCartDto fakeStoreCartDto = restTemplate.postForObject(
                "https://fakestoreapi.com/carts",
                temp,
                FakeStoreCartDto.class
        );
        return cart;
    }

    @Override
    public void updateCart(Cart cart, Long id) {
        FakeStoreCartDto temp = new FakeStoreCartDto();
        temp.setId(cart.getId());
        temp.setDate(cart.getDate());
        temp.setUserId(cart.getUserId());
        temp.getFakeStoreProductDtos()[0].setProductId(cart.getProducts()[0].getProductId());
        temp.getFakeStoreProductDtos()[0].setQuantity(cart.getProducts()[1].getQuantity());
        restTemplate.put(
                "https://fakestoreapi.com/carts/" + id,
                temp,
                FakeStoreCartDto.class
        );
    }

    public List<Cart> getUserCarts(Long id) {
        FakeStoreCartDto[] cartArray = restTemplate.getForObject(
                "https://fakestoreapi.com/carts/user/" + id,
                FakeStoreCartDto[].class
        );
        ArrayList<Cart> arrayOfCarts = new ArrayList<Cart>();
        for (int i = 0; i < cartArray.length; i++) {
            FakeStoreCartDto fakeStoreCartDto = cartArray[i];
            if (fakeStoreCartDto.getUserId() == id) {
                Cart cart = new Cart();
                cart.setId(fakeStoreCartDto.getId());
                cart.setDate(fakeStoreCartDto.getDate());
                cart.setUserId(fakeStoreCartDto.getUserId());
                    cart.setProducts(new Product[2]);
                    cart.getProducts()[0].setProductId(fakeStoreCartDto.getFakeStoreProductDtos()[0].getProductId());
                    cart.getProducts()[1].setQuantity(fakeStoreCartDto.getFakeStoreProductDtos()[1].getQuantity());
                    arrayOfCarts.add(cart);
            }
        }
        return arrayOfCarts;
    }
}
