package com.rodolfosaturnino.user.microservice.dataacessobject;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDTO {

	private String id;
    private Integer priceInCents;
    private String title;
    private String description;
    private DiscountDTO discount;
}
