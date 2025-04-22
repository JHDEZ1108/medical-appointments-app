package com.g05.medical_appointments_app.models

data class ProfessionalFormData(
    val firstName: String = "",
    val lastName: String = "",
    val dateOfBirth: String = "",
    val gender: String = "",

    val phone: String = "",
    val email: String = "",
    val password: String = "",
    val specialty: String = "",

    val profileImageUri: String? = null
)
