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
import com.g05.medical_appointments_app.ui.screens.home.HomePatientScreen
import com.g05.medical_appointments_app.ui.screens.home.HomeProfessionalScreen
import com.g05.medical_appointments_app.ui.screens.register_patient.registerPatientNav
import com.g05.medical_appointments_app.ui.screens.register_professional.registerProfessionalNav
import com.g05.medical_appointments_app.ui.viewmodel.RegisterPatientViewModel
import com.g05.medical_appointments_app.ui.viewmodel.RegisterProfessionalViewModel
import com.g05.medical_appointments_app.ui.viewmodel.SessionViewModel

@Composable
fun AppNavigation() {
    val navController: NavHostController = rememberNavController()

    // ✅ Shared ViewModels
    val registerPatientViewModel: RegisterPatientViewModel = viewModel()
    val registerProfessionalViewModel: RegisterProfessionalViewModel = viewModel()
    val sessionViewModel: SessionViewModel = viewModel()

    NavHost(navController = navController, startDestination = "login") {
        // Login
        composable("login") {
            LoginScreen(navController, sessionViewModel)
        }

        // Account type selection
        composable("account_type_selection") {
            AccountTypeSelectionScreen(navController)
        }

        // Patient registration flow
        navigation(
            startDestination = "step1",
            route = "register_patient"
        ) {
            registerPatientNav(
                navController = navController,
                viewModel = registerPatientViewModel
            )
        }

        // Professional registration flow
        navigation(
            startDestination = "step1",
            route = "register_professional"
        ) {
            registerProfessionalNav(
                navController = navController,
                viewModel = registerProfessionalViewModel
            )
        }

        // Home screens for different user types
        composable("home_patient") {
            HomePatientScreen()
        }

        composable("home_professional") {
            HomeProfessionalScreen()
        }
    }
}

