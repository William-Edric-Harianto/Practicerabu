package com.example.practicerabu.ui.screen

import android.graphics.Bitmap
import android.util.Patterns
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.util.loadScaledBitmap


@Composable
fun BookingScreen(
    onSubmit: (name: String, email: String, photo: Bitmap?) -> Unit,
    onCancel: () -> Unit
) {
    val context = LocalContext.current

    var name by rememberSaveable() { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var showError by rememberSaveable { mutableStateOf(false) }
    var photo by remember { mutableStateOf<Bitmap?>(null) } // hanya di memori

    // Photo picker bawaan Android: tidak butuh izin & tidak menyalin file
    val pickPhoto = rememberLauncherForActivityResult (
        ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        uri?.let { photo = loadScaledBitmap(context, it) }
    }
    val launchPicker = {
        pickPhoto.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }

    val nameError = showError && name.isBlank()
    val emailError = showError && !Patterns.EMAIL_ADDRESS.matcher(email.trim()).matches()

    Column (
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            "Form Booking",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

//        BookingAvatar(
//            photo = photo,
//            size = 120.dp,
//            modifier = Modifier.clickable { launchPicker() }
//        )

        photo?.let {
            Image(
                bitmap = it.asImageBitmap(),
                contentDescription = "Foto",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(120.dp).clip(CircleShape).clickable{launchPicker()}
            )
        }
        OutlinedButton (onClick = { launchPicker() }) {
            Text(if (photo == null) "Pilih Foto" else "Ganti Foto")
        }

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nama Pemesan") },
            singleLine = true,
            isError = nameError,
            supportingText = { if (nameError) Text("Nama wajib diisi") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email Pemesan") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            isError = emailError,
            supportingText = { if (emailError) Text("Format email tidak valid") },
            modifier = Modifier.fillMaxWidth()
        )

        Row (
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedButton(onClick = onCancel, modifier = Modifier.weight(1f)) {
                Text("Batal")
            }
            Button (
                onClick = {
                    showError = true
                    if (name.isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(email.trim()).matches()) {
                        onSubmit(name.trim(), email.trim(), photo)
                    }
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Simpan")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BookingScreenPreview() {
    BookingScreen(onSubmit = { _, _, _ -> }, onCancel = {})
}