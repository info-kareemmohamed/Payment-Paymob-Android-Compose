package com.example.myapplication.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun PaymentScreen(
    modifier: Modifier = Modifier,
    processLoadingState: Boolean,
    errorMessage: String? = null,
    amount: String,
    currency: String = "EGP",
    onAmountChanged: (String) -> Unit,
    onPayClicked: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = amount,
            onValueChange = onAmountChanged,
            label = { Text("Amount") },
            modifier = Modifier.padding(bottom = 16.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                focusedLabelColor = MaterialTheme.colorScheme.primary
            ),
            singleLine = true,
            trailingIcon = { Text(text = currency, style = MaterialTheme.typography.bodyMedium) },
            leadingIcon = { Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = null) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        errorMessage?.let {
            Text(text = it, color = Color.Red, modifier = Modifier.padding(bottom = 16.dp))
        }

        Button(
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
            onClick = onPayClicked
        ) {
            if (processLoadingState)
                CircularProgressIndicator(color = Color.White)
            else
                Text(text = "Pay Now", color = Color.White, fontWeight = FontWeight.Bold)
        }
    }
}
