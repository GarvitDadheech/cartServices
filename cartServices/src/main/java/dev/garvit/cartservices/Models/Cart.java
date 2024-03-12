package dev.garvit.cartservices.Models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cart {
    private Long id;
    private Long userId;
    private String date;
    private Product[] Products;
}
