package com.g05.medical_appointments_app.ui.screens.register_professional

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
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.g05.medical_appointments_app.ui.components.DotsIndicator
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.g05.medical_appointments_app.ui.viewmodel.RegisterProfessionalViewModel

@Composable
fun RegisterProfessionalStep4Screen(
    navController: NavController,
    viewModel: RegisterProfessionalViewModel
) {
    val context = LocalContext.current
    val form = viewModel.formData

    val imageLoader = ImageLoader.Builder(context)
        .components { add(SvgDecoder.Factory()) }
        .build()

    val avatar = form.profileImageUri ?: if (form.gender == "Female") {
        "android.resource://${context.packageName}/raw/female_avatar"
    } else {
        "android.resource://${context.packageName}/raw/male_avatar"
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
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

        Spacer(modifier = Modifier.height(20.dp))

        AsyncImage(
            model = ImageRequest.Builder(context)
                .data(avatar)
                .build(),
            imageLoader = imageLoader,
            contentDescription = "Final Avatar",
            modifier = Modifier
                .size(140.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Summary of data
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            SummaryItem(label = "Name", value = "${form.firstName} ${form.lastName}")
            SummaryItem(label = "Email", value = form.email)
            SummaryItem(label = "Phone", value = form.phone)
            SummaryItem(label = "Gender", value = form.gender)
            SummaryItem(label = "Birthdate", value = if (form.dateOfBirth.isNotBlank()) form.dateOfBirth else "Not provided")
            SummaryItem(label = "Specialty", value = form.specialty)
        }

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
                    // TODO: Handle saving this professional to the database (SQLite, Firebase, etc.)
                    // You can use form data from RegisterProfessionalViewModel
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
private fun SummaryItem(label: String, value: String) {
    Column(modifier = Modifier.padding(vertical = 4.dp)) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold)
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}
