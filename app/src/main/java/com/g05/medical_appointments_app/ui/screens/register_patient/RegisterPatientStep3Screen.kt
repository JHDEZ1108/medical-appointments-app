package com.g05.medical_appointments_app.ui.screens.register_patient

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.g05.medical_appointments_app.ui.components.DotsIndicator
import com.g05.medical_appointments_app.ui.viewmodel.RegisterPatientViewModel

@Composable
fun RegisterPatientStep3Screen(
    navController: NavController,
    viewModel: RegisterPatientViewModel
) {
    val context = LocalContext.current
    val form = viewModel.formData
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }

    // 📌 TEMPORARY image display before saving to DB
    // When a user selects a photo, we save the URI as a string to the ViewModel for now.
    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        if (uri != null) {
            selectedImageUri = uri
            viewModel.updateProfileImage(uri.toString()) // Store image URI in form
        }
    }

    // Determine default avatar based on gender
    val defaultAvatarRes = if (form.gender == "Female") {
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
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Step 3",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Text(
            text = "Profile Photo",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Show selected photo if available, otherwise fallback to gender-based SVG
        if (selectedImageUri != null) {
            Image(
                painter = rememberAsyncImagePainter(selectedImageUri),
                contentDescription = "Selected Profile Photo",
                modifier = Modifier
                    .size(180.dp)
                    .clip(CircleShape)
                    .padding(8.dp)
            )
        } else {
            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(defaultAvatarRes)
                    .build(),
                contentDescription = "Default Avatar",
                imageLoader = imageLoader,
                modifier = Modifier
                    .size(180.dp)
                    .clip(CircleShape)
                    .padding(8.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                imagePickerLauncher.launch("image/*")
            }
        ) {
            Text("Choose a profile picture")
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
                    // Image URI already saved in ViewModel, ready for Step 4
                    navController.navigate("step4")
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Next")
            }
        }

        Spacer(modifier = Modifier.height(22.dp))

        DotsIndicator(
            totalSteps = 4,
            currentStep = 2,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}
