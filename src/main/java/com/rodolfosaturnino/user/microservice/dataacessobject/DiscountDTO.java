package com.rodolfosaturnino.user.microservice.dataacessobject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiscountDTO {

	private Double pct;
    private Integer valueInCents;
}
