package com.example.schoolink.ui.components.inputs

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.example.schoolink.ui.theme.*

@Composable
fun OutlinedInputField(
    value: String,
    label: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label) },
        singleLine = true,
        modifier = modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Text
        ),
        keyboardActions = KeyboardActions.Default,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.secondary,
            unfocusedBorderColor = Smoke,
            focusedLabelColor = Green,
            unfocusedLabelColor = Smoke,
            cursorColor = MaterialTheme.colorScheme.secondary,
            focusedLeadingIconColor = MaterialTheme.colorScheme.secondary,
            focusedTrailingIconColor = MaterialTheme.colorScheme.secondary
        )
    )
}
