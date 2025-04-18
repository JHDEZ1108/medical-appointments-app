package com.g05.medical_appointments_app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.request.ImageRequest

@Composable
fun AccountTypeSelectionScreen(navController: NavController) {
    val context = LocalContext.current
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
            text = "Choose Account Type",
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        AsyncImage(
            model = ImageRequest.Builder(context)
                .data("android.resource://${context.packageName}/raw/account_type_choice")
                .build(),
            contentDescription = "Different Account Types Illustration",
            imageLoader = imageLoader,
            modifier = Modifier
                .height(180.dp)
                .padding(8.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { navController.navigate("register_patient") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("I'm a Patient")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { navController.navigate("register_professional") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("I'm a Healthcare Professional")
        }
    }
}
