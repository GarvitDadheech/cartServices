package dev.garvit.cartservices.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreCartDto {
    private Long id;
    private Long userId;
    String date;
    FakeStoreProductDto[] fakeStoreProductDtos;
}
