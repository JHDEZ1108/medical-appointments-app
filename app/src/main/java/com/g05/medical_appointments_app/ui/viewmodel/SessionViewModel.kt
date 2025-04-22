package com.g05.medical_appointments_app.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.g05.medical_appointments_app.models.UserType

class SessionViewModel : ViewModel() {
    var userType: UserType? = null
        private set

    fun setUserType(type: UserType) {
        userType = type
    }

    fun clearSession() {
        userType = null
    }
}
