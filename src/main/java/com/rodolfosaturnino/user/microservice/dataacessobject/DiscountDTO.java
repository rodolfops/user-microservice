package com.rodolfosaturnino.user.microservice.dataacessobject;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DiscountDTO {

	private Double price;
    private Integer valueInCents;
}
