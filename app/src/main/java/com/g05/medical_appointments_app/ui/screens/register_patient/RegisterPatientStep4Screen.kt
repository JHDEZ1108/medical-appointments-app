package com.g05.medical_appointments_app.ui.screens.register_patient

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.g05.medical_appointments_app.ui.components.DotsIndicator
import com.g05.medical_appointments_app.ui.viewmodel.RegisterPatientViewModel

@Composable
fun RegisterPatientStep4Screen(
    navController: NavController,
    viewModel: RegisterPatientViewModel
) {
    val context = LocalContext.current
    val form = viewModel.formData

    val avatar = form.profileImageUri ?: if (form.gender == "Female") {
        "android.resource://${context.packageName}/raw/female_avatar"
    } else {
        "android.resource://${context.packageName}/raw/male_avatar"
    }

    val imageLoader = ImageLoader.Builder(context)
        .components { add(SvgDecoder.Factory()) }
        .build()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Step 4",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Text(
            text = "Confirm Your Info",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))

        AsyncImage(
            model = ImageRequest.Builder(context)
                .data(avatar)
                .build(),
            contentDescription = "Profile Avatar",
            imageLoader = imageLoader,
            modifier = Modifier
                .size(140.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Data Summary
        SummaryItem("First Name", form.firstName)
        SummaryItem("Last Name", form.lastName)
        SummaryItem("Date of Birth", form.dateOfBirth.ifBlank { "Not provided" })
        SummaryItem("Gender", form.gender)
        SummaryItem("Phone", form.phone)
        SummaryItem("Email", form.email)

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier.weight(1f)
            ) {
                Text("Back")
            }

            Spacer(modifier = Modifier.width(16.dp))

            Button(
                onClick = {
                    // TODO: Save form data to database (SQLite or remote backend)
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Create Account")
            }
        }

        Spacer(modifier = Modifier.height(22.dp))

        DotsIndicator(
            totalSteps = 4,
            currentStep = 3,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

@Composable
fun SummaryItem(label: String, value: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Text(text = label, style = MaterialTheme.typography.labelMedium)
        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(bottom = 8.dp)
        )
    }
}
