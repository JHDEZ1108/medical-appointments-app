package com.g05.medical_appointments_app.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.g05.medical_appointments_app.models.ProfessionalFormData

class RegisterProfessionalViewModel : ViewModel() {

    // Holds all form data across the registration steps
    var formData by mutableStateOf(ProfessionalFormData())
        private set

    // Step 1: Personal Info
    fun updateFirstName(value: String) {
        formData = formData.copy(firstName = value)
    }

    fun updateLastName(value: String) {
        formData = formData.copy(lastName = value)
    }

    fun updateDateOfBirth(value: String) {
        formData = formData.copy(dateOfBirth = value)
    }

    fun updateGender(value: String) {
        formData = formData.copy(gender = value)
    }

    // Step 2: Contact + Auth Info
    fun updatePhone(value: String) {
        formData = formData.copy(phone = value)
    }

    fun updateEmail(value: String) {
        formData = formData.copy(email = value)
    }

    fun updatePassword(value: String) {
        formData = formData.copy(password = value)
    }

    fun updateSpecialty(value: String) {
        formData = formData.copy(specialty = value)
    }

    // Step 3: Profile Photo
    fun updateProfileImage(uri: String) {
        formData = formData.copy(profileImageUri = uri)
    }

    // Reset the form after account creation if needed
    fun clearForm() {
        formData = ProfessionalFormData()
    }
}
