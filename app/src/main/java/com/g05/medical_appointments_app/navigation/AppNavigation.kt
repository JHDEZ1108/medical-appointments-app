package com.g05.medical_appointments_app.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.g05.medical_appointments_app.ui.screens.AccountTypeSelectionScreen
import com.g05.medical_appointments_app.ui.screens.LoginScreen
import com.g05.medical_appointments_app.ui.screens.register_patient.registerPatientNav
import com.g05.medical_appointments_app.ui.screens.register_professional.registerProfessionalNav
import com.g05.medical_appointments_app.ui.viewmodel.RegisterPatientViewModel
import com.g05.medical_appointments_app.ui.viewmodel.RegisterProfessionalViewModel

@Composable
fun AppNavigation() {
    val navController: NavHostController = rememberNavController()
    val registerPatientViewModel: RegisterPatientViewModel = viewModel()
    val registerProfessionalViewModel: RegisterProfessionalViewModel = viewModel()

    NavHost(navController = navController, startDestination = "login") {
        // Login
        composable("login") {
            LoginScreen(navController)
        }

        // Account type selection (Patient or Healthcare Pro)
        composable("account_type_selection") {
            AccountTypeSelectionScreen(navController)
        }

        // Multi-step patient registration flow
        navigation(
            startDestination = "step1",
            route = "register_patient"
        ) {
            registerPatientNav(
                navController = navController,
                viewModel = registerPatientViewModel
            )
        }

        // Multi-step patient registration flow
        navigation(
            startDestination = "step1",
            route = "register_professional"
        ) {
            registerProfessionalNav(
                navController = navController,
                viewModel = registerProfessionalViewModel
            )
        }

        // TODO: Add professional page
    }
}

