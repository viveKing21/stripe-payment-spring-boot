package com.stripepayments.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.model.Refund;
import com.stripepayments.dao.PaymentDetails;
import com.stripepayments.services.StripeService;


@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1")
public class StripController {
	 @Value("${stripe.secretKey}")
	 private String secretKey;
	
	@Autowired
	StripeService stripeService;
	
	@GetMapping
	ResponseEntity<String> welcomeMessage(){
		return ResponseEntity.ok("Welcome to Stripe Payment Service API v1");
	}
	
	@PostMapping("/create_intent")
	ResponseEntity<Object> createPaymentIntent(@RequestBody PaymentDetails paymentDetails) throws StripeException{
		PaymentIntent paymentIntent = stripeService.createPaymentIntent(paymentDetails);
		return ResponseEntity.ok(Map.of("clientSecret", paymentIntent.getClientSecret()));
	}
	@GetMapping("/capture_intent/{paymentIntentId}")
	public ResponseEntity<Object> capturePaymentIntent(@PathVariable String paymentIntentId) throws StripeException {
		PaymentIntent paymentIntent = stripeService.capturePaymentIntent(paymentIntentId);
        return ResponseEntity.ok(Map.of("amount", paymentIntent.getAmount(), "currency", paymentIntent.getCurrency()));
	}
	@GetMapping("/refund/{paymentIntentId}")
	public ResponseEntity<Object> refundPayment(@PathVariable String paymentIntentId) throws StripeException{
		Refund refund = stripeService.refundPayment(paymentIntentId);
		return ResponseEntity.ok(Map.of("amount", refund.getAmount(), "status", refund.getStatus()));
	}
	@GetMapping("/get_intents")
	public ResponseEntity<Object> getPaymentIntents() throws StripeException{
		return ResponseEntity.ok(stripeService.getAllPaymentIntent());
	}

	
	@ExceptionHandler({StripeException.class, Exception.class})
	ResponseEntity<Object> errorHandle(Exception exception){
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", exception.getMessage()));
	}
	
}
