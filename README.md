Stripe Payment Service API
Overview
This is the documentation for the Stripe Payment Service API version 1. The API provides endpoints for handling payment-related operations using the Stripe payment gateway.

Base URL
All API endpoints are relative to the base URL:

bash
Copy code
/api/v1
Endpoints
1. Welcome Message
Endpoint: GET /
Description: Returns a welcome message for the Stripe Payment Service API.
2. Create Payment Intent
Endpoint: POST /create_intent
Description: Creates a payment intent with the provided payment details.
Request Body: Expects a JSON object with payment details.
json
Copy code
{
  "amount": 1000,
  "currency": "usd",
  "payment_method": "pm_card_visa"
  // Add other payment details as needed
}
Response: Returns a JSON object with the client secret.
json
Copy code
{
  "clientSecret": "your_client_secret_here"
}
3. Capture Payment Intent
Endpoint: GET /capture_intent/{paymentIntentId}

Description: Captures the payment intent with the specified ID.

Path Parameters:

{paymentIntentId}: The ID of the payment intent to capture.
Response: Returns a JSON object with the captured amount and currency.

json
Copy code
{
  "amount": 1000,
  "currency": "usd"
}
4. Refund Payment
Endpoint: GET /refund/{paymentIntentId}

Description: Refunds the payment associated with the specified payment intent ID.

Path Parameters:

{paymentIntentId}: The ID of the payment intent to refund.
Response: Returns a JSON object with the refunded amount and refund status.

json
Copy code
{
  "amount": 1000,
  "status": "succeeded"
}
5. Get Payment Intents
Endpoint: GET /get_intents

Description: Retrieves a list of all payment intents.

Response: Returns a JSON array containing payment intents.

json
Copy code
[
  {
    "id": "pi_1234567890",
    "amount": 1000,
    "currency": "usd",
    // Add other payment intent details as needed
  },
  // Add more payment intents as applicable
]
6. Error Handling
Response: In case of an error, the API will return a JSON object with an error message.
json
Copy code
{
  "error": "error_message_here"
}
Configuration
Make sure to configure the Stripe secret key in your application properties:

properties
Copy code
stripe.secretKey=your_stripe_secret_key_here
Exception Handling
The API handles StripeException and generic Exception types. In case of an error, an internal server error response will be returned with an error message.

Feel free to reach out for any further assistance or clarifications.
