package com.g05.medical_appointments_app.ui.screens.register_patient

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.g05.medical_appointments_app.ui.viewmodel.RegisterPatientViewModel

fun NavGraphBuilder.registerPatientNav(
    navController: NavController,
    viewModel: RegisterPatientViewModel
) {
    composable("step1") {
        RegisterPatientStep1Screen(navController = navController, viewModel = viewModel)
    }
    composable("step2") {
        RegisterPatientStep2Screen(navController = navController, viewModel = viewModel)
    }
    composable("step3") {
        RegisterPatientStep3Screen(navController = navController, viewModel = viewModel)
    }
    composable("step4") {
        RegisterPatientStep4Screen(navController = navController, viewModel = viewModel)
    }
}
