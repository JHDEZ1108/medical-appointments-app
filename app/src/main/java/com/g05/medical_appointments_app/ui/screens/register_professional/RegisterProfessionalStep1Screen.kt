package com.g05.medical_appointments_app.ui.screens.register_professional

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.g05.medical_appointments_app.ui.components.DotsIndicator
import com.g05.medical_appointments_app.ui.viewmodel.RegisterProfessionalViewModel
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.format.DateTimeFormatter
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController

@Composable
fun RegisterProfessionalStep1Screen(
    navController: NavController,
    viewModel: RegisterProfessionalViewModel
) {
    val context = LocalContext.current

    val imageLoader = ImageLoader.Builder(context)
        .components { add(SvgDecoder.Factory()) }
        .build()

    val dateDialogState = rememberMaterialDialogState()
    val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    val form = viewModel.formData

    val isFormValid = form.firstName.isNotBlank() &&
            form.lastName.isNotBlank() &&
            form.gender.isNotBlank()

    // TODO: [Date of Birth] Review or replace the current DatePicker implementation.
    MaterialDialog(
        dialogState = dateDialogState,
        buttons = {
            positiveButton("OK")
            negativeButton("Cancel")
        }
    ) {
        datepicker(title = "Select your birthdate") { date ->
            viewModel.updateDateOfBirth(date.format(dateFormatter))
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Step 1",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            Text(
                text = "Personal Info",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(12.dp))

            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data("android.resource://${context.packageName}/raw/professional_illustration")
                    .build(),
                contentDescription = "Professional Illustration",
                imageLoader = imageLoader,
                modifier = Modifier
                    .height(160.dp)
                    .padding(8.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = form.firstName,
                onValueChange = { viewModel.updateFirstName(it) },
                label = { Text("First Name") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = form.lastName,
                onValueChange = { viewModel.updateLastName(it) },
                label = { Text("Last Name") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = form.dateOfBirth,
                onValueChange = {},
                readOnly = true,
                label = { Text("Date of Birth") },
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { dateDialogState.show() }
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Gender",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                val selectedColor = MaterialTheme.colorScheme.primary

                OutlinedButton(
                    onClick = { viewModel.updateGender("Male") },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = if (form.gender == "Male") selectedColor else Color.Transparent,
                        contentColor = if (form.gender == "Male") Color.White else MaterialTheme.colorScheme.onBackground
                    )
                ) {
                    Text("Male")
                }

                OutlinedButton(
                    onClick = { viewModel.updateGender("Female") },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = if (form.gender == "Female") selectedColor else Color.Transparent,
                        contentColor = if (form.gender == "Female") Color.White else MaterialTheme.colorScheme.onBackground
                    )
                ) {
                    Text("Female")
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    navController.navigate("step2")
                },
                enabled = isFormValid,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Next")
            }

            Spacer(modifier = Modifier.height(22.dp))

            DotsIndicator(
                totalSteps = 4,
                currentStep = 0,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}
