package com.rodolfosaturnino.user.microservice.dataacessobject;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DiscountDTO {

	private Double pct;
    private Integer valueInCents;
}
