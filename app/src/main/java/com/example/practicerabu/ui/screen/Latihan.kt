package com.example.practicerabu.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CommentBank
import androidx.compose.material.icons.filled.Dataset
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.practicerabu.R
import com.example.practicerabu.ui.widgets.BottomListSheet
import com.example.practicerabu.viewmodel.LatihanViewModel


@Composable
fun latihanView(viewModel: LatihanViewModel = viewModel()){

    val galleryList by viewModel.galleryList.collectAsState()
    val bookingList by viewModel.bookingList.collectAsState()
    var showData by remember {mutableStateOf(false)}

    Box(Modifier.fillMaxSize()){
        Column(Modifier.fillMaxSize()){
            Image(painter = painterResource(R.drawable.banner), contentDescription = "imgBanner", contentScale = ContentScale.Crop, modifier = Modifier
                .weight(3f)
                .fillMaxSize())
            LazyRow(Modifier.weight(2f), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                items(galleryList){ item ->
                    Column (horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(vertical = 4.dp)) {
                        Image(painter = painterResource(item.imgRes), contentDescription = "img",  modifier = Modifier
                            .fillMaxSize()
                            .weight(1f))
                        Text(item.name)
                    }
                }

            }
            Text("Spider-Man Home Coming",
                Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Text("Film Spider-Man: Homecoming menceritakan kisah Peter Parker (Tom Holland) yang mencoba menyeimbangkan kehidupannya sebagai siswa SMA di Queens dengan ambisinya menjadi pahlawan super seutuhnya setelah bergabung bersama para Avengers.\n\n" +
                    "Di bawah bimbingan Tony Stark (Robert Downey Jr.) sebagai mentor, Peter diminta untuk menjaga keamanan lingkungan sekitar saja dan tidak terburu-buru menghadapi musuh besar. Namun, ia merasa diremehkan dan bertekad membuktikan diri ketika menemukan ancaman baru di kotanya.\n\n" +
                    "Ancaman tersebut datang dari Adrian Toomes (Michael Keaton) alias Vulture, seorang penjahat yang merakit senjata berbahaya dari teknologi sisa alien Chitauri. Dibantu sahabat setianya, Ned Leeds (Jacob Batalon), Peter secara diam-diam menyelidiki kejahatan Toomes tanpa sepengetahuan Stark.\n\n" +
                    "Konflik memuncak ketika Peter mengetahui bahwa Toomes adalah ayah dari Liz (Laura Harrier), gadis yang ia sukai dan ingin ajak ke pesta dansa sekolah. Pada akhirnya, Peter harus menghentikan rencana jahat Vulture sekaligus membuktikan bahwa dirinya layak menjadi seorang pahlawan sejati.", Modifier
                .weight(8f)
                .verticalScroll(
                    rememberScrollState()
                )
                .padding(horizontal = 8.dp), fontSize = 18.sp)

//            Box(Modifier.padding(16.dp).weight(2f)) {
//                Card(
//                    modifier = Modifier.fillMaxSize(),
//                    colors = CardDefaults.cardColors(containerColor = Color.White),
//                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
//                ) {
//                    Row(Modifier.fillMaxSize().padding(8.dp), Arrangement.SpaceEvenly, ){
//                        Column(Modifier.fillMaxSize().weight(1f), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
//                            Icon(Icons.Filled.ThumbUp, "like", tint = Color.Red)
//                            Text("124 likes")
//                        }
//                        Column(Modifier.fillMaxSize().weight(1f),horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
//                            Icon(Icons.Filled.CommentBank, "like", tint = Color.Red)
//                            Text("18 comments")
//                        }
//                        Column(Modifier.fillMaxSize().weight(1f),horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
//                            Icon(Icons.Filled.Share, "like", tint = Color.Red)
//                            Text("38 shares")
//                        }
//                    }
//                }
//            }
        }
        Row(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ExtendedFloatingActionButton(
                icon = {
                    Icon(
                        Icons.Filled.Add,
                        contentDescription = null
                    )
                },
                text = { Text("Boooking") },
                containerColor = Color.White,
                onClick = {viewModel.openForm()}
            )
            ExtendedFloatingActionButton(
                icon = {
                    Icon(
                        Icons.Filled.Dataset,
                        contentDescription = null
                    )
                },
                text = { Text("Data") },
                containerColor = Color.White,
                onClick = {showData = true}
            )
        }
        if(showData){
            BottomListSheet(
                dataBooking = bookingList,
                onDelete = viewModel::deleteBooking,
                onDismiss = {showData = false}
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LatihanPreview(){
    latihanView()
}