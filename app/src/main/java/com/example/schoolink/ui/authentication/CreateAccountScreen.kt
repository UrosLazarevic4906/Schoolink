package com.example.schoolink.ui.authentication

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.schoolink.ui.authentication.components.AuthenticationHeader
import com.example.schoolink.ui.components.InteractionText
import com.example.schoolink.ui.components.inputs.ConfirmPasswordInputField
import com.example.schoolink.ui.components.inputs.EmailInputField
import com.example.schoolink.ui.components.inputs.PasswordInputField
import com.example.schoolink.ui.theme.SchoolinkTheme

@Composable
fun CreateAccountScreen() {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    val focusManager = LocalFocusManager.current

    SchoolinkTheme {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .imePadding()
                .padding(horizontal = 24.dp, vertical = 32.dp)
                .clickable(
                    onClick = { focusManager.clearFocus() },
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ),
            verticalArrangement = Arrangement.SpaceBetween
        ){
            LazyColumn (
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
               item {
                   AuthenticationHeader(
                       onBackClick = { /* ToDo: handle on back click */},
                       title = "Create an account",
                       description = "We’ll send you a verification code to your email in order to verify your account."
                   )
               }

                item {
                    EmailInputField(value = email, onValueChange = { email = it })
                }

                item {
                    PasswordInputField(value = password, onValueChange = { password = it})
                }

                item {
                    ConfirmPasswordInputField(
                        password = password,
                        confirmPassword = confirmPassword,
                        onConfirmPasswordChange = { confirmPassword = it }
                    )
                }

            }

            Column (
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Button(
                    onClick = { /* ToDo: Handle create account click */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    enabled = confirmPassword == password && password.isNotEmpty(),
                ) {
                    Text(text = "Create account")
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(text = "By tapping on \"Create account\" you agree to our ", color = MaterialTheme.colorScheme.onBackground)
                    InteractionText(
                        text = "Terms & Conditions",
                        onClick = { /* TODO: Handle Terms & Conditions click */ }
                    )
                    Text(text = " and ", color = MaterialTheme.colorScheme.onBackground)
                    InteractionText(
                        text = "Privacy Policy.",
                        onClick = { /* TODO: Handle Privacy Policy click */ }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun CreateAccountScreenPreview() {
    CreateAccountScreen()
}