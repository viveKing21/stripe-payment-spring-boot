# Stripe Payment Service API

## Overview

This is the documentation for the Stripe Payment Service API version 1. The API provides endpoints for handling payment-related operations using the Stripe payment gateway.

## Base URL

All API endpoints are relative to the base URL: api/v1

# Usage

## 1. Welcome Message

**Endpoint:** `GET /`

**Description:** Returns a welcome message for the Stripe Payment Service API.

## 2. Create Payment Intent

**Endpoint:** `POST /create_intent`

**Description:** Creates a payment intent with the provided payment details.

**Request Body:**
```json
{
  "amount": 1000,
  "currency": "usd",
  "payment_method": "pm_card_visa"
  // Add other payment details as needed
}
```

**Response Body:**
```json
{
  "clientSecret": "your_client_secret_here"
}
```

## 3. Capture Payment Intent

**Endpoint:**  `GET /capture_intent/{paymentIntentId}`

**Description:** Captures the payment intent with the specified ID.

**Path Parameters:**

- `{paymentIntentId}`: The ID of the payment intent to capture.

**Response:**

```json
{
  "amount": 1000,
  "currency": "usd"
}
```

## 3. Refund Payment

**Endpoint:**  `GET /refund/{paymentIntentId}`

**Description:** Refunds the payment associated with the specified payment intent ID.

**Path Parameters:**

- `{paymentIntentId}`: The ID of the payment intent to refund.

**Response:**

```json
{
  "amount": 1000,
  "currency": "usd"
}
```

## 5. Get Payment Intents
**Endpoint:** `GET /get_intents`

**Description:** Retrieves a list of all payment intents.

**Response:**

```json
[
  {
    "id": "pi_1234567890",
    "amount": 1000,
    "currency": "usd"
    // Add other payment intent details as needed
  },
  // Add more payment intents as applicable
]
```

# Configuration

Make sure to configure the Stripe secret key in your application properties:

`stripe.secretKey=your_stripe_secret_key_here`

# Payment Handling UI

## Overview

This UI provides a seamless and secure way to handle payments by leveraging the Stripe Payment Service API. The interface is designed to automate the payment process, eliminating the need to directly pass card details to the SDK. Users can initiate payments through the UI, which will automatically capture and process the payment. The UI provides real-time feedback on the payment status, distinguishing between successful transactions and failed attempts. With a user-friendly design, this UI enhances the payment experience while ensuring the security and efficiency of the payment flow.
