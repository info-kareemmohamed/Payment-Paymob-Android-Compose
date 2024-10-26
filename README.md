<h1 align="center"> Paymob Integration in Android using the Mobile SDKs </h1>

<p align="center"> 💰 Paymob Integration in Android using the new method, which is the Mobile SDKs to make it easier for developers to use Paymob functionalities in their applications after this project you can pay with visa or master card or wallets (Vodafone Cash, Orange Cash, Etisalat Cash) in your android app. </p>


## Preview 📱


[preview.webm](https://github.com/user-attachments/assets/652b53de-4547-4028-ab5a-42421a4e1565)




## Manual Installation

Reference: [Paymob integration mobile sdks v2](https://developers.paymob.com/egypt/mobile-sdks/mobile-sdks-v2/android-sdk-2)

## Usage
- Set Paymob credentials
```kotlin
     const val PUBLIC_KEY = "your_public_key_here"
     const val BASE_URL = "https://accept.paymob.com/"
     const val SECRET_KEY = "your_secret_key_here"
     const val ONLINE_CARD_PAYMENT_METHOD_ID = "your_payment_methods_id_here"
```

- PUBLIC_KEY: Replace with the public key provided by Paymob.
- SECRET_KEY: Replace with your Paymob secret key.
- ONLINE_CARD_PAYMENT_METHOD_ID: This ID represents the payment method you want to enable, such as online card payments. You can add multiple IDs to a list to support various payment methods in your app.

## Making an API Call to Retrieve the Client Secret

- To retrieve the clientSecret, use the API call below with your SECRET_KEY in the headers. Here’s the setup:

```kotlin

@Headers("Authorization: Token $SECRET_KEY")
@POST("v1/intention/")
suspend fun getClientSecret(@Body paymentRequest: PaymentRequest): PaymentResponse

```

 - This function triggers a payment request. Here’s an example of how it can be implemented:

```kotlin

override fun getClientSecret(
    amount: Int,
    currency: String
): Flow<ClientSecretResult<String>> = flow {
    emit(ClientSecretResult.Loading())
    val clientSecret = api.getClientSecret(
        PaymentRequest(
            amount = amount * 100, // Multiply by 100 to handle amount in cents
            currency = currency,
            payment_methods = listOf(ONLINE_CARD_PAYMENT_METHOD_ID), // Add all Payment Method IDs as needed
            items = listOf(Item("Item name 1", amount * 100, "Item description 1", 1)),
            billing_data = BillingData(
                apartment = "example",
                first_name = "John",
                last_name = "Doe",
                street = "Main St",
                building = "123",
                phone_number = "+201234567890",
                city = "Cairo",
                country = "EG",
                email = "john.doe@example.com",
                floor = "1",
                state = "Cairo"
            ),
            customer = Customer(
                first_name = "John",
                last_name = "Doe",
                email = "john.doe@example.com",
                extras = mapOf("reference" to "12345")
            ),
            extras = mapOf("extra_param" to 123)
        )
    ).client_secret
    emit(ClientSecretResult.Success(clientSecret))
}.catch { e -> emit(ClientSecretResult.Error(e.message ?: "Unknown error")) }


```



## Setting the clientSecret in PaymobSdk.Builder

- After retrieving the clientSecret, you can use it and your public key in the PaymobSdk.Builder to initialize the SDK for handling transactions, enabling you to process payments within your app seamlessly.


```kotlin

PaymobSdk.Builder(
context = this@MainActivity,
clientSecret = “CLIENT_SECRET”,//Place Client Secret here
publicKey = “PUBLIC_KEY”,//Place Public Key here
paymobSdkListener = this,
)
.setButtonBackgroundColor(Color.BLACK)//changes the color of button backgrounds throughout the SDK, and set by default to black
.setButtonTextColor(Color.WHITE)//changes the color of button texts throughout the SDK, and set by default to white
.isAbleToSaveCard(true) //changes the ability for the sdk to save the card info or no
.isSavedCardCheckBoxCheckedByDefault(true) //changes the ability for the sdk if the save card checkbox is checked ot not
.build()
.start()

```




