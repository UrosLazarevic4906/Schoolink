package com.example.schoolink.ui.screens.authentication

import DateOfBirthPicker
import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import com.example.schoolink.domain.models.Gender
import com.example.schoolink.ui.theme.*
import com.example.schoolink.R
import com.example.schoolink.domain.models.ProfessorModel
import com.example.schoolink.ui.viewmodels.ProfessorViewModel
import com.example.schoolink.utils.saveImageToInternalStorage

@Composable
fun ProfessorInputScreen(viewModel: ProfessorViewModel, context: Context) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf(Gender.MALE) }
    var dateOfBirth by remember { mutableStateOf("") }
    var profileUri by remember { mutableStateOf<Uri?>(null) }
    var expanded by remember { mutableStateOf(false) } // State to manage dropdown menu

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        profileUri = uri
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(Cream, CircleShape)
                .clickable { launcher.launch("image/*") }
        ) {
            if (profileUri == null) {
                Image(
                    painter = painterResource(id = R.drawable.ic_user),
                    contentDescription = "Profile Picture Placeholder",
                    modifier = Modifier.size(100.dp),
                    contentScale = ContentScale.Crop
                )
            } else {
                Image(
                    painter = rememberAsyncImagePainter(profileUri),
                    contentDescription = "Selected Profile Picture",
                    modifier = Modifier.size(100.dp),
                    contentScale = ContentScale.Crop
                )
            }
        }

        OutlinedTextField(
            value = firstName,
            onValueChange = { firstName = it },
            label = { Text(text = "First Name") }
        )

        OutlinedTextField(
            value = lastName,
            onValueChange = { lastName = it },
            label = { Text(text = "Last Name") }
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(text = "Email") }
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(text = "Password") }
        )

        // Gender Picker Dropdown
        Box(modifier = Modifier.padding(top = 16.dp)) {
            Text(
                text = "Gender: ${gender.name}",
                modifier = Modifier
                    .clickable { expanded = true }
                    .background(Cream, CircleShape)
                    .padding(8.dp)
            )

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                Gender.entries.forEach { genderOption ->
                    DropdownMenuItem(
                        onClick = {
                            gender = genderOption
                            expanded = false
                        },
                        text = { Text(genderOption.name) }
                    )
                }
            }
        }

        DateOfBirthPicker { selectedDate ->
            dateOfBirth = selectedDate
        }

        Button(onClick = {
            val professor = ProfessorModel(
                email = email,
                password = password,
                profilePicturePath =profileUri?.let { saveImageToInternalStorage(context, it) },
                firstName = firstName,
                lastName = lastName,
                gender = gender,
                dateOfBirth = dateOfBirth
            )
            viewModel.addProfessor(professor)
        }) {
            Text("Save Professor")
        }
    }
}
