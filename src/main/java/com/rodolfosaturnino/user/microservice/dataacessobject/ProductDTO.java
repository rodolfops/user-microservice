package com.rodolfosaturnino.user.microservice.dataacessobject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
	
	private Long id;
	private Integer priceInCents;
	private String title;
	private String description;
	private DiscountDTO discount;
}
