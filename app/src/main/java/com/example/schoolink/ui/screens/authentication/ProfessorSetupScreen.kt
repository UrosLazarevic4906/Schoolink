package com.example.schoolink.ui.screens.authentication

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.snippets.components.DatePickerFieldToModal
import com.example.compose.snippets.components.DatePickerModal
import com.example.schoolink.domain.models.Gender
import com.example.schoolink.ui.components.inputs.GenderSelectDropdown
import com.example.schoolink.ui.components.inputs.ImagePicker
import com.example.schoolink.ui.components.inputs.OutlinedInputField
import com.example.schoolink.ui.screens.authentication.components.AuthenticationHeader
import com.example.schoolink.ui.theme.DissabledButton
import com.example.schoolink.ui.theme.SchoolinkTheme
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun ProfessorSetupScreen(
//    viewModel: ProfessorViewModel,
//    onProfileComplete: () -> Unit
    // onBack: () -> Unit
) {

    var profilePictureUri by remember { mutableStateOf<Uri?>(null) }
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var dateOfBirth by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf<Gender?>(null) }

    var selectedDate by remember { mutableStateOf<String?>(null) }

    val focusManager = LocalFocusManager.current

    if (selectedDate != null) {
        val date = Date(selectedDate!!)
        val formattedDate = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(date)
        Text("Selected date: $formattedDate")
    } else {
        Text("No date selected")
    }

    SchoolinkTheme {
        Column(
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
            verticalArrangement = Arrangement.SpaceBetween,
        ) {

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                item {
                    AuthenticationHeader(
                        onBackClick = {},
                        title = "First things first",
                        description = "Upload your photo and tell us your name, gender and when you were born"
                    )
                }

                item {
                    Box(
                        modifier = Modifier
                            .size(150.dp)
                    ) {
                        ImagePicker(
                            imageUri = profilePictureUri,
                            onImagePicked = { selectedUri -> profilePictureUri = selectedUri }
                        )
                    }
                }

                item {
                    OutlinedInputField(
                        value = firstName,
                        onValueChange = { firstName = it },
                        label = "First name"
                    )
                }

                item {
                    OutlinedInputField(
                        value = lastName,
                        onValueChange = { lastName = it },
                        label = "Last name"
                    )
                }

                item {
                    GenderSelectDropdown(
                        selectedGender = gender,
                        onGenderSelected = { gender = it }
                    )
                }

                item {
                    DatePickerFieldToModal(
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = {/* TODO: */ },
                    enabled = false, // TODO: set validity for fields
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary,
                        disabledContainerColor = DissabledButton,
                        disabledContentColor = MaterialTheme.colorScheme.onPrimary
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                ) {
                    Text(text = "Add some students")
                }
            }

        }
    }

}

@Preview(showBackground = true)
@Composable
private fun ProfessorSetupScreenPreview() {
    ProfessorSetupScreen(
//        onProfileComplete = {}
    )
}