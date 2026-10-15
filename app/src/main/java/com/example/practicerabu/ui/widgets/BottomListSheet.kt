package com.example.practicerabu.ui.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.practicerabu.model.Booking

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomListSheet(
    dataBooking: List<Booking>,
    onDelete: (index: Int) -> Unit,
    onDismiss: () -> Unit
){
    ModalBottomSheet(onDismissRequest = onDismiss) {
        Text(
            "Data Booking (${dataBooking.size})",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp)
        )

        if(dataBooking.isEmpty()){
            Text("Belum ada data booking.",
                modifier = Modifier.padding(16.dp))
        }else{
            LazyColumn(
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                itemsIndexed(dataBooking){ index, booking ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                    ) {
                        ListItem(
                            colors = ListItemDefaults.colors(containerColor = Color.Transparent),
                            leadingContent = {
                                if(booking.photo != null){
                                    Image(
                                        bitmap = booking.photo.asImageBitmap(),
                                        contentDescription = "Foto",
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier.size(48.dp).clip(CircleShape)
                                    )
                                }else{
                                    Box(
                                        modifier = Modifier.size(48.dp).clip(CircleShape).background(Color.LightGray),
                                        contentAlignment = Alignment.Center
                                    ){
                                        Icon(
                                            Icons.Filled.Person,
                                            contentDescription = "JohnDoe",
                                            modifier = Modifier.size(24.dp)
                                        )
                                    }
                                }
                            },
                            headlineContent = {Text(booking.name, fontWeight = FontWeight.Bold)},
                            supportingContent = {Text(booking.email)},
                            trailingContent = {
                                IconButton(onClick = {onDelete(index)}) {
                                    Icon(Icons.Filled.Delete, "Hapus", tint = Color.Red)
                                }
                            }
                        )
                    }
                }
            }
        }

    }
}