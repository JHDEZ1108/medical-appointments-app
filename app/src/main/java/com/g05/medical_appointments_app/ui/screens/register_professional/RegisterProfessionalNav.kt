package com.g05.medical_appointments_app.ui.screens.register_professional

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.g05.medical_appointments_app.ui.viewmodel.RegisterProfessionalViewModel

fun NavGraphBuilder.registerProfessionalNav(
    navController: NavController,
    viewModel: RegisterProfessionalViewModel
) {
    composable(route = "step1") {
        RegisterProfessionalStep1Screen(navController = navController, viewModel = viewModel)
    }

    composable(route = "step2") {
        RegisterProfessionalStep2Screen(navController = navController, viewModel = viewModel)
    }

    composable(route = "step3") {
        RegisterProfessionalStep3Screen(navController = navController, viewModel = viewModel)
    }

    composable(route = "step4") {
        RegisterProfessionalStep4Screen(navController = navController, viewModel = viewModel)
    }
}
