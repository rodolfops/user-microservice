package com.rodolfosaturnino.user.microservice.grpc;

import io.grpc.stub.StreamObserver;

import org.lognet.springboot.grpc.GRpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.rodolfosaturnino.user.microservice.dataacessobject.DiscountDTO;
import com.rodolfosaturnino.user.microservice.service.ProductDiscountService;

@GRpcService
public class GrpcServerImpl extends DiscountServiceGrpc.DiscountServiceImplBase {
	
	private static final Logger log = LoggerFactory.getLogger(GrpcServerImpl.class);

	@Autowired
	private ProductDiscountService productDiscountService;
	
	@Override
	public void calculateDiscount(DiscountRequest request,
	        StreamObserver<DiscountReply> responseObserver) {
		log.info("calculateDiscount");
		String productId = request.getProduct();
		String userId = request.getUser();
		log.info("Product {} and User {} ",productId, userId);
		System.out.println("Product: "+productId);
		System.out.println("User: "+userId);
		DiscountDTO discountDTO = productDiscountService.calculateDiscount(productId, userId);
		log.info("DiscountDTO pct {} and valueInCents {} ",discountDTO.getPct(), discountDTO.getValueInCents());
		responseObserver.onNext(DiscountReply.newBuilder()
				.setPct(discountDTO.getPct())
				.setValueInCents(discountDTO.getValueInCents())
				.build());
		responseObserver.onCompleted();
	}
	
}
