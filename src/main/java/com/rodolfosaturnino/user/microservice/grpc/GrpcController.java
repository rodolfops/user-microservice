package com.rodolfosaturnino.user.microservice.grpc;

import org.springframework.beans.factory.annotation.Autowired;

import com.rodolfosaturnino.user.microservice.service.DiscountService;


//@GRpcService
public class GrpcController {//extends DiscountServiceGrpc.DiscountServiceImplBase {

	@Autowired
	private DiscountService discountService;
	
	public void calculateDiscount(DiscountRequest request, DiscountResponse response) {
		
	}
}
