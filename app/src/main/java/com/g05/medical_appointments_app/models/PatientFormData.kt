package com.g05.medical_appointments_app.models

data class PatientFormData(
    val firstName: String = "",
    val lastName: String = "",
    val gender: String = "",
    val dateOfBirth: String = "",
    val phone: String = "",
    val email: String = "",
    val password: String = "",
    val profileImageUri: String? = null
)
