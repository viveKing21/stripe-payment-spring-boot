package com.stripepayments.services;

import java.util.List;

import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.model.Refund;
import com.stripepayments.dao.PaymentDetails;

public interface StripeService {
	PaymentIntent createPaymentIntent(PaymentDetails paymentDetails) throws StripeException;
	PaymentIntent capturePaymentIntent(String paymentIntentId) throws StripeException;
	Refund refundPayment(String paymentIntentId) throws StripeException;
	List<PaymentIntent> getAllPaymentIntent() throws StripeException;
}
