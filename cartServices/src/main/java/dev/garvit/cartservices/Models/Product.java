package dev.garvit.cartservices.Models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
    private Long productId;
    private Long quantity;
    private Long id;
    private String title;
    private String description;
    private double price;
    private Category category;
    private String imageUrl;
}
