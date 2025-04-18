package com.g05.medical_appointments_app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.g05.medical_appointments_app.ui.screens.LoginScreen
import com.g05.medical_appointments_app.ui.screens.AccountTypeSelectionScreen

@Composable
fun AppNavigation() {
    val navController: NavHostController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {
        // Login screen
        composable("login") {
            LoginScreen(navController)
        }

        // Account type selection screen (Patient or Professional)
        composable("account_type_selection") {
            AccountTypeSelectionScreen(navController)
        }

        // TODO: Add registration screens for patient and professional
        // composable("register_patient") { RegisterPatientScreen(navController) }
        // composable("register_professional") { RegisterProfessionalScreen(navController) }
    }
}
