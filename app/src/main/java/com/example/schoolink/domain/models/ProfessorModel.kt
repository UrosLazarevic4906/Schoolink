package com.example.schoolink.domain.models

data class ProfessorModel(
    val id: Int = 0,
    val email: String,
    val password: String,
    val profilePicturePath: String?,
    val firstName: String,
    val lastName: String,
    val gender:Gender,
    val dateOfBirth: String
)