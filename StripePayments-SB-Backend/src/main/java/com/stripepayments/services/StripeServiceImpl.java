package com.stripepayments.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.model.Refund;
import com.stripe.param.PaymentIntentCaptureParams;
import com.stripe.param.PaymentIntentCreateParams;
import com.stripe.param.PaymentIntentListParams;
import com.stripe.param.RefundCreateParams;
import com.stripepayments.dao.PaymentDetails;

@Service
public class StripeServiceImpl implements StripeService{
	 @Value("${stripe.secretKey}")
	 private String secretKey;

	 @Override
	 public PaymentIntent createPaymentIntent(PaymentDetails paymentDetails) throws StripeException {
		 Stripe.apiKey = secretKey;
		 
		 PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
				 .setAmount(paymentDetails.getAmount().longValue())
				 .setCurrency(paymentDetails.getCurrency().toString())
				 .build();
		 
		 return PaymentIntent.create(params);
	 }

	@Override
	public PaymentIntent capturePaymentIntent(String paymentIntentId) throws StripeException {
		Stripe.apiKey = secretKey;
		
		PaymentIntentCaptureParams params = PaymentIntentCaptureParams.builder().build();
		
		return PaymentIntent.retrieve(paymentIntentId).capture(params);
	}

	@Override
	public List<PaymentIntent> getAllPaymentIntent() throws StripeException {
		Stripe.apiKey = secretKey;
		
		PaymentIntentListParams params = PaymentIntentListParams.builder().build();
		
		return PaymentIntent.list(params).getData();
	}

	@Override
	public Refund refundPayment(String paymentIntentId) throws StripeException {
		Stripe.apiKey = secretKey;
		
		RefundCreateParams params = RefundCreateParams.builder()
				.setPaymentIntent(paymentIntentId)
				.build();
		return Refund.create(params);
	}
}
