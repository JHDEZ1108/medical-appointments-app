package com.g05.medical_appointments_app.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.g05.medical_appointments_app.models.PatientFormData

class RegisterPatientViewModel : ViewModel() {

    // Entire form state lives here
    var formData by mutableStateOf(PatientFormData())
        private set

    fun updateFirstName(value: String) {
        formData = formData.copy(firstName = value)
    }

    fun updateLastName(value: String) {
        formData = formData.copy(lastName = value)
    }

    fun updateGender(value: String) {
        formData = formData.copy(gender = value)
    }

    fun updateDateOfBirth(value: String) {
        formData = formData.copy(dateOfBirth = value)
    }

    fun updatePhone(value: String) {
        formData = formData.copy(phone = value)
    }

    fun updateEmail(value: String) {
        formData = formData.copy(email = value)
    }

    fun updatePassword(value: String) {
        formData = formData.copy(password = value)
    }

    fun updateProfileImage(uri: String?) {
        formData = formData.copy(profileImageUri = uri)
    }
}
